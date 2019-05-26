package com.hzmsc.scada.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.ProductionDao;
import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.utils.OperationUtils;

@Service
public class ProductionServiceImpl implements ProductionService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductionService.class);

	@Autowired
	private ProductionDao productionDao;

	public Production findById(int productionId) {
		return productionDao.findById(productionId);
	}

	public List<Production> findAll() {
		return productionDao.findAll();
	}
	
	public Production findByEquipmentIdLatestBeforeTime(int equipmentId) {
		return productionDao.findByEquipmentIdLatestBeforeTime(equipmentId);
	}
	
	public Production findByEquipmentIdLatestBeforeTime(int equipmentId, Timestamp endTime) {
		return productionDao.findByEquipmentIdLatestBeforeTime(equipmentId, endTime);
	}
	
	public Production findByEquipmentIdFirstAfterTime(int equipmentId) {
		return productionDao.findByEquipmentIdFirstAfterTime(equipmentId);
	}
	
	public Production findByEquipmentIdFirstAfterTime(int equipmentId, Timestamp beginTime) {
		return productionDao.findByEquipmentIdFirstAfterTime(equipmentId, beginTime);
	}
	
	public List<Production> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {

		return productionDao.findByEquipmentIdBetweenTime(equipmentId, beginTime, endTime);
	}
	
	public int countOfEquipmentIdBeforeTime(int equipmentId, Timestamp endTime) {
		return productionDao.countOfEquipmentIdBeforeTime(equipmentId, endTime);
	}

	public int countOfEquipmentIdAfterTime(int equipmentId, Timestamp beginTime) {
		return productionDao.countOfEquipmentIdAfterTime(equipmentId, beginTime);
	}
	
	public int countOfEquipmentId(int equipmentId){
		return productionDao.countOfEquipmentId(equipmentId);
	}
	public int countOfEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime){
		return productionDao.countOfEquipmentIdBetweenTime(equipmentId, beginTime, endTime);
	}
	
	public int countOfId(int productionId) {
		return productionDao.countOfId(productionId);
	}

	public Production createProduction(Production production) {
		return productionDao.createProduction(production);
	}

	public void updateProduction(Production production) {
		productionDao.updateProduction(production);

	}

	public void deleteProduction(Production production) {
		productionDao.deleteProduction(production);

	}

	/**
	 * 计算两个设备状态之间的产量-重量
	 * 重量 = 长度/支数
	 * 获取最近一次的重量，得到累计重量
	 */
	public Production calculateProduction(Production lastProduction, Equipment lastEquipment, Equipment currentEquipment) {
		int yarnCount = currentEquipment.getEquipmentConfigure().getYarnCount();  //纱线支数
		int spindleAmount = currentEquipment.getEquipmentConfigure().getSpindleAmount();  //整机锭数
		//System.out.println("纱线支数:"+yarnCount);
		long lastLength = OperationUtils.catTowHalfWord(lastEquipment.getEquipmentStatus().getYarnLengthHighHalfWord(),
				lastEquipment.getEquipmentStatus().getYarnLengthLowHalfWord());
		
		//System.out.println("旧长度："+lastLength);
		long length = OperationUtils.catTowHalfWord(currentEquipment.getEquipmentStatus().getYarnLengthHighHalfWord(),
				currentEquipment.getEquipmentStatus().getYarnLengthLowHalfWord());
		//System.out.println("长度："+length);
		/*logger.debug("currentEquipment{}:(length-lastLength)/yarnCount: {}-{}/{}",currentEquipment.getEquipmentId(), length, lastLength, yarnCount);
		*/
		if(length >= lastLength){
			
			/*
			 *   设备启动后  长度会每分钟增加 200
			 *   增长长度 = 最终长度 - 初始长度
			 * */
			length = length - lastLength;
		}

		double weight = 0;

		if (yarnCount == 0) {
			logger.debug("weight=length/yarnCount, but yarnCount == 0!");
		} else {
			
			/*
			 *  产量 = 增长长度 / 纱线支数 * 锭数
			 * */
			weight = (double)length / yarnCount * spindleAmount;
			
		}
		/*logger.debug("currentEquipment{}:weight = weight + lastWeight : {}={}+{}",currentEquipment.getEquipmentId(),
				weight + lastProduction.getWeight(),
				weight,
				lastProduction.getWeight());*/
		//System.out.println("原有重量："+lastProduction.getWeight());
		//System.out.println("新增产量："+weight);
		weight = weight + lastProduction.getWeight();//加上最近一次的重量，获得累计重量。
		//System.out.println("最终数值:"+weight);
		Production production = new Production();
		production.setEquipmentId(currentEquipment.getEquipmentId());
		production.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		production.setWeight(weight);

		return production;
	}

	public double getWeight(int equipmentId) {
		double weight = 0;
		if(this.countOfEquipmentId(equipmentId) > 0){
			weight = this.findByEquipmentIdLatestBeforeTime(equipmentId).getWeight() -
					this.findByEquipmentIdFirstAfterTime(equipmentId).getWeight();
		}
		
		return weight;
	}

	public double getWeight(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		double weight = 0;
		if(this.countOfEquipmentIdBetweenTime(equipmentId, beginTime, endTime) > 0){
			weight = this.findByEquipmentIdLatestBeforeTime(equipmentId, endTime).getWeight() -
					this.findByEquipmentIdFirstAfterTime(equipmentId, beginTime).getWeight();
		}
		return weight;
	}
	
	public List<Production> sumequipbyid(String beginDay, String endDay,int equipmentId) {
		
		return productionDao.sumequipbyid(beginDay,endDay,equipmentId);
	}

	public int allproduction(String beginDay, String endDay) {
		
		return productionDao.allproduction(beginDay,endDay);
	}

	public List sumproduction(String beginDay, String endDay) {
		
		return productionDao.sumproduction(beginDay, endDay);
	}

	public List<Production> listproduction() {

		return productionDao.listproduction();
	}

	public List<Object> listsumequipbyid(String beginDay, String endDay, int equipmentId) {
		
		return productionDao.listsumequipbyid(beginDay, endDay, equipmentId);
	}

	public List findbyDateEquipmentId(String beginDay, String endDay, int equipmentId) {
	
		return productionDao.findbyDateEquipmentId(beginDay, endDay, equipmentId);
	}

	public List findbyOneDayEquipmentId(String beginDay, int equipmentId) {
		
		return productionDao.findbyOneDayEquipmentId(beginDay, equipmentId);
	}


}

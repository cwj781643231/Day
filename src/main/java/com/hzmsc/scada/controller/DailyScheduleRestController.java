package com.hzmsc.scada.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzmsc.scada.entity.DailySchedule;
import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.entity.Event;
import com.hzmsc.scada.entity.PageBean;
import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.service.DailyScheduleService;
import com.hzmsc.scada.service.ProductionService;

@RestController
public class DailyScheduleRestController {

	@Autowired
	private DailyScheduleService dailyScheduleService;
	
	@Autowired
	private ProductionService productionService;
	
	PageBean pageBean = new PageBean();
	
	@RequestMapping(value = "/dailySchedule", method = RequestMethod.GET)
	public List<DailySchedule> dailyScheduleAll(){
		return dailyScheduleService.findAll();
	}
	
	@RequestMapping(value = "/dailySchedule/{scheduleDay}", method = RequestMethod.GET)
	public List<DailySchedule> dailyScheduleByDay(@PathVariable String scheduleDay){
		return dailyScheduleService.findByDay(scheduleDay);
	}
	@RequestMapping(value = "/dailySchedule/{beginDay}/{endDay}", method = RequestMethod.GET)
	public List<DailySchedule> dailyScheduleBetweenDays(@PathVariable String beginDay, @PathVariable String endDay){
		//System.out.println("王老师的"+dailyScheduleService.findBetweenDays(beginDay, endDay));
		//System.out.println(dailyScheduleService.findBetweenDays(beginDay, endDay).size());
		return dailyScheduleService.findBetweenDays(beginDay, endDay);
	}

	@RequestMapping(value = "/dailySchedule", method = RequestMethod.PUT)
	public List<DailySchedule> dailyScheduleUpdate(@RequestBody DailySchedule dailySchedule){
		//System.out.println("DailyScheduleRestController:/dailySchedule.post"+dailySchedule);
		dailyScheduleService.updateDailySchedule(dailySchedule);
		return dailyScheduleService.findAll();
	}
	
	@RequestMapping(value = "/dailySchedule", method = RequestMethod.POST)
	public List<DailySchedule> dailyScheduleCreate(@RequestBody DailySchedule dailySchedule){
		//System.out.println("DailyScheduleRestController:/dailySchedule.post"+dailySchedule);
		//System.out.println("wozaizhe我在这");
		dailyScheduleService.createDailySchedule(dailySchedule);
		return dailyScheduleService.findAll();
	}
	
	@RequestMapping(value = "/dailyScheduleDelete", method = RequestMethod.POST)
	public List<DailySchedule> dailyScheduleDelete(@RequestBody DailySchedule dailySchedule){
		//System.out.println("DailyScheduleRestController:/dailySchedule.post"+dailySchedule);
		dailyScheduleService.deleteDailySchedule(dailySchedule);
		return dailyScheduleService.findAll();
	}
	
	@RequestMapping(value = "/dailyScheduleDelete/{beginDate}/{endDate}", method = RequestMethod.POST)
	public List<DailySchedule> dailyScheduleMassDelete(@PathVariable String beginDate, @PathVariable String endDate){
		//System.out.println("DailyScheduleRestController:/dailySchedule.post"+dailySchedule);
		dailyScheduleService.deletePeriodSchedule(beginDate, endDate);
		return dailyScheduleService.findAll();
	}
	
	//计算重量，保存到dailySchdule里
	@RequestMapping(value = "/calculateWeight", method = RequestMethod.POST)
	public List<DailySchedule> productionCalculateWeight(@RequestBody DailySchedule dailySchedule) {
		// System.out.println("ProductionRestController:/production.post"+dailySchedule);
		float production = (float)productionService.getWeight(dailySchedule.getEquipmentId(), dailySchedule.getBeginTimestamp(),
				dailySchedule.getEndTimestamp());
		dailySchedule.setProduction(production);
		dailyScheduleService.updateDailySchedule(dailySchedule);
		return dailyScheduleService.findAll();
	}
	
	
	/*//查询出生产总量
	@RequestMapping(value = "/sumproduction/{beginDay}/{endDay}", method = RequestMethod.GET)
	public List<DailySchedule> dailyScheduleSumBetweenDays(@PathVariable String beginDay, @PathVariable String endDay){
		
		//查询出该时间段内设备信息
		List<DailySchedule> list= dailyScheduleService.sumproduction(beginDay, endDay);
		//System.out.println("查询设备总产量时传入的时间："+beginDay+"~~~"+ endDay);
		
		//查询出该时间段内每台设备的总产量
		List<Object> listsum = dailyScheduleService.listint(beginDay, endDay);
		//System.out.println("设备总产量"+listsum);
		
		for (int i = 0; i < list.size(); i++) {
			DailySchedule d = list.get(i);
			for (int p = 0; p < listsum.size(); p++) {

				  Map map = (Map) listsum.get(i); 
 				  d.setListequipsum(map.get("c"));
 				  
			}	
		}
		
		//System.out.println("设备信息"+dailyScheduleService.sumproduction(beginDay, endDay));
		return list;
	}*/
	//根据设备ID查询出该设备每天的总产量      
	/*
	 * 现修改代码
	 * */
		/*@RequestMapping(value = "/sumequipbyid/{beginDay}/{endDay}/{equipmentId}", method = RequestMethod.GET)
		public List<Production> findbyid(@PathVariable String beginDay, @PathVariable String endDay,@PathVariable int equipmentId){
			//查询出这台机器的工作 天数
			//System.out.println("设备详情ID："+equipmentId);
			List<Production> listsum= productionService.sumequipbyid(beginDay, endDay, equipmentId);
			
			List<Object> listsumequipby = dailyScheduleService.listsumequipbyid(beginDay, endDay, equipmentId);
			for (int i = 0; i < listsum.size(); i++) {
				Production d = listsum.get(i);
				
				for (int p = 0; p < listsumequipby.size(); p++) {

					  Map map = (Map) listsumequipby.get(i); 
	 				  d.setListsumbyid(map.get("c"));
				}	
				}	
			return listsum;
		}*/
		
  /*--------------------------  员工   ------------------------------*/
		//查询  每一位员工的生产总量
		@RequestMapping(value = "/sumemployeesta/{beginDay}/{endDay}", method = RequestMethod.GET)
		public List<DailySchedule> sumemployeesta(@PathVariable String beginDay, @PathVariable String endDay){
			List<DailySchedule> listemp= dailyScheduleService.sumemployee(beginDay, endDay);
			List<Object> listsumemp = dailyScheduleService.sumempsta(beginDay, endDay);
			//System.out.println("listemp1:"+listemp);
			for (int i = 0; i < listemp.size(); i++) {
				DailySchedule d = listemp.get(i);
				for (int p = 0; p < listsumemp.size(); p++) {

					  Map map = (Map) listsumemp.get(i); 
	 				  d.setListequipsum(map.get("c"));
				}	
			}
			//System.out.println("listemp2:"+listemp);
			return listemp;
		}
		
		//根据员工ID查询出该设备每天的总产量
			@RequestMapping(value = "/sumemployeestabyid/{beginDay}/{endDay}/{employeeId}", method = RequestMethod.GET)
			public List<DailySchedule> sumemployeestabyid(@PathVariable String beginDay, @PathVariable String endDay,@PathVariable int employeeId){
				//查询出这台机器的工作 天数
				List<DailySchedule> listempbyid= dailyScheduleService.sumempbyid(beginDay, endDay, employeeId);
				List<Object> listsumempbyid = dailyScheduleService.listsumempbyid(beginDay, endDay, employeeId);

				for (int i = 0; i < listempbyid.size(); i++) {
					DailySchedule d = listempbyid.get(i);
					
					for (int p = 0; p < listsumempbyid.size(); p++) {

						  Map map = (Map) listsumempbyid.get(i); 
		 				  d.setListsumbyid(map.get("c"));
		 				  
					}	
					}	
				return listempbyid;
			}
	
			/*
			 * 所有员工的最终产量之和
			 * */
			@RequestMapping(value = "/allempdaily/{beginDay}/{endDay}", method = RequestMethod.GET)
			public int allempdaily(@PathVariable String beginDay, @PathVariable String endDay){
		        
				//int all = productionService.allproduction(beginDay, endDay);
				List<DailySchedule> listemp= dailyScheduleService.sumemployee(beginDay, endDay);
				List<Object> listsumemp = dailyScheduleService.sumempsta(beginDay, endDay);
				int all = 0;
				
					for (int p = 0; p < listsumemp.size(); p++) {
						  Map map = (Map) listsumemp.get(p); 
						  int Weight = Float.valueOf(String.valueOf(map.get("c"))).intValue();
						
						  all = all + Weight;
					}	
				
				return all;
			}
			
	 //总页数
	 @RequestMapping(value = "/getcountBytime/{beginTime}/{endTime}", method = RequestMethod.GET)
			public PageBean countBytime(@PathVariable String beginTime, @PathVariable String endTime){
				
		        System.out.println("查看: "+dailyScheduleService.countByTime(beginTime, endTime));
				int countByTime = dailyScheduleService.countByTime(beginTime, endTime);
			    
				pageBean.setTotalRecords(countByTime);
				pageBean.getTotalPages();
				return pageBean;
			}
			
	 @RequestMapping(value = "/getcountBytime/{beginTime}/{endTime}/{equipmentId}", method = RequestMethod.GET)
			public PageBean countBytimeId(@PathVariable String beginTime, @PathVariable String endTime, @PathVariable int equipmentId){
				
				int countByTime = dailyScheduleService.countByTimeId(equipmentId, beginTime, endTime);
				pageBean.setTotalRecords(countByTime);
				pageBean.setTotalRecords(countByTime);
				pageBean.getTotalPages();
				return pageBean;
			}
	 @RequestMapping(value = "/getdailySchedule/{beginDay}/{endDay}", method = RequestMethod.GET)
		public List<DailySchedule> getdailyScheduleBetweenDays(@PathVariable String beginDay, @PathVariable String endDay){
			System.out.println("！！！！！！！！！！！！！！！！！！！！！！");
		    //改时间段内总记录数
			int countByTime = dailyScheduleService.countByTime(beginDay, endDay);
			pageBean.setTotalRecords(countByTime);
		 
		    return dailyScheduleService.getfindBetweenDays(beginDay, endDay, pageBean.getCurrentPage()-1, pageBean.getPageSize());
	}	
	 
	 @RequestMapping(value = "/getdailySchedule/{beginDay}/{endDay}/{equipmentId}", method = RequestMethod.GET)
		public List<DailySchedule> getdailyScheduleBetweenDaysById(@PathVariable String beginDay, @PathVariable String endDay, @PathVariable int equipmentId){
			
		    //改时间段内总记录数
			int countByTime = dailyScheduleService.countByTime(beginDay, endDay);
			pageBean.setTotalRecords(countByTime);
		 
		    return dailyScheduleService.getfindBetweenDaysById(equipmentId,beginDay, endDay, pageBean.getCurrentPage()-1, pageBean.getPageSize());
	}	
	 
	 /*
		 * 当进行页面跳转   无设备ID
		 * */
		@RequestMapping(value = "/countdaily/{beginTime}/{endTime}/{currentPage}", method = RequestMethod.GET)
		public List<DailySchedule> dailyScheduleBetweenTime(@PathVariable String beginTime, @PathVariable String endTime, @PathVariable int currentPage){
			//当前页数  currentPage
			if(currentPage -1 != 0){
				int a = (currentPage-1)*20+1;
				
				List<DailySchedule> listdaily = dailyScheduleService.getfindBetweenDays(beginTime, endTime, 
						a, pageBean.getPageSize());	
				return listdaily;
			}else{
				
				 List<DailySchedule> listdaily = dailyScheduleService.getfindBetweenDays(beginTime, endTime,
						 currentPage-1, pageBean.getPageSize());
				return listdaily;
				
			}
		}
		
		/*
		 * 当进行页面跳转   有设备ID
		 * */
		@RequestMapping(value = "/countdaily/{beginTime}/{endTime}/{equipmentId}/{currentPage}", method = RequestMethod.GET)
		public List<DailySchedule> dailyScheduleBetweenTimeById(@PathVariable String beginTime, @PathVariable String endTime,@PathVariable int equipmentId, @PathVariable int currentPage){
			//当前页数  currentPage
			if(currentPage -1 != 0){
				
				int a = (currentPage-1)*10+1;
				List<DailySchedule> listdaily = dailyScheduleService.getfindBetweenDaysById(equipmentId, beginTime, endTime, 
						a, pageBean.getPageSize());	
				return listdaily;
			}else{
				
				 List<DailySchedule> listdaily = dailyScheduleService.getfindBetweenDaysById(equipmentId, beginTime, endTime,
						 currentPage-1, pageBean.getPageSize());
				return listdaily;
				
			}
		}
}

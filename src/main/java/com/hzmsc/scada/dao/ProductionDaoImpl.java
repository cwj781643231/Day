package com.hzmsc.scada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.DailySchedule;
import com.hzmsc.scada.entity.Production;

@Repository
public class ProductionDaoImpl implements ProductionDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Production findById(int productionId) {
		String sql = "select * from production where productionId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { productionId }, new ProductionMapper());
	}
	
	public List<Production> findAll() {
		String sql = "select * from production";
		return this.jdbcTemplate.query(sql, new ProductionMapper());
	}
	
	public Production findByEquipmentIdLatestBeforeTime(int equipmentId) {
		String sql = "select * from production where equipmentId=? order by productionId desc limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId }, new ProductionMapper());
	}
	
	public Production findByEquipmentIdLatestBeforeTime(int equipmentId, Timestamp endTime) {
		String sql = "select * from production where equipmentId=? and createdTime<? order by productionId desc limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId, endTime }, new ProductionMapper());
	}
	
	/*
	 * 2016-12-29  因做数据报表  修改sql语句    原sql语句 因  多了一个and  所以语法不对
	 * */
	public Production findByEquipmentIdFirstAfterTime(int equipmentId) {
		//String sql = "select * from production where equipmentId=? and order by productionId limit 1";
		String sql = "select * from production where equipmentId=? order by productionId limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId }, new ProductionMapper());
	}
	
	public Production findByEquipmentIdFirstAfterTime(int equipmentId, Timestamp beginTime) {
		String sql = "select * from production where equipmentId=? and createdTime>=? order by productionId limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId, beginTime }, new ProductionMapper());
	}	
	
	public List<Production> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		String sql = "select * from production where equipmentId=? and createdTime between ? and ?";
		return this.jdbcTemplate.query(sql,  new Object[] { equipmentId, beginTime, endTime}, new ProductionMapper());
	}	
	
	public int countOfId(int productionId) {
		String sql = "select count(*) from production where productionId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, productionId);
	}
	
	public int countOfEquipmentIdBeforeTime(int equipmentId, Timestamp endTime) {
		String sql = "select count(*) from production where equipmentId=? and createdTime<?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId, endTime);
	}
	
	public int countOfEquipmentIdAfterTime(int equipmentId, Timestamp beginTime) {
		String sql = "select count(*) from production where equipmentId=? and createdTime>=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId, beginTime);
	}
	
	public int countOfEquipmentId(int equipmentId) {
		String sql = "select count(*) from production where equipmentId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId);
	}
	
	public int countOfEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		String sql = "select count(*) from production where equipmentId=? and createdTime between ? and ?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId, beginTime, endTime);
	}		
	
	public int countAll() {
		String sql = "select count(*) from production";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Production createProduction(final Production production) {

		final String sql = "INSERT INTO `production` ("
				+ "`productionName`, `equipmentId`, `createdTime`, `modifiedTime`,	`weight`, `designedWeight`" 
				+ ") VALUES (?, ?, ?, ?, ?, ?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "productionId" });
				ps.setString(1, production.getProductionName());
				ps.setInt(2, production.getEquipmentId());
				ps.setTimestamp(3, production.getCreatedTime());
				ps.setTimestamp(4, production.getModifiedTime());
				ps.setDouble(5, production.getWeight());
				ps.setFloat(6, production.getDesignedWeight());

				return ps;
			}
		}, keyHolder);

		production.setProductionId(keyHolder.getKey().intValue());
		return production;
	}

	public void updateProduction(Production production) {
		String sql = "update `production` set "
				+ "`productionName`=?, `equipmentId`=?, "
				+ "`createdTime`=?, `modifiedTime`=?, `weight`=?, `designedWeight`=? "
				+ "where productionId=?";
		this.jdbcTemplate.update(sql, production.getProductionName(), production.getEquipmentId(), 
				production.getCreatedTime(), production.getModifiedTime(), 
				production.getWeight(),	production.getDesignedWeight(), production.getProductionId());

	}

	public void deleteProduction(Production production) {
		String sql = "delete from dailyschedule where production=?";
		this.jdbcTemplate.update(sql, production.getProductionId());

	}
	
	//点击详情按钮   查询该设备  某个时间段的产量信息
		public List<Production> sumequipbyid(String beginDay, String endDay,int equipmentId){
			//String sql = "select * from dailyschedule  where scheduleDay between ? and ? and equipmentId=? GROUP BY scheduleDay";
			/*
			 * 如果用 between  and  那么   右边界的时间信息不回查询出来  所以改用  >=
			 * */
			//String sql = "select * from production  where createdTime between ? and ? and equipmentId=? GROUP BY createdTime";

			String sql = "select * from production  where createdTime >= ? and createdTime <= ? and equipmentId=? GROUP BY createdTime";

			return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay,equipmentId }, new ProductionMapper());
	}
	
	private static final class ProductionMapper implements RowMapper<Production> {
		public Production mapRow(ResultSet rs, int rowNum) throws SQLException {
			Production production = new Production();
			production.setProductionId(rs.getInt("productionId"));
			production.setProductionName(rs.getString("productionName"));
			production.setEquipmentId(rs.getInt("equipmentId"));
			production.setCreatedTime(rs.getTimestamp("createdTime"));
			production.setModifiedTime(rs.getTimestamp("modifiedTime"));
			production.setWeight(rs.getDouble("weight"));
			production.setDesignedWeight(rs.getFloat("designedWeight"));
			return production;
		}
	}

	public Timestamp findLatestCreatedTime() {
		String sql = "select createdTime from production order by productionId desc limit 1";
		return this.jdbcTemplate.queryForObject(sql, Timestamp.class);
	}

	/*----------------员工----------------------------------*/
	/*public List<Production> sumemployee(String beginDay, String endDay){
		String sql = "select d.* from production d where d.createdTime between ? and ? group by d.equipmentId";
		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay }, new ProductionMapper());
	}
	public List sumempsta(String beginDay, String endDay){
		String sql = "select sum(production) c from production d where d.scheduleDay between ? and ? group by d.employeeId";
		List<Object> list = new ArrayList();
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay });
	}
	
	public List<Production> sumempbyid(String beginDay, String endDay,int employeeId){
		String sql = "select * from production  where scheduleDay between ? and ? and employeeId=? GROUP BY scheduleDay";
		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay,employeeId }, new ProductionMapper());
	}
	
	 * 现修改  查询表
	 * 
	public List listsumempbyid(String beginDay, String endDay, int employeeId){
		//String sql = "select sum(production) c from dailyschedule d where d.scheduleDay between ? and ? and employeeId=? GROUP BY scheduleDay";
		
		String sql = "select sum(weight) c from production p where p.createdTime between ? and ? p.equipmentId in"
				+ "(select equipmentId from dailyschedule d where d.scheduleDay between ? and ? and employeeId=? GROUP BY scheduleDay)"
				+ "GROUP BY p.createdTime";
		
		List<Object> list = new ArrayList();
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay,employeeId});
	}*/

    /*
     * 数据报表   
     * */
	
	/*
	 * 所有设备的最终产量之和
	 * */
	public int allproduction(String beginDay, String endDay) {
		//String sql = "select sum(production) from dailyschedule where scheduleDay between ? and ? ";
		
		//String sql = "select SUM(m) from (select p.equipmentId,max(weight) m from production p where createdTime between ? and ? GROUP BY p.equipmentId) a ";
		
		String sql = "select SUM(m) from (select p.equipmentId,max(weight) m from production p where date_format(createdtime,'%Y-%m-%d') >= ? and date_format(createdtime,'%Y-%m-%d') <=? GROUP BY p.equipmentId) a ";

		if(this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { beginDay,endDay })==null){
			return 0;
		}else{
		return this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { beginDay,endDay });
	}
	}
	
	/*
	 * 每台设备的名称    以及该时间内的最初产量和最终产量  来计算该时间段内的产量值
	 * */
	public List sumproduction(String beginDay, String endDay){
		String sql = "select p.*,max(weight) m,MIN(weight) i,d.equipmentName from production p " 
				+ " left join dailyschedule d "
				+ " on p.equipmentId = d.equipmentId "
				//+ " where p.createdTime between ? and ? GROUP BY p.equipmentId";
				+ " where date_format(p.createdtime,'%Y-%m-%d') >= ? and date_format(p.createdtime,'%Y-%m-%d') <= ? GROUP BY p.equipmentId";
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay});
	}

	/*
	 * 在production表  查询出   每台的设备信息
	 * */
	public List<Production> listproduction() {
		String sql = "select * from production GROUP BY equipmentId";
		return this.jdbcTemplate.query(sql, new ProductionMapper());
	}

	/*
	 * 根据设备ID查询出该设备   在某个时间段的每天产量
	 * */
	public List listsumequipbyid(String beginDay, String endDay, int equipmentId) {
		
	    //String sql = "select sum(production) c from dailyschedule d where d.scheduleDay between ? and ? and equipmentId=? GROUP BY scheduleDay";
		
		//String sql = "select sum(weight) c from production d where d.createdTime between ? and ? and equipmentId=? GROUP BY createdTime";
	
		String sql = "select sum(weight) c from production d where date_format(d.createdtime,'%Y-%m-%d') >= ? and date_format(d.createdtime,'%Y-%m-%d') <= ? and equipmentId=? GROUP BY createdTime";

		List<Object> list = new ArrayList();
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay,equipmentId});
	}

	/*
	 * 根据设备ID查询  和时间   查询出该设备在规定的时间段有哪几天工作了
	 * */
	public List findbyDateEquipmentId(String beginDay, String endDay, int equipmentId) {
		
		String sql = "select distinct LEFT(createdTime,10) createdTime, equipmentId from production  "
				//+ " where createdTime between ? and ? "
				//+ " where createdTime >= ? and createdTime <= ? "
				+ " where date_format(createdtime,'%Y-%m-%d') >= ? "
				+ " and date_format(createdtime,'%Y-%m-%d') <= ? "
				+ " and equipmentId=? group by createdTime";
		//System.out.println("sql: " + sql);		
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay,equipmentId});
		//return this.jdbcTemplate.query(sql, new ProductionMapper());
	}

	/*
	 * 根据设备ID查询  和时间   查询出该设备在规定的哪一天的生产量
	 * */
	public List findbyOneDayEquipmentId(String beginDay, int equipmentId) {

		//beginDay = beginDay+"%";
		
	   String sql = "select MAX(weight) m,MIN(weight) i,d.equipmentName "
	   		+ " from production p "
	   		+ " LEFT JOIN dailyschedule d "
	   		+ " on p.equipmentId = d.equipmentId "
	   		/*+ " where p.createdTime LIKE '?' "*/
	   		+" where date_format(p.createdtime,'%Y-%m-%d')= ? "
	   		+ " and p.equipmentId=? ";

	    // return this.jdbcTemplate.queryForObject(sql, new Object[] { beginDay,equipmentId }, new ProductionMapper());
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, equipmentId});

	}

	
	 /*
     * 数据报表   员工方面
     * */
	
	/*
	 * 所有设备的最终产量之和
	 * */
/*	public List<DailySchedule> sumemployee(String beginDay, String endDay) {
		String sql = "select d.* from dailyschedule d where d.scheduleDay between ? and ? group by d.employeeId";
		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay }, new DailyScheduleMapper());
	}

	public List<Object> sumempsta(String beginDay, String endDay) {
		return null;
	}*/
}

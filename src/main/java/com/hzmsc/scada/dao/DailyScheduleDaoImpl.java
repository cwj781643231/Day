package com.hzmsc.scada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.DailySchedule;

@Repository
public class DailyScheduleDaoImpl implements DailyScheduleDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public DailySchedule findById(int dailyScheduleId) {
		String sql = "select * from dailyschedule where dailyScheduleId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { dailyScheduleId }, new DailyScheduleMapper());
	}

	public int countOfId(int dailyScheduleId) {
		String sql = "select count(*) from dailyschedule where dailyScheduleId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, dailyScheduleId);
	}

	public List<DailySchedule> findAll() {
		String sql = "select * from dailyschedule";
		return this.jdbcTemplate.query(sql, new DailyScheduleMapper());
	}
	
	public List<DailySchedule> findByDay(String scheduleDay) {
		String sql = "select * from dailyschedule where scheduleDay=?";
		return this.jdbcTemplate.query(sql, new Object[] { scheduleDay }, new DailyScheduleMapper());
	}
	
	/*
	 * 王老师的sql   2017-01-03  修改sql语句
	 * */
	public List<DailySchedule> findBetweenDays(String beginDay, String endDay) {
		
		//String sql = "select * from dailyschedule where scheduleDay between ? and ?";
		String sql = "select * from dailyschedule where scheduleDay >= ? and scheduleDay <= ?";
		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay }, new DailyScheduleMapper());
	}

	public DailySchedule createDailySchedule(final DailySchedule dailySchedule) {

		final String sql = "INSERT INTO `dailyschedule` ("
				+ "`dailyscheduleName`, `scheduleDay`, `scheduleTypeId`, `scheduleTypeName`, `beginTime`, "
				+ "`endTime`, `employeeId`, `employeeName`, `equipmentId`, `equipmentName`, "
				+ "`production`, `createdTime`, `modifiedTime`,	`createdUser`, `modifiedUser`" 
				+ ") VALUES ("
				+ "?, ?, ?, ?, ?, " 
				+ "?, ?, ?, ?, ?, " 
				+ "?, ?, ?, ?, ?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "dailyScheduleId" });
				ps.setString(1, dailySchedule.getDailyScheduleName());
				ps.setDate(2, dailySchedule.getScheduleDay());
				ps.setInt(3, dailySchedule.getScheduleTypeId());
				ps.setString(4, dailySchedule.getScheduleTypeName());
				ps.setTime(5, dailySchedule.getBeginTime());
				ps.setTime(6, dailySchedule.getEndTime());
				ps.setInt(7, dailySchedule.getEmployeeId());
				ps.setString(8, dailySchedule.getEmployeeName());
				ps.setInt(9, dailySchedule.getEquipmentId());
				ps.setString(10, dailySchedule.getEquipmentName());
				ps.setFloat(11, dailySchedule.getProduction());
				ps.setTimestamp(12, dailySchedule.getCreatedTime());
				ps.setTimestamp(13, dailySchedule.getModifiedTime());
				ps.setInt(14, dailySchedule.getCreatedUser());
				ps.setInt(15, dailySchedule.getModifiedUser());

				return ps;
			}
		}, keyHolder);

		dailySchedule.setDailyScheduleId(keyHolder.getKey().intValue());
		return dailySchedule;
	}

	public void updateDailySchedule(DailySchedule dailySchedule) {
		String sql = "update `dailyschedule` set "
				+ "`dailyscheduleName`=?, `scheduleDay`=?, `scheduleTypeId`=?, `scheduleTypeName`=?, `beginTime`=?, "
				+ "`endTime`=?, `employeeId`=?, `employeeName`=?, `equipmentId`=?, `equipmentName`=?, "
				+ "`production`=?, `createdTime`=?, `modifiedTime`=?, `createdUser`=?, `modifiedUser`=? "
				+ "where dailyScheduleId=?";
		this.jdbcTemplate.update(sql, dailySchedule.getDailyScheduleName(), dailySchedule.getScheduleDay(),
				dailySchedule.getScheduleTypeId(), dailySchedule.getScheduleTypeName(), dailySchedule.getBeginTime(),
				dailySchedule.getEndTime(), dailySchedule.getEmployeeId(), dailySchedule.getEmployeeName(),
				dailySchedule.getEquipmentId(), dailySchedule.getEquipmentName(), dailySchedule.getProduction(),
				dailySchedule.getCreatedTime(), dailySchedule.getModifiedTime(), dailySchedule.getCreatedUser(),
				dailySchedule.getModifiedUser(), dailySchedule.getDailyScheduleId());

	}

	public void deleteDailySchedule(DailySchedule dailySchedule) {
		String sql = "delete from dailyschedule where dailySchedule=?";
		this.jdbcTemplate.update(sql, dailySchedule.getDailyScheduleId());

	}
	
	public void deletePeriodSchedule(String beginDate, String endDate){
		String sql = "delete from dailyschedule where scheduleDay between ? and ?";
		this.jdbcTemplate.update(sql, beginDate, endDate);
	}

	private static final class DailyScheduleMapper implements RowMapper<DailySchedule> {
		public DailySchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
			DailySchedule dailySchedule = new DailySchedule();
			dailySchedule.setDailyScheduleId(rs.getInt("dailyScheduleId"));
			dailySchedule.setDailyScheduleName(rs.getString("dailyScheduleName"));
			dailySchedule.setScheduleDay(rs.getDate("scheduleDay"));
			dailySchedule.setScheduleTypeId(rs.getInt("scheduleTypeId"));
			dailySchedule.setScheduleTypeName(rs.getString("scheduleTypeName"));
			dailySchedule.setBeginTime(rs.getTime("beginTime"));
			dailySchedule.setEndTime(rs.getTime("endTime"));
			dailySchedule.setEmployeeId(rs.getInt("employeeId"));
			dailySchedule.setEmployeeName(rs.getString("employeeName"));
			dailySchedule.setEquipmentId(rs.getInt("equipmentId"));
			dailySchedule.setEquipmentName(rs.getString("equipmentName"));
			dailySchedule.setProduction(rs.getFloat("production"));
			dailySchedule.setCreatedTime(rs.getTimestamp("createdTime"));
			dailySchedule.setModifiedTime(rs.getTimestamp("modifiedTime"));
			dailySchedule.setCreatedUser(rs.getInt("createdUser"));
			dailySchedule.setModifiedUser(rs.getInt("modifiedUser"));
			
			//设备总产量
			return dailySchedule;
		}
	}


	
	//根据时间    查询出每台设备的总产量
	/*public List listint(String beginDay, String endDay){
		//System.out.println("sql:"+beginDay+endDay);
		//String sql = "select sum(production) c from dailyschedule d where d.scheduleDay between ? and ? group by d.equipmentId";
		
		
		 * 产量查询有误    换表查询
		 * 
		String sql = "select sum(weight) c from production d where d.createdTime between ? and ? group by d.equipmentId";
		
		List<Object> list = new ArrayList();
		//System.out.println("我在service："+this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay }));
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay });
	}*/
	
	//点击详情按钮   查询该设备  某个时间段的产量信息
	public List<DailySchedule> sumequipbyid(String beginDay, String endDay,int equipmentId){
		String sql = "select * from dailyschedule  where scheduleDay >= ? and scheduleDay <=? and equipmentId=? GROUP BY scheduleDay";
		//String sql = "select * from production  where createdTime between ? and ? and equipmentId=? GROUP BY createdTime";

		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay,equipmentId }, new DailyScheduleMapper());
	}
	/*
	 * 现执行修改    查询表
	 * */
	public List listsumequipbyid(String beginDay, String endDay, int equipmentId){
		//String sql = "select sum(production) c from dailyschedule d where d.scheduleDay between ? and ? and equipmentId=? GROUP BY scheduleDay";
		
		String sql = "select sum(weight) c from production d where d.createdTime >= ? and d.createdTime <=? and equipmentId=? GROUP BY createdTime";
		
		List<Object> list = new ArrayList();
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay,equipmentId});
	}
	/*----------------员工----------------------------------*/
	public List<DailySchedule> sumemployee(String beginDay, String endDay){
		String sql = "select d.* from dailyschedule d where scheduleDay >=? and d.scheduleDay <=? group by d.employeeId";
		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay }, new DailyScheduleMapper());
	}
	
	
	public List sumempsta(String beginDay, String endDay){
		String sql = "select sum(production) c from dailyschedule d where d.scheduleDay >=? and d.scheduleDay <=? group by d.employeeId";
		List<Object> list = new ArrayList();
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay });
	}
	
	public List<DailySchedule> sumempbyid(String beginDay, String endDay,int employeeId){
		String sql = "select * from dailyschedule  where scheduleDay >=? and scheduleDay <=? and employeeId=? GROUP BY scheduleDay";
		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay,employeeId }, new DailyScheduleMapper());
	}
	/*
	 * 现修改  查询表
	 * */
	public List listsumempbyid(String beginDay, String endDay, int employeeId){
		String sql = "select sum(production) c from dailyschedule d where d.scheduleDay >=? and d.scheduleDay <=? and employeeId=? GROUP BY scheduleDay";
		
		/*String sql = "select sum(weight) c from production p where p.createdTime between ? and ? p.equipmentId in"
				+ "(select equipmentId from dailyschedule d where d.scheduleDay between ? and ? and employeeId=? GROUP BY scheduleDay)"
				+ "GROUP BY p.createdTime";*/
		
		List<Object> list = new ArrayList();
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay,employeeId});
	}

	public List<DailySchedule> schedulingTime(String scheduleDay,int equipmentId) {
		String sql = "select * from dailyschedule where scheduleDay=? and equipmentId=?";
		/*DateTime dateTime = new DateTime(scheduleDay);
		dateTime.toDate();*/
		return this.jdbcTemplate.query(sql,  new Object[] { scheduleDay,equipmentId}, new DailyScheduleMapper());
	
	}
	
	/*
	 * 某一时间内 所有设备的总数据量
	 * */
	public int countByTime(String beginTime, String endTime) {
		String sql = "select count(*) from dailyschedule where scheduleDay between ? and ? ";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { beginTime, endTime});
	}
	
	/*
	 * 某一时间内 单一设备的总数据量
	 * */
	public int countByTimeId(int equipmentId,String beginTime, String endTime) {
		String sql = "select count(*) from dailyschedule where equipmentId = ? and scheduleDay between ? and ? ";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { equipmentId, beginTime, endTime});
	}
	
	public List<DailySchedule> getfindBetweenDays(String beginDay, String endDay,int startIndex,int pageSize) {
		
		//String sql = "select * from dailyschedule where scheduleDay between ? and ?";
		String sql = "select * from dailyschedule where scheduleDay >= ? and scheduleDay <= ? limit ?,?";
		return this.jdbcTemplate.query(sql, new Object[] { beginDay, endDay, startIndex, pageSize }, new DailyScheduleMapper());
	}
	
    public List<DailySchedule> getfindBetweenDaysById(int equipmentId, String beginDay, String endDay,int startIndex,int pageSize) {
		
		//String sql = "select * from dailyschedule where scheduleDay between ? and ?";
		String sql = "select * from dailyschedule where equipmentId = ? and scheduleDay >= ? and scheduleDay <= ? limit ?,?";
		return this.jdbcTemplate.query(sql, new Object[] { equipmentId, beginDay, endDay, startIndex, pageSize }, new DailyScheduleMapper());
	}
}


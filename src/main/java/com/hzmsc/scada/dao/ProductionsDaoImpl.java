package com.hzmsc.scada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.Production;
import com.hzmsc.scada.entity.Productions;

@Repository
public class ProductionsDaoImpl implements ProductionsDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Productions createProductions(final Productions production) {

		final String sql = "INSERT INTO `productions` ("
				+ "`productionName`, `equipmentId`, `scheduleTypeId`, `createdTime`, `earlyWeight`,	`midWeight`, `lateWeight`, `lastWeight`, `elapsedTime`" 
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "productionId" });
				ps.setString(1, production.getProductionName());
				ps.setInt(2, production.getEquipmentId());
				ps.setInt(3, production.getScheduleTypeId());
				ps.setTimestamp(4, production.getCreatedTime());
				ps.setDouble(5, production.getEarlyWeight());
				ps.setDouble(6, production.getMidWeight());
				ps.setDouble(7, production.getLateWeight());
				ps.setDouble(8, production.getLastWeight());
				ps.setTimestamp(9, production.getElapsedTime());
				return ps;
			}
		}, keyHolder);

		production.setProductionId(keyHolder.getKey().intValue());
		return production;
	}

	public void updateProductions(Productions production) {
		String sql = "update `productions` set "
				+ "`productionName`=?, `equipmentId`=?, "
				+ "`createdTime`=?, `earlyWeight`=?, `midWeight`=?, `lateWeight`=?,`lastWeight`=?, `elapsedTime`=? "
				+ "where productionId=?";
		this.jdbcTemplate.update(sql, production.getProductionName(), production.getEquipmentId(), 
				production.getCreatedTime(), production.getEarlyWeight(), 
				production.getMidWeight(),	production.getLateWeight(), production.getLastWeight(),	production.getElapsedTime(), production.getProductionId());

	}
	
	public int countOfEquipmentId(String beginDay, String endDay,int equipmentId, int scheduleTypeId) {
	    //System.out.println("beginDay:"+beginDay+"endDay:"+endDay+"equipmentId:"+equipmentId);
		//String sql = "select count(*) from productions where createdTime=? and equipmentId=?";
		String sql = "select count(*) from productions where createdTime >= ? and createdTime < ? and equipmentId=? and scheduleTypeId = ?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, beginDay, endDay ,equipmentId, scheduleTypeId);
	}
	
	public Productions findByEquipmentIdLatestBeforeTime(String beginDay, String endDay , int equipmentId, int scheduleTypeId) {
		//System.out.println("createdTime:"+createdTime+"equipmentId:"+equipmentId);

		String sql = "select * from productions where createdTime >= ? and createdTime < ? and equipmentId=? and scheduleTypeId = ?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { beginDay, endDay , equipmentId , scheduleTypeId }, new ProductionsMapper());
	}
	
	private static final class ProductionsMapper implements RowMapper<Productions> {
		public Productions mapRow(ResultSet rs, int rowNum) throws SQLException {
			Productions production = new Productions();
			production.setProductionId(rs.getInt("productionId"));
			production.setProductionName(rs.getString("productionName"));
			production.setEquipmentId(rs.getInt("equipmentId"));
			production.setScheduleTypeId(rs.getInt("scheduleTypeId"));
			production.setCreatedTime(rs.getTimestamp("createdTime"));
		    production.setEarlyWeight(rs.getDouble("earlyWeight"));
			production.setMidWeight(rs.getDouble("midWeight"));
			production.setLateWeight(rs.getDouble("lateWeight"));
			production.setLastWeight(rs.getDouble("lastWeight"));
			production.setElapsedTime(rs.getTimestamp("elapsedTime"));
			return production;
		}
	}

	public List productionsfindall(String beginDay, String endDay) {
		System.out.println("productionsfindall :"+endDay);
		//String sql = "select * from productions where date_format(createdtime,'%Y-%m-%d') >= ? and date_format(createdtime,'%Y-%m-%d') <= ?";
		//String sql = "select e.equipmentId ,e.equipmentName,p.earlyWeight,p.midWeight,p.lateWeight,p.lastWeight from productions p left join equipments e on p.equipmentId = e.equipmentId where p.createdTime between ? and ? ";
		String sql = "select p.equipmentId,e.equipmentName,SUM(p.earlyWeight) earlyWeight,SUM(p.midWeight) midWeight,SUM(p.lateWeight) lateWeight,SUM(p.lastWeight) lastWeight "
				+ "from productions p left join equipments e on p.equipmentId = e.equipmentId"
				+ "   where p.createdTime between ? and ?  "
				+ "group by p.equipmentId";
		//return this.jdbcTemplate.query(sql, new ProductionsMapper());
		return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay });
		
	}

	/*
	 * 在production表  查询出   每台的设备信息
	 * */
	public List listproductions() {
		//String sql = "select * from productions  where createdTime between ? and ? GROUP BY equipmentId ";
		System.out.println("sql开始时间："+new Date());
		String sql = "select * from productions GROUP BY equipmentId ";
		return this.jdbcTemplate.query(sql, new ProductionsMapper());
		//return this.jdbcTemplate.queryForList(sql, new Object[] { beginDay, endDay });
		
	}

	/*
	 * 某一时间内 所有设备的总数据量
	 * */
	public int countByTime(String beginTime, String endTime) {
		String sql = "select count(*) from events where createdTime between ? and ? ";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { beginTime, endTime});
	}
	
	/*
	 * 某一时间内 单一设备的总数据量
	 * */
	public int countByTimeId(int equipmentId,String beginTime, String endTime) {
		String sql = "select count(*) from events where equipmentId = ? and createdTime between ? and ? ";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { equipmentId, beginTime, endTime});
	}
}

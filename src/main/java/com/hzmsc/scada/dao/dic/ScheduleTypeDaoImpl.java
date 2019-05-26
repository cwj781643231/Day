package com.hzmsc.scada.dao.dic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.dic.ScheduleType;
import com.hzmsc.scada.service.dic.ScheduleTypeService;

@Repository
public class ScheduleTypeDaoImpl implements ScheduleTypeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ScheduleType findById(int scheduleTypeId) {
		String sql = "select * from dic_scheduletype where scheduleTypeId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { scheduleTypeId }, new ScheduleTypeMapper());
	}

	public int countOfScheduleId(int scheduleTypeId) {
		String sql = "select count(*) from dic_scheduletype where scheduleTypeId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, scheduleTypeId);
	}

	public List<ScheduleType> findAll() {
		String sql = "select * from dic_scheduletype";
		return this.jdbcTemplate.query(sql, new ScheduleTypeMapper());
	}
	
	public List<ScheduleType> findByScheduleTypeCode(String scheduleTypeCode){
		String sql = "select * from dic_scheduletype where scheduleTypeCode=?";
		return this.jdbcTemplate.query(sql, new Object[] { scheduleTypeCode }, new ScheduleTypeMapper());
	}

	public ScheduleType createScheduleType(final ScheduleType scheduleType) {
		
		final String sql = "INSERT INTO `dic_scheduletype` "
				+ "(`scheduleTypeCode`, `scheduleTypeName`, `beginTime`, `endTime`, "
				+ "`description`) values (?, ?, ?, ?, ?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "scheduleTypeId" });
				ps.setString(1, scheduleType.getScheduleTypeCode());
				ps.setString(2, scheduleType.getScheduleTypeName());
				ps.setTime(3, scheduleType.getBeginTime());
				ps.setTime(4, scheduleType.getEndTime());
				ps.setString(5, scheduleType.getDescription());
				return ps;
			}
		}, keyHolder);

		scheduleType.setScheduleTypeId(keyHolder.getKey().intValue());
		
		return scheduleType;
	}

	public void updateScheduleType(ScheduleType scheduleType) {
		String sql = "update dic_scheduletype set `scheduleTypeCode`=?, `scheduleTypeName`=?, "
				+ "`beginTime`=?, `endTime`=?, `description`=?, `state`=? where scheduleTypeId=?";
		this.jdbcTemplate.update(sql, scheduleType.getScheduleTypeCode(), scheduleType.getScheduleTypeName(),
				scheduleType.getBeginTime(), scheduleType.getEndTime(), scheduleType.getDescription(), 
				scheduleType.getState(),scheduleType.getScheduleTypeId());

	}

	public void deleteScheduleType(ScheduleType scheduleType) {
		String sql = "delete from dic_scheduletype where scheduleTypeId=?";
		this.jdbcTemplate.update(sql, scheduleType.getScheduleTypeId());

	}

	private static final class ScheduleTypeMapper implements RowMapper<ScheduleType> {
		public ScheduleType mapRow(ResultSet rs, int rowNum) throws SQLException {
			ScheduleType scheduleType = new ScheduleType();
			scheduleType.setScheduleTypeId(rs.getInt("scheduleTypeId"));
			scheduleType.setScheduleTypeCode(rs.getString("scheduleTypeCode"));
			scheduleType.setScheduleTypeName(rs.getString("scheduleTypeName"));
			scheduleType.setBeginTime(rs.getTime("beginTime"));
			scheduleType.setEndTime(rs.getTime("endTime"));
			scheduleType.setDescription(rs.getString("description"));
			scheduleType.setState(rs.getString("state"));
			return scheduleType;
		}
	}

	public List<ScheduleType> findByScheduleTypestate() {
		String sql = "select * from dic_scheduletype where state = 'Y'";
		return this.jdbcTemplate.query(sql, new ScheduleTypeMapper());
	}

	


}

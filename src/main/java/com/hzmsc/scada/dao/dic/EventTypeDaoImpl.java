package com.hzmsc.scada.dao.dic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.dic.EventType;

@Repository
public class EventTypeDaoImpl implements EventTypeDao{
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public EventType findById(int eventTypeId) {
		String sql = "select * from dic_eventtype where eventTypeId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object []{eventTypeId}, new EventTypeMapper());
	}
	
	public EventType findByCode(String eventTypeCode) {
		String sql = "select * from dic_eventtype where eventTypeCode=?";
		return this.jdbcTemplate.queryForObject(sql, new Object []{eventTypeCode}, new EventTypeMapper());
	}

	public int countById(int eventTypeId) {
		String sql = "select count(*) from dic_eventtype where eventTypeId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, eventTypeId); 
	}

	public int countByCode(String eventTypeCode) {
		String sql = "select count(*) from dic_eventtype where eventTypeCode=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, eventTypeCode); 
	}
	
	private static final class EventTypeMapper implements RowMapper<EventType> {
		public EventType mapRow(ResultSet rs, int rowNum) throws SQLException {
			EventType eventType = new EventType();
			eventType.setEventTypeId(rs.getInt("eventTypeId"));
			eventType.setEventTypeCode(rs.getString("eventTypeCode"));
			eventType.setEventTypeName(rs.getString("eventTypeName"));
			eventType.setEventTypeCN(rs.getString("eventTypeCN"));
			eventType.setEventTypeEN(rs.getString("eventTypeEN"));
			eventType.setDescription(rs.getString("description"));
			return eventType;
		}
	}

}

package com.hzmsc.scada.dao.dic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.dic.DeviceStatus;

@Repository
public class DeviceStatusDaoImpl implements DeviceStatusDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public DeviceStatus findById(int statusId) {
		String sql = "select * from dic_devicestatus where statusid=?";
		//System.out.println(statusId);
		return this.jdbcTemplate.queryForObject(sql, new Object []{statusId}, new DeviceStatusMapper());
	}

	private static final class DeviceStatusMapper implements RowMapper<DeviceStatus> {
		public DeviceStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
			DeviceStatus deviceStatus = new DeviceStatus();
			deviceStatus.setStatusId(rs.getInt("statusId"));
			deviceStatus.setStatusName(rs.getString("statusName"));
			deviceStatus.setStatusCN(rs.getString("statusCN"));
			deviceStatus.setStatusEN(rs.getString("statusEN"));
			return deviceStatus;
		}
	}

	public int countOfDeviceStatusId(int statusId) {
		String sql = "select count(*) from dic_devicestatus where statusid=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, statusId);
	}
	
}

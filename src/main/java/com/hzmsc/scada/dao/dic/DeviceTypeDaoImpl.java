package com.hzmsc.scada.dao.dic;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.dic.DeviceType;

@Repository
public class DeviceTypeDaoImpl implements DeviceTypeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public DeviceType findById(int typeId) {
		String sql = "select * from dic_devicetype where typeid=?";
		return this.jdbcTemplate.queryForObject(sql, new Object []{typeId}, new DeviceTypeMapper());
	}

	private static final class DeviceTypeMapper implements RowMapper<DeviceType> {
		public DeviceType mapRow(ResultSet rs, int rowNum) throws SQLException {
			DeviceType deviceType = new DeviceType();
			deviceType.setTypeId(rs.getInt("typeId"));
			deviceType.setTypeName(rs.getString("typeName"));
			deviceType.setTypeCN(rs.getString("typeCN"));
			deviceType.setTypeEN(rs.getString("typeEN"));
			return deviceType;
		}
	}

	public int countOfDeviceTypeId(int typeId) {
		String sql = "select count(*) from dic_devicetype where typeid=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, typeId);
	}
	
}

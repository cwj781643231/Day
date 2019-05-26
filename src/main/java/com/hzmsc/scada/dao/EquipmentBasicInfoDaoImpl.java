package com.hzmsc.scada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.EquipmentBasicInfo;

@Repository
public class EquipmentBasicInfoDaoImpl implements EquipmentBasicInfoDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EquipmentBasicInfo createEquipmentBasicInfo(final EquipmentBasicInfo equipmentBasicInfo) {
		final String sql = "insert into equipmentbasicinfo " + "(equipmentId, modifiedTime, "
				+ "softwareVersion, hardwareVersion, newsoftwareVersion, softwareVersionSuffix, deviceType, "
				+ "remainingDays, authorizationErrorNumber, addOperation, serialFirst, serialSecond, "
				+ "serialThird, registrationFirst, registrationSecond, registrationThird, expiredYear, "
				+ "expiredMonth, expiredDay, expiredHour) values "
				+ "(?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "equipmentBasicInfoId" });
				ps.setInt(1, equipmentBasicInfo.getEquipmentId());
				ps.setTimestamp(2, equipmentBasicInfo.getModifiedTime());
				ps.setInt(3, equipmentBasicInfo.getSoftwareVersion());
				ps.setInt(4, equipmentBasicInfo.getHardwareVersion());
				ps.setInt(5, equipmentBasicInfo.getNewsoftwareVersion());
				ps.setInt(6, equipmentBasicInfo.getSoftwareVersionSuffix());
				ps.setInt(7, equipmentBasicInfo.getDeviceType());
				ps.setInt(8, equipmentBasicInfo.getRemainingDays());
				ps.setInt(9, equipmentBasicInfo.getAuthorizationErrorNumber());
				ps.setInt(10, equipmentBasicInfo.getAddOperation());
				ps.setInt(11, equipmentBasicInfo.getSerialFirst());
				ps.setInt(12, equipmentBasicInfo.getSerialSecond());
				ps.setInt(13, equipmentBasicInfo.getSerialThird());
				ps.setInt(14, equipmentBasicInfo.getRegistrationFirst());
				ps.setInt(15, equipmentBasicInfo.getRegistrationSecond());
				ps.setInt(16, equipmentBasicInfo.getRegistrationThird());
				ps.setInt(17, equipmentBasicInfo.getExpiredYear());
				ps.setInt(18, equipmentBasicInfo.getExpiredMonth());
				ps.setInt(19, equipmentBasicInfo.getExpiredDay());
				ps.setInt(20, equipmentBasicInfo.getExpiredHour());

				return ps;
			}
		}, keyHolder);
		
		equipmentBasicInfo.setEquipmentBasicInfoId(keyHolder.getKey().intValue());

		return equipmentBasicInfo;
	}

	public void deleteEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo) {
		// TODO Auto-generated method stub

	}

	public void updateEquipmentBasicInfo(EquipmentBasicInfo equipmentBasicInfo) {
		// TODO Auto-generated method stub

	}

	public EquipmentBasicInfo findById(int equipmentBasicInfoId) {
		String sql = "select * from equipmentbasicinfo where equipmentBasicInfoId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object []{equipmentBasicInfoId}, new EquipmentBasicInfoMapper());
	}

	public int countOfEquipmentBasicInfoId(int equipmentBasicInfoId) {
		String sql = "select count(*) from equipmentbasicinfo where equipmentBasicInfoId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentBasicInfoId);
	}

	public List<EquipmentBasicInfo> findAll() {
		String sql = "select * from equipmentbasicinfo";
		return this.jdbcTemplate.query(sql, new EquipmentBasicInfoMapper());
	
	}
	
	private static final class EquipmentBasicInfoMapper implements RowMapper<EquipmentBasicInfo> {
	public EquipmentBasicInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		EquipmentBasicInfo equipmentBasicInfo = new EquipmentBasicInfo();
		equipmentBasicInfo.setEquipmentBasicInfoId(rs.getInt("equipmentBasicInfoId"));
		equipmentBasicInfo.setEquipmentId(rs.getInt("equipmentId"));
		equipmentBasicInfo.setModifiedTime(rs.getTimestamp("modifiedTime"));
		equipmentBasicInfo.setSoftwareVersion(rs.getInt("softwareVersion"));
		equipmentBasicInfo.setHardwareVersion (rs.getInt("hardwareVersion"));
		equipmentBasicInfo.setNewsoftwareVersion(rs.getInt("newsoftwareVersion"));
		equipmentBasicInfo.setSoftwareVersionSuffix(rs.getInt("softwareVersionSuffix"));
		equipmentBasicInfo.setDeviceType(rs.getInt("deviceType"));
		equipmentBasicInfo.setRemainingDays(rs.getInt("remainingDays"));
		equipmentBasicInfo.setAuthorizationErrorNumber(rs.getInt("authorizationErrorNumber"));
		equipmentBasicInfo.setAddOperation(rs.getInt("addOperation"));
		equipmentBasicInfo.setSerialFirst(rs.getInt("serialFirst"));
		equipmentBasicInfo.setSerialSecond(rs.getInt("serialSecond"));
		equipmentBasicInfo.setSerialThird(rs.getInt("serialThird"));
		equipmentBasicInfo.setRegistrationFirst(rs.getInt("registrationFirst"));
		equipmentBasicInfo.setRegistrationSecond(rs.getInt("registrationSecond"));
		equipmentBasicInfo.setRegistrationThird(rs.getInt("registrationThird"));
		equipmentBasicInfo.setExpiredYear(rs.getInt("expiredYear"));
		equipmentBasicInfo.setExpiredMonth(rs.getInt("expiredMonth"));
		equipmentBasicInfo.setExpiredDay(rs.getInt("expiredDay"));
		equipmentBasicInfo.setExpiredHour(rs.getInt("expiredHour"));
		
		return equipmentBasicInfo;
	}
}

}

package com.hzmsc.scada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.EquipmentOperationRate;

@Repository
public class EquipmentOperationRateDaoImpl implements EquipmentOperationRateDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EquipmentOperationRate createEquipmentOperationRate(final EquipmentOperationRate equipmentOperationRate) {
		final String sql = "INSERT INTO `equipmentoperationrate` "
				+ "(`equipmentId`, `equipmentName`, `modifiedTime`, `operationTime`, "
				+ "`operationTimeOfYear`, `operationTimeOfMonth`, `operationTimeOfDay`, `offTime`, `offTimeOfYear`, "
				+ "`offTimeOfMonth`, `offTimeOfDay`, `onTime`, `onTimeOfYear`, `onTimeOfMonth`, "
				+ "`onTimeOfDay`, `waitTime`, `waitTimeOfYear`, `waitTimeOfMonth`, `waitTimeOfDay`) VALUES "
				+ "(?, ?, ?, ?, " + "?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "equipmentOperationRateId" });
				// System.out.println("[EquipmentOperationRateDaoImpl]" +
				// equipmentOperationRate.getCreatedTime());
				ps.setInt(1, equipmentOperationRate.getEquipmentId());
				ps.setString(2, equipmentOperationRate.getEquipmentName());
				ps.setTimestamp(3, equipmentOperationRate.getModifiedTime());
				ps.setLong(4, equipmentOperationRate.getOperationTime());
				ps.setLong(5, equipmentOperationRate.getOperationTimeOfYear());
				ps.setLong(6, equipmentOperationRate.getOperationTimeOfMonth());
				ps.setLong(7, equipmentOperationRate.getOperationTimeOfDay());
				ps.setLong(8, equipmentOperationRate.getOffTime());
				ps.setLong(9, equipmentOperationRate.getOffTimeOfYear());
				ps.setLong(10, equipmentOperationRate.getOffTimeOfMonth());
				ps.setLong(11, equipmentOperationRate.getOffTimeOfDay());
				ps.setLong(12, equipmentOperationRate.getOnTime());
				ps.setLong(13, equipmentOperationRate.getOnTimeOfYear());
				ps.setLong(14, equipmentOperationRate.getOnTimeOfMonth());
				ps.setLong(15, equipmentOperationRate.getOnTimeOfDay());
				ps.setLong(16, equipmentOperationRate.getWaitTime());
				ps.setLong(17, equipmentOperationRate.getWaitTimeOfYear());
				ps.setLong(18, equipmentOperationRate.getWaitTimeOfMonth());
				ps.setLong(19, equipmentOperationRate.getWaitTimeOfDay());

				return ps;
			}

		};

		this.jdbcTemplate.update(psc, keyHolder);

		equipmentOperationRate.setEquipmentOperationRateId(keyHolder.getKey().intValue());

		return equipmentOperationRate;

	}

	public void deleteEquipmentOperationRate(EquipmentOperationRate equipmentOperationRate) {
		// TODO Auto-generated method stub

	}

	public void updateEquipmentOperationRate(EquipmentOperationRate equipmentStauts) {
		// TODO Auto-generated method stub

	}

	public EquipmentOperationRate findById(int equipmentOperationRateId) {
		String sql = "select * from equipmentoperationrate where equipmentOperationRateId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentOperationRateId },
				new EquipmentOperationRateMapper());
	}

	public int countOfEquipmentOperationRateId(int equipmentOperationRateId) {
		String sql = "select count(*) from equipmentoperationrate where equipmentOperationRateId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentOperationRateId);
	}

	public List<EquipmentOperationRate> findAll() {
		String sql = "select * from equipmentoperationrate";
		return this.jdbcTemplate.query(sql, new EquipmentOperationRateMapper());
	}

	public List<EquipmentOperationRate> findByEquipmentId(int equipmentId) {
		String sql = "select * from equipmentoperationrate where equipmentId=? order by equipmentOperationRateId";
		return this.jdbcTemplate.query(sql, new Object[] { equipmentId }, new EquipmentOperationRateMapper());
	}

	public EquipmentOperationRate findByEquipmentIdFirstAfterTime(int equipmentId) {
		String sql = "select * from equipmentoperationrate where equipmentId=? order by equipmentOperationRateId limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId }, new EquipmentOperationRateMapper());
	}

	public EquipmentOperationRate findByEquipmentIdLatestBeforeTime(int equipmentId) {
		String sql = "select * from equipmentoperationrate where equipmentId=? order by equipmentOperationRateId desc limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId }, new EquipmentOperationRateMapper());
	}

	public int countOfFindByEquipmentId(int equipmentId) {
		String sql = "select count(*) from equipmentoperationrate where equipmentId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId);
	}

	public List<EquipmentOperationRate> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		String sql = "select * from equipmentoperationrate where equipmentId=? and modifiedTime between ? and ? order by equipmentOperationRateId";
		return this.jdbcTemplate.query(sql, new Object[] { equipmentId, beginTime, endTime },
				new EquipmentOperationRateMapper());
	}

	public EquipmentOperationRate findByEquipmentIdFirstAfterTime(int equipmentId, Timestamp beginTime) {
		String sql = "select * from equipmentoperationrate where equipmentId=? and modifiedTime>=? order by equipmentOperationRateId limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId, beginTime},
				new EquipmentOperationRateMapper());
	}

	public EquipmentOperationRate findByEquipmentIdLatestBeforeTime(int equipmentId, Timestamp endTime) {
		String sql = "select * from equipmentoperationrate where equipmentId=? and modifiedTime<? order by equipmentOperationRateId desc limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { equipmentId, endTime },
				new EquipmentOperationRateMapper());
	}

	public int countOfFindByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		String sql = "select count(*) from equipmentoperationrate where equipmentId=? and modifiedTime between ? and ?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId, beginTime, endTime);
	}

	private static final class EquipmentOperationRateMapper implements RowMapper<EquipmentOperationRate> {
		public EquipmentOperationRate mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipmentOperationRate equipmentOperationRate = new EquipmentOperationRate();

			equipmentOperationRate.setEquipmentOperationRateId(rs.getInt("equipmentOperationRateId"));
			equipmentOperationRate.setEquipmentId(rs.getInt("equipmentId"));
			equipmentOperationRate.setEquipmentName(rs.getString("equipmentName"));
			equipmentOperationRate.setModifiedTime(rs.getTimestamp("modifiedTime"));

			equipmentOperationRate.setOperationTime(rs.getLong("operationTime"));
			equipmentOperationRate.setOperationTimeOfYear(rs.getLong("operationTimeOfYear"));
			equipmentOperationRate.setOperationTimeOfMonth(rs.getLong("operationTimeOfMonth"));
			equipmentOperationRate.setOperationTimeOfDay(rs.getLong("operationTimeOfDay"));

			equipmentOperationRate.setOffTime(rs.getLong("offTime"));
			equipmentOperationRate.setOffTimeOfYear(rs.getLong("offTimeOfYear"));
			equipmentOperationRate.setOffTimeOfMonth(rs.getLong("offTimeOfMonth"));
			equipmentOperationRate.setOffTimeOfDay(rs.getLong("offTimeOfDay"));

			equipmentOperationRate.setOnTime(rs.getLong("onTime"));
			equipmentOperationRate.setOnTimeOfYear(rs.getLong("onTimeOfYear"));
			equipmentOperationRate.setOnTimeOfMonth(rs.getLong("onTimeOfMonth"));
			equipmentOperationRate.setOnTimeOfDay(rs.getLong("onTimeOfDay"));

			equipmentOperationRate.setWaitTime(rs.getLong("waitTime"));
			equipmentOperationRate.setWaitTimeOfYear(rs.getLong("waitTimeOfYear"));
			equipmentOperationRate.setWaitTimeOfMonth(rs.getLong("waitTimeOfMonth"));
			equipmentOperationRate.setWaitTimeOfDay(rs.getLong("waitTimeOfDay"));

			return equipmentOperationRate;
		}
	}

}

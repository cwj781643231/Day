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

import com.hzmsc.scada.entity.EquipmentStatus;

@Repository
public class EquipmentStatusDaoImpl implements EquipmentStatusDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EquipmentStatus createEquipmentStatus(final EquipmentStatus equipmentStatus) {
		final String sql = "insert into equipmentstatus "
				+ "(equipmentId, modifiedTime, "
				+ "equipmentStatus, measuredTwist, taskCompletion, malfunction, remainmingHours, "
				+ "remainmingMinutes, remainmingSeconds, yarnLengthLowHalfWord, yarnLengthHighHalfWord, yarnSpoolWeight, "
				+ "measuredTwistingMotorSpindleSpeed, measuredCoilingRollerSpeed, currentTraversingWidth, twistingMotorReadFrequency, windingMotorReadFrequency, "
				+ "twistingMotorReadCurrent, windingMotorReadCurrent) values "
				+ "(?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "equipmentStatusId" });
				ps.setInt(1, equipmentStatus.getEquipmentId());
				ps.setTimestamp(2, equipmentStatus.getModifiedTime());
				ps.setInt(3, equipmentStatus.getEquipmentStatus());
				ps.setInt(4, equipmentStatus.getMeasuredTwist());
				ps.setInt(5, equipmentStatus.getTaskCompletion());
				ps.setInt(6, equipmentStatus.getMalfunction());
				ps.setInt(7, equipmentStatus.getRemainmingHours());
				ps.setInt(8, equipmentStatus.getRemainmingMinutes());
				ps.setInt(9, equipmentStatus.getRemainmingSeconds());
				ps.setInt(10, equipmentStatus.getYarnLengthLowHalfWord());
				ps.setInt(11, equipmentStatus.getYarnLengthHighHalfWord());
				ps.setInt(12, equipmentStatus.getYarnSpoolWeight());
				ps.setInt(13, equipmentStatus.getMeasuredTwistingMotorSpindleSpeed());
				ps.setInt(14, equipmentStatus.getMeasuredCoilingRollerSpeed());
				ps.setInt(15, equipmentStatus.getCurrentTraversingWidth());
				ps.setInt(16, equipmentStatus.getTwistingMotorReadFrequency());
				ps.setInt(17, equipmentStatus.getWindingMotorReadFrequency());
				ps.setInt(18, equipmentStatus.getTwistingMotorReadCurrent());
				ps.setInt(19, equipmentStatus.getWindingMotorReadCurrent());

				return ps;
			}

		};
		
		this.jdbcTemplate.update(psc, keyHolder);
				
		equipmentStatus.setEquipmentStatusId(keyHolder.getKey().intValue());
		
		return equipmentStatus;
	}

	public void deleteEquipmentStatus(EquipmentStatus equipmentStatus) {
		// TODO Auto-generated method stub

	}

	public void updateEquipmentStatus(EquipmentStatus equipmentStauts) {
		// TODO Auto-generated method stub

	}

	public EquipmentStatus findById(int equipmentStatusId) {
		String sql = "select * from equipmentstatus where equipmentStatusId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{equipmentStatusId}, new EquipmentStatusMapper());
	}
	
	public int countOfEquipmentStatusId(int equipmentStatusId){
		String sql = "select count(*) from equipmentstatus where equipmentStatusId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentStatusId);
	}
	
	public int countOfEquipmentId(int equipmentId){
		String sql = "select count(*) from equipmentstatus where equipmentId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId);
	}
	
	public int countOfEquipmentIdBeforeTime(int equipmentId, Timestamp beginTime){
		String sql = "select count(*) from equipmentstatus where equipmentId=? and modifiedTime<?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId, beginTime);
	}
	

	public List<EquipmentStatus> findAll() {
		String sql = "select * from equipmentstatus";
		return this.jdbcTemplate.query(sql, new EquipmentStatusMapper());
	}
	
	public List<EquipmentStatus> findByEquipmentId(int equipmentId) {
		String sql = "select * from equipmentstatus where equipmentId=? order by equipmentStatusId";
		return this.jdbcTemplate.query(sql, new Object[]{equipmentId}, new EquipmentStatusMapper());
	}
	
	private static final class EquipmentStatusMapper implements RowMapper<EquipmentStatus> {
		public EquipmentStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipmentStatus equipmentStatus = new EquipmentStatus();
			equipmentStatus.setEquipmentStatusId(rs.getInt("equipmentStatusId"));
			equipmentStatus.setEquipmentId(rs.getInt("equipmentId"));
			equipmentStatus.setModifiedTime(rs.getTimestamp("modifiedTime"));
			equipmentStatus.setEquipmentStatus(rs.getInt("equipmentStatus"));
			equipmentStatus.setMeasuredTwist (rs.getInt("measuredTwist"));
			equipmentStatus.setTaskCompletion(rs.getInt("taskCompletion"));
			equipmentStatus.setMalfunction(rs.getInt("malfunction"));
			equipmentStatus.setRemainmingHours(rs.getInt("remainmingHours"));
			equipmentStatus.setRemainmingMinutes(rs.getInt("remainmingMinutes"));
			equipmentStatus.setRemainmingSeconds(rs.getInt("remainmingSeconds"));
			equipmentStatus.setYarnLengthLowHalfWord(rs.getInt("yarnLengthLowHalfWord"));
			equipmentStatus.setYarnLengthHighHalfWord(rs.getInt("yarnLengthHighHalfWord"));
			equipmentStatus.setYarnSpoolWeight(rs.getInt("yarnSpoolWeight"));
			equipmentStatus.setMeasuredTwistingMotorSpindleSpeed(rs.getInt("measuredTwistingMotorSpindleSpeed"));
			equipmentStatus.setMeasuredCoilingRollerSpeed(rs.getInt("measuredCoilingRollerSpeed"));
			equipmentStatus.setCurrentTraversingWidth(rs.getInt("currentTraversingWidth"));
			equipmentStatus.setTwistingMotorReadFrequency(rs.getInt("twistingMotorReadFrequency"));
			equipmentStatus.setWindingMotorReadFrequency(rs.getInt("windingMotorReadFrequency"));
			equipmentStatus.setTwistingMotorReadCurrent(rs.getInt("twistingMotorReadCurrent"));
			equipmentStatus.setWindingMotorReadCurrent(rs.getInt("windingMotorReadCurrent"));

			return equipmentStatus;
		}
	}

	public List<EquipmentStatus> findByEquipmentIdAfterTime(int equipmentId, Timestamp beginTime) {
		String sql = "select * from equipmentstatus where equipmentId=? and modifiedTime>=? order by equipmentStatusId";
		return this.jdbcTemplate.query(sql, new Object[]{equipmentId, beginTime}, new EquipmentStatusMapper());
	}
	
	public EquipmentStatus findByEquipmentIdLatestBeforTime(int equipmentId, Timestamp beginTime){
		String sql = "select * from equipmentstatus where equipmentId=? and modifiedTime < ? order by equipmentStatusId desc limit 1";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{equipmentId, beginTime}, new EquipmentStatusMapper());
	}

	public List<EquipmentStatus> findBetweenTime(Timestamp beginTime, Timestamp endTime) {
		String sql = "select * from equipmentstatus where modifiedTime between ? and ? order by modifiedTime";
		return this.jdbcTemplate.query(sql, new Object[]{beginTime, endTime}, new EquipmentStatusMapper());
	}
	
	public List<EquipmentStatus> findBetweenTimeByEvent(Timestamp beginTime, Timestamp endTime, int malfunction) {
		String sql = "select * from equipmentstatus where modifiedTime between ? and ? and malfunction=? order by modifiedTime";
		return this.jdbcTemplate.query(sql, new Object[]{beginTime, endTime, malfunction}, new EquipmentStatusMapper());
	}
	
	public List<EquipmentStatus> findBetweenTimeEvents(Timestamp beginTime, Timestamp endTime) {
		String sql = "select * from equipmentstatus where modifiedTime between ? and ? order by modifiedTime";
		return this.jdbcTemplate.query(sql, new Object[]{beginTime, endTime}, new EquipmentStatusMapper());
	}

}

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

import com.hzmsc.scada.entity.EquipmentConfigure;

@Repository
public class EquipmentConfigureDaoImpl implements EquipmentConfigureDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EquipmentConfigure createEquipmentConfigure(final EquipmentConfigure equipmentConfigure) {
		final String sql = "insert into equipmentconfigures " 
				+ "(equipmentId, modifiedTime, "
				+ "planningTime, spindleSpeed, twist, twistingDirection, windingAngle, "
				+ "yarnLengthLow, yarnLengthHigh, yarnLengthCorrectionFactor, yarnCount, spindleAmount, "
				+ "initialTraverseTravel, terminatedTraverseTravel, retractSideSwitch, softSideRange, softSideNumber, "
				+ "yarnSpoolDiameterRatio, outSideSpeedFactor, retractSideSpeedFactor, outRetractSideWidth, radomTime, "
				+ "randomAmplitude, softSideHardnessFactorSynchronous, windingFormat, sTypeWindingRange, sTypeWindingHalfLoopTraverseNumber, "
				+ "sTypeWindingSoftSideHardnessFactor, shutdownWhenTaskCompleted, edgeWindingDelayTime, preventOverlapTwoLength, preventOverlapTwoCycle,"
				+ "softSideRadom, operatingMode, tailYarnOffsetDistance, tailYarnLength, twistCorrectionFactor, "
				+ "syncTypeTwoSoftSideRange, syncTypeTwoSoftSideNumber, syncTypeTwoHardnessFactor, gearboxTypeSoftSideRange, gearboxTypeSoftSideNumber,"
				+ "gearboxTypeHardnessFactor) " 
				+ "values (?, ?, " 
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, " 
				+ "?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "equipmentConfigureId" });
				//System.out.println(ps);
				//System.out.println(equipmentConfigure);
				ps.setInt(1, equipmentConfigure.getEquipmentId());
				ps.setTimestamp(2, equipmentConfigure.getModifiedTime());
				ps.setInt(3, equipmentConfigure.getPlanningTime());
				ps.setInt(4, equipmentConfigure.getSpindleSpeed());
				ps.setInt(5, equipmentConfigure.getTwist());
				ps.setInt(6, equipmentConfigure.getTwistingDirection());
				ps.setInt(7, equipmentConfigure.getWindingAngle());
				ps.setInt(8, equipmentConfigure.getYarnLengthLow());
				ps.setInt(9, equipmentConfigure.getYarnLengthHigh());
				ps.setInt(10, equipmentConfigure.getYarnLengthCorrectionFactor());
				ps.setInt(11, equipmentConfigure.getYarnCount());
				ps.setInt(12, equipmentConfigure.getSpindleAmount());
				ps.setInt(13, equipmentConfigure.getInitialTraverseTravel());
				ps.setInt(14, equipmentConfigure.getTerminatedTraverseTravel());
				ps.setInt(15, equipmentConfigure.getRetractSideSwitch());
				ps.setInt(16, equipmentConfigure.getSoftSideRange());
				ps.setInt(17, equipmentConfigure.getSoftSideNumber());
				ps.setInt(18, equipmentConfigure.getYarnSpoolDiameterRatio());
				ps.setInt(19, equipmentConfigure.getOutSideSpeedFactor());
				ps.setInt(20, equipmentConfigure.getRetractSideSpeedFactor());
				ps.setInt(21, equipmentConfigure.getOutRetractSideWidth());
				ps.setInt(22, equipmentConfigure.getRadomTime());
				ps.setInt(23, equipmentConfigure.getRandomAmplitude());
				ps.setInt(24, equipmentConfigure.getSoftSideHardnessFactorSynchronous());
				ps.setInt(25, equipmentConfigure.getWindingFormat());
				ps.setInt(26, equipmentConfigure.getsTypeWindingRange());
				ps.setInt(27, equipmentConfigure.getsTypeWindingHalfLoopTraverseNumber());
				ps.setInt(28, equipmentConfigure.getsTypeWindingSoftSideHardnessFactor());
				ps.setInt(29, equipmentConfigure.getShutdownWhenTaskCompleted());
				ps.setInt(30, equipmentConfigure.getEdgeWindingDelayTime());
				ps.setInt(31, equipmentConfigure.getPreventOverlapTwoLength());
				ps.setInt(32, equipmentConfigure.getPreventOverlapTwoCycle());
				ps.setInt(33, equipmentConfigure.getSoftSideRadom());
				ps.setInt(34, equipmentConfigure.getOperatingMode());
				ps.setInt(35, equipmentConfigure.getTailYarnOffsetDistance());
				ps.setInt(36, equipmentConfigure.getTailYarnLength());
				ps.setInt(37, equipmentConfigure.getTwistCorrectionFactor());
				ps.setInt(38, equipmentConfigure.getSyncTypeTwoSoftSideRange());
				ps.setInt(39, equipmentConfigure.getSyncTypeTwoSoftSideNumber());
				ps.setInt(40, equipmentConfigure.getSyncTypeTwoHardnessFactor());
				ps.setInt(41, equipmentConfigure.getGearboxTypeSoftSideRange());
				ps.setInt(42, equipmentConfigure.getGearboxTypeSoftSideNumber());
				ps.setInt(43, equipmentConfigure.getGearboxTypeHardnessFactor());

				return ps;
			}
		}, keyHolder);
		// TODO Auto-generated method stub
		equipmentConfigure.setEquipmentConfigureId(keyHolder.getKey().intValue());

		return equipmentConfigure;
	}

	public void deleteEquipmentConfigure(EquipmentConfigure equipmentConfigure) {
		// TODO Auto-generated method stub

	}

	public void updateEquipmentConfigure(EquipmentConfigure equipmentStauts) {
		// TODO Auto-generated method stub

	}

	public EquipmentConfigure findById(int equipmentConfigureId) {
		String sql = "select * from equipmentconfigures where equipmentConfigureId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[]{equipmentConfigureId}, new EquipmentConfigureMapper());
	}

	public int countOfEquipmentConfigureId(int equipmentConfigureId) {
		String sql = "select count(*) from equipmentconfigures where equipmentConfigureId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentConfigureId);
	}
	
	public List<EquipmentConfigure> findAll() {
		String sql = "select * from equipmentconfigures";
		return this.jdbcTemplate.query(sql, new EquipmentConfigureMapper());
	}

	private static final class EquipmentConfigureMapper implements RowMapper<EquipmentConfigure> {
		public EquipmentConfigure mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipmentConfigure equipmentConfigure = new EquipmentConfigure();
			equipmentConfigure.setEquipmentConfigureId(rs.getInt("equipmentConfigureId"));
			equipmentConfigure.setEquipmentId(rs.getInt("equipmentId"));
			equipmentConfigure.setModifiedTime(rs.getTimestamp("modifiedTime"));
			equipmentConfigure.setPlanningTime(rs.getInt("planningTime"));
			equipmentConfigure.setSpindleSpeed(rs.getInt("spindleSpeed"));
			equipmentConfigure.setTwist(rs.getInt("twist"));
			equipmentConfigure.setTwistingDirection(rs.getInt("twistingDirection"));
			equipmentConfigure.setWindingAngle(rs.getInt("windingAngle"));
			equipmentConfigure.setYarnLengthLow(rs.getInt("yarnLengthLow"));
			equipmentConfigure.setYarnLengthHigh(rs.getInt("yarnLengthHigh"));
			equipmentConfigure.setYarnLengthCorrectionFactor(rs.getInt("yarnLengthCorrectionFactor"));
			equipmentConfigure.setYarnCount(rs.getInt("yarnCount"));
			equipmentConfigure.setSpindleAmount(rs.getInt("spindleAmount"));
			equipmentConfigure.setInitialTraverseTravel(rs.getInt("initialTraverseTravel"));
			equipmentConfigure.setTerminatedTraverseTravel(rs.getInt("terminatedTraverseTravel"));
			equipmentConfigure.setRetractSideSwitch(rs.getInt("retractSideSwitch"));
			equipmentConfigure.setSoftSideRange(rs.getInt("softSideRange"));
			equipmentConfigure.setSoftSideNumber(rs.getInt("softSideNumber"));
			equipmentConfigure.setYarnSpoolDiameterRatio(rs.getInt("yarnSpoolDiameterRatio"));
			equipmentConfigure.setOutSideSpeedFactor(rs.getInt("outSideSpeedFactor"));
			equipmentConfigure.setRetractSideSpeedFactor(rs.getInt("retractSideSpeedFactor"));
			equipmentConfigure.setOutRetractSideWidth(rs.getInt("outRetractSideWidth"));
			equipmentConfigure.setRadomTime(rs.getInt("radomTime"));
			equipmentConfigure.setRandomAmplitude(rs.getInt("randomAmplitude"));
			equipmentConfigure.setSoftSideHardnessFactorSynchronous(rs.getInt("softSideHardnessFactorSynchronous"));
			equipmentConfigure.setWindingFormat(rs.getInt("windingFormat"));
			equipmentConfigure.setsTypeWindingRange(rs.getInt("sTypeWindingRange"));
			equipmentConfigure.setsTypeWindingHalfLoopTraverseNumber(rs.getInt("sTypeWindingHalfLoopTraverseNumber"));
			equipmentConfigure.setsTypeWindingSoftSideHardnessFactor(rs.getInt("sTypeWindingSoftSideHardnessFactor"));
			equipmentConfigure.setShutdownWhenTaskCompleted(rs.getInt("shutdownWhenTaskCompleted"));
			equipmentConfigure.setEdgeWindingDelayTime(rs.getInt("edgeWindingDelayTime"));
			equipmentConfigure.setPreventOverlapTwoLength(rs.getInt("preventOverlapTwoLength"));
			equipmentConfigure.setPreventOverlapTwoCycle(rs.getInt("preventOverlapTwoCycle"));
			equipmentConfigure.setSoftSideRadom(rs.getInt("softSideRadom"));
			equipmentConfigure.setOperatingMode(rs.getInt("operatingMode"));
			equipmentConfigure.setTailYarnOffsetDistance(rs.getInt("tailYarnOffsetDistance"));
			equipmentConfigure.setTailYarnLength(rs.getInt("tailYarnLength"));
			equipmentConfigure.setTwistCorrectionFactor(rs.getInt("twistCorrectionFactor"));
			equipmentConfigure.setSyncTypeTwoSoftSideRange(rs.getInt("syncTypeTwoSoftSideRange"));
			equipmentConfigure.setSyncTypeTwoSoftSideNumber(rs.getInt("syncTypeTwoSoftSideNumber"));
			equipmentConfigure.setSyncTypeTwoHardnessFactor(rs.getInt("syncTypeTwoHardnessFactor"));
			equipmentConfigure.setGearboxTypeSoftSideRange(rs.getInt("gearboxTypeSoftSideRange"));
			equipmentConfigure.setGearboxTypeSoftSideNumber(rs.getInt("gearboxTypeSoftSideNumber"));
			equipmentConfigure.setGearboxTypeHardnessFactor(rs.getInt("gearboxTypeHardnessFactor"));

			return equipmentConfigure;
		}
	}

}

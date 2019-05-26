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

import com.hzmsc.scada.entity.EquipmentBasicInfo;
import com.hzmsc.scada.entity.EquipmentConfigure;
import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.entity.Event;
import com.hzmsc.scada.entity.PageBean;
import com.hzmsc.scada.entity.QueryInfo;

@Repository
public class EventDaoImpl implements EventDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Event createEvent(final Event event) {
		final String sql = "INSERT INTO `events` (`eventName`, `eventTypeId`, `eventTypeCode`, "
				+ "`eventTypeName`, `eventLevelId`, `eventLevelCode`, `eventLevelName`, `equipmentId`, "
				+ "`equipmentName`, `createdTime`, `equipmentStatus`, `measuredTwist`, "
				+ "`taskCompletion`, `malfunction`, `remainmingHours`, `remainmingMinutes`, "
				+ "`remainmingSeconds`, `yarnLengthLowHalfWord`, `yarnLengthHighHalfWord`, "
				+ "`yarnSpoolWeight`, `measuredTwistingMotorSpindleSpeed`, `measuredCoilingRollerSpeed`, "
				+ "`currentTraversingWidth`, `twistingMotorReadFrequency`, `windingMotorReadFrequency`, "
				+ "`twistingMotorReadCurrent`, `windingMotorReadCurrent`, `planningTime`, `spindleSpeed`, "
				+ "`twist`, `twistingDirection`, `windingAngle`, `yarnLengthLow`, `yarnLengthHigh`, "
				+ "`yarnLengthCorrectionFactor`, `yarnCount`, `spindleAmount`, `initialTraverseTravel`, "
				+ "`terminatedTraverseTravel`, `retractSideSwitch`, `softSideRange`, `softSideNumber`, "
				+ "`yarnSpoolDiameterRatio`, `outSideSpeedFactor`, `retractSideSpeedFactor`, "
				+ "`outRetractSideWidth`, `radomTime`, `randomAmplitude`, `softSideHardnessFactorSynchronous`, "
				+ "`windingFormat`, `sTypeWindingRange`, `sTypeWindingHalfLoopTraverseNumber`, "
				+ "`sTypeWindingSoftSideHardnessFactor`, `shutdownWhenTaskCompleted`, `edgeWindingDelayTime`, "
				+ "`preventOverlapTwoLength`, `preventOverlapTwoCycle`, `softSideRadom`, `operatingMode`, "
				+ "`tailYarnOffsetDistance`, `tailYarnLength`, `twistCorrectionFactor`, `syncTypeTwoSoftSideRange`, "
				+ "`syncTypeTwoSoftSideNumber`, `syncTypeTwoHardnessFactor`, `gearboxTypeSoftSideRange`, "
				+ "`gearboxTypeSoftSideNumber`, `gearboxTypeHardnessFactor`, `softwareVersion`, `hardwareVersion`, "
				+ "`newsoftwareVersion`, `softwareVersionSuffix`, `deviceType`, `remainingDays`, "
				+ "`authorizationErrorNumber`, `addOperation`, `serialFirst`, `serialSecond`, `serialThird`, "
				+ "`registrationFirst`, `registrationSecond`, `registrationThird`, `expiredYear`, `expiredMonth`, "
				+ "`expiredDay`, `expiredHour`) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
				+ "?, ?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "eventId" });
				ps.setString(1, event.getEventName());
				ps.setInt(2, event.getEventTypeId());
				ps.setString(3, event.getEventTypeCode());
				ps.setString(4, event.getEventTypeName());
				ps.setInt(5, event.getEventLevelId());
				ps.setString(6, event.getEventLevelCode());
				ps.setString(7, event.getEventLevelName());
				ps.setInt(8, event.getEquipmentId());
				ps.setString(9, event.getEquipmentName());
				ps.setTimestamp(10, event.getCreatedTime());

				// EquipmentStatus
				EquipmentStatus equipmentStatus = event.getEquipmentStatus();
				ps.setInt(11, equipmentStatus.getEquipmentStatus());
				ps.setInt(12, equipmentStatus.getMeasuredTwist());
				ps.setInt(13, equipmentStatus.getTaskCompletion());
				ps.setInt(14, equipmentStatus.getMalfunction());
				ps.setInt(15, equipmentStatus.getRemainmingHours());
				ps.setInt(16, equipmentStatus.getRemainmingMinutes());
				ps.setInt(17, equipmentStatus.getRemainmingSeconds());
				ps.setInt(18, equipmentStatus.getYarnLengthLowHalfWord());
				ps.setInt(19, equipmentStatus.getYarnLengthHighHalfWord());
				ps.setInt(20, equipmentStatus.getYarnSpoolWeight());
				ps.setInt(21, equipmentStatus.getMeasuredTwistingMotorSpindleSpeed());
				ps.setInt(22, equipmentStatus.getMeasuredCoilingRollerSpeed());
				ps.setInt(23, equipmentStatus.getCurrentTraversingWidth());
				ps.setInt(24, equipmentStatus.getTwistingMotorReadFrequency());
				ps.setInt(25, equipmentStatus.getWindingMotorReadFrequency());
				ps.setInt(26, equipmentStatus.getTwistingMotorReadCurrent());
				ps.setInt(27, equipmentStatus.getWindingMotorReadCurrent());

				// EquipmentConfigure
				EquipmentConfigure equipmentConfigure = event.getEquipmentConfigure();
				ps.setInt(28, equipmentConfigure.getPlanningTime());
				ps.setInt(29, equipmentConfigure.getSpindleSpeed());
				ps.setInt(30, equipmentConfigure.getTwist());
				ps.setInt(31, equipmentConfigure.getTwistingDirection());
				ps.setInt(32, equipmentConfigure.getWindingAngle());
				ps.setInt(33, equipmentConfigure.getYarnLengthLow());
				ps.setInt(34, equipmentConfigure.getYarnLengthHigh());
				ps.setInt(35, equipmentConfigure.getYarnLengthCorrectionFactor());
				ps.setInt(36, equipmentConfigure.getYarnCount());
				ps.setInt(37, equipmentConfigure.getSpindleAmount());
				ps.setInt(38, equipmentConfigure.getInitialTraverseTravel());
				ps.setInt(39, equipmentConfigure.getTerminatedTraverseTravel());
				ps.setInt(40, equipmentConfigure.getRetractSideSwitch());
				ps.setInt(41, equipmentConfigure.getSoftSideRange());
				ps.setInt(42, equipmentConfigure.getSoftSideNumber());
				ps.setInt(43, equipmentConfigure.getYarnSpoolDiameterRatio());
				ps.setInt(44, equipmentConfigure.getOutSideSpeedFactor());
				ps.setInt(45, equipmentConfigure.getRetractSideSpeedFactor());
				ps.setInt(46, equipmentConfigure.getOutRetractSideWidth());
				ps.setInt(47, equipmentConfigure.getRadomTime());
				ps.setInt(48, equipmentConfigure.getRandomAmplitude());
				ps.setInt(49, equipmentConfigure.getSoftSideHardnessFactorSynchronous());
				ps.setInt(50, equipmentConfigure.getWindingFormat());
				ps.setInt(51, equipmentConfigure.getsTypeWindingRange());
				ps.setInt(52, equipmentConfigure.getsTypeWindingHalfLoopTraverseNumber());
				ps.setInt(53, equipmentConfigure.getsTypeWindingSoftSideHardnessFactor());
				ps.setInt(54, equipmentConfigure.getShutdownWhenTaskCompleted());
				ps.setInt(55, equipmentConfigure.getEdgeWindingDelayTime());
				ps.setInt(56, equipmentConfigure.getPreventOverlapTwoLength());
				ps.setInt(57, equipmentConfigure.getPreventOverlapTwoCycle());
				ps.setInt(58, equipmentConfigure.getSoftSideRadom());
				ps.setInt(59, equipmentConfigure.getOperatingMode());
				ps.setInt(60, equipmentConfigure.getTailYarnOffsetDistance());
				ps.setInt(61, equipmentConfigure.getTailYarnLength());
				ps.setInt(62, equipmentConfigure.getTwistCorrectionFactor());
				ps.setInt(63, equipmentConfigure.getSyncTypeTwoSoftSideRange());
				ps.setInt(64, equipmentConfigure.getSyncTypeTwoSoftSideNumber());
				ps.setInt(65, equipmentConfigure.getSyncTypeTwoHardnessFactor());
				ps.setInt(66, equipmentConfigure.getGearboxTypeSoftSideRange());
				ps.setInt(67, equipmentConfigure.getGearboxTypeSoftSideNumber());
				ps.setInt(68, equipmentConfigure.getGearboxTypeHardnessFactor());

				// EquipmentBasicInfo
				EquipmentBasicInfo equipmentBasicInfo = event.getEquipmentBasicInfo();
				ps.setInt(69, equipmentBasicInfo.getSoftwareVersion());
				ps.setInt(70, equipmentBasicInfo.getHardwareVersion());
				ps.setInt(71, equipmentBasicInfo.getNewsoftwareVersion());
				ps.setInt(72, equipmentBasicInfo.getSoftwareVersionSuffix());
				ps.setInt(73, equipmentBasicInfo.getDeviceType());
				ps.setInt(74, equipmentBasicInfo.getRemainingDays());
				ps.setInt(75, equipmentBasicInfo.getAuthorizationErrorNumber());
				ps.setInt(76, equipmentBasicInfo.getAddOperation());
				ps.setInt(77, equipmentBasicInfo.getSerialFirst());
				ps.setInt(78, equipmentBasicInfo.getSerialSecond());
				ps.setInt(79, equipmentBasicInfo.getSerialThird());
				ps.setInt(80, equipmentBasicInfo.getRegistrationFirst());
				ps.setInt(81, equipmentBasicInfo.getRegistrationSecond());
				ps.setInt(82, equipmentBasicInfo.getRegistrationThird());
				ps.setInt(83, equipmentBasicInfo.getExpiredYear());
				ps.setInt(84, equipmentBasicInfo.getExpiredMonth());
				ps.setInt(85, equipmentBasicInfo.getExpiredDay());
				ps.setInt(86, equipmentBasicInfo.getExpiredHour());

				return ps;
			}
		}, keyHolder);
		// TODO Auto-generated method stub
		event.setEventId(keyHolder.getKey().intValue());

		return event;
	}

	public void updateEvent(Event event) {

		String sql = "update events set eventName=?, eventTypeId=?, eventTypeCode=?, eventTypeName=?, eventLevelId=?, "
				+ "eventLevelCode=?, eventLevelName=?, equipmentId=?, equipmentName=?, createdTime=?, "
				+ "equipmentStatus=?, measuredTwist=?, taskCompletion=?, malfunction=?, remainmingHours=?, "
				+ "remainmingMinutes=?, remainmingSeconds=?, yarnLengthLowHalfWord=?, yarnLengthHighHalfWord=?, yarnSpoolWeight=?, "
				+ "measuredTwistingMotorSpindleSpeed=?, measuredCoilingRollerSpeed=?, currentTraversingWidth=?, twistingMotorReadFrequency=?, windingMotorReadFrequency=?, "
				+ "twistingMotorReadCurrent=?, windingMotorReadCurrent=?, "
				+ "planningTime=?, spindleSpeed=?, twist=?, twistingDirection=?, windingAngle=?, "
				+ "yarnLengthLow=?, yarnLengthHigh=?, yarnLengthCorrectionFactor=?, yarnCount=?, spindleAmount=?, "
				+ "initialTraverseTravel=?, terminatedTraverseTravel=?, retractSideSwitch=?, softSideRange=?, softSideNumber=?, "
				+ "yarnSpoolDiameterRatio=?, outSideSpeedFactor=?, retractSideSpeedFactor=?, outRetractSideWidth=?, radomTime=?, "
				+ "randomAmplitude=?, softSideHardnessFactorSynchronous=?, windingFormat=?, sTypeWindingRange=?, sTypeWindingHalfLoopTraverseNumber=?, "
				+ "sTypeWindingSoftSideHardnessFactor=?, shutdownWhenTaskCompleted=?, edgeWindingDelayTime=?, preventOverlapTwoLength=?, preventOverlapTwoCycle=?,"
				+ "softSideRadom=?, operatingMode=?, tailYarnOffsetDistance=?, tailYarnLength=?, twistCorrectionFactor=?, "
				+ "syncTypeTwoSoftSideRange=?, syncTypeTwoSoftSideNumber=?, syncTypeTwoHardnessFactor=?, gearboxTypeSoftSideRange=?, gearboxTypeSoftSideNumber=?,"
				+ "gearboxTypeHardnessFactor=?, "
				+ "softwareVersion=?, hardwareVersion=?, newsoftwareVersion=?, softwareVersionSuffix=?, deviceType=?, "
				+ "remainingDays=?, authorizationErrorNumber=?, addOperation=?, serialFirst=?, serialSecond=?, "
				+ "serialThird=?, registrationFirst=?, registrationSecond=?, registrationThird=?, expiredYear=?, "
				+ "expiredMonth=?, expiredDay=?, expiredHour=? " + "where eventId=?";

		EquipmentStatus equipmentStatus = event.getEquipmentStatus();
		EquipmentConfigure equipmentConfigure = event.getEquipmentConfigure();
		EquipmentBasicInfo equipmentBasicInfo = event.getEquipmentBasicInfo();

		this.jdbcTemplate.update(sql, event.getEventName(), event.getEventTypeId(),
				event.getEventTypeCode(), event.getEventTypeName(), event.getEventLevelId(), event.getEventLevelCode(),
				event.getEventLevelName(), event.getEquipmentId(), event.getEquipmentName(),
				event.getCreatedTime(), equipmentStatus.getEquipmentStatus(), equipmentStatus.getMeasuredTwist(),
				equipmentStatus.getTaskCompletion(), equipmentStatus.getMalfunction(),
				equipmentStatus.getRemainmingHours(), equipmentStatus.getRemainmingMinutes(),
				equipmentStatus.getRemainmingSeconds(), equipmentStatus.getYarnLengthLowHalfWord(),
				equipmentStatus.getYarnLengthHighHalfWord(), equipmentStatus.getYarnSpoolWeight(),
				equipmentStatus.getMeasuredTwistingMotorSpindleSpeed(), equipmentStatus.getMeasuredCoilingRollerSpeed(),
				equipmentStatus.getCurrentTraversingWidth(), equipmentStatus.getTwistingMotorReadFrequency(),
				equipmentStatus.getWindingMotorReadFrequency(), equipmentStatus.getTwistingMotorReadCurrent(),
				equipmentStatus.getWindingMotorReadCurrent(), equipmentConfigure.getPlanningTime(),
				equipmentConfigure.getSpindleSpeed(), equipmentConfigure.getTwist(),
				equipmentConfigure.getTwistingDirection(), equipmentConfigure.getWindingAngle(),
				equipmentConfigure.getYarnLengthLow(), equipmentConfigure.getYarnLengthHigh(),
				equipmentConfigure.getYarnLengthCorrectionFactor(), equipmentConfigure.getYarnCount(),
				equipmentConfigure.getSpindleAmount(), equipmentConfigure.getInitialTraverseTravel(),
				equipmentConfigure.getTerminatedTraverseTravel(), equipmentConfigure.getRetractSideSwitch(),
				equipmentConfigure.getSoftSideRange(), equipmentConfigure.getSoftSideNumber(),
				equipmentConfigure.getYarnSpoolDiameterRatio(), equipmentConfigure.getOutSideSpeedFactor(),
				equipmentConfigure.getRetractSideSpeedFactor(), equipmentConfigure.getOutRetractSideWidth(),
				equipmentConfigure.getRadomTime(), equipmentConfigure.getRandomAmplitude(),
				equipmentConfigure.getSoftSideHardnessFactorSynchronous(), equipmentConfigure.getWindingFormat(),
				equipmentConfigure.getsTypeWindingRange(), equipmentConfigure.getsTypeWindingHalfLoopTraverseNumber(),
				equipmentConfigure.getsTypeWindingSoftSideHardnessFactor(),
				equipmentConfigure.getShutdownWhenTaskCompleted(), equipmentConfigure.getEdgeWindingDelayTime(),
				equipmentConfigure.getPreventOverlapTwoLength(), equipmentConfigure.getPreventOverlapTwoCycle(),
				equipmentConfigure.getSoftSideRadom(), equipmentConfigure.getOperatingMode(),
				equipmentConfigure.getTailYarnOffsetDistance(), equipmentConfigure.getTailYarnLength(),
				equipmentConfigure.getTwistCorrectionFactor(), equipmentConfigure.getSyncTypeTwoSoftSideRange(),
				equipmentConfigure.getSyncTypeTwoSoftSideNumber(), equipmentConfigure.getSyncTypeTwoHardnessFactor(),
				equipmentConfigure.getGearboxTypeSoftSideRange(), equipmentConfigure.getGearboxTypeSoftSideNumber(),
				equipmentConfigure.getGearboxTypeHardnessFactor(), equipmentBasicInfo.getSoftwareVersion(),
				equipmentBasicInfo.getHardwareVersion(), equipmentBasicInfo.getNewsoftwareVersion(),
				equipmentBasicInfo.getSoftwareVersionSuffix(), equipmentBasicInfo.getDeviceType(),
				equipmentBasicInfo.getRemainingDays(), equipmentBasicInfo.getAuthorizationErrorNumber(),
				equipmentBasicInfo.getAddOperation(), equipmentBasicInfo.getSerialFirst(),
				equipmentBasicInfo.getSerialSecond(), equipmentBasicInfo.getSerialThird(),
				equipmentBasicInfo.getRegistrationFirst(), equipmentBasicInfo.getRegistrationSecond(),
				equipmentBasicInfo.getRegistrationThird(), equipmentBasicInfo.getExpiredYear(),
				equipmentBasicInfo.getExpiredMonth(), equipmentBasicInfo.getExpiredDay(),
				equipmentBasicInfo.getExpiredHour(),

				event.getEventId());

	}

	public void deleteEvent(Event event) {
		String sql = "delete from events where eventId=?";
		this.jdbcTemplate.update(sql, event.getEventId());

	}

	public Event findById(int eventId) {
		String sql = "select * from events where eventId=?";
		return this.jdbcTemplate.queryForObject(sql, new Object[] { eventId }, new EventMapper());
	}
	
	public List<Event> findAll() {
		String sql = "select * from events";
		return this.jdbcTemplate.query(sql, new EventMapper());

	}
	
	public List<Event> findBetweenTime(String beginTime, String endTime) {
		System.out.println("beginTime:"+ beginTime);
		System.out.println("endTime:"+ endTime);
		String sql = "select * from events where createdTime >= ? and createdTime <= ? order by eventId desc";
		//select * from events where createdTime between ? and ?  order by eventId desc
		//select * from dailyschedule where scheduleDay >= ? and scheduleDay <= ?
		return this.jdbcTemplate.query(sql, new Object[] { beginTime, endTime}, new EventMapper());
	}
	
	public List<Event> findByEquipmentId(int equipmentId) {
		String sql = "select * from events where equipmentId=?";
		return this.jdbcTemplate.query(sql, new Object[] { equipmentId }, new EventMapper());

	}

	public List<Event> findByEquipmentIdBetweenTime(int equipmentId, Timestamp beginTime, Timestamp endTime) {
		String sql = "select * from events where equipmentId=? and createdTime between ? and ?";
		return this.jdbcTemplate.query(sql, new Object[] { equipmentId, beginTime, endTime }, new EventMapper());

	}
	
	public int countById(int eventId) {
		String sql = "select count(*) from events where eventId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, eventId);
	}

	public int countByEquipmentId(int equipmentId) {
		String sql = "select count(*) from events where equipmentId=?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, equipmentId);
	}


	private static final class EventMapper implements RowMapper<Event> {
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event event = new Event();
			event.setEventId(rs.getInt("eventId"));
			event.setEventName(rs.getString("eventName"));
			event.setEventTypeId(rs.getInt("eventTypeId"));
			event.setEventTypeCode(rs.getString("eventTypeCode"));
			event.setEventTypeName(rs.getString("eventTypeName"));
			event.setEventLevelId(rs.getInt("eventLevelId"));
			event.setEventLevelCode(rs.getString("eventLevelCode"));
			event.setEventLevelName(rs.getString("eventLevelName"));
			event.setEquipmentId(rs.getInt("equipmentId"));
			event.setEquipmentName(rs.getString("equipmentName"));
			event.setCreatedTime(rs.getTimestamp("createdTime"));

			// EquipmentStatus
			EquipmentStatus equipmentStatus = new EquipmentStatus();
			equipmentStatus.setEquipmentId(rs.getInt("equipmentId"));
			equipmentStatus.setEquipmentStatus(rs.getInt("equipmentStatus"));
			equipmentStatus.setMeasuredTwist(rs.getInt("measuredTwist"));
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

			// EquipmentConfigure
			EquipmentConfigure equipmentConfigure = new EquipmentConfigure();
			equipmentConfigure.setEquipmentId(rs.getInt("equipmentId"));
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

			// EquipmentBasicInfo
			EquipmentBasicInfo equipmentBasicInfo = new EquipmentBasicInfo();
			equipmentBasicInfo.setEquipmentId(rs.getInt("equipmentId"));
			equipmentBasicInfo.setSoftwareVersion(rs.getInt("softwareVersion"));
			equipmentBasicInfo.setHardwareVersion(rs.getInt("hardwareVersion"));
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
			
			event.setEquipmentStatus(equipmentStatus);
			event.setEquipmentConfigure(equipmentConfigure);
			event.setEquipmentBasicInfo(equipmentBasicInfo);
			//event.setQueryInfo(queryInfo);

			return event;
		}
	}

    /*
     * 分页及条件查询
     * */
	public List<Event> pageQuery(String beginTime, String endTime, int startIndex,
			int pageSize) {
		
		String sql = "select * from events  where createdTime between ? and ? limit ?,?";
		
		return this.jdbcTemplate.query(sql, new Object[] { beginTime, endTime, startIndex, pageSize }, new EventMapper());
		
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

	public List<Event> pageQueryById(int equipmentId,String beginTime, String endTime,int startIndex,int pageSize ) {
	
        String sql = "select * from events  where equipmentId = ? and createdTime between ? and ? limit ?,?";
		
		return this.jdbcTemplate.query(sql, new Object[] { equipmentId, beginTime, endTime, startIndex, pageSize }, new EventMapper());
		
	}
}

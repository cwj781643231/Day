package com.hzmsc.scada.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.EquipmentBasicInfo;
import com.hzmsc.scada.entity.EquipmentConfigure;
import com.hzmsc.scada.entity.EquipmentStatus;
import com.hzmsc.scada.entity.view.EquipmentView;

@Repository
public class EquipmentViewDaoImpl implements EquipmentViewDao{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDatasource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EquipmentView createEquipmentView(final EquipmentView equipment) {
		final String sql = "insert into equipments "
				+ "(equipmentName, equipmentType, ipAddress,workshop, createdTime "
				+ ") " + "values (?, ?, ?, ?, ? "
				+ ")";
		/*final String sql = "INSERT INTO `equipments` ("
				+ "`equipmentName`, `equipmentType`, `ipAddress`, `workshop`, "
				+ "`isAcitved`, `createdTime` "
				+ ") VALUES ("
				+ "?, ?, ?, ?, "
				+ "?, ? "
				+ ")";*/

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "equipmentId" });
				ps.setString(1, equipment.getEquipmentName());
				ps.setString(2, equipment.getEquipmentType());
				ps.setString(3, equipment.getIpAddress());
				ps.setString(4, equipment.getWorkshop());
			//	ps.setString(5, equipment.getIsActived());
				ps.setTimestamp(5, equipment.getCreatedTime());

				return ps;
			}
		}, keyHolder);
		// TODO Auto-generated method stub
		equipment.setEquipmentId(keyHolder.getKey().intValue());

		return equipment;
	}

	public void updateEquipmentView(EquipmentView equipment) {

		String sql = "update equipments set equipmentName=?, equipmentType=?, ipAddress=?, "
				+ "workshop=?, isActived=?,createdTime=? "
				+ "where equipmentId=?";


		this.jdbcTemplate.update(sql, equipment.getEquipmentName(), equipment.getEquipmentType(),
				equipment.getIpAddress(), equipment.getWorkshop(),
				equipment.getIsActived(), equipment.getCreatedTime(),
				equipment.getEquipmentId());

	}

	public void deleteEquipmentView(EquipmentView equipment) {
		String sql = "delete from equipments where equipmentId=?";
		this.jdbcTemplate.update(sql, equipment.getEquipmentId());

	}


	
}

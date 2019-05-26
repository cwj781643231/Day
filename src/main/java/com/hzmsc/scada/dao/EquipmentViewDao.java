package com.hzmsc.scada.dao;

import com.hzmsc.scada.entity.Equipment;
import com.hzmsc.scada.entity.view.EquipmentView;

public interface EquipmentViewDao {

	public EquipmentView createEquipmentView(EquipmentView equipmentview);
	public void updateEquipmentView(EquipmentView equipmentview);
	public void deleteEquipmentView(EquipmentView equipmentview);
}

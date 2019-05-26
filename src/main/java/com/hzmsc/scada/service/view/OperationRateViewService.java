package com.hzmsc.scada.service.view;

import java.sql.Timestamp;
import java.util.List;

import com.hzmsc.scada.entity.view.OperationRateView;

public interface OperationRateViewService {

	public OperationRateView findById(int equipmentId);
	public List<OperationRateView> findAll();
	
	public OperationRateView findByIdBewteenTime(int equipmentId, Timestamp beginTime, Timestamp endTime);
	public List<OperationRateView> findBewteenTime(Timestamp beginTime, Timestamp endTime);
}

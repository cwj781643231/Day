package com.hzmsc.scada.dao.dic;

import com.hzmsc.scada.entity.dic.ErrorCode;

public interface ErrorCodeDao {

	public ErrorCode findById(int errorCodeId);
	public int countOfErrorCodeId(int errorCodeId);
	
}

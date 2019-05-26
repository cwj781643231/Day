package com.hzmsc.scada.service.dic;

import com.hzmsc.scada.entity.dic.ErrorCode;

public interface ErrorCodeService {
	public ErrorCode findById(int errorCodeId);
	public int countOfErrorCodeId(int errorCodeId);
}

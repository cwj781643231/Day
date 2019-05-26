package com.hzmsc.scada.service.dic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.dic.ErrorCodeDao;
import com.hzmsc.scada.entity.dic.ErrorCode;
@Service
public class ErrorCodeServiceImpl implements ErrorCodeService {
	
	@Autowired
	private ErrorCodeDao errorCodeDao;

	public ErrorCode findById(int errorCodeId) {
		ErrorCode errorCode = null;
		if(countOfErrorCodeId(errorCodeId) == 1){
			errorCode = errorCodeDao.findById(errorCodeId);
		}
		return errorCode;
	}

	public int countOfErrorCodeId(int errorCodeId) {
		return errorCodeDao.countOfErrorCodeId(errorCodeId);
	}

}

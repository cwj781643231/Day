package com.hzmsc.scada.entity.dic;

public class ErrorCode {
	
	private int errorCodeId;
	private String errorCodeName;
	private String errorCodeCN;
	private String errorCodeEN;
	public ErrorCode() {
		super();
	}
	public ErrorCode(int errorCodeId, String errorCodeName, String errorCodeCN, String errorCodeEN) {
		super();
		this.errorCodeId = errorCodeId;
		this.errorCodeName = errorCodeName;
		this.errorCodeCN = errorCodeCN;
		this.errorCodeEN = errorCodeEN;
	}
	public int getErrorCodeId() {
		return errorCodeId;
	}
	public void setErrorCodeId(int errorCodeId) {
		this.errorCodeId = errorCodeId;
	}
	public String getErrorCodeName() {
		return errorCodeName;
	}
	public void setErrorCodeName(String errorCodeName) {
		this.errorCodeName = errorCodeName;
	}
	public String getErrorCodeCN() {
		return errorCodeCN;
	}
	public void setErrorCodeCN(String errorCodeCN) {
		this.errorCodeCN = errorCodeCN;
	}
	public String getErrorCodeEN() {
		return errorCodeEN;
	}
	public void setErrorCodeEN(String errorCodeEN) {
		this.errorCodeEN = errorCodeEN;
	}
	@Override
	public String toString() {
		return "ErrorCode [errorCodeId=" + errorCodeId + ", errorCodeName=" + errorCodeName + ", errorCodeCN=" + errorCodeCN + ", errorCodeEN="
				+ errorCodeEN + "]";
	}

}

package com.hzmsc.scada.entity.dic;

public class DeviceType {
	
	private int typeId;
	private String typeName;
	private String typeCN;
	private String typeEN;
	public DeviceType() {
		super();
	}
	public DeviceType(int typeId, String typeName, String typeCN, String typeEN) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeCN = typeCN;
		this.typeEN = typeEN;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCN() {
		return typeCN;
	}
	public void setTypeCN(String typeCN) {
		this.typeCN = typeCN;
	}
	public String getTypeEN() {
		return typeEN;
	}
	public void setTypeEN(String typeEN) {
		this.typeEN = typeEN;
	}
	@Override
	public String toString() {
		return "DeviceType [typeId=" + typeId + ", typeName=" + typeName + ", typeCN=" + typeCN + ", typeEN="
				+ typeEN + "]";
	}

}

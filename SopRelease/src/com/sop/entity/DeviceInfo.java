package com.sop.entity;

public class DeviceInfo {
	private int dveId;
	private int dvePjtId;
	private String dveType;
	private String dvedType;
	private String dveVle;
	private String pub_crtdate;
	public int getDveId() {
		return dveId;
	}
	public void setDveId(int dveId) {
		this.dveId = dveId;
	}
	public int getDvePjtId() {
		return dvePjtId;
	}
	public void setDvePjtId(int dvePjtId) {
		this.dvePjtId = dvePjtId;
	}
	public String getDveType() {
		return dveType;
	}
	public void setDveType(String dveType) {
		this.dveType = dveType;
	}
	public String getDvedType() {
		return dvedType;
	}
	public void setDvedType(String dvedType) {
		this.dvedType = dvedType;
	}
	public String getDveVle() {
		return dveVle;
	}
	public void setDveVle(String dveVle) {
		this.dveVle = dveVle;
	}
	public String getPub_crtdate() {
		return pub_crtdate;
	}
	public void setPub_crtdate(String pub_crtdate) {
		this.pub_crtdate = pub_crtdate;
	}
	@Override
	public String toString() {
		return "DeviceInfo [dveId=" + dveId + ", dvePjtId=" + dvePjtId
				+ ", dveType=" + dveType + ", dvedType=" + dvedType
				+ ", dveVle=" + dveVle + ", pub_crtdate=" + pub_crtdate + "]";
	}
	
	
}

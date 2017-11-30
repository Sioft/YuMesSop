package com.sop.entity;

public class StationInfo {
	   //mes_stn_id
	private int mes_stn_id;
	   //mes_stn_code       varchar2(50),
	private String mes_stn_code;
	   //mes_stn_name       varchar2(50),
	private String mes_stn_name;
	   //mes_stn_EHaddress  varchar2(50),
	private String mes_stn_EHaddress;
	   //mes_stn_EHport     varchar2(50),
	private String mes_stn_EHport;
	   //mes_stn_note       varchar2(200)
	private String mes_stn_note;
	public int getMes_stn_id() {
		return mes_stn_id;
	}
	public void setMes_stn_id(int mes_stn_id) {
		this.mes_stn_id = mes_stn_id;
	}
	public String getMes_stn_code() {
		return mes_stn_code;
	}
	public void setMes_stn_code(String mes_stn_code) {
		this.mes_stn_code = mes_stn_code;
	}
	public String getMes_stn_name() {
		return mes_stn_name;
	}
	public void setMes_stn_name(String mes_stn_name) {
		this.mes_stn_name = mes_stn_name;
	}
	public String getMes_stn_EHaddress() {
		return mes_stn_EHaddress;
	}
	public void setMes_stn_EHaddress(String mes_stn_EHaddress) {
		this.mes_stn_EHaddress = mes_stn_EHaddress;
	}
	public String getMes_stn_EHport() {
		return mes_stn_EHport;
	}
	public void setMes_stn_EHport(String mes_stn_EHport) {
		this.mes_stn_EHport = mes_stn_EHport;
	}
	public String getMes_stn_note() {
		return mes_stn_note;
	}
	public void setMes_stn_note(String mes_stn_note) {
		this.mes_stn_note = mes_stn_note;
	}
	@Override
	public String toString() {
		return "StationInfo [mes_stn_id=" + mes_stn_id + ", mes_stn_code="
				+ mes_stn_code + ", mes_stn_name=" + mes_stn_name
				+ ", mes_stn_EHaddress=" + mes_stn_EHaddress
				+ ", mes_stn_EHport=" + mes_stn_EHport + ", mes_stn_note="
				+ mes_stn_note + "]";
	}
	
	
	
}

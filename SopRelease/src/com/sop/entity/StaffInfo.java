package com.sop.entity;

import java.util.Date;


public class StaffInfo {
//	 mes_sta_id         number  not null,
	private int mes_sta_id;
//	   pub_crtdate        DATE,
//	   pub_crtuser        number,
//	   pub_status         varchar2(50),
//	   mes_sta_code       varchar2(50),
	private String mes_sta_code;
//	   mes_sta_name       varchar2(50),
	private String mes_sta_name;
//	   mes_sta_position   varchar2(50),
	private String mes_sta_position;
//	   mes_sta_stime      date,
	private Date mes_sta_stime;
//	   mes_sta_etime      date,
	private Date mes_sta_etime;
//	   mes_sta_rfidNo     varchar2(20),
	private String mes_sta_rfidNo;
	public int getMes_sta_id() {
		return mes_sta_id;
	}
	public void setMes_sta_id(int mes_sta_id) {
		this.mes_sta_id = mes_sta_id;
	}
	public String getMes_sta_code() {
		return mes_sta_code;
	}
	public void setMes_sta_code(String mes_sta_code) {
		this.mes_sta_code = mes_sta_code;
	}
	public String getMes_sta_name() {
		return mes_sta_name;
	}
	public void setMes_sta_name(String mes_sta_name) {
		this.mes_sta_name = mes_sta_name;
	}
	public String getMes_sta_position() {
		return mes_sta_position;
	}
	public void setMes_sta_position(String mes_sta_position) {
		this.mes_sta_position = mes_sta_position;
	}
	public Date getMes_sta_stime() {
		return mes_sta_stime;
	}
	public void setMes_sta_stime(Date mes_sta_stime) {
		this.mes_sta_stime = mes_sta_stime;
	}
	public Date getMes_sta_etime() {
		return mes_sta_etime;
	}
	public void setMes_sta_etime(Date mes_sta_etime) {
		this.mes_sta_etime = mes_sta_etime;
	}
	public String getMes_sta_rfidNo() {
		return mes_sta_rfidNo;
	}
	public void setMes_sta_rfidNo(String mes_sta_rfidNo) {
		this.mes_sta_rfidNo = mes_sta_rfidNo;
	}
	@Override
	public String toString() {
		return "StaffInfo [mes_sta_id=" + mes_sta_id + ", mes_sta_code="
				+ mes_sta_code + ", mes_sta_name=" + mes_sta_name
				+ ", mes_sta_position=" + mes_sta_position + ", mes_sta_stime="
				+ mes_sta_stime + ", mes_sta_etime=" + mes_sta_etime
				+ ", mes_sta_rfidNo=" + mes_sta_rfidNo + "]";
	}
	
	
}

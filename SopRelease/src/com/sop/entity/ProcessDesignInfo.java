package com.sop.entity;

public class ProcessDesignInfo {
//	  mes_pdg_id         number   not null,   //id
	private Long mes_pdg_id;
//	   pub_crtdate        DATE,								//
//	   pub_crtuser        number,
//	   pub_status         varchar2(50),
//	   mes_pdg_code       varchar2(50),         //sop编码
	private String mes_pdg_code;
//	   mes_pdg_name       varchar2(50),					//sop名称
	private String mes_pdg_name;
//	   mes_pdg_imgname    varchar2(50),					//原始图片名称
	private String mes_pdg_imgname;
//	   mes_pdg_sysname    varchar2(200),				//系统名称
	private String mes_pdg_sysname;
//	   mes_pdg_path       varchar2(200),				//相对路径
	private String mes_pdg_path;
//	   mes_pdg_note       varchar2(200),		
	private String mes_pdg_note;
	public Long getMes_pdg_id() {
		return mes_pdg_id;
	}
	public void setMes_pdg_id(Long mes_pdg_id) {
		this.mes_pdg_id = mes_pdg_id;
	}
	public String getMes_pdg_code() {
		return mes_pdg_code;
	}
	public void setMes_pdg_code(String mes_pdg_code) {
		this.mes_pdg_code = mes_pdg_code;
	}
	public String getMes_pdg_name() {
		return mes_pdg_name;
	}
	public void setMes_pdg_name(String mes_pdg_name) {
		this.mes_pdg_name = mes_pdg_name;
	}
	public String getMes_pdg_imgname() {
		return mes_pdg_imgname;
	}
	public void setMes_pdg_imgname(String mes_pdg_imgname) {
		this.mes_pdg_imgname = mes_pdg_imgname;
	}
	public String getMes_pdg_sysname() {
		return mes_pdg_sysname;
	}
	public void setMes_pdg_sysname(String mes_pdg_sysname) {
		this.mes_pdg_sysname = mes_pdg_sysname;
	}
	public String getMes_pdg_path() {
		return mes_pdg_path;
	}
	public void setMes_pdg_path(String mes_pdg_path) {
		this.mes_pdg_path = mes_pdg_path;
	}
	public String getMes_pdg_note() {
		return mes_pdg_note;
	}
	public void setMes_pdg_note(String mes_pdg_note) {
		this.mes_pdg_note = mes_pdg_note;
	}
	@Override
	public String toString() {
		return "ProcessDesignInfo [mes_pdg_id=" + mes_pdg_id
				+ ", mes_pdg_code=" + mes_pdg_code + ", mes_pdg_name="
				+ mes_pdg_name + ", mes_pdg_imgname=" + mes_pdg_imgname
				+ ", mes_pdg_sysname=" + mes_pdg_sysname + ", mes_pdg_path="
				+ mes_pdg_path + ", mes_pdg_note=" + mes_pdg_note + "]";
	}
	
	
}

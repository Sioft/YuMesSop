package com.sop.entity;

public class SopInfo {
//	 mes_sop_id         number  not null,
	private Long mes_sop_id;
//	   pub_crtdate        date,
//	   pub_crtuser        number,
//	   pub_status         varchar2(50),

//工序表id
	private Long procedure_id;
//	   mes_pdt_id      		number,
	private Long mes_pdg_id;

//	   mes_sop_isdown     number(1),
	private byte mes_sop_isdown;
//	   mes_sop_isold      number(1),
	private byte mes_sop_isold;
//	   mes_sop_sequence   number(1),
	private byte mes_sop_sequence;
//	   mes_sop_iskey      number(1),
	private byte mes_sop_iskey;
//	   mes_sop_version    number(1),
	private int mes_sop_version;
//	   mes_sop_validtime  date,
	private String mes_sop_validtime;
	public Long getMes_sop_id() {
		return mes_sop_id;
	}
	public void setMes_sop_id(Long mes_sop_id) {
		this.mes_sop_id = mes_sop_id;
	}
	public Long getProcedure_id() {
		return procedure_id;
	}
	public void setProcedure_id(Long procedure_id) {
		this.procedure_id = procedure_id;
	}
	public Long getMes_pdg_id() {
		return mes_pdg_id;
	}
	public void setMes_pdg_id(Long mes_pdg_id) {
		this.mes_pdg_id = mes_pdg_id;
	}
	public byte getMes_sop_isdown() {
		return mes_sop_isdown;
	}
	public void setMes_sop_isdown(byte mes_sop_isdown) {
		this.mes_sop_isdown = mes_sop_isdown;
	}
	public byte getMes_sop_isold() {
		return mes_sop_isold;
	}
	public void setMes_sop_isold(byte mes_sop_isold) {
		this.mes_sop_isold = mes_sop_isold;
	}
	public byte getMes_sop_sequence() {
		return mes_sop_sequence;
	}
	public void setMes_sop_sequence(byte mes_sop_sequence) {
		this.mes_sop_sequence = mes_sop_sequence;
	}
	public byte getMes_sop_iskey() {
		return mes_sop_iskey;
	}
	public void setMes_sop_iskey(byte mes_sop_iskey) {
		this.mes_sop_iskey = mes_sop_iskey;
	}
	public int getMes_sop_version() {
		return mes_sop_version;
	}
	public void setMes_sop_version(int mes_sop_version) {
		this.mes_sop_version = mes_sop_version;
	}
	
	public String getMes_sop_validtime() {
		return mes_sop_validtime;
	}
	public void setMes_sop_validtime(String mes_sop_validtime) {
		this.mes_sop_validtime = mes_sop_validtime;
	}
	@Override
	public String toString() {
		return "SopInfo [mes_sop_id=" + mes_sop_id + ", procedure_id="
				+ procedure_id + ", mes_pdg_id=" + mes_pdg_id
				+ ", mes_sop_isdown=" + mes_sop_isdown + ", mes_sop_isold="
				+ mes_sop_isold + ", mes_sop_sequence=" + mes_sop_sequence
				+ ", mes_sop_iskey=" + mes_sop_iskey + ", mes_sop_version="
				+ mes_sop_version + ", mes_sop_validtime=" + mes_sop_validtime
				+ "]";
	}
	
	
}

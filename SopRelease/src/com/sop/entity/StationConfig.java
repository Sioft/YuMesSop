package com.sop.entity;

public class StationConfig {
	private int mes_scf_id;
	private String line_type;
	private String function_code;
	private int product_id;
	private String function_name;
	private int procedure_no;
	private String user_id;
	private String ip_address;
	private String mac_address;
	private String pub_crtdate;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getMes_scf_id() {
		return mes_scf_id;
	}
	public void setMes_scf_id(int mes_scf_id) {
		this.mes_scf_id = mes_scf_id;
	}
	public String getLine_type() {
		return line_type;
	}
	public void setLine_type(String line_type) {
		this.line_type = line_type;
	}
	public String getFunction_code() {
		return function_code;
	}
	public void setFunction_code(String function_code) {
		this.function_code = function_code;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	public int getProcedure_no() {
		return procedure_no;
	}
	public void setProcedure_no(int procedure_no) {
		this.procedure_no = procedure_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	
	public String getPub_crtdate() {
		return pub_crtdate;
	}
	public void setPub_crtdate(String pub_crtdate) {
		this.pub_crtdate = pub_crtdate;
	}
	@Override
	public String toString() {
		return "StationConfig [mes_scf_id=" + mes_scf_id + ", line_type="
				+ line_type + ", function_code=" + function_code
				+ ", product_id=" + product_id + ", function_name="
				+ function_name + ", procedure_no=" + procedure_no
				+ ", user_id=" + user_id + ", ip_address=" + ip_address
				+ ", mac_address=" + mac_address + ", pub_crtdate="
				+ pub_crtdate + "]";
	}
	
	
	
}

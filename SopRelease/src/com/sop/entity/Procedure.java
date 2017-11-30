package com.sop.entity;

public class Procedure {
	private int procedure_id;
	private String product_code;
	private String product_name;
	private int procedure_no;
	private String procedure_code;
	private String procedure_name;
	private String pub_crtdate;
	public int getProcedure_id() {
		return procedure_id;
	}
	public void setProcedure_id(int procedure_id) {
		this.procedure_id = procedure_id;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProcedure_no() {
		return procedure_no;
	}
	public void setProcedure_no(int procedure_no) {
		this.procedure_no = procedure_no;
	}
	public String getProcedure_code() {
		return procedure_code;
	}
	public void setProcedure_code(String procedure_code) {
		this.procedure_code = procedure_code;
	}
	public String getProcedure_name() {
		return procedure_name;
	}
	public void setProcedure_name(String procedure_name) {
		this.procedure_name = procedure_name;
	}
	public String getPub_crtdate() {
		return pub_crtdate;
	}
	public void setPub_crtdate(String pub_crtdate) {
		this.pub_crtdate = pub_crtdate;
	}
	@Override
	public String toString() {
		return "Procedure [procedure_id=" + procedure_id + ", product_code="
				+ product_code + ", product_name=" + product_name
				+ ", procedure_no=" + procedure_no + ", procedure_code="
				+ procedure_code + ", procedure_name=" + procedure_name
				+ ", pub_crtdate=" + pub_crtdate + "]";
	}
	
	
}

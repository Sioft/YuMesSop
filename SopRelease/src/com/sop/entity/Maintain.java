package com.sop.entity;

public class Maintain {
	public int yu_mtn_id;
	public String pub_crtdate;
	public String yu_mtn_note;
	public String yu_mtn_phen;
	public String off_station;
	public int getYu_mtn_id() {
		return yu_mtn_id;
	}
	public void setYu_mtn_id(int yu_mtn_id) {
		this.yu_mtn_id = yu_mtn_id;
	}
	public String getPub_crtdate() {
		return pub_crtdate;
	}
	public void setPub_crtdate(String pub_crtdate) {
		this.pub_crtdate = pub_crtdate;
	}
	public String getYu_mtn_note() {
		return yu_mtn_note;
	}
	public void setYu_mtn_note(String yu_mtn_note) {
		this.yu_mtn_note = yu_mtn_note;
	}
	public String getYu_mtn_phen() {
		return yu_mtn_phen;
	}
	public void setYu_mtn_phen(String yu_mtn_phen) {
		this.yu_mtn_phen = yu_mtn_phen;
	}
	public String getOff_station() {
		return off_station;
	}
	public void setOff_station(String off_station) {
		this.off_station = off_station;
	}
	@Override
	public String toString() {
		return "Maintain [yu_mtn_id=" + yu_mtn_id + ", pub_crtdate="
				+ pub_crtdate + ", yu_mtn_note=" + yu_mtn_note
				+ ", yu_mtn_phen=" + yu_mtn_phen + ", off_station="
				+ off_station + "]";
	}
	
}

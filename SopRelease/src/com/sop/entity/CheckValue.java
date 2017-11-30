package com.sop.entity;

public class CheckValue {
	private int CHVID; //ID	
	private String CHVCID;//barcode
	private String CHVTYPE;//检测类型
	private double CHVUVL;//检测上限
	private double CHVDVL;//检测下限
	private double CHVL;//检测值
	private String CHVRES;//检测结果
	private String CHVT;//温度
	private String CHVS;//湿度
	
	public int getCHVID() {
		return CHVID;
	}
	public void setCHVID(int cHVID) {
		CHVID = cHVID;
	}
	public String getCHVCID() {
		return CHVCID;
	}
	public void setCHVCID(String cHVCID) {
		CHVCID = cHVCID;
	}
	public String getCHVTYPE() {
		return CHVTYPE;
	}
	public void setCHVTYPE(String cHVTYPE) {
		CHVTYPE = cHVTYPE;
	}
	public double getCHVUVL() {
		return CHVUVL;
	}
	public void setCHVUVL(double cHVUVL) {
		CHVUVL = cHVUVL;
	}
	public double getCHVDVL() {
		return CHVDVL;
	}
	public void setCHVDVL(double cHVDVL) {
		CHVDVL = cHVDVL;
	}
	public double getCHVL() {
		return CHVL;
	}
	public void setCHVL(double cHVL) {
		CHVL = cHVL;
	}
	public String getCHVRES() {
		return CHVRES;
	}
	public void setCHVRES(String cHVRES) {
		CHVRES = cHVRES;
	}
	public String getCHVT() {
		return CHVT;
	}
	public void setCHVT(String cHVT) {
		CHVT = cHVT;
	}
	public String getCHVS() {
		return CHVS;
	}
	public void setCHVS(String cHVS) {
		CHVS = cHVS;
	}
}

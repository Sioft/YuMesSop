package com.sop.entity;

public class CheckInfo {
	private int CHKID;
	private int CHKPJTID;
	private String CHKTYPE;
	private int CHKUVL;
	private int CHKDVL;
	private String PUB_STATUS;
	public int getCHKID() {
		return CHKID;
	}
	public void setCHKID(int cHKID) {
		CHKID = cHKID;
	}
	public int getCHKPJTID() {
		return CHKPJTID;
	}
	public void setCHKPJTID(int cHKPJTID) {
		CHKPJTID = cHKPJTID;
	}
	public String getCHKTYPE() {
		return CHKTYPE;
	}
	public void setCHKTYPE(String cHKTYPE) {
		CHKTYPE = cHKTYPE;
	}
	public int getCHKUVL() {
		return CHKUVL;
	}
	public void setCHKUVL(int cHKUVL) {
		CHKUVL = cHKUVL;
	}
	public int getCHKDVL() {
		return CHKDVL;
	}
	public void setCHKDVL(int cHKDVL) {
		CHKDVL = cHKDVL;
	}
	public String getPUB_STATUS() {
		return PUB_STATUS;
	}
	public void setPUB_STATUS(String pUB_STATUS) {
		PUB_STATUS = pUB_STATUS;
	}
	@Override
	public String toString() {
		return "CheckInfo [CHKID=" + CHKID + ", CHKPJTID=" + CHKPJTID
				+ ", CHKTYPE=" + CHKTYPE + ", CHKUVL=" + CHKUVL + ", CHKDVL="
				+ CHKDVL + ", PUB_STATUS=" + PUB_STATUS + "]";
	}
	
	
}

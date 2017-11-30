package com.sop.entity;

import java.util.List;
import java.util.Map;

/**
 * sop所需信息展示
 * @author SIYUNX
 *
 */
public class SopShow {
	//路径+系统文件名，图片展示
	List<Map<String, Object>> pdglist;
	/*private String notePath;
	private String sysName;*/
	private List<Integer> versionlist;
	//版本号
	private int versionNo;
	//是否有新版本标识
	private int isOld;
	//新版本生效时间
	private String validTime;
	//下一版本
	private int nextVersion;
	
	public List<Map<String, Object>> getPdglist() {
		return pdglist;
	}
	public void setPdglist(List<Map<String, Object>> pdglist) {
		this.pdglist = pdglist;
	}
	public int getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	public int getIsOld() {
		return isOld;
	}
	public void setIsOld(int isOld) {
		this.isOld = isOld;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public int getNextVersion() {
		return nextVersion;
	}
	public void setNextVersion(int nextVersion) {
		this.nextVersion = nextVersion;
	}
	public List<Integer> getVersionlist() {
		return versionlist;
	}
	public void setVersionlist(List<Integer> versionlist) {
		this.versionlist = versionlist;
	}
	
	
}

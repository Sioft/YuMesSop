package com.sop.entity;

import java.io.Serializable;

public class Series  implements Serializable{
	private double percent;//数量
	private String name;//类型
	
	
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Series [percent=" + percent + ", name=" + name + "]";
	}
	
	
	
}

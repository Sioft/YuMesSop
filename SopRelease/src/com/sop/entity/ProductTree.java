package com.sop.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品大类生成树
 * @author SIYUNX
 *
 */
public class ProductTree {
	//自增ID
	private int node_id;
	//节点名称：对应各产品大类名称
	private String node_name;
	//产品大类名称
	private String node_code;
	//节点菜单等级
	private short menu_level;
	//父节点id
	private int parent_node_id;
	
	//相对路径
	private String node_path;
	//创建时间
	private String pub_crtdate;
	//childlist
	private List<ProductTree> childlist = new ArrayList<ProductTree>();
	
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public String getNode_name() {
		return node_name;
	}
	
	public String getNode_code() {
		return node_code;
	}
	public void setNode_code(String node_code) {
		this.node_code = node_code;
	}
	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}
	public short getMenu_level() {
		return menu_level;
	}
	public void setMenu_level(short menu_level) {
		this.menu_level = menu_level;
	}
	public int getParent_node_id() {
		return parent_node_id;
	}
	public void setParent_node_id(int parent_node_id) {
		this.parent_node_id = parent_node_id;
	}
	
	public String getNode_path() {
		return node_path;
	}
	public void setNode_path(String node_path) {
		this.node_path = node_path;
	}
	public List<ProductTree> getChildlist() {
		return childlist;
	}
	
	
	public String getPub_crtdate() {
		return pub_crtdate;
	}
	public void setPub_crtdate(String pub_crtdate) {
		this.pub_crtdate = pub_crtdate;
	}
	public void setChildlist(List<ProductTree> childlist) {
		this.childlist = childlist;
	}
	
	public void addTreeNode(ProductTree product){
		this.childlist.add(product);
	}
	@Override
	public String toString() {
		return "ProductTree [node_id=" + node_id + ", node_name=" + node_name
				+ ", node_code=" + node_code + ", menu_level=" + menu_level
				+ ", parent_node_id=" + parent_node_id + ", node_path="
				+ node_path + ", pub_crtdate=" + pub_crtdate + ", childlist="
				+ childlist + "]";
	}
	
	
	
	
	
	
}

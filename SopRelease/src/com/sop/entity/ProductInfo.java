package com.sop.entity;

public class ProductInfo {
	//自增ID
		private int node_id;
		//节点名称：对应各产品大类名称
		private String node_name;
		//节点菜单等级
		private short menu_level;
		//父节点id
		private int parent_node_id;
		//类型：保留
		private short node_type;
		//同一级菜单排序排序
		private short node_level;
		//相对路径
		private String path;
		//childlist
		public int getNode_id() {
			return node_id;
		}
		public void setNode_id(int node_id) {
			this.node_id = node_id;
		}
		public String getNode_name() {
			return node_name;
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
		public short getNode_type() {
			return node_type;
		}
		public void setNode_type(short node_type) {
			this.node_type = node_type;
		}
		public short getNode_level() {
			return node_level;
		}
		public void setNode_level(short node_level) {
			this.node_level = node_level;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		@Override
		public String toString() {
			return "ProductTree [node_id=" + node_id + ", node_name=" + node_name
					+ ", menu_level=" + menu_level + ", parent_node_id="
					+ parent_node_id + ", node_type=" + node_type + ", node_level="
					+ node_level + ", path=" + path + "]";
		}
		
	
}

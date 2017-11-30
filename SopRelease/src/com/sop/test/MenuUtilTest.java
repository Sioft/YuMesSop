package com.sop.test;

import com.sop.util.MenuUtil;

/**
 * 测试MenuUtilTest生成的结果集
 * @author SIYUNX
 *
 */
public class MenuUtilTest {
	public static void main(String[] args){
		String str = MenuUtil.getChildCategoryTree();
		System.out.print(str);
	}
}

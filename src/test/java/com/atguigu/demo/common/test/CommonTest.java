package com.atguigu.demo.common.test;


/**
 * @author panpala
 * @date 2017年9月7日
 */
public class CommonTest {
	
	public static void main(String[] args) {
		
		String path = CommonTest.class.getResource("/").getPath();
		System.out.println(path);
	}
	
}

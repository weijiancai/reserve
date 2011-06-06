package com.bjsxt.oa.manager;

import junit.framework.TestCase;

public class JUnitTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		System.out.println("做一些前提条件的设置");
	}

	@Override
	protected void tearDown() throws Exception {
		System.out.println("释放一些资源");
	}
	
	public void testSomething(){
		System.out.println("执行测试单元testSomething");
	}
	
	public void testSomething2(){
		System.out.println("执行测试单元testSomething2");
	}

}

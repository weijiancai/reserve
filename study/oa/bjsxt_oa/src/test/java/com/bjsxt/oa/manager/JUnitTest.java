package com.bjsxt.oa.manager;

import junit.framework.TestCase;

public class JUnitTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		System.out.println("��һЩǰ������������");
	}

	@Override
	protected void tearDown() throws Exception {
		System.out.println("�ͷ�һЩ��Դ");
	}
	
	public void testSomething(){
		System.out.println("ִ�в��Ե�ԪtestSomething");
	}
	
	public void testSomething2(){
		System.out.println("ִ�в��Ե�ԪtestSomething2");
	}

}

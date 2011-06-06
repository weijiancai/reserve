package com.bjsxt.oa.manager;

public class SystemException extends RuntimeException {
	private String key;
	private Object[] values;
	public String getKey() {
		return key;
	}
	public Object[] getValues() {
		return values;
	}
	public SystemException() {
		super();
	}
	public SystemException(String message, Throwable throwable) {
		super(message, throwable);
	}
	public SystemException(String message) {
		super(message);
	}
	public SystemException(Throwable throwable) {
		super(throwable);
	}
	
	public SystemException(String key,String message) {
		super(message);
		this.key = key;
	}
	
	public SystemException(String key,Object value,String message) {
		super(message);
		this.key = key;
		this.values = new Object[]{value};
	}
	
	public SystemException(String key,Object[] values,String message) {
		super(message);
		this.key = key;
		this.values = values;
	}
	
}

package com.bjsxt.oa;

import java.util.List;

public class PagerModel {
	private List datas;
	private int total;
	
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}

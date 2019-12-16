package com.ego.commons.pojo;

import java.io.Serializable;

public class EgoResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int status;
	
	private Object data;
	
	private String msg;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}

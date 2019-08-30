package com.luciano.cursomc.resources.exceptions;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer statusHttp;
	private String msg;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
	private Long timeStamp;
	
	public StandardError(Integer statusHttp, String msg, Long timeStamp) {
		super();
		this.statusHttp = statusHttp;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatusHttp() {
		return statusHttp;
	}

	public void setStatusHttp(Integer statusHttp) {
		this.statusHttp = statusHttp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	

}

package com.easymvp.simple.simple;


import cc.easyandroid.easyclean.httpextend.easycore.EAResult;

/**
 * 用来解析登录的返回结果
 * 
 * @author Administrator
 * @param <T>
 *
 * @param <T>
 * 
 */
public class JsonResult<T> implements EAResult {

	private String code;
	private String desc;
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public boolean isSuccess() {
		return "C0000".equals(code);
	}

	@Override
	public String getEADesc() {
		return null;
	}

	@Override
	public String getEACode() {
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


}

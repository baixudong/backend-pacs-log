package com.radida.pacs.core.common.bean;

import java.io.Serializable;

/**
 * 返回带有状态信息的对象
 * 
 * @author Author
 * @version Revision Date
 */
public class ResultDto<T> implements Serializable {

	private static final long serialVersionUID = -3059930318831708557L;

	private String resultcode;

	private T data;
	
	private T dataOther;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getDataOther() {
		return dataOther;
	}

	public void setDataOther(T dataOther) {
		this.dataOther = dataOther;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ResultDto(String resultcode) {
		this.resultcode = resultcode;
	}

	public ResultDto(String resultcode, T data) {
		this.resultcode = resultcode;
		this.data = data;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	@Override
	public String toString() {
		return "ResultDto [data=" + data + ", resultcode=" + resultcode + "]";
	}

}

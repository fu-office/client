package com.lbyt.client.error;

import com.lbyt.client.bean.JsonBean;

public class ErrorBean extends JsonBean{
	private static final long serialVersionUID = 1L;

	private String errorMessage;
	
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

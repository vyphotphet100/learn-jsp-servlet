package com.laptrinhjavaweb.utils;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.laptrinhjavaweb.model.AbstractModel;

public class MessageUtil {

	public static <T> void setMessageResponse(HttpServletRequest request, AbstractModel<T> requestModel,
			AbstractModel<T> responseModel) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
		if (requestModel.getMessage() != null && requestModel.getAlert() != null) {
			responseModel.setMessage(resourceBundle.getString(requestModel.getMessage()));
			responseModel.setAlert(requestModel.getAlert());
		}
	}
}

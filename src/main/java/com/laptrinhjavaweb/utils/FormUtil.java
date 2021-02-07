package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	
	@SuppressWarnings({ "unchecked", "deprecation"})
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
		T obj = null;
		
		try {
			obj = tClass.newInstance();
			BeanUtils.populate(obj, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			return null;
		}
		
		return obj;
	}
}

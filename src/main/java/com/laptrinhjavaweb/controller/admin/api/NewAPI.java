package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-new" })
public class NewAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private INewService newService;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			List<NewModel> res = newService.findAll();
			ObjectMapper objMap = new ObjectMapper();
			objMap.writeValue(response.getOutputStream(), res);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
			newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
			newModel = newService.save(newModel);
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.writeValue(response.getOutputStream(), newModel);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
			newService.delete(newModel.getIds());
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.writeValue(response.getOutputStream(), "{}");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			NewModel newModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
			newModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			newModel.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(request, "USERMODEL")).getUserName());
			newModel = newService.update(newModel);
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.writeValue(response.getOutputStream(), newModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

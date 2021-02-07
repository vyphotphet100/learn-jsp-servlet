package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pagable;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private INewService newService;

	@Inject
	private ICategoryService categoryService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewModel requestModel = FormUtil.toModel(NewModel.class, request);;
		NewModel responseModel= new NewModel();

		String views = "/views/admin/new/list.jsp";

		if (requestModel.getType().equals(SystemConstant.EDIT)) {
			if (requestModel.getId() != null) {
				responseModel = newService.findOne(requestModel.getId());
			}

			responseModel.setListCategory(categoryService.findAll());
			views = "/views/admin/new/edit.jsp";

		} else if (requestModel.getType().equals(SystemConstant.LIST)) {
			Pagable pagable = new PageRequest(requestModel.getPage(), requestModel.getnItemPerPage(),
					new Sorter(requestModel.getSortName(), requestModel.getSortBy()));

			responseModel.setTotalPage(newService.getTotalPage(responseModel.getnItemPerPage()));
			responseModel.setListResult(newService.findAll(pagable));
			views = "/views/admin/new/list.jsp";
		}
		
		MessageUtil.setMessageResponse(request, requestModel, responseModel);
		request.setAttribute(SystemConstant.MODEL, responseModel);
		RequestDispatcher rd = request.getRequestDispatcher(views);
		rd.forward(request, response);
	}

}

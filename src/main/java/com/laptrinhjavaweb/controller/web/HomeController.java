package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/dang-nhap", "/thoat" })
public class HomeController extends HttpServlet {

	@Inject
	private ICategoryService categoryService;

	@Inject
	private IUserService userService;

	private static final long serialVersionUID = 1L;
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("login")) {
				UserModel userModel = FormUtil.toModel(UserModel.class, request);
				String message = userModel.getMessage();
				String alert = userModel.getAlert();
				if (message != null && alert != null) {
					request.setAttribute("message", resourceBundle.getString(message));
					request.setAttribute("alert", alert);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("views/login.jsp");
				rd.forward(request, response);
			} else if (action.equals("logout")) {
				SessionUtil.getInstance().removeValue(request, "USERMODEL");
				response.sendRedirect(request.getContextPath() + "/trang-chu");
			}
		} else {
			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("login")) {
				UserModel model = new UserModel();
				model = FormUtil.toModel(UserModel.class, request);

				model = userService.findByUsernameAndPasswordAndStatus(model.getUserName(),
						model.getPassword(), model.getStatus());
				
				if (model != null) {
					SessionUtil.getInstance().putValue(request, "USERMODEL", model);
					if (model.getRole().getCode().equals("ADMIN")) {
						response.sendRedirect(request.getContextPath() + "/admin-home");
					} else if (model.getRole().getCode().equals("USER")) {
						response.sendRedirect(request.getContextPath() + "/trang-chu");
					}
				}
				else {
					response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
				}
			}
		}
	}
}

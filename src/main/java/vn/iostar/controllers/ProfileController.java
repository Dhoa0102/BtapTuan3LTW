package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.UserModel;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = {"/myaccount"})
public class ProfileController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			req.getRequestDispatcher("./views/myaccount.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/home");
		return;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		int id= Integer.parseInt(req.getParameter("id"));
		
		String fullName = req.getParameter("fullname");	
		String phone = req.getParameter("phone");
		String username=req.getParameter("username");
		UserServiceImpl service = new UserServiceImpl();
		UserModel user= service.findByUsername(username);
		String alertMsg = "";
		boolean isSuccess1 = service.updateFullname(id, fullName);
		boolean isSuccess2 = service.updatePhone(id, phone);
		if (isSuccess1 && isSuccess2) {
			System.out.println("doi thong tin thanh cong");
			alertMsg="Đổi thông tin thành công load lại trang để cập nhật thông tin";
			req.setAttribute("alert", alertMsg);
			if (user != null) {
				HttpSession session = req.getSession(true);
				session.setAttribute("account", user);
			}
		} else {
			alertMsg = "System error!";
			req.setAttribute("alert", alertMsg);
		}
		req.getRequestDispatcher("./views/myaccount.jsp").forward(req, resp);
	}
}

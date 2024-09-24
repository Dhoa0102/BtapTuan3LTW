package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iostar.models.UserModel;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.utils.Constant;
import java.io.File;
import java.io.FileNotFoundException;


@WebServlet(urlPatterns = { "/myaccount" }, name = "MultiPartServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProfileController extends HttpServlet {

	static final long serialVersionUID = 1L;

	private String getFileName(Part part) {

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return Constant.DEFAULT_FILENAME;
	}

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
		int id = Integer.parseInt(req.getParameter("id"));
		UserServiceImpl service = new UserServiceImpl();
		String username = req.getParameter("username");
		
// Load anh
		String uploadPath = File.separator + Constant.UPLOAD_DIRECTORY; 
		boolean isSuccess3 = false;
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
			 uploadDir.mkdir();
		try {
			 String fileName = "";
			for (Part part : req.getParts()) {
				 fileName = getFileName(part);
				 System.out.println("ten file"+fileName);
				 if(!fileName.equals("default.file")) {
					 System.out.println(fileName);
					 isSuccess3 = service.updateImages(id, fileName);
				 }
				 part.write(uploadPath + File.separator + fileName);
			}
			 req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
		 }
		catch (FileNotFoundException fne) {
		req.setAttribute("message", "There was an error: " + fne.getMessage());
		}
			 
// cap nhat Fullname phone 

		String fullName = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		String alertMsg = "";
		boolean isSuccess1 = service.updateFullname(id, fullName);
		boolean isSuccess2 = service.updatePhone(id, phone);
		if (isSuccess1 && isSuccess2&&isSuccess3) {
			System.out.println("doi thong tin thanh cong");
			alertMsg = "Đổi thông tin thành công";
			req.setAttribute("alert", alertMsg);
			UserModel user = service.findByUsername(username);
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

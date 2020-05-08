package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import security.Md5;

public class ChangePasswordServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		int token = Integer.parseInt(new String((request.getParameter("id")).getBytes("ISO-8859-1"),"UTF-8"));
		String password = new String((request.getParameter("password")).getBytes("ISO-8859-1"),"UTF-8");
		UserDao userdao=new UserDao();
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		try {
			int uid=(token/8-2)/5;
			userdao.changePassword(uid, Md5.getMD5(password));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

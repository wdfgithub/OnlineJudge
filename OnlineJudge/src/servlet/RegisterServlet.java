package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.Md5;
import dao.UserDao;

public class RegisterServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String username = new String((request.getParameter("username")).getBytes("ISO-8859-1"),"UTF-8");
		String password = new String((request.getParameter("password")).getBytes("ISO-8859-1"),"UTF-8");
		String email=new String((request.getParameter("email")).getBytes("ISO-8859-1"),"UTF-8");
		PrintWriter out=response.getWriter();
		UserDao userdao=new UserDao();
		try {
			userdao.adduser(username,Md5.getMD5(password),email);
			response.sendRedirect("/OnlineJudge/login.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

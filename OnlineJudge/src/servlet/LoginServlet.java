package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.CheckSQLInjectionAttack;
import security.Md5;
import vo.User;
import dao.QuestionDao;
import dao.UserDao;

public class LoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String username = new String((request.getParameter("username")).getBytes("ISO-8859-1"),"UTF-8");
		String password = new String((request.getParameter("password")).getBytes("ISO-8859-1"),"UTF-8");
		UserDao userdao=new UserDao();
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		try {
			if(!CheckSQLInjectionAttack.isValid(username)){
				out.println("用户名含有SQL检测字符！");
			}else {
				User auser=userdao.check(username, Md5.getMD5(password));
				if(auser!=null) {
					HttpSession session=request.getSession();
					session.setAttribute("user", auser);
					QuestionDao quetiondao=new QuestionDao();
					ArrayList quetions=quetiondao.getQuetionTitle(auser.getUname());
					session.setAttribute("quetions", quetions );
					out.println("登陆成功，跳转中...");
				}else {
					out.println("用户名或密码错误");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

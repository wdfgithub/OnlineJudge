package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.CheckSQLInjectionAttack;

import dao.UserDao;


public class CheckformServlet  extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String username = new String((request.getParameter("username")).getBytes("ISO-8859-1"),"UTF-8");
		String email = new String((request.getParameter("email")).getBytes("ISO-8859-1"),"UTF-8");
		String password = new String((request.getParameter("password")).getBytes("ISO-8859-1"),"UTF-8");
		String code = new String((request.getParameter("code")).getBytes("ISO-8859-1"),"UTF-8");
		
		HttpSession session=request.getSession();
		String randStr=(String)session.getAttribute("randStr");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		if(!code.equals(randStr)) {
			out.println("��֤�����");
		}else {
			UserDao userdao=new UserDao();
			if(!CheckSQLInjectionAttack.isValid(username)){
				out.println("�û�������SQL����ַ���");
			}else if(!CheckSQLInjectionAttack.isValid(username)){
				out.println("���京��SQL����ַ���");
			}else {
				try {
					String result=userdao.check_registered(email, username);
					out.println(result);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}
	}
}
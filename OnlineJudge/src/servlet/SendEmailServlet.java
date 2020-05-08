package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import vo.User;
import util.EmailUtil;

public class SendEmailServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String email = new String((request.getParameter("email")).getBytes("ISO-8859-1"),"UTF-8");
		UserDao userdao=new UserDao();
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		try {
			User user=userdao.hasUser(email);
			if(user!=null) {
				int token=(user.getUid()*5+2)*8;
				EmailUtil.sendEmail(email, "�޸��������", "������������޸��������:http://111.230.56.43/OnlineJudge/changepassword.jsp?id="+token);
				out.println("�ѷ��͸����ʼ����������");
			}else {
				out.println("�����ڵ�����");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

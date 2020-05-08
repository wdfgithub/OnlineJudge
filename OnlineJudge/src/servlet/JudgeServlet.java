package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDao;
import dao.StatusDao;
import security.CheckSQLInjectionAttack;
import util.*;
import vo.Question;

public class JudgeServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String code = new String((request.getParameter("code")).getBytes("ISO-8859-1"),"UTF-8").trim();
		String uname= new String((request.getParameter("uname")).getBytes("ISO-8859-1"),"UTF-8").trim();
		int qid = Integer.parseInt(new String((request.getParameter("qid")).getBytes("ISO-8859-1"),"UTF-8"));
		int uid = Integer.parseInt(new String((request.getParameter("uid")).getBytes("ISO-8859-1"),"UTF-8"));
		String id=uid+qid+"";
		
		if(!CheckSQLInjectionAttack.isValid(code)) {
			response.sendRedirect("/OnlineJudge/worningCode.html");
		}
		
		StatusDao statusdao=new StatusDao();
		QuestionDao quetiondao=new QuestionDao();
		Question quetion=quetiondao.getQuetion(qid);
		CompileJava cj=new CompileJava();
		String sourcecode="import java.util.*;import java.math.*;public class Solution"+id+" {"+code+"}";
		String qname=quetion.getName();
		cj.setData(sourcecode, id,quetion.getInputTest().trim(), quetion.getOutputTest().trim());
		cj.start();

		String result=null;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if(cj.isAlive()) {
			cj.stop();
			result="Time Limit Exceeded";
		}else {
			result=cj.getResult();
		}
		
		System.out.println(result);
		int state=0;
		if(result.equals("Accept")) {
			state=1;
		}else if(result.equals("Wrong Answer")){
			state=2;
		}else if(result.equals("Compilate Error")){
			state=3;
		}else if(result.equals("Time Limit Exceeded")){
			state=4;
		}
		try {
			statusdao.insertRecord(uname,qname,state,0,0,Timestamp.getStamp(),2,code);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		response.sendRedirect("/OnlineJudge/submitrecord.jsp");
	}
}

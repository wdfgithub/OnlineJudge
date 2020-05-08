package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import vo.Question;

public class QuestionDao {

	Connection conn=null;
	public ArrayList getQuetionTitle(String uname) throws Exception{
		this.initConnection();
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_QUETION]";
		ResultSet rs=stat.executeQuery(sql);
		ArrayList quetions=new ArrayList();
		for(int i=0;i<1;i++) {
			quetions.add(new HashMap());
		}
		while(rs.next()) {
			Statement stat_temp=conn.createStatement();
			sql="select * from [onlinejudge].[dbo].[T_STATUS] where qname='"+rs.getString("qname").trim()+"' and uname='"+uname+"';";
			ResultSet rs2=stat_temp.executeQuery(sql);
			boolean wa=false,ac=false;
			HashMap temp=(HashMap) quetions.get(rs.getInt("qlevel")-1);
			while(rs2.next()) {
				if(rs2.getInt("result")==1) {
					temp.put(rs.getInt("qid"), rs.getString("qname").trim()+" ac");
					ac=true;
				}else {
					wa=true;
				}
			}
			if(ac==false&&wa==true) {
				temp.put(rs.getInt("qid"), rs.getString("qname").trim()+" wa");
			}else if(ac==false&&wa==false){
				temp.put(rs.getInt("qid"), rs.getString("qname").trim()+" us");
			}
		}
		this.closeConnection();
		return quetions;
	}
	public int getQuesionNumber() throws Exception {
		this.initConnection();
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_QUETION]";
		ResultSet rs=stat.executeQuery(sql);
		int counter=0;
		while(rs.next()) {counter++;}
		this.closeConnection();
		return counter;
	}
	public Question getQuetion(int qid) {
		Question quetion=new Question();
		try {
			this.initConnection();
			Statement stat=conn.createStatement();
			String sql="select * from [onlinejudge].[dbo].[T_QUETION] where qid="+qid;
			ResultSet rs=stat.executeQuery(sql);
			if(rs.next()) {
				quetion.setDescription(rs.getString("qdescription").trim());
				quetion.setInputDescription(rs.getString("qinput").trim());
				quetion.setInputExample(rs.getString("qinputexample").trim());
				quetion.setLevel(rs.getInt("qlevel"));
				quetion.setOutputDescription(rs.getString("qoutput").trim());
				quetion.setOutputExample(rs.getString("qoutputexample").trim());
				quetion.setInputTest(rs.getString("qinputtest").trim());
				quetion.setOutputTest(rs.getString("qoutputtest").trim());
				quetion.setQid(qid);
				quetion.setName(rs.getString("qname").trim());
			}
			this.closeConnection();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return quetion;
		
	}
	public int getQid(String qname) {
		int qid=0;
		try {
			this.initConnection();
			Statement stat=conn.createStatement();
			String sql="select * from [onlinejudge].[dbo].[T_QUETION] where qname='"+qname+"';";
			ResultSet rs=stat.executeQuery(sql);
			if(rs.next()) {
				qid=rs.getInt("qid");
			}
			this.closeConnection();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return qid;
		
	}
	public String getQname(int qid) {
		String qname="";
		try {
			this.initConnection();
			Statement stat=conn.createStatement();
			String sql="select * from [onlinejudge].[dbo].[T_QUETION] where qid="+qid+";";
			ResultSet rs=stat.executeQuery(sql);
			if(rs.next()) {
				qname=rs.getString("qname");
			}
			this.closeConnection();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return qname;
		
	}
	public void initConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	    String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=onlinejudge";
	    conn=DriverManager.getConnection(url,"sa","Admin12345");
	}
	public void closeConnection() throws Exception{
		conn.close();
	}
}

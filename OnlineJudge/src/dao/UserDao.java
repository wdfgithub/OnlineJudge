package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.User;

public class UserDao {
	private Connection conn=null;
	
	public User check(String username, String password)  throws Exception{
		this.initConnection();
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_USER] where (uname='"+username+"' and upsw='"+password+"') or (uemail='"+username+"' and upsw='"+password+"');";
		ResultSet rs=stat.executeQuery(sql);
		User user=null;
		StatusDao statusdao=new StatusDao();
		if(rs.next()) {
			user=new User();
			int nosolution=0;
			String uname=rs.getString("uname").trim();
			user.setUid(rs.getInt("uid"));
			user.setUname(uname);
			user.setEmail(rs.getString("uemail").trim());

			this.closeConnection();
			ArrayList states=new ArrayList();
			try {
				states=statusdao.getUserAL(uname);
			}catch(Exception e){
				e.printStackTrace();
			}
			user.setNOsolution(states.size());
			user.setStates(states);
		}else {
			this.closeConnection();
		}
		return user;
	}
	public String check_registered(String email,String username) throws Exception {
		this.initConnection();
		String result="注册成功";
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_USER] where uname='"+username+"';";
		ResultSet rs=stat.executeQuery(sql);
		while(rs.next()) {
			result="用户名已被注册";
		}

		Statement stat2=conn.createStatement();
		String sql2="select * from [onlinejudge].[dbo].[T_USER] where uemail='"+email+"';";
		ResultSet rs2=stat.executeQuery(sql2);
		while(rs2.next()) {
			result="邮箱已被注册";
		}
		this.closeConnection();
		return result;
	}
	public void adduser(String username, String password, String email) throws Exception {
		this.initConnection();
		Statement stat=conn.createStatement();
		int uid= (int)(System.currentTimeMillis()%10000007);
		String sql="INSERT INTO T_USER VALUES("+Integer.toString(uid)+",'"+username+"','"+password+"','"+email+"');";
		stat.executeUpdate(sql);
		this.closeConnection();
	}
	public User hasUser(String email) throws Exception {
		this.initConnection();
		User user=null;
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_USER] where uemail='"+email+"';";
		ResultSet rs=stat.executeQuery(sql);
		while(rs.next()) {
			user=new User();
			user.setUid(rs.getInt("uid"));
			user.setUname(rs.getString("uname").trim());
			user.setEmail(rs.getString("uemail").trim());
		}
		this.closeConnection();
		return user;
	}
	public void changePassword(int uid,String password) throws Exception {
		this.initConnection();
		User user=null;
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_USER] where uid="+uid+";";
		ResultSet rs=stat.executeQuery(sql);
		while(rs.next()) {
			user=new User();
			user.setUid(rs.getInt("uid"));
			user.setUname(rs.getString("uname").trim());
			user.setEmail(rs.getString("uemail").trim());
		}
		this.closeConnection();
	}
	public User getUser(int uid) throws Exception {
		this.initConnection();
		User user=null;
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_USER] where uid="+uid+";";
		ResultSet rs=stat.executeQuery(sql);
		StatusDao statusdao=new StatusDao();
		if(rs.next()) {
			user=new User();
			int nosolution=0;
			String uname=rs.getString("uname").trim();
			user.setUid(rs.getInt("uid"));
			user.setUname(uname);
			user.setEmail(rs.getString("uemail").trim());

			this.closeConnection();
			ArrayList states=new ArrayList();
			try {
				states=statusdao.getUserAL(uname);
			}catch(Exception e){
				e.printStackTrace();
			}
			user.setNOsolution(states.size());
			user.setStates(states);
		}else {
			this.closeConnection();
		}
		return user;
	}
	public int getUserid(String uname) throws Exception {
		this.initConnection();
		int uid=1;
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_USER] where uname='"+uname+"';";
		ResultSet rs=stat.executeQuery(sql);
		while(rs.next()) {
			uid=rs.getInt("uid");
		}
		this.closeConnection();
		return uid;
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

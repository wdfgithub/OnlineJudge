package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Record;

public class StatusDao {
	private Connection conn=null;
	public ArrayList getUserAL(String uname) throws Exception{
		this.initConnection();
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_STATUS] where uname='"+uname+"' and result=1;";
		ResultSet rs=stat.executeQuery(sql);
		ArrayList states=new ArrayList();
		while(rs.next()) {
			if(!states.contains(rs.getString("qname").trim()))
				states.add(rs.getString("qname").trim());
		}
		this.closeConnection();
		return states;
	}
	public ArrayList getRecord(String word) throws Exception {
		this.initConnection();
		Statement stat=conn.createStatement();
		String sql="";
		if(word=="") {
			sql="select * from [onlinejudge].[dbo].[T_STATUS];";
		}else {
			sql="select * from [onlinejudge].[dbo].[T_STATUS] where uname='"+word+"' or qname='"+word+"';";
		}
		ResultSet rs=stat.executeQuery(sql);
		ArrayList records=new ArrayList();
		
		while(rs.next()) {
			Record record=new Record();
			record.setCode(rs.getString("code"));
			record.setCostmemory(rs.getInt("costmemory"));
			record.setCosttime(rs.getInt("costtime"));
			record.setLanguage(rs.getInt("language"));
			record.setQname(rs.getString("qname").trim());
			record.setResult(rs.getInt("result"));
			record.setSubmittime(rs.getInt("submittime"));
			record.setUname(rs.getString("uname").trim());
			records.add(record);
		}
		this.closeConnection();
		return records;
	}
	public Record getRecord(int runid) throws Exception {
		this.initConnection();
		Statement stat=conn.createStatement();
		String sql="select * from [onlinejudge].[dbo].[T_STATUS] where submittime="+runid;
		ResultSet rs=stat.executeQuery(sql);

		Record record=new Record();
		if(rs.next()) {
			record.setCode(rs.getString("code"));
			record.setCostmemory(rs.getInt("costmemory"));
			record.setCosttime(rs.getInt("costtime"));
			record.setLanguage(rs.getInt("language"));
			record.setQname(rs.getString("qname").trim());
			record.setResult(rs.getInt("result"));
			record.setSubmittime(rs.getInt("submittime"));
			record.setUname(rs.getString("uname").trim());
		}
		this.closeConnection();
		return record;
	}
	public void insertRecord(String uname, String qname, int state, int i, int j, int stamp, int k, String code) throws Exception {
		this.initConnection();
		Statement stat=conn.createStatement();
		String sql="insert into [onlinejudge].[dbo].[T_STATUS] values('"+uname+"','"+qname+"',"+state+","+i+","+j+","+stamp+","+k+",'"+code+"')";
		stat.executeUpdate(sql);
		this.closeConnection();
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

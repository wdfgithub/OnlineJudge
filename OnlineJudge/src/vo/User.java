package vo;

import java.util.ArrayList;

public class User {
	private int uid;
	private String uname;
	private ArrayList states;
	private String email;
	private int NOsolution;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid=uid;
	}
	public int getNOsolution() {
		return NOsolution;
	}
	public void setNOsolution(int NOsolution) {
		this.NOsolution=NOsolution;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname=uname;
	}
	public ArrayList getStates() {
		return states;
	}
	public void setStates(ArrayList states) {
		this.states=states;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
}

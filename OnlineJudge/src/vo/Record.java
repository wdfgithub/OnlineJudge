package vo;

public class Record {
	private String uname;
	private String qname;
	private int result;
	private int costmemory;
	private int costtime;
	private int submittime;
	private int language;
	private String code;
	public void setCode(String code) {
		this.code=code;
	}
	public String getCode() {
		return code;
	}
	public void setUname(String uname) {
		this.uname=uname;
	}
	public String getUname() {
		return uname;
	}
	public void setQname(String qname) {
		this.qname=qname;
	}
	public String getQname() {
		return qname;
	}
	public void setResult(int result) {
		this.result=result;
	}
	public int getResult() {
		return result;
	}
	public void setCostmemory(int costmemory) {
		this.costmemory=costmemory;
	}
	public int getCostmemory() {
		return costmemory;
	}
	public void setCosttime(int costtime) {
		this.costtime=costtime;
	}
	public int getCosttime() {
		return costtime;
	}
	public void setSubmittime(int submittime) {
		this.submittime=submittime;
	}
	public int getSubmittime() {
		return submittime;
	}
	public void setLanguage(int language) {
		this.language=language;
	}
	public int getLanguage() {
		return language;
	}
}

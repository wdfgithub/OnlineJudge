package vo;

public class Question {
	private String name;
	private String description;
	private String inputDescription;
	private String outputDescription;
	private String inputExample;
	private String outputExample;
	private String inputTest;
	private String outputTest;
	private int qid;
	private int level;
	private int status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getInputDescription() {
		return inputDescription;
	}
	public void setInputDescription(String inputDescription) {
		this.inputDescription=inputDescription;
	}
	public String getOutputDescription() {
		return outputDescription;
	}
	public void setOutputDescription(String outputDescription) {
		this.outputDescription=outputDescription;
	}
	public String getInputExample() {
		return inputExample;
	}
	public void setInputExample(String inputExample) {
		this.inputExample=inputExample;
	}
	public String getOutputExample() {
		return outputExample;
	}
	public void setOutputExample(String outputExample) {
		this.outputExample=outputExample;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level=level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status=status;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid=qid;
	}
	public String getInputTest() {
		return inputTest;
	}
	public void setInputTest(String inputTest) {
		this.inputTest=inputTest;
	}
	public String getOutputTest() {
		return outputTest;
	}
	public void setOutputTest(String outputTest) {
		this.outputTest=outputTest;
	}
}

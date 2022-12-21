package SquiRun;

public class MemDTO {
	int num;
	String id;
	String pwd;
	
	public MemDTO(int num, String id, String pwd) {
		this.num = num;
		this.id = id;
		this.pwd = pwd;
	}
	
	public MemDTO() {
		
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}

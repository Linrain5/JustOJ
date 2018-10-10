package just.oj;

public class Quest {
	private String name;
	private int id;
	private String yangli1;
	private String yanglia1;
	private String yangli2;
	private String yanglia2;
	private String yangli3;
	private String yanglia3;
	private String yangli4;
	private String yanglia4;
	private String yangli5;
	private String yanglia5;
	private String question;
	public void setname(String name) {
		this.name = name;
	}
	public String getname() {
		return name;
	}
	public void setid(int id) {
		this.id = id;
	}
	public int getid() {
		return id;
	}
	public void setquestion(String question) {
		this.question=question;
	}
	public String getquestion() {
		return this.question;
	}
	
	//样例输入
	public void sety1(String y1) {
		this.yangli1=y1;
	}
	public String gety1() {
		return this.yangli1;
	}
	
	
	public void sety2(String y2) {
		this.yangli2=y2;
	}
	public String gety2() {
		return this.yangli2;
	}
	
	public void sety3(String y3) {
		this.yangli3=y3;
	}
	public String gety3() {
		return this.yangli3;
	}
	
	public void sety4(String y4) {
		this.yangli4=y4;
	}
	public String gety4() {
		return this.yangli4;
	}
	public void sety5(String y5) {
		this.yangli5=y5;
	}
	public String gety5() {
		return this.yangli5;
	}
	//输出
	public String getya1() {
		return this.yanglia1;
	}
	public void setya1(String ya1) {
		this.yanglia1=ya1;
	}
	
	public String getya2() {
		return this.yanglia2;
	}
	public void setya2(String ya2) {
		this.yanglia2=ya2;
	}
	
	public String getya3() {
		return this.yanglia3;
	}
	public void setya3(String ya3) {
		this.yanglia3=ya3;
	}
	
	public String getya4() {
		return this.yanglia4;
	}
	public void setya4(String ya4) {
		this.yanglia4=ya4;
	}
	
	public String getya5() {
		return this.yanglia5;
	}
	public void setya5(String ya5) {
		this.yanglia5=ya5;
	}
	
	
}

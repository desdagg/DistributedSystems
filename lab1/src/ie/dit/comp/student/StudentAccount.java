package ie.dit.comp.student;

public  class StudentAccount {

	protected String name;

	protected int age;

	protected String address;

	protected String studentNo;

	public StudentAccount() {
		this("",0,"","");
	}

	public StudentAccount(String name, int age, String studentNo, String address) {
		this.name = name;
		this.age = age;
		this.studentNo = studentNo;
		this.address = address;
	}

	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
}
package application;

public class Lawyer {
	private int ID_number;
	private String Lawyer_Name;
	private String Address;
	private String Phone_number;
	private String Email_registered;
	private String L_Password;
	private String Gender;
	private int manager_ssn;
	
	public Lawyer(){
		super();
	}
	
	
	public Lawyer(int ID_number, String Lawyer_Name, String Address, String Phone_number, String Email_registered, 
				  String L_Password, String Gender, int manager_ssn){
		super();
		this.ID_number = ID_number;
		this.Lawyer_Name = Lawyer_Name;
		this.Address = Address;
		this.Phone_number = Phone_number;
		this.Email_registered = Email_registered;
		this.L_Password = L_Password;
		this.Gender = Gender;
		this.manager_ssn = manager_ssn;
	}


	public int getID_number() {
		return ID_number;
	}
	public void setID_number(int iD_number) {
		ID_number = iD_number;
	}


	public String getLawyer_Name() {
		return Lawyer_Name;
	}
	public void setLawyer_Name(String lawyer_Name) {
		Lawyer_Name = lawyer_Name;
	}


	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}


	public String getPhone_number() {
		return Phone_number;
	}
	public void setPhone_number(String phone_number) {
		Phone_number = phone_number;
	}


	public String getEmail_registered() {
		return Email_registered;
	}
	public void setEmail_registered(String email_registered) {
		Email_registered = email_registered;
	}


	public String getL_Password() {
		return L_Password;
	}
	public void setL_Password(String l_Password) {
		L_Password = l_Password;
	}


	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}


	public int getManager_ssn() {
		return manager_ssn;
	}
	public void setManager_ssn(int manager_ssn) {
		this.manager_ssn = manager_ssn;
	}
}

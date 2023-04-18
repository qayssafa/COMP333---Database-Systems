package application;

public class Defendent {
	private int ID_number;
	private String Defendent_Name;
	private String Address;
	private String Phone_number;

	public Defendent(){
		super();
	}

	public Defendent(int iD_number, String defendent_Name, String address, String phone_number) {
		super();
		ID_number = iD_number;
		Defendent_Name = defendent_Name;
		Address = address;
		Phone_number = phone_number;
	}

	
	public int getID_number() {
		return ID_number;
	}
	public void setID_number(int iD_number) {
		ID_number = iD_number;
	}

	
	public String getDefendent_Name() {
		return Defendent_Name;
	}
	public void setDefendent_Name(String defendent_Name) {
		Defendent_Name = defendent_Name;
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
}

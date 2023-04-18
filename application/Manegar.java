package application;

public class Manegar {

	String email;
	String password;
	
	static Manegar mang = new Manegar();
	
	public Manegar() {
		super();
		
	}
	public String getName() {
		return email;
	}
	public void setName(String name) {
		this.email = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

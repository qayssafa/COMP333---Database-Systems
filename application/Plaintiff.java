package application;

public class Plaintiff{
	private int P_id;
	private String P_name;
	private String P_address;
	private String P_phone_number;
	
	public Plaintiff(){
		super();
	}
	
	
	public Plaintiff(int P_id, String P_name, String P_address, String P_phone_number){
		super();
		this.P_id = P_id;
		this.P_name = P_name;
		this.P_address = P_address;
		this.P_phone_number = P_phone_number;
	}


	public int getP_id() {
		return P_id;
	}
	public void setP_id(int p_id) {
		P_id = p_id;
	}


	public String getP_name() {
		return P_name;
	}
	public void setP_name(String p_name) {
		P_name = p_name;
	}


	public String getP_address() {
		return P_address;
	}
	public void setP_address(String p_address) {
		P_address = p_address;
	}
	
	
	public String getP_phone_number() {
		return P_phone_number;
	}
	public void setP_phone_number(String p_phone_number) {
		P_phone_number = p_phone_number;
	}
}

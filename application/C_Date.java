package application;

public class C_Date{
	private int Date_id;
	private String Date_of_session;
	private String D_time;
	
	public C_Date(){
		super();
	}
	
	
	public C_Date(int Date_id, String Date_of_session, String D_time){
		super();
		this.Date_id = Date_id;
		this.Date_of_session = Date_of_session;
		this.D_time = D_time;
	}


	public int getDate_id() {
		return Date_id;
	}
	public void setDate_id(int date_id) {
		Date_id = date_id;
	}


	public String getDate_of_session() {
		return Date_of_session;
	}
	public void setDate_of_session(String date_of_session) {
		Date_of_session = date_of_session;
	}


	public String getD_time() {
		return D_time;
	}
	public void setD_time(String d_time) {
		D_time = d_time;
	}	
}

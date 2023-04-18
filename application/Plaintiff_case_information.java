package application;

public class Plaintiff_case_information {
	private int P_id;
	private String P_name;
    private String Lawyer_Name;
    private String Date_of_session;
	private String D_time;
	private String case_number_in_the_court;

	
	public Plaintiff_case_information(){
		super();
	}
	
	
	public Plaintiff_case_information(int P_id,String P_name, String Lawyer_Name , String Date_of_session, String D_time,String case_number_in_the_court){
		super();
		this.P_id = P_id;
		this.P_name = P_name;
		this.Lawyer_Name = Lawyer_Name;
		this.Date_of_session = Date_of_session;
		this.D_time = D_time;
		this.case_number_in_the_court = case_number_in_the_court;

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
	
	public String getLawyer_Name() {
		return Lawyer_Name;
	}
	public void setLawyer_Name(String lawyer_Name) {
		Lawyer_Name = lawyer_Name;
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
	
	public String getCase_number_in_the_court() {
		return case_number_in_the_court;
	}
	public void setCase_number_in_the_court(String case_number_in_the_court) {
		this.case_number_in_the_court = case_number_in_the_court;
	}
}

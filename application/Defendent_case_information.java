package application;

public class Defendent_case_information {
	private int ID_number;
	private String Defendent_Name;
    private String Lawyer_Name;
    private String Date_of_session;
	private String D_time;
	private String case_number_in_the_court;

	
	public Defendent_case_information(){
		super();
	}
	
	
	public Defendent_case_information(int ID_number,String Defendent_Name, String Lawyer_Name , String Date_of_session, String D_time,String case_number_in_the_court){
		super();
		this.ID_number = ID_number;
		this.Defendent_Name = Defendent_Name;
		this.Lawyer_Name = Lawyer_Name;
		this.Date_of_session = Date_of_session;
		this.D_time = D_time;
		this.case_number_in_the_court = case_number_in_the_court;

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


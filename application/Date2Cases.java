package application;

public class Date2Cases {
	
	private int Date_id;
	private int Case_id;
	
	public Date2Cases(){
		super();
	}
	
	public Date2Cases(int Date_id, int Case_id) {
		super();
		this.Date_id = Date_id;
		this.Case_id = Case_id;
		
	}
	
	public int getDate_id() {
		return Date_id;
	}
	public void setDate_id(int date_id) {
		Date_id = date_id;
	}
	public int getCase_id() {
		return Case_id;
	}
	public void setCase_id(int case_id) {
		Case_id = case_id;
	}
	
}

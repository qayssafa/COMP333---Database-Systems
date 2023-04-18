package application;

public class Cases {
	private int Case_id;
	private String case_number_in_the_court;
	
	public Cases(){
		super();
	}

	public Cases(int case_id, String case_number_in_the_court) {
		super();
		this.Case_id = case_id;
		this.case_number_in_the_court = case_number_in_the_court;
	}

	
	public int getCase_id() {
		return Case_id;
	}
	public void setCase_id(int case_id) {
		Case_id = case_id;
	}

	
	public String getCase_number_in_the_court() {
		return case_number_in_the_court;
	}
	public void setCase_number_in_the_court(String case_number_in_the_court) {
		this.case_number_in_the_court = case_number_in_the_court;
	}

}

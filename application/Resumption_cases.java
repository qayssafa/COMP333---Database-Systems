package application;

public class Resumption_cases {
	private int Case_id;
	private String case_description;
	
	public Resumption_cases(){
		super();
	}

	public Resumption_cases(int case_id, String case_description) {
		super();
		Case_id = case_id;
		this.case_description = case_description;
	}

	
	public int getCase_id() {
		return Case_id;
	}
	public void setCase_id(int case_id) {
		Case_id = case_id;
	}

	
	public String getCase_description() {
		return case_description;
	}
	public void setCase_description(String case_description) {
		this.case_description = case_description;
	}
	
	
}

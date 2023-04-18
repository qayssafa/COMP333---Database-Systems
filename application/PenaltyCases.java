package application;

public class PenaltyCases {
	private int Case_id;
	private String penalty_type;
	private String court_type;
	private String case_description;
	
	public PenaltyCases(){
		super();
	}

	public PenaltyCases(int case_id, String penalty_type, String court_type, String case_description) {
		super();
		Case_id = case_id;
		this.penalty_type = penalty_type;
		this.court_type = court_type;
		this.case_description = case_description;
	}

	
	public int getCase_id() {
		return Case_id;
	}
	public void setCase_id(int case_id) {
		Case_id = case_id;
	}

	
	public String getPenalty_type() {
		return penalty_type;
	}
	public void setPenalty_type(String penalty_type) {
		this.penalty_type = penalty_type;
	}

	
	public String getCourt_type() {
		return court_type;
	}
	public void setCourt_type(String court_type) {
		this.court_type = court_type;
	}

	
	public String getCase_description() {
		return case_description;
	}
	public void setCase_description(String case_description) {
		this.case_description = case_description;
	}
}

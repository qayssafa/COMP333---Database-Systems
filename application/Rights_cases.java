package application;

public class Rights_cases {
	private int Case_id;
	private String file_state;
	private String court_type;
	private String case_description;
	
	public Rights_cases(){
		super();
	}

	public Rights_cases(int case_id, String file_state, String court_type, String case_description) {
		super();
		Case_id = case_id;
		this.file_state = file_state;
		this.court_type = court_type;
		this.case_description = case_description;
	}

	
	public int getCase_id() {
		return Case_id;
	}
	public void setCase_id(int case_id) {
		Case_id = case_id;
	}

	
	public String getFile_state() {
		return file_state;
	}
	public void setFile_state(String file_state) {
		this.file_state = file_state;
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

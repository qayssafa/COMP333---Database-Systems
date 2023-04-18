package application;

public class Payement2Cases2lawyer {
	
	private int P_id;
	private int Case_id;
	private int Lawyer_ID;
	
	public Payement2Cases2lawyer(){
		super();
	}

	public Payement2Cases2lawyer(int P_id, int Case_id, int Lawyer_ID) {
		super();
		this.P_id = P_id;
		this.Case_id = Case_id;
		this.Lawyer_ID = Lawyer_ID;
	}
	
	public int getP_id() {
		return P_id;
	}
	public void setP_id(int p_id) {
		P_id = p_id;
	}
	public int getCase_id() {
		return Case_id;
	}
	public void setCase_id(int case_id) {
		Case_id = case_id;
	}
	public int getLawyer_ID() {
		return Lawyer_ID;
	}
	public void setLawyer_ID(int lawyer_ID) {
		Lawyer_ID = lawyer_ID;
	}
	 
}

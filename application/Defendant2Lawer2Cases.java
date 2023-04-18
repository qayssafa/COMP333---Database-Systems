package application;

public class Defendant2Lawer2Cases {

private int Defendent_ID_number;
private int Case_ID;
private int Lawyer_ID;	

	public Defendant2Lawer2Cases(){
		super();
	}
	
	public Defendant2Lawer2Cases(int Defendent_ID_number, int Case_ID,int Lawyer_ID) {
		super();
		this.Defendent_ID_number = Defendent_ID_number;
		this.Case_ID = Case_ID;
		this.Lawyer_ID=Lawyer_ID;
		
	}
	public int getDefendent_ID_number() {
		return Defendent_ID_number;
	}
	public void setDefendent_ID_number(int defendent_ID_number) {
		Defendent_ID_number = defendent_ID_number;
	}
	public int getCase_ID() {
		return Case_ID;
	}
	public void setCase_ID(int case_ID) {
		Case_ID = case_ID;
	}
	public int getLawyer_ID() {
		return Lawyer_ID;
	}
	public void setLawyer_ID(int lawyer_ID) {
		Lawyer_ID = lawyer_ID;
	}

}

package application;

public class Notification {
	private int Notification_id;
	private String Notification_date;
	private String Notification_method;
	private int Case_id;
	
	public Notification(){
		super();
	}

	public Notification(int notification_id, String notification_date, String notification_method, int case_id) {
		super();
		Notification_id = notification_id;
		Notification_date = notification_date;
		Notification_method = notification_method;
		Case_id = case_id;
	}

	
	public int getNotification_id() {
		return Notification_id;
	}
	public void setNotification_id(int notification_id) {
		Notification_id = notification_id;
	}

	
	public String getNotification_date() {
		return Notification_date;
	}
	public void setNotification_date(String notification_date) {
		Notification_date = notification_date;
	}

	
	public String getNotification_method() {
		return Notification_method;
	}
	public void setNotification_method(String notification_method) {
		Notification_method = notification_method;
	}

	
	public int getCase_id() {
		return Case_id;
	}
	public void setCase_id(int case_id) {
		Case_id = case_id;
	}
}

package entity;

import java.util.Date;

public class Consume {
	String ID;
	//Date date;
	String date;
	int amt;
	
	public void setID(String ID) { this.ID = ID; }
	public void setDate(String date) { this.date = date; }
	public void setAmt(int amt) { this.amt = amt; }

	public String getID() { return ID; }
	public String getDate()  { return date; }
	public int getAmt() { return amt; }

}

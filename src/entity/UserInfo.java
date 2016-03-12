package entity;

import java.util.ArrayList;

public class UserInfo {
	String ID;
	String password;
	String previlege; //新加的，之前怎么忘了 = =
	String birthdate;
	ArrayList<Consume> rcd;
	int amt = 1000;
	int level;
	int points;
	float discount;
	public UserInfo() { amt = 1000; }
	public void setID(String ID) { this.ID = ID; }
	public void setPassword(String password) { this.password = password; }
	public void setPrevilege(String previlege) { this.previlege = previlege; }
	public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
	public void setAmt(int amt) { this.amt = amt; }
	public void setLevel(int level) { this.level = level; }
	public void setPoints(int points) { this.points = points; }
	public void setDiscount(float discount) { this.discount = discount; }
	public void setRcd(ArrayList<Consume> rcd) { this.rcd = rcd; }
	public String getID() { return ID; }
	public String getPassword() { return password; }
	public String getBirthdate() { return birthdate; }
	public int getAmt() { return amt; }
	public int getLevel() { return level; }
	public int getPoints() { return points; }
	public float getDiscount() { return discount; }
	
	public void printUserInfo() {
		System.out.println(ID + " " + password + " " + birthdate);
		System.out.println(amt + " " + level + " " + points + " " + discount);
	}
}

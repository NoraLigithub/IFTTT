package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class Task extends TimerTask implements Trigger{
	int Number;
	String ID;
	boolean isOn=false;
	int thisKind; //1.定时 2.QQmail 3.微博
	String strTime; //1.发送微博 2.QQmail
	Date time;
	String rcvMail;
	String ltnWeibo;
	String ltnCxt;
	String thisPwd;
	
	int thatKind;
	String sndWeibo;
	String sndMail;
	String sndCxt;
	String thatPwd;
	
	boolean last_state = false;
	int last_rcv = -1; //init
	int last_amt = -1; //init
	
	public void setNumber(int Number) { this.Number = Number; }
	public void setID(String ID) { this.ID = ID; }
	public void setIsOn(boolean isOn) { this.isOn = isOn; }
	public void setThisKind(int thisKind) { this.thisKind = thisKind; }
	public void setStrTime(String time) { this.strTime = time; }
	public void setTime(String time) {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.time = ft.parse(time); //注意用 this 
			System.out.println(time);
			System.out.println(this.time);
		} catch (ParseException e) {
			System.out.println("Unparseable using "+time);
		}
	}
	public void setRcvMail(String rcvMail) { this.rcvMail = rcvMail; }
	public void setLtnWeibo(String ltnWeibo) { this.ltnWeibo = ltnWeibo; }
	public void setLtnCxt(String ltnCxt) { this.ltnCxt = ltnCxt; }
	public void setThisPwd(String thisPwd) { this.thisPwd = thisPwd; }
	
	public void setThatKind(int thatKind) { this.thatKind = thatKind; }
	public void setSndWeibo(String sndWeibo) { this.sndWeibo = sndWeibo; }
	public void setSndMail(String sndMail) { this.sndMail = sndMail; }
	public void setSndCxt(String sndCxt) { this.sndCxt = sndCxt; }
	public void setThatPwd(String thatPwd) { this.thatPwd = thatPwd; }
	
	public boolean getIsOn() { return isOn; }
	public int getNumber() { return Number; }
	public int getThisKind(){return thisKind;}
	public int getThatKind(){return thatKind;}	
	@Override
	public synchronized boolean THIS()  {
		// TODO Auto-generated method stub
		//return false;
		if(thisKind == 1) { //定时
			Date cur = new Date();
			String cur_time_str = cur.toString();
			if(time == null) setTime(strTime);
			System.out.println(cur_time_str);
			if(cur_time_str.equals(time.toString())) return true;
			else return false;
		}
		else if(thisKind == 2) { //QQ mail
			Mail mail = new Mail(rcvMail, thisPwd);
			
			try {
				int new_rcv = mail.receive();
				System.out.println("last rcv --------------------------"+last_rcv+" "+ new_rcv);
				if(last_rcv == -1) {
					last_rcv = new_rcv;
					return false;
				}
				else if(last_rcv == new_rcv) return false;
				else {
					last_rcv = new_rcv;
					return true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(thisKind == 3) { //微博
			int new_amt = GetUserLatestWeibo.amt(ltnWeibo);
			System.out.println(ltnWeibo+last_amt+" "+new_amt);
			if(last_amt == -1) {
				last_amt = new_amt; return false;
			}
			else if(last_amt == new_amt) return false;
			else {
				last_amt = new_amt;
				System.out.println("-----------------THIS OK-------------------------"+ltnCxt.equals(GetUserLatestWeibo.action(ltnWeibo)));
				//if(ltnCxt.equals(GetUserLatestWeibo.action(ltnWeibo)))
				return true;
				//else return false;
				
			}
		}
		
		return false;
	}
	@Override
	public void THAT() {
		// TODO Auto-generated method stub
		/* test */
		/* thatKind = 1 || thatKind = 2 */
		System.out.println("-----------------into THAT-------------------------");
		if(thatKind == 1) {
			new SendWeibo(sndCxt);
		}
		else if(thatKind == 2){
			Mail mail = new Mail("648701545@qq.com", "2220415zfjlha", sndMail, sndCxt); //小心
			try {
				mail.SendMail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println(THIS());
		if(isOn && THIS()){
		//System.out.println(isOn);
		//if(isOn)
			System.out.println("-----------------into Run-------------------------");
			THAT();
			//System.out.println(Number);
		}
	}
	public String taskInfo() {
		String thisstr = "if ";
		if(thisKind == 1) {
			thisstr += "time is ";
			thisstr += strTime;
		}
		else if(thisKind == 2) {
			thisstr += rcvMail;
			thisstr += " receive mail";
		}
		else if(thisKind == 3) {
			thisstr += "Weibo ";
			thisstr += ltnWeibo;
			thisstr += " publish ";
			thisstr += ltnCxt;
		}
		String thatstr = ", ";
		if(thatKind == 1) {
			thatstr += "Weibo ";
			thatstr += sndWeibo;
			thatstr += " send ";
			thatstr += sndCxt;
		}
		else if(thatKind == 2) {
			thatstr += "send to ";
			thatstr += sndMail;
			thatstr += " context ";
			thatstr += sndCxt;
		}
		return thisstr + thatstr;
	}
	public String getTime(){return strTime;}
	public String getrcvMail(){return rcvMail;}
	public String getltnWeibo(){return ltnWeibo;}
	public String getthisPwd(){return thisPwd;}
	public String getltnCxt(){return ltnCxt;}
	public String getsndWeibo(){return sndWeibo;}
	public String getsndCxt(){return sndCxt;}
	public String getsndMail(){return sndMail;}
	public String getthatPwd(){return thatPwd;}
}

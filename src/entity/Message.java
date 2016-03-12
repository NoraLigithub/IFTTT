package entity;

public class Message {
	int idx;
	boolean isPublic;
	boolean isBulletin;
	String ID;
	String ctx;
	public void setIdx(int idx) { this.idx = idx; }
	public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }
	public void setIsBulletin(boolean isBulletin) { this.isBulletin = isBulletin; }
	public void setID(String ID) { this.ID = ID; }
	public void setCtx(String cxt) { this.ctx = cxt; }
	public String getID(){
		if(isPublic&isBulletin)
		{
			return "public";
		}
		else return ID;
	}
	public String getCtx(){return ctx;}
	public int getIdx(){return idx;}
	public void printMsg() {
		System.out.println(idx + " " + isPublic + " " + isBulletin);
		System.out.println(ID + " " + ctx);
	}
}

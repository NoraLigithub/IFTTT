package entity;

import java.util.List;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

public class GetUserLatestWeibo {

	//public static void main(String[] args) {
	public static String action(String name) {
		//String access_token = args[0];
		String access_token = "2.00pOnRFGNqOXMD4d2a126c4brGlkqC";
		//String id = args[1];
		//String id = "5574919523";
		Timeline tm = new Timeline(access_token);
		try {
			StatusWapper sw = tm.getUserTimelineByName(name);
			List<Status> ls = sw.getStatuses();
			Status first = ls.get(0);
			System.out.println(first.getText());
			return first.getText();
			
		} catch (WeiboException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int amt(String name) {
		String access_token = "2.00pOnRFGNqOXMD4d2a126c4brGlkqC";
		Timeline tm = new Timeline(access_token);
		try {
			StatusWapper sw = tm.getUserTimelineByName(name);
			List<Status> ls = sw.getStatuses();
			return ls.size();
			
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return 0;
	}

}


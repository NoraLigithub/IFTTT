package entity;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

//package weibo4j.examples;

public class SendWeibo {
	
	public SendWeibo(String s) {
		
		String access_token = "2.00pOnRFGNqOXMD4d2a126c4brGlkqC";
		String statuses = s;
		Timeline tm = new Timeline(access_token);
		try {
			Status status = tm.updateStatus(statuses);
			Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}	
}

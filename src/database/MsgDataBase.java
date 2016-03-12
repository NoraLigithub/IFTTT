package database;

import java.sql.*;
import java.util.ArrayList;
import entity.Message;

public class MsgDataBase {
	
	static int curMsgAmt = 0;
	static ArrayList<Message> public_msgs;
	static ArrayList<Message> private_msgs;
	
	public static Connection getConnection() throws SQLException, 
 	java.lang.ClassNotFoundException 
 	{
		//绗竴姝ワ細鍔犺浇MySQL鐨凧DBC鐨勯┍鍔�
 		Class.forName("com.mysql.jdbc.Driver");

 		//鍙栧緱杩炴帴鐨剈rl,鑳借闂甅ySQL鏁版嵁搴撶殑鐢ㄦ埛鍚�,瀵嗙爜锛沯avaweb锛氭暟鎹簱鍚�
 		String url = "jdbc:mysql://localhost:3306/javaweb";        
 		String username = "root";
 		String password = "141102linan";

 		//绗簩姝ワ細鍒涘缓涓嶮ySQL鏁版嵁搴撶殑杩炴帴绫荤殑瀹炰緥
 		Connection con = DriverManager.getConnection(url, username, password);        
 		return con;        
	}
	
	//public static boolean isSignUp(String id, String password, String previlege) {
	public static boolean addMsg(boolean isPublic, boolean isBulletin, String ID, String cxt) {
		try
        {
			System.out.println("进入add message");
			//绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            //Statement sql_statement = con.createStatement();
            //String query = "select * from UserInfo";            
            //ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            //boolean isInsert = true;
            /*
            while (result.next()) 
            {
            	String cur_id = result.getString("ID");
            	if(cur_id.equals(id)) {
            		isInsert = false; break;
            	}              
            }
            if(isInsert) {
            */
            	/* 鍔ㄦ�� sql */
            
            int cur_num = 0;
            Statement sql_statement = con.createStatement();
            String query = "select MAX(idx) AS max_num from Message";            
            ResultSet result = sql_statement.executeQuery(query);
            if(result.next()) cur_num = result.getInt("max_num");
            cur_num++;
            System.out.println(cur_num);
            sql_statement.close();
            
            query = " insert into Message (idx, isPublic, isBulletin, toWhichID, context)"
            	        + " values (?, ?, ?, ?, ?)";
            	 
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            //curMsgAmt++;
            //preparedStmt.setInt(1, curMsgAmt); //key
            preparedStmt.setInt(1, cur_num);
            preparedStmt.setBoolean(2, isPublic);
            preparedStmt.setBoolean(3, isBulletin);
            preparedStmt.setString (4, ID);
            preparedStmt.setString(5, cxt);
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            	      
            //sql_statement.close();
            con.close();
           System.out.println("in addmessage ud is"+ID);
           System.out.println("send out! end");
            return true;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵�瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒�
            System.err.print("ClassNotFoundException");
            //鍏朵粬閿欒
            System.err.println(e.getMessage());
            return false;
        } catch (SQLException ex) {
            //鏄剧ず鏁版嵁搴撹繛鎺ラ敊璇垨鏌ヨ閿欒
            System.err.println("SQLException: " + ex.getMessage());
            return false;
        }

	}
	
	public static ArrayList<Message> getAllMsgs() {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from Message";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            //boolean isSucc = false;
            ArrayList<Message> msgs = new ArrayList<Message>(); //new 閲嶈鐨勪簨鎯� - -
            
            while (result.next()) 
            {
            	Message cur = new Message();
                cur.setCtx(result.getString("context"));
            	cur.setID(result.getString("toWhichID"));
            	cur.setIsBulletin(result.getBoolean("isBulletin"));
            	cur.setIsPublic(result.getBoolean("isPublic"));
            	cur.setIdx(result.getInt("idx"));
            	msgs.add(cur);
            	 
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄�     
            sql_statement.close();
            con.close();
            
            return msgs;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵�瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒�
            System.err.print("ClassNotFoundException");
            //鍏朵粬閿欒
            System.err.println(e.getMessage());
            return null;
        } catch (SQLException ex) {
            //鏄剧ず鏁版嵁搴撹繛鎺ラ敊璇垨鏌ヨ閿欒
            System.err.println("SQLException: " + ex.getMessage());
            return null;
        }

	}
	
	public static ArrayList<Message> getMsg(boolean isPublic, boolean isBulletin, String ID) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            
			Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from Message";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            //boolean isSucc = false;
            ArrayList<Message> msgs = new ArrayList<Message>();
            
            while (result.next()) 
            {
            	Message cur = new Message();
            	if(result.getBoolean("isPublic") == isPublic) {
            		if(isPublic || (!isPublic && ID.equals(result.getString("toWhichID")))) { //鍐欏鍚嶅瓧鍟婏紝涓嶆槸ID鏄痶oWhichID锛屽ソ鍍忕┖涓蹭篃涓嶅彲浠ワ紵
            			System.out.println(isPublic + " " + ID + " " + result.getString("toWhichID"));
            			cur.setCtx(result.getString("context"));
            			cur.setID(result.getString("toWhichID"));
            			cur.setIsBulletin(result.getBoolean("isBulletin"));
            			cur.setIsPublic(result.getBoolean("isPublic"));
            			cur.setIdx(result.getInt("idx"));
            			msgs.add(cur);
            		}
            	}
            	 
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄�     
            sql_statement.close();
            con.close();
            System.out.println("send message!");
            return msgs;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵�瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒�
            System.err.print("ClassNotFoundException");
            //鍏朵粬閿欒
            System.err.println(e.getMessage());
            return null;
        } catch (SQLException ex) {
            //鏄剧ず鏁版嵁搴撹繛鎺ラ敊璇垨鏌ヨ閿欒
            System.err.println("SQLException: " + ex.getMessage());
            return null;
        }

	}
	
	public static void delMsg(int idx) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
         
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            String query = "delete from Message where idx = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, idx);
            //curMsgAmt--;
         
            	      // execute the preparedstatement
            preparedStmt.execute();
            	      
            //sql_statement.close();
            con.close();
            	      
            //return true;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵�瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒�
            System.err.print("ClassNotFoundException");
            //鍏朵粬閿欒
            System.err.println(e.getMessage());
            //return false;
        } catch (SQLException ex) {
            //鏄剧ず鏁版嵁搴撹繛鎺ラ敊璇垨鏌ヨ閿欒
            System.err.println("SQLException: " + ex.getMessage());
            //return false;
        }

	}
	
	public static void updateMessage(int idx, String ctx) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
           
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
           
            String query = "update Message set context = ?"
            		+ "where idx = ?";
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, ctx);
            preparedStmt.setInt (2, idx);
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            	      
            con.close();
            	                  
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵�瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒�
            System.err.print("ClassNotFoundException");
            //鍏朵粬閿欒
            System.err.println(e.getMessage());
            
        } catch (SQLException ex) {
            //鏄剧ず鏁版嵁搴撹繛鎺ラ敊璇垨鏌ヨ閿欒
            System.err.println("SQLException: " + ex.getMessage());
            
        }

	}

}

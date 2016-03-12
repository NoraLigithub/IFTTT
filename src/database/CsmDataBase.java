package database;

import java.sql.*;
import java.util.*;
import java.util.Date;
import entity.Consume;

public class CsmDataBase {
	
	ArrayList<Consume> csm_rcds;
	
	public static Connection getConnection() throws SQLException, 
 	java.lang.ClassNotFoundException 
 	{
		//绗竴姝ワ細鍔犺浇MySQL鐨凧DBC鐨勯┍鍔?
 		Class.forName("com.mysql.jdbc.Driver");

 		//鍙栧緱杩炴帴鐨剈rl,鑳借闂甅ySQL鏁版嵁搴撶殑鐢ㄦ埛鍚?瀵嗙爜锛沯avaweb锛氭暟鎹簱鍚?
 		
 		String url = "jdbc:mysql://localhost:3306/javaweb";       //set correctly 
 		String username = "root";
 		String password = "141102linan"; //set correctly

 		//绗簩姝ワ細鍒涘缓涓嶮ySQL鏁版嵁搴撶殑杩炴帴绫荤殑瀹炰緥
 		Connection con = DriverManager.getConnection(url, username, password);        
 		return con;        
	}
	
	public static boolean addCsm(String ID, int amt) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚?sql_statement
            Connection con = getConnection();            
            //Statement sql_statement = con.createStatement();
            //String query = "select * from UserInfo";            
            //ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            String query = " insert into Consume (ID, date, Amount)"
            	        + " values (?, ?, ?)";
            	 
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            //curMsgAmt++;
            //preparedStmt.setInt(1, curMsgAmt); //key
            preparedStmt.setString(1, ID);
            preparedStmt.setString(2, new Date().toString());
            preparedStmt.setInt(3, amt);
            	      // execute the preparedstatement
            preparedStmt.execute();
            	      
            //sql_statement.close();
            con.close();
           System.out.println("add结束！"); 	      
            return true;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵€瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒?
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
	
	public static ArrayList<Consume> getAllCsms() { //绠＄悊鍛?
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚?sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from Consume";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            //boolean isSucc = false;
            ArrayList<Consume> csms = new ArrayList<Consume>(); //new 閲嶈鐨勪簨鎯?- -
            
            while (result.next()) 
            {
            	Consume cur = new Consume();
            	cur.setAmt(result.getInt("Amount"));
            	cur.setDate(result.getString("date"));
            	cur.setID(result.getString("ID"));
                csms.add(cur);
            	//msgs.add(cur);
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄?    
            sql_statement.close();
            con.close();
            
            return csms;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵€瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒?
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
	
	public static ArrayList<Consume> getCsm(String ID) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚?sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from Consume";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            //boolean isSucc = false;
            ArrayList<Consume> csms = new ArrayList<Consume>(); //new 閲嶈鐨勪簨鎯?- -
            
            while (result.next()) 
            {
            	Consume cur = new Consume();
            	if(ID.equals(result.getString("ID"))) { 
            		cur.setAmt(result.getInt("Amount"));
                	cur.setDate(result.getString("date"));
                	cur.setID(result.getString("ID"));
                    csms.add(cur);
            	}
            	 
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄?    
            sql_statement.close();
            con.close();
            
            return csms;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵€瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒?
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

}

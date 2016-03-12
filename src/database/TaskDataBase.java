package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;

import entity.Task;

public class TaskDataBase {
	
	static int curTaskAmt = 0;
	static ArrayList<Task> user_tasks = null; 
	static Timer timer = null;
	
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
	
	public static Task createTask(String ID, int thisKind, String time, String rcvMail, 
			String ltnWeibo, String ltnCxt, String thisPwd, int thatKind, String sndWeibo, 
			String sndMail, String sndCxt, String thatPwd) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();
            
            int cur_num = 0;
            Statement sql_statement = con.createStatement();
            String query = "select MAX(Number) AS max_num from Task";            
            ResultSet result = sql_statement.executeQuery(query);
            if(result.next()) cur_num = result.getInt("max_num");
            cur_num++;
            //System.out.println(cur_num);
            sql_statement.close();
            
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            	/* 鍔ㄦ�� sql */
            query = " insert into Task (Number, ID, isOn, thisKind, time, rcvMail, "
            		+ "ltnWeibo, ltnCxt, thisPwd, thatKind, sndWeibo, sndMail, sndCxt, thatPwd)"
            	        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            	 
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            //curTaskAmt++;
            //preparedStmt.setInt (1, curTaskAmt);
            preparedStmt.setInt (1, cur_num);
            preparedStmt.setString (2, ID);
            preparedStmt.setBoolean(3, false);
            preparedStmt.setInt (4, thisKind);
            preparedStmt.setString(5, time); //Date 绫诲瀷寰呭鐞�
            preparedStmt.setString(6, rcvMail);
            preparedStmt.setString(7, ltnWeibo);
            preparedStmt.setString(8, ltnCxt);
            preparedStmt.setString(9, thisPwd);
            preparedStmt.setInt(10, thatKind);
            preparedStmt.setString(11, sndWeibo);
            preparedStmt.setString(12, sndMail);
            preparedStmt.setString(13, sndCxt);
            preparedStmt.setString(14, thatPwd);
            
            Task task = new Task();
            task.setNumber(cur_num); //灏忓績鍟婏紝淇濇寔涓�鑷达紝鍐檆ur_num
            task.setIsOn(false);
            task.setID(ID);
            task.setLtnCxt(ltnCxt);
            task.setLtnWeibo(ltnWeibo);
            task.setRcvMail(rcvMail);
            task.setSndCxt(sndCxt);
            task.setSndMail(sndMail);
            task.setSndWeibo(sndWeibo);
            task.setStrTime(time);
            task.setThatKind(thatKind);
            task.setThatPwd(thatPwd);
            task.setThisKind(thisKind);
            task.setThisPwd(thisPwd);
            
            if(time != null) {
            	task.setTime(time);
            }
            	 
            // execute the preparedstatement
            preparedStmt.execute();
            	      
            con.close();
            
            if(user_tasks == null)
            	user_tasks = new ArrayList<Task>(); // the first time -- to avoid null pointer
            
            if(timer == null)
            	timer = new Timer(true); //daemon
      
            user_tasks.add(task);
            //System.out.println("enter");
            timer.schedule(task, 0, 100);	      
            return task;
       }
            catch(java.lang.ClassNotFoundException e) {
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
	
	public static ArrayList<Task> getAllTasks() {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from Task";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            ArrayList<Task> tasks = new ArrayList<Task>();
            
            while (result.next()) 
            {
            	Task cur = new Task();
            		cur.setID(result.getString("ID"));
            		cur.setLtnCxt(result.getString("ltnCxt"));
            		cur.setLtnWeibo(result.getString("ltnWeibo"));
            		cur.setNumber(result.getInt("Number"));
            		cur.setRcvMail(result.getString("rcvMail"));
            		cur.setSndCxt(result.getString("sndCxt"));
            		cur.setSndMail(result.getString("sndMail"));
            		cur.setSndWeibo(result.getString("sndWeibo"));
            		cur.setStrTime(result.getString("time"));
            		cur.setThatKind(result.getInt("thatKind"));
            		cur.setThatPwd(result.getString("thatPwd"));
            		cur.setThisKind(result.getInt("thisKind"));
            		cur.setThisPwd(result.getString("thisPwd"));
            		cur.setIsOn(result.getBoolean("isOn"));
            		tasks.add(cur);
            		//System.out.println("enter");
            	 
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄�     
            sql_statement.close();
            con.close();
            
            return tasks;
            
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
	
	public static ArrayList<Task> getUserTasks(String ID) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from Task";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            ArrayList<Task> tasks = new ArrayList<Task>();
            if(user_tasks == null) user_tasks = new ArrayList<Task>();
            
            while (result.next()) 
            {
            	Task cur = new Task();
            	if(ID.equals(result.getString("ID"))) {
            		cur.setID(ID);
            		cur.setLtnCxt(result.getString("ltnCxt"));
            		cur.setLtnWeibo(result.getString("ltnWeibo"));
            		cur.setNumber(result.getInt("Number"));
            		cur.setRcvMail(result.getString("rcvMail"));
            		cur.setSndCxt(result.getString("sndCxt"));
            		cur.setSndMail(result.getString("sndMail"));
            		cur.setSndWeibo(result.getString("sndWeibo"));
            		cur.setStrTime(result.getString("time"));
            		cur.setThatKind(result.getInt("thatKind"));
            		cur.setThatPwd(result.getString("thatPwd"));
            		cur.setThisKind(result.getInt("thisKind"));
            		cur.setThisPwd(result.getString("thisPwd"));
            		cur.setIsOn(result.getBoolean("isOn"));
            		cur.setIsOn(false);
            		tasks.add(cur);
            		user_tasks.add(cur);
            		//System.out.println("enter");
            	}
            	 
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄�     
            sql_statement.close();
            con.close();
            
            return tasks;
            
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
	
	public static void delTask(int Number) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
         
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            String query = "delete from Task where Number = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, Number);
            //curTaskAmt--; 杩樻槸涓嶅噺浜嗭紝濮嬬粓澧炲姞锛屽厑璁哥暀鏈夌┖缂�
         
            	      // execute the preparedstatement
            preparedStmt.execute();
            //System.out.println("enter");
            
            int len = user_tasks.size();
            for(int i = 0; i < len; i++) {
            	Task cur = user_tasks.get(i);
            	if(cur.getNumber() == Number) {
            		cur.cancel(); //stop
            		timer.purge();
            		user_tasks.remove(i); break;
            	}
            }
            	      
            con.close();
            	                  
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
	
	//public static void updateMessage(int idx, String ctx) {
	public static void updateTask(int Number, String ID, int thisKind, String time, String rcvMail, 
			String ltnWeibo, String ltnCxt, String thisPwd, int thatKind, String sndWeibo, 
			String sndMail, String sndCxt, String thatPwd) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
           
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            TaskDataBase.setTaskOff(Number); //淇敼鍓嶆殏鍋滀换鍔�
           
            //String query = "update Message set context = ?"
            		//+ "where idx = ?";
            String query = "update Task set ID = ?, thisKind = ?, time = ?, rcvMail = ?, "
            		+ "ltnWeibo = ?, ltnCxt = ?, thisPwd = ?, thatKind = ?, sndWeibo = ?, "
            		+ "sndMail = ?, sndCxt = ?, thatPwd = ? "
            		+ "where Number = ?";
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, ID);
            preparedStmt.setInt (2, thisKind);
            preparedStmt.setString(3, time);
            preparedStmt.setString(4, rcvMail);
            preparedStmt.setString(5, ltnWeibo);
            preparedStmt.setString(6, ltnCxt);
            preparedStmt.setString(7, thisPwd);
            preparedStmt.setInt(8, thatKind);
            preparedStmt.setString(9, sndWeibo);
            preparedStmt.setString(10, sndMail);
            preparedStmt.setString(11, sndCxt);
            preparedStmt.setString(12, thatPwd);
            preparedStmt.setInt(13, Number);
            //System.out.println("enter");
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            
            int len = user_tasks.size();
            for(int i = 0; i < len; i++) {
            	Task cur = user_tasks.get(i);
            	if(cur.getNumber() == Number) {
            		cur.setID(ID);
            		//cur.setIsOn(isOn); 淇敼灏辫涓烘槸淇敼璧勬枡鍚�
            		cur.setLtnCxt(ltnCxt);
            		cur.setLtnWeibo(ltnWeibo);
            		cur.setRcvMail(rcvMail);
            		cur.setSndCxt(sndCxt);
            		cur.setSndMail(sndMail);
            		cur.setSndWeibo(sndWeibo);
            		cur.setStrTime(time);
            		cur.setThatKind(thatKind);
            		cur.setThatPwd(thatPwd);
            		cur.setThisKind(thisKind);
            		cur.setThisPwd(thisPwd);
            		if(time != null)
            			cur.setTime(time);
            	}
            }
            
            	      
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
	
	public static void setTaskOn(int Number) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();
            Statement sql_statement = con.createStatement();
            String query = "select * from Task";            
            ResultSet result = sql_statement.executeQuery(query);
            String ID = null;
            
            while(result.next()) {
            	if(Number == result.getInt("Number")) {
            		ID = result.getString("ID");
            		break;
            	}
            }
            sql_statement.close();
            
            int amt = 0;
            sql_statement = con.createStatement();
            query = "select * from UserInfo";
            result = sql_statement.executeQuery(query);
            
            //System.out.println(ID);
            while(result.next()) {
            	if(ID.equals(result.getString("ID"))) {
            		amt = result.getInt("AMOUNT");
            		break;
            	}
            }
            sql_statement.close();
           
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            query = "update Task set isOn = true "
            		+ "where Number = ?";
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            //preparedStmt.setInt(1, amt - 10);
            preparedStmt.setInt(1, Number);
            //System.out.println("enter");
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            
            query = "update UserInfo set AMOUNT = ? "
            		+ "where ID = ?";
            	      // create the mysql insert preparedstatement
            preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, amt - 10);
            preparedStmt.setString(2, ID);
            //System.out.println("enter");
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            System.out.println("add之前");
            CsmDataBase.addCsm(ID, 10);
            
            int len = user_tasks.size();
            //System.out.println(len);
            System.out.println("start task number is "+Number);
            for(int i = 0; i < len; i++) {
            	Task cur = user_tasks.get(i);
            	System.out.println(cur.getNumber());
            	if(cur.getNumber() == Number) {
            		cur.setIsOn(true);
            		System.out.println("here3");
            		break;
            	}
            }
            	      
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
	
	public static void setTaskOff(int Number) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
           
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            String query = "update Task set isOn = false "
            		+ "where Number = ?";
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Number);
            //System.out.println("enter");
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            
            int len = user_tasks.size();
            System.out.println("stop task number is "+Number);
            for(int i = 0; i < len; i++) {
            	Task cur = user_tasks.get(i);
            	if(cur.getNumber() == Number) {
            		cur.setIsOn(false);
            		break;
            	}
            }
            	      
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

	public static Task selTask(int Number) {
			try
	        {
	            //第三步：获取连接类实例con，用con创建Statement对象类实例 sql_statement
	            Connection con = getConnection();            
	            Statement sql_statement = con.createStatement();
	            String query = "select * from Task";            
	            ResultSet result = sql_statement.executeQuery(query);
	            /************ 对数据库进行相关操作 ************/
	            
	            //ArrayList<Task> tasks = new ArrayList<Task>();
	            //if(user_tasks == null) user_tasks = new ArrayList<Task>();
	            
	            while (result.next()) 
	            {
	            	Task cur = new Task();
	            	//if(ID.equals(result.getString("ID"))) {
	            	if(Number == result.getInt("Number")) {
	            		cur.setID(result.getString("ID"));
	            		cur.setLtnCxt(result.getString("ltnCxt"));
	            		cur.setLtnWeibo(result.getString("ltnWeibo"));
	            		cur.setNumber(result.getInt("Number"));
	            		cur.setRcvMail(result.getString("rcvMail"));
	            		cur.setSndCxt(result.getString("sndCxt"));
	            		cur.setSndMail(result.getString("sndMail"));
	            		cur.setSndWeibo(result.getString("sndWeibo"));
	            		cur.setStrTime(result.getString("time"));
	            		cur.setThatKind(result.getInt("thatKind"));
	            		cur.setThatPwd(result.getString("thatPwd"));
	            		cur.setThisKind(result.getInt("thisKind"));
	            		cur.setThisPwd(result.getString("thisPwd"));
	            		cur.setIsOn(result.getBoolean("isOn"));
	            		sql_statement.close();
	                    con.close();
	            		return cur;
	            		//tasks.add(cur);
	            		//user_tasks.add(cur);
	            		//System.out.println("enter");
	            	}
	            	 
	            }
	            
	            //关闭连接和声明     
	            sql_statement.close();
	            con.close();
	            
	            return null;
	            
	        } catch(java.lang.ClassNotFoundException e) {
	            //加载JDBC错误,所要用的驱动没有找到
	            System.err.print("ClassNotFoundException");
	            //其他错误
	            System.err.println(e.getMessage());
	            return null;
	        } catch (SQLException ex) {
	            //显示数据库连接错误或查询错误
	            System.err.println("SQLException: " + ex.getMessage());
	            return null;
	        }
		}
}

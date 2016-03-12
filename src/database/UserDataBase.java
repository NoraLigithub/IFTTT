package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Timer;

import entity.Consume;
import entity.Task;
import entity.UserInfo;

public class UserDataBase {
	
	static UserInfo cur_user;
	
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
	
	public static boolean isSignUp(String id, String password, String previlege) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from UserInfo";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            boolean isInsert = true;
            
            while (result.next()) 
            {
            	String cur_id = result.getString("ID");
            	if(cur_id.equals(id)) {
            		isInsert = false; break;
            	}              
            }
            if(isInsert) {
            	/* 鍔ㄦ�� sql */
            	query = " insert into UserInfo (ID, PASSWORD, PREVILEGE, AMOUNT)"
            	        + " values (?, ?, ?, ?)";
            	 
            	      // create the mysql insert preparedstatement
            		  PreparedStatement preparedStmt = con.prepareStatement(query);
            	      preparedStmt.setString (1, id);
            	      preparedStmt.setString (2, password);
            	      preparedStmt.setString (3, previlege);
            	      preparedStmt.setInt(4, 1000);
            	 
            	      // execute the preparedstatement
            	      preparedStmt.execute();
            	      
            	      sql_statement.close();
                      con.close();
            	      
            	      return true;
            }
            else {
                //鍏抽棴杩炴帴鍜屽０鏄�
            	sql_statement.close();
                con.close();
            	return false;
            }
            
            
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
	
	public static boolean updateProfile(String id,  String birthdate) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            //String query = "select * from UserInfo";            
            //ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            //boolean isInsert = true;
            
            	/* 鍔ㄦ�� sql */
            	//query = " insert into UserInfo (ID, PASSWORD, PREVILEGE)"
            	        //+ " values (?, ?, ?)";
            String query = "update UserInfo set BIRTHDATE = ?"
            		+ "where ID = ?";
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
           // preparedStmt.setString (1, password);
            preparedStmt.setString (1, birthdate);
            preparedStmt.setString (2, id);
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            	      
            sql_statement.close();
            con.close();
            	      
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
	
	public static boolean isLoginIn(String id, String password, String previlege) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from UserInfo";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            boolean isSucc = false;
            
            while (result.next()) 
            {
            	String cur_id = result.getString("ID");
            	if(cur_id.equals(id)) {
            		String cur_pwd = result.getString("PASSWORD");
            		String cur_pvg = result.getString("PREVILEGE");
            		if(cur_pwd.equals(password) && cur_pvg.equals(previlege)) {
            			if(TaskDataBase.user_tasks != null) {
            				
            				int len = TaskDataBase.user_tasks.size();
            				for(int i = 0; i < len; i++) {
            					TaskDataBase.user_tasks.get(i).setIsOn(false);
            				}
            			}
            			
            			TaskDataBase.user_tasks = null;
            			cur_user = new UserInfo(); //new new new
            			cur_user.setID(id);
            			cur_user.setPassword(cur_pwd);
            			cur_user.setPrevilege(cur_pvg);
            			
            			TaskDataBase.getUserTasks(id); //鑾峰彇浠ュ墠鐧婚檰淇濆瓨鐨勪换鍔�
            			int len = TaskDataBase.user_tasks.size();
            			Timer  timer = TaskDataBase.timer; //杩愯璋冨害鐧婚檰鍓嶄繚瀛樼殑浠诲姟
            			if(timer == null) {
            				TaskDataBase.timer = new Timer(true); //daemon 浼樺厛绾т綆
            				timer = TaskDataBase.timer;
            			}
            			//timer.cancel();
            			for(int i = 0; i < len; i++) {
            				TaskDataBase.user_tasks.get(i).setIsOn(false);
            				Task cur = TaskDataBase.user_tasks.get(i);
            				//cur.cancel();
            				timer.schedule(cur, 0, 100);
            			}
            			
            			return true;
            		}
            	}       
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄�     
            sql_statement.close();
            con.close();
            	      
            return isSucc;
            
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
	
	@SuppressWarnings("null")
	public static UserInfo getUser(String ID) {
        UserInfo user = null; //涓嶈兘鐩存帴鐢紝瑕乶ew!
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from UserInfo";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            //Users users;
            //ArrayList<UserInfo> users;
            user = new UserInfo(); //pay attention!
                        
            while (result.next()) 
            {
            	if(ID.equals(result.getString("ID"))) {
            		System.out.println(result.getString("ID"));
            		user.setID(result.getString("ID"));
                	user.setAmt(result.getInt("AMOUNT"));
                	user.setBirthdate(result.getString("BIRTHDATE"));
                	user.setDiscount(result.getFloat("DISCOUNT"));
                	user.setLevel(result.getInt("LEVEL"));
                	user.setPassword(result.getString("PASSWORD"));
                	user.setPoints(result.getInt("POINTS"));
                	break;
            	}
            }
            
            //鍏抽棴杩炴帴鍜屽０鏄�     
            sql_statement.close();
            con.close();
            return user;
            	      
            //return isSucc;
            
        } catch(java.lang.ClassNotFoundException e) {
            //鍔犺浇JDBC閿欒,鎵�瑕佺敤鐨勯┍鍔ㄦ病鏈夋壘鍒�
            System.err.print("ClassNotFoundException");
            //鍏朵粬閿欒
            System.err.println(e.getMessage());
            //return false;
            return user;
        } catch (SQLException ex) {
            //鏄剧ず鏁版嵁搴撹繛鎺ラ敊璇垨鏌ヨ閿欒
            System.err.println("SQLException: " + ex.getMessage());
            return user;
        }

	}
	
	public static ArrayList<UserInfo> getAllUsers() {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from UserInfo";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            ArrayList<UserInfo> users = new ArrayList<UserInfo>();
            
            while (result.next()) 
            {
            	UserInfo cur = new UserInfo();
            	cur.setID(result.getString("ID"));
            	cur.setLevel(result.getInt("LEVEL"));
            	cur.setAmt(result.getInt("AMOUNT"));
            	
            	ArrayList<Consume> rcd = new ArrayList<Consume>();
            	
            	query = "select * from Consume";
            	Statement sql_statement2 = con.createStatement();
            	ResultSet result2 = sql_statement2.executeQuery(query);
            	
            	while(result2.next()) {
            		Consume cons = new Consume();
            		if(cur.getID().equals(result2.getString("ID"))) {
            			cons.setAmt(result2.getInt("Amount"));
            			cons.setDate(result2.getString("date"));
            			cons.setID(result2.getString("ID"));
            			rcd.add(cons);
            		}
            	}
            	sql_statement2.close();
            	
            	cur.setRcd(rcd);
            	users.add(cur);
            }
            
            sql_statement.close();
            con.close();
            return users;
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
	
	public static void chargeAmount(String ID, int inc) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();
            Statement sql_statement = con.createStatement();
            String query = "select * from UserInfo";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
           
            int amt = 0;
            
            while (result.next()) 
            {
            	if(ID.equals(result.getString("ID"))) {
            		amt = result.getInt("AMOUNT");
                	break;
            	}
            }
                                    
            query = "update UserInfo set AMOUNT = ? " //涓�寮�濮嬫病鏈夊姞鏈�鍚庣殑绌烘牸锛宒ebug鎯冲摥
            		+ "where ID = ?";
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, amt + inc);
            preparedStmt.setString (2, ID);
            	 
            	      // execute the preparedstatement
            preparedStmt.execute();
            	      
            sql_statement.close();
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
	
	public static ArrayList<Consume> getUserConsume(String ID) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();            
            Statement sql_statement = con.createStatement();
            String query = "select * from Consume";            
            ResultSet result = sql_statement.executeQuery(query);
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
            
            ArrayList<Consume> cons = new ArrayList<Consume>();
            
            while (result.next()) 
            {
            	if(ID.equals(result.getString("ID"))) {
                	Consume cur = new Consume();
                	cur.setAmt(result.getInt("Amount"));
                	cur.setDate(result.getString("date"));
                	cur.setID(ID);
                	cons.add(cur);
                	//System.out.println("enter");
            	}
            	
            }
            
            sql_statement.close();
            con.close();
            return cons;
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
	
	public static void setLevel(String ID, int level) {
		try
        {
            //绗笁姝ワ細鑾峰彇杩炴帴绫诲疄渚媍on锛岀敤con鍒涘缓Statement瀵硅薄绫诲疄渚� sql_statement
            Connection con = getConnection();
            
            /************ 瀵规暟鎹簱杩涜鐩稿叧鎿嶄綔 ************/
                                 
            String query = "update UserInfo set LEVEL = ? " //涓�寮�濮嬫病鏈夊姞鏈�鍚庣殑绌烘牸锛宒ebug鎯冲摥
            		+ "where ID = ?";
            	      // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt (1, level);
            preparedStmt.setString (2, ID);
            	 
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

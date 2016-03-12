package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MsgDataBase;
import database.TaskDataBase;
import database.UserDataBase;
import entity.Message;
import entity.Task;
import entity.UserInfo;

/**
 * Servlet implementation class loginInHttp
 */
@WebServlet("/loginInHttp")
public class loginInHttp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String id;
    public String password;
    public String previlege;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginInHttp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");

	      PrintWriter out = response.getWriter();
		  String title = "Login in Successfully!";
		  id=request.getParameter("id");
		  password=request.getParameter("password");
		  previlege=request.getParameter("Previlege");
		  //String info[]=request.getParameterValues("privelege");
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      
	      boolean isLoginIn = UserDataBase.isLoginIn(id, password, previlege);
	      if(isLoginIn) 
	      {
	    	  float money=UserDataBase.getUser(id).getAmt();
	    	  System.out.println("=================="+money+"=====================");
	    	  request.getSession().setAttribute("id", id);
	    	  request.getSession().setAttribute("Previlege", previlege);
	    	  request.getSession().setAttribute("money", money);
	    	  response.sendRedirect("main.jsp");
	      }
	      else
	      {
	    	  title = "Login in Failure!";
	    	  out.println(
		                "<html>\n<body>\n" +
		               "<head><title>" + title + "</title></head>\n" +
		                "<body bgcolor=\"#f0f0f0\">\n" +
		                "<h1 align=\"center\">" + title + "</h1>\n" +
		                "<p>"+
		                "  <b>id:</b>"
		                + request.getParameter("id") + "\n<br />\n" +
		                "  <b>previlege:</b>"
		                + request.getParameter("Previlege") + "\n<br />\n" 
		                 +
		                "</p>\n" +
		                "<a href=loginIn.html>return to login in</a> "+
		                "</body></html>");
	      }
	      /* Test  ^_^ */
	      //UserDataBase.updateProfile("222", "333", "1995-5-6"); YES!
	      //UserInfo user = UserDataBase.getUser("222");
	      //user.printUserInfo();
	      //MsgDataBase.addMsg(false, true, "222", "test");
	      //MsgDataBase.addMsg(true, false, null, "testpublic");
	      /*
	      ArrayList<Message> msgs = MsgDataBase.getMsg(false, true, "222");
	      System.out.println(msgs.size());
	      for(int i = 0; i < msgs.size(); i++) {
	    	  msgs.get(i).printMsg();
	      }
	      ArrayList<Message> msgs2 = MsgDataBase.getMsg(true, false, null);
	      for(int i = 0; i < msgs2.size(); i++) {
	    	  msgs2.get(i).printMsg();
	      }
	      MsgDataBase.delMsg(2);
	      ArrayList<Message> msgs3 = MsgDataBase.getMsg(true, false, null);
	      for(int i = 0; i < msgs3.size(); i++) {
	    	  msgs3.get(i).printMsg();
	      }
	     
	      MsgDataBase.updateMessage(1, "update");
	      ArrayList<Message> msgs = MsgDataBase.getMsg(false, true, "222");
	      System.out.println(msgs.size());
	      for(int i = 0; i < msgs.size(); i++) {
	    	  msgs.get(i).printMsg();
	      }
	      */
	      //UserDataBase.getAllUsers();
	      //UserDataBase.chargeAmount("222", 1);
	      //UserDataBase.getUserConsume("222");
	      //UserDataBase.setLevel("222", 5);
	      //TaskDataBase.createTask("222", 1, "2015-12-25 21:08:30", null, null, null, null, 2,"瑙ｅ崡闃�",  "648701545@qq.com", "zz", "2220415zfjlha");
	      //TaskDataBase.createTask("222", 1, "2015-12-25 21:09:00", null, null, null, null, 1,"瑙ｅ崡闃�",  "648701545@qq.com", "zz", "2220415zfjlha");
	      //TaskDataBase.createTask("222", 1, null, null, null, null, null, 2, null, null, null, null);
	      //TaskDataBase.getUserTasks("222");
	      //TaskDataBase.delTask(2);
	      //TaskDataBase.updateTask(1, "222", 1, "232", null, null, null, null, 2, null, null, null, null);
	      //TaskDataBase.setTaskOn(1);
	      //TaskDataBase.setTaskOn(2);
	      //System.out.println("here");
	      //TaskDataBase.setTaskOff(1);
	      
	      //Timer timer = new Timer();
	      
	      
	      
	      //while(true) {
	      /*
		      ArrayList<Task> tasks = TaskDataBase.getAllTasks();
		      if(tasks == null) return;
	    	  for(int i = 0; i < tasks.size(); i++) {
		    	  Task cur = tasks.get(i);
		    	  //if(cur.getIsOn()) {
		    		  timer.schedule(cur, 0, 100);
		    		  
		    	  //}
		      }
	      //}
	      */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

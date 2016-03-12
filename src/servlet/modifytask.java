package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class modifytask
 */
@WebServlet("/modifytask")
public class modifytask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifytask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String tasknumber=request.getParameter("tasknumber");
		int number=Integer.parseInt(tasknumber);
		entity.Task t=database.TaskDataBase.selTask(number);
		 PrintWriter out = response.getWriter();
		 out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
				 +"<html><body>");
		 
		 out.println("<title>Insert title here</title></head><body bgcolor=\"#d0d0d0\"><h>Modify Task </h> <br /><strong> IF </strong> <br />");
		 out.println("<form name=\"input\" action=\"moTask\" method=\"post\">");
		 int thisKind=t.getThisKind(),thatKind=t.getThatKind();
		 switch(thisKind){
		 case 1:
		 	out.println("Time is (yyyy-MM-dd HH:mm:ss)<input type=\"text\" name=\"time\" id=\"time\" "
		 			+"value=\""+t.getTime()+"\" onfocus=\"if (value =='"+t.getTime()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getTime()+"'}\" "
		   			+"><br />");
		 	break;
		 case 2:
		 	out.println("receive  an email <br /> address<input type=\"text\" name=\"rcvMail\" id=\"rcvMail\""
		 			+"value=\""+t.getrcvMail()+"\" onfocus=\"if (value =='"+t.getrcvMail()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getrcvMail()+"'}\" "
		 +"><br />\n");
		 	out.println("password <input type=\"password\" name=\"thisPwd\" id=\"thisPwd\""
		 			+"value=\""+t.getthisPwd()+"\" onfocus=\"if (value =='"+t.getthisPwd()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getthisPwd()+"'}\" "
		 +"><br />\n");
		 
		 	break;
		 case 3:
		 	out.println("receive a specific weibo <br /> weibo name <input type=\"text\" name=\"ltnWeibo\" id=\"ltnWeibo\""
		 			+"value=\""+t.getltnWeibo()+"\" onfocus=\"if (value =='"+t.getltnWeibo()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getltnWeibo()+"'}\" "
		 			+"><br />");
		 	System.out.println(t.getltnWeibo());
		 	out.println("password <input type=\"password\" name=\"thisPwd\" id=\"thisPwd\""
		 			+"value=\""+t.getthisPwd()+"\" onfocus=\"if (value =='"+t.getthisPwd()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getthisPwd()+"'}\" "
		 			+"><br />\n");
		 	out.println("weibo text <input type=\"text\" name=\"ltnCxt\" id=\"ltnCxt\""
		 			+"value=\""+t.getltnCxt()+"\" onfocus=\"if (value =='"+t.getltnCxt()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getltnCxt()+"'}\" "
		 			+" ><br />\n");
		 	break;	
		 }
		 out.println("<strong> THEN </strong> <br />");
		 switch(thatKind){
		 case 2:
		 	out.println("send an email <br />receiver address <input type=\"text\" name=\"sndMail\" id=\"sndMail\""
		 			+"value=\""+t.getsndMail()+"\" onfocus=\"if (value =='"+t.getsndMail()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getsndMail()+"'}\" "
		 			+"><br />\n");
		 	out.println("text <input type=\"text\" name=\"sndCxt\" id=\"sndCxt\" "
		 			+"value=\""+t.getsndCxt()+"\" onfocus=\"if (value =='"+t.getsndCxt()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getsndCxt()+"'}\" "
		 			+"><br />\n");
		 	break;
		 case 1:
		 	out.println("send a weibo <br />weibo name <input type=\"text\" name=\"sndWeibo\" id=\"sndWeibo\""
		 			+"value=\""+t.getsndWeibo()+"\" onfocus=\"if (value =='"+t.getsndWeibo()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getsndWeibo()+"'}\" "
		 			+" ><br />");
		 	System.out.println("---------------------------------"+t.getsndWeibo());
		 	out.println("password <input type=\"password\" name=\"thatPwd\" id=\"thatPwd\""
		 			+"value=\""+t.getthatPwd()+"\" onfocus=\"if (value =='"+t.getthatPwd()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getthatPwd()+"'}\" "
		 			+"><br />\n");
		 	out.println("weibo text <input type=\"text\" name=\"sndCxt\" id=\"sndCxt\""
		 			+"value=\""+t.getsndCxt()+"\" onfocus=\"if (value =='"+t.getsndCxt()+"'){value =''}\" onblur=\"if (value ==''){value='"+t.getsndCxt()+"'}\" "
		 			+"><br />\n");
		 	break;		
		 }
		 out.println("<input type=\"hidden\" name=\"tasknumber\" value=\""+number+"\">");
		 out.println("<input type=\"hidden\" name=\"thisKind\" value=\""+thisKind+"\">");
		 out.println("<input type=\"hidden\" name=\"thatKind\" value=\""+thatKind+"\">"); 
		 out.println("<input type=\"submit\" value=\"Submit\" > %>");
		 out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

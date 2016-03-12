package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDataBase;

/**
 * Servlet implementation class signUpHttp
 */
@WebServlet("/signUpHttp")
public class signUpHttp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public String id;
    public String password;
    public String previlege;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUpHttp() {
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
		response.setContentType("text/html");

	      PrintWriter out = response.getWriter();
		  String title = "Sign Up Successfully!";
		  id=request.getParameter("id");
		  password=request.getParameter("password");
		  previlege=request.getParameter("Previlege");
		  
		  boolean isInsert = UserDataBase.isSignUp(id, password, previlege);
		  if(isInsert) response.sendRedirect("loginIn.html");/*title = "Sign up Failure!";*/
		  else 
		  {
			  title = "Sign up Failure!";
			  String docType =
				      "<!doctype html public \"-//w3c//dtd html 4.0 " +
				      "transitional//en\">\n";
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
				                "</p>\n"+
				                 "<a href=signUp.html>return to sign up</a> "+
				                "</body></html>");
		  }
		  //String info[]=request.getParameterValues("privelege");
	     /* String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
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
	                "</body></html>");*/
	      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
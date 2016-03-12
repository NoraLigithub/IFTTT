package servlet;
 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

 public class Hello extends HttpServlet {
 
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	 		throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("Hello");
	}
 } 
       
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.TaskDataBase;

/**
 * Servlet implementation class addTask
 */
@WebServlet("/addTask")
public class addTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addTask() {
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
		String ID;int thisKind=1; String time=null; String rcvMail=null;
		String ltnWeibo=null; String ltnCxt=null; String thisPwd=null; int thatKind=1; String sndWeibo=null; 
		String sndMail=null; String sndCxt=null; String thatPwd=null;
		ID=(String)request.getSession().getAttribute("id");
		String thisType=(String)request.getSession().getAttribute("thisType");
		String thatType=(String)request.getSession().getAttribute("thatType");
		switch(thisType){
		case "time":thisKind=1;time=request.getParameter("time");break;
		case "mail":thisKind=2;rcvMail=request.getParameter("rcvMail");thisPwd=request.getParameter("thisPwd");break;
		case "weibo":thisKind=3;ltnWeibo=request.getParameter("ltnWeibo");ltnCxt=request.getParameter("ltnCxt");thisPwd=request.getParameter("thisPwd");break;
		}
		switch(thatType){
		case "weibo": thatKind=1;sndWeibo=request.getParameter("sndWeibo");sndCxt=request.getParameter("sndCxt");break;
		case "mail": thatKind=2;sndMail=request.getParameter("sndMail"); sndCxt=request.getParameter("sndCxt");break;
		}
		TaskDataBase.createTask(ID, thisKind, time, rcvMail, ltnWeibo, ltnCxt, thisPwd, thatKind, sndWeibo, sndMail, sndCxt, thatPwd);
		response.sendRedirect("main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

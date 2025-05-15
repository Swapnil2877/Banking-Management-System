

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Admin_Loans_Request
 */
public class Admin_Loans_Request extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String id=req.getParameter("id");
		String event=req.getParameter("submit");
		
		if(event.equals("Approve"))
		{
			try
			{
				out.println(db.update("update home_loan_application set status = 'Approved' where id ='"+id+"' "));
				resp.sendRedirect("Admin_Loans_Request.jsp");
			}
			catch(Exception ex)
			{
				out.println(ex.toString());
			}
		}
		 
		
		if(event.equals("Reject"))
		{
			try
			{
				out.println(db.update("update home_loan_application set status = 'Rejected' where id ='"+id+"' "));
				resp.sendRedirect("Admin_Loans_Request.jsp");
			}
			catch(Exception ex)
			{
				out.println(ex.toString());
			}
		}
		
		
	}
}

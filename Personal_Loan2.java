

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.io.*;

/**
 * Servlet implementation class Personal_Loan2
 */
public class Personal_Loan2 extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String Aadhar=req.getParameter("aadhar");
		String Pan_no=req.getParameter("pan_no");
		String passport=req.getParameter("pass_no");
		String Voter_id=req.getParameter("voter_id");
		String event=req.getParameter("Next");
		
		out.println(Aadhar);
		out.println(Pan_no);
		out.println(passport);
		out.println(Voter_id);
		out.println(event);
		
		if(event.equals("Next"))
		{
			if(Aadhar.equals("") || Pan_no.equals("") || passport.equals("") || Voter_id.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Personal_Loan2.jsp' </script>");
			}
			else {
				try {
					String sq1="insert into personal_loan_2 (account_number,Aadhar_number, Pan_no, Voter_id) values('"+session.getAttribute("account_number")+"','"+Aadhar+"', '"+Pan_no+"', '"+Voter_id+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Phase 2'); location='Personal_Loan3.jsp' </script>");
				}
				catch(Exception ex)
				{
					out.println(ex.toString());
					
				}
				
			}
		}

	}
}

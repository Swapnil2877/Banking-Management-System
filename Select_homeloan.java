

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Select_homeloan
 */
public class Select_homeloan extends HttpServlet {
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String principle=req.getParameter("principle");
		String rate=req.getParameter("rate");
		String time=req.getParameter("time");
		String loanamount=req.getParameter("loanamount");
		String event=req.getParameter("submit");
		
		out.println(principle);
		out.println(rate);
		out.println(time);
		out.println(loanamount);
		out.println(event);
		
		if(event.equals("Submit"))
		{
			if(principle.equals("") ||  rate.equals("") ||  loanamount.equals("") ||  time.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Select_homeloan.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into home_loan_amount (account_number,loan_amount, loan_interest, loan_time) values('"+session.getAttribute("account_number")+"','"+loanamount+"', '"+rate+"', '"+time+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Home Loan Applied'); location='Success_Homeloan.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	}

}

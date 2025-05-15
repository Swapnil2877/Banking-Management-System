

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
 * Servlet implementation class Create_Account3
 */
public class Create_Account3 extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String Account_Type=req.getParameter("Account_Type");
		String Mpin=req.getParameter("Mpin");
		String smsalert=req.getParameter("req");
		String email_alert=req.getParameter("req1");
		String internet_banking=req.getParameter("req2");
		String Cheqe_book=req.getParameter("req3");
		String event=req.getParameter("submit");
		
		
		String account = "Account_Type";
		 
		
		out.println(account);
		out.println(Mpin);
		out.println(smsalert);
		out.println(email_alert);
		out.println(internet_banking);
		out.println(Cheqe_book);
		out.println(event);
		
		
		
		if(event.equals("save & continue"))
		{
			if(account.equals("") || Mpin.equals("") || smsalert.equals("") || email_alert.equals("") || internet_banking.equals("") || Cheqe_book.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Create_Account3.jsp' </script>");

			}
			else
			{
				try {
					String sq1="insert into Create_Account3 (account_number,Holder_account_type , Holder_account_mpin , Holder_smsalert , Holder_emailalret ,Holder_internet_banking ,  Holder_cheque_book ) values('"+session.getAttribute("new_ac_no")+"','"+Account_Type+"','"+Mpin+"','"+smsalert+"','"+email_alert+"','"+internet_banking+"','"+Cheqe_book+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Account Setup is Done'); location='SignUpFinal.jsp' </script>");
					
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	
		
		
	}
}

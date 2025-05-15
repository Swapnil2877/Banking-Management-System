

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Personal_Loan
 */
public class Personal_Loan extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		Date date = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("dd/MM//yyyy");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		
		String transaction_date = f1.format(date);
		String transaction_time = f2.format(date);
		
		String relation_bank=req.getParameter("relation_bank");
		String loan_amnt=req.getParameter("loan_amnt");
		String first_name=req.getParameter("first_name");
		String middle_name=req.getParameter("middle_name");
		String last_name=req.getParameter("last_name");
		String dob=req.getParameter("dob");
		String gender=req.getParameter("gender");
		String mobile_no=req.getParameter("mobile_no");
		String email=req.getParameter("email");
		String married_status=req.getParameter("married_status");
		String event=req.getParameter("Next");
		String relationis = "relation_bank";
		
		if(relation_bank.equals("Yes"))
		{
			relationis = "Yes";
		}
		else
		{
			relationis = "No";
		}
		
		out.println(relationis);
		out.println(loan_amnt);
		out.println(first_name);
		out.println(middle_name);
		out.println(last_name);
		out.println(dob);
		out.println(gender);
		out.println(mobile_no);
		out.println(email);
		out.println(married_status);
		out.println(event);
		
		
		
		if(event.equals("Next"))
		{
			if(relationis.equals("") ||  loan_amnt.equals("") ||  first_name.equals("") ||  middle_name.equals("") ||  last_name.equals("") ||  dob.equals("") ||  gender.equals("") ||  mobile_no.equals("") ||  email.equals("") ||  married_status.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Personal_Loan.jsp' </script>");
			}
			else {
				try {
					String sq1="insert into personal_loan_1 (account_number,Existing_Acc, Loan_amount, first_name, middle_name, last_name, dob, gender, mobile_no, email_id, married_status,loan_date,loan_time) values('"+session.getAttribute("account_number")+"','"+relationis+"', '"+loan_amnt+"', '"+first_name+"', '"+middle_name+"', '"+last_name+"', '"+dob+"', '"+gender+"', '"+mobile_no+"', '"+email+"', '"+married_status+"','"+transaction_date+"','"+transaction_time+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Phase 2'); location='Personal_Loan2.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	}

}

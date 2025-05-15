

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.io.*;
import java.sql.*;

import java.util.Date;

/**
 * Servlet implementation class Personal_Loan4
 */
public class Personal_Loan4 extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		Date date = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("dd/MM//yyyy");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		
		String transaction_date = f1.format(date);
		String transaction_time = f2.format(date);
		
		String employe_type=req.getParameter("employe_type");
		String job_name=req.getParameter("job_name");
		String job_experience=req.getParameter("job_experience");
		String job_address1=req.getParameter("job_address1");
		String job_address2=req.getParameter("job_address2");
		String job_address3=req.getParameter("job_address3");
		String State=req.getParameter("State");
		String City=req.getParameter("City");
		String District=req.getParameter("District");
		String pin_code=req.getParameter("pin_code");
		String event=req.getParameter("Submit");
		
		
		out.println(employe_type);
		out.println(job_name);
		out.println(job_experience);
		out.println(job_address1);
		out.println(job_address2);
		out.println(job_address3);
		out.println(State);
		out.println(City);
		out.println(District);
		out.println(pin_code);
		out.println(event);
		
		if(event.equals("Save"))
		{
			if( employe_type.equals("") || job_name.equals("") || job_experience.equals("") || job_address1.equals("") || job_address2.equals("") || job_address3.equals("") || State.equals("") 
					|| City.equals("") || District.equals("") || pin_code.equals("") )
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Personal_Loan4.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into personal_loan_4 (account_number,employe_type, job_name, job_experience, job_address1, job_address2, job_address3, State, City, District, pin_code,loan_date,loan_time) values('"+session.getAttribute("account_number")+"','"+employe_type+"','"+job_name+"','"+job_experience+"','"+job_address1+"','"+job_address2+"','"+job_address3+"','"+State+"','"+City+"','"+District+"','"+pin_code+"','"+transaction_date+"','"+transaction_time+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Appling Personal Loan Proccess Completed'); location='Personal_Loan_Done.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}

}

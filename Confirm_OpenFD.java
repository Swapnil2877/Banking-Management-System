

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Confirm_OpenFD
 */
public class Confirm_OpenFD extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String acc_type=req.getParameter("acc_type");
		String FD_type=req.getParameter("FD_type");
		String Deposite_amount=req.getParameter("Deposite_amount");
		String period_deposite=req.getParameter("period_deposite");
		String interest_rate=req.getParameter("interest_rate");
		String maturity_amnt=req.getParameter("maturity_amnt");
		String nominee_name=req.getParameter("nominee");
		String event=req.getParameter("Confirm");

		
		out.println(acc_type);
		out.println(FD_type);
		out.println(Deposite_amount);
		out.println(period_deposite);
		out.println(interest_rate);
		out.println(maturity_amnt);
		out.println(nominee_name);
		out.println(event);
		
		if(event.equals("Confirm"))
		{
			if(acc_type.equals("") || FD_type.equals("") || Deposite_amount.equals("") || period_deposite.equals("") || interest_rate.equals("") || maturity_amnt.equals("") || nominee_name.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Document_Req_homeloan.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into Confirm_OpenFD (account_number,acc_type, fd_type, deposite_amount, period_deposite, interest_rate, maturity_amount, nominee_name) values('"+session.getAttribute("account_number")+"','"+acc_type+"', '"+FD_type+"', '"+Deposite_amount+"', '"+period_deposite+"', '"+interest_rate+"', '"+maturity_amnt+"', '"+nominee_name+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}

}

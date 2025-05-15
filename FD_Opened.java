

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
 * Servlet implementation class FD_Opened
 */
public class FD_Opened extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		String reference=req.getParameter("reference");
		String from_acc=req.getParameter("from_acc");
		String FD_Type=req.getParameter("FD_Type");
		String Deposite_acc_no=req.getParameter("Deposite_acc_no");
		String deposite_amnt=req.getParameter("deposite_amnt");
		String event=req.getParameter("Submit");
		
		out.println(reference);
		out.println(from_acc);
		out.println(FD_Type);
		out.println(Deposite_acc_no);
		out.println(deposite_amnt);
		out.println(event);
		
		if(event.equals("Go To Home"))
		{
			if(reference.equals("") || from_acc.equals("") || FD_Type.equals("") || Deposite_acc_no.equals("") || deposite_amnt.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='FD_Opened.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into FD_Opened(account_number,reference, from_acc, FD_Type, Deposite_acc_no, deposite_amnt) values('"+session.getAttribute("account_number")+"','"+reference+"','"+from_acc+"','"+FD_Type+"','"+Deposite_acc_no+"','"+deposite_amnt+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.sendRedirect("User_DashBoard.jsp");
					
					//resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('FD Opend Successfull '); location='User_DashBoard.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
		
	}
}

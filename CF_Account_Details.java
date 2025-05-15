

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class CF_Account_Details
 */
public class CF_Account_Details extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String paye_type=req.getParameter("paye_type");
		String paye_name=req.getParameter("paye_name");
		String nick_name=req.getParameter("nick_name");
		String acc_no=req.getParameter("acc_no");
		String event=req.getParameter("Next");
		
		out.println(paye_type);
		out.println(paye_name);
		out.println(nick_name);
		out.println(acc_no);
		out.println(event);
		
		if(event.equals("Next"))
		{
			if(paye_type.equals("") || paye_name.equals("") || nick_name.equals("") || acc_no.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='CF_Account_Details.jsp' </script>");
			}
			else
			{
				try 
				{
					String sq1="insert into cf_account_details (account_number,paye_type, paye_name, nick_name, acc_no) values('"+session.getAttribute("account_number")+"','"+paye_type+"','"+paye_name+"','"+nick_name+"','"+acc_no+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
				}
				catch(Exception ex)
				{
					out.println(ex.toString());
					
				}
			}
		}
		
		
	}

}

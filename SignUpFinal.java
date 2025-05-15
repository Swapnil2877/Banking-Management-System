

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
 * Servlet implementation class SignUpFinal
 */
public class SignUpFinal extends HttpServlet {
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String user_id=req.getParameter("user_id");
		String mobile_no=req.getParameter("mobile_no");
		String password=req.getParameter("password");
		String Re_Enter_password=req.getParameter("Re_Enter_password");
		String event=req.getParameter("submit");
		
		String pass="password";
		
		if(password.equals(Re_Enter_password))
		{
			pass = password;
		}
		else
		{
			resp.setContentType("text/html");
			out.println("<script type=\"text/javascript\">  alert('wrong password please fill same password'); location='SignUpFinal.jsp' </script>");
		}
		
		out.println(user_id);
		out.println(pass);
		out.println(event);
		
		if(event.equals("SignUp"))
		{
			
			
			if(user_id.equals("") || password.equals("") || Re_Enter_password.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Some fields are empty'); location='SignUpFinal.jsp'; </script>");
			}
			else
			{
				try {
					String data="insert into signupfinal (account_number , user_name , signup_password) values ('"+session.getAttribute("new_ac_no")+"','"+user_id+"','"+pass+"');";
					String insert=db.Insert(data);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Signup Success'); location='Login.jsp' </script>");
					
				} 
				catch (Exception ex) {
		     		out.println(ex.toString());
				} 
			}
		}

	}

}

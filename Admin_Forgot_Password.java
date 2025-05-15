

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.*;
import java.io.PrintWriter;

/**
 * Servlet implementation class Admin_Forgot_Password
 */
public class Admin_Forgot_Password extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		String  admin_name=req.getParameter("admin_name");
		String  newpass=req.getParameter("newpassword");
		String  event=req.getParameter("Submit");
		
		
		out.println(admin_name);
		out.println(newpass);
		out.println(event);
		
		if(event.equals("Submit"))
		{
			if( admin_name.equals("")  || newpass.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Admin_Forgot_Password.jsp' </script>");
			}
			else
			{
				try {
					String sq1=" update adminsignup set admin_signup_password = '"+newpass+"' where admin_name='"+admin_name+"' ";
					String update=db.update(sq1);
					out.println(update);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Password Updated Successfull'); location='AdminLogin.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}
}

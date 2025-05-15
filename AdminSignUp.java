

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class AdminSignUp
 */
public class AdminSignUp extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connection cn=null;
		Statement st=null;
	
		
		PrintWriter out=resp.getWriter();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String user_id=req.getParameter("user_id");
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
			out.println("<script type=\"text/javascript\">  alert('wrong password please fill same password'); location='AdminSignUp.jsp' </script>");
		}
		
		out.println(user_id);
		out.println(pass);
		out.println(event);
		
		if(event.equals("SignUp"))
		{
			
			
			if(user_id.equals("") || password.equals("") || Re_Enter_password.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Some fields are empty'); location='AdminSignUp.jsp'; </script>");
			}
			else
			{
				try 
				{
					String data="insert into adminsignup (admin_name , admin_signup_password ) values('"+user_id+"', '"+pass+"')";
					String insert=db.Insert(data);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Signup Success'); location='AdminLogin.jsp'; </script>");
					
				} catch (Exception ex) {
		     		out.println(ex.toString());
				} 
			}
		}
	}

}

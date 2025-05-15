

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
 * Servlet implementation class Contact_Us
 */
public class Contact_Us extends HttpServlet {
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String subject=req.getParameter("subject");
		String message=req.getParameter("message");
		String event=req.getParameter("Submit");
		
		out.println(name);
		out.println(email);
		out.println(subject);
		out.println(message);
		out.println(event);
		
		if(event.equals("Submit"))
		{
			if(name.equals("") || email.equals("") || subject.equals("") || message.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Contact_Us.jsp' </script>");
			}
			else
			{
				try {
					String data="insert into contact_us (name, email, subject, message) values ('"+name+"','"+email+"','"+subject+"','"+message+"');";
					String insert=db.Insert(data);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Message Send Successfully'); location='Home_Page.jsp' </script>");
					
				} 
				catch (Exception ex) {
		     		out.println(ex.toString());
				} 
			}
		}
	}

}



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
 * Servlet implementation class DebitCard_Pin
 */
public class DebitCard_Pin extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		String pin=req.getParameter("pin");
		String re_pin=req.getParameter("re_pin");
		String event=req.getParameter("Submit");
		
		  
		
		
		
		
		if(event.equals("Submit"))
		{
			if(pin.equals("") || re_pin.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='DebitCard_Pin.jsp' </script>");
			}
			else
			{
				try 
				{
					if(pin.equals(re_pin))
					{
						String sq1="insert into ATM_Pin ( account_number ,ATM_Pin) values('"+session.getAttribute("account_number")+"','"+pin+"')";
						String insert=db.Insert(sq1);
						out.println(insert);
						
						
						//resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\">  alert('Pin Set Successfully'); location='User_DashBoard.jsp' </script>");
						resp.sendRedirect("User_DashBoard.jsp");
						
					}
					else
					{
						resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\">  alert('Pin not matching to re-pin'); location='DebitCard_Pin.jsp' </script>");
					}
					
					
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	}

}

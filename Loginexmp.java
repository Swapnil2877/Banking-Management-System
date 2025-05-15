

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Loginexmp
 */
public class Loginexmp extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String name=req.getParameter("name");
		String pass=req.getParameter("pass");
		String event=req.getParameter("submit");
		
		out.println(name);
		out.println(pass);
		out.println(event);
		
		if(event.equals("Login"))
		{
			if(name.equals("") || pass.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Some fields are  empty'); location='LoginExmp.jsp' </script>");

			}
			else
			{
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking_Management_System","root","root");
					String sq1="select * from Signupfinal where user_name ='"+name+"' && signup_password = '"+pass+"' ";
					st=cn.createStatement();
					ResultSet rs=st.executeQuery(sq1);
					if(rs.next())
					{
						out.println("Login success");
						resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\"> alert('Login success'); location='User_DashBoard.jsp'; </script>");
	
					}
					else
					{
						resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\"> alert('Login Unsucesfull'); location='LoginExmp.jsp'; </script>");
	
					}
				}
				catch(Exception ex)
				{
					out.println(ex.toString());
				}
			}
		}
	}

}

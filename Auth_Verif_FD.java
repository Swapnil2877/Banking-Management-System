

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
 * Servlet implementation class Auth_Verif_FD
 */
public class Auth_Verif_FD extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session =req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String Pin=req.getParameter("pin");
		String event=req.getParameter("Submit");
		
		out.println(Pin);
		out.println(event);
		
		if(event.equals("login"))
		{
			if(Pin.equals("") )
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Enter PIN'); location='auth_verif_FD.jsp' </script>");

			}
			else {
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking_Management_System","root","root");
					String sq1="select * from atm_pin where ATM_Pin ='"+Pin+"' ";
					st=cn.createStatement();
					ResultSet rs=st.executeQuery(sq1);
					if(rs.next())
					{
						
						session.setAttribute("user_id",rs.getString("user_id") );
						session.setAttribute("account_number",rs.getString("account_number") );
						session.setAttribute("Pin", rs.getString("ATM_Pin"));
						
						
						out.println("Login success");
						resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\"> alert('FD Completed'); location='FD_Opend.jsp'; </script>");
					}
					else
					{
						out.println("Login failed");
						resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\"> alert('Enter Correct Pin'); location='auth_verif_FD.jsp'; </script>");
						
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

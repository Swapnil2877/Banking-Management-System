

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLogin extends HttpServlet {

	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		
		Database db=new Database();
		String result = db.Connectdb();
		out.println(result);
		
		String user_id=req.getParameter("user_id");
		String password=req.getParameter("password");
		String event=req.getParameter("submit");

		
		out.println(user_id);
		out.println(password);
		
		if(event.equals("login"))
		{
			if(user_id.equals("") || password.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Some value is empty'); location='Login.jsp' </script>");

			}
			else 
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking_Management_System","root","root");
					String sq1="select * from adminsignup where admin_name ='"+user_id+"' && admin_signup_password = '"+password+"' ";
					st=cn.createStatement();
					ResultSet rs=st.executeQuery(sq1);
					if(rs.next())
					{
						out.println("Login success");
						resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\"> alert('Login success'); location='Admin_Home_Page.jsp'; </script>");
					}
					else
					{
						out.println("Login failed");
						resp.setContentType("text/html");
						out.println("<script type=\"text/javascript\"> alert('Login Failed'); location='AdminLogin.jsp'; </script>");
						
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

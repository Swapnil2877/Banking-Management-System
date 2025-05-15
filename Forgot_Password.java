

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

/**
 * Servlet implementation class Forgot_Password
 */
public class Forgot_Password extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		
		String Acc_number=req.getParameter("Acc_number");
		String newpass=req.getParameter("newpassword");
		String event=req.getParameter("Submit");
		
		
		out.println(Acc_number);
		out.println(newpass);
		out.println(event);
		
		if(event.equals("Submit"))
		{
			if( Acc_number.equals("")  || newpass.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Forgot_Password.jsp' </script>");
			}
			else
			{
				try {
					String sq1=" update signupfinal set signup_password = '"+newpass+"' where account_number='"+Acc_number+"' ";
					String update=db.update(sq1);
					out.println(update);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Password Updated Successfull'); location='Login.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}

}

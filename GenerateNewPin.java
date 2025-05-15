

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
 * Servlet implementation class GenerateNewPin
 */
public class GenerateNewPin extends HttpServlet {
	Connection cn=null;
	Statement st=null;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		String oldpin=req.getParameter("oldpin");
		String newpin=req.getParameter("newpin");
		String re_pin=req.getParameter("re_pin");
		String event=req.getParameter("Submit");
		
		String pin = newpin;
		
		out.println(oldpin);
		out.println(newpin);
		out.println(newpin);
		
		out.println(event);
		
		if(event.equals("Submit"))
		{
			if(oldpin.equals("") || newpin.equals("") || re_pin.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='FD_Opened.jsp' </script>");
			}
			else
			{
				try {
					
					
				if(newpin.equals(re_pin))
				{
					String sq1=" update atm_pin set ATM_Pin = '"+pin+"' where account_number='"+session.getAttribute("account_number")+"' ";
					String update=db.update(sq1);
					out.println(update);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Pin Updated Successfull'); location='Login.jsp' </script>");

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

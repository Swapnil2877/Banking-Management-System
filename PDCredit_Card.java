

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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class PDCredit_Card
 */
public class PDCredit_Card extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		Date date = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("dd/MM//yyyy");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		
		String transaction_date = f1.format(date);
		String transaction_time = f2.format(date);
		
		String first_name=req.getParameter("first_name");
		String middle_name=req.getParameter("middle_name");
		String last_name=req.getParameter("last_name");
		String mobile_number=req.getParameter("mobile_number");
		String accept_term=req.getParameter("accept_term");
		String event=req.getParameter("submit");
		
		
		out.println(first_name);
		out.println(middle_name);
		out.println(last_name);
		out.println(mobile_number);
		out.println(accept_term);
		out.println(event);
		
		if(event.equals("Continue"))
		{
			if(first_name.equals("") || middle_name.equals("") || last_name.equals("") || mobile_number.equals("") 
					|| accept_term.equals("") || transaction_date.equals("") || transaction_time.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='PDCredit_Card.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into PDCredit_Card (account_number,first_name, middle_name, last_name, mobile_number, accept_term,Date,Time) values('"+session.getAttribute("account_number")+"','"+first_name+"','"+middle_name+"','"+last_name+"','"+mobile_number+"','"+accept_term+"','"+transaction_date+"','"+transaction_time+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Continue'); location='Professional_D_Credit_Card.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}
}

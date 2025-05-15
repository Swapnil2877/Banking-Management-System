

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
 * Servlet implementation class PaymentDone
 */
public class PaymentDone extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String reference=req.getParameter("reference");
		String paye_type=req.getParameter("paye_type");
		String paye_name=req.getParameter("paye_name");
		String acc_number=req.getParameter("acc_number");
		String nick_name=req.getParameter("nick_name");
		String date=req.getParameter("date");
		String event=req.getParameter("submit");
		
		
		
		out.println(reference);
		out.println(paye_type);
		out.println(paye_name);
		out.println(acc_number);
		out.println(nick_name);
		out.println(date);
		out.println(event);
		
		if(event.equals("Go To Home"))
		{
			if(reference.equals("") || paye_type.equals("") || paye_name.equals("") || acc_number.equals("") 
					|| nick_name.equals("") || date.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='DTH_Rech.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into PaymentDone (account_number,reference, paye_type, paye_name, acc_number, nick_name, date_) values('"+session.getAttribute("account_number")+"','"+reference+"','"+paye_type+"','"+paye_name+"','"+acc_number+"','"+nick_name+"','"+date+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	
	
	}

}

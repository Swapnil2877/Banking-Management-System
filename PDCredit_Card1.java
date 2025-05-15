

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
 * Servlet implementation class PDCredit_Card1
 */
public class PDCredit_Card1 extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		String PAN_number=req.getParameter("PAN_number");
		String full_DOB=req.getParameter("full_DOB");
		String event=req.getParameter("submit");
		
		
		
		out.println(PAN_number);
		out.println(full_DOB);
		out.println(event);
		
		if(event.equals("Continue"))
		{
			if(PAN_number.equals("") || full_DOB.equals("") )
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='PDCredit_Card1.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into PDCredit_Card1(account_number,PAN_number, full_DOB) values('"+session.getAttribute("account_number")+"','"+PAN_number+"','"+full_DOB+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Credit Card Genererated Sucessfully'); location='DebitCardDone.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

		
	}

}

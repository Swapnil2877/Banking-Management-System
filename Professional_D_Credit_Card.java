

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
 * Servlet implementation class Professional_D_Credit_Card
 */
public class Professional_D_Credit_Card extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		String i_am=req.getParameter("i_am");
		String company_name=req.getParameter("company_name");
		String designation=req.getParameter("designation");
		String event=req.getParameter("Submit");
		
		String i_m = "i_am";
		
		if(i_am.equals("Salaried Person"))
		{
			i_m = "Salaried Person";
		}
		else if(i_am.equals("Self Employed Person"))
		{
			i_m = "Self Employed Person";
		}
		else if(i_am.equals("Retired Person"))
		{
			i_m = "Retired Person";
		}
		
		
		
		out.println(i_m);
		out.println(company_name);
		out.println(designation);
		out.println(event);
		
		if(event.equals("Continue To Step 3"))
		{
			if( i_m.equals("") || company_name.equals("") || designation.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Professional_D_Credit_Card.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into professional_D_Credit_Card (account_number,i_am, company_name, designation) values('"+session.getAttribute("account_number")+"','"+i_m+"','"+company_name+"','"+designation+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Continue TO Step 3'); location='PDCredit_Card1.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}

}

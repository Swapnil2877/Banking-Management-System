

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
 * Servlet implementation class OpenFD
 */
public class OpenFD extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String deposite_amount=req.getParameter("deposite_amount");
		String interest_earned=req.getParameter("interest_earned");
		String interest_rate=req.getParameter("interest_rate");
		String maturity_amount=req.getParameter("maturity_amount");
		String FD_Type=req.getParameter("FD_Type");
		String Period_deposit=req.getParameter("Period_deposit");
		String deposit_amount=req.getParameter("deposite_amount");
		String Nominee_name=req.getParameter("Nominee_name");
		String Nominee_relation=req.getParameter("Nominee_relation");
		String ok=req.getParameter("ok");
		String event=req.getParameter("submit");
		
		String oks = "ok";
		
		if(ok.equals("Yes"))
		{
			oks = "Yes";
		}
		else
		{
			oks = "No";
		}
		
		out.println(deposite_amount);
		out.println(interest_earned);
		out.println(interest_rate);
		out.println(maturity_amount);
		out.println(FD_Type);
		out.println(Period_deposit);
		out.println(deposit_amount);
		out.println(Nominee_name);
		out.println(Nominee_relation);
		out.println(oks);
		out.println(event);
		
		if(event.equals("Proceed"))
		{
			if(deposite_amount.equals("") || interest_earned.equals("") || interest_rate.equals("") || maturity_amount.equals("") 
					|| FD_Type.equals("") || Period_deposit.equals("") || deposit_amount.equals("") || Nominee_name.equals("") 
					|| Nominee_relation.equals("") || oks.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='OpenFD.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into OpenFD (account_number,deposite_amount, interest_earned, interest_rate, maturity_amount, FD_Type, Period_deposit, deposit_amount, Nominee_name, Nominee_relation, nominee_minor) values('"+session.getAttribute("account_number")+"','"+deposite_amount+"','"+interest_earned+"','"+interest_rate+"','"+maturity_amount+"','"+FD_Type+"','"+Period_deposit+"','"+deposit_amount+"','"+Nominee_name+"','"+Nominee_relation+"','"+oks+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Continue'); location='FD_Opend.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}

}

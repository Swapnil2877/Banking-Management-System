

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
 * Servlet implementation class Personal_Loan3
 */
public class Personal_Loan3 extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String address_line1=req.getParameter("address_line1");
		String address_line2=req.getParameter("address_line2");
		String address_line3=req.getParameter("address_line3");
		String State=req.getParameter("State");
		String City=req.getParameter("City");
		String District=req.getParameter("District");
		String pin_code=req.getParameter("pin_code");
		String terms=req.getParameter("terms");
		String event=req.getParameter("Submit");
		
		
		out.println(address_line1);
		out.println(address_line2);
		out.println(address_line3);
		out.println(State);
		out.println(City);
		out.println(District);
		out.println(pin_code);
		out.println(terms);
		out.println(event);
		
		if(event.equals("Next"))
		{
			if(address_line1.equals("") || address_line2.equals("") || address_line3.equals("") || State.equals("") 
					|| City.equals("") || District.equals("") || pin_code.equals("") || terms.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Personal_Loan3.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into personal_loan_3 (account_number,address_line1, address_line2, address_line3, State, City, District, pin_code, sameAs_Permenent) values('"+session.getAttribute("account_number")+"','"+address_line1+"','"+address_line2+"','"+address_line3+"','"+State+"','"+City+"','"+District+"','"+pin_code+"','"+terms+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Phase4'); location='Personal_Loan4.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	}

}

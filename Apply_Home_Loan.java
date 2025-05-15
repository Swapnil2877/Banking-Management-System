

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Apply_Home_Loan
 */
public class Apply_Home_Loan extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		Date date = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("dd/MM//yyyy");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		
		String transaction_date = f1.format(date);
		String transaction_time = f2.format(date);
		
		String name=req.getParameter("first_name");
		String last_name=req.getParameter("last_name");
		String email=req.getParameter("email");
		String mobile_number=req.getParameter("mobile_number");
		String address1=req.getParameter("address1");
		String address2=req.getParameter("address2");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String pin_code=req.getParameter("pin_code");
		String home=req.getParameter("home");
		String sell=req.getParameter("sell");
		String loan=req.getParameter("loan");
		String realtor=req.getParameter("realtor");
		String event=req.getParameter("Next");
		
		String homeis="home";
		
		if(home.equals("Own"))
		{
			homeis = "Own";
		}
		else
		{
			homeis = "Rent";
		}
		
		String sellin="sell";
		
		if(sell.equals("Yes"))
		{
			sellin = "Yes";
		}
		else
		{
			sellin = "No";
		}
		
		String loanis="loan";
		
		if(loan.equals("Yes"))
		{
			loanis = "Yes";
		}
		else if(loan.equals("No"))
		{
			loanis = "No";
		}
		else
		{
			loanis = "Cash Buyer";
		}
		
		String realtoris="realtor";
		
		if(realtor.equals("Yes"))
		{
			realtoris = "Yes";
		}
		else
		{
			realtoris = "No";
		}
		
		out.println(name);
		out.println(last_name);
		out.println(email);
		out.println(mobile_number);
		out.println(address1);
		out.println(address2);
		out.println(city);
		out.println(state);
		out.println(pin_code);
		out.println(homeis);
		out.println(sellin);
		out.println(loanis);
		out.println(realtoris);
		out.println(event);
		
		if(event.equals("Next"))
		{
			if(name.equals("") || last_name.equals("") || email.equals("") || mobile_number.equals("") || address1.equals("") || address2.equals("") || city.equals("") || state.equals("") || pin_code.equals("")|| homeis.equals("") || sellin.equals("") || loanis.equals("") || realtoris.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Some fields are Empty'); location='Apply_Home_Loan.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into home_loan_application (account_number,first_name, last_name, email, mobile_no, address_line_1, address_line_2, city, state, PIN_code, home_condition, home_sell, qualified_loan, realtor,homeloan_date,homeloan_time) values('"+session.getAttribute("account_number")+"','"+name+"', '"+last_name+"', '"+email+"', '"+mobile_number+"', '"+address1+"', '"+address2+"', '"+city+"', '"+state+"', '"+pin_code+"', '"+homeis+"', '"+sellin+"', '"+loanis+"', '"+realtoris+"','"+transaction_date+"','"+transaction_time+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Enter Ok To Next Page'); location='Document_Req_homeloan.jsp' </script>");
					
					
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
				
			}
			
		}
	}

}



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

/**
 * Servlet implementation class Create_Account
 */
public class Create_Account extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
	    HttpSession session = req.getSession();
	    
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String account_balance = "5000" ;
		String full_name=req.getParameter("full_name");
		String father_name=req.getParameter("father_name");
		String gender=req.getParameter("gender");
		String DOB=req.getParameter("DOB");
		String email=req.getParameter("email_id");
		String Marital_Status=req.getParameter("Marital_Status");
		String address=req.getParameter("address");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String pin_code=req.getParameter("pin_code");
		String event=req.getParameter("submit");
		
	
		
		String Gender="gender";
		String Marital= "marital_status";
		if(gender.equals("male")) {
			Gender = "female";
		}
		else {
			Gender = "male";
		}
		if(Marital_Status.equals("married"))
		{
			Marital="married";
		}
		else
		{
			Marital ="UnMarried";
		}
		
		out.println(full_name);
		out.println(father_name);
		out.println(Gender);
		out.println(DOB);
		out.println(email);
		out.println(Marital);
		out.println(address);
		out.println(city);
		out.println(state);
		out.println(pin_code);
		out.println(event);
		
		if(event.equals("submit"))
		{
			if(full_name.equals("") || father_name.equals("") || Gender.equals("") || DOB.equals("") || email.equals("") || Marital_Status.equals("")
					|| address.equals("") || city.equals("") || state.equals("") || pin_code.equals("") )
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill are the fields'); location='Create_Account.jsp' </script>");
			}
			else
			{
				try 
				{
					long ac_no = (long) (Math.random() * 1_000_000_000_000_000L);
					ac_no += 1_000_000_000_000_000L; // To ensure it's always a 16-digit number	
					
					
					session.setAttribute("new_ac_no", ac_no );
					
					String data="insert into Create_Account (account_balance ,account_number, Holder_name, Holder_Father_name, Holder_Gender, Holder_DOB, Holder_Email, Holder_Marital_Status, Holder_Address, Holder_City, Holder_State, Holder_PinCode) values('"+account_balance+"','"+ac_no+"','"+full_name+"','"+father_name+"', '"+Gender+"','"+DOB+"','"+email+"','"+Marital+"','"+address+"', '"+city+"','"+state+"','"+pin_code+"')";
					String insert=db.Insert(data);
					out.println(insert);
					resp.setContentType("text/html");
							
					out.println("<script type=\"text/javascript\">  alert('account created successfully'); location='Create_Account2.jsp' </script>");
			
					
					
				}catch(Exception ex)
				{
					out.println(ex.toString());
				}
			}
		}
		
	}

}

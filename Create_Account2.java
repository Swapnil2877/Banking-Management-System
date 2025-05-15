

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Create_Account2
 */
public class Create_Account2 extends HttpServlet {
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String Religion=req.getParameter("Religion");
		String category=req.getParameter("category");
		String income=req.getParameter("income");
		String education=req.getParameter("education");
		String occupation=req.getParameter("occupation");
		String pan_number=req.getParameter("pan_number");
		String PAN_image=req.getParameter("PAN_image");
		String aadhar_number=req.getParameter("aadhar_number");
		String aadhar_image=req.getParameter("Aadhar_image");
		String citizen=req.getParameter("citizen");
		String already_account=req.getParameter("already_account");
		String event=req.getParameter("submit");
		
		
		
		
		out.println(Religion);
		out.println(category);
		out.println(income);
		out.println(education);
		out.println(occupation);
		out.println(pan_number);
		out.println(PAN_image);
		out.println(aadhar_number);
		out.println(aadhar_image);
		out.println(citizen);
		out.println(already_account);
		out.println(event);
		
	
		
		
		if(event.equals("submit"))
		{
			if(Religion.equals("") || category.equals("") || income.equals("") || education.equals("") || occupation.equals("") || pan_number.equals("") || PAN_image.equals("") || aadhar_number.equals("") || aadhar_image.equals("") || citizen.equals("") || already_account.equals("")) 
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Create_Account2.jsp' </script>");
			}
			else
			{
				
				try {
					String sq1="insert into Create_Account2 (account_number, Holder_religion, Holder_category, Holder_income, Holder_education , phone_no, Holder_pan_no, Holder_pan_image, Holder_aadhar_no, Holder_aadhar_image, Holder_citizen, Holder_already_account) values('"+session.getAttribute("new_ac_no")+"','"+Religion+"', '"+category+"', '"+income+"', '"+education+"', '"+occupation+"','"+pan_number+"','"+PAN_image+"','"+aadhar_number+"','"+aadhar_image+"','"+citizen+"','"+already_account+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					
					out.println("<script type=\"text/javascript\">  alert('account created successfully'); location='Create_Account3.jsp' </script>");
			
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
				
			}
				
			
		}

		
	}

}

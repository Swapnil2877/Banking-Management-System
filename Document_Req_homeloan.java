

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
 * Servlet implementation class Document_Req_homeloan
 */
public class Document_Req_homeloan extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String identity=req.getParameter("identity");
		String age=req.getParameter("age");
		String residence=req.getParameter("residence");
		String income=req.getParameter("income");
		String photo=req.getParameter("photo");
		String event=req.getParameter("Next");
		
		out.println(identity);
		out.println(age);
		out.println(residence);
		out.println(income);
		out.println(photo);
		out.println(event);
		
		if(event.equals("Next"))
		{
			if(identity.equals("") || age.equals("") || residence.equals("") || income.equals("") || photo.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='Document_Req_homeloan.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into home_loan_document (account_number,identity_proof, age_proof, residence_proof, income_proof, photo) values('"+session.getAttribute("account_number")+"','"+identity+"', '"+age+"', '"+residence+"', '"+income+"', '"+photo+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Home_Loan_Select'); location='Select_homeloan.jsp' </script>");
		
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	}

}

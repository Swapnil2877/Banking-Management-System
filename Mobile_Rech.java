

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
 * Servlet implementation class Mobile_Rech
 */
public class Mobile_Rech extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String mobile=req.getParameter("mobile");
		String operator=req.getParameter("operator");
		String amount=req.getParameter("amount");
		String event=req.getParameter("submit");
		
		out.println(mobile);
		out.println(operator);
		out.println(amount);
		out.println(event);
		
		if(event.equals("Proceed to Recharge"))
		{
			if(mobile.equals("") || operator.equals("") || amount.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='DTH_Rech.jsp' </script>");
			}
			else
			{
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Banking_Management_System","root","root");
					String sq1_1="select * from create_account where account_number ='"+session.getAttribute("account_number")+"' ";
					st=cn.createStatement();
					ResultSet rs=st.executeQuery(sq1_1);
					if(rs.next())
					{
						long current_balance = Long.parseLong(rs.getString("account_balance"));
						long transfer_amount = Long.parseLong(amount);
						long updated_balance = current_balance - transfer_amount;
						
						if(updated_balance>=0)
						{
							String sq1="insert into Mobile_Rech (account_number,mobile_no, operator_name, amount) values('"+session.getAttribute("account_number")+"','"+mobile+"','"+operator+"','"+amount+"')";
							String insert=db.Insert(sq1);
							out.println(insert);
							 
							
							String sql2 = "update create_account set account_balance = '"+updated_balance+"' where  account_number ='"+session.getAttribute("account_number")+"'";
							String update = db.update(sql2);
							out.println(update);
							
							resp.setContentType("text/html");
							out.println("<script type=\"text/javascript\">  alert('Recharge Suucess'); location='Mobile_Rech.jsp' </script>");
							
							
						}
						else
						{
							resp.setContentType("text/html");
							out.println("<script type=\"text/javascript\">  alert('Insufficient Balance'); location='PaymentProceed1.jsp' </script>");
						}
					}
					 
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	}

}



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
 * Servlet implementation class DebitCard_Blocked
 */
public class DebitCard_Blocked extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String card_no=req.getParameter("card_no");
		String current_status=req.getParameter("current_status");
		String block_un=req.getParameter("block");
		String reason=req.getParameter("reason");
		String remark=req.getParameter("remark");
		String event=req.getParameter("submit");
		
		String unblock = "block_un";
		
		if(block_un.equals("Block"))
		{
			unblock = "Block";
		}
		else
		{
			unblock = "Unblock";
		}
		
		out.println(card_no);
		out.println(current_status);
		out.println(unblock);
		out.println(reason);
		out.println(remark);
		out.println(event);
		
		if(event.equals("Submit"))
		{
			if(card_no.equals("") || current_status.equals("") ||  unblock.equals("") || reason.equals("") || remark.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='DebitCard_Blocked.jsp' </script>");
			}
			else
			{
				try 
				{
					String sq1="insert into debitcard_blocked (account_number,debit_card, current_status, action_, reason, remark) values('"+session.getAttribute("account_number")+"','"+card_no+"','"+current_status+"','"+unblock+"','"+reason+"','"+remark+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert(' Next'); location='DebitCardBlock_auth.jsp' </script>");
			
				}
				catch(Exception ex)
				{
					out.println(ex.toString());
					
				}
			}
		}
		
		

	}
}

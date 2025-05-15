

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.io.*;
import java.sql.*;
import java.util.Date;

/**
 * Servlet implementation class EnterAmount
 */
public class EnterAmount extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		Date date = new Date();
		SimpleDateFormat f1 = new SimpleDateFormat("dd/MM//yyyy");
		SimpleDateFormat f2 = new SimpleDateFormat("HH:mm:ss");
		
		String transaction_date = f1.format(date);
		String transaction_time = f2.format(date);
		
		String amount=req.getParameter("amount");
		String event=req.getParameter("Next");
		
		out.println(amount);
		out.println(event);
		
		if(event.equals("Next"))
		{
			if(amount.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='EnterAmount.jsp' </script>");
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
							String sq1="insert into enter_amount(account_number,amount) values('"+session.getAttribute("new_ac_no")+"','"+amount+"')";
							String insert=db.Insert(sq1);
							out.println(insert);
							
							String sql1 = "insert into transfer (from_account_number, to_account_number, amount, transaction_date, transaction_time) values ('"+session.getAttribute("account_number")+"' , '"+session.getAttribute("acc_no")+"', '"+amount+"', '"+transaction_date+"', '"+transaction_time+"')";
							String insert1 = db.Insert(sql1);
							out.println(insert1);
							
							String sql2 = "update create_account set account_balance = '"+updated_balance+"' where  account_number ='"+session.getAttribute("account_number")+"'";
							String update = db.update(sql2);
							out.println(update);
							
							resp.setContentType("text/html");
							out.println("<script type=\"text/javascript\">  alert('Amount Transfered'); location='PaymentProceed1.jsp' </script>");
							
							
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

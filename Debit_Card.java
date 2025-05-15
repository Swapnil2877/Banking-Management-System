

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import  java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Debit_Card
 */
public class Debit_Card extends HttpServlet {
	
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
		
		String name=req.getParameter("name");
		String account=req.getParameter("acc_no");
		String branch=req.getParameter("Branch_address");
		String home=req.getParameter("home");
		String address=req.getParameter("Add");
		String card_type=req.getParameter("card_type");
		String event=req.getParameter("Submit");
		String add = "address";
		
		if(address.equals("Home"))
		{
			add = "Home";
		}
		else
		{
			add = "Branch";
					
		}

		out.println(name);
		out.println(account);
		out.println(branch);
		out.println(home);
		out.println(add);
		out.println(card_type);
		out.println(event);
	
		
		if(event.equals("Submit"))
		{
			if(name.equals("") || account.equals("") || branch.equals("") || home.equals("") || add.equals("") || card_type.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Some value is empty'); location='Debit_Card.jsp' </script>");
			}
			else
			{
				try {
					
					long debit_no = (long) (Math.random() * 5_000_000_000_000_000L);
					debit_no += 5_000_000_000_000_000L; // To ensure it's always a 16-digit number	
					
					
					session.setAttribute("debit_no", debit_no );
					
					
					String data="insert into debit_card (user_id,account_number,debit_no, name_for_atm, account_no, branch_address, home_address, select_address, card_type,debitcard_created_date,debitcard_created_time) values ('"+session.getAttribute("user_id")+"','"+session.getAttribute("account_number1")+"','"+debit_no+"','"+name+"','"+account+"','"+branch+"','"+home+"','"+add+"','"+card_type+"','"+transaction_date+"','"+transaction_time+"');";
					String insert=db.Insert(data);
					out.println(insert);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Debit Card created successfully'); </script>");
					resp.sendRedirect("DebitCard_Pin.jsp");
					
			
				} catch (Exception ex) {
		     		out.println(ex.toString());
				} 
			}
		}

	}
}

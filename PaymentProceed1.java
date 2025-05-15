
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
 * Servlet implementation class PaymentProceed1
 */
public class PaymentProceed1 extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String uni=req.getParameter("uni");
		String paye_name=req.getParameter("paye_name");
		String nick_name=req.getParameter("nick_name");
		String acc_no=req.getParameter("acc_no");
		String ifsc_code=req.getParameter("ifsc_code");
		String acc_type=req.getParameter("acc_type");
		String event=req.getParameter("Submit");
		
		String bank = "uni";
		
		if(uni.equals("UniBank"))
		{
			bank = "UniBank";
		}
		else
		{
			bank = "Other Bank";
		}
		
		out.println(bank);
		out.println(paye_name);
		out.println(nick_name);
		out.println(acc_no);
		out.println(ifsc_code);
		out.println(acc_type);
		out.println(event);
		
		if(event.equals("Proceed"))
		{
			if(bank.equals("") || paye_name.equals("") || nick_name.equals("") || acc_no.equals("") 
					|| ifsc_code.equals("") || acc_type.equals(""))
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='PaymentProceed1.jsp' </script>");
			}
			else
			{
				try 
				{
					session.setAttribute("acc_no", acc_no);
					
					String sq1="insert into PaymentProceed (account_number,bank, paye_name, nick_name, acc_no, ifsc_code, acc_type) values('"+session.getAttribute("account_number")+"','"+bank+"','"+paye_name+"','"+nick_name+"','"+acc_no+"','"+ifsc_code+"','"+acc_type+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					resp.sendRedirect("EnterAmount.jsp");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}

	}
}

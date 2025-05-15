

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SignUp1
 */
public class SignUp1 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database db=new Database();
		String result = db.Connectdb();
		out.println(result);
		
		String Account_Number=req.getParameter("account_number");
		String Cif_Number=req.getParameter("cif_number");
		String branch_code=req.getParameter("branch_code");
		String Country=req.getParameter("Country");
		String mobile_number=req.getParameter("mobile_number");
		String event=req.getParameter("submit");
		
		out.println(Account_Number);
		out.println(Cif_Number);
		out.println(branch_code);
		out.println(mobile_number);
		out.println(Country);
		out.println(event);
		
		
		if(event.equals("submit"))
		{
			if(Account_Number.equals("") || Cif_Number.equals("") || branch_code.equals("") || Country.equals("") || mobile_number.equals("") )
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='SignUp.jsp' </script>");
			}
			else
			{
				try {
					String sq1="insert into signup (account_number, user_Account_No, user_CIF_No, user_Branch_Code, user_Nationality , user_Mobile_No) values('"+session.getAttribute("new_ac_no")+"','"+Account_Number+"', '"+Cif_Number+"', '"+branch_code+"', '"+Country+"', '"+mobile_number+"')";
					String insert=db.Insert(sq1);
					out.println(insert);
					resp.sendRedirect("SignUpFinal.jsp");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}

}

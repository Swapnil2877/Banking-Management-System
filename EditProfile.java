
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
 * Servlet implementation class EditProfile
 */
public class EditProfile extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		HttpSession session = req.getSession();
		
		Database  db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		
		String user_name=req.getParameter("user_name");
		String mobile_no=req.getParameter("mobile_no");
		String pan_number=req.getParameter("pan_number");
		String email_id=req.getParameter("email_id");
		String ckyc_number=req.getParameter("ckyc_number");
		String aadhhar_no=req.getParameter("aadhhar_no");
		String address=req.getParameter("address");
		String event=req.getParameter("Submit");
		
		
		out.println(user_name);
		out.println(mobile_no);
		out.println(pan_number);
		out.println(email_id);
		out.println(ckyc_number);
		out.println(aadhhar_no);
		out.println(address);
		out.println(event);
		
		if(event.equals("Submit"))
		{
			if(  user_name.equals("") || mobile_no.equals("") || pan_number.equals("") || email_id.equals("") || ckyc_number.equals("") 
					|| aadhhar_no.equals("") || address.equals("") )
			{
				resp.setContentType("text/html");
				out.println("<script type=\"text/javascript\">  alert('Please fill all the fields'); location='EditProfile.jsp' </script>");
			}
			else
			{
				try {
					String sq1="update create_account set Holder_name='"+user_name+"',Holder_Email='"+email_id+"',Holder_Address='"+address+"' where id='"+session.getAttribute("user_id")+"'";
					String insert=db.Insert(sq1);
					out.println(insert);
					
					String sq11="update create_account2 set phone_no='"+mobile_no+"',Holder_pan_no='"+pan_number+"',Holder_aadhar_no='"+aadhhar_no+"' where Account_id='"+session.getAttribute("user_id")+"'";
					String insert1=db.Insert(sq11);
					out.println(insert1);
					
					resp.setContentType("text/html");
					out.println("<script type=\"text/javascript\">  alert('Profile Update Successfull'); location='Profile.jsp' </script>");
				}
				catch(Exception ex){
					out.println(ex.toString());
					
				}
			}
		}
	}
}

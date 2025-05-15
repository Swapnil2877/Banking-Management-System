

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Admin_manage_user
 */
public class Admin_manage_user extends HttpServlet {
	
	Connection cn=null;
	Statement st=null;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		Database db=new Database();
		String result=db.Connectdb();
		out.println(result);
		
		String delete=req.getParameter("submit");
		
		
		
		
		
	}

}

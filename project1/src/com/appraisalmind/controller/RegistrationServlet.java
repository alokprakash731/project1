package com.appraisalmind.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RegistrationServlet extends GenericServlet{

	Connection con=null;
	PreparedStatement ps=null;
	
	public void init(ServletConfig config) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","manager");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		PrintWriter out=res.getWriter();
		String username=req.getParameter("username");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String password2=req.getParameter("password2");
		
		try {
			ps=con.prepareStatement("insert into registration values(?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, password2);
			
			int rowupdate=ps.executeUpdate();
			
			if(rowupdate!=0) {
				out.println("<html><body bgcolor='wheat'><center><h1>");
				out.println("Hi "+username+"  Your  REGISTRATION DONE  SUCCESSFULY  ");
				out.println("Welcome to our AppraisalMind Team");
				out.println("</h1></center></body></html>");
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		out.close();
	}
	
	public void destroy() {
		
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}

package internal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
/**
 * Servlet implementation class Stat
 */
@WebServlet("/Stat")
public class Stat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agri","root","root");
			//PreparedStatement pst=con.prepareStatement("insert into users values(?,?,?,?)") ;	
			PreparedStatement p1=con.prepareStatement("select * from aadhar where id=?");
			String u=request.getParameter("name");
			String p=request.getParameter("phone");
			String q=request.getParameter("aadhar");
			String r=request.getParameter("password");
			String s=request.getParameter("password1");
			PrintWriter pw=response.getWriter();
	    	p1.setString(1, q);
	    	ResultSet rs=p1.executeQuery();
	    	if(rs.next()==true) {
	    		PreparedStatement pst=con.prepareStatement("insert into users values(?,?,?,?)") ;	
	    		if(r.equals(s)) {
	    			pst.setString(1, u);
	    			pst.setString(2, p);
	    			pst.setString(3, q);
	    			pst.setString(4, r);
	    			pst.executeUpdate();
	    			RequestDispatcher rd=request.getRequestDispatcher("home.html");
	    			rd.forward(request, response);
	    			HttpSession se=request.getSession();
	    			se.setAttribute("mobile",p);
	   	    	 	se.setAttribute("name", u);
	    		}
	    		else {
	    			pw.println("invalid password,please enter again");
	    			RequestDispatcher rd=request.getRequestDispatcher("agri_register.html");
	    			rd.include(request, response);
	    		}
	    	}
	    	else {
		    	pw.println("invalid user,please re-enter your aadhar no.");
		    	RequestDispatcher rd=request.getRequestDispatcher("agri_register.html");
		    	rd.include(request, response);
	    	}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

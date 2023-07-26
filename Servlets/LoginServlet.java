

import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServelt
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		String no=request.getParameter("mno");
		String password=request.getParameter("pass");
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agri","root","root");
	    PreparedStatement pst = con.prepareStatement("Select * from users where mobile=? and password=?");
	    pst.setString(1,no);
	    pst.setString(2,password);
	    ResultSet rs=pst.executeQuery();
	     if(rs.next()) {
	    	 HttpSession s=request.getSession();
	    	 s.setAttribute("mobile",no);
	    	 s.setAttribute("name", rs.getString(1));
	    	 
	    	 if(s!=null) {
	    	 RequestDispatcher rd=request.getRequestDispatcher("home.html");
	 		rd.forward(request, response); 
	    	 }
	     }
	     else {
	    	 pw.println("Please enter valid mobile number or password");
		RequestDispatcher rd=request.getRequestDispatcher("Login.html");
		rd.include(request, response);
	     }
	}
	catch(Exception e) {
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

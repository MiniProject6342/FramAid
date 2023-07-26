

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class CookieDemo
 */
@WebServlet("/CookieDemo")
public class CookieDemo extends HttpServlet {
    public CookieDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String u=request.getParameter("u");
		String p=request.getParameter("p");
		PrintWriter pw=response.getWriter();
		ServletConfig sc=getServletConfig();
		String u1=sc.getInitParameter("user");
		ServletContext s=sc.getServletContext();
		String p1=s.getInitParameter("pass");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internal","root","root");
			PreparedStatement pst=con.prepareStatement("select * from login where rno=? and sname=?");
			pst.setString(1, u);
			pst.setString(2, p);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				pw.print("valid");
			}
			else {
				pw.print("invalid user");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		/*if(u.equals(u1) && p.equals(p1)) {
			pw.print("valid");
		}
		else {
			pw.print("invalid");
		}*/
		PrintWriter pw=response.getWriter();
		pw.println("welcome");
	}

}

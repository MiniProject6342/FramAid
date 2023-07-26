

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.Blob;

/**
 * Servlet implementation class Blobb
 */
@WebServlet("/Blobb")
public class Blobb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Blobb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			PrintWriter pw=response.getWriter();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agri","root","root");
		Statement st=con.createStatement();
		PreparedStatement pst1=con.prepareStatement("select pid,sname,loc,cost from products where ename=?");
		String search=request.getParameter("search1");
		pst1.setString(1, search);
		 ResultSet rst=pst1.executeQuery();
		 ////
		 
		 
		 ////
		response.setContentType("text/html");
		pw.println("<html>");
		pw.print("<style> th{background-color:yellow;}tr{background-color:cyan;} </style>");
		pw.println("<body>");
		pw.println("<form action='Addcart' method='get'>");
		pw.println("<table  cellspacing='10' cellpadding='50'>");
		//
		pw.println("<tr >");
		pw.println("<th>Shop name</th>");
		pw.println("<th>Location</td>");
		pw.println("<th>Cost</th>");
		pw.println("<th>ADD TO CART</th>");
		if(search.equals("tractor")) {
		pw.println("<th background-color='white' rowspan='5'><img src='https://th.bing.com/th?id=OIP.KZz8sMrchIIJGFMxxBAE3AHaE8&w=306&h=204&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2'></th>");}
		else if(search.equals("seed drill")) {
			pw.println("<th rowspan='5'><img src='https://th.bing.com/th/id/OIP.AjfPDMKkQbaYYuwrAh8-8gHaG5?w=206&h=192&c=7&r=0&o=5&dpr=1.3&pid=1.7'></th>");
		}
		else if(search.equals("plough")) {
			pw.println("<th rowspan='5'><img src='https://th.bing.com/th/id/OIP.hIpHKuEQu1HZDzyW4ufr8AHaFJ?w=260&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7'></th>");
		}
		
		pw.println("</tr>");
		while(rst.next()) {
			pw.println("<tr>");
			pw.println("<td>"+rst.getString(2)+"</td>");
			pw.println("<td>"+rst.getString(3)+"</td>");
			int a=rst.getInt(1);
			pw.println("<td>"+rst.getString(4)+"</td>");
			pw.println("<td align='center'><a href='MyCart?id="+a+"'>+</a></td>");
//			HttpSession se=request.getSession();
//			se.setAttribute("name", rst.getString(1));
//			se.setAttribute("loc", rst.getString(2));
//			se.setAttribute("cost", rst.getString(3));
//			pw.println("<td><input type='submit'></td>");
			pw.println("</tr>");
		}
		pw.println("</tr>");
		pw.println("</table>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
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



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DisplayCart
 */
@WebServlet("/DisplayCart")
public class DisplayCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCart() {
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
		HttpSession session=request.getSession(false);
		String mob=(String) session.getAttribute("mobile");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agri","root","root");
		PreparedStatement pst=con.prepareStatement("select * from cart where uid=?");
		pst.setString(1, mob);
		int price=0;
		ResultSet rst=pst.executeQuery();
		pw.println("<html>");
		pw.print("<style> th{background-color:yellow;}tr{background-color:cyan;} </style>");
		pw.println("<body>");
		pw.println("<form action='Thank.html' method='get'>");
		pw.println("<table  cellspacing='10' cellpadding='50'>");
		//
		pw.println("<tr >");
		pw.println("<th>Shop name</th>");
		pw.println("<th>Location</td>");
		pw.println("<th>Cost</th>");
		pw.println("<th>Equipment</th>");
		pw.println("</tr >");
		
		while(rst.next()) {
			pw.println("<tr>");
			pw.println("<td>"+rst.getString("sname")+"</td>");
			pw.println("<td>"+rst.getString("loc")+"</td>");
			
			pw.println("<td>"+rst.getString("ename")+"</td>");
			pw.println("<td>"+rst.getDouble("cost")+"</td>");
			price+=rst.getDouble("cost");
			pw.print("</tr>");
			
			
		}
		pw.print("<tr>");
		pw.println("<td colspan='4' align='center'>your total cost is:"+price);
		pw.println("<input type='submit' value='Buy Now'></td> </tr>");
		pw.print("</table>");
	    pw.print("</form>");
	    pw.print("</body>");
	    pw.print("</html>");
		}
		catch(Exception e)
		{
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

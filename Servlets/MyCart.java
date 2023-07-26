

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyCart
 */
@WebServlet("/MyCart")
public class MyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCart() {
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
			String id=request.getParameter("id");
			PrintWriter pw=response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agri","root","root");
			PreparedStatement pst=con.prepareStatement("select sname,loc,cost,ename from products where pid=?");
			pst.setString(1,id);
			ResultSet rs=pst.executeQuery();
			if(rs.next()==true)
			{
				HttpSession se=request.getSession(false);
				//pw.println(rs.getString("sname")+" "+rs.getString("loc")+" "+rs.getString("cost")+" "+se.getAttribute("mobile"));
				pst=con.prepareStatement("insert into cart values(?,?,?,?,?)");
				pst.setString(1, rs.getString("sname"));
				pst.setString(2, rs.getString("loc"));
				pst.setDouble(3,rs.getDouble("cost"));
				pst.setString(4,(String)se.getAttribute("mobile"));
				pst.setString(5, rs.getString("ename"));
				pst.executeUpdate();
				response.setContentType("text/html");
				
				pw.print("<html><body><script>alert('product got added');</script></body></html>");
				
			}
			
		else {
				//pw.println("try again couldnt add");
			pw.print("<html><body><script>alert('product not added');</script></body></html>");
			}
			//pw.print(s);
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

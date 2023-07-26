

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

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
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
			String search=request.getParameter("search1");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/agri","root","root");
			PreparedStatement pst;
			RequestDispatcher rd;
			ResultSet rst;
			//pw.println("this is a project"+search);
		if (search.equals("labor"))
		{
			
			pst=con.prepareStatement("select *from workers");
			rst=pst.executeQuery();
			rd=request.getRequestDispatcher("worker.html");
			rd.forward(request, response);
			
		}
		else if(search.equals("storage"))
		{
			pw.println("this is a project");
			pst=con.prepareStatement("select *from storage");
			rst=pst.executeQuery();
			rd=request.getRequestDispatcher("storage.html");
			rd.forward(request, response);
		}
		else {
			
			 pst=con.prepareStatement("select sname,loc,cost from products where ename=?");
			pst.setString(1, search);
			 rst=pst.executeQuery();
			
			if(rst.next()==false)
			{
				pw.println("product not found");
				rd=request.getRequestDispatcher("home.html");
				rd.include(request, response);
				
			}
			else {
				pw.println("this is a project"+search);
				rd=request.getRequestDispatcher("Blobb");
				rd.forward(request, response);
			}
			
		}
		
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

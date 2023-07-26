
package internal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Statements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internal","root","root");
			//PreparedStatement pst=con.prepareStatement("select * login values(?,?)");
			while(true) {
				System.out.println("enter 1.insert 2.delete 3.update 4.select 5.exit");
				int ch=sc.nextInt();
				switch(ch) {
					case 1:System.out.println("enter rno,name");
					       int rno=sc.nextInt();
					       sc.nextLine();
					       String sname=sc.nextLine();
					       PreparedStatement pst=con.prepareStatement("insert into login values(?,?)");
					       pst.setInt(1,rno);
					       pst.setString(2, sname);
					       pst.execute();
					       break;
					case 2:System.out.println("enter rno");
					       rno=sc.nextInt();
					       pst=con.prepareStatement("delete from login where rno=?");
					       pst.setInt(1, rno);
					       pst.execute();
					       break;
					case 3:System.out.println("enter rno and name");
						   rno=sc.nextInt();
				           sc.nextLine();
				           sname=sc.nextLine();
				           pst=con.prepareStatement("update login set sname=? where rno=?");
				           pst.setString(1, sname);
				           pst.setInt(2, rno);
				           pst.execute();
				           break;
					case 4:pst=con.prepareStatement("select * from login");
					       ResultSet rs=pst.executeQuery();
					       while(rs.next()) {
					    	   System.out.println(rs.getString(1)+" "+rs.getString(2));
					       }
					       break;
					case 5:System.exit(1);
					       break;
					default:System.out.println("enter correct choice");       
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

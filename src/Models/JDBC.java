package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBC {
	public static Connection getConnection(String url) throws ClassNotFoundException {
		Connection c=null;
		try {
		// Đăng ký MySQL Driver với DriverManager
		//Class.forName("com.mysql.cj.jdbc.Driver");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		// Các thông số
		//String url=linkurl;
		//"jdbc:mySQL://localhost:3306/data";
		String username = "root";
		String password = "";
		
		// Tạo kết nối
		c= DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	// Đóng kết nối
	public static void closeConnection(Connection c) {
		try {
			if(c!=null)
				c.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void printInfor(Connection c) {
		try {
			java.sql.DatabaseMetaData mtdt = null;
			if(c!=null)
				 mtdt = c.getMetaData()	;
				System.out.println(mtdt.getDatabaseProductName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
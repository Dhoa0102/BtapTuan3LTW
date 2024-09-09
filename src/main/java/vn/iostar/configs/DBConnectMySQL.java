package vn.iostar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {
	private static String USERNAME = "root";
	private static String PASSWORD = "hoa856856";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/ltweb";

	public static Connection getDatabaseConnection() throws SQLException, Exception {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			Connection c=getDatabaseConnection();
			System.out.println(DBConnectMySQL.getDatabaseConnection());
			if(c==null)
			{
				System.out.println("ket noi khong thanh cong");
			}
			else
			{
				System.out.println("ok");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

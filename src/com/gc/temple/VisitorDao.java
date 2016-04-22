package com.gc.temple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class VisitorDao {
	public static Connection getConnection() throws SQLException {
		String dbURL = "jdbc:mysql://localhost:3306/temple";
		String username = "root";
		String password = "sesame";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = DriverManager.getConnection(dbURL, username, password);
		return connection;
	}

	public static void saveEntry(Visitor visitor) {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/temple";
			String username = "root";
			String password = "sesame";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection connection = DriverManager.getConnection(dbURL, username, password);

			String sql = "INSERT INTO visitors (phone, firstName, lastName, address)"
					+ "VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
			preparedStatement1.setString(1, visitor.getPhoneNumber());
			preparedStatement1.setString(2, visitor.getFirstName());
			preparedStatement1.setString(3, visitor.getLastName());
			preparedStatement1.setString(4, visitor.getAddress());
			preparedStatement1.executeUpdate();

		} catch (SQLException e) {
			for (Throwable t : e)
				t.printStackTrace();
		}
	}

	public static void getColumnNames(ResultSet rs) throws SQLException {
		if (rs == null) {
			return;
		}
		ResultSetMetaData rsMetaData = (ResultSetMetaData) rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();

		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			String tableName = rsMetaData.getTableName(i);
			System.out.println("column name=" + columnName + " table=" + tableName + "");
		}
	}

	static void listVisitors() {
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String query = "select * from temple.visitors where phone = '8108887654';";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("phone number: " + rs.getString("phone"));
				System.out.println("address: " + rs.getString("address"));
				System.out.println("first name: " + rs.getString("firstName"));
				System.out.println("last name: " + rs.getString("lastName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * 
 */
package me.huzorro.simple;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author huzorro
 *
 */
public class SqliteTest1 {

	/**
	 * 
	 */
	public SqliteTest1() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    	try {
			Class.forName("org.sqlite.JDBC");
			URL url = ClassLoader.getSystemResource("session.config");
			System.out.println(url.getPath());
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + url.getFile());
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM session_config_tbl");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("desc"));
				System.out.println(new String(rs.getBytes("desc"),  "GBK"));
				System.out.println(new String("我们的".getBytes(), "utf-8"));
				System.out.println(rs.getString("createtime"));
				System.out.println("test...");
				System.out.println("t1.");
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

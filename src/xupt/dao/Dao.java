package xupt.dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class Dao {

	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String URL = "jdbc:mysql://localhost:3306/xupt?useSSL = false&serverTimezone=UTC&charset=UTF8&allowPublicKeyRetrieval=true&useInformationSchema=true";
	private String USER = "root";
	private String PASS = "1996";
	private Connection conn;
	
	public Dao() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(Driver);
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "加载数据库驱动失败,请检查驱动版本！");
			e.printStackTrace();
		}
		refreshConnection();
	}
	
	private void refreshConnection() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库连接失败，请检查数据库设置！");
		}
	}
	
	public ResultSet excuteQuery(String sql) {
		ResultSet rs = null;
		try {
			if(conn.isClosed())
				refreshConnection();
			rs = conn.createStatement().executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = null;
		}
		return rs;
	}
	
	public int excuteUpdate(String sql) {
		int i = 0;
		try {
			if(conn.isClosed())
				refreshConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			i = 0;
			System.out.println(sql);
		}
		return i;
	}
	
	public void close() {
		try {
			if( !conn.isClosed() ) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public String[] getColumnName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}

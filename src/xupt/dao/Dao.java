package xupt.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

import xupt.util.Tools;

public class Dao {

	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String URL = "jdbc:mysql://localhost:3306/xupt?useSSL = false&serverTimezone=UTC&charset=UTF8&allowPublicKeyRetrieval=true&useInformationSchema=true";
	private String USER = "root";
	private String PASS = "1996";
	protected Connection conn;
	
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
	
	protected void refreshConnection() {
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
	
	public Vector<String> getColumnName(String tableName) {
		// TODO Auto-generated method stub
		Vector<String> columns = null;
		String sql = "SHOW FULL COLUMNS FROM "+tableName;
		try {
			if(conn.isClosed()) {
				refreshConnection();
			}
			ResultSet rs = this.excuteQuery(sql);
			columns = new Vector<String>();
			while(rs.next()) {
				columns.add(rs.getString("FIELD"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			columns = null;
		}finally {
			close();
		}
		return columns;
	}
	
	public Vector<String> getComment(String tableName) {
		Vector<String> commentVes = null;
		try {
			if(conn.isClosed())
				refreshConnection();
			ResultSet re = this.excuteQuery("show full columns from " + tableName);
			commentVes = new Vector<String>();
			while(re.next()) {
				commentVes.add(re.getString("Comment"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return commentVes;
	}
	
	public List<String> getIdAndNameList(String tableName) {
		List<String> list = null;
		String column = null;
		if(tableName.equals("base_info")) {
			column = "user_id,name";
		}else {
			column = "id,name";
		}
		String sql = "SELECT "+column+" FROM "+tableName+";";
		try {
			if(conn.isClosed()) {
				refreshConnection();
			}
			ResultSet rs = this.excuteQuery(sql);
			list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("id")+" "+rs.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			list = null;
		}finally {
			close();
		}
		return list;
	}
	
	//获取字段类型为enum的字段所有可能的类型值
		public List<String> getEnumValue(String tableName,String columnName){
			List<String> enumValue = null;
			String sql = "SELECT column_type FROM information_schema.COLUMNS WHERE TABLE_SCHEMA="
					+ "'xupt' AND DATA_TYPE='enum' AND TABLE_NAME='"+tableName+"' AND COLUMN_NAME='"+columnName+
					"';";
			try {
				ResultSet rs = this.excuteQuery(sql);
				if(rs.next()) {
					enumValue = new Tools().splitEnumValue(rs.getString(1));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				this.close();
			}
			return enumValue;
		}
	
	public static void main(String[] argv) {
		Dao dao = new Dao();
		Vector<String> columns = dao.getColumnName("base_info");
		System.out.println(columns);
	}

}

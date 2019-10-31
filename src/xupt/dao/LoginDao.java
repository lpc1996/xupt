package xupt.dao;

import java.sql.ResultSet;

import xupt.mode.LoginModel;

public class LoginDao extends Dao{
	
	String tableName = "login";
	
	public LoginDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	//登录系统，验证用户名和密码
	public LoginModel login(String user,String pass) {
		String sql = "Select * from login where id='"+user+"' and pass='"+pass+"';";
		LoginModel loginModel = null;
		try {
			ResultSet rs = this.excuteQuery(sql);
			if(rs.next()) {
				loginModel = new LoginModel();
				loginModel.setId(user);
				loginModel.setName(rs.getString("name"));
				loginModel.setLimit(rs.getInt("limit"));
			}
			this.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loginModel;
	}
	
	public boolean addUser(LoginModel user,String pass) {
		boolean isOk = false;
		StringBuffer sb = new StringBuffer("insert into login values('");
		sb.append(user.getId());
		sb.append("','");
		sb.append(user.getName());
		sb.append("','");
		sb.append(user.getLimit());
		sb.append("','");
		sb.append(pass);
		sb.append("',';");
		String sql = sb.toString();
		try {
			if(this.excuteUpdate(sql) == 1) {
				isOk = true;
				this.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isOk = false;
		}
		return isOk;
	}
}

package xupt.dao;

import java.sql.ResultSet;

import xupt.mode.LoginModel;

public class LoginDao extends Dao{
	
	String tableName = "login";
	
	public LoginDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	//��¼ϵͳ����֤�û���������
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

}

package xupt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import xupt.mode.TeacherModel;

public class TeacherDao extends Dao {
	
	private final String tableName = "teacher";
	private final String viewName = "base_teacher";

	public TeacherDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public List<TeacherModel> getList(){
		List<TeacherModel> list = null;
		String sql ="SELECT * FROM "+viewName;
		try {
			ResultSet rs = this.excuteQuery(sql);
			list = new ArrayList<TeacherModel>();
			while(rs.next()) {
				list.add(setValue(rs));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
		}finally {
			close();
		}
		return list;
	}
	
	private TeacherModel setValue(ResultSet rs) {
		TeacherModel teacher = null;
		try {
			teacher = new TeacherModel();
			teacher.getBase().setId(rs.getString("user_id"));
			teacher.getBase().setName(rs.getString("name"));
			teacher.getBase().setFormarName(rs.getString("formar_name"));
			teacher.getBase().setSex(rs.getString("sex"));
			teacher.getBase().setAge(rs.getInt("age"));
			teacher.getBase().setNativePlace(rs.getString("native_place"));
			teacher.getBase().setIDCARDTYPE(rs.getString("IDCARD_type"));
			teacher.getBase().setIDCARDNUM(rs.getString("IDCARD_NUM"));
			teacher.getBase().setType(rs.getString("user_type"));
			teacher.getBase().setTel(rs.getString("tel"));
			teacher.setCollege(rs.getString("college"));
			teacher.setDepartment(rs.getString("department"));
			teacher.setLevel(rs.getString("level"));
			teacher.setEducation(rs.getString("education"));
			teacher.setYear(rs.getString("year"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			teacher = null;
		}
		return teacher;
	}
	
	public Vector<String> getComments(){
		return getComment(viewName);
	}

}

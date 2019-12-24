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
			teacher.getBaseInfo().setId(rs.getString("user_id"));
			teacher.getBaseInfo().setName(rs.getString("name"));
			teacher.getBaseInfo().setFormarName(rs.getString("formar_name"));
			teacher.getBaseInfo().setSex(rs.getString("sex"));
			teacher.getBaseInfo().setAge(rs.getInt("age"));
			teacher.getBaseInfo().setNativePlace(rs.getString("native_place"));
			teacher.getBaseInfo().setIDCARDTYPE(rs.getString("IDCARD_type"));
			teacher.getBaseInfo().setIDCARDNUM(rs.getString("IDCARD_NUM"));
			teacher.getBaseInfo().setType(rs.getString("user_type"));
			teacher.getBaseInfo().setTel(rs.getString("tel"));
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
	
	public boolean insertData(TeacherModel teacher) {
		boolean result = false;
		String sql = "INSERT INTO teacher VALUES('"+teacher.getBaseInfo().getId()+"','"+teacher.getCollege()
		+"','"+teacher.getDepartment()+"','"+teacher.getLevel()+"','"+teacher.getEducation()+"','"
				+teacher.getYear()+"');";
		try {
			if(new BaseInfoDao().insertData(teacher.getBaseInfo())) {
				if(this.excuteUpdate(sql) == 1) {
					result = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	
	public Vector<String> getComments(){
		return getComment(viewName);
	}

	public boolean updateData(TeacherModel data, String id) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "UPDATE "+tableName+" SET id='"+data.getBaseInfo().getId()+"',college='"
		+data.getCollege()+"',department='"+data.getDepartment()+"',level='"+data.getLevel()+"',education='"
				+data.getEducation()+"',year='"+data.getYear()+"' WHERE id='"+id+"';";
		try {
			if(new BaseInfoDao().updateData(data.getBaseInfo(), id)) {
				if(this.excuteUpdate(sql) == 1) {
					result = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}

	public boolean deleteData(String id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM "+tableName+" WHERE id='"+id+"'";
		boolean result = false;
		try {
			if(new BaseInfoDao().deleteData(id)) {
				if(this.excuteUpdate(sql) == 1) {
					result = true;
				}
			}
		}catch (Exception e){
			System.out.println(sql);
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			close();
		}
		return result;
	}

}

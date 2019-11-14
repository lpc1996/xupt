package xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import xupt.mode.BaseInfoModel;
import xupt.mode.StudentModel;

public class StudentDao extends Dao {
	
	private final String tableName = "student";
	private final String baseTableName = "base_info";
	private final String viewName= "Base_Student";
	
	public StudentDao() {
		super();
	}
	
	public Object setValue(ResultSet rs) {
		StudentModel student = null;
		try {
			student = new StudentModel();
			BaseInfoModel base = new BaseInfoModel();
			base.setId(rs.getString("user_id"));
			base.setName(rs.getString("name"));
			base.setFormarName(rs.getString("formar_name"));
			base.setAge(rs.getInt("age"));
			base.setSex(rs.getString("sex"));
			base.setNativePlace(rs.getString("native_place"));
			base.setIDCARDTYPE(rs.getString("IDCARD_type"));
			base.setIDCARDNUM(rs.getString("IDCARD_NUM"));
			base.setType(rs.getString("user_type"));
			base.setTel(rs.getString("tel"));
			student.setId(rs.getString("user_id"));
			student.setYear(rs.getString("year"));
			student.setCollege(rs.getString("college"));
			student.setDepartment(rs.getString("department"));
			student.setMajor(rs.getString("major"));
			student.setGrade(rs.getString("grade"));
			student.setClassId(rs.getString("class"));
			student.setCulture_level(rs.getString("culture_level"));
			student.setType(rs.getString("student_type"));
			student.setEducation(rs.getString("Education"));
			student.setBaseInfo(base);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return student;
	}
	
	public List getList() {
		List studentList = null;
		String sql ="SELECT user_id,`name`,formar_name,sex,age,native_place,IDCARD_type,IDCARD_NUM,user_type,tel,"
				+ "`year`,college,department,major,grade,class,culture_level,student_type,Education FROM "+
				viewName+";";
		//System.out.println(sql);
		try {
			ResultSet rs = this.excuteQuery(sql);
			studentList = new ArrayList<StudentModel>();
			while(rs.next()) {
				StudentModel student = (StudentModel)setValue(rs);
				studentList.add(student);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return studentList;
	}
	
	public Vector getComments() {
		Vector comments = null;
		comments= getComment("Base_Student");
		return comments;
	}
	
	
}

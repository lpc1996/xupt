package xupt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import xupt.mode.DepartmentModel;

public class DepartmentDao extends Dao {
	
	private final String tableName = "department";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
	
	private DepartmentModel setValue(ResultSet rs) {
		DepartmentModel department = new DepartmentModel();
		try {
			department.setId(rs.getString(1));
			department.setName(rs.getString(2));
			department.setCollegeId(rs.getString(3));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return department;
	}

	public List<DepartmentModel> getList() {
		// TODO Auto-generated method stub
		List<DepartmentModel> departmentList = null;
		String sql = "SELECT * FROM "+tableName+";";
		try {
			ResultSet rs = excuteQuery(sql);
			departmentList = new ArrayList<DepartmentModel>();
			while(rs.next()) {
				departmentList.add(setValue(rs));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
			e.printStackTrace();
		}finally {
			close();
		}
		return departmentList;
	}

	public Vector<String> getComments() {
		// TODO Auto-generated method stub
		Vector<String> comments = getComment(tableName);
		return comments;
	}
}

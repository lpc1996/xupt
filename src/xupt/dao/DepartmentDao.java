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
	
	public boolean insertData(DepartmentModel data) {
		boolean result = false;
		String sql = "INSERT INTO "+tableName+" VALUES('"+data.getId()+"','"+data.getName()+"','"+data.getCollegeId()+
				"');";
		try{
			if(excuteUpdate(sql) == 1 ) {
				result = true;
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
	
	public boolean updateData(DepartmentModel data,String id) {
		boolean result = false;
		String sql = "update "+tableName+" set id='"+data.getId()+"',name='"+data.getName()+"',college_id='"+
		data.getCollegeId()+"' WHERE id='"+id+"';";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean deleteData(String id) {
		boolean result = false;
		String sql = "DELETE FROM "+tableName+" WHERE id='"+id+"';";
		result = updateOperation(sql);
		return result;
	}

	public Vector<String> getComments() {
		// TODO Auto-generated method stub
		Vector<String> comments = getComment(tableName);
		return comments;
	}
}

package xupt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import xupt.mode.SemesterModel;

public class SemesterDao extends Dao {
	
	private final String tableName = "semester";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
	
	private SemesterModel setValue(ResultSet rs) {
		SemesterModel semester = null;
		try {
			semester = new SemesterModel();
			semester.setId(rs.getString(1));
			semester.setName(rs.getString(2));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			semester = null;
		}
		return semester;
	}
	
	public List<SemesterModel> getList(){
		String sql = "SELECT * FROM "+tableName;
		List<SemesterModel> semesterList = null;
		try {
			ResultSet rs = excuteQuery(sql);
			semesterList = new ArrayList<SemesterModel>();
			while(rs.next()) {
				semesterList.add(setValue(rs));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return semesterList;
	}
	
	public boolean insertData(SemesterModel semester) {
		boolean result = false;
		String sql = "INSERT INTO "+tableName+" VALUES('"+semester.getId()+"','"+semester.getName()+"');";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean updateData(SemesterModel semester,String id) {
		boolean result = false;
		String sql = "UPDATE "+tableName+" set id='"+semester.getId()+"',name='"+semester.getName()+"' WHERE id='"+
		id+"';";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean deleteData(String id) {
		boolean result = false;
		String sql = "DELETE FROM "+tableName+" WHERE id='"+id+"';";
		result = updateOperation(sql);
		return result;
	}
	
	public Vector<String> getComments(){
		Vector<String> comments = getComment(tableName);
		return comments;
	}
}

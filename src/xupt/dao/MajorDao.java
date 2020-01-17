package xupt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import xupt.mode.MajorModel;

public class MajorDao extends Dao {
	
	private final String tableName = "major";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
	
	private MajorModel setValue(ResultSet rs) {
		MajorModel major = null;
		try {
			major = new MajorModel();
			major.setId(rs.getString(1));
			major.setName(rs.getString(2));
			major.setCollegeId(rs.getString(3));
			major.setDepartmentId(rs.getString(4));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return major;
	}
	
	public List<MajorModel> getList(){
		List<MajorModel> majorList = new ArrayList<MajorModel>();
		String sql = "SELECT * FROM "+tableName+";";
		try {
			ResultSet rs = excuteQuery(sql);
			while(rs.next()) {
				MajorModel major = setValue(rs);
				if(major != null) {
					majorList.add(major);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return majorList;
	}
	
	public boolean insertData(MajorModel major) {
		boolean result = false;
		String sql = "INSERT INTO "+tableName+" VALUES('"+major.getId()+"','"+major.getName()+"','"+
		major.getCollegeId()+"','"+major.getDepartmentId()+"');";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean updateData(MajorModel major,String id) {
		boolean result = false;
		String sql = "UPDATE "+tableName+" set id='"+major.getId()+"',name='"+major.getName()+"',college_id='"+
		major.getCollegeId()+"',department_id='"+major.getDepartmentId()+"' WHERE id='"+id+"';";
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
		return getComment(tableName);
	}
}

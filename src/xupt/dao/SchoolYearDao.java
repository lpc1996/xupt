package xupt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import xupt.mode.SchoolYearModel;

public class SchoolYearDao extends Dao {
	
	private final String tableName = "school_year";
	
	public List<String> getIdAndNameList(){
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
	
	private SchoolYearModel setValue(ResultSet rs) {
		SchoolYearModel schoolYear = new SchoolYearModel();
		try {
			schoolYear.setId(rs.getString(1));
			schoolYear.setName(rs.getString(2));
			schoolYear.setBegin(rs.getString(3));
			schoolYear.setEnd(rs.getString(4));
		} catch (Exception e) {
			// TODO: handle exception
			schoolYear = null;
			e.printStackTrace();
		}
		return schoolYear;
	}
	
	public List<SchoolYearModel> getList(){
		List<SchoolYearModel> list = null;
		String sql = "SELECT * FROM "+tableName;
		try {
			ResultSet rs = excuteQuery(sql);
			list = new ArrayList<SchoolYearModel>();
			while(rs.next()) {
				SchoolYearModel schoolYear = setValue(rs);
				if(schoolYear != null) {
					list.add(schoolYear);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	public boolean insertData(SchoolYearModel schoolYear) {
		boolean result = false;
		String sql = "INSERT INTO "+tableName+" VALUES('"+schoolYear.getId()+"','"+schoolYear.getName()+"','"+
		schoolYear.getBegin()+"','"+schoolYear.getEnd()+"');";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean updateData(SchoolYearModel schoolYear,String id) {
		boolean result = false;
		String sql = "UPDATE "+tableName+" SET id='"+schoolYear.getId()+"',name='"+schoolYear.getName()+"',begin='"+
		schoolYear.getBegin()+"',end='"+schoolYear.getEnd()+"' WHERE id='"+id+"';";
		System.out.println(sql);
		result = updateOperation(sql);
		return result;
	}
	
	public boolean deleteDate(String id) {
		boolean result = false;
		String sql = "DELETE FROM "+tableName+" WHERE id='"+id+"';";
		result = updateOperation(sql);
		return result;
	}
	public Vector<String> getComments(){
		return getComment(tableName);
	}
}

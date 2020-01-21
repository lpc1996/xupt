package xupt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import xupt.mode.SchoolTremModel;

public class SchoolTremDao extends Dao {
	
	private final String tableName = "school_trem";
	
	public List<String> getIdAndNameList(){
		List<String> list = getIdAndNameList(tableName);
		return list;
		
	}
	
	private SchoolTremModel setValues(ResultSet rs) {
		SchoolTremModel schoolTrem = null;
		try {
			schoolTrem = new SchoolTremModel();
			schoolTrem.setId(rs.getString(1));
			schoolTrem.setName(rs.getString(2));
			schoolTrem.setSYId(rs.getString(3));
			schoolTrem.setBegin(rs.getString(4));
			schoolTrem.setEnd(rs.getString(5));
		} catch (Exception e) {
			// TODO: handle exception
			schoolTrem = null;
			e.printStackTrace();
		}
		return schoolTrem;
	}
	
	public List<SchoolTremModel> getList(){
		List<SchoolTremModel> schoolTremList = null;
		String sql = "SELECT * FROM "+tableName+";";
		try {
			ResultSet rs = excuteQuery(sql);
			schoolTremList = new ArrayList<SchoolTremModel>();
			while(rs.next()) {
				SchoolTremModel schoolTrem = setValues(rs);
				if(schoolTrem != null) {
					schoolTremList.add(schoolTrem);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
			e.printStackTrace();
		}finally {
			close();
		}
		return schoolTremList;
	}
	
	public boolean insertData(SchoolTremModel schoolTrem) {
		boolean result = false;
		String sql = "INSERT INTO "+tableName+" VALUES('"+schoolTrem.getId()+"','"+schoolTrem.getName()+"','"+
		schoolTrem.getSYId()+"','"+schoolTrem.getBegin()+"','"+schoolTrem.getEnd()+"');";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean updateData(SchoolTremModel schoolTrem,String id) {
		boolean result = false;
		String sql = "UPDATE "+tableName+" set id='"+schoolTrem.getId()+"',name='"+schoolTrem.getName()+
				"',school_year='"+schoolTrem.getSYId()+"',begin='"+schoolTrem.getBegin()+"',end='"+schoolTrem.getEnd()+
				"' WHERE id='"+id+"';";
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

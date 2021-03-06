package xupt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import xupt.mode.CollegeModel;

/**
 * @author ���F-�h��
 *
 */
public class CollegeDao extends Dao {
	
	private final String tableName = "college";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
	
	private CollegeModel setValue(ResultSet rs) {
		CollegeModel college = null;
		try {
			college = new CollegeModel();
			college.setId(rs.getString(1));
			college.setName(rs.getString(2));
			college.setPresidentId(rs.getString(3));
			college.setVicePresidentId(rs.getString(4));
			college.setInformation(rs.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			college = null;
		} 
		return college;
	}
	
	public List<CollegeModel> getList(){
		List<CollegeModel> collegeList = null;
		String sql = "SELECT * FROM "+tableName+";";
		try {
			ResultSet rs = this.excuteQuery(sql);
			collegeList = new ArrayList<CollegeModel>();
			while(rs.next()) {
				CollegeModel college = setValue(rs);
				if(college != null) {
					collegeList.add(college);
				}else {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			collegeList = null;
			e.printStackTrace();
		}finally {
			close();
		}
		return collegeList;
	}
	
	public Vector<String> getComments(){
		Vector<String> comments = getComment(tableName);
		return comments;
	}
	
	public boolean insertData(CollegeModel college) {
		boolean result = false;
		String sql = "INSERT INTO "+tableName+" VALUES('"+college.getId()+"','"+college.getName()+"','"+
		college.getPresidentId()+"','"+college.getVicePresidentId()+"','"+college.getInformation()+"');";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean updateData(CollegeModel college,String id) {
		boolean result = false;
		String sql = "UPDATE "+tableName+" SET id='"+college.getId()+"',name='"+college.getName()+
				"',presidentId='"+college.getPresidentId()+"',vicePresidentId='"+college.getVicePresidentId()+
				"',information='"+college.getInformation()+"' WHERE id='"+id+"';";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean deleteData(String id) {
		boolean result = false;
		String sql = "DELETE FROM "+tableName+" WHERE id='"+id+"';";
		result = updateOperation(sql);
		return result;
	}
}

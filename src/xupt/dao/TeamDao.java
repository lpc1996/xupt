package xupt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import xupt.mode.TeamModel;

public class TeamDao extends Dao {

	private final String tableName = "team";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
	
	private TeamModel setValue(ResultSet rs) {
		TeamModel team = null;
		try {
			team = new TeamModel();
			team.setId(rs.getString(1));
			team.setName(rs.getString(2));
			team.setNumber(rs.getInt(3));
			team.setCollegeId(rs.getString(4));
			team.setDepartmentId(rs.getString(5));
			team.setMajorId(rs.getString(6));
			team.setSemester(rs.getString(7));
		} catch (Exception e) {
			// TODO: handle exception
			team = null;
		}
		return team;
	}
	
	public List<TeamModel> getList(){
		List<TeamModel> list = null;
		String sql = "SELECT * FROM "+tableName;
		try {
			ResultSet rs = excuteQuery(sql);
			list = new ArrayList<TeamModel>();
			while(rs.next()) {
				TeamModel team = setValue(rs);
				if(team != null) {
					list.add(team);
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
	
	public boolean insertData(TeamModel team) {
		boolean result = false;
		String sql = "INSERT INTO "+tableName+" VALUES('"+team.getId()+"','"+team.getName()+"','"+team.getNumber()+
				"','"+team.getCollegeId()+"','"+team.getDepartmentId()+"','"+team.getMajorId()+"','"+
				team.getSemester()+"');";
		result = updateOperation(sql);
		return result;
	}
	
	public boolean updateData(TeamModel team,String id) {
		boolean result = false;
		String sql = "UPDATE "+tableName+" SET id='"+team.getId()+"',name='"+team.getName()+"',number="+
		team.getNumber()+",college_id='"+team.getCollegeId()+"',department_id='"+team.getDepartmentId()+"',major_id='"+
				team.getMajorId()+"',semester='"+team.getSemester()+"' WHERE id='"+id+"';";
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

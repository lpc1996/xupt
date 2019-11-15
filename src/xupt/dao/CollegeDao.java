package xupt.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CollegeDao extends Dao {
	
	private final String tableName = "college";
	
	public List getIdAndNameList() {
		List list = null;
		String sql = "SELECT id,name FROM "+tableName+";";
		try {
			ResultSet rs = this.excuteQuery(sql);
			list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("id")+" "+rs.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			close();
		}
		return list;
	}
}

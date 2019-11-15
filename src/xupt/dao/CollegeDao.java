package xupt.dao;

import java.util.List;

public class CollegeDao extends Dao {
	
	private final String tableName = "college";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
}

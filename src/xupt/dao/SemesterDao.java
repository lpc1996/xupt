package xupt.dao;

import java.util.List;

public class SemesterDao extends Dao {
	
	private final String tableName = "semester";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
}

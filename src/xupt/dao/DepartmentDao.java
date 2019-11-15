package xupt.dao;

import java.util.List;

public class DepartmentDao extends Dao {
	
	private final String tableName = "department";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
}

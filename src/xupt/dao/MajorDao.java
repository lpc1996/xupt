package xupt.dao;

import java.util.List;

public class MajorDao extends Dao {
	
	private final String tableName = "major";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
}

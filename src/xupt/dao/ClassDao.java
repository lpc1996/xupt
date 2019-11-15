package xupt.dao;

import java.util.List;

public class ClassDao extends Dao {

	private final String tableName = "xclass";
	
	public List<String> getIdAndNameList() {
		List<String> list = getIdAndNameList(tableName);
		return list;
	}
}

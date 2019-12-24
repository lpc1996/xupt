package xupt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import xupt.mode.BaseInfoModel;
import xupt.util.Tools;

public class BaseInfoDao extends Dao{
	
	private final String tableName = "base_info";
	private boolean isOk;

	public BaseInfoDao() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public BaseInfoModel getBaseInfo(String id) {
		BaseInfoModel baseInfo = null;
		String sql="SELECT * FROM base_info where user_id='"+id+"'";
		try {
			ResultSet rs = this.excuteQuery(sql);
			if(rs.next()) {
				baseInfo=(BaseInfoModel)setValue(rs);
			}
		} catch (Exception e) {
			// TODO: handle exception
			baseInfo=null;
		}finally {
			this.close();
		}
		return baseInfo;
	}

//	@Override
//	public List getList( ) {
//		// TODO Auto-generated method stub
//		List baseInfoList = null;
//		String sql = "select * from "+tableName;
//		baseInfoList = getData(sql);
//		
//		return baseInfoList;
//	}
	
	public List getList( String type) {
		List baseInfoList = null;
		String sql = setSql( type);
		baseInfoList=getData(sql);
		
		return baseInfoList;
	}
	
	private String setSql( String type) {
		String sql = new String();
		if(type.equals("")) {
			sql = "select * from "+tableName+";";
		}
		sql = "select * from "+tableName+" where user_type = '"+type+"';";
		return sql;
	}
	
	private List<BaseInfoModel> getData(String sql) {
		List<BaseInfoModel> baseInfoList = null;
		try {
			Vector<String> column = this.getColumnName("base_info");
			ResultSet rs = this.excuteQuery(sql);
			baseInfoList = new ArrayList<>();
			while(rs.next()) {
				BaseInfoModel baseInfo = (BaseInfoModel) setValue(rs);
				if(baseInfo != null) {
					baseInfoList.add(baseInfo);
				}
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.close();
		}
		return baseInfoList;
	}

	public boolean deleteData(String id) {
		// TODO Auto-generated method stub
		isOk = false;
		String sql = "DELETE FROM base_info where user_id='"+id+"';";
		try {
			if(this.excuteUpdate(sql) == 1) {
				isOk = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.close();
		}
		return isOk;
	}

	public boolean addData(Object data) {
		// TODO Auto-generated method stub
		isOk = false;
		BaseInfoModel baseInfoModel = (BaseInfoModel)data;
		String sql = "INSERT INTO base_info values(null,'"+baseInfoModel.getId()+"','"
		+baseInfoModel.getName()+"','"+baseInfoModel.getFormarName()+"','"+	baseInfoModel.getSex()+"',"
				+baseInfoModel.getAge()+",'"+baseInfoModel.getNativePlace()+"','"+baseInfoModel.getIDCARDTYPE()
				+"','"+baseInfoModel.getIDCARDNUM()+"','"+baseInfoModel.getType()+"','"
				+baseInfoModel.getTel()+"');";
		try {
			if(this.excuteUpdate(sql) == 1) {
				isOk = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			isOk = false;
		}finally {
			this.close();
		}
		return isOk;
	}

	public boolean updateData(BaseInfoModel data, String id) {
		// TODO Auto-generated method stub
		isOk = false;
		String sql = "update base_info set user_id='"+data.getId()+"',name='"+data.getName()
		+"',formar_name='"+data.getFormarName()+"',sex='"+data.getSex()+"',age="+data.getAge()
		+",native_place='"+data.getNativePlace()+"',IDCARD_type='"+data.getIDCARDTYPE()+"',IDCARD_NUM='"
		+data.getIDCARDNUM()+"',tel='"+data.getTel()+"' where user_id='"+id+"';";
		try {
			if(this.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(sql);
			isOk = false;
		}finally {
			this.close();
		}
		return isOk;
	}

	public Object setValue(ResultSet rs) {
		// TODO Auto-generated method stub
		BaseInfoModel base = null;
		try {
			base = new BaseInfoModel();
			base.setId(rs.getString("user_id"));
			base.setName(rs.getString("name"));
			base.setFormarName(rs.getString("formar_name"));
			base.setAge(rs.getInt("age"));
			base.setSex(rs.getString("sex"));
			base.setNativePlace(rs.getString("native_place"));
			base.setIDCARDTYPE(rs.getString("IDCARD_type"));
			base.setIDCARDNUM(rs.getString("IDCARD_NUM"));
			base.setType(rs.getString("user_type"));
			base.setTel(rs.getString("tel"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base;
	}

	public BaseInfoModel getUser(String id) {
		// TODO Auto-generated method stub
		BaseInfoModel base = null;
		String sql = "SELECT * FROM "+tableName+" where user_id='"+id+"';";
		try {
			ResultSet rs = this.excuteQuery(sql);
			if(rs.next()) {
				base = (BaseInfoModel)setValue(rs);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			base = null;
		}finally {
			this.close();
		}
		return base;
	}
	
	//获取字段类型为enum的字段所有可能的类型值
	public List<String> getEnumValue(String columnName){
		List<String> enumValue = null;
		String sql = "SELECT column_type FROM information_schema.COLUMNS WHERE TABLE_SCHEMA="
				+ "'xupt' AND DATA_TYPE='enum' AND TABLE_NAME='"+tableName+"' AND COLUMN_NAME='"+columnName+
				"';";
		try {
			ResultSet rs = this.excuteQuery(sql);
			if(rs.next()) {
				enumValue = new Tools().splitEnumValue(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			this.close();
		}
		return enumValue;
	}
	
	public static void main(String[] argv) {
		BaseInfoDao baseDao = new BaseInfoDao();
		System.out.println(baseDao.getEnumValue("sex"));
		
	}

}

package xupt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xupt.mode.BaseInfoModel;

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
			System.out.println(sql);
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
	
	private List getData(String sql) {
		List baseInfoList = null;
		try {
			String [] column = this.getColumnName("base_info");
			ResultSet rs = this.excuteQuery(sql);
			baseInfoList = new ArrayList();
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

	public boolean updateData(Object data, String id) {
		// TODO Auto-generated method stub
		isOk = false;
		BaseInfoModel baseInfo = (BaseInfoModel)data;
		String sql = "update base_info set user_id='"+baseInfo.getId()+"',name='"+baseInfo.getName()
		+"',formar_name='"+baseInfo.getFormarName()+"',sex='"+baseInfo.getSex()+"',age="+baseInfo.getAge()
		+",native_place='"+baseInfo.getNativePlace()+"',IDCARD_type='"+baseInfo.getIDCARDTYPE()+"',IDCARD_NUM='"
		+baseInfo.getIDCARDNUM()+"',tel='"+baseInfo.getTel()+"' where user_id='"+id+"';";
		try {
			if(this.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

}

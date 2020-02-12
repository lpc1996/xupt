package xupt.mode;

public class BaseInfoModel {
	
	private String id;
	private String name;
	private String formarName;
	private String sex;
	private int age;
	private String nativePlace;
	private String IDCARDTYPE;
	private String IDCARDNUM;
	private String tel;
	private String type;
	
	public BaseInfoModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id 编号
	 * @param name 姓名
	 * @param formarName 曾用名
	 * @param sex 性别
	 * @param age 年龄
	 * @param nativePlace 籍贯
	 * @param iDCARDTYPE 证件类别
	 * @param iDCARDNUM 证件号码
	 * @param type 用户类型
	 */
	public BaseInfoModel(String id, String name, String formarName, String sex, int age, String nativePlace,
			String iDCARDTYPE, String iDCARDNUM, String type,String tel) {
		super();
		this.id = id;
		this.name = name;
		this.formarName = formarName;
		this.sex = sex;
		this.age = age;
		this.nativePlace = nativePlace;
		IDCARDTYPE = iDCARDTYPE;
		IDCARDNUM = iDCARDNUM;
		this.tel = tel;
		this.type = type;
	}

	/**
	 * 获取编号
	 * @return 返回ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 获取姓名
	 * @return 返回姓名
	 */
	public String getName() {
		return name;
	}

	public String getFormarName() {
		return formarName;
	}

	public String getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public String getIDCARDTYPE() {
		return IDCARDTYPE;
	}

	public String getIDCARDNUM() {
		return IDCARDNUM;
	}

	public String getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFormarName(String formarName) {
		this.formarName = formarName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public void setIDCARDTYPE(String iDCARDTYPE) {
		IDCARDTYPE = iDCARDTYPE;
	}

	public void setIDCARDNUM(String iDCARDNUM) {
		IDCARDNUM = iDCARDNUM;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}

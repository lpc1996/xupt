package xupt.mode;

public class StudentModel {
	
	private BaseInfoModel baseInfo;
	private String id;
	private String year;
	private String college;
	private String department;
	private String major;
	private String grade;
	private String classId;
	private String culture_level;
	private String type;
	private String education;

	public StudentModel() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getYear() {
		return year;
	}

	public String getCollege() {
		return college;
	}

	public String getDepartment() {
		return department;
	}

	public String getMajor() {
		return major;
	}

	public String getGrade() {
		return grade;
	}

	public String getClassId() {
		return classId;
	}

	public String getCulture_level() {
		return culture_level;
	}

	public String getType() {
		return type;
	}

	public String getEducation() {
		return education;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public void setCulture_level(String culture_level) {
		this.culture_level = culture_level;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public BaseInfoModel getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(BaseInfoModel baseInfo) {
		this.baseInfo = baseInfo;
	}

}

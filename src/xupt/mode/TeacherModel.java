package xupt.mode;

public class TeacherModel {
	
	private BaseInfoModel base;
	private String college;
	private String department;
	private String level;
	private String education;
	private String year;

	public TeacherModel() {
		// TODO Auto-generated constructor stub
		super();
		base = new BaseInfoModel();
	}
	
	public String getCollege() {
		return college;
	}

	public String getDepartment() {
		return department;
	}

	public String getLevel() {
		return level;
	}

	public String getEducation() {
		return education;
	}

	public String getYear() {
		return year;
	}
	
	public void setCollege(String college) {
		this.college = college;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BaseInfoModel getBaseInfo() {
		return base;
	}

	public void setBaseInfo(BaseInfoModel base) {
		this.base = base;
	}

}

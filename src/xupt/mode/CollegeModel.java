package xupt.mode;

public class CollegeModel {
	
	private String id;
	private String name;
	private String presidentId;//Ժ��ID
	private String vicePresidentId;//��Ժ��ID
	private String information;//ѧԺ���

	
	public CollegeModel() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPresidentId() {
		return presidentId;
	}

	public void setPresidentId(String presidentId) {
		this.presidentId = presidentId;
	}

	public String getVicePresidentId() {
		return vicePresidentId;
	}

	public void setVicePresidentId(String vicePresidentId) {
		this.vicePresidentId = vicePresidentId;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}

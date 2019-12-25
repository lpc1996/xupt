package xupt.mode;

public class CollegeModel {
	
	private String id;
	private String name;
	private String presidentId;//院长ID
	private String vicePresidentId;//副院长ID

	/**
	 * @param id
	 * @param name
	 * @param president
	 * @param introduce
	 */
	public CollegeModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
}

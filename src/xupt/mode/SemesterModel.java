package xupt.mode;

public class SemesterModel {
	
	private String id;
	private String name;
	
	public SemesterModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public SemesterModel() {
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

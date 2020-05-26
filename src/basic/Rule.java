package basic;

public abstract class Rule {
	private String description;
	
	public Rule (String description) {
		this.description= description;
	}
	public abstract void applyRule(DataSet array[]);
	public String getDescription() {
		return description;
	}
	
}

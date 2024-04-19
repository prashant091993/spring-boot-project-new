package global.Template;



public class TemplateDesignerVO extends ValueObject {
	private String paraID;
	private String controlID;
	private String allPropertyIDs; 
	private String allPropertyValues;
	private String allPropertyValues2;
	private String allPropertyValues3;
	private String paraLocation;
	public String getParaID() {
		return paraID;
	}
	public void setParaID(String paraID) {
		this.paraID = paraID;
	}
	public String getControlID() {
		return controlID;
	}
	public void setControlID(String controlID) {
		this.controlID = controlID;
	}
	public String getAllPropertyIDs() {
		return allPropertyIDs;
	}
	public void setAllPropertyIDs(String allPropertyIDs) {
		this.allPropertyIDs = allPropertyIDs;
	}
	public String getAllPropertyValues() {
		return allPropertyValues;
	}
	public void setAllPropertyValues(String allPropertyValues) {
		this.allPropertyValues = allPropertyValues;
	}
	public String getAllPropertyValues2() {
		return allPropertyValues2;
	}
	public void setAllPropertyValues2(String allPropertyValues2) {
		this.allPropertyValues2 = allPropertyValues2;
	}
	public String getAllPropertyValues3() {
		return allPropertyValues3;
	}
	public void setAllPropertyValues3(String allPropertyValues3) {
		this.allPropertyValues3 = allPropertyValues3;
	}
	public String getParaLocation() {
		return paraLocation;
	}
	public void setParaLocation(String paraLocation) {
		this.paraLocation = paraLocation;
	}
	
}

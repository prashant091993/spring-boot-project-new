package global.Template;

public class OTTemplateControlVO extends ValueObject
{
	String controlId;
	String controlName;
	String controlDisplayName;
	String controlHtmlTag;
	String controltldImport;
	String controlstructTag;
	String controlImage;
	String isActive;	
	String isVisble;
	String allPropertyIds;
	String controlType;
	String defaultControlpropertyIds;
	String defaultControlpropertyValues;
	String isUsedVisDep;
	
	public String getDefaultControlpropertyIds() {
		return defaultControlpropertyIds;
	}
	public void setDefaultControlpropertyIds(String defaultControlpropertyIds) {
		this.defaultControlpropertyIds = defaultControlpropertyIds;
	}
	public String getDefaultControlpropertyValues() {
		return defaultControlpropertyValues;
	}
	public void setDefaultControlpropertyValues(String defaultControlpropertyValues) {
		this.defaultControlpropertyValues = defaultControlpropertyValues;
	}
	public String getControlId() {
		return controlId;
	}
	public void setControlId(String controlId) {
		this.controlId = controlId;
	}
	public String getControlName() {
		return controlName;
	}
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	public String getControlDisplayName() {
		return controlDisplayName;
	}
	public void setControlDisplayName(String controlDisplayName) {
		this.controlDisplayName = controlDisplayName;
	}
	public String getControlHtmlTag() {
		return controlHtmlTag;
	}
	public void setControlHtmlTag(String controlHtmlTag) {
		this.controlHtmlTag = controlHtmlTag;
	}
	public String getControltldImport() {
		return controltldImport;
	}
	public void setControltldImport(String controltldImport) {
		this.controltldImport = controltldImport;
	}
	public String getControlstructTag() {
		return controlstructTag;
	}
	public void setControlstructTag(String controlstructTag) {
		this.controlstructTag = controlstructTag;
	}
	public String getControlImage() {
		return controlImage;
	}
	public void setControlImage(String controlImage) {
		this.controlImage = controlImage;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsVisble() {
		return isVisble;
	}
	public void setIsVisble(String isVisble) {
		this.isVisble = isVisble;
	}
	public String getAllPropertyIds() {
		return allPropertyIds;
	}
	public void setAllPropertyIds(String allPropertyIds) {
		this.allPropertyIds = allPropertyIds;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getIsUsedVisDep() {
		return isUsedVisDep;
	}
	public void setIsUsedVisDep(String isUsedVisDep) {
		this.isUsedVisDep = isUsedVisDep;
	}
}
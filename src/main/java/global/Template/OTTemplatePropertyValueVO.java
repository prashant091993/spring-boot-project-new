package global.Template;

public class OTTemplatePropertyValueVO extends ValueObject
{
	String propertyValue;
	String propertyDisplayValue;
	String isDefault;
	String isPropertyValueActive;
	String sqno;
	String propertyId;
	
	public String getSqno() {
		return sqno;
	}
	public void setSqno(String sqno) {
		this.sqno = sqno;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getPropertyDisplayValue() {
		return propertyDisplayValue;
	}
	public void setPropertyDisplayValue(String propertyDisplayValue) {
		this.propertyDisplayValue = propertyDisplayValue;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getIsPropertyValueActive() {
		return isPropertyValueActive;
	}
	public void setIsPropertyValueActive(String isPropertyValueActive) {
		this.isPropertyValueActive = isPropertyValueActive;
	}	
}
package global.Template;

import javax.servlet.http.HttpServletRequest;



public class TemplatePropertyMstFB 
{
	String hmode;
	String msg;
	String initMode;
	String chk;
	String propertyId;
	String propertyName;
	String propertyDisplayName;
	String isActive;	
	String propertyType;
	String[] sqno;
	String[] propertyValue;
	String[] propertyDisplayValue;
	String isDefault;
	String[] isPropertyValueActive;
	String isVisible;
	String parentId;

	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getInitMode() {
		return initMode;
	}
	public void setInitMode(String initMode) {
		this.initMode = initMode;
	}	
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyDisplayName() {
		return propertyDisplayName;
	}
	public void setPropertyDisplayName(String propertyDisplayName) {
		this.propertyDisplayName = propertyDisplayName;
	}
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String[] getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String[] propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String[] getPropertyDisplayValue() {
		return propertyDisplayValue;
	}
	public void setPropertyDisplayValue(String[] propertyDisplayValue) {
		this.propertyDisplayValue = propertyDisplayValue;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String[] getIsPropertyValueActive() {
		return isPropertyValueActive;
	}
	public void setIsPropertyValueActive(String[] isPropertyValueActive) {
		this.isPropertyValueActive = isPropertyValueActive;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String[] getSqno() {
		return sqno;
	}
	public void setSqno(String[] sqno) {
		this.sqno = sqno;
	}
	
}
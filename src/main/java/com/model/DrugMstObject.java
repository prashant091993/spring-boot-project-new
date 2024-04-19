package com.model;

public class DrugMstObject 
{
	private String hstnum_ITEM_ID ="";
	private String hststr_ITEM_NAME ="";
	private String gnum_HOSPITAL_CODE="";
	private String hstnum_SL_NO="";
	private String gstr_INVENTORY_UNITNAME="";
	private String gnum_INVENTORY_UNITID="";		
	private String hstnum_GROUP_ID="";
	private String sstnum_ITEM_CAT_NO="";		
	
	public String getHSTNUM_GROUP_ID() {
		return hstnum_GROUP_ID;
	}
	public void setHSTNUM_GROUP_ID(String hstnum_group_id) {
		this.hstnum_GROUP_ID = hstnum_group_id;
	}
	public String getSSTNUM_ITEM_CAT_NO() {
		return sstnum_ITEM_CAT_NO;
	}
	public void setSSTNUM_ITEM_CAT_NO(String sstnum_item_cat_no) {
		this.sstnum_ITEM_CAT_NO = sstnum_item_cat_no;
	}
	public String getHSTNUM_SL_NO() {
		return hstnum_SL_NO;
	}
	public void setHSTNUM_SL_NO(String hSTNUM_SL_NO) {
		hstnum_SL_NO = hSTNUM_SL_NO;
	}
	public String getGNUM_HOSPITAL_CODE() {
		return gnum_HOSPITAL_CODE;
	}
	public void setGNUM_HOSPITAL_CODE(String gNUM_HOSPITAL_CODE) {
		gnum_HOSPITAL_CODE = gNUM_HOSPITAL_CODE;
	}
	public String getGNUM_INVENTORY_UNITID() {
		return gnum_INVENTORY_UNITID;
	}
	public void setGNUM_INVENTORY_UNITID(String gNUM_INVENTORY_UNITID) {
		gnum_INVENTORY_UNITID = gNUM_INVENTORY_UNITID;
	}
	public String getGSTR_INVENTORY_UNITNAME() {
		return gstr_INVENTORY_UNITNAME;
	}
	public void setGSTR_INVENTORY_UNITNAME(String gSTR_INVENTORY_UNITNAME) {
		gstr_INVENTORY_UNITNAME = gSTR_INVENTORY_UNITNAME;
	}
	public String getHSTNUM_ITEM_ID() {
		return hstnum_ITEM_ID;
	}
	public void setHSTNUM_ITEM_ID(String hSTNUM_ITEM_ID) {
		hstnum_ITEM_ID = hSTNUM_ITEM_ID;
	}
	public String getHSTSTR_ITEM_NAME() {
		return hststr_ITEM_NAME;
	}
	public void setHSTSTR_ITEM_NAME(String hSTSTR_ITEM_NAME) {
		hststr_ITEM_NAME = hSTSTR_ITEM_NAME;
	}
		
}

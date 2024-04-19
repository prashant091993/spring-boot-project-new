package com.model;

public class ItemTypeMstObject 
{
	private String HSTNUM_ITEMTYPE_ID;
	private String HSTSTR_ITEMTYPE_NAME;
	private String GNUM_HOSPITAL_CODE;
	private String SSTNUM_ITEM_CAT_NO;
	
	public String getHSTNUM_ITEMTYPE_ID() 
	{
		return HSTNUM_ITEMTYPE_ID;
	}
	public void setHSTNUM_ITEMTYPE_ID(String hSTNUM_ITEMTYPE_ID) 
	{
		HSTNUM_ITEMTYPE_ID = hSTNUM_ITEMTYPE_ID;
	}
	public String getHSTSTR_ITEMTYPE_NAME() 
	{
		return HSTSTR_ITEMTYPE_NAME;
	}
	public void setHSTSTR_ITEMTYPE_NAME(String hSTSTR_ITEMTYPE_NAME) 
	{
		HSTSTR_ITEMTYPE_NAME = hSTSTR_ITEMTYPE_NAME;
	}
	public String getGNUM_HOSPITAL_CODE() {
		return GNUM_HOSPITAL_CODE;
	}
	public void setGNUM_HOSPITAL_CODE(String gNUM_HOSPITAL_CODE) {
		GNUM_HOSPITAL_CODE = gNUM_HOSPITAL_CODE;
	}
	public String getSSTNUM_ITEM_CAT_NO() {
		return SSTNUM_ITEM_CAT_NO;
	}
	public void setSSTNUM_ITEM_CAT_NO(String sSTNUM_ITEM_CAT_NO) {
		SSTNUM_ITEM_CAT_NO = sSTNUM_ITEM_CAT_NO;
	}
	

}

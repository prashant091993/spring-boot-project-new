package com.model;

public class LoginObject 
{

	private String GNUM_USERID = "";
	private String GSTR_USER_NAME = "";
	private String GSTR_PASSWORD = "";
	private String GNUM_USER_SEATID = "";
	private String PSRSTR_EMP_NO = "";
	private String GNUM_HOSPITAL_CODE = "";
	private String GNUM_USERLEVEL = "";
	private String GSTR_USR_NAME = "";
	private String GNUM_DESIGNATION = "";
	private String GNUM_MOBILE_NUMBER = "";
	private String GSTR_EMAIL_ID = "";
	private String GNUM_USER_TYPE_ID = "";
	private String GNUM_USER_TYPE = "";
	private String GNUM_ISLOCK = "0";
	private String LOGIN_MSG = "";
	private String LOGIN_FLG = "0"; // 1 - PWD Match , 2 - PWD Not Match ,3 - PWD Lock 
	
	
	
	public String getLOGIN_FLG() {
		return LOGIN_FLG;
	}
	public void setLOGIN_FLG(String lOGIN_FLG) {
		LOGIN_FLG = lOGIN_FLG;
	}
	public String getLOGIN_MSG() {
		return LOGIN_MSG;
	}
	public void setLOGIN_MSG(String lOGIN_MSG) {
		LOGIN_MSG = lOGIN_MSG;
	}
	public String getGNUM_USER_TYPE_ID() {
		return GNUM_USER_TYPE_ID;
	}
	public void setGNUM_USER_TYPE_ID(String gNUM_USER_TYPE_ID) {
		GNUM_USER_TYPE_ID = gNUM_USER_TYPE_ID;
	}
	public String getGNUM_USER_TYPE() {
		return GNUM_USER_TYPE;
	}
	public void setGNUM_USER_TYPE(String gNUM_USER_TYPE) {
		GNUM_USER_TYPE = gNUM_USER_TYPE;
	}
	public String getGNUM_ISLOCK() {
		return GNUM_ISLOCK;
	}
	public void setGNUM_ISLOCK(String gNUM_ISLOCK) {
		GNUM_ISLOCK = gNUM_ISLOCK;
	}
	public String getGNUM_USERID() {
		return GNUM_USERID;
	}
	public void setGNUM_USERID(String gNUM_USERID) {
		GNUM_USERID = gNUM_USERID;
	}
	public String getGSTR_USER_NAME() {
		return GSTR_USER_NAME;
	}
	public void setGSTR_USER_NAME(String gSTR_USER_NAME) {
		GSTR_USER_NAME = gSTR_USER_NAME;
	}
	public String getGSTR_PASSWORD() {
		return GSTR_PASSWORD;
	}
	public void setGSTR_PASSWORD(String gSTR_PASSWORD) {
		GSTR_PASSWORD = gSTR_PASSWORD;
	}
	public String getGNUM_USER_SEATID() {
		return GNUM_USER_SEATID;
	}
	public void setGNUM_USER_SEATID(String gNUM_USER_SEATID) {
		GNUM_USER_SEATID = gNUM_USER_SEATID;
	}
	public String getPSRSTR_EMP_NO() {
		return PSRSTR_EMP_NO;
	}
	public void setPSRSTR_EMP_NO(String pSRSTR_EMP_NO) {
		PSRSTR_EMP_NO = pSRSTR_EMP_NO;
	}
	public String getGNUM_HOSPITAL_CODE() {
		return GNUM_HOSPITAL_CODE;
	}
	public void setGNUM_HOSPITAL_CODE(String gNUM_HOSPITAL_CODE) {
		GNUM_HOSPITAL_CODE = gNUM_HOSPITAL_CODE;
	}
	public String getGNUM_USERLEVEL() {
		return GNUM_USERLEVEL;
	}
	public void setGNUM_USERLEVEL(String gNUM_USERLEVEL) {
		GNUM_USERLEVEL = gNUM_USERLEVEL;
	}
	public String getGSTR_USR_NAME() {
		return GSTR_USR_NAME;
	}
	public void setGSTR_USR_NAME(String gSTR_USR_NAME) {
		GSTR_USR_NAME = gSTR_USR_NAME;
	}
	public String getGNUM_DESIGNATION() {
		return GNUM_DESIGNATION;
	}
	public void setGNUM_DESIGNATION(String gNUM_DESIGNATION) {
		GNUM_DESIGNATION = gNUM_DESIGNATION;
	}
	public String getGNUM_MOBILE_NUMBER() {
		return GNUM_MOBILE_NUMBER;
	}
	public void setGNUM_MOBILE_NUMBER(String gNUM_MOBILE_NUMBER) {
		GNUM_MOBILE_NUMBER = gNUM_MOBILE_NUMBER;
	}
	public String getGSTR_EMAIL_ID() {
		return GSTR_EMAIL_ID;
	}
	public void setGSTR_EMAIL_ID(String gSTR_EMAIL_ID) {
		GSTR_EMAIL_ID = gSTR_EMAIL_ID;
	}

	
	
	
	
}

package global.Template;

/**
 * UserVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting user information in the DB tables. 
 * @author AHIS
 */
public class UserVO extends ValueObject
{
	private TransactionVO transactionInfo = new TransactionVO();
	private String userId;
	private String userSeatId;
	private String userName;
	private String seatId;
	private String userType;
	private String userLevel;
	private String userEmpID;
	private String tariffID;
	private String hospitalCode;
	private String ipAddress;
	private String moduleId;
	private String districtCode;
	private String usrName;
	private String designation;
	private String emailId;
	private String mobileNo;
	private String swapcardNo;
	private HospitalMstVO strHospitalMstVo;
	private String districtName;
	private String checkBackDateDayEndFlag;
	
	private String smsWebServiceURL;
	private String smsUserName;
	private String smsPassword;
	private String smsSecureKey;
	private String smsSenderId;
	private String sslVer;
	private String smsServiceFlag;//1-Up,0-Down
	
	private String smtpMailServerURL;
	private String smtpHost;
	private String smtpPort;
	private String mailUserId;
	private String mailPassword;
	private String mailServiceFlag;//1-Up,0-Down
	
	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	/**
	 * Retrieves tariffID.
	 * @return Value of tariffID.	
	 */
	public String getTariffID()
	{
		return tariffID;
	}

	/**
	 * Sets tariffID.
	 * @param tariffID
	 */
	public void setTariffID(String tariffID)
	{
		this.tariffID = tariffID;
	}



	/**
	 * Sets userType.
	 * @param userType
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	/**
	 * Sets seatId.
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Sets userName.
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * Sets userName.
	 * @param userName
	 */
	public void setUserLevel(String userLevel)
	{
		this.userLevel = userLevel;
	}

	/**
	 * Sets userId.
	 * @param userId
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * Sets userEmpID.
	 * @param userEmpID
	 */
	public void setUserEmpID(String userEmpID)
	{
		this.userEmpID = userEmpID;
	}

	/**
	 * Retrieves userType.
	 * @return Value of userType.	
	 */
	public String getUserType()
	{
		return userType;
	}

	/**
	 * Retrieves seatId.
	 * @return Value of seatId.	
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Retrieves userName.
	 * @return Value of userName.	
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Retrieves userName.
	 * @return Value of userName.	
	 */
	public String getUserLevel()
	{
		return userLevel;
	}

	/**
	 * Retrieves userId.
	 * @return Value of userId.	
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Retrieves userEmpID.
	 * @return Value of userEmpID.	
	 */
	public String getUserEmpID()
	{
		return userEmpID;
	}

	/**
	 * Retieves User Seat Id to which it is mapped
	 * @return
	 */
	public String getUserSeatId()
	{
		return userSeatId;
	}

	public void setUserSeatId(String userSeatId)
	{
		this.userSeatId = userSeatId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public TransactionVO getTransactionInfo() {
		return transactionInfo;
	}

	public void setTransactionInfo(TransactionVO transactionInfo) {
		this.transactionInfo = transactionInfo;
	}

	public String getDistrictCode()
	{
		return districtCode;
	}

	public void setDistrictCode(String districtCode)
	{
		this.districtCode = districtCode;
	}

	public String getUsrName()
	{
		return usrName;
	}

	public void setUsrName(String usrName)
	{
		this.usrName = usrName;
	}

	public String getDesignation()
	{
		return designation;
	}

	public void setDesignation(String designation)
	{
		this.designation = designation;
	}

	public String getEmailId()
	{
		return emailId;
	}

	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}

	public String getMobileNo()
	{
		return mobileNo;
	}

	public void setMobileNo(String mobileNo)
	{
		this.mobileNo = mobileNo;
	}

	public String getSwapcardNo()
	{
		return swapcardNo;
	}

	public void setSwapcardNo(String swapcardNo)
	{
		this.swapcardNo = swapcardNo;
	}

	public HospitalMstVO getStrHospitalMstVo() {
		return strHospitalMstVo;
	}

	public void setStrHospitalMstVo(HospitalMstVO strHospitalMstVo) {
		this.strHospitalMstVo = strHospitalMstVo;
	}

	public String getDistrictName()
	{
		return districtName;
	}

	public void setDistrictName(String districtName)
	{
		this.districtName = districtName;
	}

	public String getCheckBackDateDayEndFlag() {
		return checkBackDateDayEndFlag;
	}

	public void setCheckBackDateDayEndFlag(String checkBackDateDayEndFlag) {
		this.checkBackDateDayEndFlag = checkBackDateDayEndFlag;
	}

	public String getSmsWebServiceURL() {
		return smsWebServiceURL;
	}

	public void setSmsWebServiceURL(String smsWebServiceURL) {
		this.smsWebServiceURL = smsWebServiceURL;
	}

	public String getSmsUserName() {
		return smsUserName;
	}

	public void setSmsUserName(String smsUserName) {
		this.smsUserName = smsUserName;
	}

	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getSmsSecureKey() {
		return smsSecureKey;
	}

	public void setSmsSecureKey(String smsSecureKey) {
		this.smsSecureKey = smsSecureKey;
	}

	public String getSmsSenderId() {
		return smsSenderId;
	}

	public void setSmsSenderId(String smsSenderId) {
		this.smsSenderId = smsSenderId;
	}

	public String getSslVer() {
		return sslVer;
	}

	public void setSslVer(String sslVer) {
		this.sslVer = sslVer;
	}

	public String getSmsServiceFlag() {
		return smsServiceFlag;
	}

	public void setSmsServiceFlag(String smsServiceFlag) {
		this.smsServiceFlag = smsServiceFlag;
	}

	public String getSmtpMailServerURL() {
		return smtpMailServerURL;
	}

	public void setSmtpMailServerURL(String smtpMailServerURL) {
		this.smtpMailServerURL = smtpMailServerURL;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getMailUserId() {
		return mailUserId;
	}

	public void setMailUserId(String mailUserId) {
		this.mailUserId = mailUserId;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailServiceFlag() {
		return mailServiceFlag;
	}

	public void setMailServiceFlag(String mailServiceFlag) {
		this.mailServiceFlag = mailServiceFlag;
	}

}

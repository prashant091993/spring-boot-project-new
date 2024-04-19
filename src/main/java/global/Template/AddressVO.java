package global.Template;

/**
 * AddressVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DB tables. 
 * @author AHIS
 */
public class AddressVO extends ValueObject
{
	private String patCrNo;
	private String serialNo;
	private String patAddType;
	private String patAddTypeCode;
	private String patAddCityLoc;
	private String patAddCityLocCode;
	private String patAddState;
	private String patAddStateCode;
	private String patAddCountry;
	private String patAddCountryCode;
	private String patAddHNo;
	private String patAddStreet;
	private String patAddDistrict;
	private String patAddDistrictCode;
	private String patAddCity;
	private String patAddPIN;
	private String patAddContactNo;
	private String isAddressDelhi;
	private String isCurrentAddress;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String verificationDocuments;
	private String patAddStateName;
	private String verificationDocumentId;
	private String patAddMobileNo;
	private String patAddFaxNo;
	private String patAddEmailId;
	private String patAddPhoneNo;
	private String patAddCityLocMstValue;
	private String patAddDistrictMstValue;
	private String patIsUrban;
	private String systemIPAddress;

	private String requestBy;
	private String requestRelation;
	private String requestByName;
	private String constableDesig;
	private String constableBadgeNo;
	private String requestByAddress;
	private String strPatAddressTehsil;

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getRequestRelation() {
		return requestRelation;
	}

	public void setRequestRelation(String requestRelation) {
		this.requestRelation = requestRelation;
	}

	public String getRequestByName() {
		return requestByName;
	}

	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}

	public String getConstableDesig() {
		return constableDesig;
	}

	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}

	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}

	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}

	public String getRequestByAddress() {
		return requestByAddress;
	}

	public void setRequestByAddress(String requestByAddress) {
		this.requestByAddress = requestByAddress;
	}


	public String getSystemIPAddress() {
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}

	public String getPatIsUrban() {
		return patIsUrban;
	}

	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}

	public String getPatAddMobileNo()
	{
		return patAddMobileNo;
	}

	public void setPatAddMobileNo(String patAddMobileNo)
	{
		this.patAddMobileNo = patAddMobileNo;
	}

	public String getPatAddFaxNo()
	{
		return patAddFaxNo;
	}

	public void setPatAddFaxNo(String patAddFaxNo)
	{
		this.patAddFaxNo = patAddFaxNo;
	}

	public String getPatAddEmailId()
	{
		return patAddEmailId;
	}

	public void setPatAddEmailId(String patAddEmailId)
	{
		this.patAddEmailId = patAddEmailId;
	}

	public String getVerificationDocumentId()
	{
		return verificationDocumentId;
	}

	public void setVerificationDocumentId(String verificationDocumentId)
	{
		this.verificationDocumentId = verificationDocumentId;
	}

	public String getPatAddStateName()
	{
		return patAddStateName;
	}

	public void setPatAddStateName(String patAddStateName)
	{
		this.patAddStateName = patAddStateName;
	}

	/**
	 * Retrieves verificationDocuments.
	 * @return Value of verificationDocuments.	
	 */
	public String getVerificationDocuments()
	{
		return verificationDocuments;
	}

	/**
	 * Sets verificationDocuments.
	 * @param verificationDocuments
	 */
	public void setVerificationDocuments(String verificationDocuments)
	{
		this.verificationDocuments = verificationDocuments;
	}

	/**
	 * Retrieves patAddCityLoc.
	 * @return Value of patAddCityLoc.	
	 */
	public String getPatAddCityLoc()
	{
		return patAddCityLoc;
	}

	/**
	 * Sets patAddCityLoc.
	 * @param patAddCityLoc
	 */
	public void setPatAddCityLoc(String patAddCityLoc)
	{
		this.patAddCityLoc = patAddCityLoc;
	}

	/**
	 * Retrieves patAddCityLocCode.
	 * @return Value of patAddCityLocCode.	
	 */
	public String getPatAddCityLocCode()
	{
		return patAddCityLocCode;
	}

	/**
	 * Sets patAddCityLocCode.
	 * @param patAddCityLocCode
	 */
	public void setPatAddCityLocCode(String patAddCityLocCode)
	{
		this.patAddCityLocCode = patAddCityLocCode;
	}

	/**
	 * Sets isCurrentAddress.
	 * @param isCurrentAddress
	 */
	public void setIsCurrentAddress(String isCurrentAddress)
	{
		this.isCurrentAddress = isCurrentAddress;
	}

	/**
	 * Retrieves isCurrentAddress.
	 * @return Value of isCurrentAddress.	
	 */
	public String getIsCurrentAddress()
	{
		return this.isCurrentAddress;
	}

	/**
	 * Sets patAddType.
	 * @param patAddType
	 */
	public void setPatAddType(String patAddType)
	{
		this.patAddType = patAddType;
	}

	/**
	 * Retrieves patAddType.
	 * @return Value of patAddType.	
	 */
	public String getPatAddType()
	{
		return patAddType;
	}

	/**
	 * Retrieves entryDate.
	 * @return Value of entryDate.	
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves isValid.
	 * @return Value of isValid.	
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isValid.
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves patCrNo.
	 * @return Value of patCrNo.	
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patCrNo.
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
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
	 * Sets seatId.
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Sets patAddContactNo.
	 * @param patAddContactNo
	 */
	public void setPatAddContactNo(String patAddContactNo)
	{
		this.patAddContactNo = patAddContactNo;
	}

	/**
	 * Sets patAddCountryCode.
	 * @param patAddCountryCode
	 */
	public void setPatAddCountryCode(String patAddCountryCode)
	{
		this.patAddCountryCode = patAddCountryCode;
	}

	/**
	 * Sets patAddStateCode.
	 * @param patAddStateCode
	 */
	public void setPatAddStateCode(String patAddStateCode)
	{
		this.patAddStateCode = patAddStateCode;
	}

	/**
	 * Retrieves patAddTypeCode.
	 * @return Value of patAddTypeCode.	
	 */
	public String getPatAddTypeCode()
	{
		return patAddTypeCode;
	}

	/**
	 * Sets patAddTypeCode.
	 * @param patAddTypeCode
	 */
	public void setPatAddTypeCode(String patAddTypeCode)
	{
		this.patAddTypeCode = patAddTypeCode;
	}

	/**
	 * Sets isAddressDelhi.
	 * @param isAddressDelhi
	 */
	public void setIsAddressDelhi(String isAddressDelhi)
	{
		this.isAddressDelhi = isAddressDelhi;
	}

	/**
	 * Sets patAddStreet.
	 * @param patAddStreet
	 */
	public void setPatAddStreet(String patAddStreet)
	{
		this.patAddStreet = patAddStreet;
	}

	/**
	 * Sets patAddState.
	 * @param patAddState
	 */
	public void setPatAddState(String patAddState)
	{
		this.patAddState = patAddState;
	}

	/**
	 * Sets patAddPIN.
	 * @param patAddPIN
	 */
	public void setPatAddPIN(String patAddPIN)
	{
		this.patAddPIN = patAddPIN;
	}

	/**
	 * Sets patAddHNo.
	 * @param patAddHNo
	 */
	public void setPatAddHNo(String patAddHNo)
	{
		this.patAddHNo = patAddHNo;
	}

	/**
	 * Sets patAddDistrict.
	 * @param patAddDistrict
	 */
	public void setPatAddDistrict(String patAddDistrict)
	{
		this.patAddDistrict = patAddDistrict;
	}

	/**
	 * Sets patAddCountry.
	 * @param patAddCountry
	 */
	public void setPatAddCountry(String patAddCountry)
	{
		this.patAddCountry = patAddCountry;
	}

	/**
	 * Sets patAddCity.
	 * @param patAddCity
	 */
	public void setPatAddCity(String patAddCity)
	{
		this.patAddCity = patAddCity;
	}

	/**
	 * Retrieves patAddContactNo.
	 * @return Value of patAddContactNo.	
	 */
	public String getPatAddContactNo()
	{
		return patAddContactNo;
	}

	/**
	 * Retrieves patAddCountryCode.
	 * @return Value of patAddCountryCode.	
	 */
	public String getPatAddCountryCode()
	{
		return patAddCountryCode;
	}

	/**
	 * Retrieves patAddStateCode.
	 * @return Value of patAddStateCode.	
	 */
	public String getPatAddStateCode()
	{
		return patAddStateCode;
	}

	/**
	 * Retrieves isAddressDelhi.
	 * @return Value of isAddressDelhi.	
	 */
	public String getIsAddressDelhi()
	{
		return isAddressDelhi;
	}

	/**
	 * Retrieves patAddStreet.
	 * @return Value of patAddStreet.	
	 */
	public String getPatAddStreet()
	{
		return patAddStreet;
	}

	/**
	 * Retrieves patAddState.
	 * @return Value of patAddState.	
	 */
	public String getPatAddState()
	{
		return patAddState;
	}

	/**
	 * Retrieves patAddPIN.
	 * @return Value of patAddPIN.	
	 */
	public String getPatAddPIN()
	{
		return patAddPIN;
	}

	/**
	 * Retrieves patAddHNo.
	 * @return Value of patAddHNo.	
	 */
	public String getPatAddHNo()
	{
		return patAddHNo;
	}

	/**
	 * Retrieves patAddDistrict.
	 * @return Value of patAddDistrict.	
	 */
	public String getPatAddDistrict()
	{
		return patAddDistrict;
	}

	/**
	 * Retrieves patAddCountry.
	 * @return Value of patAddCountry.	
	 */
	public String getPatAddCountry()
	{
		return patAddCountry;
	}

	/**
	 * Retrieves patAddCity.
	 * @return Value of patAddCity.	
	 */
	public String getPatAddCity()
	{
		return patAddCity;
	}

	/**
	 * Retrieves serialNo.
	 * @return Value of serialNo.	
	 */
	public String getSerialNo()
	{
		return serialNo;
	}

	/**
	 * Sets serialNo.
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getPatAddPhoneNo()
	{
		return patAddPhoneNo;
	}

	public void setPatAddPhoneNo(String patAddPhoneNo)
	{
		this.patAddPhoneNo = patAddPhoneNo;
	}

	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}

	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
	}

	public String getPatAddCityLocMstValue() {
		return patAddCityLocMstValue;
	}

	public void setPatAddCityLocMstValue(String patAddCityLocMstValue) {
		this.patAddCityLocMstValue = patAddCityLocMstValue;
	}

	public String getPatAddDistrictMstValue() {
		return patAddDistrictMstValue;
	}

	public void setPatAddDistrictMstValue(String patAddDistrictMstValue) {
		this.patAddDistrictMstValue = patAddDistrictMstValue;
	}

	public String getStrPatAddressTehsil() {
		return strPatAddressTehsil;
	}

	public void setStrPatAddressTehsil(String strPatAddressTehsil) {
		this.strPatAddressTehsil = strPatAddressTehsil;
	}

	
	
}

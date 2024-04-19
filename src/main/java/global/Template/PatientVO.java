package global.Template;

/**
 * PatientVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DB tables. 
 * @author AHIS
 */
public class PatientVO extends PatientDetailVO
{
	private AddressVO patAddress = new AddressVO();
	private String patCrNo;
	private String patMaritalStatus;
	private String patMaritalStatusCode;
	private String patReligion;
	private String patReligionCode;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patFirstNameInMultiLang;
    private String patMiddleNameInMultiLang;
    private String patLastNameInMultiLang;
	private String patGender;
	private String patGenderCode;
	private String patAge;
	private String patAgeUnit;
	private String patDOB;
	private String patIsUrban;
	private String patMonthlyIncome;
	private String isReferred;
	private String isActualDob;//if (age provided) isActualDob=0 else isActualDob=1
	private String prevCrNo;
	private String patIdNo;
	private String patStatus;
	private String patStatusCode;
	//	private String patIdMark;
	private String patIdMark1;
	private String patIdMark2;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String patRegCat;
	private String patRegCatCode;
	private String patNationalityCode;
	private String patNationality;
	private String entryDate;
	private String isCMOFree;
	private String isUnknown;
	private String isMLC;
	private String desigSemester;
	private String deptStaffStudent;
	private String registerDate;
	private String seatId;
	private String isValid;
	private String systemIPAddress;//IP address of the system from which the details are entered
	private String patMotherName;
	private String patHusbandName;
	private String patNationalId;
	private String isImageUploaded;
	private String patBloodGroup;
	private String isNewBorn = "0";
	private String patMotherCrNo;
	private String registrationType;
	private byte[] imageFile;
	private String imageFileName;
	private String patSecondaryCat;
	private String patSecondaryCatCode;
	private String patAmountCollected;
	private String patGuardianName;
	
	private String patEducation;
	private String patEducationCode;

	private String departmentUnitCode;
	private String patRefGnctdHospitalCrno;
	private String patRefGnctdHospitalDept;
	private String patRefGnctdHospitalDeptUnit;
	private String mlcNo;
	//private String emergencyType;
	//private String isLock;
	//private String isLockReason;
	//private String patCat;
	private String patCatCode;
	private String expiryDate;
	//private String isNProgram;
	//private String department;
	private String departmentCode;
	//private String currentDepartmentCode;
	//private String currentDepartment;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;//hospital name in case reffered from some other hospital
	private String patRefDoctor;
	private String isRefferInOut;
	private String systemDate;
	private String episodeCode;
	private String patOldPrimaryCat;

	//private String patSalutationCode;
	//private String patMomName;// in case of new born child
	//private String patFatherName;
	private String regRenewalRequired;
	private String patOccupation;
	private String patFatherOccupation;
	private String patHusbandOccupation;
	private String patNickName;
	private String patCardNo;
	private String isPatReferByList;
	private String previouslyReffered;
	private String episodeVisitNo;//for HRGNUM_VISIT_NO
	private String isFree;
	private String verificationDocumentId;
	private String isBroughtDead;
	private String ipAddress;
	private String isMainCrNo;
	private String patOccupationDesc;
	private String patFatherOccupationDesc;
	private String patHusbandOccupationDesc;
	private String requestBy;
	private String requestRelation;
	private String requestByName;
	private String constableDesig;
	private String constableBadgeNo;
	private String requestByAddress;
	private String birthRegNo;
	private String deathDateTime;
	private String disasterId;
	private String roomCode;   
	
	
	private String familyHeadName="";
	private String patBPLCardNo="";
	private String memberName="";
	private String bplFamilyId="";
	private String bplStateId="";
	private String bplRelationCode;
	private String bplMMJRKNo="";
	private String bplDetailsFound="";
	private String patCasteCode="";
	private String isDuplicate="";
	private String patBirthPlace;
	private String patDocType;
	private String strMctsNo;
	private String requestByContactNo;
	
	


	public String getPatCasteCode() {
		return patCasteCode;
	}
	public void setPatCasteCode(String patCasteCode) {
		this.patCasteCode = patCasteCode;
	}
	public String getBplDetailsFound() {
			return bplDetailsFound;
	}
	public void setBplDetailsFound(String bplDetailsFound) {
			this.bplDetailsFound = bplDetailsFound;
	}	
	public String getFamilyHeadName() {
		return familyHeadName;
	}
	public void setFamilyHeadName(String familyHeadName) {
		this.familyHeadName = familyHeadName;
	}
	public String getPatBPLCardNo() {
		return patBPLCardNo;
	}
	public void setPatBPLCardNo(String patBPLCardNo) {
		this.patBPLCardNo = patBPLCardNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBplFamilyId() {
		return bplFamilyId;
	}
	public void setBplFamilyId(String bplFamilyId) {
		this.bplFamilyId = bplFamilyId;
	}
	public String getBplStateId() {
		return bplStateId;
	}
	public void setBplStateId(String bplStateId) {
		this.bplStateId = bplStateId;
	}
	public String getBplRelationCode() {
		return bplRelationCode;
	}
	public void setBplRelationCode(String bplRelationCode) {
		this.bplRelationCode = bplRelationCode;
	}
	public String getBplMMJRKNo() {
		return bplMMJRKNo;
	}
	public void setBplMMJRKNo(String bplMMJRKNo) {
		this.bplMMJRKNo = bplMMJRKNo;
	}
	
	
	/*public String getPatFatherName() {
		return patFatherName;
	}

	public void setPatFatherName(String patFatherName) {
		this.patFatherName = patFatherName;
	}*/

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getDisasterId() {
		return disasterId;
	}

	public void setDisasterId(String disasterId) {
		this.disasterId = disasterId;
	}

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

	public String getIsBroughtDead()
	{
		return isBroughtDead;
	}

	public void setIsBroughtDead(String isBroughtDead)
	{
		this.isBroughtDead = isBroughtDead;
	}

	public String getVerificationDocumentId()
	{
		return verificationDocumentId;
	}

	public void setVerificationDocumentId(String verificationDocumentId)
	{
		this.verificationDocumentId = verificationDocumentId;
	}

	public String getIsFree()
	{
		return isFree;
	}

	public void setIsFree(String isFree)
	{
		this.isFree = isFree;
	}

	public String getIsPatReferByList()
	{
		return isPatReferByList;
	}

	public void setIsPatReferByList(String isPatReferByList)
	{
		this.isPatReferByList = isPatReferByList;
	}

	public String getPatCardNo()
	{
		return patCardNo;
	}

	public void setPatCardNo(String patCardNo)
	{
		this.patCardNo = patCardNo;
	}

	public String getPatFatherOccupation()
	{
		return patFatherOccupation;
	}

	public void setPatFatherOccupation(String patFatherOccupation)
	{
		this.patFatherOccupation = patFatherOccupation;
	}

	public String getPatHusbandOccupation()
	{
		return patHusbandOccupation;
	}

	public void setPatHusbandOccupation(String patHusbandOccupation)
	{
		this.patHusbandOccupation = patHusbandOccupation;
	}

	public String getPatNickName()
	{
		return patNickName;
	}

	public void setPatNickName(String patNickName)
	{
		this.patNickName = patNickName;
	}

	public String getPatOccupation()
	{
		return patOccupation;
	}

	public void setPatOccupation(String patOccupation)
	{
		this.patOccupation = patOccupation;
	}

	/**
	 * @return Returns the regRenewalRequired.
	 */
	public String getRegRenewalRequired()
	{
		return regRenewalRequired;
	}

	/**
	 * @param regRenewalRequired The regRenewalRequired to set.
	 */
	public void setRegRenewalRequired(String regRenewalRequired)
	{
		this.regRenewalRequired = regRenewalRequired;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getIsRefferInOut()
	{
		return isRefferInOut;
	}

	public void setIsRefferInOut(String isRefferInOut)
	{
		this.isRefferInOut = isRefferInOut;
	}

	public String getPatMotherName()
	{
		return patMotherName;
	}

	public void setPatMotherName(String patMotherName)
	{
		this.patMotherName = patMotherName;
	}

	public String getPatNationalId()
	{
		return patNationalId;
	}

	public void setPatNationalId(String patNationalId)
	{
		this.patNationalId = patNationalId;
	}

	public String getIsImageUploaded()
	{
		return isImageUploaded;
	}

	public void setIsImageUploaded(String isImageUploaded)
	{
		this.isImageUploaded = isImageUploaded;
	}

	public String getMlcNo()
	{
		return mlcNo;
	}

	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	public byte[] getImageFile()
	{
		return imageFile;
	}

	public void setImageFile(byte[] imageFile)
	{
		this.imageFile = imageFile;
	}

	/**
	 * Sets systemIPAddress.
	 * @param systemIPAddress
	 */
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	/**
	 * Retrieves systemIPAddress.
	 * @return Value of systemIPAddress.	
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	/**
	 * Sets patRefHospitalName.
	 * @param patRefHospitalName
	 */
	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}

	/**
	 * Retrieves patRefHospitalName.
	 * @return Value of patRefHospitalName.	
	 */
	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	/**
	 * Sets patRefGnctdHospitalCode.
	 * @param patRefGnctdHospitalCode
	 */
	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	/**
	 * Retrieves patRefGnctdHospitalCode.
	 * @return Value of patRefGnctdHospitalCode.	
	 */
	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	/**
	 * Sets patCatcode.
	 * @param patCatcode
	 */
	/*public void setPatCatcode( String patCatcode )
	{
		this.patCatcode = patCatcode;
	}

	*//**
	 * Retrieves patCatcode.
	 * @return Value of patCatcode.	
	 *//*
	public String getPatCatcode( )
	{
		return patCatcode;
	}*/

	/**
	 * Sets patCat.
	 * @param patCat
	 */
	/*public void setPatCat( String patCat )
	{
		this.patCat = patCat;
	}*/

	/**
	 * Retrieves patCat.
	 * @return Value of patCat.	
	 */
	/*public String getPatCat( )
	{
		return patCat;
	}*/

	/**
	 * Sets emergencyType.
	 * @param emergencyType
	 */
	/*public void setEmergencyType( String emergencyType )
	{
		this.emergencyType = emergencyType;
	}
	 */
	/**
	 * Retrieves emergencyType.
	 * @return Value of emergencyType.	
	 */
	/*public String getEmergencyType( )
	{
		return emergencyType;
	}*/

	/**
	 * Sets patRegCat.
	 * @param patRegCat
	 */
	public void setPatRegCat(String patRegCat)
	{
		this.patRegCat = patRegCat;
	}

	/**
	 * Retrieves patRegCat.
	 * @return Value of patRegCat.	
	 */
	public String getPatRegCat()
	{
		return patRegCat;
	}

	/**
	 * Sets registerDate.
	 * @param registerDate
	 */
	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}

	/**
	 * Retrieves registerDate.
	 * @return Value of registerDate.	
	 */
	public String getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * Sets prevCrNo.
	 * @param prevCrNo
	 */
	public void setPrevCrNo(String prevCrNo)
	{
		this.prevCrNo = prevCrNo;
	}

	/**
	 * Retrieves prevCrNo.
	 * @return Value of prevCrNo.	
	 */
	public String getPrevCrNo()
	{
		return prevCrNo;
	}

	/**
	 * Sets patStatusCode.
	 * @param patStatusCode
	 */
	public void setPatStatusCode(String patStatusCode)
	{
		this.patStatusCode = patStatusCode;
	}

	/**
	 * Retrieves patStatusCode.
	 * @return Value of patStatusCode.	
	 */
	public String getPatStatusCode()
	{
		return patStatusCode;
	}

	/**
	 * Sets patSecondaryCatCode.
	 * @param patSecondaryCatCode
	 */
	public void setPatSecondaryCatCode(String patSecondaryCatCode)
	{
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	/**
	 * Retrieves patSecondaryCatCode.
	 * @return Value of patSecondaryCatCode.	
	 */
	public String getPatSecondaryCatCode()
	{
		return patSecondaryCatCode;
	}

	/**
	 * Sets patSecondaryCat.
	 * @param patSecondaryCat
	 */
	public void setPatSecondaryCat(String patSecondaryCat)
	{
		this.patSecondaryCat = patSecondaryCat;
	}

	/**
	 * Retrieves patSecondaryCat.
	 * @return Value of patSecondaryCat.	
	 */
	public String getPatSecondaryCat()
	{
		return patSecondaryCat;
	}

	/**
	 * Sets patReligionCode.
	 * @param patReligionCode
	 */
	public void setPatReligionCode(String patReligionCode)
	{
		this.patReligionCode = patReligionCode;
	}

	/**
	 * Retrieves patReligionCode.
	 * @return Value of patReligionCode.	
	 */
	public String getPatReligionCode()
	{
		return patReligionCode;
	}

	/**
	 * Sets patReligion.
	 * @param patReligion
	 */
	public void setPatReligion(String patReligion)
	{
		this.patReligion = patReligion;
	}

	/**
	 * Retrieves patReligion.
	 * @return Value of patReligion.	
	 */
	public String getPatReligion()
	{
		return patReligion;
	}

	/**
	 * Sets patRegCatCode.
	 * @param patRegCatCode
	 */
	public void setPatRegCatCode(String patRegCatCode)
	{
		this.patRegCatCode = patRegCatCode;
	}

	/**
	 * Retrieves patRegCatCode.
	 * @return Value of patRegCatCode.	
	 */
	public String getPatRegCatCode()
	{
		return patRegCatCode;
	}

	/**
	 * Sets patRefDoctor.
	 * @param patRefDoctor
	 */
	public void setPatRefDoctor(String patRefDoctor)
	{
		this.patRefDoctor = patRefDoctor;
	}

	/**
	 * Retrieves patRefDoctor.
	 * @return Value of patRefDoctor.	
	 */
	public String getPatRefDoctor()
	{
		return patRefDoctor;
	}

	/**
	 * Sets patPrimaryCatCode.
	 * @param patPrimaryCatCode
	 */
	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	/**
	 * Retrieves patPrimaryCatCode.
	 * @return Value of patPrimaryCatCode.	
	 */
	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	/**
	 * Sets patPrimaryCat.
	 * @param patPrimaryCat
	 */
	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	/**
	 * Retrieves patPrimaryCat.
	 * @return Value of patPrimaryCat.	
	 */
	public String getPatPrimaryCat()
	{
		if (this.patPrimaryCat == null || this.patPrimaryCat.equals(""))
		{
			this.patPrimaryCat = this.patOldPrimaryCat;
		}
		return patPrimaryCat;
	}

	public String getPatOldPrimaryCat()
	{
		return patOldPrimaryCat;
	}

	public void setPatOldPrimaryCat(String patOldPrimaryCat)
	{
		this.patOldPrimaryCat = patOldPrimaryCat;
	}

	/**
	 * Sets patMonthlyIncome.
	 * @param patMonthlyIncome
	 */
	public void setPatMonthlyIncome(String patMonthlyIncome)
	{
		this.patMonthlyIncome = patMonthlyIncome;
	}

	/**
	 * Retrieves patMonthlyIncome.
	 * @return Value of patMonthlyIncome.	
	 */
	public String getPatMonthlyIncome()
	{
		return patMonthlyIncome;
	}

	/**
	 * Sets patMomName.
	 * @param patMomName
	 */
	/*public void setPatMomName( String patMomName )
	{
		this.patMomName = patMomName;
	}*/

	/**
	 * Retrieves patMomName.
	 * @return Value of patMomName.	
	 */
	/*public String getPatMomName( )
	{
		return patMomName;
	}
	 */
	/**
	 * Sets patMiddleName.
	 * @param patMiddleName
	 */
	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	/**
	 * Retrieves patMiddleName.
	 * @return Value of patMiddleName.	
	 */
	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	/**
	 * Sets patMaritalStatusCode.
	 * @param patMaritalStatusCode
	 */
	public void setPatMaritalStatusCode(String patMaritalStatusCode)
	{
		this.patMaritalStatusCode = patMaritalStatusCode;
	}

	/**
	 * Retrieves patMaritalStatusCode.
	 * @return Value of patMaritalStatusCode.	
	 */
	public String getPatMaritalStatusCode()
	{
		return patMaritalStatusCode;
	}

	/**
	 * Sets patMaritalStatusCode.
	 * @param patMaritalStatusCode
	 */
	public void setPatMaritalStatus(String patMaritalStatus)
	{
		this.patMaritalStatus = patMaritalStatus;
	}

	/**
	 * Retrieves patMaritalStatusCode.
	 * @return Value of patMaritalStatusCode.	
	 */
	public String getPatMaritalStatus()
	{
		return patMaritalStatus;
	}

	/**
	 * Sets patLastName.
	 * @param patLastName
	 */
	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	/**
	 * Retrieves patLastName.
	 * @return Value of patLastName.	
	 */
	public String getPatLastName()
	{
		return patLastName;
	}

	public String getPatFirstNameInMultiLang() {
		return patFirstNameInMultiLang;
	}
	public void setPatFirstNameInMultiLang(String patFirstNameInMultiLang) {
		this.patFirstNameInMultiLang = patFirstNameInMultiLang;
	}
	public String getPatMiddleNameInMultiLang() {
		return patMiddleNameInMultiLang;
	}
	public void setPatMiddleNameInMultiLang(String patMiddleNameInMultiLang) {
		this.patMiddleNameInMultiLang = patMiddleNameInMultiLang;
	}
	public String getPatLastNameInMultiLang() {
		return patLastNameInMultiLang;
	}
	public void setPatLastNameInMultiLang(String patLastNameInMultiLang) {
		this.patLastNameInMultiLang = patLastNameInMultiLang;
	}
	/**
	 * Sets patIsUrban.
	 * @param patIsUrban
	 */
	public void setPatIsUrban(String patIsUrban)
	{
		this.patIsUrban = patIsUrban;
	}

	/**
	 * Retrieves patIsUrban.
	 * @return Value of patIsUrban.	
	 */
	public String getPatIsUrban()
	{
		return patIsUrban;
	}

	/**
	 * Sets patIdNo.
	 * @param patIdNo
	 */
	public void setPatIdNo(String patIdNo)
	{
		this.patIdNo = patIdNo;
	}

	/**
	 * Retrieves patIdNo.
	 * @return Value of patIdNo.	
	 */
	public String getPatIdNo()
	{
		return patIdNo;
	}

	/**
	 * Sets patGuardianName.
	 * @param patGuardianName
	 */
	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	/**
	 * Retrieves patGuardianName.
	 * @return Value of patGuardianName.	
	 */
	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	/**
	 * Sets patGenderCode.
	 * @param patGenderCode
	 */
	public void setPatGenderCode(String patGenderCode)
	{
		this.patGenderCode = patGenderCode;
	}

	/**
	 * Retrieves patGenderCode.
	 * @return Value of patGenderCode.	
	 */
	public String getPatGenderCode()
	{
		return patGenderCode;
	}

	/**
	 * Sets patGender.
	 * @param patGender
	 */
	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	/**
	 * Retrieves patGender.
	 * @return Value of patGender.	
	 */
	public String getPatGender()
	{
		return patGender;
	}

	public String getPatEducation() {
		return patEducation;
	}
	public void setPatEducation(String patEducation) {
		this.patEducation = patEducation;
	}
	public String getPatEducationCode() {
		return patEducationCode;
	}
	public void setPatEducationCode(String patEducationCode) {
		this.patEducationCode = patEducationCode;
	}
	/**
	 * Sets patFirstName.
	 * @param patFirstName
	 */
	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	/**
	 * Retrieves patFirstName.
	 * @return Value of patFirstName.	
	 */
	public String getPatFirstName()
	{
		return patFirstName;
	}

	/**
	 * Sets patDOB.
	 * @param patDOB
	 */
	public void setPatDOB(String patDOB)
	{
		this.patDOB = patDOB;
	}

	/**
	 * Retrieves patDOB.
	 * @return Value of patDOB.	
	 */
	public String getPatDOB()
	{
		return patDOB;
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
	 * Retrieves patCrNo.
	 * @return Value of patCrNo.	
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patAmountCollected.
	 * @param patAmountCollected
	 */
	public void setPatAmountCollected(String patAmountCollected)
	{
		this.patAmountCollected = patAmountCollected;
	}

	/**
	 * Retrieves patAmountCollected.
	 * @return Value of patAmountCollected.	
	 */
	public String getPatAmountCollected()
	{
		return patAmountCollected;
	}

	/**
	 * Retrieves patAddress.
	 * @return Value of patAddress.	
	 */
	public AddressVO getPatAddress()
	{
		return patAddress;
	}

	/**
	 * Sets patAddress.
	 * @param patAddress
	 */
	public void setPatAddress(AddressVO patAddress)
	{
		this.patAddress = patAddress;
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
	 * Retrieves isValid.
	 * @return Value of isValid.	
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isUnknown.
	 * @param isUnknown
	 */
	public void setIsUnknown(String isUnknown)
	{
		this.isUnknown = isUnknown;
	}

	/**
	 * Retrieves isUnknown.
	 * @return Value of isUnknown.	
	 */
	public String getIsUnknown()
	{
		return isUnknown;
	}

	/**
	 * Sets isReferred.
	 * @param isReferred
	 */
	public void setIsReferred(String isReferred)
	{
		this.isReferred = isReferred;
	}

	/**
	 * Retrieves isReferred.
	 * @return Value of isReferred.	
	 */
	public String getIsReferred()
	{
		return isReferred;
	}

	/**
	 * Sets isMLC.
	 * @param isMLC
	 */
	public void setIsMLC(String isMLC)
	{
		this.isMLC = isMLC;
	}

	/**
	 * Retrieves isMLC.
	 * @return Value of isMLC.	
	 */
	public String getIsMLC()
	{
		return isMLC;
	}

	/**
	 * Sets isActualDob.
	 * @param isActualDob
	 */
	public void setIsActualDob(String isActualDob)
	{
		this.isActualDob = isActualDob;
	}

	/**
	 * Retrieves isActualDob.
	 * @return Value of isActualDob.	
	 */
	public String getIsActualDob()
	{
		return isActualDob;
	}

	/**
	 * Sets expiryDate.
	 * @param expiryDate
	 */
	public void setExpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	/**
	 * Retrieves expiryDate.
	 * @return Value of expiryDate.	
	 */
	public String getExpiryDate()
	{
		return expiryDate;
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
	 * Retrieves entryDate.
	 * @return Value of entryDate.	
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets departmentCode.
	 * @param departmentCode
	 */
	/*public void setDepartmentCode( String departmentCode )
	{
		this.departmentCode = departmentCode;
	}*/

	/**
	 * Retrieves departmentCode.
	 * @return Value of departmentCode.	
	 */
	/*public String getDepartmentCode( )
	{
		return departmentCode;
	}*/

	/**
	 * Sets department.
	 * @param department
	 */
	/*public void setDepartment( String department )
	{
		this.department = department;
	}*/

	/**
	 * Retrieves department.
	 * @return Value of department.	
	 */
	/*public String getDepartment( )
	{
		return department;
	}*/

	//	 populate addressVO
	/**
	 * Retrieves isAddressDelhi.
	 * @return Value of isAddressDelhi.	
	 */
	public String getIsAddressDelhi()
	{
		return getPatAddress().getIsAddressDelhi();
	}

	/**
	 * Sets isAddressDelhi.
	 * @param isAddressDelhi
	 */
	public void setIsAddressDelhi(String isAddressDelhi)
	{
		this.getPatAddress().setIsAddressDelhi(isAddressDelhi);
	}

	/**
	 * Retrieves isCurrentAddress.
	 * @return Value of isCurrentAddress.	
	 */
	public String getIsCurrentAddress()
	{
		return getPatAddress().getIsCurrentAddress();
	}

	/**
	 * Sets isCurrentAddress.
	 * @param isCurrentAddress
	 */
	public void setIsCurrentAddress(String isCurrentAddress)
	{
		this.getPatAddress().setIsCurrentAddress(isCurrentAddress);
	}

	/**
	 * Retrieves patAddCity.
	 * @return Value of patAddCity.	
	 */
	public String getPatAddCity()
	{
		return getPatAddress().getPatAddCity();
	}

	/**
	 * Sets patAddCity.
	 * @param patAddCity
	 */
	public void setPatAddCity(String patAddCity)
	{
		this.getPatAddress().setPatAddCity(patAddCity);
	}

	/**
	 * Retrieves patAddCityLoc.
	 * @return Value of patAddCityLoc.	
	 */
	public String getPatAddCityLoc()
	{
		return getPatAddress().getPatAddCityLoc();
	}

	/**
	 * Sets patAddCityLoc.
	 * @param patAddCityLoc
	 */
	public void setPatAddCityLoc(String patAddCityLoc)
	{
		this.getPatAddress().setPatAddCityLoc(patAddCityLoc);
	}

	/**
	 * Retrieves patAddCityLocCode.
	 * @return Value of patAddCityLocCode.	
	 */
	public String getPatAddCityLocCode()
	{
		return getPatAddress().getPatAddCityLocCode();
	}

	/**
	 * Sets patAddCityLocCode.
	 * @param patAddCityLocCode
	 */
	public void setPatAddCityLocCode(String patAddCityLocCode)
	{
		this.getPatAddress().setPatAddCityLocCode(patAddCityLocCode);
	}

	/**
	 * Retrieves patAddContactNo.
	 * @return Value of patAddContactNo.	
	 */
	public String getPatAddContactNo()
	{
		return getPatAddress().getPatAddContactNo();
	}

	/**
	 * Sets patAddContactNo.
	 * @param patAddContactNo
	 */
	public void setPatAddContactNo(String patAddContactNo)
	{
		this.getPatAddress().setPatAddContactNo(patAddContactNo);
	}

	/**
	 * Retrieves patAddCountry.
	 * @return Value of patAddCountry.	
	 */
	public String getPatAddCountry()
	{
		return getPatAddress().getPatAddCountry();
	}

	/**
	 * Sets patAddCountry.
	 * @param patAddCountry
	 */
	public void setPatAddCountry(String patAddCountry)
	{
		this.getPatAddress().setPatAddCountry(patAddCountry);
	}

	/**
	 * Retrieves patAddCountryCode.
	 * @return Value of patAddCountryCode.	
	 */
	public String getPatAddCountryCode()
	{
		return getPatAddress().getPatAddCountryCode();
	}

	/**
	 * Sets patAddCountryCode.
	 * @param patAddCountryCode
	 */
	public void setPatAddCountryCode(String patAddCountryCode)
	{
		this.getPatAddress().setPatAddCountryCode(patAddCountryCode);
	}

	/**
	 * Retrieves patAddDistrict.
	 * @return Value of patAddDistrict.	
	 */
	public String getPatAddDistrict()
	{
		return getPatAddress().getPatAddDistrict();
	}

	/**
	 * Sets patAddDistrict.
	 * @param patAddDistrict
	 */
	public void setPatAddDistrict(String patAddDistrict)
	{
		this.getPatAddress().setPatAddDistrict(patAddDistrict);
	}
	
	public String getPatAddDistrictCode()
	{
		return getPatAddress().getPatAddDistrictCode();
	}
	
	public void setPatAddDistrictCode(String patAddDistrictCode)
	{
		this.getPatAddress().setPatAddDistrictCode(patAddDistrictCode);
	}

	/**
	 * Retrieves patAddHNo.
	 * @return Value of patAddHNo.	
	 */
	public String getPatAddHNo()
	{
		return getPatAddress().getPatAddHNo();
	}

	/**
	 * Sets patAddHNo.
	 * @param patAddHNo
	 */
	public void setPatAddHNo(String patAddHNo)
	{
		this.getPatAddress().setPatAddHNo(patAddHNo);
	}

	/**
	 * Retrieves patAddPIN.
	 * @return Value of patAddPIN.	
	 */
	public String getPatAddPIN()
	{
		return getPatAddress().getPatAddPIN();
	}

	/**
	 * Sets patAddPIN.
	 * @param patAddPIN
	 */
	public void setPatAddPIN(String patAddPIN)
	{
		this.getPatAddress().setPatAddPIN(patAddPIN);
	}

	/**
	 * Retrieves patAddState.
	 * @return Value of patAddState.	
	 */
	public String getPatAddState()
	{
		return getPatAddress().getPatAddState();
	}

	public String getPatAddStateName()
	{
		return getPatAddress().getPatAddStateName();
	}

	/**
	 * Sets patAddState.
	 * @param patAddState
	 */
	public void setPatAddState(String patAddState)
	{
		this.getPatAddress().setPatAddState(patAddState);
	}

	public void setPatAddStateName(String patAddStateName)
	{
		this.getPatAddress().setPatAddStateName(patAddStateName);
	}

	/**
	 * Retrieves patAddStateCode.
	 * @return Value of patAddStateCode.	
	 */
	public String getPatAddStateCode()
	{
		return getPatAddress().getPatAddStateCode();
	}

	/**
	 * Sets patAddStateCode.
	 * @param patAddStateCode
	 */
	public void setPatAddStateCode(String patAddStateCode)
	{
		this.getPatAddress().setPatAddStateCode(patAddStateCode);
	}

	/**
	 * Retrieves patAddStreet.
	 * @return Value of patAddStreet.	
	 */

	public String getPatAddStreet()
	{
		return getPatAddress().getPatAddStreet();
	}

	/**
	 * Sets patAddStreet.
	 * @param patAddStreet
	 */
	public void setPatAddStreet(String patAddStreet)
	{
		this.getPatAddress().setPatAddStreet(patAddStreet);
	}
	
	public String getStrPatAddressTehsil() {
		return this.getPatAddress().getStrPatAddressTehsil();
	}

	public void setStrPatAddressTehsil(String strPatAddressTehsil) {
		this.getPatAddress().setStrPatAddressTehsil(strPatAddressTehsil);
	}
	/**
	 * Retrieves patAddType.
	 * @return Value of patAddType.	
	 */
	public String getPatAddType()
	{
		return getPatAddress().getPatAddType();
	}

	/**
	 * Sets patAddType.
	 * @param patAddType
	 */
	public void setPatAddType(String patAddType)
	{
		this.getPatAddress().setPatAddType(patAddType);
	}

	/**
	 * Retrieves patAddTypeCode.
	 * @return Value of patAddTypeCode.	
	 */
	public String getPatAddTypeCode()
	{
		return getPatAddress().getPatAddTypeCode();
	}

	/**
	 * Sets patAddTypeCode.
	 * @param patAddTypeCode
	 */
	public void setPatAddTypeCode(String patAddTypeCode)
	{
		this.getPatAddress().setPatAddTypeCode(patAddTypeCode);
	}

	/**
	 * Retrieves currentDepartment.
	 * @return Value of currentDepartment.	
	 */
	/*public String getCurrentDepartment() {
		return currentDepartment;
	}*/

	/**
	 * Sets currentDepartment.
	 * @param currentDepartment
	 */
	/*public void setCurrentDepartment(String currentDepartment) {
		this.currentDepartment = currentDepartment;
	}*/

	/**
	 * Retrieves currentDepartmentCode.
	 * @return Value of currentDepartmentCode.	
	 */
	/*public String getCurrentDepartmentCode() {
		return currentDepartmentCode;
	}*/

	/**
	 * Sets currentDepartmentCode.
	 * @param currentDepartmentCode
	 */
	/*public void setCurrentDepartmentCode(String currentDepartmentCode) {
		this.currentDepartmentCode = currentDepartmentCode;
	}*/

	/**
	 * Retrieves deptStaffStudent.
	 * @return Value of deptStaffStudent.	
	 */
	public String getDeptStaffStudent()
	{
		return deptStaffStudent;
	}

	/**
	 * Sets deptStaffStudent.
	 * @param deptStaffStudent
	 */
	public void setDeptStaffStudent(String deptStaffStudent)
	{
		this.deptStaffStudent = deptStaffStudent;
	}

	/**
	 * Retrieves desigSemester.
	 * @return Value of desigSemester.	
	 */
	public String getDesigSemester()
	{
		return desigSemester;
	}

	/**
	 * Sets desigSemester.
	 * @param desigSemester
	 */
	public void setDesigSemester(String desigSemester)
	{
		this.desigSemester = desigSemester;
	}

	/**
	 * Retrieves isCMOFree.
	 * @return Value of isCMOFree.	
	 */
	public String getIsCMOFree()
	{
		return isCMOFree;
	}

	/**
	 * Sets isCMOFree.
	 * @param isCMOFree
	 */
	public void setIsCMOFree(String isCMOFree)
	{
		this.isCMOFree = isCMOFree;
	}

	/**
	 * Retrieves isLock.
	 * @return Value of isLock.	
	 */
	/*public String getIsLock() {
		return isLock;
	}*/

	/**
	 * Sets isLock.
	 * @param isLock
	 */
	/*public void setIsLock(String isLock) {
		this.isLock = isLock;
	}*/

	/**
	 * Retrieves isLockReason.
	 * @return Value of isLockReason.	
	 */
	/*public String getIsLockReason() {
		return isLockReason;
	}*/

	/**
	 * Sets isLockReason.
	 * @param isLockReason
	 */
	/*public void setIsLockReason(String isLockReason) {
		this.isLockReason = isLockReason;
	}*/

	/**
	 * Retrieves isNProgram.
	 * @return Value of isNProgram.	
	 */
	/*public String getIsNProgram() {
		return isNProgram;
	}*/

	/**
	 * Sets isNProgram.
	 * @param isNProgram
	 */
	/*public void setIsNProgram(String isNProgram) {
		this.isNProgram = isNProgram;
	}*/

	/**
	 * Retrieves patAge.
	 * @return Value of patAge.	
	 */
	public String getPatAge()
	{
		return patAge;
	}

	/**
	 * Sets patAge.
	 * @param patAge
	 */
	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	/**
	 * Retrieves patAgeUnit.
	 * @return Value of patAgeUnit.	
	 */
	public String getPatAgeUnit()
	{
		return patAgeUnit;
	}

	/**
	 * Sets patAgeUnit.
	 * @param patAgeUnit
	 */
	public void setPatAgeUnit(String patAgeUnit)
	{
		this.patAgeUnit = patAgeUnit;
	}

	/**
	 * Retrieves patIdMark.
	 * @return Value of patIdMark.	
	 */
	/*	public String getPatIdMark() {
			return patIdMark;
		}

	 *//**
	 * Sets patIdMark.
	 * @param patIdMark
	 */
	/*
			public void setPatIdMark(String patIdMark) {
				this.patIdMark = patIdMark;
			}
	 */
	/**
	 * Retrieves patSalutationCode.
	 * @return Value of patSalutationCode.	
	 */
	/*public String getPatSalutationCode() {
		return patSalutationCode;
	}*/

	/**
	 * Sets patSalutationCode.
	 * @param patSalutationCode
	 */
	/*public void setPatSalutationCode(String patSalutationCode) {
		this.patSalutationCode = patSalutationCode;
	}*/

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
	 * Retrieves patNationality.
	 * @return Value of patNationality.	
	 */
	public String getPatNationality()
	{
		return patNationality;
	}

	/**
	 * Sets patNationality.
	 * @param patNationality
	 */
	public void setPatNationality(String patNationality)
	{
		this.patNationality = patNationality;
	}

	/**
	 * Retrieves patNationalityCode.
	 * @return Value of patNationalityCode.	
	 */
	public String getPatNationalityCode()
	{
		return patNationalityCode;
	}

	/**
	 * Sets patNationalityCode.
	 * @param patNationalityCode
	 */
	public void setPatNationalityCode(String patNationalityCode)
	{
		this.patNationalityCode = patNationalityCode;
	}

	public String getPatHusbandName()
	{
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName)
	{
		this.patHusbandName = patHusbandName;
	}

	public String getRegistrationType()
	{
		return registrationType;
	}

	public void setRegistrationType(String registrationType)
	{
		this.registrationType = registrationType;
	}

	public String getImageFileName()
	{
		return imageFileName;
	}

	public void setImageFileName(String imageFileName)
	{
		this.imageFileName = imageFileName;
	}

	public String getIsNewBorn()
	{
		return isNewBorn;
	}

	public void setIsNewBorn(String isNewBorn)
	{
		this.isNewBorn = isNewBorn;
	}

	public String getPatBloodGroup()
	{
		return patBloodGroup;
	}

	public void setPatBloodGroup(String patBloodGroup)
	{
		this.patBloodGroup = patBloodGroup;
	}

	public String getPatMotherCrNo()
	{
		return patMotherCrNo;
	}

	public void setPatMotherCrNo(String patMotherCrNo)
	{
		this.patMotherCrNo = patMotherCrNo;
	}

	public String getPatStatus()
	{
		return patStatus;
	}

	public void setPatStatus(String patStatus)
	{
		this.patStatus = patStatus;
	}

	/*public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	 */

	/**
	 * Retrieves patRefGnctdHospitalCrno.
	 * @return Value of patRefGnctdHospitalCrno.	
	 */
	public String getPatRefGnctdHospitalCrno()
	{
		return patRefGnctdHospitalCrno;
	}

	/**
	 * Sets patRefGnctdHospitalCrno.
	 * @param patRefGnctdHospitalCrno
	 */
	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno)
	{
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}

	/**
	 * Retrieves patRefGnctdHospitalDept.
	 * @return Value of patRefGnctdHospitalDept.	
	 */
	public String getPatRefGnctdHospitalDept()
	{
		return patRefGnctdHospitalDept;
	}

	/**
	 * Sets patRefGnctdHospitalDept.
	 * @param patRefGnctdHospitalDept
	 */
	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept)
	{
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}

	/**
	 * Retrieves patRefGnctdHospitalDeptUnit.
	 * @return Value of patRefGnctdHospitalDeptUnit.	
	 */
	public String getPatRefGnctdHospitalDeptUnit()
	{
		return patRefGnctdHospitalDeptUnit;
	}

	/**
	 * Sets patRefGnctdHospitalDeptUnit.
	 * @param patRefGnctdHospitalDeptUnit
	 */
	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit)
	{
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	public String getSystemDate()
	{
		return systemDate;
	}

	public void setSystemDate(String systemDate)
	{
		this.systemDate = systemDate;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getPreviouslyReffered()
	{
		return previouslyReffered;
	}

	public void setPreviouslyReffered(String previouslyReffered)
	{
		this.previouslyReffered = previouslyReffered;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getPatIdMark1()
	{
		return patIdMark1;
	}

	public void setPatIdMark1(String patIdMark1)
	{
		this.patIdMark1 = patIdMark1;
	}

	public String getPatIdMark2()
	{
		return patIdMark2;
	}

	public void setPatIdMark2(String patIdMark2)
	{
		this.patIdMark2 = patIdMark2;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getIsMainCrNo() {
		return isMainCrNo;
	}

	public void setIsMainCrNo(String isMainCrNo) {
		this.isMainCrNo = isMainCrNo;
	}

	public String getPatOccupationDesc() {
		return patOccupationDesc;
	}

	public void setPatOccupationDesc(String patOccupationDesc) {
		this.patOccupationDesc = patOccupationDesc;
	}

	public String getPatFatherOccupationDesc() {
		return patFatherOccupationDesc;
	}

	public void setPatFatherOccupationDesc(String patFatherOccupationDesc) {
		this.patFatherOccupationDesc = patFatherOccupationDesc;
	}

	public String getPatHusbandOccupationDesc() {
		return patHusbandOccupationDesc;
	}

	public void setPatHusbandOccupationDesc(String patHusbandOccupationDesc) {
		this.patHusbandOccupationDesc = patHusbandOccupationDesc;
	}

	public String getBirthRegNo() {
		return birthRegNo;
	}

	public void setBirthRegNo(String birthRegNo) {
		this.birthRegNo = birthRegNo;
	}

	public String getDeathDateTime() {
		return deathDateTime;
	}

	public void setDeathDateTime(String deathDateTime) {
		this.deathDateTime = deathDateTime;
	}

	public String getPatCatCode() {
		return patCatCode;
	}

	public void setPatCatCode(String patCatCode) {
		this.patCatCode = patCatCode;
	}
	public String getIsDuplicate() {
		return isDuplicate;
	}
	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public String getPatBirthPlace() {
		return patBirthPlace;
	}
	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}
	public String getPatDocType() {
		return patDocType;
	}
	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}
	
	public String getStrMctsNo() {
		return strMctsNo;
	}
	public void setStrMctsNo(String strMctsNo) {
		this.strMctsNo = strMctsNo;
	}
	
	public String getRequestByContactNo() {
		return requestByContactNo;
	}
	public void setRequestByContactNo(String requestByContactNo) {
		this.requestByContactNo = requestByContactNo;
	}

	

}

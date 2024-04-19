
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	
## Module Name					: 	MRD
## Process/Database Object Name	:	PatientDetailVO
## Purpose						:	online request raise from OPD Doctor Desk or OPD Bay Desk or IPD Doctor Desk. Doctor provide request slip to patient with complete medical certificate information like rest dates, fitness dates etc.
## Date of Creation				: 	
## Modification Log				:					
##		Modify Date				: 	12-Dec2014
##		Reason	(CR/PRS)		: 	Add More Patient Detail field For IPD Docter Desk
##		Modify By				:	Akash Singh
*/
package global.Template;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientDetailVO 
{
	/*
	 * private AddressVO patAddress = new AddressVO(); private String patCrNo; private String serialNo; private String
	 * patMaritalStatus; private String patMaritalStatusCode; private String patReligion; private String patReligionCode;
	 * private String patFirstName; private String patMiddleName; private String patLastName; private String patGender;
	 * private String patGenderCode; private String patDOB;
	 * 
	 * private String patAge; private String patIsUrban; private String patMonthlyIncome; private String isReferred; private
	 * String isActualDob;// if (age provided) isActualDob=0 else isActualDob=1 private String prevCrNo; private String
	 * patIdNo; private String patStatus; private String patStatusCode; private String patPrimaryCat; private String
	 * patPrimaryCatCode; private String patRegCat; private String patRegCatCode; private String patNationalityCode; private
	 * String patNationality; private String entryDate; private String isUnknown; private String isMLC; private String
	 * registerDate; private String seatId; private String isValid; private String systemIPAddress;// IP address of the
	 * system from which the details are entered private String patMotherName; private String patHusbandName; private String
	 * patNationalId; private String patBloodGroup; private String isNewBorn = "0"; private String patMotherCrNo; private
	 * String registrationType; private String patSecondaryCat; private String patSecondaryCatCode; private String
	 * patAmountCollected; private String patGuardianName; private String patIsOld; private String department; private String
	 * departmentCode; private String departmentUnit; private String departmentUnitCode; private String patIsOld; private
	 * String room; private String roomCode; private String queNo; private String patientAllowed; private String
	 * episodeCode;// for HRGNUM_EPISODE_NO private String episodeVisitNo;// for HRGNUM_VISIT_NO private String
	 * episodeTypeCode; private String patRefGnctdHospitalCode; private String patRefHospitalName; private String
	 * patRefDoctor; private String episodeDate; private String patRefGnctdHospitalCrno; private String
	 * patRefGnctdHospitalDept; private String patRefGnctdHospitalDeptUnit; private String episodeVisitType;
	 * 
	 * private String patOccupation; private String patFatherOccupation; private String patHusbandOccupation; private String
	 * patNickName; private String patCardNo; private String patGenderAge; private String orederBy; private String sortType;
	 * private String fileNo; private String patIdMark1; private String patIdMark2; private String isFree; private String
	 * isBroughtDead;
	 * 
	 * private String colorCode; private String colorCodeOrder; private String isConfirmed;
	 */
	private String departmentName;
	private String departmentUnitName;
	private String mlcNo;
	private String patSpouceName;

	private String patName;
	private String patCompleteAddress;

	private String patAdmNo;
	private String admAdvNo;
	private String admDateTime;
	private String bedAllocDateTime;
	private String disDateTime;
	private String disStatusCode;
	private String admComplain;
	private String dischargeAdvice;
	private String wardCode;
	private String ipdRoomCode;
	private String bedCode;
	private String wardName;
	private String wardTypeCode;
	private String ipdRoomName;
	private String ipdRoomTypeCode;
	private String bedName;
	private String bedTypeCode;
	private String ownDepartmentUnitCode;
	private String ownWardCode;
	private String ownDepartmentCode;
	private String motherAdmNo;
	private String patFrom;
	private String refAdmNo;
	private String admStatusCode;
	private String charge;
	private String isDead;
	private String consultantID;
	private String consultantName;

	// ADT Details
	private String isIpd;
	private String patAdmittedDays;
	private String isBedSharable;

	// Other Details
	private String patCaste;
	private String patFamilyType;
	private String patMotherOccupation;
	private String patEducationStatus;
	private String patSpouseEducationStatus;
	private String patFatherEducationStatus;
	private String patMotherEducationStatus;
	private String isActualDateOfMarriage;
	private String patDateOfMarriage;
	private String patAgeOfMarriage;
	private String patDateOfMenarche;
	private String patAgeOfMenarche;
	private String patHusbandBloodGroup;

	// Gender Type
	private String patGenderType;
	private String patCasteName;

	// ANC Detail
	private String patIsPregnant;
	private String patGestationWeek;

	// Request By used in Audit Trial
	private String requestBy;
	private String requestByName;
	
	// ICD Entry Form
	private String patDiagnosisDtl;
	private String patIsDocPresent;
	
	// Redirect OPD patient
	private String patChangeType;
	private String hospitalCode;	
	private String hospitalName;
	
	// Patient Address without AddressVO
	private String isAddressDelhi;
	private String isCurrentAddress;
	private String patAddCity;
	private String patAddCityLoc;
	private String patAddCityLocCode;
	private String patAddContactNo;
	private String patAddCountry;
	private String patAddCountryCode;
	private String patAddDistrict;
	private String patAddDistrictCode;
	private String patAddHNo;
	private String patAddPIN;
	private String patAddState;
	private String patAddStateName;
	private String patAddStateCode;
	private String patAddStreet;
	private String patAddType;
	private String patAddTypeCode;
	private String keywords;
	private String nextVisitCriteria;
	private String nextVisitDate;
	private String nextVisitDuration;
	
	//Added By Akash For IPD Doc Desk
	private String lengthOfStay;
	private String isRural;
	private String relationCode;
	private String isAccepted;
	private String hospServiceCode;
	private String roomNo;
	private String isDoubleOccupancy;
	private String holdingRoom;
	private String policeInformedDate;
	private String mlcApprovedDate;
	private String mlcApprovedBy;
	private String dischargeAdviceBy;
	private String dischargeAdviceDate;
	private String reliefFundCode;
	private String admissionTypeCode;
	private String treatmentResultCode;
	private String admissionCharge;
	private String patMovNo;
	
	public int compareTo(Object o)
	{
		int flag = 0;
		PatientDetailVO patDtlVO = (PatientDetailVO) o;
		
		return flag;
	}

	public int compareByAdmNo(PatientDetailVO patDtlVO)
	{
		int flag = 0;
		
		return flag;
	}

	public int compareByCrno(PatientDetailVO patDtlVO)
	{
		int flag = 0;
		
		return flag;
	}

	public int compareByPatientName(PatientDetailVO patDtlVO)
	{
		int flag = 0;
		
		return flag;
	}

	public int compareByAdmissionDate(PatientDetailVO patDtlVO)
	{
		int flag = 0;
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			Date convertedDate = dateFormat.parse(admDateTime);
			Date convertedDateVO = dateFormat.parse(patDtlVO.getAdmDateTime());

			
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	// Setting Patient Address in Complete in VO
	public void setPatientCompleteAddress()
	{
		StringBuilder sbAdd = new StringBuilder("");
		
		this.patCompleteAddress = sbAdd.toString();
	}

	public String getPatDateOfMenarche()
	{
		return patDateOfMenarche;
	}

	public void setPatDateOfMenarche(String patDateOfMenarche)
	{
		this.patDateOfMenarche = patDateOfMenarche;
	}

	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public String getDepartmentUnitName()
	{
		return departmentUnitName;
	}

	public void setDepartmentUnitName(String departmentUnitName)
	{
		this.departmentUnitName = departmentUnitName;
	}

	public String getPatAdmNo()
	{
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo)
	{
		this.patAdmNo = patAdmNo;
	}

	public String getAdmAdvNo()
	{
		return admAdvNo;
	}

	public void setAdmAdvNo(String admAdvNo)
	{
		this.admAdvNo = admAdvNo;
	}

	public String getAdmDateTime()
	{
		return admDateTime;
	}

	public void setAdmDateTime(String admDateTime)
	{
		this.admDateTime = admDateTime;
	}

	public String getBedAllocDateTime()
	{
		return bedAllocDateTime;
	}

	public void setBedAllocDateTime(String bedAllocDateTime)
	{
		this.bedAllocDateTime = bedAllocDateTime;
	}

	public String getDisDateTime()
	{
		return disDateTime;
	}

	public void setDisDateTime(String disDateTime)
	{
		this.disDateTime = disDateTime;
	}

	public String getDisStatusCode()
	{
		return disStatusCode;
	}

	public void setDisStatusCode(String disStatusCode)
	{
		this.disStatusCode = disStatusCode;
	}

	public String getAdmComplain()
	{
		return admComplain;
	}

	public void setAdmComplain(String admComplain)
	{
		this.admComplain = admComplain;
	}

	public String getDischargeAdvice()
	{
		return dischargeAdvice;
	}

	public void setDischargeAdvice(String dischargeAdvice)
	{
		this.dischargeAdvice = dischargeAdvice;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getIpdRoomCode()
	{
		return ipdRoomCode;
	}

	public void setIpdRoomCode(String ipdRoomCode)
	{
		this.ipdRoomCode = ipdRoomCode;
	}

	public String getBedCode()
	{
		return bedCode;
	}

	public void setBedCode(String bedCode)
	{
		this.bedCode = bedCode;
	}

	public String getIpdRoomName()
	{
		return ipdRoomName;
	}

	public void setIpdRoomName(String ipdRoomName)
	{
		this.ipdRoomName = ipdRoomName;
	}

	public String getBedName()
	{
		return bedName;
	}

	public void setBedName(String bedName)
	{
		this.bedName = bedName;
	}

	public String getOwnDepartmentUnitCode()
	{
		return ownDepartmentUnitCode;
	}

	public void setOwnDepartmentUnitCode(String ownDepartmentUnitCode)
	{
		this.ownDepartmentUnitCode = ownDepartmentUnitCode;
	}

	public String getOwnWardCode()
	{
		return ownWardCode;
	}

	public void setOwnWardCode(String ownWardCode)
	{
		this.ownWardCode = ownWardCode;
	}

	public String getOwnDepartmentCode()
	{
		return ownDepartmentCode;
	}

	public void setOwnDepartmentCode(String ownDepartmentCode)
	{
		this.ownDepartmentCode = ownDepartmentCode;
	}

	public String getMotherAdmNo()
	{
		return motherAdmNo;
	}

	public void setMotherAdmNo(String motherAdmNo)
	{
		this.motherAdmNo = motherAdmNo;
	}

	public String getPatFrom()
	{
		return patFrom;
	}

	public void setPatFrom(String patFrom)
	{
		this.patFrom = patFrom;
	}

	public String getRefAdmNo()
	{
		return refAdmNo;
	}

	public void setRefAdmNo(String refAdmNo)
	{
		this.refAdmNo = refAdmNo;
	}

	public String getAdmStatusCode()
	{
		return admStatusCode;
	}

	public void setAdmStatusCode(String admStatusCode)
	{
		this.admStatusCode = admStatusCode;
	}

	public String getCharge()
	{
		return charge;
	}

	public void setCharge(String charge)
	{
		this.charge = charge;
	}

	public String getIsDead()
	{
		return isDead;
	}

	public void setIsDead(String isDead)
	{
		this.isDead = isDead;
	}

	public String getPatSpouceName()
	{
		return patSpouceName;
	}

	public void setPatSpouceName(String patSpouceName)
	{
		this.patSpouceName = patSpouceName;
	}

	public String getWardName()
	{
		return wardName;
	}

	public void setWardName(String wardName)
	{
		this.wardName = wardName;
	}

	public String getConsultantID()
	{
		return consultantID;
	}

	public void setConsultantID(String consultantID)
	{
		this.consultantID = consultantID;
	}

	public String getConsultantName()
	{
		return consultantName;
	}

	public void setConsultantName(String consultantName)
	{
		this.consultantName = consultantName;
	}

	public String getMlcNo()
	{
		return mlcNo;
	}

	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	public String getIsIpd()
	{
		return isIpd;
	}

	public void setIsIpd(String isIpd)
	{
		this.isIpd = isIpd;
	}

	public String getPatName()
	{
		return patName;
	}

	public void setPatName(String patName)
	{
		this.patName = patName;
	}

	// Setting Patient Name in Complete in VO
	public void setPatientName()
	{
		this.patName = "";
			}

	public String getPatCaste()
	{
		return patCaste;
	}

	public void setPatCaste(String patCaste)
	{
		this.patCaste = patCaste;
	}

	public String getPatFamilyType()
	{
		return patFamilyType;
	}

	public void setPatFamilyType(String patFamilyType)
	{
		this.patFamilyType = patFamilyType;
	}

	public String getPatMotherOccupation()
	{
		return patMotherOccupation;
	}

	public void setPatMotherOccupation(String patMotherOccupation)
	{
		this.patMotherOccupation = patMotherOccupation;
	}

	public String getPatEducationStatus()
	{
		return patEducationStatus;
	}

	public void setPatEducationStatus(String patEducationStatus)
	{
		this.patEducationStatus = patEducationStatus;
	}

	public String getPatSpouseEducationStatus()
	{
		return patSpouseEducationStatus;
	}

	public void setPatSpouseEducationStatus(String patSpouseEducationStatus)
	{
		this.patSpouseEducationStatus = patSpouseEducationStatus;
	}

	public String getPatFatherEducationStatus()
	{
		return patFatherEducationStatus;
	}

	public void setPatFatherEducationStatus(String patFatherEducationStatus)
	{
		this.patFatherEducationStatus = patFatherEducationStatus;
	}

	public String getPatMotherEducationStatus()
	{
		return patMotherEducationStatus;
	}

	public void setPatMotherEducationStatus(String patMotherEducationStatus)
	{
		this.patMotherEducationStatus = patMotherEducationStatus;
	}

	public String getPatDateOfMarriage()
	{
		return patDateOfMarriage;
	}

	public void setPatDateOfMarriage(String patDateOfMarriage)
	{
		this.patDateOfMarriage = patDateOfMarriage;
	}

	public String getPatAgeOfMarriage()
	{
		return patAgeOfMarriage;
	}

	public void setPatAgeOfMarriage(String patAgeOfMarriage)
	{
		this.patAgeOfMarriage = patAgeOfMarriage;
	}

	public String getPatAgeOfMenarche()
	{
		return patAgeOfMenarche;
	}

	public void setPatAgeOfMenarche(String patAgeOfMenarche)
	{
		this.patAgeOfMenarche = patAgeOfMenarche;
	}

	public String getIsActualDateOfMarriage()
	{
		return isActualDateOfMarriage;
	}

	public void setIsActualDateOfMarriage(String isActualDateOfMarriage)
	{
		this.isActualDateOfMarriage = isActualDateOfMarriage;
	}

	public String getPatHusbandBloodGroup()
	{
		return patHusbandBloodGroup;
	}

	public void setPatHusbandBloodGroup(String patHusbandBloodGroup)
	{
		this.patHusbandBloodGroup = patHusbandBloodGroup;
	}

	public String getPatGenderType()
	{
		return patGenderType;
	}

	public void setPatGenderType(String patGenderType)
	{
		this.patGenderType = patGenderType;
	}

	public String getPatIsPregnant()
	{
		return patIsPregnant;
	}

	public void setPatIsPregnant(String patIsPregnant)
	{
		this.patIsPregnant = patIsPregnant;
	}

	public String getPatGestationWeek()
	{
		return patGestationWeek;
	}

	public void setPatGestationWeek(String patGestationWeek)
	{
		this.patGestationWeek = patGestationWeek;
	}

	public String getPatCasteName()
	{
		return patCasteName;
	}

	public void setPatCasteName(String patCasteName)
	{
		this.patCasteName = patCasteName;
	}

	public String getPatCompleteAddress()
	{
		return patCompleteAddress;
	}

	public void setPatCompleteAddress(String patCompleteAddress)
	{
		this.patCompleteAddress = patCompleteAddress;
	}

	public String getRequestBy()
	{
		return requestBy;
	}

	public void setRequestBy(String requestBy)
	{
		this.requestBy = requestBy;
	}

	public String getRequestByName()
	{
		return requestByName;
	}

	public void setRequestByName(String requestByName)
	{
		this.requestByName = requestByName;
	}

	public String getPatAdmittedDays()
	{
		return patAdmittedDays;
	}

	public void setPatAdmittedDays(String patAdmittedDays)
	{
		this.patAdmittedDays = patAdmittedDays;
	}

	public String getWardTypeCode()
	{
		return wardTypeCode;
	}

	public void setWardTypeCode(String wardTypeCode)
	{
		this.wardTypeCode = wardTypeCode;
	}

	public String getIpdRoomTypeCode()
	{
		return ipdRoomTypeCode;
	}

	public void setIpdRoomTypeCode(String ipdRoomTypeCode)
	{
		this.ipdRoomTypeCode = ipdRoomTypeCode;
	}

	public String getBedTypeCode()
	{
		return bedTypeCode;
	}

	public void setBedTypeCode(String bedTypeCode)
	{
		this.bedTypeCode = bedTypeCode;
	}

	public String getPatDiagnosisDtl()
	{
		return patDiagnosisDtl;
	}

	public void setPatDiagnosisDtl(String patDiagnosisDtl)
	{
		this.patDiagnosisDtl = patDiagnosisDtl;
	}

	public String getPatIsDocPresent()
	{
		return patIsDocPresent;
	}

	public void setPatIsDocPresent(String patIsDocPresent)
	{
		this.patIsDocPresent = patIsDocPresent;
	}

	public String getPatChangeType()
	{
		return patChangeType;
	}

	public void setPatChangeType(String patChangeType)
	{
		this.patChangeType = patChangeType;
	}

	public String getIsBedSharable()
	{
		return isBedSharable;
	}

	public void setIsBedSharable(String isBedSharable)
	{
		this.isBedSharable = isBedSharable;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getIsAddressDelhi() {
		return isAddressDelhi;
	}

	public void setIsAddressDelhi(String isAddressDelhi) {
		this.isAddressDelhi = isAddressDelhi;
	}

	public String getIsCurrentAddress() {
		return isCurrentAddress;
	}

	public void setIsCurrentAddress(String isCurrentAddress) {
		this.isCurrentAddress = isCurrentAddress;
	}

	public String getPatAddCity() {
		return patAddCity;
	}

	public void setPatAddCity(String patAddCity) {
		this.patAddCity = patAddCity;
	}

	public String getPatAddCityLoc() {
		return patAddCityLoc;
	}

	public void setPatAddCityLoc(String patAddCityLoc) {
		this.patAddCityLoc = patAddCityLoc;
	}

	public String getPatAddCityLocCode() {
		return patAddCityLocCode;
	}

	public void setPatAddCityLocCode(String patAddCityLocCode) {
		this.patAddCityLocCode = patAddCityLocCode;
	}

	public String getPatAddContactNo() {
		return patAddContactNo;
	}

	public void setPatAddContactNo(String patAddContactNo) {
		this.patAddContactNo = patAddContactNo;
	}

	public String getPatAddCountry() {
		return patAddCountry;
	}

	public void setPatAddCountry(String patAddCountry) {
		this.patAddCountry = patAddCountry;
	}

	public String getPatAddCountryCode() {
		return patAddCountryCode;
	}

	public void setPatAddCountryCode(String patAddCountryCode) {
		this.patAddCountryCode = patAddCountryCode;
	}

	public String getPatAddDistrict() {
		return patAddDistrict;
	}

	public void setPatAddDistrict(String patAddDistrict) {
		this.patAddDistrict = patAddDistrict;
	}

	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}

	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
	}

	public String getPatAddHNo() {
		return patAddHNo;
	}

	public void setPatAddHNo(String patAddHNo) {
		this.patAddHNo = patAddHNo;
	}

	public String getPatAddPIN() {
		return patAddPIN;
	}

	public void setPatAddPIN(String patAddPIN) {
		this.patAddPIN = patAddPIN;
	}

	public String getPatAddState() {
		return patAddState;
	}

	public void setPatAddState(String patAddState) {
		this.patAddState = patAddState;
	}

	public String getPatAddStateName() {
		return patAddStateName;
	}

	public void setPatAddStateName(String patAddStateName) {
		this.patAddStateName = patAddStateName;
	}

	public String getPatAddStateCode() {
		return patAddStateCode;
	}

	public void setPatAddStateCode(String patAddStateCode) {
		this.patAddStateCode = patAddStateCode;
	}

	public String getPatAddStreet() {
		return patAddStreet;
	}

	public void setPatAddStreet(String patAddStreet) {
		this.patAddStreet = patAddStreet;
	}

	public String getPatAddType() {
		return patAddType;
	}

	public void setPatAddType(String patAddType) {
		this.patAddType = patAddType;
	}

	public String getPatAddTypeCode() {
		return patAddTypeCode;
	}

	public void setPatAddTypeCode(String patAddTypeCode) {
		this.patAddTypeCode = patAddTypeCode;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getNextVisitCriteria() {
		return nextVisitCriteria;
	}

	public void setNextVisitCriteria(String nextVisitCriteria) {
		this.nextVisitCriteria = nextVisitCriteria;
	}

	public String getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(String nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}

	public String getNextVisitDuration() {
		return nextVisitDuration;
	}

	public void setNextVisitDuration(String nextVisitDuration) {
		this.nextVisitDuration = nextVisitDuration;
	}

	public String getLengthOfStay() {
		return lengthOfStay;
	}

	public void setLengthOfStay(String lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}

	public String getIsRural() {
		return isRural;
	}

	public void setIsRural(String isRural) {
		this.isRural = isRural;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getHospServiceCode() {
		return hospServiceCode;
	}

	public void setHospServiceCode(String hospServiceCode) {
		this.hospServiceCode = hospServiceCode;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getIsDoubleOccupancy() {
		return isDoubleOccupancy;
	}

	public void setIsDoubleOccupancy(String isDoubleOccupancy) {
		this.isDoubleOccupancy = isDoubleOccupancy;
	}

	public String getHoldingRoom() {
		return holdingRoom;
	}

	public void setHoldingRoom(String holdingRoom) {
		this.holdingRoom = holdingRoom;
	}

	public String getPoliceInformedDate() {
		return policeInformedDate;
	}

	public void setPoliceInformedDate(String policeInformedDate) {
		this.policeInformedDate = policeInformedDate;
	}

	public String getMlcApprovedDate() {
		return mlcApprovedDate;
	}

	public void setMlcApprovedDate(String mlcApprovedDate) {
		this.mlcApprovedDate = mlcApprovedDate;
	}

	public String getMlcApprovedBy() {
		return mlcApprovedBy;
	}

	public void setMlcApprovedBy(String mlcApprovedBy) {
		this.mlcApprovedBy = mlcApprovedBy;
	}

	public String getDischargeAdviceBy() {
		return dischargeAdviceBy;
	}

	public void setDischargeAdviceBy(String dischargeAdviceBy) {
		this.dischargeAdviceBy = dischargeAdviceBy;
	}

	public String getDischargeAdviceDate() {
		return dischargeAdviceDate;
	}

	public void setDischargeAdviceDate(String dischargeAdviceDate) {
		this.dischargeAdviceDate = dischargeAdviceDate;
	}

	public String getReliefFundCode() {
		return reliefFundCode;
	}

	public void setReliefFundCode(String reliefFundCode) {
		this.reliefFundCode = reliefFundCode;
	}

	public String getAdmissionTypeCode() {
		return admissionTypeCode;
	}

	public void setAdmissionTypeCode(String admissionTypeCode) {
		this.admissionTypeCode = admissionTypeCode;
	}

	public String getTreatmentResultCode() {
		return treatmentResultCode;
	}

	public void setTreatmentResultCode(String treatmentResultCode) {
		this.treatmentResultCode = treatmentResultCode;
	}

	public String getAdmissionCharge() {
		return admissionCharge;
	}

	public void setAdmissionCharge(String admissionCharge) {
		this.admissionCharge = admissionCharge;
	}

	public String getPatMovNo() {
		return patMovNo;
	}

	public void setPatMovNo(String patMovNo) {
		this.patMovNo = patMovNo;
	}

}

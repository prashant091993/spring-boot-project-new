package global.Template;

public interface OperationTheatreConfig
{

	
	public String QUERY_FILE_FOR_DAO_OT ="operationTheatre.operationTheatreQuery";
	String CONNECTION_USERNAME="nimsnew"; 
	String CONNECTION_USERPASSWORD="nimsnew";
	String ESSENTIAL_BO_OPTION_ALLDEPT_SESSION="optionAllDepartmentsession";
	String ESSENTIAL_BO_OPTION_PATDTL_SESSION="optionPatDtlSession";
	String ESSENTIAL_BO_OPTION_ALLPATDTL="optionAllDtl";
	String ALL_DEPARTMENT="alldep";
	String MENUCODE="menucode"; // added by shubham saxena
	//String CONNECTION_USERNAME="phdm";  //"mhmis";//"ggsh";
	//String CONNECTION_USERPASSWORD="phdm"; //"mhmis";//"ggsh";
	String CR_NO_FORMAT_SIZE="15";// 12 for PGI and 13 for GNCTD
	String SYSADATEOBJECT="SYSDATEOBJECT";
	String ESSENTIAL_BO_OPTION_ALLOPERATION="allOperation";
	String QUERY_FILE_FOR_MASTERSDAO="operationTheatre.operationTheatreQuery";
	String DEPT_TYPE_CLINICAL_VALUE="1";
	String hospitalCode="HOSPITAL_CODE";
	//String CONNECTION_URL="jdbc:oracle:thin:@10.0.5.167:1521:GGSH";
	String sessionPatientVO="PatientVO";	
	String OPERATION_THEATRE_PATIENTVO="operationTheatrePatientVO";
	String PAC_REQUISITION_VO="pacRequsitionVO";		
	String OPDRAISEDDIAGNOSISDTL="OpdRaisedDiagnosisDtl";
	String OPERATION_RAISEDDIAGNOSISDTL="operationRaisedDiagnosisDtl";
	String OPTION_DESEASE_DTL="OptionDesease_Dtl";
	String OPTION_ANESTHESIA_DTL="OptionAnesthesia_Dtl";
	String OPTION_DIAGNOSIS_TYPE_DETAIL="OptionDiagnosisTypeDetail";	
	String OPERATION_DEPARTMENT="optionDept";	
	String OPTIONSEARCH ="OptionSearch";
	String OPTION_OPERATION_LIST="OperationList";
	String OPTION_OPERATION_LEFT_LIST ="OptionLeftList";
	
	String OPTION_TEMPLATE_LIST ="OptionTemplateList";
	String OPTION_OPERATION_RIGHT_LIST ="OptionRightList";
	String OPTION_OPERATION_SITE_LIST ="OptionSiteList";
	String seatId="SEATID";
	String TRANSACTIONNAME="TransactionName";
	String TRANSACTIONID="TransactionId";
	String DEPARTMENTCODE="departmentCode";
	String DEPARTMENTNAME="departmentName";
	String LISTPAGEDATA="ListPageData";
	String TEMPLATE_TEANSACTION_MENU="template_teansaction_Menu";
	String PATIENT_LIST="PatientList";
	String SELECETED_PATEINT_INFO="SelectedPatientInfo";
	String HISTORYLIST="historylist"; // added by shubham on 11-oct-2017
	String MODE="mode";
	String MENUID="menuId";
	String MENUNAME="menuName";
	String ALLTEMPLATEID="allTemplateId";
	String GETALLTEMPLATES="getallTemplates";
	String LIST_PAC_REQUISITION_VO="listPACReqVO";
	String IS_REVIEW_STATUS="IsReview";
	String OPTION_OPERATIONTYPE="Option_operationType";	

	String POSTOPERATION_DRUG="drugItem";
	String POSTOPERATION_FLUID="fluidItem";
		

	String OTLISTPAGEDATA="OTListPageData";
	String PAC_STATUS_VO="OtListPacDetailVO";
	
	String PAC_COMBO_VO="PacCombo";
	
	String OPTION_OPERATION_LEFT_LIST_OT ="otoperationLeftList";
	String OPTION_OPERATION_RIGHT_LIST_OT ="otoperationRightList";
	
	// is consent list
	String OPTION_OPERATION_CONSENT_LEFT_LIST_OT ="otoperationconsentLeftList";
	String OPTION_OPERATION_CONSENT_RIGHT_LIST_OT ="otoperationsonsentRightList";
	
	
	String OT_STATUS_VO="OtListPacDetailVO1";
	String BEAN_NAME="beanName";
	
	//OT TEMPLATE SAVE 
	String OTLISTRAISING_TRANSACTIONID="5";
	String OTLISTRAISING_MENUID="51";
	// NEW FINAL STATUS
	String STATUS_ACTIVE="1";
	String STATUS_CLOSE="2";
	//String STATUS_REVIEW="3";
	String IS_ONLINE="0";
	String PAC_RAISED="1";
	String PAC_RESULT_ENTERED="2";
	String PAC_RAISED_WITHOUT_RESULT="3";
	String PAC_FAILED="4";
	String PAC_REVIEW="22";
	String OT_RAISED="11";
	String OT_VALIDATED="12";
	String OT_RESULT_ENTERED="13";
	String OT_ANESTHESIA_RECORD_ENTERED="14";
	String OT_POST_OPERATIVE_CARE_UNIT_RECORD_ENTERED="15";
	String OT_POSTUP_ENTERED="18";
	String OT_CANCELLED="16";
	String OT_RESCHEDULED="11";
	String OT_RECORD_ENTRY="9"; 
	
	String PATIENT_REJECTED="0";
	String PATIENT_ACCEPTED="1";	
	String PATIENT_SEND_TO_WARD="2";
	String PACREQUISITION_TYPE_OPD="10";
	String PACREQUISITION_TYPE_IPD="11";
	String PACREQUISITION_TYPE_MLC="12";	
	String OPERATION_TYPE_EMERGENCY="1";
	String OPERATION_TYPE_NORMAL="0";
	String TEMPLATEIDLIST="templateIdList";
	String USERSIGNATURE="userSignature";
	String USERDESIGNATION="userDesignation";
	
	// Patient Condition For Post Order
	
	String PATIENT_AWAKE="0";
	String PATIENT_DROWNSY="1";
	String PATIENT_STABLE="2";
	String PATIENT_UNSTABLE="3";
	String PATIENT_CONCIOUS="4";
	String PATIENT_UNCONCIOUS="5";
	String POSTORDER_DRUGITEMTYPE="110100";
	String POSTORDER_FLUIDITEMTYPE="116100";
	String POSTUP_NEWENTRY="1";
	String POSTUP_MODIFIED="2";
	
	// operation dossier list
	
	String SURGICAL_ITEM_LIST="surgicalItem";
	String SURGICAL_ITEMTYPE_LIST="surgicalItemType";
	String ANESTHESIA_ITEM_LIST="anesthesiaItem";
	String ANESTHESIA_ITEMTYPE_LIST="anesthesiaItemType";
		
		
	String OPTION_MEMBER_LIST ="OptionMemberList";
	String OPTION_TIME_LIST ="OptionTimeList";

	String PDF="1";
	String RTF="2";	
	String TEXT="TEXT";
	String GRAPH="GRAPH";
	String CHOICE_TODAY="T";
	String CHOICE_DATE_WISE="D";
	String OLD="1";
	String NEW="2";
	String SYSDATE = "SYSDATE";
    String SYSDATEOBJECT = "SYSDATEOBJECT";
	//Report Specific file
    String OT_JRXML_PATH="/operationTheatre/reports/jrxml/";
    String  COUNT_PAC_REQ="countPacReq.jrxml";
    String COUNT_PAC_PERFORMED="countPacPerformed.jrxml";
    String LIST_PAC_PERFORMED="listPacPerformed.jrxml";
    String OPERATION_CANCELLED_REPORT="OperationCancelledReport.jrxml";
    String COUNT_OF_CANCELLED_OPERATION="CountOfCancelledOperations.jrxml";
    String DEPARTMENT_WISE_PAC_REQ_DATEWISE="deptWiseListofPacReqDateWise.jrxml";
    String DEPARTMENT_WISE_PAC_REQ_TODAY="deptWiseListofPacReqToday.jrxml";
    String ALLDEPT_PAC_REQ_TODAY="allDeptListofPacReqToday.jrxml";
    String ALLDEPT_PAC_REQ_DATEWISE="allDeptListofPacReqDateWise.jrxml";
    String OT_NOTES_REPORT="OtNotes.jrxml"; 
    String AGE_WISE_PRE_OPERATIVE_DIAGNOSIS_REPORT="AgeWisePreOperativeDiagnosisReport.jrxml";
    String GENDER_WISE_PRE_OPERATIVE_DIAGNOSIS_REPORT="GenderWisePreOperativeDiagnosisReport.jrxml";
    String OT_DEPT_WISE_PAC_STAT_TODAY="countOfPacReqToday.jrxml";
    String FINAL_SUMMERY_REPORT="FinalSummeryReport.jrxml";
    String STATISTCAL_REPORT_OF_OPERATION_PERFORMED="StatisticalReportOfOperationPerformed.jrxml"; 
    String OT_DEPT_WISE_PAC_STAT_DATEWISE="countOfPacReqDateWise.jrxml";
    String OT_DEPT_WISE_ACC_PATIENT="patientAcceptanceDateWise.jrxml";
    String OT_DATE_WISE_SEND_PATIENT="dateWiseSendPatientReport.jrxml";
    String DTL_OF_OP_RAISED ="DtlOfOpRaised.jrxml";
    String OT_CANNED_REPORT ="cannedReport.jrxml";
	String OT_OPTION_ALLDEPARMENT="alldepartment";
	String ESSENTIAL_BO_OPTION_ALLDEPT="optionAllDepartment";
	String OT_OPTION_GROUP="optionGroup";
	String ALLSURGICALLIST="allSurgicalList";
	String ALLANESTHISIALIST="allAnesthisiaList";
	String IS_FROM_OTHER_TRANSAACTION="IS_FROM_OTHER_TRANSAACTION";
	String TEMPLATEID="templateId";
	String ANESTHESIALIST="anesthesiaList";
	String THEATRELIST="theatreList";
	String TABLELIST="tableList";
	String SITELIST="siteList";
	String MEMBERTYPELIST="MEMBERTYPELIST";
	String TIMETYPELIST="TIMETYPELIST";
	String ACTUALOPERATIONLEFTLIST="ACTUALOPERATIONLEFTLIST";
	String ACTUALOPERATIONRIGHTLIST="ACTUALOPERATIONRIGHTLIST";
	String OPERATIONRECORDENTRYVO="OperationRecordEntryVO";
	String SURGEONLIST="SurgeonList";
	String EXISTINGMEMBERLST="ExistingMemberLst";
	String COMBOOFEXISTINGMEMBERLST="ComboOfExistingMemberLst";
	String EXISTINGMEMBERROW="ExistingMemberRow";
	String EXISTINGTIMELST="existingTimeLst";
	String OPERATION_THEATRE_EPISODELIST="OTEpisodeList";
	String HIDEOTREQ="hideOtReq";
	
	
	
	String ANESTHESIALEFTLIST="ANESTHESIALEFTLIST";
	String ANESTHESIARIGHTLIST="ANESTHESIARIGHTLIST";
	
	String OTLISTRAISINGDTLLISTVO="OtListRaisingDtlListVO";
	
	String POSTOPMONITORINGVO="PostOpMonitoringVO";
	String YEARLIST="yearList";
	String DIAG_ROWS="diagRows";
	String APPOINTMENT_ROW="appointmentRows";	
	String OPTION_OPERATION_INIT_RIGHT_LIST="OPTION_OPERATION_INIT_RIGHT_LIST";
	String ISREPORT="isReport";
	String ISTRANSACTIONSPECIFIC="isTransactionSpecific";
	String REPORT_RESULT="reportResult";
	String REPORT_RESULT_REMARK_HEADING="reportResultRemarkHeading";
	String REPORT_RESULT_REMARK="reportResultRemark";
	String PAT_MINOROT_DTL="pat_minorot_dtl";
	
	String ANESTHESIAENTRYMEMBERLST="AnesthesiaEntryMemberLst";
	String OPENTRYMEMBERLST="OpEntryMemberLst";
	String LST_VITALMONITORINGMODIFYLBL="VitalMonitoringModifyLbl";
	
	
	String PAC_DISCLAIMER1=" This fitness is valid for maximum of three months";
	String PAC_DISCLAIMER2=" and is subject to review on the day of operation!";
	String DOSSIEROPERATIONLIST="DossierOperationList";
	String STORECATEGORYLIST="StoreCategoryList";
	String DOSSIERDATALIST="DossierDataList";
	String STOREITEMTYPELIST="StoreItemTypeList";
	String STOREITEMLIST="StoreItemList";
	String REPORT_OT_INSRUMENTTOOLS_REPORTS="InstrumentToolDetailsReport.jrxml";
	
	//PACU
	String DRUGDETAILPRESENT="DRUGDETAILPRESENT";
	 String FLUIDDETAILPRESENT="FLUIDDETAILPRESENT";
	 String INTAKEOTHERDTAILPRESENT="INTAKEOTHERDTAILPRESENT";
	  
	 String URINEDETAILPRESENT="URINEDETAILPRESENT" ;
	 String GASTDEATILPRESENT="GASTDEATILPRESENT" ;
	 String DRAINAGEPRESENT="DRAINAGEPRESENT" ;
	 String OUTTAKEOTHERPRESENT="OUTTAKEOTHERPRESENT" ;
	 
	    
	 String VITALPRESENT="VITALPRESENT" ;
	 String INTAKEPRESENT="INTAKEPRESENT" ;
	 String OUTTAKEPRESENT="OUTTAKEPRESENT" ;
	 

	   
	 
	 String firstLableVitalWidth="firstLableVitalWidth" ;
	 
	 String firstLableIntakeWidth="firstLableVitalWidth" ;
	 String firstLableOuttakeWidth="firstLableOuttakeWidth" ;
	 String secendLableVitalWidth ="secendLableVitalWidth" ;
	 String secondLableIntakeWidth="secondLableIntakeWidth" ;
	 String secondLableOuttakeWidth="secondLableOuttakeWidth" ;
	 
	 String ENTEREDTIMESHEETDETAIL="EnterdTimesSheetDetail";
	 String OTLISTRAISEDDETAIL="OtListRaisedDetail";
	 String CHECKLISTCONSENTDETAIL="ChecklistConsentDetail";
	 
	 String PREVIOUSPACTEMPLATEDETAIL="PreviousPacTemplateDetail";
	 
	 
	 //Vital Monitroing
	 String VITALPARENTNAME="VitalParentName";
	 String VITALENTPARENTNAME="VitalEntParentName";
	 String VITALENTERTIME="VitalEnterTime";
	 
	 String VITALDRUGLST="VitalDrugLst";
	 String VITALFLUIDLST="VitalFluidLst";
	 String VITALCONSIOUSLST="VitalConsiousLst";
	 
	 
	 //OLD
	 String VITALCONFIGURATIONMONITORINGVO="VitalConfigurationMonitoringVO";
	 //NEW
	 String MSTVITALCONFIGURATIONMONITORINGVO="MstVitalConfigurationMonitoringVO";
	 String ENTVITALCONFIGURATIONMONITORINGVO="EntVitalConfigurationMonitoringVO";
	 
	 String VITALCONFIGURATIONCHECKISGRAPH="VitalConfigurationCheckIsGraph";
	 
	 String VITALCONFIGURATIONMSTCHECKVALIDATE="VitalConfigurationMstCheckValidate";
	 
	 String VITALCONFIGURATIONENTCHECKVALIDATE="VitalConfigurationEntCheckValidate";
	 
	 
	 String VITALMONITORINGMODIFYVO="AVitalMonitoringModifyVO";
	 String LST_VITALMONITORINGMODIFYVO="VitalMonitoringModifyVO";
	 String LST_INTAKEMONITORINGMODIFYVO="IntakeMonitoringModifyVO";
	 String LST_OUTAKEMONITORINGMODIFYVO="OuttakeMonitoringModifyVO";
	 
	 String LST_VITALWIDTH="VitalWidth";
	 String LST_INATKEWIDTH="InatkeWidth";
	 String LST_OUTALEWIDTH="OuttakeWidth";
	 
	 
	 String LST_VITALMONITORINGTIME="VitalMonitoringTime";
	 String LST_INTAKEMONITORINGTIME="IntakeMonitoringTime";
	 String LST_OUTTAKEONITORINGTIME="OuttakeMonitoringTime";
	 
	 String LST_VITALMONITORINGDATE="VitalMonitoringDate";
	 String LST_INTAKEMONITORINGDATE="IntakeMonitoringDate";
	 String LST_OUTTAKEONITORINGDATE="OuttakeMonitoringDate";
	 
	 //For Doisser
	 String DOSSIER_PARENTLIST="dossier_Parentlist";	
	 String LISTPROPERTYVALUEVO="listpropertyvaluevo";
	 String PARENTLISTPROPERTYVALUEVO="parentlistpropertyvaluevo";
	 String PROPERTYLIST="PROPERTYLIST";
	 String PARENTLISTPROPERTYVO="PARENTLISTPROPERTYVO";
	 String LISTPROPERTYVO="LISTPROPERTYVO";
	 String LISTCONTROL="LISTCONTROL";
	 String DISPALY_PROPERTY_LIST="DISPALY_PROPERTY_LIST";
	 String DISPLAY_PROPERTY_VALUE_LIST="DISPLAY_PROPERTY_VALUE_LIST";
	 String DEFAULT_PROPERTY_ID ="2,9,20";	 
	 String DEFAULT_PROPERTY_VALUES1="tdfonthead,1,1";
	 String DEFAULT_PROPERTY_VALUES2="tdfont,1,1";
	 String ALLOTREQNOFOROTREPORT="ALLOTREQNOFOROTREPORT";
	 String ALLCONTROLOBJECT="ALLCONTROLOBJECT";
	 String ALLTDOBJECT="ALLTDOBJECT";
	 String TEMPLATEDTL="TEMPLATEDTL"; 
	 
	 String PROPERTYTYPE_TEXT1="STYLE TYPE";
	 String STYLE_TYPE="1";
	 String PROPERTYTYPE_TEXT2="HTML TYPE";
	 String HTML_TYPE="2";
	 String PROPERTYTYPE_TEXT3="DIV TYPE";
	 String DIV_TYPE="3";
	 String PROPERTYTYPE_TEXT4="DIV STYLE TYPE";
	 String DIV_STYLE_TYPE="4";
	 String PROPERTYTYPE_TEXT5="PROPERTY NAME TYPE";
	 String PROPERTY_NAME_TYPE="5";
	 String PROPERTYTYPE_TEXT6="PARENT TYPE";
	 String PARENT_TYPE="6";
	 String PROPERTYTYPE_TEXT7="MULTIROW TYPE";
	 String MULTIROW_TYPE="7";
	 String PROPERTYTYPE_TEXT8="FUNCTION CALL ONKEYPRESS";
	 String FUNCTION_TYPE_CALL_ONKEYPRESS="8";
	 String PROPERTYTYPE_TEXT9="FUNCTION CALL ONCLICK";
	 String FUNCTION_TYPE_CALL_ONCLICK="9";
	 String PROPERTYTYPE_TEXT10="FUNCTION CALL ONBLUR";
	 String FUNCTION_TYPE_CALL_ONBLUR="10";
	 String PROPERTYTYPE_TEXT11="FUNCTION CALL ONCHANGE";
	 String FUNCTION_TYPE_CALL_ONCHANGE="11";	 
	 String PROPERTYTYPE_TEXT12="OTHER";
	 String OTHER_TYPE="12";
	 String LIST_TEMPLATE_CATEGORY="LIST_TEMPLATE_CATEGORY";
	 String LIST_TEMPLATE_SUBCATEGORY="LIST_TEMPLATE_SUBCATEGORY";	
	 String TD_CONTROL_ID="1";
	 String PROPID_NAMEFORMAP="11";
	 String PROPID_PARENTCONTROLVALUE="32";
	 String PROPID_LABELNAME="33";
	 String PROPID_OPTIONS="21";
	 String PROPID_VALUE="8";	 
	 String PROPID_COLSPAN="9";	 
	 String PROPID_ROWSPAN="20";	 
	 String PROPID_FORMULAATTRIBUTE="37";	 
	 String LSTPARENTPROPERTYNAME="LSTPARENTPROPERTYNAME"; 
	 String PAC_REPORT_MENUCODE="19";
	 String DRUG_CATEGORY_ID="10";
	 String DRUG_FLUID_ITEMTYPE_ID="101004";
	 String OTTEMPLATE_ENTRY_MODE="OTTEMPLATEENTRYMODE";
	 String OTTEMPLATE_REPORT_MODE="OTTEMPLATEREPORTMODE";
	 String PARAMETER_STARTS_WITH="parameter_";
	 String OTTEMPLATE_PARAMETER_VALUES_MAP="OTTEMPLATEPARAMETERVALUESMAP"; 
	 
	 
	 String INSRUMENTCOUNTPRINTDTLSVO="InstrumentPrintCountVO";
	 
	 //Report 
	String hospitalName="Guru Gobind Singh Hospital,Raghubir Nagar";
	String hospitalAddress="Delhi,Ph:011-27568684";
		
	String APPT_STATUS_BY_DEPT="1";
	String APPT_STATUS_BY_ALLDEPT ="2";
	String MINOR_OT_PAC_RASING_AUTOMATIC ="1";
	String MINOR_OT_PAC_RASING_MANUAL ="2";
	String MINOROTDEPTLIST="MINOROTDEPTLIST";
	String MINOROTALLOPERATONLIST="MINOROTOPERATONLIST";
	String MINOROTDISPLAYOPERATONLIST="MINOROTDISPLAYOPERATONLIST";
	String MINOROTTHEATERLIST="MINOROTTHEATERLIST";
	String MINOROTSELECTEDOPERATONLIST="MINOROTSELECTEDOPERATONLIST";
	String ALLMINOROTREQUISITIONFORMTEMPLATE="MINOROTREQUISITIONFORMTEMPLATE";
	String MINOROTPREVIOUSOPDTL="MINOROTPREVIOUSOPDTL";
	
	String MINOROT_STATUS_RAISED="1";
	String MINOROT_STATUS_RESULT_ENTRY_DONE="2";
	String MINOROT_STATUS_RESULT_MODIFY_CLOSE="3";
	String MINOROT_STATUS_RESULT_ENTRY_NOTDONE="4";
	String MINOROT_RESULT_ENTRY_CLOSE_DAY_NO="5";
	String MINOROT_RESULT_MODIFY_CLOSE_DAY_NO="1";
	
	String TEMPLATEHTMLMAP="TEMPLATEHTMLMAP";
	String LIST_DOC_TYPE="LIST_DOC_TYPE";
	String STATUS_UNDERREVIEW="3";
	String TRANSPLANTNOLST="TransplantNoLst";
	String PACREMARKSAFTERCLOSE="PacRemarksAfterClose";
	String OTGLOBALHOSPITAL="100";
	String STATUS_REVIEW="3";
	String EPISODE_ISOPEN_OPEN = "1";
	String EPISODE_ISOPEN_CLOSE = "0";
	int allowed_backdays_episode = 1000;
	String databaseDriver = "com.edb.Driver";
//	public static String databaseusername="phdm";//"mhmis";
//	public static String databasepassword="phdm";//"mhmis";
	public static String databaseusername="nims_uat";
	public static String databasepassword="nims_uat";
	
	String QUERY_FILE_FOR_REPORTSDAO="operationTheatre.rpt_ot_query";
	String ESSENTIAL_BO_OPTION_ALLHOSPITAL ="hospitallist";
	String SURGERY_WISE_BOOKING_DTL="SurgeryWiseBookingDtl.jrxml";
	String SURGERY_WISE_BOOKING_DTL_TODAY="SurgeryWiseBookingDtlTODAY.jrxml";
	String DIAGNOSIS_WISE_GENDER_WISE_STATS="GenderWiseDiagnosisStatsDATE.jrxml";
	String DIAGNOSIS_WISE_GENDER_WISE_STATS_TODAY="GenderWiseDiagnosisStatsTODAY.jrxml";
	String OT_NOTES_REPORT_DATEWISE="PatientSurgeryOTNotesDate.jrxml"; 
	String OT_NOTES_REPORT_TODAY="PatientSurgeryOTNotesToday.jrxml"; 
	String PROCEDURE_UPDATE_BILLING="{call Bill_Interface.Dml_Online_Billreq(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	String PROCEDURE_CHECK_BILLING="{call Bill_Interface.func_Online_Bill_dtl(?,?,?,?,?,?)}";
	public String QUERY_FILE_FOR_MST_OT ="operationTheatre.mst_ot_query";
	String MEDICAL_CERTIFICATE_ONLINE_BILL_DTL="bill_interface.func_online_bill_dtl";
	
	// added by yogender yadav on 3-Oct-2017 for upload image
		String PATIENT_IMAGE_DTL_VO_LIST="patientImageDtlVoList";
		String PATIENT_IMAGE_MAP="patientImageMap"; 
		String PATIENT_IMAGE_UPLOADED_IN_SESSION="patientImageInSession";
		String IS_IMAGE_DEFAULT_TRUE="1";
		String YES="1";
		String NO="0";
  }

package global.Template;

public interface Config {
	// ********************************************************************************************
	// CONFIGURABLE FLAGS & PATHS
	// ********************************************************************************************

	// -- Client Configuration
	String CLIENT_GNCTD = "0"; // CLIENT is GNCTD
	String CLIENT_PGIMER = "1"; // CLIENT is PGIMER
	String CLIENT_SMS = "2";
	String CLIENT_AROGYA = "3";
	String CLIENT = CLIENT_AROGYA;
	String SUPER_USER_HOSPITAL_CODE="100";
	// -- Hospital Code Configuration
	String SUPER_HOSPITAL_CODE="100";
	String DEFAULT_HOSPITAL_CODE_VALUE = "101";
	String HOSPITAL_CODE_VALUE = "101";
	
	
	// -- CR Number Size Configuration
	String CR_NO_FORMAT_SIZE_TWELVE = "12"; // value for size 12 PGIMER
	String CR_NO_FORMAT_SIZE_THIRTEEN = "13"; // value for size 13 
	String CR_NO_FORMAT_SIZE = CR_NO_FORMAT_SIZE_THIRTEEN;
	String CR_NO_FORMAT_SIZE_FIFTEEN = "15"; // value for size 15 NIMS AP TS 

	// -- Time Duration For Patient Detail Modification
	String TIME_DURATION_MODIFICATION = "10";

	// -- Emergency Brought By Detail For Registration And 10 Minimum
	// Modification
	String EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE = "1";
	String EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_FALSE = "0";
	String EMG_BROUGHT_BY_DETAIL_FLAG_VALUE = EMG_BROUGHT_BY_DETAIL_FLAG_VALUE_TRUE;
	// EMG_BROUGHT_BY_DETAIL_FLAG_VALUE=0 :: Don't Display Brought By Detail
	// EMG_BROUGHT_BY_DETAIL_FLAG_VALUE=1 :: Display Brought By Detail

	// -- Emergency Brought By Detail For MLC And MLC Modification
	String EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE_TRUE = "1";
	String EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE_FALSE = "0";
	String EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE = EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE_TRUE;
	// EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE=0 :: Don't Display Brought By Detail
	// EMG_BROUGHT_BY_DETAIL_MLC_FLAG_VALUE=1 :: Display Brought By Detail

	// -- File No Generation
	String FILE_NO_GENRATION_FALSE = "0";
	String FILE_NO_GENRATION_MANNUAL_TRUE = "1";
	String FILE_NO_GENRATION_AUTO_TRUE = "2";
	String FILE_NO_GENERATION_FLAG = FILE_NO_GENRATION_AUTO_TRUE;
	// i. FILE_NO_GENERATION_FLAG 0: No Need of File No.
	// ii. FILE_NO_GENERATION_FLAG 1: Manual Allotment of File No.
	// iii. FILE_NO_GENERATION_FLAG 2: Auto Generated File No.
	// ///////////////// File no genration type --> set
	// FILENO_GENRATION_TYPE_AUTO for autogenrated FileNo//////
	String FILENO_GENRATION_TYPE_AUTO = "0";
	// ////////////////File no genration type --> set
	// FILENO_GENRATION_TYPE_MANNUAL for Mannual entry of FileNo//////
	String FILENO_GENRATION_TYPE_MANNUAL = "1";

	// -- File No Visibility Flag --> Assign value true to make it visible
	// otherwise make it false
	boolean FILENO_VISIBILTY_FLAG_ACTIVE = false;

	// -- Color Codification and Priority Order for Patient List at Doctor Desk
	String OPD_PAT_LIST_COLOR_CODE_PRIORITY_ARR[] = { "", "", "", "", "", "" };
	// {"","#0000CD","#0000FF","#7B68EE","#00BFFF","#87CEEB"};
	// "#0000CD" - Medium Blue
	// "#0000FF" - Blue
	// "#7B68EE" - Medium Slate Blue
	// "#00BFFF" - Deep Sky Blue
	// "#87CEEB" - Sky Blue
	String OPD_PAT_LIST_COLOR_CODE_MLC = "#FF0000"; // Red
	String OPD_PAT_LIST_COLOR_CODE_BROUGHT_DEAD = "#FFFFFF"; // White
	String OPD_PAT_LIST_COLOR_CODE_UNKNOWN = "#FFC0CB"; // Pink
	String OPD_PAT_LIST_COLOR_CODE_VISITD = ""; // Light Gray

	String OPD_PAT_LIST_COLOR_CODE_PRIORITY_ARR_ORDER[] = { "0", "0", "0", "0",
			"0", "0" };
	String OPD_PAT_LIST_COLOR_CODE_PRIORITY_ORDER = "10";
	String OPD_PAT_LIST_COLOR_CODE_MLC_ORDER = "20";
	String OPD_PAT_LIST_COLOR_CODE_BROUGHT_DEAD_ORDER = "40";
	String OPD_PAT_LIST_COLOR_CODE_UNKNOWN_ORDER = "30";
	String OPD_PAT_LIST_COLOR_CODE_VISITD_ORDER = "50";

	// -- HIS Resource Bundle
	String RESOURCE_BUNDLE_PROPERTY_FILE_PATH = "hisglobal.hisconfig.hisResourceBundle";

	// **** Storage Paths ************************************************
	// Registration
	String PATIENT_IMAGE_FILE_STORAGE_PATH = "C:/PHDM/AHIMS/PatientImages";
	String PATIENT_IMAGE_FILE_STORAGE_PATH_LINUX = "/PHDM/AHIMS/PatientImages";
	String MLC_DOC_IMAGE_FILE_STORAGE_PATH = "c:/PHDM/AHIMS/MlcDocImages";
	String MLC_DOC_IMAGE_FILE_STORAGE_PATH_LINUX = "/PHDM/AHIMS/MlcDocImages";

	// OPD
	/* Not in Use */
	// String OPD_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH =
	// "c:\\OpdDocumentDIR\\OpdTemplateDIR";
	// String OPD_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_NAME = "OpdTemplate_"; //
	// concatenated with TemplateId int
	// String OPD_PATIENT_PROFILE_STORAGE_PATH = "c:\\OpdPatientProfileDIR";
	// String OPD_EXAMINATION_IMAGE_PATH = "C:/OPDImages/OPDExaminationImages/";
	// String OPD_EXAMINATION_PAT_IMAGE_PATH = "C:/PImage/";
	// String OPD_DOCUMENT_FILE_STORAGE_PATH = "c:\\OpdDocumentDIR";

	// Patient Examination Images Files
	String IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_WINDOWS = "C:/PHDM/AHIMS/PatientExaminationEditorImageDIR";
	String IMAGE_EDITOR_EXAMINATION_PATIENTS_IMAGES_PATH_LINUX = "/PHDM/AHIMS/PatientExaminationEditorImageDIR";
	String IMAGE_EDITOR_EXAMINATION_FILENAME_STARTSWITH = "I"; // concatenated
																// with Par Cr
																// No +
	String IMAGE_EDITOR_EXAMINATION_FILENAME_CONNECTOR = "$"; // concatenated
																// with Serial
																// No +
	String IMAGE_EDITOR_EXAMINATION_FILENAME_EXTENSION = ".png";

	// Document Upload
	String OPD_DOCUMENT_FILE_STORAGE_PATH_WINDOWS = "C:/PHDM/AHIMS/PatDocumentUploadFiles";
	String OPD_DOCUMENT_FILE_STORAGE_PATH_LINUX = "/PHDM/AHIMS/PatDocumentUploadFiles";

	// Image Editor
	String IMAGE_EXAMINATION_IMAGE_PATH_WINDOWS = "C:/PHDM/AHIMS/ImageExaminationFiles";
	String IMAGE_EXAMINATION_IMAGE_PATH_LINUX = "/PHDM/AHIMS/ImageExaminationFiles";

	// Audio Video Player
	String URL_OF_AUDIO_VIDEO_FILE_ON_SERVER = "ftp://administrator:hisopd@10.0.5.177/dir/";
	String OPD_AUDIO_VIDEO_STORAGE_PATH = "C:/PHDM/OPDAV/OPDAudioVideo";
	String OPD_AUDIO_VIDEO_STORAGE_PATH_WINDOWS = "C:/PHDM/AHIMS/AudioVideoPlayerFiles";
	String OPD_AUDIO_VIDEO_STORAGE_PATH_LINUX = "/PHDM/AHIMS/AudioVideoPlayerFiles";

	// Generic Template
	String GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH_WINDOWS = "C:/PHDM/AHIMS/GenericTemplateDIR";
	String GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_PATH_LINUX = "/PHDM/AHIMS/GenericTemplateDIR";

	String GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_NAME = "GenericTemplate_"; // concatenated
																					// with
																					// TemplateId
																					// int
	String GENERIC_TEMPLATE_HTML_DOCUMENT_FILE_STORAGE_EXT = ".htm"; // concatenated
																		// with
																		// Generic
																		// Temp
																		// Name

	String GENERIC_TEMPLATE_IMAGE_VIEW_FILE_STORAGE_NAME = "IMG_"; // concatenated
																	// with
																	// TemplateId
																	// int +"_"+
																	// row_col

	// Generic Patient Profile
	String PATIENT_PROFILE_STORAGE_PATH_WINDOWS = "C:/PHDM/AHIMS/PatientProfileDIR";
	String PATIENT_PROFILE_STORAGE_PATH_LINUX = "/PHDM/AHIMS/PatientProfileDIR";
	String PATIENT_PROFILE_FILE_STORAGE_EXT = ".htm"; // concatenated with
														// Patient Profile Id

	// /Mortuary Patient Image
	String MORTUARY_PATIENT_IMAGE_PATH_WINDOWS = "C:/PHDM/AHIMS/Mortuary";
	String MORTUARY_PATIENT_IMAGE_PATH_LINUX = "/PHDM/AHIMS/Mortuary";


	// -- Registration Renewal Configuration
	String RENEWAL_TYPE = "2";
	// RENEWAL=0 : NO NEED OF RENEWAL
	// RENEWAL=1 : HOSPITAL RENEWAL : MONTH BASIS : EXPIRY WILL SAVE IN
	// HRGT_PATIENT_DTL
	// RENEWAL=2 : HOSPITAL RENEWAL : DAYS BASIS : EXPIRY WILL SAVE IN
	// HRGT_PATIENT_DTL
	// RENEWAL=3 : UNIT WISE RENEWAL: CONSTANT MONTH BASIS SAME AS 1 BUT EXPIRY
	// WILL SAVE IN HRGT_EPISODE_DTL
	// RENEWAL=4 : UNIT WISE RENEWAL: CONSTANT DAYS BASIS SAME AS 1 BUT EXPIRY
	// WILL SAVE IN HRGT_EPISODE_DTL
	// RENEWAL=5 : UNIT WISE RENEWAL MONTH/DAYS BASIS FROM DATABASE, AND EXPIRY
	// WILL SAVE IN HRGT_EPISOE_DTL
	String HOSPITAL_RENEWAL_EXPIRY_MONTH = "31-Dec"; // Format must be like
														// dd-mon
	String HOSPITAL_RENEWAL_EXPIRY_DAY = "15";

	// //This ExpiryDate Is For Bi YearLy registration Used For PGI Only//////
	// /It works For Case 3 only/////////////////
	// ////If in any hospital case 3 is required but Bi yearly Registration is
	// not required
	// /than set both expirt dates same////

	String HOSPITAL_RENEWAL_EXPIRY_MONTH_FIRST = "30-Jun";
	String HOSPITAL_RENEWAL_EXPIRY_MONTH_SECOND = "31-Dec";

	// ////////////////////////////////////////////////////////////////
	// -- Patient Category Employee Configuration
	String PATCAT_EMPLOYEE_DATA_FROM_TABLE_FALSE = "0"; // / Data is not fetched
														// from Table
	String PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE = "1"; // / Data is fetched from
														// table
	String PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE = PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE;
	// / To make fields editable even if data is fetched from table
	// -PATCAT_EMPLOYEE_DATA_FROM_TABLE_VALUE =
	// PATCAT_EMPLOYEE_DATA_FROM_TABLE_FALSE
	String PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE = "0";
	String PATCAT_EMPLOYEE_FIELD_EDITABLE_TRUE = "1";
	String PATCAT_EMPLOYEE_FIELD_VALUE = PATCAT_EMPLOYEE_FIELD_EDITABLE_FALSE;
	// i. PATCAT_EMPLOYEE_FIELD_VALUE 0: Data will be fetched from Employee
	// Table only and Fields are not editable except nationalId,Refer.
	// ii. PATCAT_EMPLOYEE_FIELD_VALUE 1: Data can either fetched from table or
	// entered Manually.
	// 1) If data fetched from table i.e. PATCAT_EMPLOYEE_DATA_FROM_TABLE_TRUE
	// then Fields are not editable except nationalId,Refer
	// 2) If Data entered manually i.e. PATCAT_EMPLOYEE_DATA_FROM_TABLE_FALSE
	// then all fields are editable

	// -- Billing Grouping Configuration
	String REGISTRATION_BILLING_GROUPING_FLAG = "1";
	// Flag = 0 :: For normal billing i.e. without grouping
	// Flag = 1 :: For billing with grouping in case of Referral

	// -- File Number Printing Required
	String PRINT_REQUIRED_TRUE = "1";
	String PRINT_REQUIRED_FALSE = "0";
	String FILE_NUMBER_PRINT_REQUIRED = PRINT_REQUIRED_TRUE;

	// -- OPD Card Printing Required
	String OPD_CARD_PRINTING_REQUIRED = PRINT_REQUIRED_TRUE;

	// -- Print Required for Renewal of Registration
	String CARD_PRINTING_REQUIRED_FOR_RENEWAL = PRINT_REQUIRED_TRUE;

	// -- Number of file number slips to be generated
	String NUMBER_OF_FILE_SLIP_TO_BE_GENERATED = "1";

	// End
	// ****************************************************************************************

	// ****
	String TREE_NAME = "trICD";

	String DIAGNOSIS_TREE = "diagnosistree";

	String CLIENT_NAME = "clientName";
	String DEFAULT_VALUE_CR_NO_FORMAT = "defaultValueCrNoFormat";

	// **** Sorting Flags
	String SORT_TYPE_ASC = "0";
	String SORT_TYPE_DSC = "1";

	// **** Registration Timings
	String REGISTRATION_ALLOWED_TRUE = "1";
	String REGISTRATION_ALLOWED_FALSE = "0";

	// **** Registration Mandatory
	String REGISTRATION_MANDATORY_DEPT_LIST = "registrationMandatoryDeptList";
	String PROPERTIES_FILE_FOR_MANDATORY_FIELD = "registration.MandatoryFields";
	String PROPERTIES_FILE_FOR_REFER_DEPARTMENTS = "registration.ReferDepartments";

	// **** Emergency Brought By Detail For Registration And 10 Minimum
	// Modification
	String EMG_BROUGHT_BY_DETAIL_FLAG = "emgBroughtByDetail";

	// **** Emergency Brought By Detail For MLC And MLC Modification
	String EMG_BROUGHT_BY_DETAIL_MLC_FLAG = "emgBroughtByMlcDetail";

	// **** File No Generation
	String FILE_NO_GENERATION_FLAG_NAME = "fileNoGenrationFlag";
	String FILE_NUMBER_LABEL = "fileNumberLabel"; // temporary file name for
													// label generation

	// **** Report Chart Related
	String QUERY_FILE_FOR_REPORT_CHART = "registration.ReportsChartQuery";
	String UPLOAD_CHART_IMAGE = "chartImage";
	String PDF = "1";
	String RTF = "2";
	String PIE = "1";
	String BAR = "2";
	String LINE = "3";
	String GRAPH = "GRAPH";
	String TEXT = "TEXT";
	String CHOICE_TODAY = "T";
	String CHOICE_DATE_WISE = "D";
	String CHOICE_MONTH_WISE = "M";
	String CHOICE_YEAR_WISE = "Y";
	String OLD = "1";
	String NEW = "2";

	// **** Session Related
	String TRANSACTION_SPECIFIC_SESSION_ITEMS = "Transactionspecificsessionitems";
	String MASTER_SPECIFIC_SESSION_ITEMS = "masterSpecificSessionItems";
	String STATUS_OBJECT = "statusobject";
	String USER_VO = "USERVO";
	String SEAT_ID = "SEATID";

	String HOSPITAL_CODE = "HOSPITAL_CODE";
	String IP_ADDRESS = "IP_ADDR";
	String MENU_ID = "MENUID";
	String SYSDATE = "SYSDATE";
	String SYSDATEOBJECT = "SYSDATEOBJECT";
	String USER_SEAT_ID = "ACTUALSEATID";
	String USER_EMP_ID = "EMPID";
	String USER_FULL_NAME = "UserFullName";
	String USER_LEVEL = "USER_LEVEL";

	// **** Global Query Property File
	String GLOBAL_QUERY_FILE_FOR_GLOBALESSENTIALDAO = "hisglobal.persistence.globalQuery";

	// **** Validation Flags
	String IS_VALID_INACTIVE = "2";
	String IS_VALID_ACTIVE = "1";
	String IS_VALID_DELETED = "0";

	// **** Primary Category
	String PRIMARY_CATEGORY_NORMAL_CODE = "11";
	String PRIMARY_CATEGORY_EMPLOYEE_CODE = "12";
	String PRIMARY_CATEGORY_BPL_CODE="13";
	String PRIMARY_CATEGORY_POOR_FREE_CODE = "13";
	String PRIMARY_CATEGORY_SENIOR_CITIZEN = "14";
	String SENIOR_CITIZEN_AGE = "60";
	String PRIMARY_CATEGORY_STAFF_CODE = "15";

	// **** Order By
	String OPD_PATIENT_OREDRE_BY_QUEUE_NO = "0";
	String OPD_PATIENT_ORDER_BY_VISIT_DATE = "5";
	String OPD_PATIENT_OREDRE_BY_CR_NO = "1";
	String OPD_PATIENT_OREDRE_BY_NAME = "2";
	String OPD_PATIENT_OREDRE_BY_PATIENT_CATEGORY = "3";
	String OPD_PATIENT_OREDRE_BY_COLOR_CODE = "4";
	String OPD_PATIENT_OREDRE_BY_TRIAGE_DURATION = "5";

	String ORDER_BY_ADMISSION_NO = "6";
	String ORDER_BY_ADMISSION_DATE = "7";
	String IPD_PATIENT_OREDRE_BY_NAME = "8";

	String OPD_PATIENT_DESK_NEW = "0";
	String OPD_PATIENT_DESK_OLD = "1";

	// **** REGISTRATION ONLINE_OFFLIINE FLAG
	String REGISTRATION_OFFLINE = "0";
	String REGISTRATION_ONLINE = "1";
	String REGISTRATION_ONLINE_OFFLINE_BOTH = "2";
	String REGISTRATION_ONLINE_OFFLINE_TYPE = REGISTRATION_ONLINE;

	// **** Patient Category Employee Name
	String PATCAT_EMPLOYEE_NAME = "patCatEmployeeName";

	// **** Master Workshop
	String ESSENTIALBO_OPTION_DEPARTMENT = "optionDepartment";
	String ESSENTIALBO_OPTION_DEPT_UNIT = "deptUnit";
	String SEQUENCE_DETAILS_ARR = "sequenceDetails";
	String SEQUENCE_LIST = "sequencelist";
	String ESSENTIAL_BO_WEEKDAY = "weekday";
	String ROSTER_SEQUENCE_DETAILS_ARR = "rostersequenceDetailsarr";
	String ROSTER_SEQUENCE_LIST = "rostersequencelist";

	// ////Color For the project/////////////

	String APPLICATION_COLOR_CODE = "#FFB468";
	// String APPLICATION_COLOR_CODE="#9090b0";////Previous Color of Application
	String IMAGE_FOR_TITLE_TAG = "shd-trans-FFB468.png";

	String IS_LOCATION_COMBO_REQ = "0";// 1-Combo Required YES, 0-Combo Required
										// NO
	String IS_LOCATION_COMBO_REQ_YES = "1";
	String IS_LOCATION_COMBO_REQ_NO = "0";

	// ///Mapping Location With Estate////

	String LOCATION_MAPPING_WITH_ESTATE_REQUIRED = "1"; // 1-Mapping Required
														// YES, 0-Mapping
														// Required NO
	String LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES = "1";
	String LOCATION_MAPPING_WITH_ESTATE_REQUIRED_NO = "0";

	String PATIENT_CAT_TYPE = "0";
	String PATIENT_CAT_TYPE_IS_PAID_NO = "0";

	// ******************* Certificate Related Configuration flags
	// Medical Certificate Generation Option
	String MEDICAL_CERTIFICATE_GENERATION_AUTOMATIC = "1";
	String MEDICAL_CERTIFICATE_GENERATION_MANUAL = "2";
	String MEDICAL_CERTIFICATE_GENERATION = MEDICAL_CERTIFICATE_GENERATION_AUTOMATIC;

	// Medical Certificate Received Option
	String CERTIFICATE_RECEIVED_ONLINE = "1";
	String CERTIFICATE_RECEIVED_OFFLINE = "2";
	String CERTIFICATE_RECEIVED_OPTION = CERTIFICATE_RECEIVED_ONLINE;

	// Medical Certificate Generation Option Back Dated
	String GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_YES = "1";
	String GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_NO = "2";
	String GENERATE_MEDICAL_CERTIFICATE_BACK_DATED = GENERATE_MEDICAL_CERTIFICATE_BACK_DATED_YES;

	// Fitness Certificate Generation Option Back Dated
	String GENERATE_FITNESS_CERTIFICATE_BACK_DATED_YES = "1";
	String GENERATE_FITNESS_CERTIFICATE_BACK_DATED_NO = "2";
	String GENERATE_FITNESS_CERTIFICATE_BACK_DATED = GENERATE_FITNESS_CERTIFICATE_BACK_DATED_YES;

	// Consultant Name Mapping
	String MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_ONLINE = "1";
	String MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE = "2";
	String MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING = MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING_OFFLINE;

	String MARQUE_MESSAGE = "marqueMessage";

	// //////////////User for prompting and forcing the user to change his
	// password after a certain duration//

	int NO_OF_DAYS_ALLOWED_TO_LOGIN_AFTER_PASSWORD_CHANGED = 90;

	int NO_OF_DAYS_BEFORE_TO_ASK_USER_FOR_PASSWORD_CHANGED = 3;

	// ////////////////User Roles and Privelages To be shown through it
	// ////////////////////////////////////////////////

	String LIST_OF_ROLES_CONCATE = "listOfRolesConcate";

	String LIST_OF_ROLES_ID = "listOfRolesId";

	String LIST_OF_ROLES_NAME = "listOfRolesName";

	String LIST_OF_MENUS = "listOfMenus";

	String LIST_OF_PRIVELAGES = "listOfPrivelages";

	String USER_ROLES_LIST = "userRolesList";

	// All Low level Users for the corresponding Group to be shown

	String LIST_OF_ALL_LOW_LEVEL_USERS_FOR_THE_GROUP = "listOfAllLowLevelUsersForTheGroup";
	String RESET_PASSWORD = "123456";

	// //// MLC Detail Process- Police Verification Detail Required///////////
	String MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_YES = "1";
	String MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_NO = "0";
	String MLC_DETAIL_POLICE_VERIFICATION_REQUIRED = MLC_DETAIL_POLICE_VERIFICATION_REQUIRED_NO;

	// //////////Normal Death Body Handover ////////////
	String BODY_HANDOVER_MORTUARY_YES = "1";
	String BODY_HANDOVER_MORTUARY_NO = "0";
	String BODY_HANDOVER_MORTUARY = BODY_HANDOVER_MORTUARY_NO;

	// ////////// Normal Death Body Handover ////////////
	String NORMAL_BODY_HANDOVER_MORTUARY = "1";
	String NORMAL_BODY_HANDOVER_PATIENT = "0";
	String NORMAL_BODY_HANDOVER = NORMAL_BODY_HANDOVER_MORTUARY;

	// ////////// MLC Death Body Handover ////////////
	String MLC_BODY_HANDOVER_MORTUARY = "1";
	String MLC_BODY_HANDOVER_POLICE = "0";
	String MLC_BODY_HANDOVER = MLC_BODY_HANDOVER_MORTUARY;

	// /////////////ANC Detail Options//////////
	String ANC_DETAIL_MINIMUN_AGE_FOR_ANC_ELIGIBILITY = "12";
	String ANC_DETAIL_MAXIMUM_GESTATION_WEEK_FOR_ANC = "40";
	String ANC_DETAIL_MANIMUM_GESTATION_WEEK_FOR_DELIVERY = "24";
	String ANC_DETAIL_MAXIMUM_WEEK_GAP_FOR_OBSTETRIC_DEATH = "6"; // Death
																	// Detail
																	// Mother
																	// Death
																	// Status
																	// Check
	String ANC_DETAIL_MAXIMUM_NO_OF_BIRTH = "6";

	// /////////////Gender Types //////////
	String GENDER_TYPE_MALE = "1";
	String GENDER_TYPE_FEMALE = "2";
	String GENDER_TYPE_OTHER = "3";

	// Added by Akash Singh on date 13-feb-2015
	// /////////////Gender Types CODE FOR NIMS//////////
	String GENDER_TYPE_CODE_MALE = "M";
	String GENDER_TYPE_CODE_FEMALE = "F";
	String GENDER_TYPE_CODE_OTHER = "O";
	
	// ////////////////Image Parameter//////////////////
	String IMAGE_BYTE_ARRAY = "imageByteArray";
	String REQ_PARAMETER_IMAGE_INDEX = "reqParameterImageIndex";
	String IMAGE_BYTE_ARRAY_KEY = "key";
	// ////////////////////////////////

	// ////////////////////Path to save Pdf file for
	// printing////////////////////////////////////
	String PRINT_PDF_FILE_PATH_WINDOWS = "C:/PHDM/printFile";
	String PRINT_PDF_FILE_PATH_LINUX = "/PHDM/printFile";
	String PRINT_PDF_FILE_NAME = "print.pdf";

	// //////////////////////Operating System
	// Type///////////////////////////////////////////
	String OS_TYPE_WINDOWS = "1";
	String OS_TYPE_LINUX = "2";
	String SOFTWARE_LIST_VO = "softwareListVO";

	// //////////Hospital VO for Printing Header
	String HOSPITAL_VO = "hospitalVOForHeader";

	//

	String SL_NO = "1";

	// ////////Disaster Plan Storage Path

	String DISASTER_PLAN_STORAGE_PATH_WINDOW = "C:/PHDM/AHIMS/DisasterPlan";
	String DISASTER_PLAN_STORAGE_PATH_LINUX = "/PHDM/AHIMS/DisasterPlan";

	// Master verification
	String MASTER_VERIFICATION_MODULE_LIST = "masterVerficationModuleList";
	String MASTER_VERIFICATION_VO_LIST = "masterVerficationVOList";
	String MASTER_VERIFICATION_MASTER_OPTION_LIST = "masterOptionList";
	String MASTER_VERIFICATION_ORDERBY_OPTION_LIST = "orderByOptionList";
	String MASTER_VERIFICATION_VO = "masterVerificationVO";
	String MASTER_DATA_LIST = "masterDataList";
	String MASTER_CRITERIA_OPTION_LIST = "masterCriteriaOptionList";
	String MASTER_CRITERIA_DATA = "masterCriteriaDataList";
	String MASTER_DATA_MAP = "masterDataMap";
	String MASTER_LIST = "masterList";
	String MASTER_COLUMN_LIST = "masterColumnList";

	// /////Registration Timine Bound

	String TIME_BOUND_REGISTRATION_REQUIRED_YES = "1";
	String TIME_BOUND_REGISTRATION_REQUIRED_NO = "0";

	String TIME_BOUND_REGISTRATION_REQUIRED = TIME_BOUND_REGISTRATION_REQUIRED_NO;

	// //Module ID
	String MODULE_ID_REGISTRATION = "11";
	String MODULE_ID_EMERGENCY = "12";

	// ////Refer Patient Acceptence Days

	String REFER_PATIENT_ACCEPTENCE_WITHIN_DAYS = "1";

	// ///////Registration Billing Required

	/*
	 * String REGISTRATION_BILLING_REQUIRED_YES="1"; String
	 * REGISTRATION_BILLING_REQUIRED_NO="0"; String
	 * REGISTRATION_BILLING_REQUIRED=REGISTRATION_BILLING_REQUIRED_NO;
	 */

	String PATIENT_AUDIT_LOG_MST_VO_LIST = "patientAuditLogVOList";
	String PATIENT_AUDIT_LOG_DATA = "patientAuditLogData";
	String PATIENT_AUDIT_LOG_OPTIONS = "patientAuditLogOptions";
	String AUDIT_LOG_COLUMN_NAME_LIST = "auditLogColumnNameList";
	String AUDIT_LOG_CURRENT_RECORD_ROW_LIST = "auditLogCurrentRecordRowList";
	String AUDIT_LOG_ROW_LIST = "auditLogRowList";
	String AUDIT_LOG_DISPLAY_LOGIC_NOT_NEEDED = "1"; // if no logic is needed to
														// display the record
	String AUDIT_LOG_DISPLAY_LOGIC_NEEDED = "2"; // to display the entry date
													// and enter by
	String AUDIT_LOG_DISPLAY_LOGIC_NEEDED_FOR_BASERECORD = "3"; // separate
																// Query for the
																// base/first
																// record
	String AUDIT_HEADER_LIST = "auditHeaderList";

	String SYSDATE_LOGIN = "SYSDATELOGIN";
	String SYSDATEOBJECT_LOGIN = "SYSDATEOBJECTLOGIN";

	String[] IS_VALID_ARR = { "Deleted", "Active", "InActive" };
	
	int deploymentMode = 0;
	String CANCELPAGE="CANCELPAGE";
	String PATH_APPLICATION_SERVER_FILE_STORAGE_WINDOWS = "C:/AHIMS";
	String PATH_APPLICATION_SERVER_FILE_STORAGE_LINUX = "/AHIMS";	
	String TARGET_FOLDER_PATIENT_IMAGE = "/PatientImages";
	String PATIENT_IMAGE_FILE_STORAGE_SEPARATOR_NAME_N_SNO = "_";
	String PATIENT_IMAGE_FILE_STORAGE_EXT = ".png";
}

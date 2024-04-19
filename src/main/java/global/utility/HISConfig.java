package global.utility;

import javax.servlet.ServletContext;

public abstract class HISConfig
{
	/* HIS Configuration Settings */
	// JNDI LOOKUP IDS
		// Data Source
	public static String JNDI_LOOKUP_ID_DATASOURCE_OLTP = "AHIS";
	public static String JNDI_LOOKUP_ID_DATASOURCE_OLAP = "AHIS_OLAP";
	public static String JNDI_LOOKUP_ID_DATASOURCE_AUDIT = "AHIS_AUDIT";
	
	// Other Module Context
	public static String HIS_WEB_MODULE_LOGIN = "/AHIMSG5";
	public static String HIS_WEB_MODULE_GLOBAL = "/HIS";
	
	// enum
	public static enum APP_TYPE
	{
		MODULE, GROUP, APPLICATION
	};

	/* Information */
	// Application
	public static String APPLICATION_CONTEXT;
	public static String APPLICATION_DESCRIPTION;
	public static APP_TYPE APPLICATION_TYPE;
	public static String APPLICATION_SERVER_INFO;
	public static String APPLICATION_SERVER_OS;
	public static String APPLICATION_URL;
	public static String APPLICATION_SERVER_URL;

	// Common URLs
	public static String HIS_APPLICATION_ERROR_PAGE_URL = "/application/jsp/app_error_page.jsp";

	// HIS Services
	public static String HIS_SERVICE_OBJECT = "keyHISServiceObject";
	public static String HIS_SERVICES_SERVER_URL = "http://localhost:8081"; //like ""http://10.11.12.13:1415" // Should not be Used for Internal REST Services Call
	public static String HIS_SNOMEDCT_SERVER_URL = "http://localhost:8081"; //like ""http://10.11.12.13:1415" // Should not be Used for Internal REST Services Call
	public static String WATERMARK_IMAGE_FOR_DUPLICATE_REPORT_PRINTING="\\images\\duplicateStamp.jpg";
	public static String IMAGE_FOR_HEADER="\\images\\duplicateStamp.jpg";
	// HIS Login
		// UserVO, HospitalVO, System Date
	public static String USER_VO = "keyUserVO";
	public static String HOSPITAL_VO = "keyHospitalVO";
	public static String SYSDATEOBJECT="keySysDateLogin";
//	public static String CURRENT_YEAR = "keyCurrentYear";
//	public static String CURRENT_MONTH = "keyCurrentMonth";
//	public static String SYSDATE_OBJECT = "keySystemDateObject";

	
	// Common Keys
	public static String YES = "1";
	public static String NO = "0"; 
	
 	//MONGO DB SERVICES ADDED BY PATHAN BASHA ON 17-06-2015
		public static String HIS_MONGODB_SERVER_URL = "";
		public static String HIS_MONGOSERVER_INV_DBNAME = "";
		public static String HIS_MONGOSERVER_INV_CONNECTION = "";

	public abstract boolean setApplictaionInitials(ServletContext objContext);

	public static String HIS_MONGOSERVER_INV_DBNAME_COLLECTION_NAME = "";
}

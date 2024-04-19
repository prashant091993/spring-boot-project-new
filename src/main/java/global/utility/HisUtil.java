/*
 Name		Ajay Kumar Gupta
 */
package  global.utility;

import global.transactionmgnt.HisDAO;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;

/**
 * 
 * Global Class for all kind of His Utilities. The Class contains Various
 * utility methods which are used in various part of HIS Project.
 * 
 * @author Ajay Kumar Gupta <br>
 *         Copyright �C-DAC Noida
 * 
 */
public final class HisUtil {

	/** The ret value. */
	private boolean retValue = false;

	/** The module name. */
	private String moduleName = "";

	/** The file name. */
	private String fileName = "";
	// used for error messages
	/** The err msg. */
	private String errMsg = "";

	// used in getDatePicker() function
	// private static final String jsPath = "../../hisglobal/js/calendar.js";
	// private static final String cssPath =
	// "../../hisglobal/css/calendar-tas.css";
	/** The Constant imgPath. */
	private static final String imgPath = "../../hisglobal/images/imgDatepicker.png";

	// used in auditLog() function
	// private static Context auditLogCtx = null;
	// private static QueueConnectionFactory auditLogConnFactory = null;
	// private static Queue auditLogQueue = null;

	// private static final String AUDITLOG_QUEUE_NAME = "AUDIT_LOG_QUEUE";
	// private static final String AUDITLOG_FACTORY_NAME =
	// "AUDIT_LOG_QUEUE_CONNECTION_FACTORY";
	// private static final String AUDITLOG_PATH = "C:/logs/";
	// StartTime Format: YYYYMMDD hh:mm:ss
	/** The scheduled time. */
	static String scheduledTime = "20120801 15:40:00";

	/** The connection. */
	static HttpURLConnection connection = null;

 
	/**
	 * Constructor.
	 * 
	 * @param moduleName
	 *            -The Current Working Module.
	 * @param fileName
	 *            - The Current Working Java File.
	 */
	public HisUtil(String moduleName, String fileName) {
		this.moduleName = moduleName;
		this.fileName = fileName;
	}

	/**
	 * Returns retValue.
	 * 
	 * @return retValue
	 */
	public boolean getReturnValue() {
		return retValue;
	}

	/**
	 * returns option value for combo/list. qry will be non-parameterized query
	 * and will have only more than two fields, one for option name but could be
	 * more than one for option id. In this case the system will concatenate
	 * fields with ^
	 * 
	 * e.g. if ws has the value dept id, unit id, dept name
	 * 
	 * now we want to select dept name on basis of dept id then set selValueId
	 * is false, If it is true it will compare entire string i.e. dept id ^ unit
	 * id with dept id that will never match
	 * 
	 * @param ws
	 *            WebRowSet which contains the Values.
	 * @param selValue
	 *            If it is <b><code>true</code></b> then it will compare the
	 *            complete string with selValue and if it is <b>
	 *            <code>false</code></b> it will compare first one string with
	 *            selValue.
	 * @param defOption
	 *            If you want to add more than one option then concatenate with
	 *            # e.g. For More than one 0^Select Value#1^All Value [0/1
	 *            represent option value & Select Value/ All Value represent
	 *            option name]. If developer does not define option value then
	 *            the system will start with 0 and then increment by 1
	 * @param concateId
	 *            If it is <b><code>true</code></b> then this function will
	 *            concatenate all the column except the last name which will be
	 *            used for display name. If it is <b><code>false</code></b> then
	 *            this function will assume the first column as ID and the last
	 *            one as display name.
	 * @param selValueId
	 *            If it is <b><code>true</code></b> it will compare entire
	 *            string and if it is <b><code>false</code></b>it will compare
	 *            first column values.
	 * @return Option List in String.
	 * @throws Exception
	 *             if WebRowSet is blank.
	 * 
	 * @see #getOptionValue(WebRowSet, String, String, boolean)
	 */
	public String getOptionValue(WebRowSet ws, String selValue,
			String defOption, boolean concateId, boolean selValueId)
					throws Exception {
		if (selValue == null || selValue.equals("null"))
			selValue = "0";
		String optStr = new String("");
		String optVal = "";
		String optName = "";
		String compStr = "";

		StringBuffer strBuffer = new StringBuffer(1000);
		String[] defOptionStr = null;
		String[] optValueStr = null;

		int i = 0, j = 0;
		int index = 0;
		int colCount = 0;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (ws != null) {
				if (!defOption.equals("")) {
					defOptionStr = defOption.split("#");
					for (j = 0; j < defOptionStr.length; j++) {
						optValueStr = defOptionStr[j].replace('^', '#').split(
								"#");
						if (optValueStr.length == 1) {
							optVal = String.valueOf(index++);
							optName = optValueStr[0];
						} else {
							optVal = optValueStr[0];
							optName = optValueStr[1];
						}

						if (j == 0)
							strBuffer.append("<option #^# title=\"" + optName
									+ "\" value = \"" + optVal + "\">"
									+ optName + "</option>\n");
						else
							strBuffer.append("<option title=\"" + optName
									+ "\" value = \"" + optVal + "\">"
									+ optName + "</option>\n");
					}
				}

				// count the column provided
				colCount = ws.getMetaData().getColumnCount();
				boolean fFlag = false;
				while (ws.next()) {
					optVal = "";
					optName = "";

					// concatenate the id
					if (concateId) {
						for (j = 0; j < (colCount - 1); j++) {
							if (j == 0) {
								optVal = ws.getString(j + 1);
								if (!selValueId)
									compStr = optVal.replace("^", "#").split(
											"#")[0];
							} else
								optVal += "^" + ws.getString(j + 1);
						}
					} else {
						optVal = ws.getString(1);
						if (!selValueId)
							compStr = optVal.replace("^", "#").split("#")[0];

						j = colCount - 1;
					}

					if (selValueId)
						compStr = optVal;

					// option value
					optName = ws.getString(j + 1);
					if ((!selValue.equals("") && selValue.equals(compStr))) {
						strBuffer.append("<option selected title=\"" + optName
								+ "\" value = \"" + optVal + "\">" + optName
								+ "</option>\n");
						fFlag = true;
					} else if ((selValue.equals("") && (i++ == 0) && defOption
							.equals("")))
						strBuffer.append("<option #^# title=\"" + optName
								+ "\" value = \"" + optVal + "\">" + optName
								+ "</option>\n");
					else
						strBuffer.append("<option title=\"" + optName
								+ "\" value = \"" + optVal + "\">" + optName
								+ "</option>\n");
				} // end while block

				optStr = strBuffer.toString();
				if (fFlag) {
					optStr = optStr.replace("#^#", " ");
				} else {
					optStr = optStr.replace("#^#", "selected");
				}
				this.retValue = true;
			} // end if block
			else {
				this.errMsg = "HisUtil.getOptionValue(4 parameters) -->WebRowSet is blank !!";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisUtil.getOptionValue(4 parameters) -->"
					+ e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (strBuffer != null)
				strBuffer = null;
			try {
				if (ws != null) {
					ws.close();
					ws = null;
				}
			} catch (Exception e) {
			}

			defOptionStr = null;
			optValueStr = null;
		}

		return optStr;
	}

	/**
	 * returns the given amount in Decimal Format.
	 * 
	 * @param amount
	 *            amount value in int, float, double.
	 * @param decimalSize
	 *            Size of the decimal in integer, 3 will convert the amount in
	 *            0.000 Format. Negative integer or zero will return the amount
	 *            without Decimals.
	 * @return amount by converting the value in decimal Format by considering
	 *         the decimalSize.
	 * @throws Exception
	 *             the exception
	 * @see #getAmountWithDecimal(String, int)
	 */

	public static String getAmountWithDecimal(double amount, int decimalSize)
			throws Exception {

		String strAmt = "";

		StringBuffer strPattern = new StringBuffer();

		DecimalFormat df = new DecimalFormat();

		if (decimalSize > 0) {

			strPattern.append(".");

			for (int i = 1; i <= decimalSize; i++) {

				strPattern = strPattern.append("0");

			}

		} else {

			strPattern.append("0");

		}

		df.applyPattern(strPattern.toString());

		if (amount == 0) {

			strAmt = "0" + strPattern.toString();

		} else {

			strAmt = df.format(amount);
		}

		if (df != null)

			df = null;

		if (strPattern != null)

			strPattern = null;

		return strAmt;

	}

	/**
	 * returns the given amount in Decimal Format.
	 * 
	 * @param amount
	 *            amount value in String
	 * @param decimalSize
	 *            Size of the decimal in integer, 3 will convert the amount in
	 *            0.000 Format. Negative integer or zero will return the amount
	 *            without Decimals.
	 * @return amount by converting the value in decimal Format by considering
	 *         the decimalSize.
	 * @throws Exception
	 *             when amount value other than Digits.
	 * @see #getAmountWithDecimal(double, int)
	 */

	public static String getAmountWithDecimal(String amount, int decimalSize)
			throws Exception {

		String strAmt = "";

		StringBuffer strPattern = new StringBuffer();

		DecimalFormat df = new DecimalFormat();

		if (decimalSize > 0) {

			strPattern.append(".");

			for (int i = 1; i <= decimalSize; i++) {

				strPattern = strPattern.append("0");

			}

		} else {

			strPattern.append("0");

		}

		df.applyPattern(strPattern.toString());

		if (!amount.equals("0")) {

			strAmt = df.format(Double.parseDouble(amount));

		} else {

			strAmt = "0" + strPattern.toString();
		}

		if (df != null)

			df = null;

		if (strPattern != null)

			strPattern = null;

		return strAmt;

	}

	/**
	 * returns option value for combo/list. qry will be non-parameterized query
	 * and will have only two fields, one for option value and other for option
	 * name.
	 * 
	 * 
	 * @param qry
	 *            SQL Query.
	 * @param selValue
	 *            If it is <b><code>true</code></b> then it will compare the
	 *            complete string with selValue and if it is <b>
	 *            <code>false</code></b> it will compare first one string with
	 *            selValue.
	 * @param defOption
	 *            If you want to add more than one option then concatenate with
	 *            # e.g. For More than one 0^Select Value#1^All Value [0/1
	 *            represent option value & Select Value/ All Value represent
	 *            option name]. If developer does not define option value then
	 *            the system will start with 0 and then increment by 1
	 * @return Option List in String.
	 * @throws Exception
	 *             if Query is Blank.
	 * 
	 * @see #getOptionValue(List, String, String) #getOptionValue(WebRowSet,
	 *      String, String, boolean)
	 * 
	 */
	public String getOptionValue(String qry, String selValue, String defOption)
			throws Exception {
		if (selValue == null || selValue.equals("null"))
			selValue = "0";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String optStr = new String("");
		String optVal = "";
		String optName = "";
		StringBuffer strBuffer = new StringBuffer(1000);
		String[] defOptionStr = null;
		String[] optValueStr = null;

		int i = 0;
		int index = 0;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (!qry.equals("")) {
				// initilize HisDAO object
				daoObj = new HisDAO(this.moduleName, "HisUtil." + this.fileName);
				ws = daoObj.getQryResult(qry);
				if (ws != null && ws.size() > 0) {
					if (!defOption.equals("")) {
						defOptionStr = defOption.split("#");
						for (int j = 0; j < defOptionStr.length; j++) {
							optValueStr = defOptionStr[j].replace('^', '#')
									.split("#");
							if (optValueStr.length == 1) {
								optVal = String.valueOf(index++);
								optName = optValueStr[0];
							} else {
								optVal = optValueStr[0];
								optName = optValueStr[1];
							}

							if (j == 0)
								strBuffer.append("<option title=\"" + optName
										+ "\" selected value = \"" + optVal
										+ "\">" + optName + "</option>\n");
							else
								strBuffer.append("<option title=\"" + optName
										+ "\" value = \"" + optVal + "\">"
										+ optName + "</option>\n");
						}
					}

					while (ws.next()) {
						if ((selValue.equals("") && (i++ == 0) && defOption
								.equals(""))
								|| (selValue != "" && selValue.compareTo(ws
										.getString(1)) == 0))
							strBuffer.append("<option title=\""
									+ ws.getString(2)
									+ "\" selected value = \""
									+ ws.getString(1) + "\">" + ws.getString(2)
									+ "</option>\n");
						else
							strBuffer.append("<option title=\""
									+ ws.getString(2) + "\" value = \""
									+ ws.getString(1) + "\">" + ws.getString(2)
									+ "</option>\n");
					} // end while block

					optStr = strBuffer.toString();
					this.retValue = true;
				} // end if block
				if (ws.size() <= 0) {
					// if(defOption.equals(""))
					strBuffer
					.append("<option title=\"Select Value\" selected value = \"0\">Select Value</option>\n");
					optStr = strBuffer.toString();
					this.retValue = true;
				}
			} else {
				this.errMsg = "HisUtil.getOptionValue(qry) -->Query is blank !!";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisUtil.getOptionValue(qry) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (strBuffer != null)
				strBuffer = null;
			try {
				if (ws != null) {
					ws.close();
					ws = null;
				}
			} catch (Exception e) {
			}

			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}

			defOptionStr = null;
			optValueStr = null;
		}

		return optStr;
	}

	/**
	 * returns option value for combo/list. qry will be non-parameterized query
	 * and will have only two fields, one for option value and other for option
	 * name.
	 * 
	 * @param qry
	 *            SQL Query.
	 * @param selValue
	 *            If it is <b><code>true</code></b> then it will compare the
	 *            complete string with selValue and if it is <b>
	 *            <code>false</code></b> it will compare first one string with
	 *            selValue.
	 * @param defOption
	 *            If you want to add more than one option then concatenate with
	 *            # e.g. For More than one 0^Select Value#1^All Value [0/1
	 *            represent option value & Select Value/ All Value represent
	 *            option name]. If developer does not define option value then
	 *            the system will start with 0 and then increment by 1
	 * @param defValue
	 *            the def value
	 * @return Option List in String.
	 * @throws Exception
	 *             if Query is Blank.
	 * @see #getOptionValue(List, String, String) #getOptionValue(WebRowSet,
	 *      String, String, boolean)
	 */
	public String getOptionValue_DefaultSelected(String qry, String selValue,
			String defOption, String defValue) throws Exception {

		if (selValue == null || selValue.equals("null"))
			selValue = "0";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String lastComboVal = "0";
		// String lastComboVal1 = "0";
		String optStr = new String("");
		String optVal = "";
		String optName = "";
		StringBuffer strBuffer = new StringBuffer(1000);
		String[] defOptionStr = null;
		String[] optValueStr = null;

		int i = 0;
		int index = 0;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (!qry.equals("")) {
				// initilize HisDAO object

				daoObj = new HisDAO(this.moduleName, "HisUtil." + this.fileName);
				ws = daoObj.getQryResult(qry);

				if (ws != null && ws.size() > 0) {
					if (!defOption.equals("")) {
						defOptionStr = defOption.split("#");
						for (int j = 0; j < defOptionStr.length; j++) {
							optValueStr = defOptionStr[j].replace('^', '#')
									.split("#");
							if (optValueStr.length == 1) {
								optVal = String.valueOf(index++);
								optName = optValueStr[0];
							} else {
								optVal = optValueStr[0];
								optName = optValueStr[1];
							}

							if (j == 0) {
								strBuffer.append("<option title=\"" + optName
										+ "\" selected value = \"" + optVal
										+ "\">" + optName + "</option>\n");
								// lastComboVal1 = optVal;
							} else
								strBuffer.append("<option title=\"" + optName
										+ "\" value = \"" + optVal + "\">"
										+ optName + "</option>\n");
						}
					}

					while (ws.next()) {
						if ((selValue.equals("") && (i++ == 0) && defOption
								.equals(""))
								|| (selValue != "" && selValue.compareTo(ws
										.getString(1)) == 0)
										|| defValue.equals("2")) {

							if (selValue.compareTo(ws.getString(1)) == 0) {
								lastComboVal = selValue;
								strBuffer.append("<option title=\""
										+ ws.getString(2)
										+ "\" selected value = \""
										+ ws.getString(1) + "\">"
										+ ws.getString(2) + "</option>\n");
								// lastComboVal1=ws.getString(1) ;
							} else if (ws.isFirst() && defValue.equals("2")) {
								lastComboVal = ws.getString(1);
								strBuffer.append("<option title=\""
										+ ws.getString(2)
										+ "\" selected value = \""
										+ ws.getString(1) + "\">"
										+ ws.getString(2) + "</option>\n");
								// lastComboVal1=ws.getString(1) ;
							} else {
								strBuffer.append("<option title=\""
										+ ws.getString(2) + "\" value = \""
										+ ws.getString(1) + "\">"
										+ ws.getString(2) + "</option>\n");
							}
						} else {
							strBuffer.append("<option title=\""
									+ ws.getString(2) + "\" value = \""
									+ ws.getString(1) + "\">" + ws.getString(2)
									+ "</option>\n");
							if (ws.isFirst() && defValue.equals("2")) {
								lastComboVal = ws.getString(1);
								/*
								 * if(lastComboVal1.equals("0") &&
								 * selValue.equals("0"))
								 * lastComboVal1=ws.getString(1) ; else
								 * lastComboVal1=selValue ;
								 */
							}
						}

					} // end while block

					optStr = strBuffer.toString();
					this.retValue = true;
				} // end if block
				if (ws.size() <= 0) {
					// if(defOption.equals(""))
					strBuffer
					.append("<option title=\"Select Value\" selected value = \"0\">Select Value</option>\n");
					optStr = strBuffer.toString();
					this.retValue = true;
				}
			} else {
				this.errMsg = "HisUtil.getOptionValue(qry) -->Query is blank !!";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisUtil.getOptionValue(qry) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (strBuffer != null)
				strBuffer = null;
			try {
				if (ws != null) {
					ws.close();
					ws = null;
				}
			} catch (Exception e) {
			}

			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}

			defOptionStr = null;
			optValueStr = null;
		}

		return optStr + "$" + lastComboVal;
	}

	/**
	 * returns option value for combo/list. WebRowSet will have only two fields,
	 * one for option value and other for option name
	 * 
	 * 
	 * 
	 * @param data
	 *            List Object.
	 * @param selValue
	 *            If it is <b><code>true</code></b> then it will compare the
	 *            complete string with selValue and if it is <b>
	 *            <code>false</code></b> it will compare first one string with
	 *            selValue.
	 * @param defOption
	 *            If you want to add more than one option then concatenate with
	 *            # e.g. For More than one 0^Select Value#1^All Value [0/1
	 *            represent option value & Select Value/ All Value represent
	 *            option name]. If developer does not define option value then
	 *            the system will start with 0 and then increment by 1
	 * @return Option List in String.
	 * @throws Exception
	 *             if List is blank.
	 * 
	 * @see #getOptionValue(String, String, String) <br>
	 *      #getOptionValue(WebRowSet, String, String, boolean, boolean)
	 * 
	 */
	public String getOptionValue(List<?> data, String selValue, String defOption)
			throws Exception {
		if (selValue == null || selValue.equals("null"))
			selValue = "0";
		String optStr = new String("");
		String optVal = "";
		String optName = "";
		StringBuffer strBuffer = new StringBuffer(1000);

		String[] defOptionStr = null;
		String[] optValueStr = null;

		int i = 0;
		int index = 0;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (data != null) {
				if (!defOption.equals("")) {
					defOptionStr = defOption.split("#");
					for (i = 0; i < defOptionStr.length; i++) {
						optValueStr = defOptionStr[i].replace('^', '#').split(
								"#");
						if (optValueStr.length == 1) {
							optVal = String.valueOf(index++);
							optName = optValueStr[0];
						} else {
							optVal = optValueStr[0];
							optName = optValueStr[1];
						}

						if (i == 0)
							strBuffer.append("<option title=\"" + optName
									+ "\" selected value = \"" + optVal + "\">"
									+ optName + "</option>\n");
						else
							strBuffer.append("<option title=\"" + optName
									+ "\" value = \"" + optVal + "\">"
									+ optName + "</option>\n");
					}
				}

				for (i = 0; i < data.size(); i = i + 2) {
					if ((selValue.equals("") && (i == 0) && defOption
							.equals(""))
							|| (selValue != "" && selValue
							.compareTo((String) data.get(i)) == 0))
						strBuffer.append("<option title=\""
								+ (String) data.get(i + 1)
								+ "\" selected value = \""
								+ (String) data.get(i) + "\">"
								+ (String) data.get(i + 1) + "</option>\n");
					else
						strBuffer.append("<option title=\""
								+ (String) data.get(i + 1) + "\" value = \""
								+ (String) data.get(i) + "\">"
								+ (String) data.get(i + 1) + "</option>\n");
				} // end while block

				optStr = strBuffer.toString();
				this.retValue = true;
			} // end if block
			else {
				this.errMsg = "HisUtil.getOptionValue(ws) -->List is blank !!";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisUtil.getOptionValue(ws) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (strBuffer != null)
				strBuffer = null;
			defOptionStr = null;
			optValueStr = null;
		}

		return optStr;
	}

	/**
	 * returns option value for combo/list. qry will be non-parameterized query
	 * and will have only more than two fields, one for option name but could be
	 * more than one for option id. In this case the system will concatenate
	 * fields with ^
	 * 
	 * e.g. if ws has the value dept id, unit id, dept name
	 * 
	 * now we want to select dept name on basis of dept id then set selValueId
	 * is false, If it is true it will compare entire string i.e. dept id ^ unit
	 * id with dept id that will never match
	 * 
	 * @param ws
	 *            WebRowSet which contains the Values.
	 * @param selValue
	 *            If it is <b><code>true</code></b> then it will compare the
	 *            complete string with selValue and if it is <b>
	 *            <code>false</code></b> it will compare first one string with
	 *            selValue.
	 * @param defOption
	 *            If you want to add more than one option then concatenate with
	 *            # e.g. For More than one 0^Select Value#1^All Value [0/1
	 *            represent option value & Select Value/ All Value represent
	 *            option name]. If developer does not define option value then
	 *            the system will start with 0 and then increment by 1
	 * @param concateId
	 *            If it is <b><code>true</code></b> then this function will
	 *            concatenate all the column except the last name which will be
	 *            used for display name. If it is <b><code>false</code></b> then
	 *            this function will assume the first column as ID and the last
	 *            one as display name.
	 * @return Option List in String.
	 * @throws Exception
	 *             if WebRowSet is blank.
	 * 
	 * @see #getOptionValue(WebRowSet, String, String, boolean, boolean)
	 *      #getOptionValue(List, String, String)
	 * 
	 */
	public String getOptionValue(WebRowSet ws, String selValue,
			String defOption, boolean concateId) throws Exception {
		if (selValue == null || selValue.equals("null"))
			selValue = "0";
		String optStr = new String("");
		String optVal = "";
		String optName = "";
		StringBuffer strBuffer = new StringBuffer(1000);
		String[] defOptionStr = null;
		String[] optValueStr = null;

		int i = 0, j = 0;
		int index = 0;
		int colCount = 0;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (ws != null) {
				if (!defOption.equals("")) {
					// System.out.println("defOption :"+ defOption);
					defOptionStr = defOption.split("#");
					// System.out.println("defOption after split->defOptionStr :"+
					// defOptionStr);
					for (j = 0; j < defOptionStr.length; j++) {

						optValueStr = defOptionStr[j].replace('^', '#').split(
								"#");
						// System.out.println("---defOption after split inside for->defOptionStr["+j+"] :"+
						// optValueStr);
						if (optValueStr.length == 1) {
							optVal = String.valueOf(index++);
							optName = optValueStr[0];
						} else {
							optVal = optValueStr[0];
							optName = optValueStr[1];
						}

						if (j == 0)
							strBuffer.append("<option title=\"" + optName
									+ "\" selected value = \"" + optVal + "\">"
									+ optName + "</option>\n");
						else
							strBuffer.append("<option title=\"" + optName
									+ "\" value = \"" + optVal + "\">"
									+ optName + "</option>\n");
					}
				}

				// count the column provided
				colCount = ws.getMetaData().getColumnCount();

				while (ws.next()) {
					optVal = "";
					optName = "";

					// concatenate the id
					if (concateId) {
						for (j = 0; j < (colCount - 1); j++) {
							if (j == 0)
								optVal = ws.getString(j + 1);
							else
								optVal += "^" + ws.getString(j + 1);
						}
					} else {
						optVal = ws.getString(1);
						j = colCount - 1;
					}

					// option value
					optName = ws.getString(j + 1);

					if ((selValue.equals("") && (i++ == 0) && defOption
							.equals(""))
							|| (selValue != "" && selValue.compareTo(optVal) == 0))
						strBuffer.append("<option title=\"" + optName
								+ "\" selected value = \"" + optVal + "\">"
								+ optName + "</option>\n");
					else
						strBuffer.append("<option title=\"" + optName
								+ "\" value = \"" + optVal + "\">" + optName
								+ "</option>\n");
				} // end while block

				optStr = strBuffer.toString();
				this.retValue = true;
			} // end if block
			else {
				this.errMsg = "HisUtil.getOptionValue(4 parameters) -->WebRowSet is blank !!";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisUtil.getOptionValue(4 parameters) -->"
					+ e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (strBuffer != null)
				strBuffer = null;
			/*
			 * try { if(ws != null) { ws.close(); ws = null; } } catch(Exception
			 * e) {}
			 */

			defOptionStr = null;
			optValueStr = null;
		}

		return optStr;
	}

	/**
	 * Convert number to word.
	 * 
	 * @param number
	 *            the number
	 * @return the string
	 */
	public String convertNumberToWord(int number) {
		String convertedNumber = "";

		if (number <= 0) {
			System.out.println("Enter numbers greater than 0");
		} else {
			convertedNumber = number_conversion((number / 1000000000),
					" Hundred");
			convertedNumber = number_conversion((number / 10000000) % 100,
					" crore");
			convertedNumber = number_conversion(((number / 100000) % 100),
					" lakh");
			convertedNumber = number_conversion(((number / 1000) % 100),
					" thousand");
			convertedNumber = number_conversion(((number / 100) % 10),
					" hundred");
			convertedNumber = number_conversion((number % 100), " ");
		}

		return convertedNumber;
	}

	/**
	 * Number_conversion.
	 * 
	 * @param number
	 *            the number
	 * @param ch
	 *            the ch
	 * @return the string
	 */
	public String number_conversion(int number, String ch) {
		String result;
		String one[] = { " ", " one", " two", " three", " four", " five",
				" six", " seven", " eight", " Nine", " ten", " eleven",
				" twelve", " thirteen", " fourteen", "fifteen", " sixteen",
				" seventeen", " eighteen", " nineteen" };
		String ten[] = { " ", " ", " twenty", " thirty", " forty", " fifty",
				" sixty", "seventy", " eighty", " ninety" };

		if (number > 19) {
			result = ten[number / 10] + " " + one[number % 10];
			System.out.print(result);
		} else {
			result = one[number];
			System.out.print(result);
		}

		if (number > 0) {
			System.out.print(ch);
		}

		return result;
	}

	/**
	 * Returns amount in words.
	 * 
	 * @param amt
	 *            amount value
	 * @return amount in words.
	 */

	public String getAmountStr(String amt) {

		WebRowSet ws = null;
		HisDAO daoObj = null;
		String amtStr = "";
		String sql = "";
		String rupees = "";
		String paisa = "";
		int pos = -1;

		if (Double.parseDouble(amt) == 0)
			return "Zero Rupee Only";
		if (Double.parseDouble(amt) < 0)
			amt = amt.substring(1);

		rupees = amt;
		pos = amt.indexOf(".");

		if (pos != -1) {
			rupees = amt.substring(0, pos);
			paisa = amt.substring(pos + 1);
			if (paisa.length() >= 2)
				paisa = amt.substring(pos + 1, pos + 3);
			else
				paisa = amt.substring(pos + 1) + "0";

			if (!paisa.equals(""))
				if (Integer.parseInt(paisa) == 0)
					paisa = "";
		}

		if (!rupees.equals(""))
			if (Double.parseDouble(rupees) == 0)
				rupees = "";

		// execte function that converts amount in word
		if (paisa.equals(""))
			sql = "SELECT INITCAP(DIGITWORD('" + rupees
			+ "')||' '||'RUPEES ONLY') FROM DUAL";
		else {
			if (rupees.equals(""))
				sql = "SELECT  INITCAP(DIGITWORD('" + paisa
				+ "')||' '||'PAISA ONLY') FROM DUAL";
			else {
				sql = "SELECT  INITCAP(DIGITWORD('" + rupees
						+ "')||' '||'RUPEES AND '||DIGITWORD('" + paisa
						+ "')||' '||'PAISA ONLY') FROM DUAL";
			}
		}

		try {
			if (!sql.equals("")) {
				// initilize HisDao object
				daoObj = new HisDAO("global", "ReportFunctionUtil");
				ws = daoObj.getQryResult(sql);
				if (ws != null) {
					if (ws.next()) {
						amtStr = ws.getString(1);

					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (ws != null) {
					ws.close();
					ws = null;
				}
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return amtStr;
	}

	/*
	 * public String getAmountStr(String amt) throws Exception {
	 * 
	 * WebRowSet ws = null; HisDAO daoObj = null; String amtStr = ""; String sql
	 * = ""; String rupees = ""; String paisa = ""; int pos = -1;
	 * 
	 * this.retValue = false; this.errMsg = "";
	 * 
	 * try {
	 * 
	 * if (Double.parseDouble(amt) == 0) return "Zero Rupee Only"; if
	 * (Double.parseDouble(amt) < 0) amt = amt.substring(1);
	 * 
	 * rupees = amt; pos = amt.indexOf(".");
	 * 
	 * 
	 * if (pos != -1) { rupees = amt.substring(0, pos); paisa =
	 * amt.substring(pos + 1);
	 * 
	 * if (!paisa.equals("")){
	 * 
	 * //if(paisa.length() > 2) paisa = paisa.substring(0, 2);
	 * 
	 * if (Double.parseDouble(paisa) == 0) paisa = "";
	 * 
	 * }
	 * 
	 * }
	 * 
	 * if (!rupees.equals("")) if (Double.parseDouble(rupees) == 0) rupees = "";
	 * 
	 * // System.out.println("rupees :: "+rupees + "    paise :: "+paisa);
	 * 
	 * // execte function that converts amount in word if (paisa.equals("")) sql
	 * = "SELECT INITCAP( DIGITWORD('" + rupees +
	 * "')||' '||'RUPEES ONLY' ) FROM DUAL"; else { if (rupees.equals("")) sql =
	 * "SELECT INITCAP(DIGITWORD(RPAD(SUBSTR(TO_CHAR(ROUND('." + paisa +
	 * "',2)),2),2,'0'))) FROM DUAL "; else { sql =
	 * "SELECT INITCAP( DIGITWORD('" + rupees +
	 * "')||' '||'RUPEES AND '||DIGITWORD(RPAD(SUBSTR(TO_CHAR(ROUND('." + paisa
	 * + "',2)),2),2,'0'))" + "||' '||'PAISA ONLY' ) FROM DUAL"; } }
	 * 
	 * 
	 * if (!sql.equals("")) { // initilize HisDao object daoObj = new
	 * HisDAO("dynamicreport", "ReportFunctionUtil"); ws =
	 * daoObj.getQryResult(sql); if (ws != null) { if (ws.next()) { amtStr =
	 * ws.getString(1); // this.retValue = true; } } } } catch (Exception e) {
	 * this.errMsg = "HisUtil.getAmountStr() -->" + e.getMessage(); throw new
	 * Exception(this.errMsg); } finally { try { if (ws != null) { ws.close();
	 * ws = null; } if (daoObj != null) { daoObj.free(); daoObj = null; } }
	 * catch (Exception e) { } }
	 * 
	 * return amtStr; }
	 */

	/**
	 * returns string in initcap [e.g. Ajay Kumar Gupta]
	 * 
	 * @param myString
	 *            String.
	 * @return InitCap String.
	 * @throws Exception
	 *             the exception
	 */
	public String toInitCap(String myString) throws Exception {

		StringTokenizer st = new StringTokenizer(myString, " ");

		String initCapStr = "";
		String temp1 = "";
		String temp = "";

		this.retValue = false;
		this.errMsg = "";

		try {
			while (st.hasMoreTokens()) {
				temp = st.nextToken();
				temp1 = temp.substring(0, 1).toUpperCase();
				temp1 = temp1 + temp.substring(1).toLowerCase();
				initCapStr = initCapStr + temp1 + " ";
			}

			this.retValue = true;
		} catch (Exception e) {
			this.errMsg = "HisUtil.toInitCap() -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			st = null;
		}

		return initCapStr;
	}

	/**
	 * Returns key value based on key name [keyName should be integer] lstData
	 * should have two fields first for key Value and last for Key Name The Data
	 * in lstData should be either in ascending order or descending order
	 * 
	 * This method uses Binary Search Algorithm.
	 * 
	 * @param lstData
	 *            List
	 * @param keyName
	 *            keyName in Int.
	 * @param dataOrder
	 *            <b>0</b> Ascending order and <b>1</b> Descending order.
	 * @return String keyValue based on keyName
	 * @throws Exception
	 *             the exception
	 * @see #search(List, String, int)
	 */
	public String search(List<?> lstData, int keyName, int dataOrder)
			throws Exception {

		String keyValue = "No Data Found !!";

		int recSize = 0;
		int lowValue = 0;
		int midValue = 0;
		int highValue = 0;
		int index = 0;
		int tempKeyName = 0;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (lstData != null) {

				recSize = lstData.size() / 2; // record count
				lowValue = 0;
				highValue = recSize;

				if (recSize > 0) {
					while (lowValue <= highValue) {
						midValue = (int) ((lowValue + highValue) / 2);
						index = (midValue * 2 - 1);

						if (index < 0)
							index = 1;
						tempKeyName = Integer.parseInt((String) lstData
								.get(index));
						if (keyName == tempKeyName) {
							keyValue = (String) lstData.get(index - 1);
							break;
						} else {
							if (dataOrder == 0) { // data is in ascending
								// order
								if (keyName > tempKeyName)
									lowValue = midValue + 1;
								else
									highValue = midValue - 1;
							} else { // data is in descending order
								if (keyName > tempKeyName)
									highValue = midValue - 1;
								else
									lowValue = midValue + 1;
							}
						}
					} // end while loop
				} // end if block (recSize > 0)
			} // end if block
			this.retValue = true;
		} catch (Exception e) {
			this.errMsg = "HisUtil.search(int)-->" + e.getMessage();
			throw new Exception(this.errMsg);
		}

		return keyValue;
	}

	/**
	 * Returns key value based on key name [keyName should be integer] lstData
	 * should have two fields first for key Value and last for Key Name The Data
	 * in lstData should be either in ascending order or descending order
	 * 
	 * This method uses Binary Search Algorithm.
	 * 
	 * @param lstData
	 *            List
	 * @param keyName
	 *            String keyName
	 * @param dataOrder
	 *            <b>0</b> Ascending order and <b>1</b> Descending order.
	 * @return String keyValue based on keyName
	 * @throws Exception
	 *             the exception
	 * @see #search(List, int, int)
	 */
	public String search(List<?> lstData, String keyName, int dataOrder)
			throws Exception {

		String keyValue = "No Data Found !!";
		String tempKeyName = "";

		int recSize = 0;
		int lowValue = 0;
		int midValue = 0;
		int highValue = 0;
		int index = 0;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (lstData != null) {

				recSize = lstData.size() / 2; // record count
				lowValue = 0;
				highValue = recSize;

				if (recSize > 0) {
					while (lowValue <= highValue) {
						midValue = (int) ((lowValue + highValue) / 2);
						index = (midValue * 2 - 1);

						if (index < 0)
							index = 1;
						tempKeyName = (String) lstData.get(index);
						if (keyName.equals(tempKeyName)) {
							keyValue = (String) lstData.get(index - 1);
							break;
						} else {
							if (dataOrder == 0) { // data is in ascending
								// order
								if (keyName.compareTo(tempKeyName) > 0)
									lowValue = midValue + 1;
								else
									highValue = midValue - 1;
							} else { // data is in descending order
								if (keyName.compareTo(tempKeyName) > 0)
									highValue = midValue - 1;
								else
									lowValue = midValue + 1;
							}
						}
					} // end while loop
				} // end if block (recSize > 0)
			} // end if block
			this.retValue = true;
		} catch (Exception e) {
			this.errMsg = "HisUtil.search(String) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		}

		return keyValue;
	}

	/**
	 * Returns Application Server Date dtFormat --> date format[dd/MM/yyyy,
	 * dd/MMM/yyyy etc]
	 * 
	 * if dtFormat is blank then default format is dd/MM/yyyy.
	 * 
	 * @param dtFormat
	 *            Date Format in String.
	 * @return Application Server Date in required Format.
	 * @throws Exception
	 *             the exception
	 * @see #getDSDate(String)
	 */
	public String getASDate(String dtFormat) throws Exception {

		String dtStr = "";
		String defFormat = "";
		Calendar c = null;
		SimpleDateFormat dateFormat = null;

		this.retValue = false;
		this.errMsg = "";

		defFormat = dtFormat;
		if (defFormat.equals(""))
			defFormat = "dd/MM/yyyy";

		try {
			c = Calendar.getInstance();
			dateFormat = new SimpleDateFormat(defFormat);
			dtStr = dateFormat.format(c.getTime());
			this.retValue = true;
		} catch (Exception e) {
			this.errMsg = "HisUtil.getASDate() -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (c != null) {
				c.clear();
				c = null;
			}
			if (dateFormat != null)
				dateFormat = null;
		}

		return dtStr;
	}

	/**
	 * Returns Database Server Date dtFormat --> date format[dd/mm/yyyy,
	 * dd/mon/yyyy etc]
	 * 
	 * if dtFormat is blank then default format is dd/mm/yyyy.
	 * 
	 * @param dtFormat
	 *            Date Format in String.
	 * @return Database Server Date in required Format.
	 * @throws Exception
	 *             the exception
	 * @see #getASDate(String)
	 */
	public String getDSDate(String dtFormat) throws Exception {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String dtStr = "";
		String qry = "";
		String defFormat = "";

		this.retValue = false;
		this.errMsg = "";

		defFormat = dtFormat;
		if (defFormat.equals(""))
			defFormat = "dd/mm/yyyy";
		qry = "SELECT TO_CHAR(SYSDATE,'" + defFormat + "') FROM DUAL";

		try {
			// initilize HisDAO object
			daoObj = new HisDAO(this.moduleName, "HisUtil." + this.fileName);
			ws = daoObj.getQryResult(qry);
			if (ws != null) {
				if (ws.next()) {
					dtStr = ws.getString(1);
					this.retValue = true;
				}
			}
		} catch (Exception e) {
			this.errMsg = "HisUtil.getDSDate()-->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (ws != null) {
					ws.close();
					ws = null;
				}
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			} catch (Exception e) {
			}
		}

		return dtStr;
	}

	/**
	 * Returns date picker string.
	 * 
	 * @param fieldName
	 *            Field Name of The Date Picker
	 * @param dateValue
	 *            Value of the Date Picker.
	 * @param readOnly
	 *            true or false.
	 * @return String Date Picker.
	 * 
	 * @see #getDatePicker(String, String, String, boolean)
	 */

	public static String getDatePicker(String fieldName, String dateValue,
			boolean readOnly) {
		String dateString = "";
		StringBuilder strBuffer = new StringBuilder(500);
		strBuffer.append((new StringBuilder(
				" <input size='10%' type=\"text\" name=\"")).append(fieldName)
				.append("\" id=\"").append(fieldName).append("\" ").toString());
		if (readOnly)
			strBuffer.append(" readonly = \"false\" ");
		strBuffer.append((new StringBuilder(" value='")).append(dateValue)
				.append("'>").toString());
		strBuffer
		.append((new StringBuilder(
				" <img name=\""
						+ fieldName
						+ "DateImage\" src=\"../../hisglobal/images/imgDatepicker.png\" id=\""))
						.append(fieldName)
						.append("1\" style=\"cursor: pointer; border: 1px solid red;\" title=\"Date selector\" ")
						.toString());
		strBuffer
		.append(" onmouseover=\"Calendar.datePickerAjax(event,document.forms[0]."
				+ fieldName
				+ "),this.style.background='red';\" onmouseout=\"this.style.background=''\"> \n");
		/*
		 * strBuffer.append(
		 * "<script event=\"Click()\" language=\"JavaScript\"> \"+Calendar.setup({ "
		 * ); strBuffer.append((new
		 * StringBuilder(" inputField : \"")).append(fieldName
		 * ).append("\",ifFormat : \"%d-%b-%Y\",button : \""
		 * ).append(fieldName).append("1\",singleClick : true\n").toString());
		 * strBuffer.append("})\";</script>\n");
		 */
		dateString = strBuffer.toString();
		strBuffer = null;
		return dateString;
	}

	/*
	 * public static String getDatePicker(String fieldName, String dateValue,
	 * boolean readOnly) {
	 * 
	 * String dateString = "";
	 * 
	 * StringBuilder strBuffer = new StringBuilder(500);
	 * 
	 * strBuffer.append((new StringBuilder(
	 * " <input size='9%' type=\"text\" name=\"")).append(fieldName)
	 * .append("\" id=\"").append(fieldName).append("\" ").toString());
	 * 
	 * if (readOnly)
	 * 
	 * strBuffer.append(" readonly = \"false\" ");
	 * 
	 * strBuffer.append((new StringBuilder(" value='")).append(dateValue)
	 * .append("'>").toString());
	 * 
	 * strBuffer .append((new StringBuilder(
	 * " <img src=\"../../hisglobal/images/imgDatepicker.png\" id=\""))
	 * .append(fieldName) .append(
	 * "1\" style=\"cursor: pointer; border: 1px solid red;\" title=\"Date selector\" "
	 * ) .toString());
	 * 
	 * strBuffer
	 * .append(" onmouseover=\"Calendar.datePickerAjax(event,document.forms[0]."
	 * + fieldName +
	 * "),this.style.background='red';\" onmouseout=\"this.style.background=''\"> \n"
	 * );
	 * 
	 * dateString = strBuffer.toString();
	 * 
	 * strBuffer = null;
	 * 
	 * return dateString; }
	 */
	/**
	 * Returns date picker string.
	 * 
	 * @param fieldName
	 *            Field Name of The Date Picker
	 * @param dateValue
	 *            Value of the Date Picker.
	 * @param idValue
	 *            Id value of Date Picker.
	 * @param readOnly
	 *            true or false.
	 * 
	 * @return String Date Picker.
	 * 
	 * @see #getDatePicker(String, String, boolean)
	 */
	public static String getDatePicker(String fieldName, String dateValue,
			String idValue, boolean readOnly) {

		String dateString = "";
		StringBuilder strBuffer = new StringBuilder(500);

		// strBuffer.append("<script language=\"JavaScript\" src=\"" + jsPath +
		// "\"></script>\n");
		// strBuffer.append("<link href=\"" + cssPath + "\" rel=\"stylesheet\"
		// type=\"text/css\">\n");

		strBuffer.append("<input size='9%' type=\"text\" name=\"" + fieldName
				+ "\" id=\"" + (fieldName + idValue) + "\" class=\"dtBox\" ");
		if (readOnly)
			strBuffer.append(" readonly ");
		strBuffer.append("value='" + dateValue + "'>");
		strBuffer
		.append("<img src=\""
				+ imgPath
				+ "\" id=\""
				+ (fieldName + idValue)
				+ "1\" align=\"absmiddle\" style=\"cursor: pointer; border: 1px solid red;\" title=\"Date selector\" ");

		strBuffer
		.append(" onmouseover=\"Calendar.datePickerAjax(event,document.forms[0]."
				+ fieldName
				+ "),this.style.background='red';\" onmouseout=\"this.style.background=''\"> \n");

		/*
		 * strBuffer .append("<script language=\"JavaScript\">\n
		 * Calendar.setup\n(\n{ \n"); strBuffer.append("inputField : \"" +
		 * (fieldName + idValue) + "\",ifFormat : \"%d-%b-%Y\",button : \"" +
		 * (fieldName + idValue) + "1\",singleClick : true\n");
		 * strBuffer.append("}\n);\n</script>\n");
		 */
		dateString = strBuffer.toString();
		strBuffer = null;

		return dateString;
	}

	/**
	 * Gets the multi row date picker.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param dateValue
	 *            the date value
	 * @param idValue
	 *            the id value
	 * @param readOnly
	 *            the read only
	 * @return the multi row date picker
	 */
	public static String getMultiRowDatePicker(String fieldName,
			String dateValue, String idValue, boolean readOnly) {

		String dateString = "";
		StringBuilder strBuffer = new StringBuilder(500);

		strBuffer
		.append("<input size='10%' type=\"text\" class=\"txtFldDatePickerNormal\" name=\""
				+ fieldName
				+ "\" id=\""
				+ idValue
				+ "\" readonly=\"true\" value=''> "
				+ "	<img tabindex=1 src=\"../../hisglobal/images/iconPicDate.gif\" id=\"img"
				+ idValue
				+ "1\" style=\"cursor: pointer; border: 1px solid red;\"  "
				+ "	title=\"Date selector\"  "
				+ "	onmouseover=\"this.style.background='red';\"  "
				+ "	onmouseout=\"this.style.background=''\"> "
				+ "	<script language=\"JavaScript\" > "
				+ "Calendar.setup"
				+ "("
				+ " {"
				+ " inputField     :    \""
				+ idValue
				+ "\","
				+ "eventName : \"click\","
				+ " ifFormat       :    \"%d-%b-%Y\","
				+ " button         :    \"img"
				+ idValue
				+ "1\","
				+ " singleClick    :    true"
				+ " }"
				+ ");"
				+ "Calendar.setup"
				+ "("
				+ " {"
				+ " inputField     :    \""
				+ idValue
				+ "\","
				+ "eventName : \"keypress\",mapKey : \"32\","
				+ " ifFormat       :    \"%d-%b-%Y\","
				+ " button         :    \"img"
				+ idValue
				+ "1\","
				+ " singleClick    :    true" + " }" + ");</script>");

		dateString = strBuffer.toString();
		strBuffer = null;

		return dateString;
	}

	/**
	 * Gets the multi row date picker with js function.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param dateValue
	 *            the date value
	 * @param idValue
	 *            the id value
	 * @param readOnly
	 *            the read only
	 * @param jsFuncName
	 *            the js func name
	 * @return the multi row date picker with js function
	 */
	public static String getMultiRowDatePickerWithJsFunction(String fieldName,
			String dateValue, String idValue, boolean readOnly,
			String jsFuncName) {

		String dateString = "";
		StringBuilder strBuffer = new StringBuilder(500);

		strBuffer
		.append("<input size='10%' type=\"text\" class=\"txtFldDatePickerNormal\" name=\""
				+ fieldName
				+ "\" id=\""
				+ idValue
				+ "\" onBlur='"
				+ jsFuncName
				+ "' value=''> "
				+ "	<img tabindex=1 src=\"../../hisglobal/images/iconPicDate.gif\" id=\"img"
				+ idValue
				+ "1\" style=\"cursor: pointer; border: 1px solid red;\"  "
				+ "	title=\"Date selector\"  "
				+ "	onmouseover=\"this.style.background='red';\"  "
				+ "	onmouseout=\"this.style.background=''\"> "
				+ "	<script language=\"JavaScript\" > "
				+ "Calendar.setup"
				+ "("
				+ " {"
				+ " inputField     :    \""
				+ idValue
				+ "\","
				+ "eventName : \"click\","
				+ " ifFormat       :    \"%d-%b-%Y\","
				+ " button         :    \"img"
				+ idValue
				+ "1\","
				+ " singleClick    :    true"
				+ " }"
				+ ");"
				+ "Calendar.setup"
				+ "("
				+ " {"
				+ " inputField     :    \""
				+ idValue
				+ "\","
				+ "eventName : \"keypress\",mapKey : \"32\","
				+ " ifFormat       :    \"%d-%b-%Y\","
				+ " button         :    \"img"
				+ idValue
				+ "1\","
				+ " singleClick    :    true" + " }" + ");</script>");

		dateString = strBuffer.toString();
		strBuffer = null;

		return dateString;
	}


	/**
	 * function that appends space up to given length.
	 * 
	 * @param strValue
	 *            String
	 * @param len
	 *            length
	 * @param mode
	 *            0 - Left Align 1 - Right Align
	 * @return space Appended String.
	 */
	public String appendSpace(String strValue, int len, int mode) {

		String retStrValue = "";

		if (mode == 0) {

			retStrValue = appendSpaceAtEnd(strValue, len);

		} else {

			retStrValue = appendSpaceAtBegining(strValue, len);

		}

		return retStrValue;
	} // end function

	/**
	 * Append space at begining.
	 * 
	 * @param strValue
	 *            the str value
	 * @param len
	 *            the len
	 * @return the string
	 */
	public String appendSpaceAtBegining(String strValue, int len) {

		String retStrValue = "";
		int diffLen = 0;
		int i = 0;

		diffLen = len - strValue.length();

		if (diffLen > 0) {
			for (i = 0; i < diffLen; i++)
				retStrValue += " ";
		}

		retStrValue = retStrValue + "" + strValue;

		return retStrValue;
	} // end function

	/**
	 * Append space at end.
	 * 
	 * @param strValue
	 *            the str value
	 * @param len
	 *            the len
	 * @return the string
	 */
	public String appendSpaceAtEnd(String strValue, int len) {

		String retStrValue = "";
		int diffLen = 0;
		int i = 0;

		diffLen = len - strValue.length();
		retStrValue = strValue;

		if (diffLen > 0) {
			for (i = 0; i < diffLen; i++)
				retStrValue += " ";
		}

		return retStrValue;
	} // end function

	/*
	 * function that breaks up the string based on given length [len --> total
	 * length, symbol --> used if string breaks up to identify the string] len
	 * should be greater than 2
	 */
	/**
	 * function that breaks up the string based on given length.
	 * 
	 * @param strValue
	 *            source String.
	 * @param len
	 *            total length and should be greater than 2.
	 * @param symbol
	 *            the symbol
	 * @return list of String
	 */
	public List<String> breakString(String strValue, int len, String symbol) {

		List<String> retStrValue = new ArrayList<String>();
		String tempStr = "";
		int diffLen = 0;

		tempStr = strValue;

		while (true) {
			diffLen = tempStr.length() - len;
			if (diffLen > 0) { // break up
				retStrValue.add(tempStr.substring(0, len - 2) + " " + symbol);
				tempStr = tempStr.substring(len - 2);
			} else {
				retStrValue.add(appendSpace(tempStr, len, 0));
				break;
			}
		}

		return retStrValue;
	} // end function

	/**
	 * used to read the entire contents from the file.
	 * 
	 * @param filePath
	 *            Complete Path of the File.
	 * @return file content in String.
	 * @throws Exception
	 *             If File Does't Exist.
	 * @see #writeFile(String, String)
	 */
	public String readFile(String filePath) throws Exception {

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		File f = null;
		int j = 0;
		boolean eof = false;
		StringBuffer strBuff = null;

		this.errMsg = "";

		try {
			if (!filePath.trim().equals("")) {
				fis = new FileInputStream(filePath);
				bis = new BufferedInputStream(fis);
				f = new File(filePath);
				strBuff = new StringBuffer((int) f.length());

				while (!eof) {
					j = bis.read();
					if (j == -1)
						eof = true;
					else
						strBuff.append((new Character((char) j)).toString());
				}
			}
		} catch (Exception e) {
			this.errMsg = "HisUtil.readFile()-->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (bis != null)
					bis.close();
				if (fis != null)
					fis.close();
				f = null;
				fis = null;
				bis = null;
			} catch (Exception e) {
			}
		}

		return strBuff.toString();
	} // end

	/**
	 * This function is used to write the contents into file. It will always
	 * overwrite into file
	 * 
	 * @param contents
	 *            String Contents.
	 * @param filePath
	 *            Complete File Path.
	 * @return true if File Write Successfully otherwise false.
	 * @throws Exception
	 *             the exception
	 * @see #readFile(String)
	 */
	public boolean writeFile(String contents, String filePath) throws Exception {

		FileWriter FR = null;
		BufferedWriter OS = null;
		boolean retValue = false;
		this.errMsg = "";

		try {
			FR = new FileWriter(filePath, false);
			OS = new BufferedWriter(FR);
			OS.write(contents);
			retValue = true;
		} catch (Exception e) {
			this.errMsg = "HisUtil.writeFile()-->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (OS != null)
					OS.close();
				if (FR != null)
					FR.close();

				OS = null;
				FR = null;
			} catch (Exception e) {
			}
		}

		return retValue;
	}

	/**
	 * This method is used to send the message to JMS server. It accepts the
	 * following parameter
	 * 
	 * 
	 * Currently the method is commented b'coz Tomcat is not application server
	 * String
	 * 
	 * @param session
	 *            this is session object which is created at the time of login.
	 *            So you don't worry about its attributes.
	 * @param modName
	 *            Name of Module.
	 * @param task
	 *            Name of process.
	 * @param tabFieldInfo
	 *            This field will have the table.field details with unique index
	 *            that will be used with linking with oldValue/newValue
	 *            1^tableName^fieldName#2^tableName^fieldName
	 * @param oldValue
	 *            This field will have the old value in the following format if
	 *            there is more than one row then each & every row will be
	 *            concatenated with # symbol
	 *            1^value1^value2^value3...#2^value1^value2....
	 * @param newValue
	 *            This field will have the new value in the following format if
	 *            there is more than one row then each & every row will be
	 *            concatenated with # symbol
	 *            1^value1^value2^value3...#2^value1^value2....
	 * @param remarks
	 *            There is not necessary that index order should be same with
	 *            tabFieldInfo/oldVale/newValue
	 * @param fileName
	 *            Audit Log File Name (do n't specify the path) remarks : Any
	 *            other info that you want to display [you can pass primary key]
	 * @return true if successfully Logged, false otherwise.
	 * @throws Exception
	 *             the exception
	 */
	public static boolean auditLog(HttpSession session, String modName,
			String task, String tabFieldInfo, String oldValue, String newValue,
			String remarks, String fileName) throws Exception {

		boolean retVal = false;
		// String errorStr = "";

		/*
		 * commented b'coz Tomacat is not application server String userName =
		 * ""; String ipAddr = ""; String auditMsg = "";
		 * 
		 * QueueConnection auditLogConn = null; QueueSession auditLogSession =
		 * null; QueueSender audiLogSender = null; MapMessage audiLogInfo =
		 * null;
		 * 
		 * try { //extract module/process name from session userName =
		 * (String)session.getAttribute("userName"); ipAddr =
		 * (String)session.getAttribute("IP_ADDR");
		 * 
		 * if(userName == null) userName = ""; if(ipAddr == null) ipAddr = "";
		 * 
		 * if(fileName.equals("") || userName.equals("") || modName.equals(""))
		 * { errorStr = "HisUtil.auditLog()-->FileName/user/module is blank";
		 * throw new Exception(errorStr); }
		 * 
		 * //java.util.Hashtable props = new java.util.Hashtable();
		 * //props.put("java.naming.factory.initial",
		 * "com.pramati.naming.client.PramatiClientContextFactory");
		 * //props.put("java.naming.provider.url", "rmi://10.103.0.18:9191");
		 * //props.put("java.naming.provider.url", "rmi://localhost:9191");
		 * //props.put(Context.SECURITY_PRINCIPAL,"root");
		 * //props.put(Context.SECURITY_CREDENTIALS,"pramati");
		 * //props.put("com.pramati.naming.realm","system");
		 * 
		 * 
		 * //getting connection factory & queue reference //if(auditLogCtx ==
		 * null) auditLogCtx = new InitialContext(props); if(auditLogCtx ==
		 * null) auditLogCtx = new InitialContext(); if(auditLogConnFactory ==
		 * null) auditLogConnFactory = (QueueConnectionFactory)
		 * auditLogCtx.lookup(AUDITLOG_FACTORY_NAME); if(auditLogQueue == null)
		 * auditLogQueue = (Queue) auditLogCtx.lookup(AUDITLOG_QUEUE_NAME);
		 * 
		 * //getting queue connection auditLogConn =
		 * auditLogConnFactory.createQueueConnection(); auditLogSession =
		 * auditLogConn.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
		 * audiLogSender = auditLogSession.createSender(auditLogQueue);
		 * audiLogInfo = auditLogSession.createMapMessage();
		 * 
		 * //create message that is to be sent to JMS server
		 * audiLogInfo.setString("1",modName); audiLogInfo.setString("2",task);
		 * audiLogInfo.setString("3",userName);
		 * audiLogInfo.setString("4",ipAddr);
		 * audiLogInfo.setString("5",tabFieldInfo);
		 * audiLogInfo.setString("6",oldValue);
		 * audiLogInfo.setString("7",newValue);
		 * audiLogInfo.setString("8",remarks);
		 * audiLogInfo.setString("9",AUDITLOG_PATH + fileName);
		 * 
		 * audiLogSender.send(audiLogInfo); retVal = true; }
		 * catch(NamingException ne) { errorStr = "HisUtil.auditLog()-->" +
		 * ne.getMessage(); throw new Exception(errorStr); } catch(JMSException
		 * je) { errorStr = "HisUtil.auditLog() -->" + je.getMessage(); throw
		 * new Exception(errorStr); } finally { try { if(auditLogConn != null)
		 * auditLogConn.close(); if(auditLogSession != null)
		 * auditLogSession.close(); if(audiLogSender != null)
		 * audiLogSender.close();
		 * 
		 * auditLogConn = null; auditLogSession = null; audiLogSender = null;
		 * audiLogInfo = null; } catch(Exception e) { errorStr =
		 * "HisUtil.auditLog()-->" + e.getMessage(); throw new
		 * Exception(errorStr); } }
		 */

		return retVal;
	}

	  

	/**
	 * Gets the parameter from his path xml.
	 * 
	 * @param _Param
	 *            the _ param
	 * @return the parameter from his path xml
	 */
	public static String getParameterFromHisPathXML(String _Param) {
		String osType = null;
		String paramValue = null;
		try {
			osType = System.getProperties().getProperty("os.name");
			
			if (osType.startsWith("Win")) {
				_Param += "_WIN";
				paramValue = getParameterFromXML("C:/IMCS/INTEG_WAR/hisPath.xml", _Param);
			} else {
				_Param += "_LIN";
				paramValue = getParameterFromXML("/opt/IMCS/INTEG_WAR/hisPath.xml", _Param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return paramValue;
	}

	/*
	 * public static String getParameterFromXML1(String _XMLpath,String _Param){
	 * String parameterValue = null; DocumentBuilderFactory dbf = null;
	 * DocumentBuilder db = null; Document document = null; File file = null;
	 * NodeList nodeList = null; Node node = null; try{ dbf =
	 * DocumentBuilderFactory.newInstance(); db = dbf.newDocumentBuilder(); file
	 * = new File(_XMLpath); document = db.parse(file); nodeList =
	 * document.getElementsByTagName(_Param); node=nodeList.item(0);
	 * parameterValue = node.getFirstChild().getNodeValue(); }catch(Exception
	 * e){ dbf = null; db = null; document = null; file = null; nodeList = null;
	 * node = null; } return parameterValue; }
	 */
	/**
	 * Gets the parameter from xml.
	 * 
	 * @param _XMLpath
	 *            the _ xm lpath
	 * @param _Param
	 *            the _ param
	 * @return the parameter from xml
	 */
	public static String getParameterFromXML(String _XMLpath, String _Param) {
		String strResult = "";
		SaxHandler sax = null;

		try {
			sax = new SaxHandler();
			strResult = sax.getParameter(_XMLpath, _Param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sax = null;
		}
		return strResult;
	}

	/**
	 * This method will set all the null fields (String Type) of a VO object to
	 * empty String.
	 * 
	 * @param currentObject_p
	 *            the current object_p
	 */
	public static void replaceNullValueWithEmptyString(Object currentObject_p) {

		Class<? extends Object> currentClass = currentObject_p.getClass();

		Method[] methods = currentClass.getMethods();
		Method setterMethod = null;
		String getterName;
		String setterName;
		try {
			for (Method method : methods) {
				Class<?> returnType = method.getReturnType();
				if (returnType.equals(String.class)) {

					getterName = method.getName();
					setterName = getterName.replaceFirst("get", "set");

					String objVal = (String) method.invoke(currentObject_p);
					if (objVal == null) {
						setterMethod = currentClass.getMethod(setterName,
								String.class);
						setterMethod.invoke(currentObject_p, "");
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method will print all the String fields of VO. This is used for
	 * debugging.
	 * 
	 * @param currentObject_p
	 *            the current object_p
	 */
	public static void printStringFieldsOfVO(Object currentObject_p) {

		class MethodNameComparator implements Comparator<Method> {

			public int compare(Method o1, Method o2) {
				if (o1 instanceof Method && o2 instanceof Method) {

					String s1 = o1.getName();
					String s2 = o2.getName();

					return s1.compareTo(s2);
				}
				return 0;
			}
		}

		Class<? extends Object> currentClass = currentObject_p.getClass();

		Method[] methods = currentClass.getMethods();

		String getterName;
		String fieldName;
		try {
			Arrays.sort(methods, new MethodNameComparator());
			for (Method method : methods) {
				Class<?> returnType = method.getReturnType();
				if (returnType.equals(String.class)) {

					getterName = method.getName();
					fieldName = getterName.replaceFirst("get", "");
					fieldName = fieldName.substring(0, 1).toLowerCase()
							+ fieldName.substring(1);

					String objVal = (String) method.invoke(currentObject_p);
					System.out.println(fieldName + " = " + objVal);
				}
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Authored by: Ranjan Kumar, Kapil Kant Kamal, Manish Kumar Contributors:
	 * MSDP Team Contact: msdp@cdac.in Website: http://mgov.gov.in
	 * 
	 * HTTP API lets departments send across SMS messages using HTTP URL
	 * interface. The API supports SMS push (Single SMS and Bulk SMS) and SMS
	 * Scheduling
	 * 
	 * Sender ID: Sender ID or CLI (Caller Line Identification) is limited to 8
	 * characters in the API. According to TRAI regulations, there will be a 2
	 * character prefix when delivered to the phone. For example if you are
	 * passing the Sender ID as �cdac_mum�, you'll may the SMS delivered as
	 * AD-cdac_mum or TA-cdac_mum according the route SMS Gateway chooses.
	 * 
	 * Message Length: For standard character set 160 characters per SMS is
	 * supported. If a message is sent, whose length is longer than permitted
	 * characters limit, it shall be broken into multiple messages. You can
	 * submit up to 480 characters in one API request. username : Specify the
	 * username provided(10-15 characters) password : Password attached to the
	 * username(only alphanumeric, max 10 characters) message : The SMS Text you
	 * want to submit numbers : The set of mobile numbers to broadcast the above
	 * SMS content. You can pass 10 or 12 digit mobile numbers in comma
	 * seperated format. Eg : 895123456,9847123456,919809123456 senderid :
	 * Sender id should have 6 characters only and only alphabets are allowed no
	 * numbers or special characters, all should be uppercase as per new TRAI
	 * regulations starttime: For schedule message in WSDL service. Mention this
	 * for scheduling messages. Messages will be sent at the set time TIME
	 * FORMAT YYYYMMDD hh:mm:ss and time GMT ie IST - 05:30 hours for example if
	 * you want to schedule sms for Jan 1 2009 08:00:00PM you should enter start
	 * time as 2009-01-01 14:30:00 endtime : Leave this field blank, not
	 * relevant currently messages : This is for using sendpairedsms method in
	 * webservice. <message> <text>Test Message</text>
	 * <numbers>919000000000</numbers> </message>
	 * ------------------------------------------------------------------------
	 * Response Code Meaning 401 Credentials Error, may be invalid username or
	 * password 402, X X messages submitted successfully
	 * 
	 * 403 Credits not available
	 * 
	 * 404 Internal Database Error
	 * 
	 * 405 Internal Networking Error
	 * 
	 * 406 Invalid or Duplicate numbers
	 * 
	 * 407, 408 Network Error on SMSC
	 * 
	 * 409 SMSC response timed out, message will be submitted 410 Internal Limit
	 * Exceeded, Contact support
	 * 
	 * 411, 412 Sender ID not approved.
	 * 
	 * 413 Suspect Spam, we do not accept these messages.
	 * 
	 * 414 Rejected by various reasons by the operator such as DND, SPAM etc
	 */

	/**
	 * Send sms.
	 * 
	 * @param username
	 *            the username 
	 * @param password
	 *            the password
	 * @param senderId
	 *            the sender id
	 * @param mode
	 *            the mode 1 - send single sms, 2 - send bulk sms, 3 - send schdeuled sms
	 * @param mobileNos
	 *            the mobile nos
	 * @param message
	 *            the message
	 * @param scheduledTime
	 *            the scheduled time - required in case of mode 3
	 * @throws Exception
	 *             the exception
	 */
	public static void SendSMS(String username, String password,
			String senderId, String mode, String mobileNos, String message,
			String scheduledTime) throws Exception {
		try {

			URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			HttpURLConnection.setFollowRedirects(true);

			if (mode.equals("1")) {
				if (!username.equals("0") && !password.equals("0")
						&& !senderId.equals("0")) {
					connection = sendSingleSMS(mobileNos, message, username,
							password, senderId);
				}
			}
			if (mode.equals("2")) {
				if (!username.equals("0") && !password.equals("0")
						&& !senderId.equals("0")) {
					connection = sendBulkSMS(mobileNos, message, username,
							password, senderId);
				}

			}
			if (mode.equals("3")) {
				if (!username.equals("0") && !password.equals("0")
						&& !senderId.equals("0")) {
					connection = sendScheduledSMS(mobileNos, message,
							scheduledTime, username, password, senderId);
				}

			}

			// System.out.println("Resp Code:" + connection.getResponseCode());
			// System.out.println("Resp Message:"+
			// connection.getResponseMessage());

		}

		catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	// method for sending bulk SMS
	/**
	 * Send bulk sms.
	 * 
	 * @param mobileNos
	 *            the mobile nos
	 * @param message
	 *            the message
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param senderId
	 *            the sender id
	 * @return the http url connection
	 */
	@SuppressWarnings("deprecation")
	public static HttpURLConnection sendBulkSMS(String mobileNos,
			String message, String username, String password, String senderId) {
		try {
			// System.out.println("Username==>"+username+"::PWD::"+password+"::SENDER ID::"+senderId+"::No::"+mobileNos+"::Msg::"+message);
			String smsservicetype = "bulkmsg"; // For bulk msg
			String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message) + "&bulkmobno="
					+ URLEncoder.encode(mobileNos, "UTF-8") + "&senderid="
					+ URLEncoder.encode(senderId);

			connection.setRequestProperty("Content-length",
					String.valueOf(query.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(
					connection.getOutputStream());

			output.writeBytes(query);
			// output.close();

			System.out.println("In HisUtil sendBulkSMS Resp Code:"
					+ connection.getResponseCode());
			System.out.println("In HisUtil sendBulkSMS Resp Message:"
					+ connection.getResponseMessage());

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(
					connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
		} catch (Exception e) {
			System.out.println("Something bad just happened.");
			System.out.println(e);
			e.printStackTrace();
		}
		return connection;
	}

	// method for sending the scheduled SMS
	/**
	 * Send scheduled sms.
	 * 
	 * @param mobileNos
	 *            the mobile nos
	 * @param message
	 *            the message
	 * @param scheduledTime
	 *            the scheduled time
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param senderId
	 *            the sender id
	 * @return the http url connection
	 */
	@SuppressWarnings("deprecation")
	public static HttpURLConnection sendScheduledSMS(String mobileNos,
			String message, String scheduledTime, String username,
			String password, String senderId) {
		try {

			String smsservicetype = "schmsg"; // For Scheduled message.

			String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message) + "&bulkmobno="
					+ URLEncoder.encode(mobileNos, "UTF-8") + "&senderid="
					+ URLEncoder.encode(senderId) + "&time="
					+ URLEncoder.encode(scheduledTime, "UTF-8");

			connection.setRequestProperty("Content-length",
					String.valueOf(query.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(
					connection.getOutputStream());

			output.writeBytes(query);
			// output.close();

			System.out.println("Resp Code:" + connection.getResponseCode());
			System.out.println("Resp Message:"
					+ connection.getResponseMessage());

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(
					connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
		} catch (Exception e) {
			System.out.println("Something bad just happened.");
			System.out.println(e);
			e.printStackTrace();
		}
		return connection;
	}

	// Method for sending single SMS.
	/**
	 * Send single sms.
	 * 
	 * @param mobileNo
	 *            the mobile no
	 * @param message
	 *            the message
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param senderId
	 *            the sender id
	 * @return the http url connection
	 */
	@SuppressWarnings("deprecation")
	public static HttpURLConnection sendSingleSMS(String mobileNo,
			String message, String username, String password, String senderId) {
		try {

			String smsservicetype = "singlemsg"; // For single message.
			String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message) + "&mobileno="
					+ URLEncoder.encode(mobileNo) + "&senderid="
					+ URLEncoder.encode(senderId);

			connection.setRequestProperty("Content-length",
					String.valueOf(query.length()));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(
					connection.getOutputStream());

			output.writeBytes(query);
			// output.close();

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(
					connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
		} catch (Exception e) {
			System.out.println("Something bad just happened.");
			System.out.println(e);
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * Gets the md5 hash.
	 * 
	 * @param strContent
	 *            the str content
	 * @return the md5 hash
	 */
	public static String getMd5Hash(String strContent) {
		// strContent = strContent.trim();
		System.out.println("length is" + strContent.length());
		StringBuffer sb = new StringBuffer("");

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strContent.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}


	/**
	 * Gets the config param value.
	 *
	 * @param strMode : 1
	 * @param strHospCode : Hospital Code
	 * @param strParamName : available parameter names are
	 * 							<br> HSTSTR_PARAM_NAME <br>
	 * 		CONDEMN_REJECTDRUG_DAYS <br>
	 * 		DB_LINK <br>
	 * 		DDW_SUGGEST_QTY_MODIFY_PERIOD <br>
	 * 		DEMAND_ACTIVE_DURATION<br>
	 * 		DRUG_ITEM_TYPE_CODE<br>
	 * 		FTP_ADDRESS<br>
	 * 		FTP_ADDRESS11<br>
	 * 		ISSUE_PAT_HEADER<br>
	 * 		ISSUE_PAT_HEADER1<br>
	 * 		LOCAL_PURCHASE_CODE<br>
	 * 		MODIFY_PERIOD<br>
	 * 		PENELTY_REJ_BREAKAGE<br>
	 * 		PO_DEMAND_VALIDITY<br>
	 * 		RETURN_CANCEL_ISSUE_PAT_PERIOD<br>
	 * 		SERVICE_TAX<br>
	 * 		SMS_CONFIG<br>
	 * 		TABLET_DRUG_TYPE_CODE<br>
	 * 		ALERT_BASE_URL <br>
	 * 		ALERT_CLIENT_ID <br>
	 * 		ALERT_PROJECT_ID <br>
	 * 		ALERT_SYS_PING <br>
	 * @return paramValue
	 */
	public static String getConfigParamValue(String strMode , String strHospCode , String strParamName) 
	{

		HisDAO dao = null;
		String strParamValue = null;

		try {

			dao = new HisDAO("mms", "ChallanModificationTransDAO");
			//config_dtl (modval NUMBER, hosp_code NUMBER, paramname VARCHAR2)
			String strFuncName = "{? = call mms_mst.config_dtl (?, ?, ?)}";
			// FUNCTION get_groupNameAndId_dtl (modeval NUMBER, hosp_code
			// NUMBER, itemId NUMBER)
			int nFuncIndex = dao.setFunction(strFuncName);
			dao.setFuncInValue(nFuncIndex, 2, strMode);
			dao.setFuncInValue(nFuncIndex, 3, strHospCode);
			dao.setFuncInValue(nFuncIndex, 4, strParamName);
			dao.setFuncOutValue(nFuncIndex, 1);
			dao.executeFunction(nFuncIndex);
			strParamValue = dao.getFuncString(nFuncIndex);			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		} 
		finally 
		{
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return strParamValue;
	}


	 

	}
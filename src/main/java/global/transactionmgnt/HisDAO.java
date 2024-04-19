/*
		Name		Ajay Kumar Gupta
 */

package  global.transactionmgnt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import com.sun.rowset.WebRowSetImpl;

import  global.exceptions.HisException;
import global.utility.HisUtil;

// TODO: Auto-generated Javadoc
/**
 * 
 * Global Class for Queries, Functions and Procedure execution Processes.
 * Database Connectivity is also handle by the Class, user no need to create
 * Database Connections, just set Query or Function or Procedure and execute it.
 * 
 * @author Ajay Kumar Gupta <br>
 *         Copyright �C-DAC Noida
 * 
 */
public class HisDAO {

	/** The ic. */
	private static Context ic = null;

	/** The ds. */
	private static DataSource ds = null;

	/** The look up name. */
	private static String lookUpName = "";

	/** The look up name1. */
	private static String lookUpName1 = "";
	// private static String connURL = "";

	// private static String userName = "";
	// private static String pwd = "";

	/** The qry obj. */
	private List<HisQuery> qryObj = null;

	/** The proc obj. */
	private List<HisProcedure> procObj = null;

	/** The func obj. */
	private List<HisFunction> funcObj = null;

	/** The final obj. */
	private List<String> finalObj = null;

	// private Map proc_out_value = null;
	/** The gbl conn. */
	private Connection gblConn = null;

	/** The qry index. */
	private int qryIndex = 0;

	/** The proc index. */
	private int procIndex = 0;

	/** The func index. */
	private int funcIndex = 0;

	/** The module name. */
	private String moduleName = "";

	/** The file name. */
	private String fileName = "";

	/** The ret value. */
	private boolean retValue = false;

	/** used for error messages. */
	private String errMsg = "";

	/** The conn type. */
	private String connType = "1";
  
	
	private final static String url = "jdbc:edb://10.10.10.235:5444/aiims_bibinagar";
	private final static String user = "aiimsbibinagar";
	private final static String password = "a11msb!b!n@g@r*$";
	
	
	/*
	private final static String url = "jdbc:edb://10.10.10.127:5444/aiims_raipur";
	private final static String user = "aiimsraipur";
	private final static String password = "A11m$Ra19Ur";
	*/

	// private static final String pathFileName = "mms.hisglobal.hisconfig.hisPath";
	// // containing into class folder
	/**
	 * constructor with arguments Enters HisDAO Open status into log file.
	 * 
	 * @param moduleName
	 *            -The Current Working Module.
	 * @param fileName
	 *            - The Current Working Java File.
	 */
	public HisDAO(String moduleName, String fileName) {

		this.moduleName = moduleName;
		this.fileName = fileName;
		HisException.putHisDAOStatus(this.moduleName, this.fileName, 0);

	}

	/**
	 * This function sets the config path.
	 * 
	 * @param connType
	 *            the new config path
	 */
	private static void setConfigPath(int connType) {

		ResourceBundle rsBundle = null;

		try {
			if (connType == 1) {

				if (lookUpName == null || lookUpName.equals("")) {

					lookUpName = HisUtil
							.getParameterFromHisPathXML("JNDI_LOOKUP");
					
					 System.out.println("lookUpName1 :<--Connection 1-->:"+lookUpName1);
					// connURL = rsBundle.getString("DIRECT_CONN_URL");
					// userName = rsBundle.getString("CONN_USER_NAME");
					// pwd = rsBundle.getString("CONN_PWD");
					rsBundle = null;

				}
			} else {

				 System.out.println("lookUpName1 :<--Connection 2-->:"+lookUpName1);
				if (lookUpName1 == null || lookUpName1.equals("")) {

					lookUpName1 = HisUtil
							.getParameterFromHisPathXML("JNDI_LOOKUP_RPT");

					// connURL = rsBundle.getString("DIRECT_CONN_URL");
					// userName = rsBundle.getString("CONN_USER_NAME");
					// pwd = rsBundle.getString("CONN_PWD");
					rsBundle = null;

				}
			}
		} catch (Exception e) {

			System.out.println("error :: " + e.getMessage());
		} finally {
			if (rsBundle != null)
				rsBundle = null;
		}

	}// end setConfigPath() function

	/**
	 * returns connection. This function will be used within this class
	 * 
	 * @return Connection by retrieving from IntialContext through DataSource.
	 * @throws Exception
	 *             the exception
	 */
	public Connection getConnection() throws Exception {

		Connection conn = null;

		retValue = false;
		this.errMsg = "";

		try {


			try {
				 Class.forName("com.edb.Driver");
				 conn = DriverManager.getConnection(url, user, password);
				   

				 			
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			retValue = true;
		} catch (Exception e) {
			this.errMsg = "HisDAO.getConnection() -->" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		return conn;
	}

	/**
	 * returns return value.
	 * 
	 * @return boolean
	 */
	public boolean getReturnValue() {
		return this.retValue;
	}

	/**
	 * retrieves Query from user and returns HisQuery Index.
	 * 
	 * @param qry
	 *            - SQL Query.
	 * @return int - query Index
	 * @see mms.hisglobal.transactionmgnt#HisQuery
	 */
	public int setQuery(String qry) {

		retValue = false;
		if (qryObj == null)
			qryObj = new ArrayList<HisQuery>(1);
		// create instance of query class and store into arraylist
		qryObj.add(qryIndex++, HisQuery.getQryInstance(qry));
		retValue = true;
		return (qryIndex - 1);
	}

	/**
	 * retrieves Procedure from user and returns HisProcedure Index.
	 * 
	 * @param proc
	 *            - Stored Procedures
	 * @return int - procedure Index
	 * @see mms.hisglobal.transactionmgnt#HisProcedure
	 */
	public int setProcedure(String proc) {

		retValue = false;
		if (procObj == null)
			procObj = new ArrayList<HisProcedure>(1);
		// create instance of query class and store into arraylist
		procObj.add(procIndex++, HisProcedure.getProcInstance(proc));
		retValue = true;
		return (procIndex - 1);
	}

	/**
	 * retrieves Function from user and returns HisFunction Index.
	 * 
	 * @param func
	 *            - SQL Functions
	 * @return int - function Index
	 * @see mms.hisglobal.transactionmgnt#HisFunction
	 */
	public int setFunction(String func) {

		retValue = false;
		if (funcObj == null)
			funcObj = new ArrayList<HisFunction>(1);
		// create instance of query class and store into arraylist
		funcObj.add(funcIndex++, HisFunction.getFuncInstance(func));
		retValue = true;
		return (funcIndex - 1);
	}

	/**
	 * sets the value for the parameterized query.
	 * 
	 * @param qryIndex
	 *            - query Index which obtained by {@link #setQuery(String)}
	 *            method.
	 * @param parameterIndex
	 *            - starts with <b>1</b>
	 * @param value
	 *            - SQL Query Parameter value, for corresponding parameterIndex.
	 * @throws Exception
	 *             - HisException if Query Index is Invalid.
	 * 
	 * @see mms.hisglobal.exceptions.HisException
	 * 
	 */
	public void setQryValue(int qryIndex, int parameterIndex, String value)
			throws Exception {

		retValue = false;
		this.errMsg = "";

		// get HisQuery Instance based on qryIndex & call setValue method to set
		// the value
		if (this.qryIndex > qryIndex) {
			if (value == null)
				value = "";

			((HisQuery) qryObj.get(qryIndex)).setValue(parameterIndex, value);
			retValue = true;
		} else {
			this.errMsg = "HisDAO.setQryValue() -->Invalid query index";
			throw new Exception(this.errMsg);
		}
	}

	/**
	 * sets the input value for the parameterized procedure.
	 * 
	 * @param procIndex
	 *            - procedure Index which obtained by
	 *            {@link #setProcedure(String)} method.
	 * @param parameterName
	 *            - name of the Procedure Parameter.
	 * @param value
	 *            - values for the corresponding Procedure Parameter.
	 * @throws Exception
	 *             - HisException if Procedure Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public void setProcInValue(int procIndex, String parameterName, String value)
			throws Exception {

		retValue = false;
		this.errMsg = "";

		// get HisProcedure Instance based on procIndex & call setValue method
		// to set the value
		if (this.procIndex > procIndex) {
			if (value == null)
				value = "";
			((HisProcedure) procObj.get(procIndex)).setInValue(parameterName,
					value);
			retValue = true;
		} else {
			this.errMsg = "HisDAO.setProcInValue() -->Invalid procedure index";
			throw new Exception(this.errMsg);
		}
	}
	
	/**
	 * sets the input value for the parameterized procedure
	 * 
	 * @param procIndex - procedure Index which obtained by {@link #setProcedure(String)} method.
	 * @param parameterName - name of the Procedure Parameter.
	 * @param value - values for the corresponding Procedure Parameter.
	 * @throws Exception - HisException if Procedure Index is Invalid.
	 * 
	 * @see hisglobal.exceptions.HisException     
	 */
	public void setProcInValueByPosition(int procIndex, String parameterName, String value,int paramIndex)
			throws Exception {

		retValue = false;
		this.errMsg = "";

		// get HisProcedure Instance based on procIndex & call setValue method
		// to set the value
		if (this.procIndex > procIndex) {
			((HisProcedure) procObj.get(procIndex)).setInValue(parameterName,
					value,paramIndex);
			retValue = true;
		} else {
			this.errMsg = "HisDAO.setProcInValue() -->Invalid procedure index";
			throw new Exception(this.errMsg);
		}
	}

	/*
	 * this function
	 */

	/**
	 * sets the input value for the parameterized function type 1 for integer, 2
	 * for double and 3 for string.
	 * 
	 * @param funcIndex
	 *            - function Index which obtained by
	 *            {@link #setFunction(String)} method.
	 * @param parameterIndex
	 *            - starts with <b>2</b>.
	 * @param value
	 *            - values for the corresponding Function Parameter.
	 * @throws Exception
	 *             - HisException if Function Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public void setFuncInValue(int funcIndex, int parameterIndex, String value)
			throws Exception {

		retValue = false;
		this.errMsg = "";

		// get HisFunction Instance based on funcIndex & call setValue method
		// to set the value
		if (this.funcIndex > funcIndex) {
			if (value == null)
				value = "";
			((HisFunction) funcObj.get(funcIndex)).setInValue(
					String.valueOf(parameterIndex), value);
			retValue = true;
		} else {
			this.errMsg = "HisDAO.setFuncInValue() -->Invalid function index";
			throw new Exception(this.errMsg);
		}
	}

	/**
	 * sets the output value for the parameterized query.
	 * 
	 * @param procIndex
	 *            - procedure Index which obtained by
	 *            {@link #setProcedure(String)} method.
	 * @param parameterName
	 *            - name of Procedure Parameter.
	 * @param outputType
	 *            - <b>1</b> for String and <b>2</b> for Object
	 * @throws Exception
	 *             - HisException if Procedure Index is Invalid.
	 * 
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public void setProcOutValue(int procIndex, String parameterName,
			int outputType) throws Exception {

		retValue = false;
		this.errMsg = "";

		// get HisProcedure Instance based on procIndex & call setValue method
		// to set the value
		if (this.procIndex > procIndex) {
			((HisProcedure) procObj.get(procIndex)).setOutValue(parameterName,
					outputType);
			retValue = true;
		} else {
			this.errMsg = "HisDAO.setProcOutValue() -->Invalid procedure index";
			throw new Exception(this.errMsg);
		}
	}
	
	/**
	 * sets the output value for the parameterized query.
	 * @param procIndex - procedure Index which obtained by {@link #setProcedure(String)} method.
	 * @param parameterName - name of Procedure Parameter.
	 * @param outputType - <b>1</b> for String and <b>2</b> for Object
	 * @throws Exception - HisException if Procedure Index is Invalid.
	 * 
	 * @see hisglobal.exceptions.HisException     
	 */
	public void setProcOutValue(int procIndex, String parameterName,
			int outputType,int paramIndex) throws Exception {

		retValue = false;
		this.errMsg = "";

		// get HisProcedure Instance based on procIndex & call setValue method
		// to set the value
		if (this.procIndex > procIndex) {
			((HisProcedure) procObj.get(procIndex)).setOutValue(parameterName,
					outputType,paramIndex);
			retValue = true;
		} else {
			this.errMsg = "HisDAO.setProcOutValue() -->Invalid procedure index";
			throw new Exception(this.errMsg);
		}
	}

	/**
	 * sets the output value for the parameterized query.
	 * 
	 * @param funcIndex
	 *            - function Index which obtained by
	 *            {@link #setFunction(String)} method.
	 * @param outputType
	 *            - <b>1</b> for int/double/string and <b>2</b> for resultset
	 * @throws Exception
	 *             - HisException if Function Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public void setFuncOutValue(int funcIndex, int outputType) throws Exception {

		retValue = false;
		this.errMsg = "";

		// get HisProcedure Instance based on procIndex & call setValue method
		// to set the value
		if (this.funcIndex > funcIndex) {
			((HisFunction) funcObj.get(funcIndex)).setOutValue(outputType);
			retValue = true;
		} else {
			this.errMsg = "HisDAO.setFuncOutValue() -->Invalid function index";
			throw new Exception(this.errMsg);
		}
	}

	/**
	 * used to add queries/procedure into batch. Call this method after setting
	 * value for parameterized query/procedure if there is parameterized
	 * query/procedure.
	 * 
	 * It does not execute the query/procedure.
	 * 
	 * Logic --> add 1000 in index while storing index into arraylist for
	 * procedure. At the time of execution, we could know whether we have to
	 * execute either query or procedure
	 * 
	 * @param index
	 *            - queryIndex or procedureIndex which obtained by
	 *            {@link #setQuery(String)} or {@link #setProcedure(String)}
	 *            method.
	 * @param mode
	 *            - <b>0</b> if query are added into batch and <b>1</b> if
	 *            procedures are added into batch
	 * @throws Exception
	 *             - HisException if Query or Procedure Index is Invalid.
	 * 
	 * @see mms.hisglobal.exceptions.HisException
	 * 
	 * 
	 */
	public void execute(int index, int mode) throws Exception {

		retValue = false;
		this.errMsg = "";

		if (finalObj == null)
			finalObj = new ArrayList<String>(1);
		// for query
		if (mode == 0) {
			if (this.qryIndex > index) {
				// add into final list
				finalObj.add(String.valueOf(index));
				((HisQuery) this.qryObj.get(index)).execute();
				retValue = true;
			} else {
				this.errMsg = "HisDAO.execute() -->Invalid query index";
				throw new Exception(this.errMsg);
			}
		} else {
			if (this.procIndex > index) {
				// add into final list [add 1000 for identifying procedure]
				finalObj.add(String.valueOf(index + 1000));
				((HisProcedure) this.procObj.get(index)).execute();
				retValue = true;
			} else {
				this.errMsg = "HisDAO.execute() -->Invalid procedure index";
				throw new Exception(this.errMsg);
			}
		}
	}

	/**
	 * executes a single query whether query is parameterized or not and returns
	 * WebRowSet. qryIndex is index returned by {@link #setQuery(String)}
	 * method. Use this function when query is parameterized.
	 * 
	 * @param qryIndex
	 *            - query Index which obtained by {@link #setQuery(String)}
	 *            method.
	 * @return Query Results in WebRowSet
	 * @throws Exception
	 *             - HisException if Query Index is Invalid.
	 * 
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public WebRowSet executeQry(int qryIndex) throws Exception {

		Connection conn = null;
		WebRowSet ws = null;
		retValue = false;
		this.errMsg = "";

		try {
			if (this.qryIndex > qryIndex) {
				conn = this.getConnection();
				ws = ((HisQuery) qryObj.get(qryIndex)).executeQry(conn);
				retValue = true;
			} else {
				this.errMsg = "Invalid query index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.retValue = false;
			this.errMsg = "HisDAO.executeQry(1 Args) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}

		return ws;
	}

	/**
	 * executes a single query whether query is parameterized or not and returns
	 * Array List. qryIndex is index returned by {@link #setQuery(String)}
	 * method. Use this function when query is parameterized.
	 * 
	 * @param qryIndex
	 *            - query Index which obtained by {@link #setQuery(String)}
	 *            method.
	 * @param no_of_fields
	 *            - Number of Fields specified in SQL Query.
	 * @return ArrayList - ArrayList Object contain Results.
	 * @throws Exception
	 *             - HisException if Query Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public ArrayList<String> executeQry(int qryIndex, int no_of_fields)
			throws Exception {

		Connection conn = null;
		ArrayList<String> qryResult = null;

		this.retValue = false;
		this.errMsg = "";

		try {
			if (this.qryIndex > qryIndex) {
				conn = this.getConnection();
				qryResult = ((HisQuery) qryObj.get(qryIndex)).executeQry(conn,
						no_of_fields);
				retValue = true;
			} else {
				this.errMsg = "Invalid query index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.retValue = false;
			this.errMsg = "HisDAO.executeQry(2 Args) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}

		return qryResult;
	}

	/*
	 * firstFireFlag = 1 meaning the first calling lstFireFlag = 1 meaning that
	 * connection should be closed else connection will be alive
	 */
	/**
	 * Fire_without_commit.
	 * 
	 * @param firstFireFlag
	 *            the first fire flag
	 * @param lstFireFlag
	 *            the lst fire flag
	 * @throws Exception
	 *             the exception
	 */
	public void fire_without_commit(int firstFireFlag, int lstFireFlag)
			throws Exception {

		int tempIndex = 0;
		int gblConnCloseFlag = 0;

		retValue = false;
		this.errMsg = "";

		if (firstFireFlag != 1 && gblConn == null) {
			this.retValue = false;
			this.errMsg = "HisDAO.fire() -->You have not catched the exception, please check coding !!";
			throw new Exception(this.errMsg);
		} else {
			try {
				// finalObj contains the object index. Query/procedure will be
				// executed in same order as it is contained
				if (this.finalObj != null && this.finalObj.size() > 0) {

					// get connection
					if (firstFireFlag == 1) {
						gblConn = this.getConnection();
						gblConn.setAutoCommit(false);
					}

					this.retValue = false;

					for (int i = 0; i < this.finalObj.size(); i++) {
						// getting object if index is less than 1000 then there
						// is
						// query object else procedure object
						tempIndex = Integer.parseInt((String) this.finalObj
								.get(i));
						if (tempIndex < 1000) {
							// query object
							((HisQuery) this.qryObj.get(tempIndex))
									.executeQry_Batch(gblConn);
						} else {
							// procedure object
							((HisProcedure) this.procObj.get(tempIndex - 1000))
									.executeProc_Batch(gblConn);
						}
					}// end for loop

					this.retValue = true;
				} // end if block
				else {
					this.errMsg = "No Query/Procedure added into batch";
					throw new Exception(this.errMsg);
				}
			} catch (Exception e) {
				this.retValue = false;
				this.errMsg = "HisDAO.fire() -->" + e.getMessage();
				throw new Exception(this.errMsg);
			} finally {
				// free used resources
				try {
					if (lstFireFlag == 1 || this.retValue == false)
						gblConnCloseFlag = 1;

					if (gblConnCloseFlag == 1) {
						if (this.retValue == true)
							gblConn.commit();
						else
							gblConn.rollback();

						// free connection
						if (gblConn != null) {
							gblConn.close();
							gblConn = null;
						}
					}

					// destroy final object
					if (this.finalObj != null) {
						this.finalObj.clear();
						this.finalObj = null;
					}
				} catch (Exception e) {
				}
			}
		}

	} // end fire_without_commit() function

	/*
	 * This function consult.
	 */

	/**
	 * executes a single Function. funcIndex is index returned by
	 * {@link #setFunction(String)} method. If there is output parameter defined
	 * for the function then {@link #getFuncString(int)} is used to get the
	 * value of output parameterized
	 * 
	 * @param funcIndex
	 *            - function Index which obtained by
	 *            {@link #setFunction(String)} method.
	 * @throws Exception
	 *             - HisException if Function Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public void executeFunction(int funcIndex) throws Exception {

		Connection conn = null;
		retValue = false;
		this.errMsg = "";

		try {
			if (this.funcIndex > funcIndex) {
				conn = this.getConnection();
				((HisFunction) funcObj.get(funcIndex)).executeFunc(conn);
				retValue = true;
			} else {
				this.errMsg = "Invalid function index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.retValue = false;
			this.errMsg = "HisDAO.executeFunction() -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}
	}

	/*
	 * This function consult.
	 */

	/**
	 * executes a single procedure. procIndex is index returned by
	 * {@link #setProcedure(String)} method. If there is output parameter
	 * defined for the procedure then getOutputValue() is used to get the value
	 * of output parameterized
	 * 
	 * @param procIndex
	 *            - procedure Index which obtained by
	 *            {@link #setProcedure(String)} method.
	 * @throws Exception
	 *             - HisException if Procedure Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public void executeProcedure(int procIndex) throws Exception {

		Connection conn = null;
		retValue = false;
		this.errMsg = "";

		try {
			if (this.procIndex > procIndex) {
				conn = this.getConnection();
				conn.setAutoCommit(false);
				((HisProcedure) procObj.get(procIndex)).executeProc(conn);
				retValue = true;
			} else {
				this.errMsg = "Invalid procedure index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.retValue = false;
			this.errMsg = "HisDAO.executeProcedure() -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (this.retValue == true)
					conn.commit();
				else
					conn.rollback();
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * executes a single procedure. 
	 * procIndex is index returned by {@link #setProcedure(String)} method. 
	 * If there is output parameter defined for the
	 * procedure then getOutputValue() is used to get the value of output
	 * parameterized
	 * 
	 * @param procIndex - procedure Index which obtained by {@link #setProcedure(String)} method.
	 * @throws Exception  - HisException if Procedure Index is Invalid.
	 * @see hisglobal.exceptions.HisException 
	 */
	public void executeProcedureByPosition(int procIndex) throws Exception {

		Connection conn = null;
		retValue = false;
		this.errMsg = "";

		try {
			if (this.procIndex > procIndex) {
				conn = this.getConnection();
				conn.setAutoCommit(false);
				((HisProcedure) procObj.get(procIndex)).executeProcByPosition(conn);
				retValue = true;
			} else {
				this.errMsg = "Invalid procedure index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.retValue = false;
			this.errMsg = "HisDAO.executeProcedureByPosition() -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if(this.retValue == true)
					conn.commit();
				else
					conn.rollback();
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}
	}


	/**
	 * used to get the String value for output parameter of procedure. If
	 * procedure is executed using {@link #executeProcedure(int)} method.
	 * 
	 * @param procIndex
	 *            - procedure Index which obtained by
	 *            {@link #setProcedure(String)} method.
	 * @param parameterName
	 *            - Name which has been defined for output parameter.
	 * @return String - value for output parameter of procedure.
	 * @throws Exception
	 *             - HisException if Procedure Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public String getString(int procIndex, String parameterName)
			throws Exception {
		String retData = null;
		retValue = false;
		

		try {
			if (this.procIndex > procIndex) {
				retData = (String) ((HisProcedure) procObj.get(procIndex))
						.getOutputValue(parameterName);
				retValue = true;
			} else {
				this.errMsg = "Invalid procedure index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisDAO.getString() --> " + e.getMessage();
			throw new Exception(this.errMsg);
		}

		return retData;
	}

	/*
	 * 
	 * procIndex --> Object returened while setting the procedure parameterName
	 * --> Name which has been defined for output parameter
	 */

	/**
	 * This function is used to get the WebRowSet value for output parameter of
	 * procedure. If procedure is executed using {@link #executeProcedure(int)}
	 * method.
	 * 
	 * @param procIndex
	 *            - procedure Index which obtained by
	 *            {@link #setProcedure(String)} method.
	 * @param parameterName
	 *            - Name which has been defined for output parameter.
	 * @return WebRowSet - which contains Results.
	 * @throws Exception
	 *             - HisException if Procedure Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public WebRowSet getWebRowSet(int procIndex, String parameterName)
			throws Exception {
		WebRowSet retData = null;
		ResultSet rs = null;
		retValue = false;

		try {
			if (this.procIndex > procIndex) {
				/*
				 * rs = (ResultSet) ((HisProcedure) procObj.get(procIndex))
				 * .getOutputValue(parameterName);
				 */
				// initilize webrowset
				// retData = new WebRowSetImpl();
				// retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);

				// retData.populate(rs);

				retData = (WebRowSet) ((HisProcedure) procObj.get(procIndex))
						.getOutputValue(parameterName);

				retValue = true;
			} else {
				this.errMsg = "Invalid procedure index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisDAO.getWebRowSet() --> " + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		}

		return retData;
	}

	/*
	 * This function is funcIndex --> Object returened while setting the
	 * function
	 */

	/**
	 * used to get the String value for output parameter of function.
	 * 
	 * @param funcIndex
	 *            - function Index which obtained by
	 *            {@link #setFunction(String)} method.
	 * @return String - output of the function.
	 * @throws Exception
	 *             - HisException if Function Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public String getFuncString(int funcIndex) throws Exception {
		String retData = null;
		retValue = false;

		try {
			if (this.funcIndex > funcIndex) {
				retData = (String) ((HisFunction) funcObj.get(funcIndex))
						.getOutputValue();
				retValue = true;
			} else {
				this.errMsg = "Invalid function index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisDAO.getFuncString() --> " + e.getMessage();
			throw new Exception(this.errMsg);
		}

		return retData;
	}

	/*
	 * This function is funcIndex --> Object returened while setting the
	 * function
	 */
	/**
	 * used to get the WebRowSet value for output parameter of function.
	 * 
	 * @param funcIndex
	 *            - function Index which obtained by
	 *            {@link #setFunction(String)} method.
	 * @return WebRowSet - which contains Results.
	 * @throws Exception
	 *             - HisException if Function Index is Invalid.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public WebRowSet getFuncWebRowSet(int funcIndex) throws Exception {
		WebRowSet retData = null;
		ResultSet rs = null;
		retValue = false;

		try {
			if (this.funcIndex > funcIndex) {
				/*
				 * rs = (ResultSet) ((HisFunction) funcObj.get(funcIndex))
				 * .getOutputValue(); // initilize webrowset retData = new
				 * WebRowSetImpl();
				 * retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
				 * retData.populate(rs);
				 */

				retData = (WebRowSet) ((HisFunction) funcObj.get(funcIndex))
						.getOutputValue();
				retValue = true;
			} else {
				this.errMsg = "Invalid function index";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.errMsg = "HisDAO.getFuncWebRowSet() --> " + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		}

		return retData;
	}

	/*
	 * This function This
	 */

	/**
	 * executes all queries/procedures which has been kept into batch.
	 * 
	 * This method either commits or rollback the entire transaction.
	 * 
	 * method does not free the objects. It only resets the value defined for
	 * query/procedure meaning that the query index/procedure index returned by
	 * {@link #setQuery(String)}/{@link #setProcedure(String)} method remains
	 * valid but the values disappears
	 * 
	 * @throws Exception
	 *             - HisException if No Query/Procedure added into batch.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public void fire() throws Exception {

		Connection conn = null;
		int tempIndex = 0;

		retValue = false;
		this.errMsg = "";

		try {
			// finalObj contains the object index. Query/procedure will be
			// executed in same order as it is contained
			if (this.finalObj != null && this.finalObj.size() > 0) {

				// get connection
				conn = this.getConnection();
				conn.setAutoCommit(false);
				this.retValue = false;

				for (int i = 0; i < this.finalObj.size(); i++) {
					// getting object if index is less than 1000 then there is
					// query object else procedure object
					tempIndex = Integer.parseInt((String) this.finalObj.get(i));
					if (tempIndex < 1000) {
						// query object
						((HisQuery) this.qryObj.get(tempIndex))
								.executeQry_Batch(conn);
					} else {
						// procedure object
						((HisProcedure) this.procObj.get(tempIndex - 1000))
								.executeProc_Batch(conn);
					}
				}// end for loop

				this.retValue = true;
			} // end if block
			else {
				this.errMsg = "No Query/Procedure added into batch";
				throw new Exception(this.errMsg);
			}
		} catch (Exception e) {
			this.retValue = false;
			this.errMsg = "HisDAO.fire() -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			// free used resources
			try {
				if (this.retValue == true)
					conn.commit();
				else
					conn.rollback();

				// free connection
				if (conn != null) {
					conn.close();
					conn = null;
				}
				// destroy final object
				if (this.finalObj != null) {
					this.finalObj.clear();
					this.finalObj = null;
				}
			} catch (Exception e) {
			}
		}

	} // end fire() function

	/**
	 * Returns query result which are not parameterized. It returns WebRowSet
	 * 
	 * @param qry
	 *            - SQL Query.
	 * @param fromRow
	 *            the from row
	 * @param maxRows
	 *            the max rows
	 * @return WebRowSet - contains Query Results.
	 * @throws Exception
	 *             - HisException if any Problem occurs at the time of
	 *             execution.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public WebRowSet getQryResultFiltered(String qry, String fromRow,
			String maxRows) throws Exception {

		Connection conn = null;
		WebRowSet ws = null;
		this.errMsg = "";

		try {
			ws = new WebRowSetImpl();
			ws.setMaxRows(Integer.parseInt(maxRows));
			ws.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
			ws.setCommand(qry);
			conn = this.getConnection();
			ws.execute(conn);
			if (Integer.parseInt(fromRow) != 0)
				ws.absolute(Integer.parseInt(fromRow));
			/*
			 * else ws.absolute(Integer.parseInt(fromRow));
			 */
		} catch (Exception e) {
			this.errMsg = "HisDAO.getQryResult(qry,Query : " + qry + ") -->"
					+ e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}

		return ws;
	}

	/**
	 * Returns query result which are not parameterized. It returns WebRowSet
	 * 
	 * @param qry
	 *            - SQL Query.
	 * @return WebRowSet - contains Query Results.
	 * @throws Exception
	 *             - HisException if any Problem occurs at the time of
	 *             execution.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public WebRowSet getQryResult(String qry) throws Exception {

		Connection conn = null;
		WebRowSet ws = null;
		this.errMsg = "";

		try {
			ws = new WebRowSetImpl();
			ws.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
			ws.setCommand(qry);
			conn = this.getConnection();
			ws.execute(conn);
		} catch (Exception e) {
			this.errMsg = "HisDAO.getQryResult(qry,Query : " + qry + ") -->"
					+ e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}

		return ws;
	}

	/**
	 * Returns query result which are not parameterized.
	 * 
	 * @param qry
	 *            - SQL Query.
	 * @param no_of_fields
	 *            - Number of Fields Query Contains.
	 * @return ArrayList - Query Result in ArrayList.
	 * @throws Exception
	 *             - HisException if any Problem occurs at the time of
	 *             execution.
	 * @see mms.hisglobal.exceptions.HisException
	 */
	public ArrayList<String> getQryResult(String qry, int no_of_fields)
			throws Exception {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		ArrayList<String> qryResult = null;
		String tempValue = "";

		this.retValue = false;
		this.errMsg = "";

		try {
			conn = this.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(qry);

			if (rs != null) {
				// initilize array list
				qryResult = new ArrayList<String>(1);
				while (rs.next()) {
					for (int i = 1; i <= no_of_fields; i++) {
						tempValue = rs.getString(i);
						if (tempValue == null)
							tempValue = "";
						qryResult.add(tempValue);
					}

				}
			}
			this.retValue = true;
		} catch (Exception e) {
			this.errMsg = "HisDAO.getQryResult(qry,fields,Query : " + qry
					+ ") -->" + e.getMessage();
			throw new Exception(this.errMsg);
		} finally {
			try {
				if (st != null) {
					st.close();
					st = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
			}
		}

		return qryResult;
	}

	/**
	 * free all the objects. call the method when all your DataBase process for
	 * the required task have been completed.
	 */
	public void free() {
		// put HisDAO Open status into log file
		HisException.putHisDAOStatus(this.moduleName, this.fileName, 1);

		int counter = 0;
		int tempSize = 0;

		// free the used resources
		if (this.qryObj != null) {
			tempSize = qryObj.size();
			for (counter = 0; counter < tempSize; counter++) {
				((HisQuery) qryObj.get(counter)).resetValue();
			}
			this.qryObj.clear();
			this.qryObj = null;
		}

		if (this.procObj != null) {
			tempSize = procObj.size();
			for (counter = 0; counter < tempSize; counter++) {
				((HisProcedure) procObj.get(counter)).resetValue();
			}
			this.procObj.clear();
			this.procObj = null;
		}

		if (this.finalObj != null) {
			this.finalObj.clear();
			this.finalObj = null;
		}

		this.qryIndex = 0;
		this.procIndex = 0;
		this.moduleName = "";
		this.fileName = "";
		this.retValue = false;
	}

	/**
	 * Gets the conn type.
	 * 
	 * @return the conn type
	 */
	public String getConnType() {
		return connType;
	}

	/**
	 * Sets the conn type.
	 * 
	 * @param connType
	 *            the new conn type
	 */
	public void setConnType(String connType) {
		this.connType = connType;
	}
	
	
	
}

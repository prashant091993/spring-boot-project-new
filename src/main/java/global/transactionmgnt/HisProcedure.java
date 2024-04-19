/*
 * Copyright �.
 */

package global.transactionmgnt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import oracle.jdbc.OracleTypes;
import javax.sql.rowset.WebRowSet;
import java.sql.ResultSet;
import com.sun.rowset.WebRowSetImpl;

/**
 * @author Ajay Kumar Gupta <br> Copyright �C-DAC Noida
 */
public final class HisProcedure
{
	String proc = "";
	
	private CallableStatement cs = null;
	private List <String>proc_single_value = null;
	private List <Object[]>proc_multiple_value = null;
	private List <String>proc_out_index = null;
	
	//procedure output value
	Map  <String,Object>proc_out_value = null;	//used to store the value returned by procedure
	
	private int colSize = -1;
	private int rowSize = 0;
	
	/**
	 * used for getting value from qry_multiple_value list
	 */
	private int gblIndex = 0;
	/**
	 * used for error messages
	 */
	private String errMsg = "";
	
	/**
	 * flag indicating whether err parameter is defined in procedure or not. It is mandatory parameter
	 */
	private boolean errFlag = false;
	
	/**
	 * 
	 */
	private String procErrStr = "";
	/**
	 * Make the class Non-instanciable
	 */
	private HisProcedure() {}
	
	/**
	 * HisProcedure Constructor.
	 * @param proc - Procedure Call String.
	 */
	public HisProcedure(String proc) {
		this.proc = proc;
	}
	
	/**
	 * returns HisProcedure Object.
	 * @param proc - Procedure Call String.
	 * @return HisProcedure.
	 */
	public static HisProcedure getProcInstance(String proc) {
		return (new HisProcedure(proc));
	} 	
	
	/**
	 * sets the input value for parameterized procedure. It sets value by name
	 * @param parameterName - Procedure Parameter Name.
	 * @param val - Procedure Parameter Value
	 */
	public void setInValue(String parameterName,String val) {
		//initialize the ArrayList
		if(this.proc_single_value == null) this.proc_single_value = new ArrayList<String>(2);
		this.proc_single_value.add(parameterName);
				
		this.proc_single_value.add(val);
	}
	
	
	/**
	 * sets the input value for parameterized procedure. It sets value by name
	 * @param parameterName - Procedure Parameter Name.
	 * @param val - Procedure Parameter Value
	 */
	public void setInValue(String parameterName,String val,int paramIndex) {
		//initialize the ArrayList
		if(this.proc_single_value == null) this.proc_single_value = new ArrayList<String>(2);
		this.proc_single_value.add(paramIndex+":"+parameterName);
					
		this.proc_single_value.add(val);
	}
	//added by shalini for fire_without_commit method
		public void setOutValue_new(String parameterName,int outputType) {
			int index = 0;
			
			//initialize the ArrayList
			if(this.proc_single_value == null) this.proc_single_value = new ArrayList<String>(2);
			if(this.proc_out_index == null) this.proc_out_index = new ArrayList<String>(1);
			
			//ERR PARAMETER MUST EXIST IN THE PROCEDURE
			if(parameterName.toUpperCase().trim().equals("ERR")) errFlag = true;
			
			this.proc_single_value.add(parameterName);
			this.proc_single_value.add("OUTPARAMETER#" + String.valueOf(outputType));
			
			index = this.proc_single_value.size()/2;//change forpostgres
			this.proc_out_index.add(parameterName + "#" + String.valueOf(outputType) + "#" + index);
		}
	/**
	 * sets the output value for parameterized procedure
	 * @param parameterName - Procedure Parameter Name.
	 * @param outputType <b>1</b>-String and <b>2</b>-WebRowSet.
	 */
	/*public void setOutValue(String parameterName,int outputType) {
		//initialize the ArrayList
		if(this.proc_single_value == null) this.proc_single_value = new ArrayList<String>(2);
		if(this.proc_out_index == null) this.proc_out_index = new ArrayList<String>(1);
		
		//ERR PARAMETER MUST EXIST IN THE PROCEDURE
		if(parameterName.toUpperCase().trim().equals("ERR")) errFlag = true;
		
		this.proc_single_value.add(parameterName);
		this.proc_single_value.add("OUTPARAMETER#" + String.valueOf(outputType));
		this.proc_out_index.add(parameterName + "#" + String.valueOf(outputType));
	}*/
	
	
	/**
	 * sets the output value for parameterized procedure
	 * @param parameterName - Procedure Parameter Name.
	 * @param outputType <b>1</b>-String and <b>2</b>-WebRowSet.
	 */
	public void setOutValue(String parameterName,int outputType) {
		int index = 0;
		
		//initialize the ArrayList
		if(this.proc_single_value == null) this.proc_single_value = new ArrayList<String>(2);
		if(this.proc_out_index == null) this.proc_out_index = new ArrayList<String>(1);
		
		//ERR PARAMETER MUST EXIST IN THE PROCEDURE
		if(parameterName.toUpperCase().trim().equals("ERR")) errFlag = true;
		
		this.proc_single_value.add(parameterName);
		this.proc_single_value.add("OUTPARAMETER#" + String.valueOf(outputType));
		
		index = this.proc_single_value.size()/2;//change forpostgres
		this.proc_out_index.add(parameterName + "#" + String.valueOf(outputType) + "#" + index);
	}
	
	/**
	 * sets the output value for parameterized procedure
	 * @param parameterName - Procedure Parameter Name.
	 * @param outputType <b>1</b>-String and <b>2</b>-WebRowSet.
	 */
	public void setOutValue(String parameterName,int outputType,int paramIndex) {
		//initialize the ArrayList
		if(this.proc_single_value == null) this.proc_single_value = new ArrayList<String>(2);
		if(this.proc_out_index == null) this.proc_out_index = new ArrayList<String>(1);
		
		//ERR PARAMETER MUST EXIST IN THE PROCEDURE
		if(parameterName.toUpperCase().trim().equals("ERR")) errFlag = true;
		
		this.proc_single_value.add(paramIndex+":"+parameterName);
		this.proc_single_value.add("OUTPARAMETER#" + String.valueOf(outputType));
		this.proc_out_index.add(parameterName+":" +paramIndex +"#" + String.valueOf(outputType));
	}

	/**
	 * used to retrieve the procedure output value	
	 * @param parameterName - Procedure Parameter Name.
	 * @return Object - Procedure Output Value for given Parameter.
	 * @throws Exception if No Out-Parameter Defined.
	 */
	public Object getOutputValue(String parameterName) throws Exception {
		Object outVal = null;
		
		try {
			if(proc_out_value != null) {
				if(!proc_out_value.containsKey(parameterName)) {
					this.errMsg = "Invalid Parameter Name ( " + parameterName + " )";
					throw new Exception(this.errMsg);
				}
				else
					outVal = proc_out_value.get(parameterName);
			}
			else {
				this.errMsg = "No Out-Parameter Defined";
				throw new Exception(this.errMsg);
			}
		}
		catch(Exception e) {
			this.errMsg = "HisProcedure.getOutputValue(Procedure Name : " + this.proc + ") -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		
		return outVal;
	}
	
	
	/**
	 * used to retrieve the procedure output value	
	 * @param parameterName - Procedure Parameter Name.
	 * @return Object - Procedure Output Value for given Parameter.
	 * @throws Exception if No Out-Parameter Defined.
	 */
	public Object getOutputValue(int paramIndex) throws Exception {
		Object outVal = null;
		
		try {
			if(proc_out_value != null) {
				/*if(!proc_out_value.containsKey(parameterName)) {
					this.errMsg = "Invalid Parameter Name ( " + parameterName + " )";
					throw new Exception(this.errMsg);
				}
				else*/
					outVal = proc_out_value.get(paramIndex);
			}
			else {
				this.errMsg = "No Out-Parameter Defined";
				throw new Exception(this.errMsg);
			}
		}
		catch(Exception e) {
			this.errMsg = "HisProcedure.getOutputValue(Procedure Name : " + this.proc + ") -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		
		return outVal;
	}
	
	/**
	 * executes a single procedure and sets the output value into proc_out_value map.
	 * @param conn - Connection Object.
	 * @throws Exception - if any Exception arrives at the time of execution.
	 */
public void executeProc(Connection conn) throws Exception {
		
		CallableStatement cs = null;
		//Map  <String,String>proc_out_value = null;
		
		String strProcValue = "";
		String[] strProcArr = null;
		String keyName = "";
		String[] keyValue;	
		
		String strTemp = "";
		Object outValue = null;		//output value for procedure
		ResultSet rs = null;
		WebRowSet retData = null;
		
		int lcl_col_size = 0;
		this.errMsg = "";
		this.procErrStr = "";
		int counter = 0;
		
		try {
			if(!this.proc.equals("")) {
				cs = conn.prepareCall(this.proc);
								
				if(this.proc_single_value != null) {
					lcl_col_size = this.proc_single_value.size()/2;
					
					//sets the values in callable statement
					for(int i = 0;i<lcl_col_size;i++) {
						keyName = (String)this.proc_single_value.get(2*i);
						
						keyValue = ((String)this.proc_single_value.get((2*i)+1)).split("#");
										
						if(keyValue[0].equals("OUTPARAMETER")) {
							
							strProcValue = ":X" + (++counter);
							
							/*oracle
							if(keyValue[1].equals("1"))		//string
								cs.registerOutParameter(keyName,java.sql.Types.VARCHAR);
							else			//web row set
								cs.registerOutParameter(keyName,OracleTypes.CURSOR);
							*/
							
							//postgres
							if(keyValue[1].equals("1"))		//string
								cs.registerOutParameter(i+1,java.sql.Types.VARCHAR);
							else			//web row set
							{
								cs.setNull(i+1, Types.NULL);
								cs.registerOutParameter(i+1,java.sql.Types.REF_CURSOR);
							}
						}
						else {
							
								 strProcValue = (String)this.proc_single_value.get((2*i)+1);
								 //oracle
								 //cs.setString(keyName,strProcValue);
								 
								 //postgres
								 if(strProcValue==null || strProcValue.trim().equals("")) 
									 this.cs.setNull(i+1,Types.NULL);
								 else
									 cs.setString(i+1,strProcValue);
						}
						
						if (i == 0)
							this.procErrStr += (keyName + "=>'" + strProcValue + "'");
						else
							this.procErrStr += ("," + keyName + "=>'" + strProcValue + "'");
						
					} //end for loop
										
					//free the qry_single_value arraylist
					this.proc_single_value.clear();
					this.proc_single_value = null;
				} // end if
				
				//execute the procedure
				cs.execute();
			
				//set the output data into Map
				if(this.proc_out_index != null && this.proc_out_index.size() > 0) {
					lcl_col_size = this.proc_out_index.size();
					//initilize HashMap
					this.proc_out_value = new HashMap<String,Object>(lcl_col_size);	
										
					for(int i = 0; i<lcl_col_size;i++) {
						//keyValue = PARAMETER NAME#OUTPUT TYPE
						keyValue = ((String)this.proc_out_index.get(i)).split("#");
						if(keyValue[1].equals("1"))	{		//string return value
							//oracle
							//outValue = cs.getString(keyValue[0]);
							//postgres
							outValue = cs.getString(Integer.parseInt(keyValue[2]));
							
							if(outValue == null) outValue = "";
							//check err message returned by the procedure
							if(keyValue[0].toUpperCase().trim().equals("ERR")) {
								strTemp = (String)outValue;
								if(!strTemp.equals("") || strTemp.trim().length() > 0) {
									this.errMsg = "Error thrown by procedure : " + strTemp;
									throw new Exception(this.errMsg);
								}
							}
							
							proc_out_value.put(keyValue[0],outValue);
						}
						else {		//resultset/webrowset
							//oracle
							//rs = (ResultSet)cs.getObject(keyValue[0]);
							//postgres
							rs = (ResultSet)cs.getObject(Integer.parseInt(keyValue[2]));
							
							retData = new WebRowSetImpl();
							retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
							retData.populate(rs);
							
							proc_out_value.put(keyValue[0],retData);
						}
						
						//proc_out_value.put(keyValue[0],outValue);
					}
				}
				
			} //end outer if block
			else {
				this.errMsg = "Procedure is blank";
				throw new Exception(this.errMsg);
			}
			
		}
		catch(Exception e) {
			strProcArr = (this.proc.replace("(", "#")).split("#");
			this.errMsg = "HisProcedure.executeProc(" + strProcArr[0] + "(" + this.procErrStr + ")) -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		finally {
			try {
				if(cs != null) {
					cs.close();
					cs = null;
				}
				
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
				
				outValue = null;
				if(this.proc_out_index != null) {
					this.proc_out_index.clear();
					this.proc_out_index = null;
				}
				this.errFlag = false;
				this.procErrStr = "";
			}
			catch(Exception e) {}
		}
	}
	
	
	/**
	 * executes a single procedure and sets the output value into proc_out_value map.
	 * @param conn - Connection Object.
	 * @throws Exception - if any Exception arrives at the time of execution.
	 */
	public void executeProcByPosition(Connection conn) throws Exception {
		
		CallableStatement cs = null;
		//Map  <String,String>proc_out_value = null;
		
		String strProcValue = "";
		String[] strProcArr = null;
		String keyName = "";
		String[] keyValue;	
		String[] paramValue;
		int index=0;
		ArrayList<String> keyNameIndex = null;
		
		String strTemp = "";
		Object outValue = null;		//output value for procedure
		ResultSet rs = null;
		WebRowSet retData = null;
		
		int lcl_col_size = 0;
		this.errMsg = "";
		this.procErrStr = "";
		int counter = 0;
		
		try {
			if(!this.proc.equals("")) {
				cs = conn.prepareCall(this.proc);
								
				if(this.proc_single_value != null) {
					lcl_col_size = this.proc_single_value.size()/2;
					
					//sets the values in callable statement
					for(int i = 0;i<lcl_col_size;i++) {
						
						paramValue = ((String)this.proc_single_value.get(2*i)).split(":");
						index=Integer.parseInt(paramValue[0]);
						
						keyValue = ((String)this.proc_single_value.get((2*i)+1)).split("#");
						//paramValue=((String)keyName[0]).split(":");
										
						if(keyValue[0].equals("OUTPARAMETER")) {
							
						
							strProcValue = ":X" + (++counter);
							
							if(keyValue[1].equals("1"))		//string
								cs.registerOutParameter(index,java.sql.Types.VARCHAR);
							else
							{//web row set
								cs.setNull(index, Types.NULL);
								cs.registerOutParameter(index,java.sql.Types.REF_CURSOR);
							}
							
								//cs.registerOutParameter(keyName,OracleTypes.CURSOR);
						}
						else {
														        							
								 strProcValue = (String)this.proc_single_value.get((2*i)+1);
								 if(strProcValue==null || strProcValue.trim().equals(""))
										cs.setNull(index, Types.NULL);
								 else
						         cs.setString(index, strProcValue);
								 
						}
						
						if (i == 0)
							this.procErrStr += (paramValue[1] + "=>'" + strProcValue + "'");
						else
							this.procErrStr += ("," + paramValue[1] + "=>'" + strProcValue + "'");
						
					} //end for loop
										
					//free the qry_single_value arraylist
					this.proc_single_value.clear();
					this.proc_single_value = null;
				} // end if
				
				//execute the procedure
				cs.execute();
			
				//set the output data into Map
				if(this.proc_out_index != null && this.proc_out_index.size() > 0) {
					lcl_col_size = this.proc_out_index.size();
					//initilize HashMap
					this.proc_out_value = new HashMap<String,Object>(lcl_col_size);	
										
					for(int i = 0; i<lcl_col_size;i++) {
						//keyValue = PARAMETER NAME:PARAMETER POSITION#OUTPUT TYPE
						keyValue = ((String)this.proc_out_index.get(i)).split("#");
						paramValue=keyValue[0].split(":");
						index=Integer.parseInt(paramValue[1]);
						if(keyValue[1].equals("1"))	{		//string return value
							outValue = cs.getString(index);
							//outValue = cs.getString(4);
							if(outValue == null) outValue = "";
							//check err message returned by the procedure
							if(paramValue[0].toUpperCase().trim().equals("ERR")) {
								strTemp = (String)outValue;
								if(!strTemp.equals("") || strTemp.trim().length() > 0) {
									this.errMsg = "Error thrown by procedure : " + strTemp;
									throw new Exception(this.errMsg);
								}
							}
							
							proc_out_value.put(paramValue[0],outValue);
						}
						else {		//resultset/webrowset
							//rs = (ResultSet)cs.getObject(keyValue[0]);
							rs = (ResultSet)cs.getObject(index);
							
							retData = new WebRowSetImpl();
							retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
							retData.populate(rs);
							
							proc_out_value.put(paramValue[0],retData);
							//proc_out_value.put(paramValue[0],rs);
						}
						
						//proc_out_value.put(keyValue[0],outValue);
					}
				}
				
			} //end outer if block
			else {
				this.errMsg = "Procedure is blank";
				throw new Exception(this.errMsg);
			}
			
		}
		catch(Exception e) {
			strProcArr = (this.proc.replace("(", "#")).split("#");
			this.errMsg = "HisProcedure.executeProcByPosition(" + strProcArr[0] + "(" + this.procErrStr + ")) -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		finally {
			try {
				if(cs != null) {
					cs.close();
					cs = null;
				}
				
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
				
				outValue = null;
				if(this.proc_out_index != null) {
					this.proc_out_index.clear();
					this.proc_out_index = null;
				}
				this.errFlag = false;
				this.procErrStr = "";
			}
			catch(Exception e) {}
		}
	}

	
	/**
	 * used to add procedure into batch whether it is parameterized procedure or not.
	 * It does not execute the procedure.
	 * @throws Exception - Either Mismatch in previous column size & current size
	 */
	public void execute() throws Exception {
		int valueSize = 0;
		Object[] strObj = null;
		this.errMsg = "";
		
		try {
			if(this.proc_single_value != null) valueSize = this.proc_single_value.size()/2;
			if(this.colSize == -1) this.colSize = valueSize;
			
			if(this.colSize == valueSize) {
				//how many times this query will be executed
				this.rowSize++;
				if(this.proc_single_value != null) {
					if(this.proc_multiple_value == null) this.proc_multiple_value = new ArrayList<Object[]>(1); 
					
					//store array list into string [as list is clear, refrences is lost]
					strObj = new Object[this.colSize*2];
					strObj = this.proc_single_value.toArray();
										
					this.proc_multiple_value.add(strObj);
										
					//free the qry_single_value arraylist
					this.proc_single_value.clear();
					this.proc_single_value = null;
					strObj = null;
					this.errFlag = false;
				}
			}
			else {
				this.errMsg = "Either Mismatch in previous column size & current size";
				throw new Exception(this.errMsg);
			}
			
		}
		catch(Exception e) {
			this.errMsg = "HisProcedure.execute(Procedure Name : " + this.proc + ") -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
	}
	
	/**
	 * This function executes a single procedure at a time which is in batch.
	 * It is called from {@link HisDAO#fire()} method.
	 * This method does not set the output parameter.
	 * @param conn - Connection Object
	 * @throws Exception - if any Exception arrives at the time of Execution. 
	 */
	public void executeProc_Batch(Connection conn) throws Exception {
		
		Object[] tempList = null;
		String keyName = "";
		String[] keyValue = null;
		String strTemp = "";
		String strProcValue = "";
		String[] strProcArr = null;
		int lcl_col_size = 0;
		Object outValue = null;
		WebRowSet retData = null;
		int counter = 0;
		
		this.errMsg = "";
		
		try {
			
			if(!this.proc.equals("")) {
				if(this.cs == null) this.cs = conn.prepareCall(this.proc);
				
						
				if(this.proc_multiple_value != null) {
					tempList = (Object[])this.proc_multiple_value.get(this.gblIndex++);
					
							
					//sets the values in prepared statement
					for(int i = 0;i<this.colSize;i++) { 
						keyName = (String)tempList[2*i];
						keyValue = ((String)tempList[(2*i) + 1]).split("#");
											
						if(keyValue[0].equals("OUTPARAMETER")) {
							
							strProcValue = ":X" + (++counter);
							
							if(keyValue[1].equals("1"))	//string
								this.cs.registerOutParameter(keyName,java.sql.Types.VARCHAR);
							else
								this.cs.registerOutParameter(keyName,java.sql.Types.REF_CURSOR);
								//this.cs.registerOutParameter(keyName,OracleTypes.CURSOR);
						}
						else {				
							
						 strProcValue = (String)tempList[(2*i) + 1];
						 if(strProcValue==null || strProcValue.trim().equals("")) // Added By Pragya 2012.11.15 
							 this.cs.setNull(keyName,Types.NULL);
						 this.cs.setString(keyName,strProcValue);
							
						}
					
						if (i == 0)
							this.procErrStr += (keyName + "=>'" + strProcValue + "'");
						else
							this.procErrStr += ("," + keyName + "=>'" + strProcValue + "'");
					}
				}
				
				//execute the query
				this.cs.execute();
				
				//get the procedure output value
				if(this.proc_out_index != null && this.proc_out_index.size() > 0) {
					lcl_col_size = this.proc_out_index.size();
					//initilize HashMap
					this.proc_out_value = new HashMap<String,Object>(lcl_col_size);	
										
					for(int i = 0; i<lcl_col_size;i++) {
						//keyValue = PARAMETER NAME#OUTPUT TYPE
						keyValue = ((String)this.proc_out_index.get(i)).split("#");
						if(keyValue[1].equals("1"))	{		//string return value
							outValue = cs.getString(keyValue[0]);
							if(outValue == null) outValue = "";

							//check err message returned by the procedure
							if(keyValue[0].toUpperCase().trim().equals("ERR")) {
								strTemp = (String)outValue;
								if(!strTemp.equals("") || strTemp.trim().length() > 0) {
									this.errMsg = "Error thrown by procedure : " + strTemp;
									throw new Exception(this.errMsg);
								}
							}
							
							proc_out_value.put(keyValue[0],outValue);
						}
						else {		//resultset/webrowset
							outValue = cs.getObject(keyValue[0]);
							
							retData = new WebRowSetImpl();
							retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
							retData.populate((ResultSet)outValue);
						}
						
						//proc_out_value.put(keyValue[0],outValue);
					}
				}
			}
			else {
				this.errMsg = "Procedure is blank";
				throw new Exception(this.errMsg);
			}
		}
		catch(Exception e) {
				
			strProcArr = (this.proc.replace("(", "#")).split("#");
			this.errMsg = "HisProcedure.executeProc_Batch(" + strProcArr[0] + "(" + this.procErrStr + ")) -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		finally {
			tempList = null;
		}
	}
	
	/**
	 * This function executes a single procedure at a time which is in batch.
	 * It is called from {@link HisDAO#fire()} method.
	 * This method does not set the output parameter.
	 * @param conn - Connection Object
	 * @throws Exception - if any Exception arrives at the time of Execution. 
	 */
	public void executeProc_BatchByPosition(Connection conn) throws Exception {
		
		Object[] tempList = null;
		String keyName = "";
		String[] keyValue = null;
		String[] paramValue;
		int index=0;
		String strTemp = "";
		String strProcValue = "";
		String[] strProcArr = null;
		int lcl_col_size = 0;
		Object outValue = null;
		WebRowSet retData = null;
		int counter = 0;
		
		this.errMsg = "";
		
		try {
			
			if(!this.proc.equals("")) {
				if(this.cs == null) this.cs = conn.prepareCall(this.proc);
				
						
				if(this.proc_multiple_value != null) {
					tempList = (Object[])this.proc_multiple_value.get(this.gblIndex++);
					
							
					//sets the values in prepared statement
					for(int i = 0;i<this.colSize;i++) { 
						
						paramValue = ((String)tempList[2*i]).split(":");
						index=Integer.parseInt(paramValue[0]);
						
						keyValue = ((String)tempList[(2*i) + 1]).split("#");
						//paramValue=((String)keyName[0]).split(":");
						
											
						if(keyValue[0].equals("OUTPARAMETER")) {
							
							strProcValue = ":X" + (++counter);
							
							if(keyValue[1].equals("1"))	//string
								this.cs.registerOutParameter(index,java.sql.Types.VARCHAR);
							else
								this.cs.registerOutParameter(index,java.sql.Types.REF_CURSOR);
								//this.cs.registerOutParameter(keyName,OracleTypes.CURSOR);
						}
						else {				
							
						 strProcValue = (String)tempList[(2*i) + 1];
						 if(strProcValue.equals("defaulttime"))
							 this.cs.setString(index, "0000-00-00" );
						 else if(strProcValue==null || strProcValue.trim().equals("")) // Added By Pragya 2012.11.15 
							 this.cs.setNull(index,Types.NULL);
						 else
     						 this.cs.setString(index,strProcValue);
							
						}
					
						if (i == 0)
							this.procErrStr += (paramValue[1] + "=>'" + strProcValue + "'");
						else
							this.procErrStr += ("," + paramValue[1] + "=>'" + strProcValue + "'");
					}
				}
				
				//execute the query
				this.cs.execute();
				
				//get the procedure output value
				if(this.proc_out_index != null && this.proc_out_index.size() > 0) {
					lcl_col_size = this.proc_out_index.size();
					//initilize HashMap
					this.proc_out_value = new HashMap<String,Object>(lcl_col_size);	
										
					for(int i = 0; i<lcl_col_size;i++) {
						//keyValue = PARAMETER NAME#OUTPUT TYPE
						//keyValue = ((String)this.proc_out_index.get(i)).split("#");
						keyValue = ((String)this.proc_out_index.get(i)).split("#");
						paramValue=keyValue[0].split(":");
						index=Integer.parseInt(paramValue[1]);
						if(keyValue[1].equals("1"))	{		//string return value
							outValue = cs.getString(index);
							if(outValue == null) outValue = "";

							//check err message returned by the procedure
							if(paramValue[0].toUpperCase().trim().equals("ERR")) {
								strTemp = (String)outValue;
								if(!strTemp.equals("") || strTemp.trim().length() > 0) {
									this.errMsg = "Error thrown by procedure : " + strTemp;
									throw new Exception(this.errMsg);
								}
							}
							
							proc_out_value.put(paramValue[0],outValue);
						}
						else {		//resultset/webrowset
							outValue = cs.getObject(index);
							
							retData = new WebRowSetImpl();
							retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
							retData.populate((ResultSet)outValue);
						}
						
						//proc_out_value.put(keyValue[0],outValue);
					}
				}
			}
			else {
				this.errMsg = "Procedure is blank";
				throw new Exception(this.errMsg);
			}
		}
		catch(Exception e) {
				
			strProcArr = (this.proc.replace("(", "#")).split("#");
			this.errMsg = "HisProcedure.executeProc_Batch(" + strProcArr[0] + "(" + this.procErrStr + ")) -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		finally {
			tempList = null;
		}
	}

	
	/**
	 * used to reset value	
	 */
	public void resetValue() {
		try {
			if(this.cs != null) {
				cs.close();
				cs = null;
			}
		}
		catch(Exception e) {}
		
		//variable initilization
		if(this.proc_single_value != null) {
			this.proc_single_value.clear();
			this.proc_single_value = null;
		}
		
		if(this.proc_multiple_value != null) {
			this.proc_multiple_value.clear();
			this.proc_multiple_value = null;
		}
		
		if(this.proc_out_index != null) {
			this.proc_out_index.clear();
			this.proc_out_index = null;
		}
		
		if(this.proc_out_value != null) {
			this.proc_out_value.clear();
			this.proc_out_value = null;
		}
		
		this.colSize = -1;
		this.rowSize = 0;
		this.gblIndex = 0;
		this.errFlag = false;
		this.procErrStr = "";
	}
	/**
	 * This function executes a single procedure at a time which is in batch.
	 * It is called from {@link HisDAO#fire()} method.
	 * This method does not set the output parameter.
	 * @param conn - Connection Object
	 * @throws Exception - if any Exception arrives at the time of Execution. 
	 */
	public void executeProc_Batch_new(Connection conn) throws Exception {
		
		Object[] tempList = null;
		String keyName = "";
		String[] keyValue = null;
		String strTemp = "";
		String strProcValue = "";
		String[] strProcArr = null;
		int lcl_col_size = 0;
		Object outValue = null;
		WebRowSet retData = null;
		int counter = 0;
		
		this.errMsg = "";
		
		try {
			
			if(!this.proc.equals("")) {
				if(this.cs == null) this.cs = conn.prepareCall(this.proc);
						
				if(this.proc_multiple_value != null) {
					tempList = (Object[])this.proc_multiple_value.get(this.gblIndex++);
					
							
					//sets the values in prepared statement
					for(int i = 0;i<this.colSize;i++) { 
						keyName = (String)tempList[2*i];
						keyValue = ((String)tempList[(2*i) + 1]).split("#");
											
						if(keyValue[0].equals("OUTPARAMETER")) {
							
							strProcValue = ":X" + (++counter);
							
							if(keyValue[1].equals("1"))	//string
							{
								//oracle
								//this.cs.registerOutParameter(keyName,java.sql.Types.VARCHAR);
								//postgres
								this.cs.registerOutParameter(i+1,java.sql.Types.VARCHAR);
							}
							else
							{
								//oracle
								//this.cs.registerOutParameter(keyName,OracleTypes.CURSOR);
								//postgres
								this.cs.registerOutParameter(i+1,java.sql.Types.REF_CURSOR);
							}
						}
						else {				
							
						 strProcValue = (String)tempList[(2*i) + 1];
						 //oracle
						 //this.cs.setString(keyName,strProcValue);
						 //postgres
						 if(strProcValue==null || strProcValue.trim().equals("")) 
							 this.cs.setNull(i+1,Types.NULL);
						 else
							 this.cs.setString(i+1,strProcValue);
						}
					
						if (i == 0)
							this.procErrStr += (keyName + "=>'" + strProcValue + "'");
						else
							this.procErrStr += ("," + keyName + "=>'" + strProcValue + "'");
					}
				}
				
				//execute the query
				this.cs.execute();
				
				//get the procedure output value
				if(this.proc_out_index != null && this.proc_out_index.size() > 0) {
					lcl_col_size = this.proc_out_index.size();
					//initilize HashMap
					this.proc_out_value = new HashMap<String,Object>(lcl_col_size);	
										
					for(int i = 0; i<lcl_col_size;i++) {
						//keyValue = PARAMETER NAME#OUTPUT TYPE
						keyValue = ((String)this.proc_out_index.get(i)).split("#");
						if(keyValue[1].equals("1"))	{		//string return value
							//oracle
							//outValue = cs.getString(keyValue[0]);
							//postgres
							outValue = cs.getString(Integer.parseInt(keyValue[2]));
							
							if(outValue == null) outValue = "";

							//check err message returned by the procedure
							if(keyValue[0].toUpperCase().trim().equals("ERR")) {
								strTemp = (String)outValue;
								if(!strTemp.equals("") || strTemp.trim().length() > 0) {
									this.errMsg = "Error thrown by procedure : " + strTemp;
									throw new Exception(this.errMsg);
								}
							}
							
							proc_out_value.put(keyValue[0],outValue);
						}
						else {		//resultset/webrowset
							//oracle
							//outValue = cs.getObject(keyValue[0]);
							//postgres
							outValue = cs.getObject(Integer.parseInt(keyValue[2]));
							
							retData = new WebRowSetImpl();
							retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
							retData.populate((ResultSet)outValue);
							
							//change on 31/oct/13 by ajay
							proc_out_value.put(keyValue[0],retData);
						}
						
						//proc_out_value.put(keyValue[0],outValue);
					}
				}
			}
			else {
				this.errMsg = "Procedure is blank";
				throw new Exception(this.errMsg);
			}
		}
		catch(Exception e) {
				
			strProcArr = (this.proc.replace("(", "#")).split("#");
			this.errMsg = "HisProcedure.executeProc_Batch_new(" + strProcArr[0] + "(" + this.procErrStr + ")) -->\n" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		finally {
			tempList = null;
			if(this.cs != null)
			{
				this.cs.close();
				this.cs = null;
			}
		}
	}
	
}

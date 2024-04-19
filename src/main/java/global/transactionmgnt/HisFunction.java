/*
 * Copyright �C-DAC Noida.
 */

package global.transactionmgnt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.rowset.WebRowSetImpl;

//import oracle.jdbc.OracleTypes;

import javax.sql.rowset.WebRowSet;
import java.sql.ResultSet;
import com.sun.rowset.WebRowSetImpl;
/**
 * @author Ajay Kumar Gupta <br> Copyright �C-DAC Noida
 * 
 */
public final class HisFunction
{
	String func = "";
	
	private CallableStatement cs = null;
	private List <String>func_single_value = null;
	
	private int func_return_type = 0;
	private Object func_out_value = null;
	
	/**
	 * used for error messages
	 */
	private String errMsg = "";
	
	private String funcErrStr = "";
	
	/**
	 * Make the class Non-instanciable
	 */
	private HisFunction() {}
	
	/**
	 * HisFunction Constructor
	 * @param func - Function Call String.
	 */
	public HisFunction(String func) {
		this.func = func;
	}
	
	/**
	 * returns HisFunction Object
	 * @param func - Function Call String.
	 * @return HisFunction.
	 */
	public static HisFunction getFuncInstance(String func) {
		return (new HisFunction(func));
	} 	
	
	/*sets the input value for parameterized procedure. It sets value by name
	 *  ParameterType 	= 1 -->> Integer
	 * 			  		= 2 -->> DOUBLE
	 * 			  		= 3 -->> String
	 */
	
	/**
	 * 
	 * @param parameterName
	 * @param val
	 */
	public void setInValue(String parameterName,String val) {
		//initialize the ArrayList
		if(this.func_single_value == null) this.func_single_value = new ArrayList<String>(3);
		
		this.func_single_value.add(parameterName);
		this.func_single_value.add(val);
	}
	
	/*
	 * This function 
	 * ReturnType = 1 -->> 
	 * 			  = 2 -->> 
	 */
	
	/**
	 * sets the return type for Oracle Function
	 * @param returnType  <b>1</b>-Integer/Double/String and <b>2</b>-WebRowSet.
	 */
	public void setOutValue(int returnType) {
		this.func_return_type = returnType;
	}
	
	
	/**
	 * used to retrieve the procedure output value
	 * @return Object - procedure output value.
	 * @throws Exception - when No Out-Parameter Defined
	 */	
	public Object getOutputValue() throws Exception {
			
		try {
			if(this.func_return_type > 0) {
				return func_out_value;
			}
			else {
				this.errMsg = "No Out-Parameter Defined";
				throw new Exception(this.errMsg);
			}
		}
		catch(Exception e) {
			this.errMsg = "HisFunction.getOutputValue(Function Name : " + this.func + ") -->" + e.getMessage();
			throw new Exception(this.errMsg);
		}
	}
	
	
	/**
	 * executes a single procedure and sets the output value into proc_out_value map.
	 * @param conn - Connection Object.
	 * @throws Exception - Either Function is blank Or Return Type is not defined
	 */
	public void executeFunc(Connection conn) throws Exception {
		
		CallableStatement cs = null;
				
		int keyName = 0;
		String keyValue = "";	
		String[] strFuncArr = null;		
		int lcl_col_size = 0;
		ResultSet rs = null;
		
		this.errMsg = "";
				
		try {
			if(!this.func.equals("") && this.func_return_type > 0) {
				cs = conn.prepareCall(this.func);
								
				if(this.func_single_value != null) {
					lcl_col_size = this.func_single_value.size()/2;
					
					//sets the values in callable statement
					for(int i = 0;i<lcl_col_size;i++) {
						keyName = Integer.parseInt((String)this.func_single_value.get(2*i));
						keyValue = ((String)this.func_single_value.get((2*i)+1));
						
						if(i == 0) {
							//Integer
							if(this.func_return_type == 1) cs.registerOutParameter(1,java.sql.Types.VARCHAR);
							
							//ResultSet
							if(this.func_return_type == 2)
								cs.registerOutParameter(1,java.sql.Types.REF_CURSOR);

								//cs.registerOutParameter(1,OracleTypes.CURSOR);
						}
						
						cs.setString(keyName,keyValue);
						
						if (i == 0)
							this.funcErrStr += (keyName + "=>'" + keyValue + "'");
						else
							this.funcErrStr += ("," + keyName + "=>'" + keyValue + "'");
						
					} //end for loop
										
					//free the qry_single_value arraylist
					this.func_single_value.clear();
					this.func_single_value = null;
				} // end if
				
				//execute the procedure
				cs.execute();
						
				//set the output data into Map
				if(this.func_return_type > 0) {
					if(this.func_return_type == 1) 
							this.func_out_value = cs.getString(1);
					if(this.func_return_type == 2) {
						//this.func_out_value = cs.getObject(1);
						
						WebRowSet retData = new WebRowSetImpl();
						retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
						rs = (ResultSet)cs.getObject(1);
						retData.populate(rs);
						this.func_out_value = retData;
					}
				}
				
			} //end outer if block
			else {
				this.errMsg = "Either Function is blank Or Return Type is not defined";
				throw new Exception(this.errMsg);
			}
			
		}
		catch(Exception e) {
			strFuncArr = (this.func.replace("(", "#")).split("#");
			this.errMsg = "HisFunction.executeFunc(" + strFuncArr[0] + "(" + this.funcErrStr + ")) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		finally {
			try {
				if(cs != null) {
					cs.close();
					cs = null;
				}
				
				if (rs != null) {
					rs.close();
					rs = null;
				}
			}
			catch(Exception e) {}
		}
	}

	
	/**
	 * executes a single procedure and sets the output value into proc_out_value map.
	 * @param conn - Connection Object.
	 * @throws Exception - Either Function is blank Or Return Type is not defined
	 */
	public void executeFuncForNumeric(Connection conn) throws Exception {
		
		CallableStatement cs = null;
				
		int keyName = 0;
		String keyValue = "";	
		String[] strFuncArr = null;		
		int lcl_col_size = 0;
		ResultSet rs = null;
		
		this.errMsg = "";
				
		try {
			if(!this.func.equals("") && this.func_return_type > 0) {
				cs = conn.prepareCall(this.func);
								
				if(this.func_single_value != null) {
					lcl_col_size = this.func_single_value.size()/2;
					
					//sets the values in callable statement
					for(int i = 0;i<lcl_col_size;i++) {
						keyName = Integer.parseInt((String)this.func_single_value.get(2*i));
						keyValue = ((String)this.func_single_value.get((2*i)+1));
						
						if(i == 0) {
							//VARCHAR
							if(this.func_return_type == 1) cs.registerOutParameter(1,java.sql.Types.VARCHAR);
							//ResultSet
							//if(this.func_return_type == 2) cs.registerOutParameter(1,OracleTypes.CURSOR);
							if(this.func_return_type == 2) cs.registerOutParameter(1,java.sql.Types.REF_CURSOR);
							//NUMERIC
							if(this.func_return_type == 3) cs.registerOutParameter(1,java.sql.Types.NUMERIC);
						}
						
						cs.setString(keyName,keyValue);
						
						if (i == 0)
							this.funcErrStr += (keyName + "=>'" + keyValue + "'");
						else
							this.funcErrStr += ("," + keyName + "=>'" + keyValue + "'");
						
					} //end for loop
										
					//free the qry_single_value arraylist
					this.func_single_value.clear();
					this.func_single_value = null;
				} // end if
				
				//execute the procedure
				cs.execute();
						
				//set the output data into Map
				if(this.func_return_type > 0) {
					if(this.func_return_type == 1) 
						this.func_out_value = cs.getString(1);
					if(this.func_return_type == 3) 
						this.func_out_value = cs.getBigDecimal(1);
					
					if(this.func_return_type == 2) {
						//this.func_out_value = cs.getObject(1);
						
						WebRowSet retData = new WebRowSetImpl();
						retData.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
						rs = (ResultSet)cs.getObject(1);
						retData.populate(rs);
						this.func_out_value = retData;
					}
				}
				
			} //end outer if block
			else {
				this.errMsg = "Either Function is blank Or Return Type is not defined";
				throw new Exception(this.errMsg);
			}
			
		}
		catch(Exception e) {
			strFuncArr = (this.func.replace("(", "#")).split("#");
			this.errMsg = "HisFunction.executeFunc(" + strFuncArr[0] + "(" + this.funcErrStr + ")) -->" + e.getMessage();
			throw new Exception(this.errMsg);
		}
		finally {
			try {
				if(cs != null) {
					cs.close();
					cs = null;
				}
				
				if (rs != null) {
					rs.close();
					rs = null;
				}
			}
			catch(Exception e) {}
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
		if(this.func_single_value != null) {
			this.func_single_value.clear();
			this.func_single_value = null;
		}
		
		func_return_type = 0;
		func_out_value = null;
		this.funcErrStr = "";
	}
	
}

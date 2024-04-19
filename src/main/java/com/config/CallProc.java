package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import global.transactionmgnt.HisDAO;

// private Map proc_out_value = null;




import global.transactionmgnt.HisProcedure;
/**
 *
 * @author postgresqltutorial.com
 */
public class CallProc
{
        
   	
	private final static String url = "jdbc:edb://10.10.10.235:5444/aiims_bibinagar";
	private final static String user = "aiimsbibinagar";
	private final static String password = "a11msb!b!n@g@r*$";
	
	
	/*
	private final static String url = "jdbc:edb://10.10.10.127:5444/aiims_raipur";
	private final static String user = "aiimsraipur";
	private final static String password = "A11m$Ra19Ur";
	*/
    
    private int qryIndex = 0;
    private static int procIndex = 0;
    private int funcIndex = 0;

    private String moduleName = "";
    private String fileName = "";
    private static boolean retValue = false;
    private static String errMsg = "";
    private static List<HisProcedure> procObj = null;
    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    private static Connection getConnection() throws Exception 
    {

    	String lookUpName = "AHIS";
    	
		Connection conn = null;
		
		 Context ic = null;
		 DataSource ds = null;


		try {
			 Class.forName("com.edb.Driver");
			 conn = DriverManager.getConnection(url, user, password);
			   

			 			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return conn;
	}
    
         
    
    
    public static void main2(String[] args)
    {
    	HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			        System.out.println("------------------ CategoryMstDAO. getCategoryList -- CALLPROC--[PKG_MMS_VIEW.proc_item_category_list - 3]----------------");
			        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
			        			       
			        
			        strProcName = "{call PKG_MMS_VIEW.proc_item_category_list(?,?,?,?,?   ,?)}"; // 6				       
			        nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    "3");				
					dao.setProcInValue(nprocIndex, "store_id",		   "100");			
					dao.setProcInValue(nprocIndex, "hosp_code",	        "10");
					dao.setProcInValue(nprocIndex, "reqtype",	         "0");				
									
					dao.setProcOutValue(nprocIndex, "err",			     1);
					dao.setProcOutValue(nprocIndex, "resultset", 	     2);
					//dao.executeProcedure(nprocIndex);
					strerr = dao.getString(nprocIndex, "err");
					if (strerr == null) {
						strerr = "";
					}
					ws = dao.getWebRowSet(nprocIndex, "resultset");
					
					System.out.println("Size --"+ws.size());
					if(ws.next())
					{
						System.out.println("One --"+ws.getString(1));
						System.out.println("Two --"+ws.getString(2));
					}
				
				
				if (strerr.equals("")) 
				{
									 

				} 
				else 
				{
					throw new Exception(strerr);
				}
		

		} catch (Exception e) {
			
			e.printStackTrace();
			
							

		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;

			}

		}
    }
   
    
        
    
    
}
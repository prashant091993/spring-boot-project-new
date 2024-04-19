package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class GroupMstService 
{
	public WebRowSet findAllGroup(String hosp_code,String catg_Code,String grp_id,String strMode)
	{
			
		    	HisDAO dao = null;
				WebRowSet ws =null;
				String strProcName = "",strerr="";
				int nprocIndex = 0;		
				
				try 
				{
							System.out.println("------------------ GroupMstDAO. getGroupList ----[pkg_inventory_ws_view.ws_group_list -1]----------------");
					        dao = new HisDAO("MMS", "GroupMstDAO");				        
					        strProcName = "{call pkg_inventory_ws_view.ws_group_list(?,?,?,?,?   ,?)}"; //6	
					       
					        nprocIndex = dao.setProcedure(strProcName);				
							dao.setProcInValue(nprocIndex, "modeval", 		   strMode);				
							dao.setProcInValue(nprocIndex, "hosp_code",		   "100");			
							dao.setProcInValue(nprocIndex, "item_category",	   catg_Code);
							dao.setProcInValue(nprocIndex, "group_id",	       grp_id);								
							dao.setProcOutValue(nprocIndex, "err",			     1);
							dao.setProcOutValue(nprocIndex, "resultset", 	     2);
							dao.executeProcedure(nprocIndex);
							strerr = dao.getString(nprocIndex, "err");
							if (strerr == null) {
								strerr = "";
							}
							ws = dao.getWebRowSet(nprocIndex, "resultset");
							
										
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
		
		return ws;

	}
	
	public String Delete_Group(String strGroupId) throws Exception 
	{		
		int 		nProcIndex1=0;
		HisDAO 		dao = null;
		String 		result="";
		String      err ="";
		try 
		{
			
			    System.out.println("---- GroupMstDAO. Drug_Group_Save_Update_Method -[ pkg_MMS_dml.dml_group_webservice Mode - 2 ] ----");
			    System.out.println("Grp_id -->>"+strGroupId);			    
			 
			    
				dao = new HisDAO("MMS", "IndentOpnDAO");					
				String strProcName1 = "{call pkg_MMS_dml.dml_group_webservice(?,?,?,?,?,   ?,?)}"; // 7			
				nProcIndex1 = dao.setProcedure(strProcName1);							
				dao.setProcInValue(nProcIndex1, "modval",          	    "2"); 
				dao.setProcInValue(nProcIndex1, "grp_Name", 	        "NA"); 
				dao.setProcInValue(nProcIndex1, "grp_Id", 		     	strGroupId); 
				dao.setProcInValue(nProcIndex1, "catg_Code", 		   	"0"); 
				dao.setProcInValue(nProcIndex1, "hosp_Code", 	     	"100");
				dao.setProcInValue(nProcIndex1, "seat_Id",    	        "10001");							
				dao.setProcOutValue(nProcIndex1, "err", 				1); 				
				dao.executeProcedure(nProcIndex1);					
				//dao.getString(nProcIndex1, "err");
				
				err = dao.getString(nProcIndex1, "err");

				if(err.length()==0)
				{	
				    result="Group ["+strGroupId+"] Logicaly Deleted Successfully !!!";
				}
				else
				{
					result="Error Occured "+err;
				}
				
									
				System.out.println("----------------------------END Drug_Group_Save_Update_Method-----------------------------------");

				
				
		}
		catch (Exception e) 
		{
			result="Exception Raised ";
			e.printStackTrace();
			
			
			if(e.getMessage().contains("###")){
				
				return e.getMessage().split("###")[1];
				
			}else{
				
				throw e;
			}

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;

			}

		}
		//System.out.println("result-->>"+result);
		return result;
	}
	
	public String Drug_Group_Save_Update_Method(String var1) throws Exception 
	{		
		int 		nProcIndex1=0;
		HisDAO 		dao = null;
		String 		result="";
		String      err ="";
		try 
		{
			
			    System.out.println("---- GroupMstDAO. Drug_Group_Save_Update_Method -[ pkg_MMS_dml.dml_group_webservice Mode - 1 ] ----");
			    System.out.println("Grp_Name ^ Grp_Id ^ Catg_Code -->>"+var1);			    
			 
			    
				dao = new HisDAO("MMS", "IndentOpnDAO");					
				String strProcName1 = "{call pkg_MMS_dml.dml_group_webservice(?,?,?,?,?,   ?,?)}"; // 7			
				nProcIndex1 = dao.setProcedure(strProcName1);							
				dao.setProcInValue(nProcIndex1, "modval",          	    "1"); 
				dao.setProcInValue(nProcIndex1, "grp_Name", 	        var1.split("\\^")[0]); 
				dao.setProcInValue(nProcIndex1, "grp_Id", 		     	var1.split("\\^")[1]); 
				dao.setProcInValue(nProcIndex1, "catg_Code", 		   	var1.split("\\^")[2]); 
				dao.setProcInValue(nProcIndex1, "hosp_Code", 	     	"100");
				dao.setProcInValue(nProcIndex1, "seat_Id",    	        "10001");							
				dao.setProcOutValue(nProcIndex1, "err", 				1); 				
				dao.executeProcedure(nProcIndex1);					
				//dao.getString(nProcIndex1, "err");
				
				err = dao.getString(nProcIndex1, "err");

				if(var1.split("\\^")[1].equals("0"))
				{	
				    result="Group ["+var1.split("\\^")[0]+"] Created Successfully !!!";
				}
				else
				{
					result="Group ["+var1.split("\\^")[0]+"] Updated Successfully !!!";
				}
				
									
				System.out.println("----------------------------END Drug_Group_Save_Update_Method-----------------------------------");

				
				
		}
		catch (Exception e) 
		{
			result="Exception Raised ";
			e.printStackTrace();
			
			
			if(e.getMessage().contains("###")){
				
				return e.getMessage().split("###")[1];
				
			}else{
				
				throw e;
			}

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;

			}

		}
		//System.out.println("result-->>"+result);
		return result;
	}
	

}

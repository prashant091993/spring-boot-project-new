package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class DrugMstService 
{

	public WebRowSet getGenDrugList(String strVar1,String strVar2,String strVar3,String strMode) 
	{

		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			
			
			        System.out.println("------------------ DrugMstDAO. getGenDrugList ----[pkg_inventory_ws_view.ws_generic_drugs_mst_dtls ]----------------");
			        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
			        //proc_item_list(modeval , catcode , grpid , subgroup_id , hosp_code , OUT err character varying, OUT resultset ahis_type.refcursor)
			        strProcName = "{call pkg_inventory_ws_view.ws_generic_drugs_mst_dtls(?,?,?,?,?   ,?,?)}"; // 7			        
			       
				    nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    "1");		
					dao.setProcInValue(nprocIndex, "hosp_code",    	   "100");		
					
					dao.setProcInValue(nprocIndex, "item_id",		   strVar1);			
					dao.setProcInValue(nprocIndex, "grp_id",	       strVar2);
					dao.setProcInValue(nprocIndex, "catg_code",	       strVar3);						
								
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
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;

			}

		}
		return ws;

		
	}
	
	public String Delete_Drug(String strGroupId) throws Exception 
	{		
		int 		nProcIndex1=0;
		HisDAO 		dao = null;
		String 		result="";
		String      err ="";
		try 
		{
			
			    System.out.println("---- GroupMstDAO. Drug_Group_Save_Update_Method -[ pkg_inventory_ws_dml.dml_group_webservice Mode - 2 ] ----");
			    System.out.println("Grp_id -->>"+strGroupId);			    
			 
			    
				dao = new HisDAO("MMS", "IndentOpnDAO");					
				String strProcName1 = "{call pkg_inventory_ws_dml.dml_group_webservice(?,?,?,?,?,   ?,?)}"; // 7			
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
	
	public String Generic_Drug_Group_Save_Update_Method(String var1) throws Exception 
	{		
		int 		nProcIndex1=0;
		HisDAO 		dao = null;
		String 		result="";
		String      err ="";
		try 
		{
			
			    System.out.println("---- DrugMstDAO. Generic_Drug_Group_Save_Update_Method -[ pkg_inventory_ws_dml.dml_gen_drug_webservice Mode - 1 ] ----");
			    System.out.println("genericDrugName ^ itemId ^ groupId ^ unitId ^ catg_Code ^ catg_Code [ Test Generic^0^101007^6301^10 ]  -->>"+var1);		
			    		   
			   			    
				dao = new HisDAO("MMS", "DrugMstDAO");					
				String strProcName1 = "{call pkg_inventory_ws_dml.dml_gen_drug_webservice(?,?,?,?,?,   ?,?,?,?,? )}"; // 10			
				nProcIndex1 = dao.setProcedure(strProcName1);							
				dao.setProcInValue(nProcIndex1, "modval",          	    "1"); 
				dao.setProcInValue(nProcIndex1, "gen_Name", 	        var1.split("\\^")[0]); 
				dao.setProcInValue(nProcIndex1, "item_Id", 		     	var1.split("\\^")[1]); 
				dao.setProcInValue(nProcIndex1, "catg_Code", 		   	var1.split("\\^")[4]); 
				dao.setProcInValue(nProcIndex1, "grp_Id", 	     	    var1.split("\\^")[2]);				
				dao.setProcInValue(nProcIndex1, "sub_grp_Id", 	     	"100");
				dao.setProcInValue(nProcIndex1, "inv_unit_Id", 	     	var1.split("\\^")[3]);
				dao.setProcInValue(nProcIndex1, "hosp_code", 	     	"100");				
				dao.setProcInValue(nProcIndex1, "seat_Id",    	        "10001");							
				dao.setProcOutValue(nProcIndex1, "err", 				1); 				
				dao.executeProcedure(nProcIndex1);					
				//dao.getString(nProcIndex1, "err");
				
				err = dao.getString(nProcIndex1, "err");

				if(var1.split("\\^")[1].equals("0"))
				{	
				    result="Generic Drug  ["+var1.split("\\^")[0]+"] Added Successfully !!!";
				}
				else
				{
					result="Generic Drug ["+var1.split("\\^")[0]+"] Updated Successfully !!!";
				}
				
									
				System.out.println("----------------------------END Generic_Drug_Group_Save_Update_Method-----------------------------------");

				
				
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

package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.DrugBrandMstObject;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class DrugBrandMstService 
{

	public WebRowSet getDrugBrandList(String brand_Id,String edf_Flg,String catg_Code,String grp_id) 
	{

		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			        System.out.println("------------------ DrugBrandMstDAO. getDrugBrandList ----[pkg_inventory_ws_view.ws_drugs_mst_dtls ]----------------");
			        dao = new HisDAO("MMS", "DrugInventoryTransDAO");				        
			        strProcName = "{call pkg_inventory_ws_view.ws_drugs_mst_dtls(?,?,?,?,?   ,?,?,?,?)}"; // 9			        
			       
				    nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    "1");				
					dao.setProcInValue(nprocIndex, "hosp_code",		   "100");			
					dao.setProcInValue(nprocIndex, "brand_Id",	        brand_Id);
					dao.setProcInValue(nprocIndex, "item_Id",		    "0");						
					dao.setProcInValue(nprocIndex, "grp_Id",    	    grp_id);
					dao.setProcInValue(nprocIndex, "edf_Flg",		    edf_Flg);	
					dao.setProcInValue(nprocIndex, "catg_Code",		    catg_Code);	
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
	
	public String Drug_Brand_Save_Update_Method(String strCatgCode,DrugBrandMstObject[] drugBrandArray) throws Exception 
	{		
		int 		nProcIndex=0;
		HisDAO 		dao = null;
		String 		result="";
		String      err ="",drugName="",brandId="0";
		try 
		{
			
			    System.out.println("---- DrugMstDAO. Generic_Drug_Group_Save_Update_Method -[ pkg_inventory_ws_dml.dml_drug_brand_webservice Mode - 1 ] ----");
			    			    
				dao = new HisDAO("MMS", "DrugMstDAO");					
				
				String strProcName = "{call pkg_inventory_ws_dml.dml_drug_brand_webservice(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?,?)}"; // 32
	            
				for(DrugBrandMstObject drugBrandObj:drugBrandArray)
		      	{
				
					nProcIndex = dao.setProcedure(strProcName);
					dao.setProcInValue(nProcIndex, "modval", 			"1"); 
					dao.setProcInValue(nProcIndex, "brand_id", 		    drugBrandObj.getHSTNUM_ITEMBRAND_ID()); 
					dao.setProcInValue(nProcIndex, "hosp_code", 		drugBrandObj.getGNUM_HOSPITAL_CODE()); 
					dao.setProcInValue(nProcIndex, "catg_no",		 	drugBrandObj.getSSTNUM_ITEM_CAT_NO());
					dao.setProcInValue(nProcIndex, "item_id", 			drugBrandObj.getHSTNUM_ITEM_ID()); 
					dao.setProcInValue(nProcIndex, "drug_name", 		drugBrandObj.getHSTSTR_ITEM_NAME()); 
					dao.setProcInValue(nProcIndex, "mfg_id", 			drugBrandObj.getHSTNUM_MANUFACTURER_ID()); 
					dao.setProcInValue(nProcIndex, "default_rate", 		drugBrandObj.getHSTNUM_DEFAULT_RATE()); 
					dao.setProcInValue(nProcIndex, "default_rate_unit", drugBrandObj.getHSTNUM_RATE_UNIT_ID()); 
					dao.setProcInValue(nProcIndex, "app_type", 			"1"); 
					dao.setProcInValue(nProcIndex, "issue_type", 		"1"); 
					dao.setProcInValue(nProcIndex, "drug_specification",drugBrandObj.getHSTSTR_SPECIFICATION()); 
					dao.setProcInValue(nProcIndex, "item_make", 		"1"); 
					dao.setProcInValue(nProcIndex, "eff_from_date", 	"SYSDATE"); 
					dao.setProcInValue(nProcIndex, "seat_id", 			"1001");
					dao.setProcInValue(nProcIndex, "StrIsValid",		"1"); 
					dao.setProcInValue(nProcIndex, "item_type", 		drugBrandObj.getHSTNUM_ITEMTYPE_ID()); 
					dao.setProcInValue(nProcIndex, "cpa_code", 			"0"); 
					dao.setProcInValue(nProcIndex, "issue_rate", 		"0"); 
					dao.setProcInValue(nProcIndex, "qc_type", 		    "0"); 
					dao.setProcInValue(nProcIndex, "market_rate", 		"0"); 
					dao.setProcInValue(nProcIndex, "market_rate_unit", 	"0"); 
					dao.setProcInValue(nProcIndex, "grp_id", 			drugBrandObj.getHSTNUM_GRP_ID()); 	
					dao.setProcInValue(nProcIndex, "isbatchreq", 		"1"); 
					dao.setProcInValue(nProcIndex, "isexpreq", 			"1"); 			
					dao.setProcInValue(nProcIndex, "edl_flg", 			drugBrandObj.getHSTNUM_EDL_CATEGORY()); 
					dao.setProcInValue(nProcIndex, "hsncode", 			drugBrandObj.getHSTNUM_HSN_CODE()); 
					dao.setProcInValue(nProcIndex, "ismisc",		    "0"); 	
					
					dao.setProcInValue(nProcIndex, "MAX_VALUE", 		drugBrandObj.getHSTNUM_MAX_VALUE()); 
					dao.setProcInValue(nProcIndex, "MIN_VALUE", 		drugBrandObj.getHSTNUM_MIN_VALUE()); 
					dao.setProcInValue(nProcIndex, "TEMP_SENS_FLAG",	"0");
					dao.setProcOutValue(nProcIndex, "err", 				1);	
					
					dao.executeProcedure(nProcIndex);	
					
					drugName = drugBrandObj.getHSTSTR_ITEM_NAME(); 
					brandId  = (drugBrandObj.getHSTNUM_ITEMBRAND_ID() == null ? "0" :drugBrandObj.getHSTNUM_ITEMBRAND_ID());
		      	}
				if(brandId.equals("0"))
				{
					
				    result="Drug  ["+drugName+"] Added  Successfully !!!";
				}
				else
				{
					 result="Drug  ["+drugName+"]  Update Successfully !!!";
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
	
	public String Drug_Brand_Delete_Method(String strBrandId,String strItemId,String drugName) throws Exception 
	{		
		int 		nProcIndex=0;
		HisDAO 		dao = null;
		String 		result="";	
		try 
		{
			
			    System.out.println("---- DrugMstDAO. Generic_Drug_Group_Save_Update_Method -[ pkg_inventory_ws_dml.dml_drug_brand_webservice Mode - 2 ] ----");
			    			    
				dao = new HisDAO("MMS", "DrugMstDAO");					
				
				String strProcName = "{call pkg_inventory_ws_dml.dml_drug_brand_webservice(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?,?)}"; // 32
	            
				
				
					nProcIndex = dao.setProcedure(strProcName);
					dao.setProcInValue(nProcIndex, "modval", 			"2"); 
					dao.setProcInValue(nProcIndex, "brand_id", 		    strBrandId); 
					dao.setProcInValue(nProcIndex, "hosp_code", 		"0"); 
					dao.setProcInValue(nProcIndex, "catg_no",		 	"10");
					dao.setProcInValue(nProcIndex, "item_id", 			strItemId); 
					dao.setProcInValue(nProcIndex, "drug_name", 		"NA"); 
					dao.setProcInValue(nProcIndex, "mfg_id", 			"0"); 
					dao.setProcInValue(nProcIndex, "default_rate", 		"0"); 
					dao.setProcInValue(nProcIndex, "default_rate_unit", "6301"); 
					dao.setProcInValue(nProcIndex, "app_type", 			"1"); 
					dao.setProcInValue(nProcIndex, "issue_type", 		"1"); 
					dao.setProcInValue(nProcIndex, "drug_specification","NA"); 
					dao.setProcInValue(nProcIndex, "item_make", 		"1"); 
					dao.setProcInValue(nProcIndex, "eff_from_date", 	"SYSDATE"); 
					dao.setProcInValue(nProcIndex, "seat_id", 			"1001");
					dao.setProcInValue(nProcIndex, "StrIsValid",		"1"); 
					dao.setProcInValue(nProcIndex, "item_type", 		"1"); 
					dao.setProcInValue(nProcIndex, "cpa_code", 			"0"); 
					dao.setProcInValue(nProcIndex, "issue_rate", 		"0"); 
					dao.setProcInValue(nProcIndex, "qc_type", 		    "0"); 
					dao.setProcInValue(nProcIndex, "market_rate", 		"0"); 
					dao.setProcInValue(nProcIndex, "market_rate_unit", 	"0"); 
					dao.setProcInValue(nProcIndex, "grp_id", 			"0"); 	
					dao.setProcInValue(nProcIndex, "isbatchreq", 		"1"); 
					dao.setProcInValue(nProcIndex, "isexpreq", 			"1"); 			
					dao.setProcInValue(nProcIndex, "edl_flg", 			"0"); 
					dao.setProcInValue(nProcIndex, "hsncode", 			"0"); 
					dao.setProcInValue(nProcIndex, "ismisc",		    "0"); 	
					
					dao.setProcInValue(nProcIndex, "MAX_VALUE", 		"0"); 
					dao.setProcInValue(nProcIndex, "MIN_VALUE", 		"0"); 
					dao.setProcInValue(nProcIndex, "TEMP_SENS_FLAG",	"0");
					dao.setProcOutValue(nProcIndex, "err", 				1);	
					
					dao.executeProcedure(nProcIndex);	
					
				
					result="Drug  ["+drugName+"]  Logically Deleted Successfully !!!";
				
				
				
									
				System.out.println("----------------------------END Generic_Drug_Group_Save_Update_Method-----------------------------------");

				
				
		}
		catch (Exception e) 
		{
			result="Exception Raised "+e.getMessage();
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

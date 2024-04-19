package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.SupplierMstObject;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class SupplierMstService 
{
	
	public  WebRowSet getSupplierList(String supplier_Id,String catg_Code,String status_Id,String strMode) 
	 {
		
			HisDAO dao = null;
			WebRowSet ws =null;
			String strProcName = "",strerr="";
			int nprocIndex = 0;		
			
			try 
			{
					 	 
					
			        System.out.println("------------------ SupplierMstDAO. Supplier_List ----[pkg_inventory_ws_view.ws_supplier_list ]----------------");
			        dao = new HisDAO("MMS", "SupplierMstDAO");
			        
			        strProcName = "{call pkg_inventory_ws_view.ws_supplier_list(?,?,?,?,?, ?,?)}"; // 7			        
			       
				    nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    strMode);				
					dao.setProcInValue(nprocIndex, "supp_id",		    supplier_Id);			
					dao.setProcInValue(nprocIndex, "hosp_code",	        "100");	
					dao.setProcInValue(nprocIndex, "item_category",		catg_Code);	
					dao.setProcInValue(nprocIndex, "supplier_status",	status_Id);	
					dao.setProcOutValue(nprocIndex, "err",			    1);
					dao.setProcOutValue(nprocIndex, "resultset", 	    2);
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
	
	public  WebRowSet getSupplierStatus(String supplier_Id,String catg_Code,String status_Id,String strMode) 
	{
	
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
				 	 
				
		        System.out.println("------------------ SupplierMstDAO. Supplier_Status ----[pkg_inventory_ws_view.ws_supplier_status ]----------------");
		        dao = new HisDAO("MMS", "SupplierMstDAO");
		        
		        strProcName = "{call pkg_inventory_ws_view.ws_supplier_status(?,?,?,?,? )}"; // 5			        
		       
			    nprocIndex = dao.setProcedure(strProcName);				
				dao.setProcInValue(nprocIndex, "modeval", 		    strMode);				
				dao.setProcInValue(nprocIndex, "itemcatcode",		catg_Code);			
				dao.setProcInValue(nprocIndex, "hosp_code",	        "100");							
				dao.setProcOutValue(nprocIndex, "err",			    1);
				dao.setProcOutValue(nprocIndex, "resultset", 	    2);
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
	
	public  WebRowSet getSupplierType(String supplier_Id,String catg_Code,String status_Id,String strMode) 
	{
	
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
				 	 
				
		        System.out.println("------------------ SupplierMstDAO. SUPPLIER_TYPE ----[pkg_inventory_ws_view.ws_supp_type_list ]----------------");
		        dao = new HisDAO("MMS", "SupplierMstDAO");
		        
		        strProcName = "{call pkg_inventory_ws_view.ws_supp_type_list(?,?,?,?)}"; // 4			        
		       
			    nprocIndex = dao.setProcedure(strProcName);				
				dao.setProcInValue(nprocIndex, "modeval", 		    strMode);
				dao.setProcInValue(nprocIndex, "hosp_code",	        "100");							
				dao.setProcOutValue(nprocIndex, "err",			    1);
				dao.setProcOutValue(nprocIndex, "resultset", 	    2);
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
	
	public  WebRowSet getSupplierGrade(String supplier_Id,String catg_Code,String status_Id,String strMode) 
	{
	
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
				 	 
				
		        System.out.println("------------------ SupplierMstDAO. SUPPLIER_GRADE ----[pkg_inventory_ws_view.ws_supplier_grade ]----------------");
		        dao = new HisDAO("MMS", "SupplierMstDAO");
		        
		        strProcName = "{call pkg_inventory_ws_view.ws_supplier_grade(?,?,?,?,?)}"; // 5			        
		       
			    nprocIndex = dao.setProcedure(strProcName);				
				dao.setProcInValue(nprocIndex, "modeval", 		    strMode);
				dao.setProcInValue(nprocIndex, "itemcatcode",       catg_Code);	
				dao.setProcInValue(nprocIndex, "hosp_code",	        "100");							
				dao.setProcOutValue(nprocIndex, "err",			    1);
				dao.setProcOutValue(nprocIndex, "resultset", 	    2);
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
	
	public  String Supplier_Save_Update_Method(SupplierMstObject mobRptObject) throws Exception 
	{		
		int 		nProcIndex=0;
		HisDAO 		dao = null;
		String 		result="";
		String      err ="";
		try 
		{
			
			    System.out.println("---- SupplierMstDAO. Supplier_Save_Update_Method -[ pkg_inventory_ws_dml.dml_supplier_webservice Mode - 1 ] ----");
			    System.out.println("-storeJsonObj.getGNUM_HOSPITAL_CODE()---"+mobRptObject.getHSTNUM_SUPPLIER_ID());
				  System.out.println("-storeJsonObj.getHSTNUM_STORETYPE_ID()---"+mobRptObject.getGNUM_HOSPITAL_CODE());
				  System.out.println("-storeJsonObj.getGNUM_DEPT_CODE()---"+mobRptObject.getSSTNUM_ITEM_CAT_NO());
				  System.out.println("-storeJsonObj.getGNUM_WARD_CODE()---"+mobRptObject.getHSTNUM_SUPPLIER_GRADE_ID());					  
				  System.out.println("-storeJsonObj.getHGSTR_EMP_CODE()---"+mobRptObject.getHSTSTR_SUPPLIER_NAME());
				  System.out.println("-storeJsonObj.getSSTNUM_ITEM_CAT_NO()---"+mobRptObject.getHSTSTR_CONTACT_PERSON());
				  System.out.println("-storeJsonObj.getSSTNUM_INDENTTYPE_ID()---"+mobRptObject.getHSTSTR_ADDRESS());
				  System.out.println("-storeJsonObj.getHSTNUM_STORETYPE_ID()---"+mobRptObject.getHSTSTR_CITY_NAME());
				  System.out.println("-storeJsonObj.getGNUM_DEPT_CODE()---"+mobRptObject.getHSTSTR_PINCODE());
				  System.out.println("-storeJsonObj.getGNUM_WARD_CODE()---"+mobRptObject.getHSTSTR_PHONE1());					  
				  System.out.println("-storeJsonObj.getHGSTR_EMP_CODE()---"+mobRptObject.getHSTSTR_EMAIL1());
				  System.out.println("-storeJsonObj.getSSTNUM_ITEM_CAT_NO()---"+mobRptObject.getHSTSTR_FAXNO1());
				  System.out.println("-storeJsonObj.getSSTNUM_INDENTTYPE_ID()---"+mobRptObject.getHSTSTR_PAN_NO());
			   				    			    
				dao = new HisDAO("MMS", "SupplierMstDAO");					
				
				String strProcName = "{call pkg_inventory_ws_dml.dml_supplier_webservice(?,?,?,?,?,  ?,?,?,?,?  , ?,?,?,?,?  ,?,?,?,?,?, ?)}";//21 Varibale's
			    					
		        nProcIndex = dao.setProcedure(strProcName);			                          
		        
				dao.setProcInValue(nProcIndex, "modval", 					"1");                         																							 		                                
				dao.setProcInValue(nProcIndex, "strsupplierid",				(mobRptObject.getHSTNUM_SUPPLIER_ID() == null ? "0" :mobRptObject.getHSTNUM_SUPPLIER_ID()));          	     																							 		                                 
				dao.setProcInValue(nProcIndex, "strhospitalcode",			(mobRptObject.getGNUM_HOSPITAL_CODE() == null ? "100" :mobRptObject.getGNUM_HOSPITAL_CODE()));  	     				 		                                
				dao.setProcInValue(nProcIndex, "strcatgcode",				(mobRptObject.getSSTNUM_ITEM_CAT_NO() == null ? "10" :mobRptObject.getSSTNUM_ITEM_CAT_NO()));           				 		                                 
				dao.setProcInValue(nProcIndex, "strsuppliergradeid",		(mobRptObject.getHSTNUM_SUPPLIER_GRADE_ID() == null ? "0" :mobRptObject.getHSTNUM_SUPPLIER_GRADE_ID()));         		                                  
				dao.setProcInValue(nProcIndex, "strsuppliername",			(mobRptObject.getHSTSTR_SUPPLIER_NAME() == null ? "NA" :mobRptObject.getHSTSTR_SUPPLIER_NAME()));        	     		                                  
				dao.setProcInValue(nProcIndex, "strcontactperson",			(mobRptObject.getHSTSTR_CONTACT_PERSON() == null ? "NA" :mobRptObject.getHSTSTR_CONTACT_PERSON())); 			 		                                  
				dao.setProcInValue(nProcIndex, "straddress",				(mobRptObject.getHSTSTR_ADDRESS() == null ? "ADDRESS" :mobRptObject.getHSTSTR_ADDRESS()));   //Drug Inventory  		         		                                  
				dao.setProcInValue(nProcIndex, "strcityname",				(mobRptObject.getHSTSTR_CITY_NAME() == null ? "CITY NAME" :mobRptObject.getHSTSTR_CITY_NAME()));      	 	                             		                                
				dao.setProcInValue(nProcIndex, "strpincode",				(mobRptObject.getHSTSTR_PINCODE() == null ? "000001" :mobRptObject.getHSTSTR_PINCODE()));         		             		                                 
				dao.setProcInValue(nProcIndex, "strphone1",					(mobRptObject.getHSTSTR_PHONE1() == null ? "0000000000" :mobRptObject.getHSTSTR_PHONE1()));  
				dao.setProcInValue(nProcIndex, "stremail1",  				(mobRptObject.getHSTSTR_EMAIL1() == null ? "NA@gmail.com" :mobRptObject.getHSTSTR_EMAIL1()));
				dao.setProcInValue(nProcIndex, "strfaxno1",					(mobRptObject.getHSTSTR_FAXNO1() == null ? "0000000000" :mobRptObject.getHSTSTR_FAXNO1())); 
				dao.setProcInValue(nProcIndex, "strpanno",					(mobRptObject.getHSTSTR_PAN_NO() == null ? "0000000000" :mobRptObject.getHSTSTR_PAN_NO())); 					
				dao.setProcInValue(nProcIndex, "strsupplierstatus", 		(mobRptObject.getHSTNUM_SUPPLIER_STATUS() == null ? "0" :mobRptObject.getHSTNUM_SUPPLIER_STATUS()));  				  
				dao.setProcInValue(nProcIndex, "strforeignersuppflag",		(mobRptObject.getHSTNUM_IS_FOREIGN() == null ? "0" :mobRptObject.getHSTNUM_IS_FOREIGN()));									
				dao.setProcInValue(nProcIndex, "strsuppliertype", 			(mobRptObject.getHSTNUM_SUPPLIERTYPE_ID() == null ? "0" :mobRptObject.getHSTNUM_SUPPLIERTYPE_ID()));					 									 
				dao.setProcInValue(nProcIndex, "strcountrycode",			(mobRptObject.getGNUM_COUNTRYCODE() == null ? "0" :mobRptObject.getGNUM_COUNTRYCODE()));						 									  
				dao.setProcInValue(nProcIndex, "strstatecode",				(mobRptObject.getGNUM_STATECODE() == null ? "0" :mobRptObject.getGNUM_STATECODE()));	
				dao.setProcInValue(nProcIndex, "strsupplierprovmaintenance",(mobRptObject.getHSTNUM_MAINTANANCE_FLAG() == null ? "0" :mobRptObject.getHSTNUM_MAINTANANCE_FLAG()));		
				dao.setProcOutValue(nProcIndex,"err", 				        1);	
				dao.executeProcedure(nProcIndex);					
				
				
				
	
				if(mobRptObject.getHSTNUM_SUPPLIER_ID().equals("0"))
				{	
				    result="Supplier  ["+mobRptObject.getHSTSTR_SUPPLIER_NAME()+"] Added Successfully !!!";
				}
				else
				{
					result="Supplier ["+mobRptObject.getHSTSTR_SUPPLIER_NAME()+"] Updated Successfully !!!";
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

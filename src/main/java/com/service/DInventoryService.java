package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.DInventoryObject;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class DInventoryService 
{
	
	
	
	public  WebRowSet getInventoryDtls(String hosp_code,String store_Id,String grp_id,String item_id,String brand_id,String catcode,String seatid,String status_code,String batch_no,String strMode) 
	 {
		
			HisDAO dao = null;
			WebRowSet ws =null;
			String strProcName = "",strerr="";
			int nprocIndex = 0;					
			try 
			{
			        System.out.println("------------------ DInventoryService. Store_List ----[pkg_inventory_ws_view.ws_inventory_dtls ]----------------");
			        dao = new HisDAO("INVENTORY", "DInventoryService");
			        /*
			         * Mode Value
			         * 1.Get Status Combo
			         * 2.Get Store Hierarchy
			         * 3.Get List Page of Store Master
			         * 4.EMP Combo
			         * 5.Ward Name Combo Pass Dept Code
			         * 6.Dept Combo
			         * 
			         * */
			      
			        
			        strProcName = "{call pkg_inventory_ws_view.ws_inventory_dtls(?,?,?,?,?, ?,?,?,?,?  ,?,?)}"; // 12		        
			       
				    nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    strMode);				
					dao.setProcInValue(nprocIndex, "hosp_code",		    (hosp_code==null) ? "0":hosp_code);			
					dao.setProcInValue(nprocIndex, "store_id",	        (store_Id==null) ? "0":store_Id);	
					dao.setProcInValue(nprocIndex, "grp_id",		    (grp_id==null) ? "0":grp_id);	
					dao.setProcInValue(nprocIndex, "item_id",	        (item_id==null) ? "0":item_id);						
					dao.setProcInValue(nprocIndex, "brand_id",	        (brand_id==null) ? "0":brand_id);	
					dao.setProcInValue(nprocIndex, "catcode",	        (catcode==null) ? "0":catcode);	
					dao.setProcInValue(nprocIndex, "seatid",	        (seatid==null) ? "0":seatid);		
					dao.setProcInValue(nprocIndex, "status_code",	    (status_code==null) ? "0":status_code);	
					dao.setProcInValue(nprocIndex, "batch_no",   	    (batch_no==null) ? "0":batch_no);	
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
	
	
	 public  String Inventory_Save_Method(DInventoryObject invntoryJsonObj,String strStoreId) throws Exception 
	 {		
			int 		nProcIndex=0;
			HisDAO 		dao = null;
			String 		result="";					
			try 
			{
							
						      System.out.println("---- StoreMstDAO. Supplier_Save_Update_Method -[ pkg_inventory_ws_Dml.dml_store_webservice Mode - 1 ] ----");
						  			 
							  System.out.println("-storeJsonObj.getGNUM_HOSPITAL_CODE()---"+invntoryJsonObj.getGNUM_HOSPITAL_CODE());		
							  System.out.println("-storeJsonObj.getSSTNUM_ITEM_CAT_NO()---"+invntoryJsonObj.getSSTNUM_ITEM_CAT_NO());
							  
							  System.out.println("-storeJsonObj.getGNUM_SEATID()---"+invntoryJsonObj.getGNUM_SEATID());
							  System.out.println("-storeJsonObj.getHSTNUM_ITEM_ID()---"+invntoryJsonObj.getHSTNUM_ITEM_ID());
							  System.out.println("-storeJsonObj.getHSTNUM_ITEMBRAND_ID()---"+invntoryJsonObj.getHSTNUM_ITEMBRAND_ID());
							  System.out.println("-storeJsonObj.getHSTSTR_BATCH_NO()---"+invntoryJsonObj.getHSTSTR_BATCH_NO());
							  System.out.println("-storeJsonObj.getHSTNUM_GROUP_ID()---"+invntoryJsonObj.getHSTNUM_GROUP_ID());
							  
							  
							  System.out.println("-storeJsonObj.getHSTDT_EXPIRY_DATE()---"+invntoryJsonObj.getHSTDT_EXPIRY_DATE());
							  System.out.println("-storeJsonObj.getHSTDT_MANUF_DATE()---"+invntoryJsonObj.getHSTDT_MANUF_DATE());
							  System.out.println("-storeJsonObj.getHSTNUM_STOCK_STATUS_CODE()---"+invntoryJsonObj.getHSTNUM_STOCK_STATUS_CODE());
							  System.out.println("-storeJsonObj.getHSTNUM_INHAND_QTY()---"+invntoryJsonObj.getHSTNUM_INHAND_QTY());
							  System.out.println("-storeJsonObj.getHSTNUM_SUPPLIER_ID()---"+invntoryJsonObj.getHSTNUM_SUPPLIER_ID());
							  System.out.println("-storeJsonObj.getHSTNUM_RATE()---"+invntoryJsonObj.getHSTNUM_RATE());
							  
							  System.out.println("-storeJsonObj.getHSTNUM_PO_NO()---"+invntoryJsonObj.getHSTNUM_PO_NO());
							  System.out.println("-storeJsonObj.getGNUM_SEATID()---"+invntoryJsonObj.getGNUM_SEATID());
							  System.out.println("-storeJsonObj.getHSTDT_PO_DATE()---"+invntoryJsonObj.getHSTDT_PO_DATE());
							  
							  System.out.println("-storeJsonObj.getHSTNUM_ADMINISTRATIVE_CHARGES()---"+invntoryJsonObj.getHSTNUM_ADMINISTRATIVE_CHARGES());
							  System.out.println("-storeJsonObj.getHSTNUM_TAX()---"+invntoryJsonObj.getHSTNUM_TAX());
							  System.out.println("-storeJsonObj.getHSTNUM_PACK_SIZE()---"+invntoryJsonObj.getHSTNUM_PACK_SIZE());
							  System.out.println("-storeJsonObj.getHSTNUM_NOOF_PACKS()---"+invntoryJsonObj.getHSTNUM_NOOF_PACKS());
				 
					  
			      	
				             System.out.println("---------------------------------------------------------------------------------------");
				  
				             dao = new HisDAO("INVENTORY", "StoreMstDAO");			
				    
				        
						    String strProcName = "{call pkg_inventory_ws_dml.dml_stock_update_prg_New(?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,? ,?)}"; //31  Varibale's
					    					
				            nProcIndex = dao.setProcedure(strProcName);	
				            				   		 				      			        			      
						    dao.setProcInValue(nProcIndex, "modval",			"1"); 
							dao.setProcInValue(nProcIndex, "strId",				strStoreId); 
							dao.setProcInValue(nProcIndex, "brandId",			invntoryJsonObj.getHSTNUM_ITEMBRAND_ID());
							dao.setProcInValue(nProcIndex, "batchNo",			invntoryJsonObj.getHSTSTR_BATCH_NO()); 
							dao.setProcInValue(nProcIndex, "expDate",			invntoryJsonObj.getHSTDT_EXPIRY_DATE()); 
							dao.setProcInValue(nProcIndex, "mfgDate",			invntoryJsonObj.getHSTDT_MANUF_DATE()); 
							dao.setProcInValue(nProcIndex, "manufId",			invntoryJsonObj.getHSTNUM_SUPPLIER_ID()); 
							dao.setProcInValue(nProcIndex, "activeQty",			invntoryJsonObj.getHSTNUM_INHAND_QTY()); 
							dao.setProcInValue(nProcIndex, "inactiveQty",		"0"); 
							dao.setProcInValue(nProcIndex, "quartineQty",		"0"); 
							dao.setProcInValue(nProcIndex, "seatId",			invntoryJsonObj.getGNUM_SEATID()); 						
							dao.setProcInValue(nProcIndex, "rate",         		invntoryJsonObj.getHSTNUM_RATE()); 
							dao.setProcInValue(nProcIndex, "rateUnitId",		"6301"); 
							dao.setProcInValue(nProcIndex, "tenderNo",			"NA"); 
							dao.setProcInValue(nProcIndex, "poNo",				invntoryJsonObj.getHSTNUM_PO_NO()); 
							dao.setProcInValue(nProcIndex, "hosp_code",			invntoryJsonObj.getGNUM_HOSPITAL_CODE()); 
							dao.setProcInValue(nProcIndex, "supplierId",		invntoryJsonObj.getHSTNUM_SUPPLIER_ID()); 
							dao.setProcInValue(nProcIndex, "prgid",				"0"); 
							dao.setProcInValue(nProcIndex, "existingbatch",		invntoryJsonObj.getHSTSTR_BATCH_NO()); 
							dao.setProcInValue(nProcIndex, "existstockstatus", 	"10"); 	
							dao.setProcInValue(nProcIndex, "dcno", 				"NA");
							dao.setProcInValue(nProcIndex, "invoiceno", 		"NA");
							dao.setProcInValue(nProcIndex, "invoicedate",		invntoryJsonObj.getHSTDT_PO_DATE());
							dao.setProcInValue(nProcIndex, "purrate",			invntoryJsonObj.getHSTNUM_RATE()); 
							dao.setProcInValue(nProcIndex, "handlingcharges",	invntoryJsonObj.getHSTNUM_ADMINISTRATIVE_CHARGES()); 
							dao.setProcInValue(nProcIndex, "purtax",			invntoryJsonObj.getHSTNUM_TAX()); 
							dao.setProcInValue(nProcIndex, "flag",				"1"); 
							dao.setProcInValue(nProcIndex, "packsize",  		invntoryJsonObj.getHSTNUM_PACK_SIZE());
							dao.setProcInValue(nProcIndex, "packno",			invntoryJsonObj.getHSTNUM_NOOF_PACKS());
							dao.setProcInValue(nProcIndex, "invFlag",			"1");
							dao.setProcOutValue(nProcIndex, "err",				 1); 					
						    dao.executeProcedure(nProcIndex);
						
											
						
						
						/*
						
			
						if(invntoryJsonObj.getHSTSTR_STORE_NAME().equals("0"))
						{	
						    result="Supplier  ["+invntoryJsonObj.getHSTSTR_STORE_NAME()+"] Added Successfully !!!";
						}
						else
						{
							result="Supplier ["+invntoryJsonObj.getHSTSTR_STORE_NAME()+"] Updated Successfully !!!";
						}
						*/
						
					
				   
				   				    			    
					
										
					System.out.println("----------------------------END Generic_Drug_Group_Save_Update_Method-----------------------------------");
		
					
					
			}
			catch (Exception e) 
			{
				result="Exception Raised ";
				e.printStackTrace();
								
				
		
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

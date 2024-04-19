package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.StoreMstObject;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class StoreMstService 
{

	
	 public  WebRowSet getStoreList(String store_Id,String dept_Code,String type_Id,String hosp_code,String seat_Id,String strMode) 
	 {
		
			HisDAO dao = null;
			WebRowSet ws =null;
			String strProcName = "",strerr="";
			int nprocIndex = 0;					
			try 
			{
			        System.out.println("------------------ StoreMstDAO. Store_List ----[pkg_inventory_ws_view.ws_store_mst ]----------------");
			        dao = new HisDAO("INVENTORY", "StoreMstDAO");
			        /*
			         * Mode Value
			         * 1.Get Store Combo
			         * 2.Get Store Hierarchy
			         * 3.Get List Page of Store Master
			         * 4.EMP Combo
			         * 5.Ward Name Combo Pass Dept Code
			         * 6.Dept Combo
			         * 
			         * */
			        
			        strProcName = "{call pkg_inventory_ws_view.ws_store_mst(?,?,?,?,?, ?,?,?,?,?)}"; // 10		        
			       
				    nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    strMode);				
					dao.setProcInValue(nprocIndex, "hosp_code",		    (hosp_code==null) ? "0":hosp_code);			
					dao.setProcInValue(nprocIndex, "storeid",	        (store_Id==null) ? "0":store_Id);	
					dao.setProcInValue(nprocIndex, "reqtype",		    (type_Id==null) ? "0":type_Id);	
					dao.setProcInValue(nprocIndex, "catcode",	        "10");						
					dao.setProcInValue(nprocIndex, "seatid",	        (seat_Id==null) ? "0":seat_Id);	
					dao.setProcInValue(nprocIndex, "dept_code",	        (dept_Code==null) ? "0":dept_Code);	
					dao.setProcInValue(nprocIndex, "ward_code",	        "0");					
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
	 
	 
	 public  WebRowSet getStoreType(String store_Id,String dept_Code,String type_Id,String hosp_code,String seat_Id,String strMode) 
	 {
		
			HisDAO dao = null;
			WebRowSet ws =null;
			String strProcName = "",strerr="";
			int nprocIndex = 0;					
			try 
			{
			        System.out.println("------------------ StoreMstDAO. Store_List ----[pkg_inventory_ws_view.ws_store_type_list ]----------------");
			        dao = new HisDAO("INVENTORY", "StoreMstDAO");
			       			        
			        strProcName = "{call pkg_inventory_ws_view.ws_store_type_list(?,?,?,?,?, ?)}"; // 6		        
			       
				    nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    strMode);				
					dao.setProcInValue(nprocIndex, "type_id",		    (type_Id==null) ? "0":type_Id);			
					dao.setProcInValue(nprocIndex, "hosp_code",	        (hosp_code==null) ? "0":hosp_code);	
					dao.setProcInValue(nprocIndex, "item_category",		"10");										
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
	 
	
	 
	 public  String Store_Save_Update_Method(StoreMstObject storeJsonObj) throws Exception 
		{		
			int 		nFuncIndex=0,nProcIndex0=0,nProcIndex=0,nProcIndex1=0,nProcIndex2=0,nProcIndex3=0,nProcIndex4=0;
			HisDAO 		dao = null;
			String 		result="";
			String      err ="",strStoreId="",strerr="";
			WebRowSet   ws = null;
			try 
			{
							
				 System.out.println("---- StoreMstDAO. Supplier_Save_Update_Method -[ pkg_inventory_ws_Dml.dml_store_webservice Mode - 1 ] ----");
				  			 
					  System.out.println("-storeJsonObj.getGNUM_HOSPITAL_CODE()---"+storeJsonObj.getGNUM_HOSPITAL_CODE());
					  System.out.println("-storeJsonObj.getHSTNUM_STORETYPE_ID()---"+storeJsonObj.getHSTNUM_STORETYPE_ID());
					  System.out.println("-storeJsonObj.getGNUM_DEPT_CODE()---"+storeJsonObj.getGNUM_DEPT_CODE());
					  System.out.println("-storeJsonObj.getGNUM_WARD_CODE()---"+storeJsonObj.getGNUM_WARD_CODE());					  
					  System.out.println("-storeJsonObj.getHGSTR_EMP_CODE()---"+storeJsonObj.getHGSTR_EMP_CODE());
					  System.out.println("-storeJsonObj.getSSTNUM_ITEM_CAT_NO()---"+storeJsonObj.getSSTNUM_ITEM_CAT_NO());
					  System.out.println("-storeJsonObj.getSSTNUM_INDENTTYPE_ID()---"+storeJsonObj.getSSTNUM_INDENTTYPE_ID());
			      	
				  System.out.println("---------------------------------------------------------------------------------------");
				  
				  dao = new HisDAO("INVENTORY", "StoreMstDAO");			
				    
				   if (storeJsonObj.getHSTNUM_STORE_ID().equals("0")) 
				   {
						
					   
					    String strProcName0 = "{call pkg_inventory_ws_view.gen_ws_store_id(?,?,?,?,?,  ?)}";//6  Varibale's
   					
				        nProcIndex0 = dao.setProcedure(strProcName0);	
				      			        			      
						dao.setProcInValue(nProcIndex0, "modeval", 				"1");                         																							 		                                
						dao.setProcInValue(nProcIndex0, "type_id",				 storeJsonObj.getHSTNUM_STORETYPE_ID());          	     																							 		                                 
						dao.setProcInValue(nProcIndex0, "hosp_code",			 storeJsonObj.getGNUM_HOSPITAL_CODE());  	     				 		                                
						dao.setProcInValue(nProcIndex0, "item_category",		 storeJsonObj.getSSTNUM_ITEM_CAT_NO());          				 		                                 
						dao.setProcOutValue(nProcIndex0, "err",			   		 1);
						dao.setProcOutValue(nProcIndex0, "resultset", 	         2);
						dao.executeProcedure(nProcIndex0);
						strerr = dao.getString(nProcIndex0, "err");
						if (strerr == null) {
							strerr = "";
						}
						ws = dao.getWebRowSet(nProcIndex0, "resultset");
						
						if(ws.next())
						{
							strStoreId = ws.getString(1);
						}						
						
						System.out.println("-[mms_mst.get_store_id]--Store_Id---"+strStoreId);
						
						storeJsonObj.setHSTNUM_STORE_ID(strStoreId);
						
						
						String strProcName = "{call pkg_inventory_ws_Dml.dml_store_webservice(?,?,?,?,?,  ?,?,?,?,?  , ?,?)}";//12  Varibale's
					    					
				        nProcIndex = dao.setProcedure(strProcName);	
				      			        			      
						dao.setProcInValue(nProcIndex, "modval", 					"1");                         																							 		                                
						dao.setProcInValue(nProcIndex, "hosp_code",					storeJsonObj.getGNUM_HOSPITAL_CODE());          	     																							 		                                 
						dao.setProcInValue(nProcIndex, "store_id",					storeJsonObj.getHSTNUM_STORE_ID());  	     				 		                                
						dao.setProcInValue(nProcIndex, "type_id",					storeJsonObj.getHSTNUM_STORETYPE_ID());           				 		                                 
						dao.setProcInValue(nProcIndex, "store_name",				storeJsonObj.getHSTSTR_STORE_NAME());         		                                  
						dao.setProcInValue(nProcIndex, "emp_code",					"0");        	     		                                  
						dao.setProcInValue(nProcIndex, "dept_code",					storeJsonObj.getGNUM_DEPT_CODE()); 			 		                                  
						dao.setProcInValue(nProcIndex, "seat_id",					storeJsonObj.getGNUM_SEATID());           		                                  
						dao.setProcInValue(nProcIndex, "ward_code",					storeJsonObj.getGNUM_WARD_CODE());      	 	                             		                                
						dao.setProcInValue(nProcIndex, "store_type",				storeJsonObj.getHSTNUM_STORETYPE_ID());         		             		                                 
						dao.setProcInValue(nProcIndex, "parent_store",				storeJsonObj.getHSTNUM_PARENT_STORE_ID()); 						                    
						dao.setProcOutValue(nProcIndex,"err", 				        1);	
						dao.execute(nProcIndex,1);
											
						
						
						
				    	String strProcName2 = "{call pkg_inventory_ws_dml.proc_store_emp_webservice(?,?,?,?,?  ,?)}";
						
						String[] arr1 = storeJsonObj.getHGSTR_EMP_CODE().split("\\@");
						
						for (int i = 0; i < arr1.length; i++) 
						{		      
							    System.out.println("Emp_Id-"+i+"--"+arr1[i]);
							    
							    nProcIndex2 = dao.setProcedure(strProcName2);
							    dao.setProcInValue(nProcIndex2, "p_mode", 				"1");                                   
						        dao.setProcInValue(nProcIndex2, "p_HSTNUM_STORE_ID", 	storeJsonObj.getHSTNUM_STORE_ID());         
								dao.setProcInValue(nProcIndex2, "p_STR_EMP_NO", 		arr1[i]); 
								dao.setProcInValue(nProcIndex2, "p_GNUM_HOSPITAL_CODE", storeJsonObj.getGNUM_HOSPITAL_CODE());  							
								dao.setProcInValue(nProcIndex2, "p_GNUM_SEATID", 		storeJsonObj.getGNUM_SEATID());    							
								dao.setProcOutValue(nProcIndex2,"err",					1);                                
								dao.execute(nProcIndex2, 1);
						      
						}				
	
						String strProcName3 = "{call pkg_inventory_ws_dml.dml_store_category_webservice(?,?,?,?,?  ,?)}";
						
						String[] arr2 = storeJsonObj.getSSTNUM_ITEM_CAT_NO().split("\\@");
						
						for (int i = 0; i < arr2.length; i++) 
						{		      
							    System.out.println("Catg_Id-"+i+"--"+arr2[i]);
							    nProcIndex3 = dao.setProcedure(strProcName3);
							    dao.setProcInValue(nProcIndex3, "p_mode", 				"1");                                   
						        dao.setProcInValue(nProcIndex3, "p_hstnum_store_id", 	storeJsonObj.getHSTNUM_STORE_ID());   
						        dao.setProcInValue(nProcIndex3, "p_sstnum_item_cat_no", arr2[i]); 	
								dao.setProcInValue(nProcIndex3, "p_gnum_hospital_code",	storeJsonObj.getGNUM_HOSPITAL_CODE()); 						
								dao.setProcInValue(nProcIndex3, "p_gnum_seatid", 		storeJsonObj.getGNUM_SEATID());    							
								dao.setProcOutValue(nProcIndex3,"err",					1);                                
								dao.execute(nProcIndex3, 1);
						      
						}
	
	                    String strProcName4 = "{call pkg_inventory_ws_dml.dml_process_mapping_webservice(?,?,?,?,?  ,?,?)}";
						
						
						for (int i = 0; i < arr2.length; i++) 
						{
							    String[] arr3 = storeJsonObj.getSSTNUM_INDENTTYPE_ID().split("\\@");
								for (int k = 0; k < arr3.length; k++) 
								{		      
									    System.out.println("Catg_Id-"+i+"--"+arr2[i]+"-Req_TYPE--"+k+"--"+arr3[k]);
									    nProcIndex4 = dao.setProcedure(strProcName4);
									    dao.setProcInValue(nProcIndex4, "modval", 				"1");                                   
								        dao.setProcInValue(nProcIndex4, "str_storeid", 			storeJsonObj.getHSTNUM_STORE_ID());   
								        dao.setProcInValue(nProcIndex4, "str_storecatid", 		arr2[i]); 					      
										dao.setProcInValue(nProcIndex4, "str_indenttypeid", 	arr3[k]); 
										dao.setProcInValue(nProcIndex4, "str_hosp",				storeJsonObj.getGNUM_HOSPITAL_CODE()); 						
										dao.setProcInValue(nProcIndex4, "str_seatid", 			storeJsonObj.getGNUM_SEATID());    							
										dao.setProcOutValue(nProcIndex4,"err",					1);                                
										dao.execute(nProcIndex4, 1);
								      
								}
						}
						
						
						dao.fire();
			
						if(storeJsonObj.getHSTSTR_STORE_NAME().equals("0"))
						{	
						    result="Supplier  ["+storeJsonObj.getHSTSTR_STORE_NAME()+"] Added Successfully !!!";
						}
						else
						{
							result="Supplier ["+storeJsonObj.getHSTSTR_STORE_NAME()+"] Updated Successfully !!!";
						}
						
					}
				    else
				    {
				    	strStoreId = "0";
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

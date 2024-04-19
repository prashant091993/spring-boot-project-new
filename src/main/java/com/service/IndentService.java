package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.IndentObject;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class IndentService 
{
	
public WebRowSet getIndentList(String hosp_code,String strStoreId,String catg_Code) {
	HisDAO dao = null;
	WebRowSet ws =null;
	String strProcName = "",strerr="";
	int nprocIndex = 0;		
	
	try 
	{
		        System.out.println("------------------ IndentMstDAO. getIndentList ----[pkg_inventory_ws_view.ws_indent_list ]----------------");
		        dao = new HisDAO("MMS", "DrugInventoryTransDAO");				        
		        strProcName = "{call pkg_inventory_ws_view.ws_indent_list(?,?,?,?,?   ,?)}"; // 6			        
		       
			    nprocIndex = dao.setProcedure(strProcName);				
				dao.setProcInValue(nprocIndex, "modeval", 		    "1");				
				dao.setProcInValue(nprocIndex, "hosp_code",		   hosp_code);			
				dao.setProcInValue(nprocIndex, "from_store_Id",	    strStoreId);				
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
	
	 public  String Indent_Save_Method(String strVar1,String strVar2,String strCatgCode,String strIndentDate,IndentObject[] indentArray) throws Exception 
	 {		
		    String 		strIndentNo="";
			int 		nProcIndex1=0,nProcIndex2=0;
			HisDAO 		dao = null;
			String 		result="";
			int 		funcIndex = 0;	
			
			String seatId       ="";
			String hosp_code    ="";
			String strStoreId   ="";
			String strToStoreId ="";
			try 
			{
							
						      System.out.println("---- StoreMstDAO. Supplier_Save_Update_Method -[ pkg_inventory_ws_Dml.dml_store_webservice Mode - 1 ] ----");
						  			 
						      hosp_code      = strVar1.split("\\^")[0];
							  seatId         = strVar1.split("\\^")[1];
								 
							  strStoreId     = strVar2.split("\\^")[0];
							  strToStoreId   = strVar2.split("\\^")[1];	
							  
							  System.out.println("strIndentNo--->>"+hosp_code);
							  System.out.println("strIndentNo--->>"+seatId );
							  System.out.println("strIndentNo--->>"+strStoreId );
							  System.out.println("strIndentNo--->>"+strToStoreId);
					  
			      	
				             System.out.println("---------------------------------------------------------------------------------------");
				  
				            dao = new HisDAO("INVENTORY", "StoreMstDAO");	
				            //hosp_code numeric, reqtype numeric, strid numeric, catcode numeric 
				            funcIndex = dao.setFunction("{? = CALL MMS_MST.GENERATE_INDENTNO(?,?,?,?)}");
							// Set Value
							dao.setFuncInValue(funcIndex, 2, hosp_code);
							dao.setFuncInValue(funcIndex, 3, "17");
							dao.setFuncInValue(funcIndex, 4, strStoreId);
							dao.setFuncInValue(funcIndex, 5, strCatgCode);
							dao.setFuncOutValue(funcIndex, 1);
							// Execute Function
							dao.executeFunction(funcIndex);
							strIndentNo = dao.getFuncString(funcIndex);
								
							System.out.println("strIndentNo--->>"+strIndentNo);
							result = strIndentNo;
				    
				        
							 for(IndentObject indentObj:indentArray)
						     {
													    	
																  
									String strProcName2 = "{call pkg_inventory_ws_Dml.dml_hstt_mobile_indentitem_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";// 23						
									nProcIndex2 = dao.setProcedure(strProcName2);						
									dao.setProcInValue(nProcIndex2, "modval", "1"); // 1
									dao.setProcInValue(nProcIndex2, "strindentno", 			strIndentNo.trim()); // 2
									dao.setProcInValue(nProcIndex2, "strid",       			strStoreId); // 3
									dao.setProcInValue(nProcIndex2, "hosp_code ",  			hosp_code); // 4
									dao.setProcInValue(nProcIndex2, "groupid",     			"0"); // 5
									dao.setProcInValue(nProcIndex2, "subgroupid",  			"0"); // 6
									dao.setProcInValue(nProcIndex2, "itemid", 				indentObj.getHstnum_ITEM_ID()); // 7
									dao.setProcInValue(nProcIndex2, "itembrandid", 			indentObj.getHstnum_ITEMBRAND_ID()); // 8
									dao.setProcInValue(nProcIndex2, "rate", 				"0"); // 9
									dao.setProcInValue(nProcIndex2, "rateunitid", 			"6301"); // 10
									dao.setProcInValue(nProcIndex2, "indentqty", 			indentObj.getHstnum_INDENT_QTY()); // 11
									dao.setProcInValue(nProcIndex2, "indentqtyunitid", 		"6301"); // 12
									dao.setProcInValue(nProcIndex2, "inhandqty", 			"0"); // 13
									dao.setProcInValue(nProcIndex2, "inhandqtyunitid", 		"6301"); // 14
									dao.setProcInValue(nProcIndex2, "consumableflag", 		"0"); // 15
									dao.setProcInValue(nProcIndex2, "reorderlevel", 		"0"); // 16
									dao.setProcInValue(nProcIndex2, "reorderlevelunitid",   "6301"); // 17
									dao.setProcInValue(nProcIndex2, "lstindentqty", 		"0"); // 18
									dao.setProcInValue(nProcIndex2, "lstindentunitid", 		"6301"); // 19
									dao.setProcInValue(nProcIndex2, "lstissueqty", 			"0"); // 20
									dao.setProcInValue(nProcIndex2, "lstissueunitid", 		"6301"); // 21
									dao.setProcInValue(nProcIndex2, "remarks", 				"Indent [ "+strIndentNo+"  ] Generated by Mobile App"); // 22
									// output value
									dao.setProcOutValue(nProcIndex2, "err", 1); // 23
									dao.execute(nProcIndex2, 1);
							
							  }
							 
														 							
							String strProcName1 = "{call pkg_inventory_ws_Dml.dml_mobile_indent_dtl(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?)}";// 17
							//System.out.println("Urgent Flg-DAO--->" + vo.getStrUrgentFlg());
							nProcIndex1 = dao.setProcedure(strProcName1);
							dao.setProcInValue(nProcIndex1, "modval",   		"1"); // 1
							dao.setProcInValue(nProcIndex1, "strid",    		strStoreId);// 2
							dao.setProcInValue(nProcIndex1, "indentNo", 		strIndentNo.trim()); // 3
							dao.setProcInValue(nProcIndex1, "hosp_code", 		hosp_code); // 4
							dao.setProcInValue(nProcIndex1, "reqtypeid", 		"17"); // 5
							dao.setProcInValue(nProcIndex1, "tostrid", 			strToStoreId); // 6
							dao.setProcInValue(nProcIndex1, "itemcatno", 		"10"); // 7
							dao.setProcInValue(nProcIndex1, "itemtypeid", 		"0"); // 8
							dao.setProcInValue(nProcIndex1, "urgentflag", 		"1"); // 9
							dao.setProcInValue(nProcIndex1, "indentperiod", 	"10"); // 10 //previously null was sending, ""
							dao.setProcInValue(nProcIndex1, "remarks",     		"Indent [ "+strIndentNo+"  ] Generated by Mobile App"); // 11
							dao.setProcInValue(nProcIndex1, "seatid",      		seatId); // 12
							dao.setProcInValue(nProcIndex1, "indentperiodvalue","2022-2023"); // 13
							dao.setProcInValue(nProcIndex1, "draftMode", 		"0"); // 14				
							dao.setProcInValue(nProcIndex1, "modifyFlag", 		"0");	//15
							dao.setProcInValue(nProcIndex1, "prgId", 			"0");   //16
							dao.setProcOutValue(nProcIndex1, "err", 			1); // 17
							dao.execute(nProcIndex1, 1);

						
					
				   
				   				    			    
					dao.fire();
										
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

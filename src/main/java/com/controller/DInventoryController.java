
package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.DInventoryObject;
import com.service.DInventoryService;


@RestController
@RequestMapping("/dInventoryService")
public class DInventoryController 
{
	
	@Autowired
	private DInventoryService dInventoryService;
	
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strVar4}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")	
	public  ResponseEntity<List<DInventoryObject>> getStoreMaster(@PathVariable("strVar1") String strVar1, @PathVariable("strVar2") String strVar2,@PathVariable("strVar3") String strVar3, @PathVariable("strVar4") String strVar4, @PathVariable("strWsMode") String strWsMode) 
	{
		ArrayList<DInventoryObject> messageData = new ArrayList<DInventoryObject>();
		WebRowSet ws =null;		
		try
		{			
			
			    System.out.println("strVar1---"+strVar1);
			    System.out.println("strVar2---"+strVar2);
			    System.out.println("strVar3---"+strVar3);
			    System.out.println("strVar4---"+strVar4);
			    System.out.println("strWsMode---"+strWsMode);
			    //http://localhost:8085/dInventoryService/0/0/0/1/1
			    //https://hmismangalagiri.uat.dcservices.in/ml_war/service/dInventoryService/0/0/0/1/1
			    System.out.println("http://localhost:8080/dInventoryService/"+strVar1+"/"+strVar2+"/"+strVar3+"/"+strVar4+"/"+strWsMode+"(strWsMode)");
			 
			    /*
		         * Mode Value
		         * 1.Stock Status Combo
		         * 2.Get Store Hierarchy
		         * 3.Get List Page of Store Master
		         * 4.EMP Combo
		         * 5.Ward Name Combo Pass Dept Code
		         * 6.Dept Combo
		         * 7.Not Mapped Category
		         * 8.Not Mapped Request Type
		         * 9.Mapped Category
		         *10.Mapped Request Type
		         * */		
			    
			    System.out.println("---- pkg_inventory_ws_view.ws_store_mst ----- "
			    		+ "\n 1.Stock Status Combo        "
			    		+ "\n 2.Get Store Hierarchy      "
			    		+ "\n 3.Get List Page of Store Master   "
			    		+ "\n 4.EMP Combo   "
			    		+ "\n 5.Ward Name Combo Pass Dept Code    "
			    		+ "\n 6.Dept Combo"
			    		+ "\n 7.Not Mapped Category"
			    		+ "\n 8.Not Mapped Request Type"
			    		+ "\n 9.Mapped Category"
			    		+ "\n10.Mapped Request Type ");
		         
			    //-----------------------------------------
			    String store_Id   = strVar1;
				String grp_id     = strVar2;
				//-----------------------------------------	
				/*
				 C.HSTNUM_STORE_ID || @ || C.HSTNUM_ITEM_ID 
				 || @ || C.HSTNUM_ITEMBRAND_ID || @ || C.HSTSTR_BATCH_NO 
				 || @ || C.SSTNUM_ITEM_CAT_NO || @ || HSTNUM_STOCK_STATUS_CODE 
				 */
				String item_id    = (strVar3.split("\\@")[1]==null) ? "0":strVar3.split("\\@")[1];
			    String brand_id   = (strVar3.split("\\@")[2]==null) ? "0":strVar3.split("\\@")[2];
			    String batch_no   = (strVar3.split("\\@")[3]==null) ? "0":strVar3.split("\\@")[3];
			    String catcode    = (strVar3.split("\\@")[4]==null) ? "0":strVar3.split("\\@")[4];
			    String status_code= (strVar3.split("\\@")[5]==null) ? "0":strVar3.split("\\@")[5]; 			   
			    //------------------------------------------
				String hosp_code  = (strVar4.split("\\@")[0]==null) ? "0":strVar4.split("\\@")[0];	
				String seat_Id    = (strVar4.split("\\@")[1]==null) ? "0":strVar4.split("\\@")[1];
				//----------------------------------------
			    if(strWsMode.equals("1")) // Get Store Combo
				{	  
						
			    	System.out.println("---Get Status Combo---");
					ws = dInventoryService.getInventoryDtls( hosp_code, store_Id, grp_id, item_id, brand_id, catcode, seat_Id, status_code, batch_no, "1"); 
					
					if (ws==null && ws.size() == 0) 
					{
			            return new ResponseEntity<List<DInventoryObject>>(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
										
				   	while(ws.next())
				    {
				   		DInventoryObject mobRptObject = new DInventoryObject();	
				   		mobRptObject.setHSTNUM_STOCK_STATUS_CODE(ws.getString(1));
				   		mobRptObject.setHSTSTR_STOCK_STATUS_DESC(ws.getString(2));				   						   		
				   		messageData.add(mobRptObject);
				    }
			    }
			    
				if(strWsMode.equals("2")) // Get Drug List
				{	  
					/*
			         * Mode Value
			         * 1.Get Store Combo
			         * 2.Get Store Hierarchy
			         * 3.Get List Page of Store Master
			         * 4.EMP Combo
			         * 5.Ward Name Combo Pass Dept Code
			         * 6.Dept Combo
			         * 7.Not Mapped Category
			         * 8.Not Mapped Request Type
			         * 9.Mapped Category
			         *10.Mapped Request Type
			         * */	
					
					System.out.println("---Get Drug List  ---");
					
					ws = dInventoryService.getInventoryDtls(hosp_code, store_Id, grp_id, item_id, brand_id, catcode, seat_Id, status_code, batch_no, "2"); 
					
					if (ws==null && ws.size() == 0) 
					{
			            return new ResponseEntity<List<DInventoryObject>>(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
					
				   	while(ws.next())
				    {
				   		DInventoryObject mobRptObject = new DInventoryObject();	
				   		mobRptObject.setHSTNUM_ITEMBRAND_ID(ws.getString(1));
				   		mobRptObject.setHSTSTR_ITEM_NAME(ws.getString(2));
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("3")) // Get BATCH COMBO  With Stock Dtls
				{	  
					/*
			         * Mode Value
			         * 1.Get Store Combo
			         * 2.Get Store Hierarchy
			         * 3.Get List Page of Store Master
			         * 4.EMP Combo
			         * 5.Ward Name Combo Pass Dept Code
			         * 6.Dept Combo
			         * 7.Not Mapped Category
			         * 8.Not Mapped Request Type
			         * 9.Mapped Category
			         *10.Mapped Request Type
			         * */		
					System.out.println("---Get BATCH Combo Wth Stock Dtls---");
					
					ws = dInventoryService.getInventoryDtls(hosp_code, store_Id, grp_id, item_id, brand_id, catcode, seat_Id, status_code, batch_no, "3"); 
					
					if (ws==null && ws.size() == 0) 
					{
			            return new ResponseEntity<List<DInventoryObject>>(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
					
				   	while(ws.next())
				    {
				   		DInventoryObject mobRptObject = new DInventoryObject();	
				   		/*
				   		  NVL(HSTSTR_BATCH_NO,''0'') 
						  		 || ''#'' ||SUM(ACTIVE_QTY) 
						  		 || ''#'' ||TO_CHAR(MAX(NVL(EXPIRY_DATE,TRUNC(SYSDATE))),''DD-Mon-YYYY'') 
						  		 || ''#'' ||NVL(TO_CHAR(MAX(MANF_DATE),''DD-Mon-YYYY''),'''')
				                 || ''#'' ||HSTNUM_RATE 
				                 || ''#'' ||HSTNUM_PO_NO
				                 || ''#'' ||HSTNUM_SUPPLIER_ID     
				                 || ''#'' ||HSTNUM_SUPPLIED_BY 
				                 || ''#'' ||NVL(HSTNUM_STOCK_STATUS_CODE,0)
				                 || ''#'' ||nvl(HSTNUM_administrative_CHARGES,0)
				                 || ''#'' ||nvl(HSTNUM_TAX,0)
				                 || ''#'' ||nvl(HSTNUM_PACK_SIZE,0)
				                 || ''#'' ||nvl(HSTNUM_NOOF_PACKS,0)
				         */				   	 
				   		mobRptObject.setSTOCK_DTL(ws.getString(1));
				   		mobRptObject.setHSTSTR_BATCH_NO(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("4")) // Get LIST Page 
				{	  
					/*
			         * Mode Value
			         ---------------------
			         * 1.Get Store Combo
			         * 2.Get Store Hierarchy
			         * 3.Get List Page of Store Master
			         * 4.EMP Combo
			         * 5.Ward Name Combo Pass Dept Code
			         * 6.Dept Combo
			         * 7.Not Mapped Category
			         * 8.Not Mapped Request Type
			         * 9.Mapped Category
			         *10.Mapped Request Type
			         * */	
					System.out.println("--- Get List Page ---");
					
					ws = dInventoryService.getInventoryDtls(hosp_code, store_Id, grp_id, item_id, brand_id, catcode, seat_Id, status_code, batch_no, "4"); 
					
					if (ws==null && ws.size() == 0) 
					{
			            return new ResponseEntity<List<DInventoryObject>>(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
					
				   	while(ws.next())
				    {
				   		DInventoryObject mobRptObject = new DInventoryObject();
				   		/*
				   		 C.HSTNUM_STORE_ID || @ || C.HSTNUM_ITEM_ID 
						 || @ || C.HSTNUM_ITEMBRAND_ID || @ || C.HSTSTR_BATCH_NO 
						 || @ || C.SSTNUM_ITEM_CAT_NO || @ || HSTNUM_STOCK_STATUS_CODE   
						 
						 C.ITEM_BRAND_NAME||'' ( ''||EDL_FLAG || '')'',  
						 C.EXPIRY_DATE , C.ITEM_INHAND_DTL , C.HSTSTR_BATCH_NO
						 */ 
				   	 
				   		mobRptObject.setP_KEY(ws.getString(1));
				   		mobRptObject.setHSTSTR_ITEM_NAME(ws.getString(2));	
				   		mobRptObject.setHSTDT_EXPIRY_DATE(ws.getString(3));
				   		mobRptObject.setHSTNUM_INHAND_QTY(ws.getString(4));
				   		mobRptObject.setHSTSTR_BATCH_NO(ws.getString(5));
				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
		//return messageData;
		return new ResponseEntity<List<DInventoryObject>>(messageData, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{strVar1}", method = RequestMethod.POST , consumes="application/json" , produces="application/json")
	@CrossOrigin(origins="*")
	public ResponseEntity<?> process(@PathVariable(value = "strVar1") String strStoreId,@RequestBody  DInventoryObject invntoryJsonObj) throws Exception 
	{
		String message ="";
		
		try
		{
			 System.out.println("------------------------- Store_Save_Update_Method Master DML Service ---------------------------");
	      		      	
		    invntoryJsonObj.setHSTNUM_STORE_ID(strStoreId);
	        
	      	message = dInventoryService.Inventory_Save_Method(invntoryJsonObj,strStoreId);  // Inventory Object
	      
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(message, HttpStatus.OK);
      	
	}	
	

}

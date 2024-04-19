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

import com.model.StoreMstObject;
import com.service.StoreMstService;

@RestController
@RequestMapping("/storeMstService")
public class StoreMstController 
{

	
	 
	@Autowired
	private StoreMstService storeMstService;
	
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	
	public  ResponseEntity<List<StoreMstObject>> getStoreMaster(@PathVariable("strVar1") String strVar1, @PathVariable("strVar2") String strVar2,@PathVariable("strVar3") String strVar3, @PathVariable("strMode") String strMode, @PathVariable("strWsMode") String strWsMode) 
	{
		ArrayList<StoreMstObject> messageData = new ArrayList<StoreMstObject>();
		WebRowSet ws =null;		
		try
		{			
			
			    System.out.println("strVar1---"+strVar1);
			    System.out.println("strVar2---"+strVar2);
			    System.out.println("strVar3---"+strVar3);
			    System.out.println("strWsMode---"+strWsMode);
			    //http://localhost:8085/storeMstService/0/0/0/1/1
			    //https://hmismangalagiri.uat.dcservices.in/ml_war/service/StoreMstService/0/0/0/1/1
			    //https://hmismangalagiri.uat.dcservices.in/storeMstService/0/0/13/1/9
			    System.out.println("http://localhost:8080/storeMstService/"+strVar1+"/"+strVar2+"/"+strVar3+"/"+strMode+"/"+strWsMode+"(strWsMode)");
			 
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
			    
			    System.out.println("---- pkg_inventory_ws_view.ws_store_mst ----- "
			    		+ "\n 1.Get Store Combo        "
			    		+ "\n 2.Get Store Hierarchy      "
			    		+ "\n 3.Get List Page of Store Master   "
			    		+ "\n 4.EMP Combo   "
			    		+ "\n 5.Ward Name Combo Pass Dept Code    "
			    		+ "\n 6.Dept Combo"
			    		+ "\n 7.Not Mapped Category"
			    		+ "\n 8.Not Mapped Request Type"
			    		+ "\n 9.Mapped Category"
			    		+ "\n10.Mapped Request Type ");
		         
			    String store_Id   = strVar1;
				String dept_Code  = strVar2;
				String type_Id    = strVar3;	
				String hosp_code  = strMode;	
				String seat_Id    = "0";
			    
			    if(strWsMode.equals("1")) // Get Store Combo
				{	  
						
			    	System.out.println("---Get Store Combo--["+strWsMode+"]-");
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"1"); 
										
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();	
				   		mobRptObject.setHSTNUM_STORE_ID(ws.getString(1));
				   		mobRptObject.setHSTSTR_STORE_NAME(ws.getString(2));				   						   		
				   		messageData.add(mobRptObject);
				    }
			    }
			    
				if(strWsMode.equals("3")) // Get Store List Page
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
					
					System.out.println("---Get Store List Page--["+strWsMode+"]-");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"3"); 
					
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();	
				   		mobRptObject.setHSTSTR_STORE_NAME(ws.getString(1));
				   		mobRptObject.setHIPSTR_WARD_NAME(ws.getString(2));					   		
				   		mobRptObject.setGSTR_DEPT_NAME(ws.getString(3));	
				   		mobRptObject.setSTR_EMP_FULL_NAME(ws.getString(4));	 // Emp Name
				   		mobRptObject.setHSTNUM_STORE_ID(ws.getString(5).split("\\@")[0]);	
				   		mobRptObject.setHSTNUM_PARENT_STORE_ID(ws.getString(5).split("\\@")[1]);	
				   		mobRptObject.setGNUM_HOSPITAL_CODE(ws.getString(5).split("\\@")[2]);   		
				   		
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("4")) // Get Employee Combo
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
					System.out.println("---Get Employee Combo--["+strWsMode+"]-");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"4"); 
					
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();						   	
				   	 
				   		mobRptObject.setSTR_EMP_FULL_NAME(ws.getString(1));
				   		mobRptObject.setHGSTR_EMP_CODE(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("5")) // Get Department Combo
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
					System.out.println("---Get Department Combo--["+strWsMode+"]-");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"6"); 
					
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();						   	
				   	 
				   		mobRptObject.setGNUM_DEPT_CODE(ws.getString(1));
				   		mobRptObject.setGSTR_DEPT_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("6")) // Get Ward Combo Based on Department
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
					System.out.println("---Get Ward Combo Based on Department--["+strWsMode+"]-");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"5"); 
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();						   	
				   	 
				   		mobRptObject.setGNUM_WARD_CODE(ws.getString(1));
				   		mobRptObject.setHIPSTR_WARD_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("7")) // Get Store type Combo
				{	  		
					System.out.println("---Get Store type Combo---");
								
					ws = storeMstService.getStoreType(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"1"); 
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();						   	
				   	 
				   		mobRptObject.setHSTNUM_STORETYPE_ID(ws.getString(1));
				   		mobRptObject.setHSTSTR_STORETYPE_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }	
				
				if(strWsMode.equals("8")) // Store Not Mapped Category 
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
					// http://localhost:8080/storeMstService/0/0/13/1/8 
					System.out.println("---Get Store Not Mapped Category---");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"7"); 
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();						   	
				   	 
				   		mobRptObject.setSSTNUM_ITEM_CAT_NO(ws.getString(1));
				   		mobRptObject.setSSTNUM_ITEM_CAT_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("9")) // Store Not Mapped Request Type
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
					//http://localhost:8080/storeMstService/0/0/13/1/9
					System.out.println("---Store Not Mapped Request Type---");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"8"); 
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();						   	
				   	 
				   		mobRptObject.setSSTNUM_INDENTTYPE_ID(ws.getString(1));
				   		mobRptObject.setSSTSTR_INDENTTYPE_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("10")) // Store  Mapped Category
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
					System.out.println("---Store  Mapped Category---");
					
					//http://localhost:8080/storeMstService/0/0/13/1/10
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"9"); 
					int i=0;
					String mappedCatg_Name = "";
					StoreMstObject mobRptObject = new StoreMstObject();
				   	while(ws.next())
				    {
				   		if(i==0)
				   		{
				   			mappedCatg_Name = ws.getString(2);
				   		}
				   		else
				   		{
				   			mappedCatg_Name = mappedCatg_Name + " <br> " +ws.getString(2);
				   		}   	
				   		i++;				   		
				    }
				   	mobRptObject.setMAPPED_ITEM_CAT_NAME(mappedCatg_Name); 
				   	messageData.add(mobRptObject);
			    }
				if(strWsMode.equals("11")) // Store Mapped Request Type
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
					System.out.println("---Store Mapped Request Type---");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"10"); 
					
					int i=0;
					String mappedRequest_Name = "";
					StoreMstObject mobRptObject = new StoreMstObject();
				   	while(ws.next())
				    {
				   		if(i==0)
				   		{
				   			mappedRequest_Name = ws.getString(2);
				   		}
				   		else
				   		{
				   			mappedRequest_Name = mappedRequest_Name + " <br> " +ws.getString(2);
				   		}   	
				   		i++;				   		
				    }
				   	mobRptObject.setMAPPED_INDENTTYPE_NAME(mappedRequest_Name); 
				   	messageData.add(mobRptObject);
			    }
				if(strWsMode.equals("12")) // Store Details
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
			         *11.Store Details
			         * */			
					
					 /*
		              1.Store Name
		              2.Store Type
		              3.Store Emp List
		              4.STORE_INDENT_TYPE
		              5.STORE_CATG
		              6.WARD_NAME
		              7.DEPT_NAME
		              8.STORE_ID
		         */
					
					System.out.println("---Store Details---");
					
					ws = storeMstService.getStoreList(store_Id,dept_Code,type_Id,hosp_code,seat_Id,"11"); 
										
					
				   	while(ws.next())
				    {
				   		StoreMstObject mobRptObject = new StoreMstObject();
				   		mobRptObject.setHSTSTR_STORE_NAME(ws.getString(1));
				   		mobRptObject.setHSTSTR_STORETYPE_NAME(ws.getString(2));
				   		mobRptObject.setSTR_EMP_FULL_NAME(ws.getString(3));
				   		mobRptObject.setSSTSTR_INDENTTYPE_NAME(ws.getString(4));
				   		mobRptObject.setSSTNUM_ITEM_CAT_NAME(ws.getString(5));			   		
				   		mobRptObject.setHIPSTR_WARD_NAME(ws.getString(6));
				   		mobRptObject.setGSTR_DEPT_NAME(ws.getString(7));
				   		mobRptObject.setHSTNUM_STORE_ID(ws.getString(8));
				   		
				   		messageData.add(mobRptObject);
				   					   		
				    }
				   	 
				   	
			    }
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
		//return messageData;
		return new ResponseEntity<List<StoreMstObject>>(messageData, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{strVar1}", method = RequestMethod.POST , consumes="application/json" , produces="application/json")
	@CrossOrigin(origins="*")
	public ResponseEntity<?> process(@PathVariable(value = "strVar1") String strStoreId,@RequestBody  StoreMstObject storeJsonObj) throws Exception 
	{
		String message ="";
		
		try
		{
			 System.out.println("------------------------- Store_Save_Update_Method Master DML Service ---------------------------");
	      		      	
		   
	        
	      	message = storeMstService.Store_Save_Update_Method(storeJsonObj);  // Supplier Object
	      
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(message, HttpStatus.OK);
      	
	}	
}

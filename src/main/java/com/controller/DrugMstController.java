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

import com.model.DrugMstObject;
import com.service.DrugMstService;

@RestController
@RequestMapping("/drugMstService")
public class DrugMstController 
{
	
	 
	@Autowired
	private DrugMstService drugService;
	
		
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<DrugMstObject>> getDrugs(@PathVariable(value = "strVar1") String strVar1, @PathVariable(value = "strVar2") String strVar2,@PathVariable(value = "strVar3") String strVar3, @PathVariable(value = "strMode") String strMode, @PathVariable(value = "strWsMode") String strWsMode) 
	{		
		ArrayList<DrugMstObject> messageData = new ArrayList<DrugMstObject>();
		WebRowSet ws =null;		
		try
		{			
			
			    System.out.println("strVar1---"+strVar1);
			    System.out.println("strVar2---"+strVar2);
			    System.out.println("strVar3---"+strVar3);
			    System.out.println("strWsMode---"+strWsMode);	
			    //http://localhost:8080/drugMstService/0/0/0/1/1
			    System.out.println("http://localhost:8080/drugMstService/"+strVar1+"/"+strVar2+"/"+strVar3+"/"+strMode+"/"+strWsMode+"(strWsMode)");
			    
				if(strWsMode.equals("1")) // Get Drug List Here
				{	  
					String item_id   = strVar1;
					String grp_id    = strVar2;
					String catg_code = strVar3;		
					
				
					
					ws = drugService.getGenDrugList(item_id,grp_id,catg_code,strMode); 
					
					//System.out.println("WS_SIZE---"+ws.size());
					
				   	while(ws.next())
				    {
				   		DrugMstObject mobRptObject = new DrugMstObject();		
				   					   	 
				   		mobRptObject.setHSTNUM_ITEM_ID(ws.getString(1));
				   		mobRptObject.setGNUM_HOSPITAL_CODE(ws.getString(2));	
				   		mobRptObject.setHSTNUM_SL_NO(ws.getString(3));	
				   		mobRptObject.setHSTSTR_ITEM_NAME(ws.getString(4));	
				   		mobRptObject.setGSTR_INVENTORY_UNITNAME(ws.getString(5));
				   		mobRptObject.setGNUM_INVENTORY_UNITID(ws.getString(6));		
				   		mobRptObject.setSSTNUM_ITEM_CAT_NO(ws.getString(7));		
				   		mobRptObject.setHSTNUM_GROUP_ID(ws.getString(8));		
				   		messageData.add(mobRptObject);
				    }
			    }
				
			
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		//returns the product list
		 return new ResponseEntity<List<DrugMstObject>>(messageData, HttpStatus.OK);
		 //return messageData;
	}
		
	@RequestMapping(value="/{strVar1}", method=RequestMethod.DELETE)
	@CrossOrigin(origins="*")
	public ResponseEntity<?> deleteEmployees(@PathVariable(value = "strVar1") String strItemId)
	{
		
	
		String message ="";
		try 
	    {
			
			message = drugService.Delete_Drug(strItemId);
	    }
		catch (Exception e) 
	    {
	      	  e.printStackTrace();   
	      	  return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
	          
	    }
	     
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	     
	}
	@RequestMapping(value = "/{strVar1}", method = RequestMethod.POST , consumes="application/json" , produces="application/json")
	@CrossOrigin(origins="*")
	public ResponseEntity<?> process(@PathVariable(value = "strVar1") String strCatgCode,@RequestBody DrugMstObject[] drugArray) throws Exception 
	{
		String message ="",drugDtl="";
	
		try
		{
			
			System.out.println("------------------------- Group Master DML Service ---------------------------");
	      	System.out.println("Catg_Code -->>"+strCatgCode);
	           	
	      	for(DrugMstObject drugObj:drugArray)
	      	{
	      		System.out.println("getGNUM_HOSPITAL_CODE--->>>"+drugObj.getGNUM_HOSPITAL_CODE());
	      		System.out.println("getGNUM_INVENTORY_UNITID--->>>"+drugObj.getGNUM_INVENTORY_UNITID());
	      		System.out.println("getHstnum_group_id--->>>"+drugObj.getHSTNUM_GROUP_ID());
	      		System.out.println("getHSTSTR_ITEM_NAME--->>>"+drugObj.getHSTSTR_ITEM_NAME());
	      		System.out.println("getSstnum_item_cat_no--->>>"+drugObj.getSSTNUM_ITEM_CAT_NO());	
	      		System.out.println("getSstnum_item_id--->>>"+drugObj.getHSTNUM_ITEM_ID());	
            
	            drugDtl = drugObj.getHSTSTR_ITEM_NAME()+"^"+(drugObj.getHSTNUM_ITEM_ID() == null ? "0" :drugObj.getHSTNUM_ITEM_ID())+"^"+drugObj.getHSTNUM_GROUP_ID()+"^"+drugObj.getGNUM_INVENTORY_UNITID();   
  
	      	}
	      
	      	 message = drugService.Generic_Drug_Group_Save_Update_Method(drugDtl+"^"+strCatgCode); // genericDrugName ^ itemId ^ groupId ^ unitId ^ catg_Code 
	      
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(message, HttpStatus.OK);
      	
	}	

}


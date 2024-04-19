package com.controller;

import java.util.ArrayList;

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

import com.model.SupplierMstObject;
import com.service.CountryMstService;
import com.service.SupplierMstService;

@RestController
@RequestMapping("/supplierMstService")
public class SupplierMstController 
{
	
	 
	@Autowired
	private SupplierMstService supplierService;
	@Autowired
	private CountryMstService countryService;
	
	
		
	
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")	
	public  ArrayList<SupplierMstObject> getDrugList(@PathVariable("strVar1") String strVar1, @PathVariable("strVar2") String strVar2,@PathVariable("strVar3") String strVar3, @PathVariable("strMode") String strMode, @PathVariable("strWsMode") String strWsMode) 
	{
		ArrayList<SupplierMstObject> messageData = new ArrayList<SupplierMstObject>();
		WebRowSet ws =null;		
		try
		{			
			
			    System.out.println("strVar1---"+strVar1);
			    System.out.println("strVar2---"+strVar2);
			    System.out.println("strVar3---"+strVar3);
			    System.out.println("strWsMode---"+strWsMode);
			    //http://localhost:8085/ml_war/service/supplierMstService/0/0/0/1/1
			    //https://hmismangalagiri.uat.dcservices.in/ml_war/service/supplierMstService/0/0/0/1/1
			    System.out.println("http://localhost:8085/supplierMstService/"+strVar1+"/"+strVar2+"/"+strVar3+"/"+strMode+"/"+strWsMode+"(strWsMode)");
			 
			    if(strWsMode.equals("1")) // Get Supplier List For Master
				{	  
					String supplier_Id  = strVar1;
					String catg_Code    = strVar2;
					String status_Id    = strVar3;	
					
					ws = supplierService.getSupplierList(supplier_Id,catg_Code,status_Id,strMode); 
					
					
				   	while(ws.next())
				    {
				   		SupplierMstObject mobRptObject = new SupplierMstObject();	
				   		mobRptObject.setHSTNUM_SUPPLIER_ID(ws.getString(1));
				   		mobRptObject.setHSTSTR_SUPPLIER_NAME(ws.getString(2));
				   		mobRptObject.setHststr_supplier_status_name(ws.getString(3));					   		
				   		messageData.add(mobRptObject);
				    }
			    }
			    
				if(strWsMode.equals("2")) // Get Supplier Status
				{	  
					String supplier_Id  = strVar1;
					String catg_Code    = strVar2;
					String status_Id    = strVar3;	
					
					ws = supplierService.getSupplierStatus(supplier_Id,catg_Code,status_Id,strMode); 
					
					
				   	while(ws.next())
				    {
				   		SupplierMstObject mobRptObject = new SupplierMstObject();	
				   		mobRptObject.setHSTNUM_SUPPLIER_STATUS(ws.getString(1));
				   		mobRptObject.setHststr_supplier_status_name(ws.getString(2));	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("3")) // Get Supplier Type
				{	  
					String supplier_Id  = strVar1;
					String catg_Code    = strVar2;
					String status_Id    = strVar3;	
					
					ws = supplierService.getSupplierType(supplier_Id,catg_Code,status_Id,strMode); 
					
				   	while(ws.next())
				    {
				   		SupplierMstObject mobRptObject = new SupplierMstObject();						   	
				   	 
				   		mobRptObject.setHSTNUM_SUPPLIERTYPE_ID(ws.getString(1));
				   		mobRptObject.setHSTSTR_SUPPLIERTYPE_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("4")) // Get Supplier Grade
				{	  
					String supplier_Id  = strVar1;
					String catg_Code    = strVar2;
					String status_Id    = strVar3;	
					
					ws = supplierService.getSupplierGrade(supplier_Id,catg_Code,status_Id,strMode); 
					
				   	while(ws.next())
				    {
				   		SupplierMstObject mobRptObject = new SupplierMstObject();						   	
				   	 
				   		mobRptObject.setHSTNUM_SUPPLIER_GRADE_ID(ws.getString(1));
				   		mobRptObject.setHSTSTR_GRADE_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("5")) // Get Country List
				{	  
										
					ws = countryService.getCountryList("0","0","0",strMode); 
					
				   	while(ws.next())
				    {
				   		SupplierMstObject mobRptObject = new SupplierMstObject();						   	
				   	 
				   		mobRptObject.setGNUM_COUNTRYCODE(ws.getString(1));
				   		mobRptObject.setGSTR_COUNTRY_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(strWsMode.equals("6")) // Get State List
				{	  
					String country_Id  = strVar1;
					
					ws = countryService.getStateList(country_Id,"0","0",strMode); 
					
				   	while(ws.next())
				    {
				   		SupplierMstObject mobRptObject = new SupplierMstObject();						   	
				   	 
				   		mobRptObject.setGNUM_STATECODE(ws.getString(1));
				   		mobRptObject.setGSTR_STATE_NAME(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				
				
				if(strWsMode.equals("7")) // Get Supplier Details
				{	  
					String supplier_Id  = strVar1;
					String catg_Code    = strVar2;
					String status_Id    = strVar3;	
					
					//http://localhost:8080/supplierMstService/1010005/10/0/1/7
					
					ws = supplierService.getSupplierList(supplier_Id,catg_Code,status_Id,"2"); 
					/*
					1.Supplier Name
					2.Supplier Grade
					3.Supplier Type
					4.Country Name
					5.State Name
					6.CITY_PIN_CODE
					7.Address
					8.E_Mail
					9.Phone No
					10.HSTSTR_PAN_NO
					11.Supplier_Id
					*/
					
				   	while(ws.next())
				    {
				   		SupplierMstObject mobRptObject = new SupplierMstObject();	
				   		
				   		mobRptObject.setHSTSTR_SUPPLIER_NAME(ws.getString(1));
				   		mobRptObject.setHSTSTR_GRADE_NAME(ws.getString(2));
				   		mobRptObject.setHSTSTR_SUPPLIERTYPE_NAME(ws.getString(3));					   		
				   		mobRptObject.setGSTR_COUNTRY_NAME(ws.getString(4));		
				   		mobRptObject.setGSTR_STATE_NAME(ws.getString(5));		
				   		mobRptObject.setHSTSTR_CITY_NAME(ws.getString(6));
				   		mobRptObject.setHSTSTR_ADDRESS(ws.getString(7));				   		
				   		mobRptObject.setHSTSTR_EMAIL1(ws.getString(8));
				   		mobRptObject.setHSTSTR_ADDRESS(ws.getString(9));
				   		mobRptObject.setHSTSTR_PAN_NO(ws.getString(10));
				   		mobRptObject.setHSTNUM_SUPPLIER_ID(ws.getString(11));
				   		
				   		messageData.add(mobRptObject);
				    }
			    }
				
				
				
				
			
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
		return messageData;
	}
	
	
	@RequestMapping(value = "/{strVar1}", method = RequestMethod.POST , consumes="application/json" , produces="application/json")
	@CrossOrigin(origins="*")
	public ResponseEntity<?> process(@PathVariable(value = "strVar1") String strCatgCode,@RequestBody SupplierMstObject supplierJsonObj) throws Exception 
	{
		String message ="";
		
		try
		{
						
	      	System.out.println("Catg_Code -->>"+strCatgCode);	      
	      	message = supplierService.Supplier_Save_Update_Method(supplierJsonObj);  // Supplier Object
	      
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(message, HttpStatus.OK);
      	
	}	
	

}

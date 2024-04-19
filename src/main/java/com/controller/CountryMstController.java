package com.controller;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.CountryMstObject;
import com.service.CountryMstService;

@RestController
@RequestMapping("/country")
public class CountryMstController 
{
	@Autowired
	private CountryMstService countryService;
	
	@RequestMapping(value="/{wsmode}/{countrycode}/{statecode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public  ArrayList<CountryMstObject> getDrugList(@PathVariable("wsmode") String wsmode,@PathVariable("countrycode") String countrycode, @PathVariable("statecode") String statecode) 
	{
		ArrayList<CountryMstObject> messageData = new ArrayList<CountryMstObject>();
		WebRowSet ws =null;		
		try
		{			
			 	System.out.println("wsmode---"+wsmode);
			    System.out.println("countrycode---"+countrycode);
			    System.out.println("statecode---"+statecode);
			   
				//10.226.28.236:8084/PWA_SERVICE/country/1/0/0 to get country list
				//10.226.28.236:8084/PWA_SERVICE/country/2/0/0 to get state list
				//10.226.28.236:8084/PWA_SERVICE/country/3/0/9 to get district list  
 			
				if(wsmode.equals("1")) // Get Country List
				{	  
										
					ws = countryService.getCountryList("0","0","0","0"); 
					
				   	while(ws.next())
				    {
				   		CountryMstObject mobRptObject = new CountryMstObject();						   	
				   	 
				   		mobRptObject.setCountry_code(ws.getString(1));
				   		mobRptObject.setCountry_name(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(wsmode.equals("2")) // Get State List
				{	  
					String country_Id  = countrycode;
					
					ws = countryService.getStateList(country_Id,"0","0","0"); 
					
				   	while(ws.next())
				    {
				   		CountryMstObject mobRptObject = new CountryMstObject();						   	
				   	 
				   		mobRptObject.setState_code(ws.getString(1));
				   		mobRptObject.setState_name(ws.getString(2));				   		
				   	
				   		messageData.add(mobRptObject);
				    }
			    }
				
				if(wsmode.equals("3")) // Get District List
				{	  
					String state_Id  = statecode;
					
					ws = countryService.getDistrictList(state_Id,"0","0","0"); 
					
				   	while(ws.next())
				    {
				   		CountryMstObject mobRptObject = new CountryMstObject();						   	
				   	 
				   		mobRptObject.setDistrict_code(ws.getString(1));
				   		mobRptObject.setDistrict_name(ws.getString(2));				   		
				   	
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
}

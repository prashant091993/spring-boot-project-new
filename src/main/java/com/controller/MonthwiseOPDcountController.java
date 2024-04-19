package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.MonthwiseOPDcountObject;
import com.service.MonthwiseOPDcountService;

@RestController
@RequestMapping("/dashboard/monthwiseopdcount")
public class MonthwiseOPDcountController 
{
	@Autowired
	private MonthwiseOPDcountService monthwiseopdcountservice;
	
	@RequestMapping(value="/{wsmode}/{procmode}/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<MonthwiseOPDcountObject>> getmalefemalecount(@PathVariable(value = "wsmode") String wsmode,@PathVariable(value = "procmode") String procmode, @PathVariable(value = "hospcode") String hospcode) 
	{
		ArrayList<MonthwiseOPDcountObject> messageData = new ArrayList<MonthwiseOPDcountObject>();
		WebRowSet ws =null;	
		try 
		{
			System.out.println("wsmode---"+wsmode);
			System.out.println("procmode---"+procmode);
			System.out.println("hospcode---"+hospcode);
		    if(wsmode.equals("1")) //to get monthwise patient details male/female count
		    {
		    	
				ws = monthwiseopdcountservice.getmalefemalecount(procmode,hospcode);
				if (ws==null && ws.size() == 0) 
				{
		            return new ResponseEntity<List<MonthwiseOPDcountObject>>(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }					
			   	while(ws.next())
			    {
			   		MonthwiseOPDcountObject mobRptObject = new MonthwiseOPDcountObject();		
			   		mobRptObject.setMonth(ws.getString(1));
			   		mobRptObject.setMale(ws.getString(2));
			   		mobRptObject.setFemale(ws.getString(3));			   						   		
			   		messageData.add(mobRptObject);
			    }
		    }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	    return new ResponseEntity<List<MonthwiseOPDcountObject>>(messageData, HttpStatus.OK);
	}

}

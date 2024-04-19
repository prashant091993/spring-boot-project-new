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

import com.model.MonthwisePatCountObject;
import com.service.MonthwisePatCountService;

@RestController
@RequestMapping("/dashboard/monthwisepatcount")
public class MonthwisePatCountController
{
	@Autowired
	private MonthwisePatCountService monthwisepatcountservice;
	
	@RequestMapping(value="/{wsmode}/{procmode}/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<MonthwisePatCountObject>> getmonthwisepatcount(@PathVariable(value = "wsmode") String wsmode,@PathVariable(value = "procmode") String procmode, @PathVariable(value = "hospcode") String hospcode) 
	{
		ArrayList<MonthwisePatCountObject> messageData = new ArrayList<MonthwisePatCountObject>();
		WebRowSet ws =null;	
		try 
		{
			System.out.println("wsmode---"+wsmode);
			System.out.println("procmode---"+procmode);
			System.out.println("hospcode---"+hospcode);
		    if(wsmode.equals("1")) 
		    {
				ws = monthwisepatcountservice.getmonthwisepatcount(procmode,hospcode);
				if (ws==null && ws.size() == 0) 
				{
		            return new ResponseEntity<List<MonthwisePatCountObject>>(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }					
			   	while(ws.next())
			    {
			   		if(procmode.equals("1"))//to get monthwise admitted patient details count
			   		{
			   			MonthwisePatCountObject mobRptObject = new MonthwisePatCountObject();		
				   		mobRptObject.setMonth(ws.getString(1));
				   		mobRptObject.setNoofadmissions(ws.getString(2));
				   					   						   		
				   		messageData.add(mobRptObject);
			   		}
			    }
		    }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	    return new ResponseEntity<List<MonthwisePatCountObject>>(messageData, HttpStatus.OK);
	}


}

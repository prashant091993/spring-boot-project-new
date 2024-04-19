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

import com.model.AvgStayCountObject;
import com.service.AvgStayCountService;

@RestController
@RequestMapping("/dashboard/avgstaycount")
public class AvgStayCountController
{
	@Autowired
	private AvgStayCountService avgstaycountservice;
	
	@RequestMapping(value="/{wsmode}/{procmode}/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<AvgStayCountObject>> getavgstaycount(@PathVariable(value = "wsmode") String wsmode,@PathVariable(value = "procmode") String procmode, @PathVariable(value = "hospcode") String hospcode) 
	{
		ArrayList<AvgStayCountObject> messageData = new ArrayList<AvgStayCountObject>();
		WebRowSet ws =null;	
		try 
		{
			System.out.println("wsmode---"+wsmode);
			System.out.println("procmode---"+procmode);
			System.out.println("hospcode---"+hospcode);
		    if(wsmode.equals("1")) 
		    {
				ws = avgstaycountservice.getavgstaycount(procmode,hospcode);
				if (ws==null && ws.size() == 0) 
				{
		            return new ResponseEntity<List<AvgStayCountObject>>(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }					
			   	while(ws.next())
			    {
			   		
			   		if(procmode.equals("3"))//to get Average length of stay for last 10 days.
			   		{
			   			AvgStayCountObject mobRptObject = new AvgStayCountObject();		
				   		mobRptObject.setMonth(ws.getString(1));
				   		mobRptObject.setAvglengthofstay(ws.getString(2));
				   					   						   		
				   		messageData.add(mobRptObject);
			   		}
			   		
			    }
		    }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	    return new ResponseEntity<List<AvgStayCountObject>>(messageData, HttpStatus.OK);
	}

	
}

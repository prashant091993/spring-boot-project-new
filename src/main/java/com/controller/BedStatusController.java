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

import com.model.BedStatusObject;
import com.service.BedStatusService;

@RestController
@RequestMapping("/dashboard/bedstatus")
public class BedStatusController 
{
	@Autowired
	private BedStatusService bedstatusservice;
	
	@RequestMapping(value="/{wsmode}/{procmode}/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<BedStatusObject>> getbedstatus(@PathVariable(value = "wsmode") String wsmode,@PathVariable(value = "procmode") String procmode, @PathVariable(value = "hospcode") String hospcode) 
	{
		ArrayList<BedStatusObject> messageData = new ArrayList<BedStatusObject>();
		WebRowSet ws =null;	
		try 
		{
			System.out.println("wsmode---"+wsmode);
			System.out.println("procmode---"+procmode);
			System.out.println("hospcode---"+hospcode);
		    if(wsmode.equals("1")) 
		    {
				ws = bedstatusservice.getbedstatus(procmode,hospcode);
				if (ws==null && ws.size() == 0) 
				{
		            return new ResponseEntity<List<BedStatusObject>>(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }					
			   	while(ws.next())
			    {
			   		if(procmode.equals("4"))//to get current bed status.
			   		{
			   			BedStatusObject mobRptObject = new BedStatusObject();		
				   		mobRptObject.setWardname(ws.getString(1));
				   		mobRptObject.setTotalbed(ws.getString(2));
				   		mobRptObject.setOccupiedbed(ws.getString(3));
				   		mobRptObject.setVacantbed(ws.getString(4));
				   					   						   		
				   		messageData.add(mobRptObject);
			   		}
			    }
		    }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	    return new ResponseEntity<List<BedStatusObject>>(messageData, HttpStatus.OK);
	}


}

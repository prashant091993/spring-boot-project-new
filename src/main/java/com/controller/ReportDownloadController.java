package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.ReportDownloadService;

@RestController
@RequestMapping("/reportDownload")
@CrossOrigin
public class ReportDownloadController 
{
	@Autowired
	private ReportDownloadService reportService;
	
	@RequestMapping(value = "/{strCrNo}", method = RequestMethod.GET )		
	public ResponseEntity<?> process(@PathVariable(value = "strCrNo") String strCrNo) throws Exception 
	{
		String message ="";
		
		try
		{		
			//http://localhost:8080/reportDownload/379132200005976
	
			System.out.println("------------------------- Group Master DML Service ---------------------------");
	      	System.out.println("strCrNo -->>"+strCrNo);
	        
	      	
	      	 message = reportService.getList(strCrNo);
	      	
	      
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(message, HttpStatus.OK);
  	
    }	

	
}

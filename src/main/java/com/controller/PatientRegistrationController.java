package com.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.PatientRegistrationObject;
import com.service.PatientRegistrationService;

@RestController
@RequestMapping("/patregistration")
public class PatientRegistrationController
{
	@Autowired
	private PatientRegistrationService patientRegistrationService;
	
	@RequestMapping(value="/{wsmode}", method=RequestMethod.POST, consumes="application/json" , produces="application/json")
	@CrossOrigin(origins="*")
	public ResponseEntity<?> getcrno(@PathVariable(value = "wsmode") String wsmode,@RequestBody PatientRegistrationObject patientregistrationJsonObj) throws Exception 
	{
		String message ="";	
		try
		{		
			System.out.println("wsmode -->>"+wsmode);
			if(wsmode.equals("1")) 
			{	  				
				message = patientRegistrationService.getcrno(patientregistrationJsonObj); 
		    }				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	}
}

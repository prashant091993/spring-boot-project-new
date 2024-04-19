package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.LabReportListObject;
import com.model.LabReportPDFObject;
import com.service.LabReportListService;
import com.service.LabReportPDFService;

@RestController
@RequestMapping("/labreportpdf")
public class LabReportPDFController 
{
	@Autowired
	private LabReportPDFService labReportPDFService;
	
	@RequestMapping(value="/{crno}/{hospcode}/{reqdno}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<LabReportPDFObject> getProduct(@PathVariable(value = "crno") String crno,@PathVariable(value = "hospcode") String hospcode,@PathVariable(value = "reqdno") String reqdno) 
	{
		System.out.println("crno---"+crno);
		System.out.println("hospcode---"+hospcode);
		System.out.println("reqdno---"+reqdno);
	    	
		//finds all the lab reports list
		List<LabReportPDFObject> labreportpdf = labReportPDFService.getpdf(crno,hospcode,reqdno);
		//returns the lab report list
		return labreportpdf;
	}

}

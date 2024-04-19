package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.model.LabReportListObject;
import com.service.LabReportListService;

@RestController
@RequestMapping("/labreportlist")
public class LabReportListController 
{
	@Autowired
	private LabReportListService labReportListService;
	
	@RequestMapping(value="/{crno}/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<LabReportListObject> getProduct(@PathVariable(value = "crno") String crno,@PathVariable(value = "hospcode") String hospcode) 
	{
		System.out.println("crno---"+crno);
		System.out.println("hospcode---"+hospcode);
	    	
		//finds all the lab reports list
		List<LabReportListObject> labreportlist = labReportListService.getList(crno,hospcode);
		//returns the lab report list
		return labreportlist;
	}
}

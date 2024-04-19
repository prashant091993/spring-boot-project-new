package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.OPDEnquiryObject;
import com.service.OPDEnquiryService;

@RestController
@RequestMapping("/opdenquiry")
public class OPDEnquiryController
{
	@Autowired
	private OPDEnquiryService opdenquiryservice;
	
	@RequestMapping(value="/{deptcode}/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<OPDEnquiryObject> getProduct(@PathVariable(value = "deptcode") String deptcode, @PathVariable(value = "hospcode") String hospcode) 
	{
		System.out.println("deptcode---"+deptcode);
		System.out.println("hospcode---"+hospcode);
	    	
		//finds all the tariffs
		List<OPDEnquiryObject> opd = opdenquiryservice.getList(deptcode,hospcode);
		//returns the tariff list
		return opd;
	}

}

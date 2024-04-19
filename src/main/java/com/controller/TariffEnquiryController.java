package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.TariffEnquiryObject;
import com.service.TariffEnquiryService;

@RestController
@RequestMapping("/tariffenquiry")
public class TariffEnquiryController 
{
	@Autowired
	private TariffEnquiryService tariffenquiryservice;
	
	@RequestMapping(value="/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<TariffEnquiryObject> getProduct(@PathVariable(value = "hospcode") String hospcode) 
	{
		System.out.println("hospcode---"+hospcode);
	    	
		//finds all the tariffs
		List<TariffEnquiryObject> tariff = tariffenquiryservice.getList(hospcode);
		//returns the tariff list
		return tariff;
	}

}

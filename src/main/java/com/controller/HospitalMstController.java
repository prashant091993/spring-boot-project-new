package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.HospitalMstObject;
import com.service.HospitalMstService;

@RestController
@RequestMapping("/hospitallist")
public class HospitalMstController 
{
	@Autowired 
	private HospitalMstService hospitalmstservice;

	@RequestMapping(value="/{hospcode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<HospitalMstObject> getHospitalList(@PathVariable(value = "hospcode") String hospcode) 
	{
		System.out.println("hospcode---"+hospcode);
	    	
		//finds all the hospitals
		List<HospitalMstObject> hospitallist = hospitalmstservice.getList(hospcode);
		//returns the hospitals list
		return hospitallist;
	}
}

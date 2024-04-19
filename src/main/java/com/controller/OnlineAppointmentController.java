package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.OnlineAppointmentObject;
import com.service.OnlineAppointmentService;

@RestController
@RequestMapping("/getDeptUnitList")
public class OnlineAppointmentController 
{
	@Autowired
	private OnlineAppointmentService onlineAppointmentService;
	
	@RequestMapping(value="/{hospcode}/{crno}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<OnlineAppointmentObject> getDeptUnitList(@PathVariable(value = "hospcode") String hospcode,@PathVariable(value = "crno") String crno) 
	{
		System.out.println("hospcode---"+hospcode);
		System.out.println("crno---"+crno);
	    	
		//finds all the department
		List<OnlineAppointmentObject> getDeptUnitList = onlineAppointmentService.getList(hospcode,crno);
		//returns the department unit list
		return getDeptUnitList;
	}

}

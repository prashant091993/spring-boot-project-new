package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.OnlineAppointmentSlotObject;
import com.service.OnlineAppointmentSlotService;

@RestController
@RequestMapping("/getappointmentslots")
public class OnlineAppointmentSlotController
{
	@Autowired
	private OnlineAppointmentSlotService onlineAppointmentSlotService;
	
	@RequestMapping(value="/{hospcode}/{deptunitcode}/{apptdate}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<OnlineAppointmentSlotObject> getappointmentslots(@PathVariable(value = "hospcode") String hospcode,@PathVariable(value = "deptunitcode") String deptunitcode,@PathVariable(value = "apptdate") String apptdate) 
	{
		System.out.println("hospcode---"+hospcode);
		System.out.println("deptunitcode---"+deptunitcode);
		System.out.println("apptdate---"+apptdate);
	    	
		//finds all the appointment slots
		List<OnlineAppointmentSlotObject> getappointmentslots = onlineAppointmentSlotService.getSlots(hospcode,deptunitcode,apptdate);
		//returns the appointment slots
		return getappointmentslots;
	}

}

package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.MonthwisePatCountObject;
import com.model.MultiplePatientsLoginObject;
import com.model.PatientLoginObject;
import com.service.PatientLoginService;

@RestController
@RequestMapping("/patlogin")
public class PatientLoginController 
{
	@Autowired
	private PatientLoginService patientLoginService;
	
	@RequestMapping(value="/{wsmode}/{hospcode}/{mobileno}/{smsflag}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public String verifypatientmobileno(@PathVariable(value = "wsmode") String wsmode,@PathVariable(value = "hospcode") String hospcode, @PathVariable(value = "mobileno") String mobileno, @PathVariable(value = "smsflag") String smsflag) 
	{
		ArrayList<PatientLoginObject> messageData = new ArrayList<PatientLoginObject>();
		WebRowSet ws =null;	
		JSONObject jsonObject = null;
		try 
		{
			System.out.println("wsmode---"+wsmode);
			System.out.println("hospcode---"+hospcode);
			System.out.println("mobileno---"+mobileno);
			System.out.println("smsflag---"+smsflag);
		    if(wsmode.equals("1")) 
		    {
		    	MultiplePatientsLoginObject multiplePatientsLoginObject  = patientLoginService.verifypatientmobileno(hospcode,mobileno,smsflag);
			   	try 
			   	{
					JSONArray jsonArray = new JSONArray();
					for (int i=0;i<multiplePatientsLoginObject.getPatientLoginObjects().size();i++)
					{
						JSONObject jObj = new JSONObject();
						PatientLoginObject obj = multiplePatientsLoginObject.getPatientLoginObjects().get(i);
						//HelperMethods.setNullToEmpty(obj);
						jObj.put("crno", obj.getCrNo());
						
						jObj.put("mobileNo", obj.getMobileNo());
						jObj.put("firstName", obj.getFname());
						jObj.put("lastName", obj.getLname());

						jObj.put("age", obj.getAge());
						jObj.put("gender", obj.getGender());
						jObj.put("fatherName", obj.getFatherName());
						jObj.put("motherName", obj.getMotherName());
						jObj.put("spouseName", obj.getSpouseName());
						jObj.put("departmentId", obj.getDepartmentId());
						jObj.put("tempRegNo", obj.getTempCrNo());
						
						jObj.put("stateId", obj.getStateId());
						jObj.put("districtId", obj.getDistrictId());
						jObj.put("hospCode", obj.getHospCode());
						jObj.put("patCategory", obj.getPatCategory());
						jObj.put("city", obj.getCity());
						jObj.put("pincode", obj.getPincode());
						jObj.put("email", obj.getEmailAddress());
						jObj.put("date", obj.getDate());
						jObj.put("hospCode", obj.getHospCode());
						jObj.put("ndhmHealthId",obj.getNdhmId());
						jObj.put("ndhmPatHealthId", obj.getNdhmPatHealthId());
						
						jsonArray.put(i, jObj);
					}
					jsonObject= new JSONObject();		
					jsonObject.put("OTP",multiplePatientsLoginObject.getOTP());
					jsonObject.put("patientdetails", jsonArray);
				}
			   	catch (JSONException e)
			   	{
					e.printStackTrace();
					
				}
		    }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	    return jsonObject.toString();
	}
	
	

}

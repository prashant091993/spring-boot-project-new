package com.model;

import java.util.ArrayList;

public class MultiplePatientsLoginObject 
{
	private ArrayList<PatientLoginObject> patientLoginObjects;
	private String OTP;
	
	public ArrayList<PatientLoginObject> getPatientLoginObjects() {
		return patientLoginObjects;
	}
	public void setPatientLoginObjects(ArrayList<PatientLoginObject> patientLoginObjects) {
		this.patientLoginObjects = patientLoginObjects;
	}
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	
	

}

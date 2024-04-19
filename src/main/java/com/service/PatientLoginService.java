package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.MultiplePatientsLoginObject;
import com.model.PatientLoginObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class PatientLoginService {

	public MultiplePatientsLoginObject verifypatientmobileno(String hospcode, String mobileno, String smsflag) {
		// TODO Auto-generated method stub
		MultiplePatientsLoginObject pMultiplePatientsLoginObject = null;
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;	
		ArrayList<PatientLoginObject> pArrayList = new ArrayList<PatientLoginObject>();
		
		try 
		{
	        System.out.println("------------------ PatientLoginService.verifypatientmobileno ----[pkg_pwa_mst.proc_patient_login]----------------");
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
	        			       
	        
	        strProcName = "{call pkg_pwa_mst.proc_patient_login(?,?,?,?,?,?)}";				       
	        nprocIndex = dao.setProcedure(strProcName);				
			dao.setProcInValue(nprocIndex, "modeval", 		    "1");						
			dao.setProcInValue(nprocIndex, "hosp_code",	        hospcode);	
			dao.setProcInValue(nprocIndex, "mobileno",	        mobileno);	
			dao.setProcInValue(nprocIndex, "smsflag",	        smsflag);	
			dao.setProcOutValue(nprocIndex, "err",			     1); 
			dao.setProcOutValue(nprocIndex, "resultset", 	     2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null) 
			{
				strerr = "";
			}
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			while(ws.next())
		    {
				PatientLoginObject mobRptObject = new PatientLoginObject();		
		   		mobRptObject.setFname(ws.getString(5));
		   		mobRptObject.setLname(ws.getString(8));
				mobRptObject.setAge(ws.getString(36));
				mobRptObject.setGender(ws.getString(9));
				mobRptObject.setFatherName(ws.getString(14));
				mobRptObject.setMotherName(ws.getString(15));
				mobRptObject.setSpouseName(ws.getString(38));
				mobRptObject.setStateId(ws.getString(70));
				mobRptObject.setDistrictId(ws.getString(71));
				mobRptObject.setCity(ws.getString(58));
				mobRptObject.setMobileNo(ws.getString(67));
				mobRptObject.setEmailAddress(ws.getString(69));
				mobRptObject.setPincode(ws.getString(59));
				mobRptObject.setHospCode(ws.getString(47));
				mobRptObject.setPatCategory(ws.getString(19));
				mobRptObject.setDate(ws.getString(22));//17
				mobRptObject.setCrNo(ws.getString(1));
				mobRptObject.setNdhmId(ws.getString(94));
				mobRptObject.setNdhmPatHealthId(ws.getString(95));
		   					   						   		
				pArrayList.add(mobRptObject);
		    }
			if (strerr.equals("")) 
			{
								 
	
			} 
			else 
			{
				throw new Exception(strerr);
			}
			String OTP = "";
			if(smsflag.equals("1")) 
			{
				OTP = RandomStringUtils.random(6,"123456789");
			}
			System.out.println("OTP............."+OTP);	
			pMultiplePatientsLoginObject = new MultiplePatientsLoginObject();
			pMultiplePatientsLoginObject.setPatientLoginObjects(pArrayList);
			pMultiplePatientsLoginObject.setOTP(OTP);	

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return pMultiplePatientsLoginObject;
	}

}

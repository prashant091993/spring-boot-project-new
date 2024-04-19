package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.PatientRegistrationObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class PatientRegistrationService 
{
	public String getcrno(PatientRegistrationObject mobRptObject) 
	{
		int 		nprocIndex=0;
		HisDAO 		dao = null;
		String 		result="";
		String      strerr ="";
		String strProcName = "";
		String crno = "";
		WebRowSet ws =null;
		try 
		{
			String pat_reg_cat_code = "";
			String seatid = "10001";
			String uhid = mobRptObject.getPatname()+mobRptObject.getPatgender();
			if (mobRptObject.getIsgatewayavailable().contentEquals("1")) 
			{
				if (mobRptObject.getIspaymentdone().contentEquals("1"))
				{
					pat_reg_cat_code="26";// MOBILE APP CR GENERATION PAID
				} 
				else if (mobRptObject.getIspaymentdone().contentEquals("0"))
				{
					pat_reg_cat_code="27";;// MOBILE APP CR GENERATION UNPAID
				}
			} 
			else if (mobRptObject.getIsgatewayavailable().contentEquals("0")) 
			{
				pat_reg_cat_code="28";// MOBILE APP CR GENERATION NOPAYMENT REQ
			}
	        System.out.println("------------------ PatientRegistrationService.getcrno ----[pkg_webservices.generate_crno_for_generic - 1]----------------");
	        
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");				       
	        
	        strProcName = "{call pkg_webservices.generate_crno_for_generic(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"; 		
	        
	        nprocIndex = dao.setProcedure(strProcName);				
	        dao.setProcInValueByPosition(nprocIndex, "i_hospital_code", mobRptObject.getHospcode(),1);
            dao.setProcInValueByPosition(nprocIndex, "p_gstr_gender_code", mobRptObject.getPatgender(),2);
            dao.setProcInValueByPosition(nprocIndex, "p_hrgstr_father_name", mobRptObject.getPatfathername(),3);
            dao.setProcInValueByPosition(nprocIndex, "p_hrgstr_spousename", "",4);
            dao.setProcInValueByPosition(nprocIndex, "p_age", mobRptObject.getPatage(),5);
            dao.setProcInValueByPosition(nprocIndex, "p_gstr_country_code", mobRptObject.getCountrycode(),6);
            dao.setProcInValueByPosition(nprocIndex, "p_gnum_state_code", mobRptObject.getStatecode(),7);
            dao.setProcInValueByPosition(nprocIndex, "p_gnum_district_code", mobRptObject.getDistrictcode(),8);
            dao.setProcInValueByPosition(nprocIndex, "p_hrgstr_address_line1", mobRptObject.getPataddress(),9);
            dao.setProcInValueByPosition(nprocIndex, "p_hrgstr_mobile_no", mobRptObject.getPatmobileno(),10);
            dao.setProcInValueByPosition(nprocIndex, "p_email_id", mobRptObject.getPatemailid(),11);
            dao.setProcInValueByPosition(nprocIndex, "p_pat_name", mobRptObject.getPatname(),12);
            dao.setProcInValueByPosition(nprocIndex, "p_reg_cat_code", pat_reg_cat_code,13);
            dao.setProcInValueByPosition(nprocIndex, "p_seat_id", seatid,14);
            dao.setProcInValueByPosition(nprocIndex, "p_hrgstr_sec_uhid", uhid,15);
          
            dao.setProcOutValue(nprocIndex, "err",1,16);
            dao.setProcOutValue(nprocIndex, "resultset",2,17);
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null) 
			{
				strerr = "";
			}
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) 
			{
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				if (ws != null && ws.size() > 0) 
				{
		              while (ws.next()) 
		              {
		      			 crno = ws.getString(1);
		              }
		          }
				result = mobRptObject.setPatcrno(crno);
			} 
			else 
			{
				throw new Exception(strerr);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
		return result;
	}
}

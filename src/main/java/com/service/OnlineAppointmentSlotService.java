package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.OnlineAppointmentSlotObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class OnlineAppointmentSlotService 
{
	public ArrayList<OnlineAppointmentSlotObject> getSlots(String hospcode, String deptunitcode, String apptdate)
	{
			
        ArrayList<OnlineAppointmentSlotObject> messageData = new ArrayList<OnlineAppointmentSlotObject>();
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		try 
		{
	        System.out.println("------------------ OnlineAppointmentSlotService.getSlots ----[pkg_pwa_mst.getonlineappointmentfreeslots]----------------");
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
	        			       
	        
	        strProcName = "{call pkg_pwa_mst.getonlineappointmentfreeslots(?,?,?,?,?,?,?,?,?,?,?,?,?)}";				       
	        nprocIndex = dao.setProcedure(strProcName);				
			dao.setProcInValue(nprocIndex, "modeval", 		    "2");	
			dao.setProcInValue(nprocIndex, "hosp_code",         hospcode);
			dao.setProcInValue(nprocIndex, "aptdate",	        apptdate);
			dao.setProcInValue(nprocIndex, "aptfor", 			"1");
            dao.setProcInValue(nprocIndex, "para_id1", 			deptunitcode);
            dao.setProcInValue(nprocIndex, "para_id2", 			"0");
            dao.setProcInValue(nprocIndex, "para_id3", 			"0");
            dao.setProcInValue(nprocIndex, "para_id4", 			"0");
            dao.setProcInValue(nprocIndex, "para_id5", 			"0");
            dao.setProcInValue(nprocIndex, "para_id6", 			"0");
            dao.setProcInValue(nprocIndex, "para_id7", 			"0");
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
				OnlineAppointmentSlotObject mobRptObject = new OnlineAppointmentSlotObject();					   	 
				
				  mobRptObject.setDate(ws.getString(1));
				  mobRptObject.setShiftcode(ws.getString(2));
				  mobRptObject.setShiftname(ws.getString(3));
				  mobRptObject.setShiftstart(ws.getString(4));
				  mobRptObject.setShiftend(ws.getString(5));
				  mobRptObject.setSlotstart(ws.getString(6));
				  mobRptObject.setSlotend(ws.getString(7));
				  mobRptObject.setSlotduration(ws.getString(8));
				  mobRptObject.setFreeslotdetail(ws.getString(9));
				  mobRptObject.setSlotstatus(ws.getString(10));
				  mobRptObject.setOpdslots(ws.getString(11));
				  mobRptObject.setIpdslots(ws.getString(12));
				  mobRptObject.setOpdbookedslots(ws.getString(13));
				  mobRptObject.setIpdbookedslots(ws.getString(14));
				  				 
		   		messageData.add(mobRptObject);
		    }
			if (strerr.equals("")) 
			{
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
		return messageData;
	}
}

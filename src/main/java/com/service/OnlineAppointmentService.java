package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.OnlineAppointmentObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class OnlineAppointmentService 
{
	public ArrayList<OnlineAppointmentObject> getList(String hospcode, String crno)
	{
			
        ArrayList<OnlineAppointmentObject> messageData = new ArrayList<OnlineAppointmentObject>();
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		try 
		{
	        System.out.println("------------------ OnlineAppointmentService.getList ----[pkg_pwa_mst.getonlineappointmentwithcr]----------------");
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
	        			       
	        
	        strProcName = "{call pkg_pwa_mst.getonlineappointmentwithcr(?,?,?,?,?)}";				       
	        nprocIndex = dao.setProcedure(strProcName);				
			dao.setProcInValue(nprocIndex, "modeval", 		    "1");	
			dao.setProcInValue(nprocIndex,  "crno",              crno);
			dao.setProcInValue(nprocIndex, "hosp_code",	        hospcode);	
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
				OnlineAppointmentObject mobRptObject = new OnlineAppointmentObject();					   	 
		   		mobRptObject.setDepartmentunitcode(ws.getString(1));
		   		mobRptObject.setLocationcode(ws.getString(2));
		   		mobRptObject.setLocationname(ws.getString(3));
		   		mobRptObject.setDepartmentname(ws.getString(4));
		   		mobRptObject.setWorkingdays(ws.getString(5));
		   		mobRptObject.setNewpatportallimit(ws.getString(6));
		   		mobRptObject.setOldpatportallimit(ws.getString(7));
		   		mobRptObject.setLoweragelimit(ws.getString(8));
		   		mobRptObject.setMaxagelimit(ws.getString(9));
		   		mobRptObject.setBoundgendercode(ws.getString(10));
		   		mobRptObject.setIsrefer(ws.getString(11));
		   		mobRptObject.setActualParameterReferenceId(ws.getString(12));
		   		mobRptObject.setIs_telecons_unit(ws.getString(13));
		   		mobRptObject.setUnit_type(ws.getString(14));
		   		mobRptObject.setUnit_type_code(ws.getString(15));
		   		mobRptObject.setTariff_id(ws.getString(16));
		   		mobRptObject.setCharge(ws.getString(17));
		   		mobRptObject.setHosp_code(ws.getString(18));
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

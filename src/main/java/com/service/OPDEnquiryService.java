package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.OPDEnquiryObject;
import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class OPDEnquiryService 
{
	public ArrayList<OPDEnquiryObject> getList(String deptcode,String hospcode)
	{			
        ArrayList<OPDEnquiryObject> messageData = new ArrayList<OPDEnquiryObject>();
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
	        System.out.println("------------------ OPDEnquiryService.getList ----[pkg_pwa_mst.proc_opdenquiry_list]----------------");
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");					       
	        
	        strProcName = "{call pkg_pwa_mst.proc_opdenquiry_list(?,?,?,?)}";				       
	        nprocIndex = dao.setProcedure(strProcName);				
			dao.setProcInValue(nprocIndex, "modeval", 		    "1");						
			dao.setProcInValue(nprocIndex, "hosp_code",	        deptcode);
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
				OPDEnquiryObject mobRptObject = new OPDEnquiryObject();		
		   					   	 
		   		mobRptObject.setDeptCode(ws.getString(1));
		   		mobRptObject.setDeptName(ws.getString(2));
		   		mobRptObject.setDeptUnitCode(ws.getString(3));
		   		mobRptObject.setUnitName(ws.getString(4));
		   		mobRptObject.setRoomCode(ws.getString(5));
		   		mobRptObject.setRoomName(ws.getString(6));
		   		mobRptObject.setUnitDays(ws.getString(7));
		   		mobRptObject.setOpdName(ws.getString(8));
		   		mobRptObject.setRosterType(ws.getString(9));
		   		mobRptObject.setLocation(ws.getString(10));
		   		mobRptObject.setMobileno(ws.getString(11));
		   		mobRptObject.setConsultantId(ws.getString(12));
		   				   						
		   		messageData.add(mobRptObject);
		    }
			if (strerr.equals("")) 
			{
				System.out.println("No Error Found");				 
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

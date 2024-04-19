package com.service;

import java.util.ArrayList;
import javax.sql.rowset.WebRowSet;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.model.LabReportListObject;
import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class LabReportListService 
{
	public ArrayList<LabReportListObject> getList(String crno,String hospcode)
	{
        ArrayList<LabReportListObject> messageData = new ArrayList<LabReportListObject>();
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		try 
		{
	        System.out.println("------------------ LabReportListService.getList ----[pkg_pwa_mst.proc_labreport_list]----------------");
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
	        			       
	        strProcName = "{call pkg_pwa_mst.proc_labreport_list(?,?,?,?,?)}";				       
	        nprocIndex = dao.setProcedure(strProcName);				
			dao.setProcInValue(nprocIndex, "modeval", 		    "1");						
			dao.setProcInValue(nprocIndex, "hosp_code",	        hospcode);	
			dao.setProcInValue(nprocIndex, "crno",	       		crno);		
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
				LabReportListObject mobRptObject = new LabReportListObject();			   					   	 
		   		mobRptObject.setTestname(ws.getString(1));
		   		mobRptObject.setReqdno(ws.getString(2));
		   		mobRptObject.setReportdate(ws.getString(3));						
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

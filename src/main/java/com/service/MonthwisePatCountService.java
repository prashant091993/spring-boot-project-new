package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.MonthwisePatCountObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class MonthwisePatCountService 
{
	public WebRowSet getmonthwisepatcount(String procmode,String hospcode)
	{			
        ArrayList<MonthwisePatCountObject> messageData = new ArrayList<MonthwisePatCountObject>();
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
	        System.out.println("------------------ DashboardMstService.getList ----[pkg_mob_app_reports.get_monthwise_admission_statistic]----------------");
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");					       
	        
	        strProcName = "{call pkg_mob_app_reports.get_monthwise_admission_statistic(?,?,?,?)}";				       
	        nprocIndex = dao.setProcedure(strProcName);				
			dao.setProcInValue(nprocIndex, "modeval", 		    procmode);						
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
		return ws;
	}

}

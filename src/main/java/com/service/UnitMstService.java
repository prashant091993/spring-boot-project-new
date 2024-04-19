package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class UnitMstService 
{
	
	public WebRowSet getUnitList(String strVar1,String strVar2,String strVar3,String strMode) 
	{

		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			        System.out.println("------------------ DrugMstDAO. getGenDrugList ----[pkg_inventory_ws_view.ws_unit_mst ]----------------");
			        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
			         
			        strProcName = "{call pkg_inventory_ws_view.ws_unit_mst(?,?,?,?,?   ,?)}"; // 6			        
			       
				    nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		     strMode);				
					dao.setProcInValue(nprocIndex, "hosp_code",		     "100");			
					dao.setProcInValue(nprocIndex, "unit_id",	         "0");
					dao.setProcInValue(nprocIndex, "module_id",	         "63");	 // Inventroy Module Id	
					dao.setProcOutValue(nprocIndex, "err",			     1);
					dao.setProcOutValue(nprocIndex, "resultset", 	     2);
					dao.executeProcedure(nprocIndex);
					strerr = dao.getString(nprocIndex, "err");
					if (strerr == null) {
						strerr = "";
					}
					ws = dao.getWebRowSet(nprocIndex, "resultset");
				
				
				if (strerr.equals("")) 
				{
									 

				} 
				else 
				{
					throw new Exception(strerr);
				}
		

		} catch (Exception e) {
			
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
		return ws;

		
	}

}

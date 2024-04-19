package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class CountryMstService 
{
	
	
	public  WebRowSet getCountryList(String strVar1,String strVar2,String strVar3,String strMode) 
	{

		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			        System.out.println("------------------ CountryStateMstDAO. getCountryList ----[pkg_inventory_ws_view.ws_country_list - 1]----------------");
			        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
			        			       
			        
			        strProcName = "{call pkg_inventory_ws_view.ws_country_list(?,?,?)}"; // 3				       
			        nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    "1");	
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
	public  WebRowSet getStateList(String country_Id,String strVar2,String strVar3,String strMode) 
	{

		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			        System.out.println("------------------ CountryStateMstDAO. getStateList ----[pkg_inventory_ws_view.ws_state_list - 1]----------------");
			        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
			        			       
			        
			        strProcName = "{call pkg_inventory_ws_view.ws_state_list(?,?,?,?)}"; // 4				       
			        nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    "1");				
					dao.setProcInValue(nprocIndex, "country_code",		country_Id);	
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
	
	public  WebRowSet getDistrictList(String state_Id,String strVar2,String strVar3,String strMode) 
	{

		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			        System.out.println("------------------ CountryStateMstDAO. getDistrictList ----[pkg_inventory_ws_view.ws_district_list - 1]----------------");
			        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
			        			       
			        
			        strProcName = "{call pkg_inventory_ws_view.ws_district_list(?,?,?,?,?)}"; // 4				       
			        nprocIndex = dao.setProcedure(strProcName);				
					dao.setProcInValue(nprocIndex, "modeval", 		    "1");				
					dao.setProcInValue(nprocIndex, "country_code",		"0");
					dao.setProcInValue(nprocIndex, "state_code",		state_Id);
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

package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.TariffEnquiryObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class TariffEnquiryService 
{
	public ArrayList<TariffEnquiryObject> getList(String hospcode)
	{
			
		        ArrayList<TariffEnquiryObject> messageData = new ArrayList<TariffEnquiryObject>();
				HisDAO dao = null;
				WebRowSet ws =null;
				String strProcName = "",strerr="";
				int nprocIndex = 0;		
				
				try 
				{
					        System.out.println("------------------ TariffEnqiryService.getList ----[pkg_pwa_mst.proc_tariff_list]----------------");
					        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
					        			       
					        
					        strProcName = "{call pkg_pwa_mst.proc_tariff_list(?,?,?,?)}";				       
					        nprocIndex = dao.setProcedure(strProcName);				
							dao.setProcInValue(nprocIndex, "modeval", 		    "1");						
							dao.setProcInValue(nprocIndex, "hosp_code",	        hospcode);											
							dao.setProcOutValue(nprocIndex, "err",			     1); 
							dao.setProcOutValue(nprocIndex, "resultset", 	     2);
							dao.executeProcedure(nprocIndex);
							strerr = dao.getString(nprocIndex, "err");
							if (strerr == null) {
								strerr = "";
							}
							ws = dao.getWebRowSet(nprocIndex, "resultset");
							
							while(ws.next())
						    {
								TariffEnquiryObject mobRptObject = new TariffEnquiryObject();		
						   					   	 
						   		mobRptObject.setPatCategory(ws.getString(1));
						   		mobRptObject.setChargeType(ws.getString(2));
						   		mobRptObject.setTariffName(ws.getString(3));
						   		mobRptObject.setTariffServiceID(ws.getString(4));
						   		mobRptObject.setTariffCharge(ws.getString(5));
						   				   		
						   								
						   		messageData.add(mobRptObject);
						    }
							
							


						
						
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

		      
		    	      				
		
		return messageData;

	}
}

package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.HospitalMstObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class HospitalMstService 
{
	public ArrayList<HospitalMstObject> getList(String hospcode)
	{
			
		        ArrayList<HospitalMstObject> messageData = new ArrayList<HospitalMstObject>();
				HisDAO dao = null;
				WebRowSet ws =null;
				String strProcName = "",strerr="";
				int nprocIndex = 0;		
				
				try 
				{
					        System.out.println("------------------ HospitalMstService.getList ----[pkg_pwa_mst.proc_hospital_list]----------------");
					        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
					        			       
					        
					        strProcName = "{call pkg_pwa_mst.proc_hospital_list(?,?,?,?)}";				       
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
								HospitalMstObject mobRptObject = new HospitalMstObject();		
						   					   	 
						   		mobRptObject.setHospcode(ws.getString(1));
						   		mobRptObject.setHospname(ws.getString(2));
						   							   				   		
						   								
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

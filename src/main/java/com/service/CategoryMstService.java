package com.service;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.CategoryMstObject;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class CategoryMstService 
{
    
	public ArrayList<CategoryMstObject> findAll()
	{
			
		        ArrayList<CategoryMstObject> messageData = new ArrayList<CategoryMstObject>();
				HisDAO dao = null;
				WebRowSet ws =null;
				String strProcName = "",strerr="";
				int nprocIndex = 0;		
				
				try 
				{
					        System.out.println("------------------ CategoryMstDAO. getCategoryList ----[pkg_inventory_ws_view.proc_item_category_list - 3]----------------");
					        dao = new HisDAO("MMS", "DrugInventoryTransDAO");		
					        			       
					        
					        strProcName = "{call pkg_inventory_ws_view.proc_item_category_list(?,?,?,?,?   ,?)}"; // 6				       
					        nprocIndex = dao.setProcedure(strProcName);				
							dao.setProcInValue(nprocIndex, "modeval", 		    "3");				
							dao.setProcInValue(nprocIndex, "store_id",		   "100");			
							dao.setProcInValue(nprocIndex, "hosp_code",	        "10");
							dao.setProcInValue(nprocIndex, "reqtype",	         "0");				
											
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
						   		CategoryMstObject mobRptObject = new CategoryMstObject();		
						   					   	 
						   		mobRptObject.setSSTNUM_ITEM_CAT_NO(ws.getString(1));
						   		mobRptObject.setSSTNUM_ITEM_CAT_NAME(ws.getString(2));				   		
						   				   		
						   								
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


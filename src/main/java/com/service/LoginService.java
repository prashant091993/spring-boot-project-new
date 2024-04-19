package com.service;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import global.InvTransactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class LoginService 
{
	public WebRowSet userLoginDtls(String p_user_name,String p_hospital_code,String p_user_id,String p_pwd,String p_mode) 
	{

		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;		
		
		try 
		{
			        System.out.println("------------------ LoginService. userLoginDtls ----[pkg_inventory_ws_view.proc_gblt_user_mst - "+p_mode+"]----------------");
			        dao = new HisDAO("MMS", "LoginService");				        
			        strProcName = "{call pkg_inventory_ws_view.proc_gblt_user_mst(?,?,?,?,?   ,?,?,?,?,?  )}"; // 10		
			        
			        nprocIndex = dao.setProcedure(strProcName);				
			        dao.setProcInValue(nprocIndex, "p_mode", 			p_mode);
					dao.setProcInValue(nprocIndex, "p_hospital_code", 	(p_hospital_code==null) ? "0":p_hospital_code);
					dao.setProcInValue(nprocIndex, "p_user_id", 		(p_user_id==null) ? "0":p_user_id);
					dao.setProcInValue(nprocIndex, "p_user_name", 		(p_user_name==null) ? "0":p_user_name);
					dao.setProcInValue(nprocIndex, "p_seat_id", 		"0");
					dao.setProcInValue(nprocIndex, "p_emp_no", 			"0");
					dao.setProcInValue(nprocIndex, "p_question_id", 	"0");
					dao.setProcInValue(nprocIndex, "p_hint_answer", 	"0");

					dao.setProcOutValue(nprocIndex, "err", 				1); // VarChar
					dao.setProcOutValue(nprocIndex, "resultset", 		2); // Cursor					
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


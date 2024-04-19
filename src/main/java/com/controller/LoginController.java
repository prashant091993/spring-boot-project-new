package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.LoginObject;
import com.service.LoginService;

import global.security.SecureHashAlgorithm;

@RestController
@RequestMapping("/loginAction")
public class LoginController 
{

	@Autowired
	private LoginService loginService;
	
		
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<LoginObject>> getProduct(@PathVariable(value = "strVar1") String strVar1, @PathVariable(value = "strVar2") String strVar2,@PathVariable(value = "strVar3") String strVar3, @PathVariable(value = "strMode") String strMode, @PathVariable(value = "strWsMode") String strWsMode) 
	{
		
		
		ArrayList<LoginObject> messageData = new ArrayList<LoginObject>();
		WebRowSet ws =null;		
		String dB_pwd   = "";
		
		try 
		{
				  if(strWsMode.equals("1")) // Get User PWD Details
				  {	  
						
					    String p_user_name     	= strVar1;
					    String p_hospital_code  = strVar2;
					    String p_user_id     	= strVar3;
					    String p_pwd        	= strMode;
					    
					    String p_is_lock        ="0";
					    
							
					    //http://localhost:8080/loginAction/admin_aiimsm/0/0/123456/1
						ws = loginService.userLoginDtls(p_user_name,p_hospital_code,p_user_id,p_pwd,"1");
						
						if (ws==null && ws.size() == 0) 
						{
				            return new ResponseEntity<List<LoginObject>>(HttpStatus.NO_CONTENT);
				            // You many decide to return HttpStatus.NOT_FOUND
				        }						
					   	
						if(ws.next())
						{
							LoginObject mobRptObject = new LoginObject();		
							
							mobRptObject.setGNUM_USERID(ws.getString(1));
							mobRptObject.setGSTR_USER_NAME(ws.getString(2));
							mobRptObject.setGSTR_PASSWORD(ws.getString(3));
							mobRptObject.setGNUM_USER_SEATID(ws.getString(4));
							mobRptObject.setPSRSTR_EMP_NO(ws.getString(5));
							mobRptObject.setGNUM_HOSPITAL_CODE(ws.getString(6));								
							mobRptObject.setGNUM_USERLEVEL(ws.getString(7));
							mobRptObject.setGSTR_USR_NAME(ws.getString(8));  // Full User Name								
							mobRptObject.setGNUM_DESIGNATION(ws.getString(9)); 
							mobRptObject.setGNUM_MOBILE_NUMBER(ws.getString(10)); 
							mobRptObject.setGSTR_EMAIL_ID(ws.getString(11)); 	
							mobRptObject.setGNUM_ISLOCK(ws.getString(14)); 							
							p_is_lock = ws.getString(14);	
							dB_pwd    = ws.getString(3);
							
							try
							{												
							   if(p_is_lock.equals("0"))	
							   {	
									String strHashedPassword = SecureHashAlgorithm.SHA1(p_pwd+p_user_name);						
									if (dB_pwd.equals(strHashedPassword))
									{
										System.out.println("Password Match  !!");	
										mobRptObject.setLOGIN_MSG("Password Match  !!");
										mobRptObject.setLOGIN_FLG("1");
									}
									else
									{
										System.out.println("Password Not Match  !!");
										mobRptObject.setLOGIN_MSG("Password Not Match  !!");
										mobRptObject.setLOGIN_FLG("2");
									}
							   }
							   else
							   {
								   System.out.println("User [ "+p_user_name+" ]  Is Lock  !!");		
								   mobRptObject.setLOGIN_MSG("User [ "+p_user_name+" ]  Is Lock  !!");
								   mobRptObject.setLOGIN_FLG("3");
							   }
							}
							catch (Exception e) 
							{
									// TODO Auto-generated catch block
									e.printStackTrace();
							}	
							
							messageData.add(mobRptObject);
							
						}						
						
						
				 }
		}
		catch (Exception e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}		
				
		return new ResponseEntity<List<LoginObject>>(messageData, HttpStatus.OK);
    }
}

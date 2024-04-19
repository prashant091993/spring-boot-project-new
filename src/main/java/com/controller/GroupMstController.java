package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.GroupMstObject;
import com.service.GroupMstService;

@RestController
@RequestMapping("/groupService")
public class GroupMstController 
{
	 
	@Autowired
	private GroupMstService groupService;
	
		
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<GroupMstObject>> getProduct(@PathVariable(value = "strVar1") String strVar1, @PathVariable(value = "strVar2") String strVar2,@PathVariable(value = "strVar3") String strVar3, @PathVariable(value = "strMode") String strMode, @PathVariable(value = "strWsMode") String strWsMode) 
	{
		
	    System.out.println("strWsMode---"+strWsMode);
	    //http://localhost:8085/PWA_SERVICE/category/0/10/10/1/1
	    
	    ArrayList<GroupMstObject> messageData = new ArrayList<GroupMstObject>();
		WebRowSet ws =null;	
		try
		{
	    
		    if(strWsMode.equals("1")) // Used to Get Group List
			{	    	
			    
		    	
		    	
		    	System.out.println("http://localhost:8080/groupService/"+strVar1+"/"+strVar2+"/"+strVar3+"/"+strMode+"/"+strWsMode+"(strWsMode)");
				String hosp_code  = strVar1;
				String catg_Code  = strVar2;
				String grp_id     = strVar3;	
				System.out.println("strVar1-[hosp_code]--"+strVar1);
			    System.out.println("strVar2-[catg_Code]--"+strVar2);
			    System.out.println("strVar3-[grp_id]--"+strVar3);
			    
			   
				ws =  groupService.findAllGroup(hosp_code,catg_Code,grp_id,strMode); 
				
				//System.out.println("WS_SIZE---"+ws.size());
				
				if (ws==null && ws.size() == 0) 
				{
		            return new ResponseEntity<List<GroupMstObject>>(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }
		       
				
			   	while(ws.next())
			    {
			   		GroupMstObject mobRptObject = new GroupMstObject();		
			   					   	 
			   		mobRptObject.setHSTNUM_GROUP_ID(ws.getString(1));
			   		mobRptObject.setHSTSTR_GROUP_NAME(ws.getString(2));					
			   				   		
			   								
			   		messageData.add(mobRptObject);
			    }
			   	
			   
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		//returns the product list
		 return new ResponseEntity<List<GroupMstObject>>(messageData, HttpStatus.OK);
		 //return messageData;
	}
		
	@RequestMapping(value="/{strVar1}", method=RequestMethod.DELETE)
	@CrossOrigin(origins="*")
	public ResponseEntity<?> deleteEmployees(@PathVariable(value = "strVar1") String strGroupId)
	{
		
		
		String message ="";
		try 
	    {
			
			message = groupService.Delete_Group(strGroupId);
	    }
		catch (Exception e) 
	    {
	      	  e.printStackTrace();   
	      	  return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
	          
	    }
	     
		return new ResponseEntity<Object>(message, HttpStatus.OK);
	     
	}
	@RequestMapping(value = "/{strVar1}", method = RequestMethod.POST , consumes="application/json" , produces="application/json")
	@CrossOrigin(origins="*")
	public ResponseEntity<?> process(@PathVariable(value = "strVar1") String strCatgCode,@RequestBody GroupMstObject[] grpArray) throws Exception 
	{
		String message ="",drugDtl="";
		/*
		 * http://localhost:8080/groupService/10
		 * [{"hststr_GROUP_NAME":"Adda-Change","hstnum_GROUP_ID":"101010"}
			,{"hststr_GROUP_NAME":"Twelve","hstnum_GROUP_ID":"101011"}]
		 */
		
		try
		{
			

			System.out.println("------------------------- Group Master DML Service ---------------------------");
	      	System.out.println("Catg_Code -->>"+strCatgCode);
	           	
	      	for(GroupMstObject grpObj:grpArray)
	      	{
	      		System.out.println("HSTNUM_GROUP_ID--->>>"+grpObj.getHSTNUM_GROUP_ID());
	      		System.out.println("HSTSTR_GROUP_NAME--->>>"+grpObj.getHSTSTR_GROUP_NAME());
	      	
	      		drugDtl = grpObj.getHSTSTR_GROUP_NAME()+"^"+(grpObj.getHSTNUM_GROUP_ID() == null ? "0" :grpObj.getHSTNUM_GROUP_ID());    
	      	}
	      
	      	message = groupService.Drug_Group_Save_Update_Method(drugDtl+"^"+strCatgCode);  // Grp_Name ^ Grp_Id ^ Catg_Code
	      
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(message, HttpStatus.OK);
      	
	}	

}


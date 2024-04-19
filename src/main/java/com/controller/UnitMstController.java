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

import com.model.UnitMstObject;
import com.service.UnitMstService;

@RestController
@RequestMapping("/unitMstService")

public class UnitMstController 
{
	
	
	@Autowired
	private UnitMstService unitMstService;
	
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public ResponseEntity<List<UnitMstObject>> getUnitList(@PathVariable("strVar1") String strVar1, @PathVariable("strVar2") String strVar2,@PathVariable("strVar3") String strVar3, @PathVariable("strMode") String strMode, @PathVariable("strWsMode") String strWsMode) 
	{
		ArrayList<UnitMstObject> messageData = new ArrayList<UnitMstObject>();
		WebRowSet ws =null;		
		try
		{			
			
			    System.out.println("strVar1---"+strVar1);
			    System.out.println("strVar2---"+strVar2);
			    System.out.println("strVar3---"+strVar3);
			    System.out.println("strWsMode---"+strWsMode);
			    //http://localhost:8085/ml_war/service/unitMstService/0/0/0/2/1  [ 2- For UnitId,Unit Name , 1- GNUM_UNIT_ID^BILL_MST.getUnitBaseValue(GNUM_UNIT_ID,A.GNUM_HOSPITAL_CODE)^NVL(GNUM_DECIMAL_ALLOWED,0)]
			    System.out.println("http://localhost:8085/ml_war/service/unitMstService/"+strVar1+"/"+strVar2+"/"+strVar3+"/"+strMode+"/"+strWsMode+"(strWsMode)");
			 
			    
				if(strWsMode.equals("1")) // Used to Get PO Drug List
				{	  
					String hosp_code  = strVar1;
					String unit_id   = strVar2;
					String module_id = strVar3;					
					ws = unitMstService.getUnitList(hosp_code,unit_id,module_id,strMode); 
					
					//System.out.println("WS_SIZE---"+ws.size());
					
					if (ws==null && ws.size() == 0) 
					{
			            return new ResponseEntity<List<UnitMstObject>>(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
					
				   	while(ws.next())
				    {
				   		UnitMstObject mobRptObject = new UnitMstObject();		
				   					   	 
				   		mobRptObject.setGNUM_UNIT_ID(ws.getString(1));
				   		mobRptObject.setGSTR_UNIT_NAME(ws.getString(2));				   		
				   				   		
				   								
				   		messageData.add(mobRptObject);
				    }
			    }
				
			
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		 return new ResponseEntity<List<UnitMstObject>>(messageData, HttpStatus.OK);
		//return messageData;
	}
	

}

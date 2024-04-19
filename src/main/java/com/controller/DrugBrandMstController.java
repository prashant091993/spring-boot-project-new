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

import com.model.DrugBrandMstObject;
import com.service.DrugBrandMstService;



@RestController
@RequestMapping("/drugBrandService")
public class DrugBrandMstController 
{
	
	 
	@Autowired
	private DrugBrandMstService durgBrandService;
	
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public  ResponseEntity<List<DrugBrandMstObject>> getDrugList(@PathVariable("strVar1") String strVar1, @PathVariable("strVar2") String strVar2,@PathVariable("strVar3") String strVar3, @PathVariable("strMode") String strMode, @PathVariable("strWsMode") String strWsMode) 
	{
		ArrayList<DrugBrandMstObject> messageData = new ArrayList<DrugBrandMstObject>();
		WebRowSet ws =null;		
		try
		{			
			
			    System.out.println("strVar1---"+strVar1);
			    System.out.println("strVar2---"+strVar2);
			    System.out.println("strVar3---"+strVar3);
			    System.out.println("strWsMode---"+strWsMode);
			    //http://localhost:8085/drugBrandService/0/0/0/1/1
			    System.out.println("http://localhost:8085/drugBrandService/"+strVar1+"/"+strVar2+"/"+strVar3+"/"+strMode+"/"+strWsMode+"(strWsMode)");
			 
			    
				if(strWsMode.equals("1")) // Used to Get PO Drug List
				{	  
					String brand_Id  = strVar1;
					String edf_Flg   = strVar2;
					String catg_Code = strVar3;	
					String grp_id    = strMode;	
					
					
					ws = durgBrandService.getDrugBrandList(brand_Id,edf_Flg,catg_Code,grp_id);
					
					if (ws==null && ws.size() == 0) 
					{
			            return new ResponseEntity<List<DrugBrandMstObject>>(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
					
					//System.out.println("WS_SIZE---"+ws.size());
					
				   	while(ws.next())
				    {
				   		DrugBrandMstObject mobRptObject = new DrugBrandMstObject();		
				   		
				   		mobRptObject.setHSTSTR_ITEMBRAND_NAME(ws.getString(1));
				   		mobRptObject.setHSTSTR_ITEM_NAME(ws.getString(2));					   		
				   		mobRptObject.setHSTSTR_SUPPLIER_NAME(ws.getString(3));
				   		mobRptObject.setHSTSTR_ITEMTYPE_NAME(ws.getString(4));
				   		mobRptObject.setHSTSTR_GRP_NAME(ws.getString(5));				   		
				   		mobRptObject.setHSTNUM_ITEMBRAND_ID(ws.getString(6));
				   		mobRptObject.setHSTNUM_ITEM_ID(ws.getString(7));
				   		mobRptObject.setGNUM_HOSPITAL_CODE(ws.getString(8));
				   		mobRptObject.setSSTNUM_ITEM_CAT_NO(ws.getString(9));
				   		mobRptObject.setHSTNUM_ITEMTYPE_ID(ws.getString(25));
				   		mobRptObject.setHSTNUM_GRP_ID(ws.getString(31));
				   		mobRptObject.setHSTNUM_EDL_CATEGORY(ws.getString(37));				   		
				   		
				   	/*	1.ITEM_NAME         , 2.GENERIC_NAME
				   	 *  3.SUPPLIER_NAME     , 4.Item_Type
				   	 *  5.Group Name  
				   	6	hstnum_itembrand_id ,		hstnum_item_id ,
					8	gnum_hospital_code ,		sstnum_item_cat_no ,	
				   10	hstnum_manufacturer_id ,	hstnum_default_rate ,
					12	hstnum_rate_unit_id ,		hstnum_approved_type ,
					14	hstnum_issue_type ,		hststr_specification ,
					16	hstnum_item_make ,		gstr_remarks ,	
					18	hstnum_currpo_no ,		gdt_entry_date ,
					20	gnum_seatid ,			gnum_isvalid ,
					22	hstnum_currpur_rate_unitid ,	hstnum_currsupplier_id ,	
					24	hstnum_set_sachet_flag ,	hstnum_itemtype_id ,
					26	hstnum_is_quantified ,		hststr_cpa_code ,	
					28	hstnum_inventory_unitid ,	hstnum_issuerate_value ,
					30	hstnum_qc_type ,		hstnum_grp_id ,
					32	hstnum_subgrp_id ,		hstnum_classification_id ,
					34	hstnum_batchno_req ,		hstnum_expirydate_req ,
					36	hstnum_hsn_code ,		hstnum_edl_category ,
					38	hstnum_is_misc ,		NVL(hstsr_desc_sctcims,''0'') ,
					40	NVL(hststr_cims_guid,''0'') ,	NVL(hststr_cimstype,''0'') ,
					42	NVL(hstnum_sct_conceptid,''0'') ,	NVL(hstnum_sct_drugform,''0'') ,
					44	NVL(hststr_sct_drug_substance,''0'') ,NVL(hstnum_standard_flag,0) ,	
					46	NVL(hstnum_sct_generic_cid,0) ,	NVL(hstnum_sct_brand_cid,0) ,
					48	NVL(hstnum_max_value,0) ,	NVL(hstnum_min_value,0) ,
					50	NVL(hstnum_temp_sens_flag,0) ,	NVL(hstnum_is_returnable,0) 
					*/
						
				   		messageData.add(mobRptObject);
				    }
			    }
				
			
			
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return new ResponseEntity<List<DrugBrandMstObject>>(messageData, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}", method=RequestMethod.DELETE)
	@CrossOrigin(origins="*")
	public ResponseEntity<?> deleteDrugBrand(@PathVariable(value = "strVar1") String strBrandId,@PathVariable(value = "strVar2") String strItemId,@PathVariable(value = "strVar3") String strDrugName)
	{
		

		String message ="";
		try 
	    {
			
			message = durgBrandService.Drug_Brand_Delete_Method(strBrandId,strItemId,strDrugName);
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
	public ResponseEntity<?> process(@PathVariable(value = "strVar1") String strCatgCode,@RequestBody DrugBrandMstObject[] drugBrandArray) throws Exception 
	{
		String message ="";
		
		try
		{
				
	      	System.out.println("Catg_Code -->>"+strCatgCode);	      
	      	message = durgBrandService.Drug_Brand_Save_Update_Method(strCatgCode,drugBrandArray);  // Catg_Code , Array Of Drug Brand Object
	      
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<Object>("Error Raised--"+e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(message, HttpStatus.OK);
      	
	}	

}

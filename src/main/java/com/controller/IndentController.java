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

import com.model.IndentObject;
import com.service.IndentService;

@RestController
@RequestMapping("/IndentController")
public class IndentController {
	@Autowired
	private IndentService indentService;

	@RequestMapping(value = "/{strVar1}/{strVar2}/{strVar3}/{strWsMode}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> processList(@PathVariable(value = "strVar1") String strVar1,
			@PathVariable(value = "strVar2") String strVar2, 
			@PathVariable(value = "strVar3") String strCatgCode,
			@PathVariable("strWsMode") String strWsMode) throws Exception {
//		String seatId = "";
		String hosp_code = "";
		String strStoreId = "";
//		String strToStoreId = "";

		ArrayList<IndentObject> messageData = new ArrayList<IndentObject>();
		WebRowSet ws = null;
		try {
			hosp_code = strVar1;

			strStoreId = strVar2;

			System.out.println("strVar1---" + strVar1);
			System.out.println("strVar2---" + strVar2);
			System.out.println("strVar3---" + strCatgCode);
			// http://localhost:8085/drugBrandService/0/0/0/1/1
			System.out.println("http://localhost:8085/indentService/" + strVar1 + "/" + strVar2 + "/" + strCatgCode
					 + "/" + strWsMode + "(strWsMode)");

			String catg_Code = strCatgCode;

			ws = indentService.getIndentList(hosp_code, strStoreId, catg_Code);

			if (ws == null && ws.size() == 0) {
				return new ResponseEntity<List<IndentObject>>(HttpStatus.NO_CONTENT);
				// You many decide to return HttpStatus.NOT_FOUND
			}

			// System.out.println("WS_SIZE---"+ws.size());

			while (ws.next()) {
				IndentObject mobRptObject = new IndentObject();

				mobRptObject.setP_KEY(ws.getString(1));
				mobRptObject.setHstnum_REQ_NO(ws.getString(2));
				mobRptObject.setHstdt_REQ_DATE(ws.getString(3));

				mobRptObject.setHststr_STORE_NAME(ws.getString(4));
				mobRptObject.setHstnum_INDENT_STATUS(ws.getString(5));
//				   		mobRptObject.setHSTSTR_GRP_NAME(ws.getString(5));				   		
//				   		mobRptObject.setHSTNUM_ITEMBRAND_ID(ws.getString(6));
//				   		mobRptObject.setHSTNUM_ITEM_ID(ws.getString(7));
//				   		mobRptObject.setGNUM_HOSPITAL_CODE(ws.getString(8));
//				   		mobRptObject.setSSTNUM_ITEM_CAT_NO(ws.getString(9));
//				   		mobRptObject.setHSTNUM_ITEMTYPE_ID(ws.getString(25));
//				   		mobRptObject.setHSTNUM_GRP_ID(ws.getString(31));
//				   		mobRptObject.setHSTNUM_EDL_CATEGORY(ws.getString(37));				   		

				/*
				 * 1.ITEM_NAME , 2.GENERIC_NAME 3.SUPPLIER_NAME , 4.Item_Type 5.Group Name 6
				 * hstnum_itembrand_id , hstnum_item_id , 8 gnum_hospital_code ,
				 * sstnum_item_cat_no , 10 hstnum_manufacturer_id , hstnum_default_rate , 12
				 * hstnum_rate_unit_id , hstnum_approved_type , 14 hstnum_issue_type ,
				 * hststr_specification , 16 hstnum_item_make , gstr_remarks , 18
				 * hstnum_currpo_no , gdt_entry_date , 20 gnum_seatid , gnum_isvalid , 22
				 * hstnum_currpur_rate_unitid , hstnum_currsupplier_id , 24
				 * hstnum_set_sachet_flag , hstnum_itemtype_id , 26 hstnum_is_quantified ,
				 * hststr_cpa_code , 28 hstnum_inventory_unitid , hstnum_issuerate_value , 30
				 * hstnum_qc_type , hstnum_grp_id , 32 hstnum_subgrp_id ,
				 * hstnum_classification_id , 34 hstnum_batchno_req , hstnum_expirydate_req , 36
				 * hstnum_hsn_code , hstnum_edl_category , 38 hstnum_is_misc ,
				 * NVL(hstsr_desc_sctcims,''0'') , 40 NVL(hststr_cims_guid,''0'') ,
				 * NVL(hststr_cimstype,''0'') , 42 NVL(hstnum_sct_conceptid,''0'') ,
				 * NVL(hstnum_sct_drugform,''0'') , 44 NVL(hststr_sct_drug_substance,''0'')
				 * ,NVL(hstnum_standard_flag,0) , 46 NVL(hstnum_sct_generic_cid,0) ,
				 * NVL(hstnum_sct_brand_cid,0) , 48 NVL(hstnum_max_value,0) ,
				 * NVL(hstnum_min_value,0) , 50 NVL(hstnum_temp_sens_flag,0) ,
				 * NVL(hstnum_is_returnable,0)
				 */

				messageData.add(mobRptObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<IndentObject>>(messageData, HttpStatus.OK);
	}

	@RequestMapping(value = "/Save/{strVar1}/{strVar2}/{strVar3}/{strVar4}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> process(@PathVariable(value = "strVar1") String strVar1,
			@PathVariable(value = "strVar2") String strVar2, @PathVariable(value = "strVar3") String strCatgCode,
			@PathVariable(value = "strVar4") String strIndentDate, @RequestBody IndentObject[] indentArray)
			throws Exception {
		String message = "";
		String seatId = "";
		String hosp_code = "";
		String strStoreId = "";
		String strToStoreId = "";

		try {
			System.out.println(
					"------------------------- Store_Save_Update_Method Master DML Service ---------------------------");

			System.out.println("string 1" + strVar1);
			System.out.println("string 2" + strVar2);
			System.out.println("string 3" + strCatgCode);
			System.out.println("string 4" + strIndentDate);

			hosp_code = strVar1.split("\\^")[0];
			seatId = strVar1.split("\\^")[1];

			strStoreId = strVar2.split("\\^")[0];
			strToStoreId = strVar2.split("\\^")[1];

			for (IndentObject indentObj : indentArray) {

				System.out.println("getSstnum_item_id--->>>" + indentObj.getHstnum_ITEM_ID());
				System.out.println("getSstnum_item_id--->>>" + indentObj.getHstnum_ITEMBRAND_ID());
				System.out.println("getSstnum_item_id--->>>" + indentObj.getHstnum_INDENT_QTY());

			}

			message = indentService.Indent_Save_Method(strVar1, strVar2, strCatgCode, strIndentDate, indentArray); // Inventory
																													// Object

		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<Object>("Error Raised--" + e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Object>(message, HttpStatus.OK);

	}

}

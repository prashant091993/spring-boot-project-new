package global.Template;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;

import org.apache.commons.codec.binary.Base64;

import global.transactionmgnt.HisDAO;

public class TemplateDesignerMstUTIL 
{

	public void gettemplateDesignerEssensial(HttpServletRequest request,	TemplateDesignerMstFB fb)
	{
		System.out.println("-------------------TemplateDesignerMstUTIL . gettemplateDesignerEssensial (check)-----------------");
		
		Status status=new Status();
		HttpSession sess= request.getSession();
		ResultSet rs_controldtl=null;
		ResultSet rs_propertydtl=null;
		ResultSet rs_propertyvaluedtl=null;
		ResultSet rs_propertydefaultvaluedtl=null;
		ResultSet rs_templateCategory=null;
		ResultSet rs_docType=null;
		List lstDocType= new ArrayList();
		

		List lstcontroldtl= new ArrayList();
		List lstpropertydtl= new ArrayList();
		List lstpropertyvaluedtl= new ArrayList();
		List lstdefaultcontrolproperty= new ArrayList();
		List lstTemplateCategory= new ArrayList();
		sess.removeAttribute(OperationTheatreConfig.LISTCONTROL);
		sess.removeAttribute(OperationTheatreConfig.LISTPROPERTYVO);
		sess.removeAttribute(OperationTheatreConfig.LISTPROPERTYVALUEVO);
		sess.removeAttribute(OperationTheatreConfig.LIST_TEMPLATE_CATEGORY);
		sess.removeAttribute(OperationTheatreConfig.LIST_TEMPLATE_SUBCATEGORY);
		sess.removeAttribute(OperationTheatreConfig.LIST_DOC_TYPE);
		
		CallableStatement csmt=null;
		Connection conn = null;
		HisDAO dao = null;
		
		
		
		
		try{
			 dao = new HisDAO("MMS", "DrugInventoryTransDAO");	
			conn=dao.getConnection();			
			UserVO uservo=ControllerUTIL.getUserVO(request);
			csmt = conn.prepareCall("{call AHIS_OT_UTILITY.GETTEMPLATEDESIGNERESSENSIAL(?,?,?,?,?,?,?,?,?,?)}");
			//System.out.println("templaateid:::"+fb.getTemplateId());
			csmt.setString(1,(fb.getTemplateId()==null) ? " " : fb.getTemplateId() );			
			csmt.setString(2,uservo.getHospitalCode());
			csmt.registerOutParameter(3,Types.VARCHAR); //OracleTypes.VARCHAR);
			csmt.registerOutParameter(4,Types.VARCHAR);// OracleTypes.VARCHAR);
			csmt.registerOutParameter(5, Types.REF);// OracleTypes.CURSOR);
			csmt.registerOutParameter(6, Types.REF);//OracleTypes.CURSOR);
			csmt.registerOutParameter(7,Types.REF);// OracleTypes.CURSOR);
			csmt.registerOutParameter(8, Types.REF);//OracleTypes.CURSOR);
			csmt.registerOutParameter(9, Types.REF);//OracleTypes.CURSOR);
			csmt.registerOutParameter(10, Types.REF);//OracleTypes.CURSOR);
			csmt.execute();			
			
			System.out.println("error msg:"+csmt.getString(3)+"\nerror code"+csmt.getString(4));
			rs_controldtl=(ResultSet)csmt.getObject(5);
			rs_propertydtl=(ResultSet)csmt.getObject(6);
			rs_propertyvaluedtl=(ResultSet)csmt.getObject(7);
			rs_propertydefaultvaluedtl=(ResultSet)csmt.getObject(8);
			rs_templateCategory=(ResultSet)csmt.getObject(9);
			rs_docType=(ResultSet)csmt.getObject(10);
			//System.out.println("after executing procedure" );
			
			while(rs_controldtl.next()){
				OTTemplateControlVO objOTTemplateControlVO=new OTTemplateControlVO();
				objOTTemplateControlVO.setControlId(rs_controldtl.getString(1));
				objOTTemplateControlVO.setControlName(rs_controldtl.getString(2));
				objOTTemplateControlVO.setControlHtmlTag(rs_controldtl.getString(3));
				objOTTemplateControlVO.setControltldImport(rs_controldtl.getString(4));
				objOTTemplateControlVO.setControlstructTag(rs_controldtl.getString(5));
				objOTTemplateControlVO.setControlDisplayName(rs_controldtl.getString(6));
				objOTTemplateControlVO.setControlImage(rs_controldtl.getString(7));
				objOTTemplateControlVO.setIsVisble(rs_controldtl.getString(8));
				objOTTemplateControlVO.setAllPropertyIds(rs_controldtl.getString(9));
				objOTTemplateControlVO.setControlType(rs_controldtl.getString(10));
				objOTTemplateControlVO.setIsUsedVisDep(rs_controldtl.getString(11));
				lstcontroldtl.add(objOTTemplateControlVO);
			}
			
			while(rs_propertydtl.next()){
				OTTemplatePropertyVO objOTTemplatePropertyVO=new OTTemplatePropertyVO();
				objOTTemplatePropertyVO.setPropertyId(rs_propertydtl.getString(1));
				objOTTemplatePropertyVO.setPropertyName(rs_propertydtl.getString(2));
				objOTTemplatePropertyVO.setPropertyType(rs_propertydtl.getString(3));
				objOTTemplatePropertyVO.setPropertyDisplayName(rs_propertydtl.getString(4));
				objOTTemplatePropertyVO.setAllpropertyValueSqnos(rs_propertydtl.getString(5));		
				objOTTemplatePropertyVO.setIsVisble(rs_propertydtl.getString(6));
				objOTTemplatePropertyVO.setParentId(rs_propertydtl.getString(7));
				objOTTemplatePropertyVO.setParentName(rs_propertydtl.getString(8));
				lstpropertydtl.add(objOTTemplatePropertyVO);
			}
			while(rs_propertyvaluedtl.next()){
				OTTemplatePropertyValueVO objOTTemplatePropertyValueVO=new OTTemplatePropertyValueVO();
				objOTTemplatePropertyValueVO.setSqno(rs_propertyvaluedtl.getString(1));
				objOTTemplatePropertyValueVO.setPropertyValue(rs_propertyvaluedtl.getString(2));
				objOTTemplatePropertyValueVO.setPropertyDisplayValue(rs_propertyvaluedtl.getString(3));
				objOTTemplatePropertyValueVO.setIsDefault(rs_propertyvaluedtl.getString(4));
				objOTTemplatePropertyValueVO.setPropertyId(rs_propertyvaluedtl.getString(5));
				lstpropertyvaluedtl.add(objOTTemplatePropertyValueVO);
			}
		
			while(rs_propertydefaultvaluedtl.next()){
				OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= new OTTemplateControlPropertyVO();
				objOTTemplateControlPropertyVO.setControlId(rs_propertydefaultvaluedtl.getString(1));
				objOTTemplateControlPropertyVO.setIsVisble(rs_propertydefaultvaluedtl.getString(2));
				objOTTemplateControlPropertyVO.setPropertyId(rs_propertydefaultvaluedtl.getString(3));
				objOTTemplateControlPropertyVO.setPropertyName(rs_propertydefaultvaluedtl.getString(4));
				objOTTemplateControlPropertyVO.setPropertyType(rs_propertydefaultvaluedtl.getString(5));
				objOTTemplateControlPropertyVO.setPropertyDisplayName(rs_propertydefaultvaluedtl.getString(6));
				objOTTemplateControlPropertyVO.setSqno(rs_propertydefaultvaluedtl.getString(7));
				objOTTemplateControlPropertyVO.setPropertyValue(rs_propertydefaultvaluedtl.getString(8));
				objOTTemplateControlPropertyVO.setPropertyDisplayValue(rs_propertydefaultvaluedtl.getString(9));
				lstdefaultcontrolproperty.add(objOTTemplateControlPropertyVO);
			}
			
			while(rs_templateCategory.next()){
				Entry ent = new Entry();
				ent.setValue(rs_templateCategory.getString(1));
				ent.setLabel(rs_templateCategory.getString(2));
				lstTemplateCategory.add(ent);				
			}
			while(rs_docType.next()){			
				Entry ent = new Entry();
				ent.setValue(rs_docType.getString(1));
				ent.setLabel(rs_docType.getString(2));
				lstDocType.add(ent);				
			}

			List lstfinalControList=new ArrayList();
			
			
			int flag=0;
			for(int i=0;i<lstcontroldtl.size();i++){
				OTTemplateControlVO objOTTemplateControlVO=(OTTemplateControlVO)lstcontroldtl.get(i);
			//	System.out.println(objOTTemplateControlVO.getControlName());
				String allDefaltPropertyIds="";
				String alldefaltPropertyValues="";
				
				for(int j=0;j<lstdefaultcontrolproperty.size();j++){
					OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= (OTTemplateControlPropertyVO)lstdefaultcontrolproperty.get(j);
					if(objOTTemplateControlVO.getControlId().equals(objOTTemplateControlPropertyVO.getControlId())){						
						allDefaltPropertyIds+=objOTTemplateControlPropertyVO.getPropertyId()+",";
						
						// setting for style type
						if(objOTTemplateControlPropertyVO.getPropertyType().equals(OperationTheatreConfig.STYLE_TYPE) ){						
							flag=1;
							alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
						}
						// setting for html type
						if(objOTTemplateControlPropertyVO.getPropertyType().equals(OperationTheatreConfig.HTML_TYPE) ){
							if(objOTTemplateControlPropertyVO.getPropertyName().toLowerCase().equals("name")){
								//htmlTypeString+=" "+ objOTTemplateControlPropertyVO.getPropertyName()+"=$";
								alldefaltPropertyValues+="$";
							}
							else{
								if(objOTTemplateControlPropertyVO.getPropertyName().toLowerCase().equals("id") ){
									//htmlTypeString+=" "+ objOTTemplateControlPropertyVO.getPropertyName()+"=@";
									alldefaltPropertyValues+="@";
								}
								else{
									alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
									
								}
							}							
						}
						// setting for div  and div style  type
						if(objOTTemplateControlPropertyVO.getPropertyType().equals("3") ||objOTTemplateControlPropertyVO.getPropertyType().equals("4") ){
							alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
							
						}
						alldefaltPropertyValues+=",";
					}
				}
				if(!allDefaltPropertyIds.equals("")){
				allDefaltPropertyIds=allDefaltPropertyIds.substring(0 , allDefaltPropertyIds.length()-1);
				alldefaltPropertyValues=alldefaltPropertyValues.substring(0 , alldefaltPropertyValues.length()-1);
				objOTTemplateControlVO.setDefaultControlpropertyIds(allDefaltPropertyIds);
				objOTTemplateControlVO.setDefaultControlpropertyValues(alldefaltPropertyValues);
				lstfinalControList.add(objOTTemplateControlVO);
				}
			}
			
			
			Map mp=new HashMap();
			mp.put(OperationTheatreConfig.LISTCONTROL, lstfinalControList);
			mp.put(OperationTheatreConfig.LISTPROPERTYVO, lstpropertydtl);
			mp.put(OperationTheatreConfig.LISTPROPERTYVALUEVO, lstpropertyvaluedtl);
			mp.put(OperationTheatreConfig.LIST_TEMPLATE_CATEGORY, lstTemplateCategory);
			mp.put(OperationTheatreConfig.LIST_DOC_TYPE,lstDocType);
			
			
			
			
			WebUTIL.setMapInSession(mp, request);
		}
		catch (Exception e) {
			status.add(status.DONE, "Exception while getting record!", "");			
			e.printStackTrace();			
		}
		finally{
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			WebUTIL.setStatus(request, status);
		}
		
	}
	 public  void  populateDataInResponse(HttpServletRequest request,HttpServletResponse response, String strResult)
	{			

		 System.out.println("-------------------TemplateDesignerMstUTIL . populateDataInResponse ---------------------");

			try{
				PrintWriter writer=response.getWriter();
				writer.write(strResult);
				}catch(IOException e)
				{e.printStackTrace();}
	}	

	public void PopulatePropertyWindow(HttpServletRequest request,HttpServletResponse response, TemplateDesignerMstFB fb)
	{
		System.out.println("-------------------TemplateDesignerMstUTIL . PopulatePropertyWindow ---------------------");
		HttpSession session= request.getSession();
		List lstcontroldtl= (List) session.getAttribute(OperationTheatreConfig.LISTCONTROL);
		List lstpropertydtl= (List) session.getAttribute(OperationTheatreConfig.LISTPROPERTYVO);
		List lstpropertyvaluedtl= (List) session.getAttribute(OperationTheatreConfig.LISTPROPERTYVALUEVO);
		String strResult="DIV_PROPERTY_WINDOW$$";
		String allpropertyId=null;
		String nameForMap=null;
		
		try{
			String selIds=null;
			String selValues=null;
			String []allselectedIds=null;
			String []allselectedValues=null;
		//	System.out.println("selected values--" + fb.getSelectedPropertyValue());
			if(fb.getSelectedPropertyValue()!=null && fb.getSelectedPropertyValue().length()>0){
				selIds=fb.getSelectedPropertyIds();
				selValues=fb.getSelectedPropertyValue();
				
				allselectedIds=selIds.replace(",","#").split("#");
				allselectedValues= new String[allselectedIds.length];
				String []arrVal= arrVal=selValues.replace(",","#").split("#");
				
				 if(arrVal.length< allselectedIds.length){
					 for(int i=0;i<allselectedIds.length;i++){
						 if(i<arrVal.length){
							 allselectedValues[i]=arrVal[i];
						 }
						 else
						 {
							 allselectedValues[i]="";
						 }				
					 }			 
				 }
				 else{
					 allselectedValues=arrVal;
				 }
				 nameForMap=getpropertyNameWiseProperyPropertyValue("NAMEFORMAP", "0", lstpropertydtl, allselectedIds, allselectedValues);
			}
			
		strResult+="<table width='100%' cellpadding='0' cellspacing='1' align='left' >";
		String controlName="";
		
		
		
		for(int i=0;i<lstcontroldtl.size();i++){
			OTTemplateControlVO objOTTemplateControlVO= (OTTemplateControlVO) lstcontroldtl.get(i);
			if(objOTTemplateControlVO.getControlId().equals(fb.getSelectedControlId())){
				allpropertyId=objOTTemplateControlVO.getAllPropertyIds();
				controlName= objOTTemplateControlVO.getControlDisplayName();	
				
				break;			
			}		
		}
		
		strResult+="<tr><td class='tdfonthead' colspan='2' height='10%'>";
		strResult+="<div align='left'>";
		strResult+="<font color='black' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>CONTROL SELECTED-  </B></FONT>";
		strResult+="<font color='blue' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>";
		strResult+=controlName.toUpperCase()+"</b></font></div></td></tr>";
		
		
		if(allpropertyId!=null){
		//	System.out.println("allpropertyId--" + allpropertyId+ "--selIds--" + selIds);
			String []propertyIdArr=allpropertyId.replace(",","#").split("#");
			String currentparentId="0";
			String currentparentName="";
			int parentFlag=0;
			for(int i=0;i<propertyIdArr.length;i++){
				for(int j=0;j<lstpropertydtl.size();j++){
					OTTemplatePropertyVO objOTTemplatePropertyVO=(OTTemplatePropertyVO)lstpropertydtl.get(j) ;
					
					if(propertyIdArr[i].equals(objOTTemplatePropertyVO.getPropertyId())){
						
						if(!currentparentId.equals(objOTTemplatePropertyVO.getParentId())){
							currentparentId=objOTTemplatePropertyVO.getParentId();
							currentparentName=objOTTemplatePropertyVO.getParentName();
							//System.out.println("currentparentName--"+currentparentName);
							parentFlag=1;
						}
						
						String allsqnos=objOTTemplatePropertyVO.getAllpropertyValueSqnos();
						String type="type='text' onblur='setProperty()' ";
						String display="";
						if(objOTTemplatePropertyVO.getIsVisble()!=null &&  objOTTemplatePropertyVO.getIsVisble().equals("0") ){
							type="type='hidden'";
							display="none";
						}
						if(parentFlag==1){
							strResult+="<tr><td class='tdfont' colspan='2' height='10%'>";
							strResult+="<div align='left'>";
							strResult+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><b>";
							strResult+=currentparentName+"</b></font></div></td></tr>";
							parentFlag=0;
						}
						
						//System.out.println("prop name-" + objOTTemplatePropertyVO.getPropertyName());
						
						strResult+="<tr style='display:"+display+";'><td class='tdfonthead' width='40%' height='10%'>";
						strResult+="<div align='right'>";
						strResult+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
						strResult+="<input type='hidden' name='propertyType' value='"+objOTTemplatePropertyVO.getPropertyType()+"'/>";
						strResult+="<input type='hidden' name='propertyName' value='"+objOTTemplatePropertyVO.getPropertyName()+"'/>";
						strResult+=objOTTemplatePropertyVO.getPropertyDisplayName()+"</font></div></td>";
						strResult+="<td class='tdfont' width='60%'><div align='left'>";
						String propVal="";
						if(allselectedValues.length>0){
							
							
							for(int l=0;l<allselectedIds.length;l++){
								//System.out.println("objOTTemplatePropertyVO.getPropertyId()---" + objOTTemplatePropertyVO.getPropertyId()+" --allselectedIds[l]-" + allselectedIds[l] );
								if(objOTTemplatePropertyVO.getPropertyId().equals(allselectedIds[l])){
									propVal=allselectedValues[l];
									//System.out.println("propVal---" + propVal);
									break;
								}
							}							
						}
						
						if(propVal==null)propVal="";	
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.MULTIROW_TYPE)){
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("OPTIONS")){
								strResult+="<table width='100%' cellspacing='1' cellpadding='0' id='TABLEID_"+objOTTemplatePropertyVO.getPropertyName()+"'>";
								strResult+="<tr  height='10%'>";
								strResult+="<td class='tdfonthead' width='90%'><div align='left'>";
								strResult+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";
								strResult+="Options";
								strResult+="<input type='hidden' id='PROPID-"+objOTTemplatePropertyVO.getPropertyId()+"' name='propertyValue' value='"+propVal+"' >";
								strResult+="</font></div></td>";
								strResult+="<td class='tdfonthead' width='10%'><div align='center'>";
								strResult+="<img src='/HISOT/hisglobal/images/pl_small.gif' style='cursor:pointer;' onclick=createnewRowForOptions('"+objOTTemplatePropertyVO.getPropertyName()+"')>";
								strResult+="</div></td>";
								strResult+="</tr>";
								if(propVal==""){
									strResult+="<tr height='10%' id='TR_"+objOTTemplatePropertyVO.getPropertyName()+"'>";
									strResult+="<td class='tdfont' width='90%'><div align='left'>";
									strResult+="<input type='checkbox' name='chk_"+objOTTemplatePropertyVO.getPropertyName()+"' onclick='setpropertyForChecked(this)'><input type='text' name='txt_"+objOTTemplatePropertyVO.getPropertyName()+"' onblur='setProperty()'>";
									strResult+="</div></td>";
									strResult+="<td class='tdfont' width='10%'><div align='center'>";
									strResult+="<img src='/HISOT/hisglobal/images/mi_small.gif' style='cursor:pointer;' onclick=deleteMultyRow('TABLEID_"+objOTTemplatePropertyVO.getPropertyName()+"','TR_"+objOTTemplatePropertyVO.getPropertyName()+"') >";
									strResult+="</div></td>";
									strResult+="</tr>";
								}
								else
								{
									String[] arrpropVal=propVal.replace("@", "#").split("#");
									for(int m=0 ;m<arrpropVal.length;m++){
										strResult+="<tr height='10%' id='TR_"+objOTTemplatePropertyVO.getPropertyName()+m+"'>";
										strResult+="<td class='tdfont' width='90%'><div align='left'>";
										String []arroptions=arrpropVal[m].replace("$", "#").split("#");
										
										if(arroptions[0].equals("0"))
											strResult+="<input type='checkbox' name='chk_"+objOTTemplatePropertyVO.getPropertyName()+"' onclick='setpropertyForChecked(this)'>";
										else
											strResult+="<input type='checkbox' checked name='chk_"+objOTTemplatePropertyVO.getPropertyName()+"' onclick='setpropertyForChecked(this)'>";
										String val="";
										if(arroptions.length>1)
											val=arroptions[1];
										strResult+="<input type='text' style='width:120;' name='txt_"+objOTTemplatePropertyVO.getPropertyName()+"' value='"+val+"'>";
										strResult+="</div></td>";
										strResult+="<td class='tdfont' width='10%'><div align='center'>";
										strResult+="<img src='/HISOT/hisglobal/images/mi_small.gif' style='cursor:pointer;' onclick=deleteMultyRow('TABLEID_"+objOTTemplatePropertyVO.getPropertyName()+"','TR_"+objOTTemplatePropertyVO.getPropertyName()+m+"') >";
										strResult+="</div></td>";
										strResult+="</tr>";
									}// for m ends
									
								}//else end						
								
								strResult+="</table>";	
							}  // if end check for option prop	
							
							// for formula handling							
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("FORMULAATTRIBUTE")){
								strResult+="<table width='100%' cellspacing='1' cellpadding='0' id='TABLEID_"+objOTTemplatePropertyVO.getPropertyName()+"'>";
								strResult+="<tr  height='10%'>";
								strResult+="<td class='tdfonthead' width='90%'><div align='left'>";
								strResult+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>";								
								strResult+="<input type='hidden' id='PROPID-"+objOTTemplatePropertyVO.getPropertyId()+"' name='propertyValue' value='"+propVal+"' >";
								strResult+="</font></div></td>";
								strResult+="<td class='tdfonthead' width='10%'><div align='center'>";
								strResult+="<img src='/HISOT/hisglobal/images/pl_small.gif' style='cursor:pointer;' onclick=createnewRowForFormulaAttribute('"+objOTTemplatePropertyVO.getPropertyName()+"')>";
								strResult+="</div></td>";
								strResult+="</tr>";
								strResult+="<tr  height='10%'>";
								strResult+="<td class='tdfonthead' colspan='2'>";
								strResult+="<font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'><B>";								
								strResult+="Formula Preview";
								strResult+="</B></font></td>";
								strResult+="</tr>";
								strResult+="<tr  height='10%'>";
								strResult+="<td class='tdfonthead' colspan='2'>";
								strResult+="<font color='blue' size='2' face='Verdana, Arial, Helvetica, sans-serif'><B>";								
								strResult+="<div align='left' id='DIVIDFORMULADISPLAY'></div>";
								strResult+="</B></font></td>";
								strResult+="</tr>";
								if(propVal==""){
									strResult+="<tr height='10%' id='TR_"+objOTTemplatePropertyVO.getPropertyName()+"'>";
									strResult+="<td class='tdfont' width='90%'><div align='left'>";
									strResult+="<select style='width:50;' name='cboAttr_"+objOTTemplatePropertyVO.getPropertyName()+"' onchange='setProperty()'><option value='-1'>Select Value</option></select>&nbsp;";
									strResult+="<input type='text' style='width:50;display:none' name='txtCnstAttr_"+objOTTemplatePropertyVO.getPropertyName()+"' >";
									strResult+="</div></td>";
									strResult+="<td class='tdfont' width='10%'><div align='center'>";
									strResult+="<img src='/HISOT/hisglobal/images/mi_small.gif' style='cursor:pointer;' onclick=deleteMultyRow('TABLEID_"+objOTTemplatePropertyVO.getPropertyName()+"','TR_"+objOTTemplatePropertyVO.getPropertyName()+"') >";
									strResult+="</div></td>";
									strResult+="</tr>";
								}
								else
								{
									String[] arrpropVal=propVal.replace("@", "#").split("#");
									for(int m=0 ;m<arrpropVal.length;m++){
										strResult+="<tr height='10%' id='TR_"+objOTTemplatePropertyVO.getPropertyName()+m+"'>";
										strResult+="<td class='tdfont' width='90%'><div align='left'>";
										String []arroptions=arrpropVal[m].replace("$", "#").split("#");
										strResult+="<select style='width:50;' name='cboAttr_"+objOTTemplatePropertyVO.getPropertyName()+"' onchange='setProperty()'><option value='-1'>Select Value</option></select>&nbsp;";
										strResult+="<input type='text' style='width:50;display:none' name='txtCnstAttr_"+objOTTemplatePropertyVO.getPropertyName()+"' >";
										strResult+="</div></td>";										
										strResult+="<td class='tdfont' width='10%'><div align='center'>";
										strResult+="<img src='/HISOT/hisglobal/images/mi_small.gif' style='cursor:pointer;' onclick=deleteMultyRow('TABLEID_"+objOTTemplatePropertyVO.getPropertyName()+"','TR_"+objOTTemplatePropertyVO.getPropertyName()+m+"') >";
										strResult+="</div></td>";
										strResult+="</tr>";
									}// for m ends
									
								}//else end				
								
								strResult+="</table>";	
							}  // if end check for formula attribute
							
							
															
						}//if ends for multirow
						else// property type other than multirow;
						{
							if(allsqnos!=null){
								String[] sqnosArr=allsqnos.replace(",", "#").split("#");
								
								int flag=0;
							//	System.out.println("sqnosArr.length--" + sqnosArr.length);
									
								
								if(sqnosArr.length==1){
									
									if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("DOCUMENT_DESCRIPTION")){
										strResult+="<img class='button' src='/HISOT/hisglobal/images/V.png' style='cursor: pointer' onClick='IFramePopup.showModal(\"/HISOT/operationTheatres/masters/TemplateDesignerMstACTION.cnt?hmode=DOCUMENT_HTML_INIT&objId="+fb.getObjId()+"&objName="+nameForMap +"&templateId="+fb.getTemplateId()+"\",700,400)'><input type='hidden' id='PROPID-"+objOTTemplatePropertyVO.getPropertyId()+"' name='propertyValue' ";										
									}
									else
									{
										strResult+="<input "+ type +" style='width:120;' id='PROPID-"+objOTTemplatePropertyVO.getPropertyId()+"' name='propertyValue' ";
									}
									flag=1;
								}
								else
								{
									strResult+="<select onchange=setProperty() style='width:120;' id='PROPID-"+objOTTemplatePropertyVO.getPropertyId()+"' name='propertyValue'><option value='-1'>Select Value</option> ";
								}
								for(int l=0;l<sqnosArr.length;l++){	
									for(int k=0;k<lstpropertyvaluedtl.size();k++){
										OTTemplatePropertyValueVO objOTTemplatePropertyValueVO=(OTTemplatePropertyValueVO)lstpropertyvaluedtl.get(k);
										if(sqnosArr[l].equals(objOTTemplatePropertyValueVO.getSqno())&& objOTTemplatePropertyVO.getPropertyId().equals(objOTTemplatePropertyValueVO.getPropertyId())){
											if(flag==1)
											{
													if(propVal.equals(""))
														strResult+="value='"+objOTTemplatePropertyValueVO.getPropertyValue()+"'>";
													else
														strResult+="value='"+propVal+"'>";
													 
											}else
											{
												if(propVal.equals("")){
													if(objOTTemplatePropertyValueVO.getIsDefault().equals("1")){
														strResult+="<option selected value='"+objOTTemplatePropertyValueVO.getPropertyValue()+"'>"+objOTTemplatePropertyValueVO.getPropertyDisplayValue()+"</option>";
													}
													else{
														strResult+="<option value='"+objOTTemplatePropertyValueVO.getPropertyValue()+"'>"+objOTTemplatePropertyValueVO.getPropertyDisplayValue()+"</option>";
													}
												}
												else
												{
													if(objOTTemplatePropertyValueVO.getPropertyValue().equals(propVal))
														strResult+="<option selected value='"+objOTTemplatePropertyValueVO.getPropertyValue()+"'>"+objOTTemplatePropertyValueVO.getPropertyDisplayValue()+"</option>";
													else
														strResult+="<option value='"+objOTTemplatePropertyValueVO.getPropertyValue()+"'>"+objOTTemplatePropertyValueVO.getPropertyDisplayValue()+"</option>";
														
												}
											}
										}
									}
								}
								if(flag==0){
									strResult+="</select>";
								}							
							}							
							else
							{
								if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("DOCUMENT_DESCRIPTION"))
									strResult+="<img class='button' src='/HISOT/hisglobal/images/V.png' style='cursor: pointer' onClick='IFramePopup.showModal(\"/HISOT/operationTheatres/masters/TemplateDesignerMstACTION.cnt?hmode=DOCUMENT_HTML_INIT&objId="+fb.getObjId()+"&objName="+nameForMap +"&templateId="+fb.getTemplateId()+"\",700,400)'><input type='hidden' id='PROPID-"+objOTTemplatePropertyVO.getPropertyId()+"' name='propertyValue' ";								
								else
									strResult+="<input "+ type +" style='width:120;' id='PROPID-"+objOTTemplatePropertyVO.getPropertyId()+"' name='propertyValue' value='"+propVal+"' >";							
							}
						}
						strResult+="</div> </td></tr>";
						
					}
				}
			}
			
		}
		strResult+="</table>";
		//System.out.println("strResult---->" + strResult);
		populateDataInResponse(request, response, strResult);
	
	}catch(Exception e){
	  e.printStackTrace();
    }
 }
 
	public String getpropertyNameWiseProperyPropertyValue(String propertyName,String propertyId, List lstpropertydtl, String[] allPropertyId,String[] allPropertyValue){
	 String val="";
	 
	 if(propertyName!=null){
		 for(int j=0;j<lstpropertydtl.size();j++){
			OTTemplatePropertyVO objOTTemplatePropertyVO=(OTTemplatePropertyVO)lstpropertydtl.get(j) ;
			if(propertyName.toUpperCase().equals(objOTTemplatePropertyVO.getPropertyName().toUpperCase())){
				propertyId=objOTTemplatePropertyVO.getPropertyId();
			}		
		}
	}
	 for(int i=0;i<allPropertyId.length;i++){
		if(allPropertyId[i].equals(propertyId)){
			val=allPropertyValue[i];		
		}
	 }	
	 return val;
 }
	
	public static OTTemplateControlPropertyVO makeHTMLStringForEntryMode(HttpServletRequest request,String controlId,TemplateDesignerMstFB fb,int indx ,List lstControList,List lstpropertydtl) 
	{
		//System.out.println("-------------------TemplateDesignerMstUTIL . makeHTMLStringForEntryMode ---------------------"); 
		 int i,j,k; 
		 String htmlString="";
		 String styleString="";
		 String divHtmlString="";
		 String divStyleString="";
		 String onchangeFunction="";
		 String onclickFunction="";
		 String onblurFunction="";
		 String onkeypressFunction="";
		 String propertyNameType="";
		 
		 String multyRow="";
		 String allPropertyIDs;
		 String allPropertyValues;
		 String controlHtmlTag="";		 
		 int flagstyle=0;
		 int flagdivstyle=0;
		 int flagOnChange=0;
		 int flagOnClick=0;
		 int flagOnBlur=0;
		 int flagonkeypress=0;
		 String controlName="";
		 String optionByQuery="";
		 OTTemplateControlPropertyVO objOTTemplateControlPropertyVO = new OTTemplateControlPropertyVO();
		 OTTemplateControlVO         objOTTemplateControlVO         = new OTTemplateControlVO();
		 String paraId="";
		 String paraValue="";
		 String documentDataAttached="";
		 
		 String ismandatory="";
		 
		 if(controlId.equals(OperationTheatreConfig.TD_CONTROL_ID) )
		 {
			 allPropertyIDs		=	fb.getTdPropertyids()[indx]; 
			 allPropertyValues	=	fb.getTdPropertyvalues()[indx]; 
		 }
		 else
		 {
			 allPropertyIDs		=	fb.getControlPropertyIds()[indx]; 
			 allPropertyValues	=	fb.getControlPropertyValues()[indx];
			 paraId				=	fb.getParaId()[indx];
			 
			 for(i=0;i<lstControList.size();i++)
			 {
				 OTTemplateControlVO obj=(OTTemplateControlVO)lstControList.get(i);
				 if(obj.getControlId().equals(controlId)){
					 HelperMethods.populate(objOTTemplateControlVO, obj);
					 controlHtmlTag=obj.getControlHtmlTag();
					 controlName=obj.getControlName();
					 break;
				 }
			 }
			 Map templateValueMap=(HashMap)request.getSession().getAttribute(OperationTheatreConfig.OTTEMPLATE_PARAMETER_VALUES_MAP);
			 Map parameterValueMap=null;
			
			 if(templateValueMap!=null && templateValueMap.size()>0){
				 parameterValueMap=(HashMap) templateValueMap.get(fb.getTemplateId());
			 }
			 else
			 {
				 parameterValueMap=null;
			 }
			 if(parameterValueMap!=null && parameterValueMap.containsKey(paraId)){
				 paraValue=(String)parameterValueMap.get(paraId);
			 }
			
		 }
		 objOTTemplateControlPropertyVO.setIsUsedVisDep(objOTTemplateControlVO.getIsUsedVisDep());
			 
		 
		 
		 String [] arrAllPropertyIDs=allPropertyIDs.replace(",", "#").split("#");
		 String [] arrVal=allPropertyValues.replace(",", "#").split("#");
		 String [] arrAllPropertyValues= new String[arrAllPropertyIDs.length];
		 if(arrVal.length< arrAllPropertyIDs.length){
			 for(i=0;i<arrAllPropertyIDs.length;i++){
				 if(i<arrVal.length){
					 arrAllPropertyValues[i]=arrVal[i];
				 }
				 else
				 {
					 arrAllPropertyValues[i]="";
				 }				
			 }			 
		 }
		 else{
			 arrAllPropertyValues=arrVal;
		 }
		
		 
		 for(i=0;i<arrAllPropertyIDs.length;i++){
			 String propertyId=arrAllPropertyIDs[i];
			 for(j=0;j<lstpropertydtl.size();j++){
				 OTTemplatePropertyVO objOTTemplatePropertyVO=(OTTemplatePropertyVO)lstpropertydtl.get(j); 
				 if(objOTTemplatePropertyVO.getPropertyId().equals(propertyId)){
					 if(controlId.equals(OperationTheatreConfig.TD_CONTROL_ID)){			 
						 //FOR TD
						// System.out.println(objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"'");
						 htmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"'  ";		 
						 
					 }//end if for td
					 else{
						 // for parameter controls
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.STYLE_TYPE)){
							//style type
							if(flagstyle==0){
								styleString="style='"+ objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";
								flagstyle=1;
							}	
							else
								styleString= objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";								
							
						}
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.HTML_TYPE)){
							// html type
							//value handling starts-----------------------------------------------------------							
							if(objOTTemplatePropertyVO.getPropertyName().equals("value")){
									String paraForchk=paraValue;
									//String propertyValuesfprchk=arrAllPropertyValues[i];
									
									if(paraValue.equals("")){
										paraValue=arrAllPropertyValues[i];
									}							
															
									if(controlHtmlTag.contains(">INITVALUE<")){
										controlHtmlTag=controlHtmlTag.replace("INITVALUE", paraValue);  
									}
									else
									{
										if(controlHtmlTag.contains("INITVALUE")){											
											if(controlName.equals("checkbox")||  controlName.equals("radio"))
											{
												if(paraForchk.equals("on"))
												{
													controlHtmlTag=controlHtmlTag.replace("INITVALUE", objOTTemplatePropertyVO.getPropertyName()+"='"+ paraValue +"' checked");
												}											
											}
											else
											{
												controlHtmlTag=controlHtmlTag.replace("INITVALUE", objOTTemplatePropertyVO.getPropertyName()+"='"+ paraValue +"' ");
											}
										}	
										else
										{	
											
											if(controlName.equals("yesNo"))
											{
												if(paraValue.toUpperCase().equals("YES"))
												{	
													controlHtmlTag=controlHtmlTag.replaceAll("value='yes'", "value='yes' checked");
												}
												if(paraValue.toUpperCase().equals("NO"))
												{
													controlHtmlTag=controlHtmlTag.replaceAll("value='no'", "value='no' checked");
												}
												
											}
											else
											{	
											htmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ paraValue +"' ";
											}
										}	
									}
								
								//value handling ends-----------------------------------------
							}
							else
							 htmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"' ";
						}						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.DIV_STYLE_TYPE)){
							//div style type
							if(flagdivstyle==0){
								divStyleString="style='"+ objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";
								flagdivstyle=1;
							}	
							else
								divStyleString+= objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";								
							
						}
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.DIV_TYPE )){
							// div  type
							divHtmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"'  ";
						}
						
						
						
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONCLICK  )){
							// div  type
							if(flagOnClick==0){
								onclickFunction+="onclick='" + arrAllPropertyValues[i] +"(this,event);";
								flagOnClick=1;
							}
							else
							{
								onclickFunction+=arrAllPropertyValues[i] +"(this,event);";
							}
						}
						

						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONCHANGE )){
							// div  type
							if(flagOnChange==0){
								onchangeFunction+="onchange='" + arrAllPropertyValues[i]+"(this,event);";
								flagOnChange=1;
							}
							else
							{
								onchangeFunction+=arrAllPropertyValues[i] +"(this,event);";						
							}
						}
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONBLUR )){
							// div  type
							if(flagOnBlur==0){
								onblurFunction+="onblur='" + arrAllPropertyValues[i]+"(this,event);";
								flagOnBlur=1;
							}
							else
							{
								onblurFunction+=arrAllPropertyValues[i] +"(this,event);";
							}
						}
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONKEYPRESS )){
							// div  type
							if(flagonkeypress==0){
								onkeypressFunction+="onkeypress='return " + arrAllPropertyValues[i]+"(this,event);";
								flagonkeypress=1;
							}
							else
							{
								onkeypressFunction+=arrAllPropertyValues[i] +"(this,event);";
							}
						}						
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.PROPERTY_NAME_TYPE )){
							// div  type
							//System.out.println("iiiii-"+arrAllPropertyValues[i].toUpperCase());
							if(arrAllPropertyValues[i].equals("1") || arrAllPropertyValues[i].toUpperCase().equals("YES") || arrAllPropertyValues[i].toUpperCase().equals("TRUE")){
								propertyNameType+="  " + objOTTemplatePropertyVO.getPropertyName() ;								
							}						
						}
						optionByQuery="";
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.OTHER_TYPE )){
							// others type
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("ISMANDATORY")){
								if(arrAllPropertyValues[i].toUpperCase().equals("YES"))
									ismandatory="<font color='red' >*</font><input type='hidden' name='mandatory_@@' id='MANDATORY_@@' value="+arrAllPropertyValues[i]+" >";
								else
									ismandatory="<input type='hidden' name='mandatory_@@' id='MANDATORY_@@' value="+arrAllPropertyValues[i]+" >";
							}	
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("DISPLAYTEXT")){
								controlHtmlTag=controlHtmlTag.replace("DISPLAYTEXT", arrAllPropertyValues[i]) ;							
							}
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("TOOLBAR")){								 
								objOTTemplateControlPropertyVO.setScriptAttached("<script language='javaScript'>WYSIWYG.attach('TEXTAREAID');</script> ");
															
							}
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("NAMEFORMAP"))								 
								objOTTemplateControlPropertyVO.setNameForMap(arrAllPropertyValues[i]);
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("VISIBLEDEPENDENCY"))								 
								objOTTemplateControlPropertyVO.setParentControlName(arrAllPropertyValues[i]);
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("PARENTCONTROLVALUE"))								 
								objOTTemplateControlPropertyVO.setParentControlValue(arrAllPropertyValues[i]);		
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("LABELNAME"))								 
								objOTTemplateControlPropertyVO.setLabelName(arrAllPropertyValues[i]);
							
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("OPTIONSBYQUERY")){
								//doing
								if(!arrAllPropertyValues[i].equals("")&& !arrAllPropertyValues[i].contains("TABLENAME")){
									String query=arrAllPropertyValues[i].replace("$", ",");
									List lstOptions=getAllDataquery(request,query);
									if(lstOptions!=null && lstOptions.size()>0){
										for(int x=0;x<lstOptions.size();x++){
											Entry entry= (Entry)lstOptions.get(x); 
											if(!paraValue.equals("") && paraValue.equals(entry.getValue()))
												optionByQuery+="<option selected value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
											else
												optionByQuery+="<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";											
										}
									}
								}
							}
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("OPTIONSBYQUERY")){
								//doing
								if(!arrAllPropertyValues[i].equals("")&& !arrAllPropertyValues[i].contains("TABLENAME")){
									String query=arrAllPropertyValues[i].replace("$", ",");
									List lstOptions=getAllDataquery(request,query);
									if(lstOptions!=null && lstOptions.size()>0){
										for(int x=0;x<lstOptions.size();x++){
											Entry entry= (Entry)lstOptions.get(x); 
											if(!paraValue.equals("") && paraValue.equals(entry.getValue()))
												optionByQuery+="<option selected value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";
											else
												optionByQuery+="<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";											
										}
									}
								}
							}
							//for document attachment with control
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("DOCUMENT_DESCRIPTION")){
									documentDataAttached="Yes";								
							}
						}// other types ends here
						multyRow="";
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.MULTIROW_TYPE )){
							// multyrow  type
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("OPTIONS")){								
								String [] arroptions=arrAllPropertyValues[i].replace("@", "#").split("#");
								
								for(int l=0;l<arroptions.length;l++){
									String []arr= arroptions[l].replace("$", "#").split("#");									
									if(arr.length>1){
										if(paraValue.equals(""))
										{											
											if(arr[0].equals("0"))
												multyRow+="<option value='"+arr[1]+"'>"+arr[1]+"</option>";
											else
												multyRow+="<option selected value='"+arr[1]+"'>"+arr[1]+"</option>";
										}
										else
										{
											if(paraValue.equals(arr[1]))
												multyRow+="<option selected value='"+arr[1]+"'>"+arr[1]+"</option>";
											else
												multyRow+="<option value='"+arr[1]+"'>"+arr[1]+"</option>";
											
										}						
											
									}
								}
								
							}
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("FORMULAATTRIBUTE"))
								objOTTemplateControlPropertyVO.setFormula(arrAllPropertyValues[i]);
							
						}
						String finalOptions="<option value=''>Select Value</option>";
						if(!optionByQuery.equals(""))
						{
							finalOptions+=optionByQuery;
						}
						if(!multyRow.equals(""))
						{
							finalOptions+=multyRow;
						}
						if(controlHtmlTag.contains("<option value=''>Select Value</option>")){
							controlHtmlTag=controlHtmlTag.replace("<option value=''>Select Value</option>", finalOptions);  
						}
						
						
					 }//end else for parameter controls
					break; 
				 }					
			 }//end of inner  for		 
		 }//end of outer for
		 if(!styleString.equals(""))styleString+="'";
		 if(!divStyleString.equals(""))divStyleString+="'";
		 if(!onchangeFunction.equals(""))onchangeFunction+="'";
		 if(!onclickFunction.equals(""))onclickFunction+="'";
		 if(!onblurFunction.equals(""))onblurFunction+="'";
		 if(!onkeypressFunction.equals(""))onkeypressFunction+="'";
		 
		 
		 
		 if(!controlId.equals(OperationTheatreConfig.TD_CONTROL_ID)){
		 controlHtmlTag=controlHtmlTag.replace("#", styleString + " " + htmlString   + " " +  onkeypressFunction + " " +  onchangeFunction + " " + onclickFunction + " " + onblurFunction + " " + propertyNameType + " @");
		 controlHtmlTag=controlHtmlTag +ismandatory;
		 //System.out.println("controlHtmlTag-->" + controlHtmlTag);
		 objOTTemplateControlPropertyVO.setControlHtmlTag(controlHtmlTag);
		 objOTTemplateControlPropertyVO.setParaId(fb.getParaId()[indx]);
		 
		 objOTTemplateControlPropertyVO.setControlName(fb.getParameterControlName()[indx]);	
		 objOTTemplateControlPropertyVO.setLocation(fb.getParameterLocation()[indx]);
		 objOTTemplateControlPropertyVO.setControlType(objOTTemplateControlVO.getControlType());
		 //for document attachment with control
		 objOTTemplateControlPropertyVO.setDocumentDataAttached(documentDataAttached);
		 
		 }
		 else
		 {
			 objOTTemplateControlPropertyVO.setLocation(fb.getLocation()[indx]);
			 objOTTemplateControlPropertyVO.setParaId(fb.getTemplateTDParaId());
		 }
		 objOTTemplateControlPropertyVO.setControlId(controlId);		 
		 objOTTemplateControlPropertyVO.setAllPropertyIds(allPropertyIDs);
		 objOTTemplateControlPropertyVO.setAllPropertyValues(allPropertyValues);
		 	
		 objOTTemplateControlPropertyVO.setStyleString(styleString);
		 objOTTemplateControlPropertyVO.setHtmlCode(htmlString);
		 objOTTemplateControlPropertyVO.setDivHtmlString(divHtmlString);
		 objOTTemplateControlPropertyVO.setDivStyleString(divStyleString);
		 objOTTemplateControlPropertyVO.setControlName(controlName);
		 return objOTTemplateControlPropertyVO;
		 
	 }


	public static OTTemplateControlPropertyVO makeHTMLStringForReport(HttpServletRequest request,String controlId,TemplateDesignerMstFB fb,int indx ,List lstControList,List lstpropertydtl) {
		//System.out.println("-------------------TemplateDesignerMstUTIL . makeHTMLStringForReport ---------------------"); 
		int i,j,k; 
		 String htmlString="";
		 String styleString="";
		 String divHtmlString="";
		 String divStyleString="";
		 String onchangeFunction="";
		 String onclickFunction="";
		 String onblurFunction="";
		 String onkeypressFunction="";
		 String propertyNameType="";
		 
		 String multyRow="";
		 String allPropertyIDs;
		 String allPropertyValues;
		 String controlHtmlTag="";		 
		 int flagstyle=0;
		 int flagdivstyle=0;
		 int flagOnChange=0;
		 int flagOnClick=0;
		 int flagOnBlur=0;
		 int flagonkeypress=0;
		 String controlName="";
		 String optionByQuery="";
		 OTTemplateControlPropertyVO objOTTemplateControlPropertyVO=new OTTemplateControlPropertyVO();
		 OTTemplateControlVO objOTTemplateControlVO= new OTTemplateControlVO();
		 String paraId="";
		 String paraValue="";
		 	 
		 
		 if(controlId.equals(OperationTheatreConfig.TD_CONTROL_ID) ){
			 allPropertyIDs=fb.getTdPropertyids()[indx]; 
			 allPropertyValues=fb.getTdPropertyvalues()[indx]; 
		 }
		 else{
			 allPropertyIDs=fb.getControlPropertyIds()[indx]; 
			 allPropertyValues=fb.getControlPropertyValues()[indx];
			 paraId=fb.getParaId()[indx];
			 for(i=0;i<lstControList.size();i++){
				 OTTemplateControlVO obj=(OTTemplateControlVO)lstControList.get(i);
				 if(obj.getControlId().equals(controlId)){
					 HelperMethods.populate(objOTTemplateControlVO, obj);
					 controlHtmlTag=obj.getControlHtmlTag();
					 controlName=obj.getControlName();
					 break;
				 }
			 }
			 if(!controlName.toUpperCase().equals("LABEL")){
				 controlHtmlTag="&nbsp;<font color='black'><label #>INITVALUE</label></font>";
				//	controlHtmlTag="&nbsp;<font color='black'><label #></label></font>";
			 }
			 
			 Map templateValueMap=(HashMap)request.getSession().getAttribute(OperationTheatreConfig.OTTEMPLATE_PARAMETER_VALUES_MAP);
			 Map parameterValueMap=null;
			
			 if(templateValueMap!=null && templateValueMap.size()>0){
				 parameterValueMap=(HashMap) templateValueMap.get(fb.getTemplateId());
			 }
			 else
			 {
				 parameterValueMap=null;
			 }
			 if(parameterValueMap!=null && parameterValueMap.containsKey(paraId)){
				 paraValue=(String)parameterValueMap.get(paraId);
			 }
			
		 }
		 objOTTemplateControlPropertyVO.setIsUsedVisDep(objOTTemplateControlVO.getIsUsedVisDep());
			 
		 
		 
		 String [] arrAllPropertyIDs=allPropertyIDs.replace(",", "#").split("#");
		 String [] arrVal=allPropertyValues.replace(",", "#").split("#");
		 String [] arrAllPropertyValues= new String[arrAllPropertyIDs.length];
		 if(arrVal.length< arrAllPropertyIDs.length){
			 for(i=0;i<arrAllPropertyIDs.length;i++){
				 if(i<arrVal.length){
					 arrAllPropertyValues[i]=arrVal[i];
				 }
				 else
				 {
					 arrAllPropertyValues[i]="";
				 }				
			 }			 
		 }
		 else{
			 arrAllPropertyValues=arrVal;
		 }
		
		 
		 for(i=0;i<arrAllPropertyIDs.length;i++){
			 String propertyId=arrAllPropertyIDs[i];
			 for(j=0;j<lstpropertydtl.size();j++){
				 OTTemplatePropertyVO objOTTemplatePropertyVO=(OTTemplatePropertyVO)lstpropertydtl.get(j); 
				 if(objOTTemplatePropertyVO.getPropertyId().equals(propertyId)){
					 if(controlId.equals(OperationTheatreConfig.TD_CONTROL_ID)){			 
						 //FOR TD
						// System.out.println(objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"'");
						 htmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"'  ";		 
						 
					 }//end if for td
					 else{
						 // for parameter controls
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.STYLE_TYPE)){
							//style type
							if(flagstyle==0){
								styleString="style='"+ objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";
								flagstyle=1;
							}	
							else
								styleString= objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";								
							
						}
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.HTML_TYPE)){
							// html type
							//value handling starts-----------------------------------------------------------							
							if(objOTTemplatePropertyVO.getPropertyName().equals("value"))
							{
								   // System.out.println("--------paraValue----["+paraValue+"]-------["+i+"]---"+arrAllPropertyValues[i]);
									if(paraValue.equals(""))
									{
										paraValue=arrAllPropertyValues[i];
									}
									
									if(paraValue.equals("on"))
									{	
										if(controlName.equals("checkbox")||  controlName.equals("radio"))
										{
											paraValue="<b>Yes</b>";
										}
									}
									else
									{
										if(controlName.equals("checkbox")||  controlName.equals("radio"))
										{
											paraValue="<b>No</b>";
										}
									}
									
															
									if(controlHtmlTag.contains(">INITVALUE<")){
										if(!paraValue.equals(""))
										controlHtmlTag=controlHtmlTag.replace("INITVALUE", paraValue); 
										else
										controlHtmlTag=controlHtmlTag.replace("INITVALUE", ""); 
									}
								/*	else
									{
										if(controlHtmlTag.contains("INITVALUE"))
											controlHtmlTag=controlHtmlTag.replace("INITVALUE", objOTTemplatePropertyVO.getPropertyName()+"='"+ paraValue +"' ");
										else
										{	
											if(controlName.equals("yesNo")){
												if(paraValue.toUpperCase().equals("YES"))
													controlHtmlTag=controlHtmlTag.replaceAll("value='yes'", "value='yes' checked");
												if(paraValue.toUpperCase().equals("NO"))
													controlHtmlTag=controlHtmlTag.replaceAll("value='no'", "value='no' checked");
												
											}
											else
											{	
											htmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ paraValue +"' ";
											}
										}	
									}*/
								
								//value handling ends-----------------------------------------
							}
							else
							 htmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"' ";
						}						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.DIV_STYLE_TYPE)){
							//div style type
							if(flagdivstyle==0){
								divStyleString="style='"+ objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";
								flagdivstyle=1;
							}	
							else
								divStyleString+= objOTTemplatePropertyVO.getPropertyName()+ ":"+arrAllPropertyValues[i]+";";								
							
						}
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.DIV_TYPE )){
							// div  type
							divHtmlString+=objOTTemplatePropertyVO.getPropertyName()+"='"+ arrAllPropertyValues[i] +"'  ";
						}
						
						
						
						
						/*if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONCLICK  )){
							// div  type
							if(flagOnClick==0){
								onclickFunction+="onclick='" + arrAllPropertyValues[i] +"(this,event);";
								flagOnClick=1;
							}
							else
							{
								onclickFunction+=arrAllPropertyValues[i] +"(this,event);";
							}
						}
						

						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONCHANGE )){
							// div  type
							if(flagOnChange==0){
								onchangeFunction+="onchange='" + arrAllPropertyValues[i]+"(this,event);";
								flagOnChange=1;
							}
							else
							{
								onchangeFunction+=arrAllPropertyValues[i] +"(this,event);";						
							}
						}
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONBLUR )){
							// div  type
							if(flagOnBlur==0){
								onblurFunction+="onblur='" + arrAllPropertyValues[i]+"(this,event);";
								flagOnBlur=1;
							}
							else
							{
								onblurFunction+=arrAllPropertyValues[i] +"(this,event);";
							}
						}
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.FUNCTION_TYPE_CALL_ONKEYPRESS )){
							// div  type
							if(flagonkeypress==0){
								onkeypressFunction+="onkeypress='return " + arrAllPropertyValues[i]+"(this,event);";
								flagonkeypress=1;
							}
							else
							{
								onkeypressFunction+=arrAllPropertyValues[i] +"(this,event);";
							}
						}
						
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.PROPERTY_NAME_TYPE )){
							// div  type
							//System.out.println("iiiii-"+arrAllPropertyValues[i].toUpperCase());
							if(arrAllPropertyValues[i].equals("1") || arrAllPropertyValues[i].toUpperCase().equals("YES") || arrAllPropertyValues[i].toUpperCase().equals("TRUE")){
								propertyNameType+="  " + objOTTemplatePropertyVO.getPropertyName() ;								
							}						
						}*/
						optionByQuery="";
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.OTHER_TYPE )){
							// others type
							if(objOTTemplatePropertyVO.getPropertyName().equals("DisplayText")){
								controlHtmlTag=controlHtmlTag.replace("DISPLAYTEXT", arrAllPropertyValues[i]) ;							
							}
							/*if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("TOOLBAR")){								 
								objOTTemplateControlPropertyVO.setScriptAttached("<script language='javaScript'>WYSIWYG.attach('TEXTAREAID',"+arrAllPropertyValues[i]+");</script> ");
															
							}*/
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("NAMEFORMAP"))								 
								objOTTemplateControlPropertyVO.setNameForMap(arrAllPropertyValues[i]);
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("VISIBLEDEPENDENCY"))								 
								objOTTemplateControlPropertyVO.setParentControlName(arrAllPropertyValues[i]);
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("PARENTCONTROLVALUE"))								 
								objOTTemplateControlPropertyVO.setParentControlValue(arrAllPropertyValues[i]);		
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("LABELNAME"))								 
								objOTTemplateControlPropertyVO.setLabelName(arrAllPropertyValues[i]);
							
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("OPTIONSBYQUERY")){
								//doing
								if(!arrAllPropertyValues[i].equals("")&& !arrAllPropertyValues[i].contains("TABLENAME")){
									String query=arrAllPropertyValues[i].replace("$", ",");
									List lstOptions=getAllDataquery(request,query);
									if(lstOptions!=null && lstOptions.size()>0){
										for(int x=0;x<lstOptions.size();x++){
											Entry entry= (Entry)lstOptions.get(x); 
											if(!paraValue.equals("") && paraValue.equals(entry.getValue()))
												controlHtmlTag=controlHtmlTag.replace("INITVALUE", entry.getLabel());	
											//else
											//	controlHtmlTag=controlHtmlTag.replace("INITVALUE", "");
										//	else
											//	optionByQuery+="<option value='"+entry.getValue()+"'>"+entry.getLabel()+"</option>";											
										}
									}
								}
							}							
						}
						multyRow="";
						
						if(objOTTemplatePropertyVO.getPropertyType().equals(OperationTheatreConfig.MULTIROW_TYPE )){
							// multyrow  type
							if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("OPTIONS")){								
								String [] arroptions=arrAllPropertyValues[i].replace("@", "#").split("#");
								
								for(int l=0;l<arroptions.length;l++){
									String []arr= arroptions[l].replace("$", "#").split("#");									
									if(arr.length>1){
											
										if(!paraValue.equals("")){
											controlHtmlTag=controlHtmlTag.replace("INITVALUE", paraValue);
										}
										else
										{
											controlHtmlTag=controlHtmlTag.replace("INITVALUE", "");
										}
										/*if(paraValue.equals(""))
										{											
											if(arr[0].equals("0"))
												multyRow+="<option value='"+arr[1]+"'>"+arr[1]+"</option>";
											else
												multyRow+="<option selected value='"+arr[1]+"'>"+arr[1]+"</option>";
										}
										else
										{
											if(paraValue.equals(arr[1]))
												multyRow+="<option selected value='"+arr[1]+"'>"+arr[1]+"</option>";
											else
												multyRow+="<option value='"+arr[1]+"'>"+arr[1]+"</option>";
											
										}		*/				
											
									}
								}
								
							}
							//if(objOTTemplatePropertyVO.getPropertyName().toUpperCase().equals("FORMULAATTRIBUTE"))
								//objOTTemplateControlPropertyVO.setFormula(arrAllPropertyValues[i]);
							
						}
					/*	String finalOptions="<option value=''>Select Value</option>";
						if(!optionByQuery.equals(""))
						{
							finalOptions+=optionByQuery;
						}
						if(!multyRow.equals(""))
						{
							finalOptions+=multyRow;
						}
						if(controlHtmlTag.contains("<option value=''>Select Value</option>")){
							controlHtmlTag=controlHtmlTag.replace("<option value=''>Select Value</option>", finalOptions);  
						}
					*/	
						
					 }//end else for parameter controls
					break; 
				 }					
			 }//end of inner  for		 
		 }//end of outer for
		 if(!styleString.equals(""))styleString+="'";
		 if(!divStyleString.equals(""))divStyleString+="'";
		 if(!onchangeFunction.equals(""))onchangeFunction+="'";
		 if(!onclickFunction.equals(""))onclickFunction+="'";
		 if(!onblurFunction.equals(""))onblurFunction+="'";
		 if(!onkeypressFunction.equals(""))onkeypressFunction+="'";
		 
		 controlHtmlTag=controlHtmlTag.replace("#", styleString + " " + htmlString   + " " +  onkeypressFunction + " " +  onchangeFunction + " " + onclickFunction + " " + onblurFunction + " " + propertyNameType + " @");
		 //System.out.println("controlHtmlTag-->" + controlHtmlTag);
		 objOTTemplateControlPropertyVO.setControlHtmlTag(controlHtmlTag);
		 objOTTemplateControlPropertyVO.setParaId(fb.getParaId()[indx]);
		 
		 objOTTemplateControlPropertyVO.setControlName(fb.getParameterControlName()[indx]);	
		 objOTTemplateControlPropertyVO.setLocation(fb.getParameterLocation()[indx]);
		 objOTTemplateControlPropertyVO.setControlType(objOTTemplateControlVO.getControlType());
		 
		 
		 objOTTemplateControlPropertyVO.setControlId(controlId);		 
		 objOTTemplateControlPropertyVO.setAllPropertyIds(allPropertyIDs);
		 objOTTemplateControlPropertyVO.setAllPropertyValues(allPropertyValues);
		 	
		 objOTTemplateControlPropertyVO.setStyleString(styleString);
		 objOTTemplateControlPropertyVO.setHtmlCode(htmlString);
		 objOTTemplateControlPropertyVO.setDivHtmlString(divHtmlString);
		 objOTTemplateControlPropertyVO.setDivStyleString(divStyleString);
		 objOTTemplateControlPropertyVO.setControlName(controlName);
		 return objOTTemplateControlPropertyVO;
		 
	 }
	public static  List getAllDataquery(HttpServletRequest request, String query)
	{
	   	
		Connection conn = null;	
   		List alRecord = new ArrayList();
	   	try{	 				 	
	   		UserVO uservo=ControllerUTIL.getUserVO(request);
	   		//Class.forName(OperationTheatreConfig.databaseDriver);// "oracle.jdbc.driver.OracleDriver");
	   		
	   		HisDAO dao = null;

	   		dao = new HisDAO("MMS", "DrugInventoryTransDAO");	
	   		conn=dao.getConnection();
		
			System.out.println("-------------------TemplateDesignerMstUTIL . getAllDataquery -------------------query--"+query); 
	   		ResultSet rs = conn.createStatement().executeQuery(query);	
	   		while(rs.next()){
	   			Entry ent= new Entry();
	   			ent.setValue(rs.getString(1));
	   			ent.setLabel(rs.getString(2));
	   			alRecord.add(ent);
	   		}	   			 	      
	 	  }
	 	   catch(Exception e)
	   	{
	    		  
	      }	
	 	  finally{
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}				
			}
		   	 return alRecord;   
    }

 public  static void setObjectInSession(HttpServletRequest request, TemplateDesignerMstFB fb) {
	 
	System.out.println("-------------------TemplateDesignerMstUTIL . setObjectInSession -------------------"); 
	HttpSession sess 		=	request.getSession(); 
	List lstControList		=	new ArrayList();
	List lstpropertydtl		=	new ArrayList();
	List lstTdObject		= 	new ArrayList();
	List lstControlobjects	= 	new ArrayList();
	
	if(fb.getTemplateTagMode()==null)
		fb.setTemplateTagMode(OperationTheatreConfig.OTTEMPLATE_ENTRY_MODE);
	
	int i,j,k;
	sess.removeAttribute(OperationTheatreConfig.ALLTDOBJECT  );
	sess.removeAttribute(OperationTheatreConfig.ALLCONTROLOBJECT);
	
	lstControList=(List) sess.getAttribute(OperationTheatreConfig.LISTCONTROL);
	lstpropertydtl=(List) sess.getAttribute(OperationTheatreConfig.LISTPROPERTYVO);

	//System.out.println("-------------------TemplateDesignerMstUTIL . makeHTMLStringForEntryMode -------------------");
	
	if(fb.getLocation()!=null)
	{
		
		//System.out.println("--fb.getParameterControlId()----"+fb.getParameterControlId().length);
		
		for(i=0;i<fb.getLocation().length;i++)
		{
				/*
				 * System.out.println("-A-makeHTMLStringForEntryMode----");
				 * System.out.println("--fb.getLocation().length-------"+fb.getLocation().length
				 * );
				 * System.out.println("--lstControList.length----------"+lstControList.size());
				 * System.out.println("--lstpropertydtl.length---------"+lstpropertydtl.size());
				 */
			
			    OTTemplateControlPropertyVO objOTTemplateControlPropertyVO=new OTTemplateControlPropertyVO();
				
				objOTTemplateControlPropertyVO=makeHTMLStringForEntryMode(request,"1", fb, i, lstControList, lstpropertydtl);					
			    lstTdObject.add(objOTTemplateControlPropertyVO);		
		}
		if(fb.getParameterControlId()!=null)
		{
			for(j=0;j<fb.getParameterControlId().length;j++)
			{
				//System.out.println("--fb.getParameterControlId()-["+j+"]---"+fb.getParameterControlId()[j]);
				
				OTTemplateControlPropertyVO obj=new OTTemplateControlPropertyVO();	
				if(fb.getTemplateTagMode().trim().equals(OperationTheatreConfig.OTTEMPLATE_ENTRY_MODE))		
				{
					//System.out.println("-B-makeHTMLStringForEntryMode----");
					obj=makeHTMLStringForEntryMode(request,fb.getParameterControlId()[j], fb, j, lstControList, lstpropertydtl);
				}
				else
				{
					//System.out.println("-B-makeHTMLStringForReport----");
					obj=makeHTMLStringForReport(request,fb.getParameterControlId()[j], fb, j, lstControList, lstpropertydtl);
				}
					
				lstControlobjects.add(obj);
			}
		}
		else
			lstControlobjects=null;	
		
		int maxRowLocation=0;
		int maxColLocation=0;
		for(i=0;i<fb.getLocation().length;i++)
		{
			if(maxColLocation<Integer.parseInt(fb.getLocation()[i].replace("*", "#").split("#")[1]))
			{
				maxColLocation=Integer.parseInt(fb.getLocation()[i].replace("*", "#").split("#")[1]);
			}
			if(maxRowLocation<Integer.parseInt(fb.getLocation()[i].replace("*", "#").split("#")[0]))
			{
				maxRowLocation=Integer.parseInt(fb.getLocation()[i].replace("*", "#").split("#")[0]);
			}
		}
		maxRowLocation=maxRowLocation-1;
		maxColLocation=maxColLocation+1;
		
		//System.out.println("maxRowLocation-" + maxRowLocation+ " maxColLocation-" + maxColLocation);
		fb.setMaxRowLocation(maxRowLocation+"");
		fb.setMaxColLocation(maxColLocation+"");
	}
	sess.setAttribute(OperationTheatreConfig.ALLTDOBJECT , lstTdObject);
	sess.setAttribute(OperationTheatreConfig.ALLCONTROLOBJECT , lstControlobjects);
	
 }
 
 
 public static String DesignTemplate(String templateId, String mode,HttpServletRequest request) 
 {	
		String resultString="";	
		HttpSession sess= request.getSession();
		List lstTdObject= new ArrayList();
		List lstControlobjects= new ArrayList();
		
		int i,j,k,l;
		try
		{
			
			System.out.println("-------------------TemplateDesignerMstUTIL . DesignTemplate ---------------------");
			
			if(!templateId.equals("master"))
			{
				System.out.println("templateId ---------------------"+templateId+" [ !templateId.equals(master) ]");
				//System.out.println("master"+templateId);
				TemplateDesignerMstFB fb= new TemplateDesignerMstFB();		
				fb.setTemplateId(templateId);
				fb.setTemplateTagMode(mode);
				initModify(request,  fb);
				DesignTemplateVO objDesignTemplateVO= new DesignTemplateVO();
				HelperMethods.populate(objDesignTemplateVO, fb);
				sess.setAttribute(OperationTheatreConfig.TEMPLATEDTL,objDesignTemplateVO);
			}
			
			
			lstTdObject=(List)sess.getAttribute(OperationTheatreConfig.ALLTDOBJECT);
			lstControlobjects=(List)sess.getAttribute(OperationTheatreConfig.ALLCONTROLOBJECT);			
			
			Map templateValueMap=(HashMap)request.getSession().getAttribute(OperationTheatreConfig.OTTEMPLATE_PARAMETER_VALUES_MAP);
			
			//System.out.println("Size of the templateValueMap is : " + templateValueMap.size());
			
			if(lstTdObject!=null && lstTdObject.size()>0)
			{
				for(k=0;k<lstTdObject.size();k++)
				{					
					    OTTemplateControlPropertyVO tdObj=(OTTemplateControlPropertyVO)lstTdObject.get(k);
					   // System.out.println("getLocation----"+tdObj.getLocation());	
					    
						if(lstControlobjects!=null && lstControlobjects.size()>0)
						{
							
							for(l=0;l<lstControlobjects.size();l++)
							{
								
								OTTemplateControlPropertyVO objControl=(OTTemplateControlPropertyVO)lstControlobjects.get(l);
								
							/*
							 * System.out.println("getControlId----"+objControl.getControlId());
							 * System.out.println("getControlName----"+objControl.getControlName());
							 * System.out.println("getDivHtmlString----"+objControl.getDivHtmlString());
							 * System.out.println("getDivStyleString----"+objControl.getDivStyleString());
							 * System.out.println("getControlHtmlTag----"+objControl.getControlHtmlTag());
							 */
								
								
								
							}
						}
					
				}
			}
			
			
			
										        
			resultString=getFinalTemplateString(templateId, mode, templateValueMap, lstTdObject, lstControlobjects);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resultString;
	}
 
 
 
 
 public static String DesignTemplateForPDF(String templateId, String mode,HttpServletRequest request) {	
		String resultString="";	
		HttpSession sess= request.getSession();
		List lstTdObject= new ArrayList();
		List lstControlobjects= new ArrayList();
		
		int i,j,k,l;
		try{
			
			System.out.println("-------------------TemplateDesignerMstUTIL . DesignTemplateForPDF ---------------------");
		if(!templateId.equals("master")){
			System.out.println("DesignTemplateForPDF.templateId-------->"+templateId);
			TemplateDesignerMstFB fb= new TemplateDesignerMstFB();		
			fb.setTemplateId(templateId);
			fb.setTemplateTagMode(mode);
			initModify(request,  fb);
			DesignTemplateVO objDesignTemplateVO= new DesignTemplateVO();
			HelperMethods.populate(objDesignTemplateVO, fb);
			sess.setAttribute(OperationTheatreConfig.TEMPLATEDTL,objDesignTemplateVO);
		}
		
		lstTdObject=(List)sess.getAttribute(OperationTheatreConfig.ALLTDOBJECT);
		lstControlobjects=(List)sess.getAttribute(OperationTheatreConfig.ALLCONTROLOBJECT);
		Map templateValueMap=(HashMap)request.getSession().getAttribute(OperationTheatreConfig.OTTEMPLATE_PARAMETER_VALUES_MAP);
		resultString=getFinalTemplateStringforPDF(templateId, mode, templateValueMap, lstTdObject, lstControlobjects);
		 if(request.getParameter("transactionId")!=null)
		 {
			if(request.getParameter("transactionId").equals("2"))
			 {	
			   insertstring(resultString,request);
			 } 
		 }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return resultString;
	}
 
 
 

 public static void insertstring(String res,HttpServletRequest request) 
 {
	 HisDAO dao = null;
	 int funcIndex,procIndex1=0,procIndex2=0,procIndex3=0;
	 String strChk="";
	 String err = "";
	 WebRowSet ws = null;
	 WebRowSet ws1 = null;
	 String patientName = "" , fatherName = "", dateOfBirth="", spouseName="", catgName="", genderName="";
	 String deptName = "" , deptUnitName="";
	 String wardName = "";
	 String visitType = "",visitDate="",visitNo="",patStatus="";
	 String roomName = "", bedName = "",OperationDate="";
	 try
	 {
		 
		   System.out.println("-------------------TemplateDesignerMstUTIL . insertstring -------[ INTO EMR TABLE]--------------");
		  
		
		 
		    if(request.getParameter("strChk")!=null)
			{
		    	strChk = request.getParameter("strChk");		
			}
			else
			{
				strChk = request.getParameter("strChk");
			}
			//System.out.println(" formBean.getStrChk() ----"+formBean.getStrChk());
		    
		    String otReqNo  = strChk.split("\\@")[2];
		    String pacReqNo = strChk.split("\\@")[4];
		    String crNo     = strChk.split("\\@")[1];
		    String addmisNo = strChk.split("\\@")[3];
		    String deptId   = strChk.split("\\@")[7];
		    String seatId   = request.getSession().getAttribute("SEATID").toString();
		    String hospCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();		
		    
		    String proc_name1 = "{call pkg_ot_view.proc_patient_dtls(?,?,?,?,?  ,?)}";
		    
		    dao = new HisDAO("mms","OTRecordEntryTransDAO.getPatientVisitDtls(OTRecordEntryTransVO vo)");	
			 	  
		    
		    procIndex1 = dao.setProcedure(proc_name1);
			
			// set value 	
			dao.setProcInValueByPosition(procIndex1, "modeval",    	"1", 1);
			dao.setProcInValueByPosition(procIndex1, "hosp_code",	  	hospCode, 2);
			dao.setProcInValueByPosition(procIndex1, "deptcode",	  	(deptId == null ? "0" :deptId), 3);					
			dao.setProcInValueByPosition(procIndex1, "puk_no",	   	(crNo == null ? "0" :crNo), 4);	
			dao.setProcOutValue(procIndex1, "err", 			1, 5); 		
			dao.setProcOutValue(procIndex1, "resultset", 	2, 6); 

			// execute procedure

			dao.executeProcedureByPosition(procIndex1);

			// get value
			err = dao.getString(procIndex1, "err");

			if (err == null)
				err = "";

			if (err.equals("")) {

				ws = dao.getWebRowSet(procIndex1, "resultset");

				

			} else {

				throw new Exception(err);
			}
			
			if(ws != null && ws.size() > 0)
			{
							ws.next();
							
							/* 
	                         1. Shakal Sheety [Name]#Suraj [ F Name ]# 55 Yrs [ Age ]# [ Spouse Name ] # General [patient_cat_name ]# Male [Gender ] # OPD [ visit_type ]
	                         2. Visit No
	                         3. Visit Date
	                         4. Pat_Status
	                         5. Dept_Unit_Name
	                         6. Dept_Name
	                         7. Ward_Name
	                         8. Room_Desc
	                         9. Bed_Name
	                         10.Diagnosis
	                          */ 
				
						    patientName 	= 	ws.getString(1).split("\\#")[0]; 
							fatherName 	 	= 	ws.getString(1).split("\\#")[1];  
							dateOfBirth		=	ws.getString(1).split("\\#")[2];  
							spouseName		=	ws.getString(1).split("\\#")[3]; 
							catgName		=	ws.getString(1).split("\\#")[4]; 
							genderName		=	ws.getString(1).split("\\#")[5]; 
							visitType       =	ws.getString(1).split("\\#")[6];
							visitNo         =   ws.getString(2);
							visitDate       =   ws.getString(3);
							patStatus       =   ws.getString(4);
							deptUnitName    =   ws.getString(5);							
							deptName 		=   ws.getString(6);							
							wardName 		=   ws.getString(7);
							roomName        =   ws.getString(8);
							bedName 		=   ws.getString(9);							
		}
			
		String proc_name2 = "{call pkg_ot_view.proc_OT_patient_prev_pac_dtl(?,?,?,?,?  ,?,?,?,?)}";	
		
		procIndex2 = dao.setProcedure(proc_name2);		
			
		// set value 	
		dao.setProcInValueByPosition(procIndex2, "modeval",    	"2", 1);
		dao.setProcInValueByPosition(procIndex2, "hosp_code",	  	hospCode, 2);
		dao.setProcInValueByPosition(procIndex2, "deptcode",	  	deptId, 3);
		dao.setProcInValueByPosition(procIndex2, "pateintcatg", 	"0", 4);			
		dao.setProcInValueByPosition(procIndex2, "crno",	      	(crNo == null ? "0" :crNo), 5);			
		dao.setProcInValueByPosition(procIndex2, "pacreqno",	  	(pacReqNo == null ? "0" :pacReqNo), 6);
		dao.setProcInValueByPosition(procIndex2, "otreqno",	  	(otReqNo == null ? "0" :otReqNo), 7);		
		
		dao.setProcOutValue(procIndex2, "err", 			1, 8); 		
		dao.setProcOutValue(procIndex2, "resultset", 	2, 9); 

		// execute procedure

		dao.executeProcedureByPosition(procIndex2);

		// get value
		err = dao.getString(procIndex2, "err");

		if (err == null)
			err = "";

		if (err.equals("")) {

			ws1 = dao.getWebRowSet(procIndex2, "resultset");

			
		  /*
            1.OT Validation / Operation Date
            2.Operation Done By
            3.Operation List
            4.Department [ Ot Report Enter By /  Ot Report Validated By ]                   
          */
		  if(ws1.next())
		  {
			  //System.out.println("OperationDate--->>"+ws1.getString(1));
			  OperationDate = ws1.getString(1).split("\\/")[1];
		  }

		} else {

			throw new Exception(err);
		}
		
		/*  
		 * Design Header Part Of OT Record Entry
		 * 
		 * */
		
		StringBuffer sb = new StringBuffer("");
		
		sb.append("<table width='100%'>");
		sb.append("<tr>");
		sb.append("<td id='td2' class='tdfonthead' colspan='4'	align='center' width='100%' height='100%' valign='top'>");
		sb.append("<div align='center'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>");
		sb.append("<font color='#000000'><u>Patient Detail</u></font></b></font>");
		sb.append("</div></td>");
		sb.append("</tr>");						
		sb.append("<tr>");				
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>CR	No.</b>	</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right'	width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000'  face='Verdana, Arial, Helvetica, sans-serif' size='2'>");
		sb.append(crNo);
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");		
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>Name.</b></font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'>");				
		sb.append(patientName);
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("</tr>");	
		
		sb.append("<tr>");				
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>Age/Gender.</b>	</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right'	width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000'  face='Verdana, Arial, Helvetica, sans-serif' size='2'>");
		sb.append(dateOfBirth+"/"+genderName);
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");		
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>Patient Category.</b></font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'>");				
		sb.append(catgName);
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("</tr>");	
		
		sb.append("<tr>");				
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>Department/Unit.</b>	</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right'	width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000'  face='Verdana, Arial, Helvetica, sans-serif' size='2'>");
		sb.append(deptName+"/"+deptUnitName);
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");		
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'></font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'>");				
		//sb.append();
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("</tr>");	
		
		sb.append("<tr>");				
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>Father Name.</b>	</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right'	width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000'  face='Verdana, Arial, Helvetica, sans-serif' size='2'>");
		sb.append(fatherName);
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");		
		sb.append("<td id='td3' class='tdfonthead' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='right'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'><b>Spouse Name.</b></font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("<td id='td4' class='tdfont' colspan='1' align='right' width='25%' valign='top'>");
		sb.append("<div align='left'>");
		sb.append("<font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'>");				
		sb.append(spouseName);
		sb.append("</font>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("</tr>");				
		sb.append("</table>");
		
		 String finalTemplateString = sb.toString()+""+res;
		
		
		 byte[] bytesEncoded = Base64.encodeBase64(finalTemplateString.getBytes());
		 System.out.println("1.-Encoded value is --->>> " + new String(bytesEncoded));
		 byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);
		 System.out.println("2.-Decoded value is --->>>" + new String(valueDecoded));
			
         
		String dtl=deptName+"#"+crNo+"#"+patientName+"#-#-#"+dateOfBirth+"#"+genderName+"#"+catgName+"#"+fatherName+"#"+spouseName+"#"+wardName+"#"+bedName+"#"+OperationDate;
	     
		System.out.println("3.-Dtl--->>"+dtl);
		
		/* hott_transaction_mst [ Transaction Id ]
		 * 1 - PAC RECORD ENTRY
		 * 2 - OPERATION RECORD ENTRY
		 * 3 - ANESTHESIA RECORD ENTRY
		 * 4 - POST OPERATIVE CARE UNIT
		 * 5 - OT LIST RAISING
		 * 6 - Consent Approval
		 */		
		
		//modeval ,trans_code , dept_code , temp_id , cr_no , ot_req_no 
		
			System.out.println("request.getParameter(transactionId)--->>"+request.getParameter("transactionId"));
			System.out.println("deptId--->>"+deptId);
			System.out.println("crNo--->>"+crNo);
			System.out.println("templateId--->>"+request.getParameter("templateId"));
			System.out.println("otReqNo--->>"+otReqNo);
			System.out.println("new String(bytesEncoded)--->>"+new String(bytesEncoded));
			System.out.println("menuId--->>"+request.getParameter("menuId"));
		
	   
		
		    String strProcName3 = "{call PKG_OT_DML.dml_save_ot_record_pdf (?,?,?,?,?  ,?,?,?,?,?, ?,?)}"; // 12 Variables
		    procIndex3 = dao.setProcedure(strProcName3);
					
			dao.setProcInValueByPosition(procIndex3, "modval",       "1",1);
			dao.setProcInValueByPosition(procIndex3, "dept_code",    deptId,2);
			dao.setProcInValueByPosition(procIndex3, "trans_code",   request.getParameter("transactionId"),3);
			dao.setProcInValueByPosition(procIndex3, "hosp_code",    hospCode,4);
			dao.setProcInValueByPosition(procIndex3, "menu_code",    request.getParameter("menuId"),5);			
			dao.setProcInValueByPosition(procIndex3, "template_id",  request.getParameter("templateId"),6);
			dao.setProcInValueByPosition(procIndex3, "cr_no",       (crNo == null ? "0" : crNo),7);	
			dao.setProcInValueByPosition(procIndex3, "ot_req_no",   (otReqNo == null ? "0" : otReqNo),8);	
			dao.setProcInValueByPosition(procIndex3, "temp_pdf",    new String(bytesEncoded),9);	
			dao.setProcInValueByPosition(procIndex3, "seat_id",     seatId,10);	
			dao.setProcInValueByPosition(procIndex3, "pat_dtl",     dtl,11);	
			dao.setProcOutValue(procIndex3, "err",        1,12); // 24
			dao.executeProcedureByPosition(procIndex3);	
			
			System.out.println("-------------------TemplateDesignerMstUTIL . insertstring -------[ PKG_OT_DML.dml_save_ot_record_pdf -------[ END ]-------");
			
	
			
				
	 }   	 
	 catch (Exception e1) 
	 {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	  }
	 
		finally{
			
			
			if (dao != null) {
				dao.free();
				dao = null;
			}
			 
			
		}
 }
 

 public static String getFinalTemplateStringforPDF_old1(String templateId, String mode,Map templateValueMap,List lstTdObject,List lstControlobjects) {	
		String resultString="<table class='table' width='100%'>";	
		int i,j,k,l;
		try{
			
		if(lstTdObject!=null && lstTdObject.size()>0){
		OTTemplateControlPropertyVO maxObj=(OTTemplateControlPropertyVO)lstTdObject.get(lstTdObject.size()-1);
		int maxRowLocation=0;
		int maxColLocation=0;
		
		for(i=0;i<lstTdObject.size();i++){
			OTTemplateControlPropertyVO Obj=(OTTemplateControlPropertyVO)lstTdObject.get(i);
			if(maxColLocation<Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[1])){
				maxColLocation=Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[1]);
			}
			if(maxRowLocation<Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[0])){
				maxRowLocation=Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[0]);
			}
			
		}
		
		//System.out.println("templateValueMap   ::"+templateValueMap);
		
	    if(mode.equals(OperationTheatreConfig.OTTEMPLATE_REPORT_MODE) && templateValueMap!=null && !templateValueMap.containsKey(templateId))
	    	return "";
	    

		
		
		String  width=100/(maxColLocation+1)+"%";
		//System.out.println("width of cols-----" + width);
		for(i=1;i<= maxRowLocation;i++){
			resultString+="<tr>";
			for(j=0;j<=maxColLocation;j++){
				String loc=i +"*"+ j;
				if(lstTdObject!=null && lstTdObject.size()>0){
					for(k=0;k<lstTdObject.size();k++){					
						OTTemplateControlPropertyVO tdObj=(OTTemplateControlPropertyVO)lstTdObject.get(k);
						if(tdObj.getLocation().equals(loc)){
							resultString+="<td >";
							if(lstControlobjects!=null && lstControlobjects.size()>0){
								
								for(l=0;l<lstControlobjects.size();l++){
									
									OTTemplateControlPropertyVO objControl=(OTTemplateControlPropertyVO)lstControlobjects.get(l);
									String idNamedtl=objControl.getControlId()+"^"+i+j+"^"+templateId+"^"+objControl.getParaId()+"^"+mode ;
									
									if(objControl.getLocation().equals(loc)){
										resultString+="<div style='  white-space: nowrap;' "+objControl.getDivHtmlString()+"  " +objControl.getDivStyleString() +">";
										String str=objControl.getControlHtmlTag();
										
										if(str.contains("DIVVIS")){
											str=str.replace("DIVVIS", "DIVVIS_"+objControl.getControlId()+"^"+i+j);
										}
										
										String id="'PARAMETER_"+idNamedtl +"'";
										String name="'parameter_"+idNamedtl+"'";
										String addedstr="name="+name+" id="+id;
										str=str.replace("@@",idNamedtl );
										if(objControl.getDocumentDataAttached()!=null && !objControl.getDocumentDataAttached().equals(""))
											addedstr+=" ondblclick='openDocumentAttached(\"PARAMETER_"+objControl.getControlId()+"^"+i+j+"\",\""+templateId+"\");' " ;
										//System.out.println("id -" + idNamedtl+"'");
										if(objControl.getIsUsedVisDep().equals("1")){
											if(objControl.getControlName().equals("combo")){
												if(str.toLowerCase().contains("onchange"))
													str=str.replace("onchange='", " onchange='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j+"^"+templateId +"^"+objControl.getParaId()+"^"+mode+"\",\""+objControl.getControlName()+"\");");
												else
													addedstr+=" onchange='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode +"\",\""+objControl.getControlName()+"\");'";
											}				 
											if( objControl.getControlName().toUpperCase().equals("RADIO") || objControl.getControlName().toUpperCase().equals("CHECKBOX") || objControl.getControlName().toUpperCase().equals("YESNO")){
												if(str.toLowerCase().contains("onclick"))
													str=str.replace("onclick='", " onclick='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode +"\" ,\""+objControl.getControlName()+"\");");
												else
													addedstr+=" onclick='showhideDependent(\"PARANAMEFORMAP_"+idNamedtl+"\" ,\""+objControl.getControlName()+"\");'";
											}
										}
										if(objControl.getControlName().toUpperCase().equals("TEXT")){
											String strreadonly="";
											if(objControl.getFormula()!=null && !objControl.getFormula().equals("-1$")){
												strreadonly="readonly";
											}
											
												if(str.toLowerCase().contains("onblur"))
													str=str.replace("onblur='",  "  "+strreadonly +" onblur='applyFormula(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"\" ,\""+objControl.getControlName()+"\");");
												else
													addedstr+="  "+strreadonly + " onblur='applyFormula(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"\" ,\""+objControl.getControlName()+"\");'";
												
												
										}
										
										
										//str=str.replace("@",addedstr);
										str=str.replace("@","");
										if(objControl.getScriptAttached()!=null)
											str+=objControl.getScriptAttached();
										if(str.contains("TEXTAREAID")){
											str=str.replace("TEXTAREAID", "PARAMETER_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode);
										}
									
										resultString+=str;										
										resultString+="</div>";
										// for document attached to control								
										if(resultString.contains("&nbsp;")) {
											resultString=resultString.replaceAll("&nbsp;", "");
										}
									}//end if objControl
								}// end for l
							}// end if lstControlobjects
							resultString+="</td>";
						}// end if tdObj
						
					} //end for k
				}//end if lstTdObject
				
			} //end for j
			resultString+="</tr>";
			
		}// end for i
		
		
		resultString+="</table>";
		resultString +="<script language='javaScript'> showhideDependentCheckOnLoad(\""+templateId+"\"); applyFormula();</script>";
		
		}// end if for lstTdObject null check
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultString;
	}
 
 public static String getFinalTemplateStringforPDF(String templateId, String mode,Map templateValueMap,List lstTdObject,List lstControlobjects) 
 {	
	 
		String resultString="";	
		int i,j,k,l;
		try{
			
		if(lstTdObject!=null && lstTdObject.size()>0)
		{
			OTTemplateControlPropertyVO maxObj=(OTTemplateControlPropertyVO)lstTdObject.get(lstTdObject.size()-1);
			int maxRowLocation=0;
			int maxColLocation=0;
			
			for(i=0;i<lstTdObject.size();i++)
			{
				OTTemplateControlPropertyVO Obj=(OTTemplateControlPropertyVO)lstTdObject.get(i);
				if(maxColLocation<Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[1])){
					maxColLocation=Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[1]);
				}
				if(maxRowLocation<Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[0])){
					maxRowLocation=Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[0]);
				}
				
			}
			
			//System.out.println("templateValueMap   ::"+templateValueMap);
			
		    if(mode.equals(OperationTheatreConfig.OTTEMPLATE_REPORT_MODE) && templateValueMap!=null && !templateValueMap.containsKey(templateId))
		    	return "";
		    
			resultString+="<table class='table-borderless table-condensed'  border='0' width='100%' id='table2' cellpadding='0' cellspacing='1' align='left'>";
			
			
			String  width=100/(maxColLocation+1)+"%";
			//System.out.println("width of cols-----" + width);
			for(i=1;i<= maxRowLocation;i++)
			{
				resultString+="<tr>";
				for(j=0;j<=maxColLocation;j++)
				{
					String loc=i +"*"+ j;
					if(lstTdObject!=null && lstTdObject.size()>0)
					{
						for(k=0;k<lstTdObject.size();k++)
						{					
							OTTemplateControlPropertyVO tdObj=(OTTemplateControlPropertyVO)lstTdObject.get(k);
							if(tdObj.getLocation().equals(loc))
							{
								resultString+="<td "+ tdObj.getHtmlCode()+" height='10%' width='"+width+"' >";
								if(lstControlobjects!=null && lstControlobjects.size()>0){
									
									for(l=0;l<lstControlobjects.size();l++)
									{
										
										OTTemplateControlPropertyVO objControl=(OTTemplateControlPropertyVO)lstControlobjects.get(l);
										String idNamedtl=objControl.getControlId()+"^"+i+j+"^"+templateId+"^"+objControl.getParaId()+"^"+mode ;
										
										if(objControl.getLocation().equals(loc))
										{
											
											if(objControl.getHtmlCode()!=null && !objControl.getHtmlCode().equals("") &&  objControl.getHtmlCode().contains("cols")) 
											{
												    String maxl=objControl.getHtmlCode().split(" ")[0];
												
												    int maxlenght=0;
												    maxl=maxl.trim();
												
													maxlenght=Integer.parseInt(maxl.substring(6, maxl.length()-1) );
											
													
													
												String htmlstr=objControl.getControlHtmlTag().split("@>")[1].replace("</label></font>", "");
												
												if( objControl.getDivHtmlString().contains("left") ) 
												{
													resultString+="<div class='wordwrap' "+objControl.getDivHtmlString()+"  " +objControl.getDivStyleString() +">";	
												}
												else 
												{
													
													
													resultString+="<div class='whitespace' "+objControl.getDivHtmlString()+"  " +objControl.getDivStyleString() +">";
												}
											}
											else 
											{
																								
												resultString+="<div class='whitespace' "+objControl.getDivHtmlString()+"  " +objControl.getDivStyleString() +">";
											}
											
											// white-space:nowrap; 
											
											//System.out.println("-Q---objControl.getControlHtmlTag()--"+objControl.getControlHtmlTag());
											
											String str="";
											if(objControl.getControlHtmlTag().contains(">>"))
											{
												String s=objControl.getControlHtmlTag().replaceAll(">>", "<br>");
												str+=s;
											}
											else 
											{
												str=objControl.getControlHtmlTag();
												
											}
											
											
											
											if(str.contains("DIVVIS")){
												str=str.replace("DIVVIS", "DIVVIS_"+objControl.getControlId()+"^"+i+j);
											}
											
											String id="'PARAMETER_"+idNamedtl +"'";
											String name="'parameter_"+idNamedtl+"'";
											String addedstr="name="+name+" id="+id;
											str=str.replace("@@",idNamedtl );
											if(objControl.getDocumentDataAttached()!=null && !objControl.getDocumentDataAttached().equals(""))
												addedstr+=" ondblclick='openDocumentAttached(\"PARAMETER_"+objControl.getControlId()+"^"+i+j+"\",\""+templateId+"\");' " ;
											//System.out.println("id -" + idNamedtl+"'");
											if(objControl.getIsUsedVisDep().equals("1")){
												if(objControl.getControlName().equals("combo")){
													if(str.toLowerCase().contains("onchange"))
														str=str.replace("onchange='", " onchange='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j+"^"+templateId +"^"+objControl.getParaId()+"^"+mode+"\",\""+objControl.getControlName()+"\");");
													else
														addedstr+=" onchange='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode +"\",\""+objControl.getControlName()+"\");'";
												}				 
												if( objControl.getControlName().toUpperCase().equals("RADIO") || objControl.getControlName().toUpperCase().equals("CHECKBOX") || objControl.getControlName().toUpperCase().equals("YESNO")){
													if(str.toLowerCase().contains("onclick"))
														str=str.replace("onclick='", " onclick='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode +"\" ,\""+objControl.getControlName()+"\");");
													else
														addedstr+=" onclick='showhideDependent(\"PARANAMEFORMAP_"+idNamedtl+"\" ,\""+objControl.getControlName()+"\");'";
												}
											}
											if(objControl.getControlName().toUpperCase().equals("TEXT")){
												String strreadonly="";
												if(objControl.getFormula()!=null && !objControl.getFormula().equals("-1$")){
													strreadonly="readonly";
												}
												
													if(str.toLowerCase().contains("onblur"))
														str=str.replace("onblur='",  "  "+strreadonly +" onblur='applyFormula(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"\" ,\""+objControl.getControlName()+"\");");
													else
														addedstr+="  "+strreadonly + " onblur='applyFormula(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"\" ,\""+objControl.getControlName()+"\");'";
													
													
											}
											
										
											//System.out.println("-A---str--"+str);
											
											str=str.replace("@","");
											
											//System.out.println("-B---str--"+str);
											
											str=str.replace("label","text");
											if(objControl.getScriptAttached()!=null)
												str+=objControl.getScriptAttached();
											if(str.contains("TEXTAREAID")){
												str=str.replace("TEXTAREAID", "PARAMETER_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode);
											}
										
											resultString+=str;										
											resultString+="</div>";
											if(resultString.contains("&nbsp;")) {
												resultString=resultString.replaceAll("&nbsp;", "");
												
											}
											
											// for document attached to control								
											
										}//end if objControl
									}// end for l
								}// end if lstControlobjects
								resultString+="</td>";
							}// end if tdObj
							
						} //end for k
					}//end if lstTdObject
					
				} //end for j
				resultString+="</tr>";
				
			}// end for i
			
			
			resultString+="</table><br><br><br>";
			resultString +="<script language='javaScript'> showhideDependentCheckOnLoad(\""+templateId+"\"); applyFormula();</script>";
			resultString=resultString.replaceAll("<font", "<font size='3 px'");
		  }// end if for lstTdObject null check
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultString;
	}
 
 
 public static String getFinalTemplateString(String templateId, String mode,Map templateValueMap,List lstTdObject,List lstControlobjects) 
 {	
		String resultString="";	
		int i,j,k,l;
		try
		{
			
			System.out.println("-------------------TemplateDesignerMstUTIL . getFinalTemplateString ---------------------");
				
			if(lstTdObject!=null && lstTdObject.size()>0)
			{
				OTTemplateControlPropertyVO maxObj=(OTTemplateControlPropertyVO)lstTdObject.get(lstTdObject.size()-1);
				int maxRowLocation=0;
				int maxColLocation=0;
				
				for(i=0;i<lstTdObject.size();i++)
				{
					OTTemplateControlPropertyVO Obj=(OTTemplateControlPropertyVO)lstTdObject.get(i);
					if(maxColLocation<Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[1])){
						maxColLocation=Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[1]);
					}
					if(maxRowLocation<Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[0])){
						maxRowLocation=Integer.parseInt(Obj.getLocation().replace("*", "#").split("#")[0]);
					}
					
				}
				
				//System.out.println("templateValueMap   ::"+templateValueMap);
				
			    if(mode.equals(OperationTheatreConfig.OTTEMPLATE_REPORT_MODE) && templateValueMap!=null && !templateValueMap.containsKey(templateId))
			    	return "";
			    
				resultString+="<table class='table' width='100%' id='table2' cellpadding='0' cellspacing='1' align='left'>";
				
				
				String  width=100/(maxColLocation+1)+"%";
				//System.out.println("width of cols-----" + width);
				for(i=1;i<= maxRowLocation;i++)
				{
					resultString+="<tr>";
					for(j=0;j<=maxColLocation;j++)
					{
						String loc=i +"*"+ j;
						if(lstTdObject!=null && lstTdObject.size()>0)
						{
							for(k=0;k<lstTdObject.size();k++)
							{					
								OTTemplateControlPropertyVO tdObj=(OTTemplateControlPropertyVO)lstTdObject.get(k);
								if(tdObj.getLocation().equals(loc))
								{
									resultString+="<td "+ tdObj.getHtmlCode()+" hieght='10%' width='"+width+"' >";
									if(lstControlobjects!=null && lstControlobjects.size()>0){
										
										for(l=0;l<lstControlobjects.size();l++)
										{
											
											OTTemplateControlPropertyVO objControl=(OTTemplateControlPropertyVO)lstControlobjects.get(l);
											String idNamedtl=objControl.getControlId()+"^"+i+j+"^"+templateId+"^"+objControl.getParaId()+"^"+mode ;
											
											if(objControl.getLocation().equals(loc))
											{
												resultString+="<div "+objControl.getDivHtmlString()+"  " +objControl.getDivStyleString() +">";
												String str=objControl.getControlHtmlTag();
												
												if(str.contains("DIVVIS"))
												{
													str=str.replace("DIVVIS", "DIVVIS_"+objControl.getControlId()+"^"+i+j);
												}
												
												String id="'PARAMETER_"+idNamedtl +"'";
												String name="'parameter_"+idNamedtl+"'";
												String addedstr="name="+name+" id="+id;
												str=str.replace("@@",idNamedtl );
												if(objControl.getDocumentDataAttached()!=null && !objControl.getDocumentDataAttached().equals(""))
													addedstr+=" ondblclick='openDocumentAttached(\"PARAMETER_"+objControl.getControlId()+"^"+i+j+"\",\""+templateId+"\");' " ;
												//System.out.println("id -" + idNamedtl+"'");
												if(objControl.getIsUsedVisDep().equals("1"))
												{
													if(objControl.getControlName().equals("combo"))
													{
														if(str.toLowerCase().contains("onchange"))
															str=str.replace("onchange='", " onchange='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j+"^"+templateId +"^"+objControl.getParaId()+"^"+mode+"\",\""+objControl.getControlName()+"\");");
														else
															addedstr+=" onchange='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode +"\",\""+objControl.getControlName()+"\");'";
													}	
													
													
													if( objControl.getControlName().toUpperCase().equals("RADIO") || objControl.getControlName().toUpperCase().equals("CHECKBOX") || objControl.getControlName().toUpperCase().equals("YESNO"))
													{
														if(str.toLowerCase().contains("onclick"))
															str=str.replace("onclick='", " onclick='showhideDependent(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode +"\" ,\""+objControl.getControlName()+"\");");
														else
															addedstr+=" onclick='showhideDependent(\"PARAMETER_"+idNamedtl+"\" ,\""+objControl.getControlName()+"\");'";
													}
												}
												if(objControl.getControlName().toUpperCase().equals("TEXT"))
												{
													String strreadonly="";
													if(objControl.getFormula()!=null && !objControl.getFormula().equals("-1$"))
													{
														strreadonly="readonly";
													}
													
														if(str.toLowerCase().contains("onblur"))
															str=str.replace("onblur='",  "  "+strreadonly +" onblur='applyFormula(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"\" ,\""+objControl.getControlName()+"\");");
														else
															addedstr+="  "+strreadonly + " onblur='applyFormula(\"PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"\" ,\""+objControl.getControlName()+"\");'";
														
														
												}
												
												
												str=str.replace("@",addedstr);
												if(objControl.getScriptAttached()!=null)
													str+=objControl.getScriptAttached();
												if(str.contains("TEXTAREAID")){
													str=str.replace("TEXTAREAID", "PARAMETER_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode);
												}
											//	if(objControl.getIsInputType().equals("1")){
												//	str+="<input type='hidden' name='paraId_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"' id='PARAID_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"' value='"+ objControl.getParaId()+"'>";
												//}
												
														/*
														 * System.out.println("nameforMap-------" + objControl.getNameForMap());
														 * System.out.println("getParentControlName-------" +
														 * objControl.getParentControlName());
														 * System.out.println("getParentControlValue-------" +
														 * objControl.getParentControlValue());
														 * System.out.println("getControlName-------" +
														 * objControl.getControlName());
														 * System.out.println("getControlId-------" +
														 * objControl.getControlId()); System.out.println("id-------" + id);
														 */
												if(objControl.getNameForMap()!=null && !objControl.getNameForMap().equals("-1") ){
													str+="<input type='hidden' name='paraNameforMap'     id='PARANAMEFORMAP_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"' value='"+ id+"'>";
													str+="<input type='hidden' name='parentControlName'  id='PARENTCONTROLNAME_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"' value='"+ objControl.getParentControlName()+"'>";
													str+="<input type='hidden' name='parentControlValue' id='PARENTCONTROLVALUE_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"' value='"+ objControl.getParentControlValue()+"'>";
													str+="<input type='hidden' name='paraControlName'    id='PARACONTROLNAME_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"' value='"+ objControl.getControlName()+"'>";
												}
												
												if(objControl.getLabelName()!=null ){
													str+="<input type='hidden' name='labelName' id='LABELNAME_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"' value='"+ objControl.getLabelName()+"'>";
												}
												if(objControl.getFormula()!=null && !objControl.getFormula().equals("-1$")){
													str+="<input type='hidden' name='formula' id='FORMULA_"+objControl.getControlId()+"^"+i+j +"^"+templateId+"^"+objControl.getParaId()+"^"+mode+"' value='"+ objControl.getFormula()+"'>";
												}
												resultString+=str;										
												resultString+="</div>";
												// for document attached to control								
												
											}//end if objControl
										}// end for l
									}// end if lstControlobjects
									resultString+="</td>";
								}// end if tdObj
								
							} //end for k
						}//end if lstTdObject
					
				} //end for j
				resultString+="</tr>";
				
			}// end for i
			
			
			resultString+="</table>";
			resultString +="<script language='javaScript'> showhideDependentCheckOnLoad(\""+templateId+"\"); applyFormula();</script>";
			
			}// end if for lstTdObject null check
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultString;
	}
 
 
 

public List<TemplateDesignerVO> populateTemplateParameterArray(String templateTDParaId,List  lstTdObject,List  lstControlobjects) 
{
	
	String templateId,templateName,hospitalCode,categoryId ,subCategoryId,seatId,strMode ,isValid ;
	
	List<TemplateDesignerVO>otTemplateParameterArray=new ArrayList<TemplateDesignerVO>();
	TemplateDesignerVO templateDesignerVO;
	
	
	String allPropertyValues="";
	String allLocation="";
	
	
	if(lstTdObject!=null && lstTdObject.size()>0 && lstControlobjects!=null && lstControlobjects.size()>0){
		
		templateDesignerVO= new TemplateDesignerVO();
		for(int i=0;i<lstTdObject.size();i++)
		{
			
			OTTemplateControlPropertyVO tdObj=(OTTemplateControlPropertyVO)lstTdObject.get(i);
			if(i==0){
				templateDesignerVO.setParaID(templateTDParaId);
				templateDesignerVO.setControlID(tdObj.getControlId());
				templateDesignerVO.setAllPropertyIDs(tdObj.getAllPropertyIds());						
			}
			allPropertyValues+=tdObj.getAllPropertyValues()+"^";
			allLocation+=tdObj.getLocation()+"^";
		}
		allPropertyValues=allPropertyValues.substring(0,allPropertyValues.length()-1);
		allLocation=allLocation.substring(0,allLocation.length()-1);
		
		if((allPropertyValues).length()<=4000){
			String finalpropertyString=allPropertyValues;
			templateDesignerVO.setAllPropertyValues(finalpropertyString);	
			templateDesignerVO.setAllPropertyValues2(null);
			templateDesignerVO.setAllPropertyValues3(null);
			templateDesignerVO.setParaLocation(allLocation);
		}	
		else{
			String finalpropertyString1=null;
			String finalpropertyString2=null;
			String finalpropertyString3=null;
			String str=allPropertyValues;
			float div=(str.length()/4000);
			if(div>1){
				finalpropertyString1=str.substring(0, 4000);
				finalpropertyString2=str.substring(4001, str.length());																		
			}
			if(div>2){
				finalpropertyString1=str.substring(0, 4000);
				finalpropertyString2=str.substring(4001, 8000);
				finalpropertyString3=str.substring(8001, str.length());						
			}
			
			templateDesignerVO.setAllPropertyValues(finalpropertyString1);	
			templateDesignerVO.setAllPropertyValues2(finalpropertyString2);
			templateDesignerVO.setAllPropertyValues3(finalpropertyString3);
			templateDesignerVO.setParaLocation(allLocation);
			
		}
		otTemplateParameterArray.add(templateDesignerVO);
							
		
		for(int i=0;i<lstControlobjects.size();i++)
		{
			templateDesignerVO= new TemplateDesignerVO();
			OTTemplateControlPropertyVO controlObj=(OTTemplateControlPropertyVO)lstControlobjects.get(i);
			
			templateDesignerVO.setParaID(controlObj.getParaId());
			templateDesignerVO.setControlID(controlObj.getControlId());
			templateDesignerVO.setAllPropertyIDs(controlObj.getAllPropertyIds());
			templateDesignerVO.setAllPropertyValues(controlObj.getAllPropertyValues());
			//System.out.println("prop Value---" + controlObj.getAllPropertyValues());
			templateDesignerVO.setAllPropertyValues2(null);
			templateDesignerVO.setAllPropertyValues3(null);
			templateDesignerVO.setParaLocation(controlObj.getLocation());
			otTemplateParameterArray.add(templateDesignerVO);
		}				
	}
	else{	
	}
	
	return otTemplateParameterArray;
	
	 
}

public boolean createHTMLDocumentFiles(HttpServletRequest request, TemplateDesignerMstFB fb)
{
	boolean success=false;
	Map mp= (Map)request.getSession().getAttribute(OperationTheatreConfig.TEMPLATEHTMLMAP);
	
        
  try{
	  
	 if(mp!=null && mp.size()>0){ 
	  String strDirectoy ="c:\\OT_HTML_DOC\\" + fb.getTemplateId();
	
	// Create one directory
	 success = (new File(strDirectoy)).mkdir();
	if (success) {
		//System.out.println("Directory: " + strDirectoy + " created");
	}	
	else
	{
		//System.out.println("Directory: " + strDirectoy + " already exist!");
	}
		Set s=mp.entrySet();
		Iterator it=s.iterator();
		 while(it.hasNext())
	    {
			// key=value separator this by Map.Entry to get key and value
	        Map.Entry m =(Map.Entry)it.next();
	        // getKey is used to get key of Map	        
	        String  key=(String)m.getKey();
	        String filename=strDirectoy+"\\"+key+".html";
	        File file = new File(filename); 
	        success = file.createNewFile();
	    	// Create file if it does not exist 
	    	 if (success) { 
	    		 // File did not exist and was created 
	    		 //System.out.println("File: " + filename + " created!");
	    	 } 
	    	 else { 
	    		// System.out.println("File: " + filename + " already exist!");			 
	    	 }
	    	 DataOutputStream outs = new DataOutputStream(new FileOutputStream(file,false));
	    	 String data=(String)  m.getValue();
	    	 outs.write(data.getBytes());
	    	 outs.close(); 
	    } 
	 }
	 else
	 {
		 success=true;
	 }
	 } catch (IOException e) { 
		 e.printStackTrace();
		 success=false;
	 } 
	 return success;
	
}


public static void initModify(HttpServletRequest request, TemplateDesignerMstFB fb) {
	Status status= new Status();
	UserVO uservo=ControllerUTIL.getUserVO(request);
	
	ResultSet rs_controldtl=null;
	ResultSet rs_propertydtl=null;
	ResultSet rs_propertyvaluedtl=null;
	ResultSet rs_propertydefaultvaluedtl=null;
	ResultSet rs_templateCategory=null;
	ResultSet rs_templateSubCategory=null;
	ResultSet rs_templateMstData=null;
	ResultSet rs_templateCtrlMstData=null;
	ResultSet rs_docType=null;
	List lstDocType= new ArrayList();
	
	List lstcontroldtl= new ArrayList();
	List lstpropertydtl= new ArrayList();
	List lstpropertyvaluedtl= new ArrayList();
	List lstdefaultcontrolproperty= new ArrayList();
	List lstTemplateCategory= new ArrayList();
	List lstTemplateSubCategory= new ArrayList();
	List lstTemplateCtrlMstData= new ArrayList();
	
	
	CallableStatement csmt=null;
	Connection conn = null;
	HttpSession sess= request.getSession(); 
	sess.removeAttribute(OperationTheatreConfig.LISTCONTROL);
	sess.removeAttribute(OperationTheatreConfig.LISTPROPERTYVO);
	sess.removeAttribute(OperationTheatreConfig.LISTPROPERTYVALUEVO);
	sess.removeAttribute(OperationTheatreConfig.LIST_TEMPLATE_CATEGORY);
	sess.removeAttribute(OperationTheatreConfig.LIST_TEMPLATE_SUBCATEGORY);
	sess.removeAttribute(OperationTheatreConfig.LIST_DOC_TYPE);
	HisDAO hisDAO_p=null;
	final int nProcedureIndex1;
	final int nProcedureIndex2;
	final int nProcedureIndex3;
	final int nProcedureIndex4;
	final int nProcedureIndex5;
	final int nProcedureIndex6;
	final int nProcedureIndex7;
	final int nProcedureIndex8;
	final int nProcedureIndex9;
	final int nProcedureIndex10;
	WebRowSet ws = null;
	String err = "";
	
	//exec PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN('10','20','37913',':x', ':y');
	//final String strProcName = "{call AHIS_OT_UTILITY.GETTEMPDESIGMODIFYINITIALIZE(?,?,?,?,?,?,?,?,?,?,?,?,?)}"; 
	final String strProcName = "{call PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN(?,?,?,?,?)}";
	try
	{
		
		System.out.println("-------------------TemplateDesignerMstUTIL . initModify -----------START----------");
		
		
		
		System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 1]------------");
		
   	
   		hisDAO_p = new HisDAO("operationTheatre", "TemplateDesignerMstUTIL");
   		
   		nProcedureIndex1 = hisDAO_p.setProcedure(strProcName);   		  	
   		
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex1, "modeval",					"1", 1);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex1, "templateid",					fb.getTemplateId(),2);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex1, "hospitalCode",				uservo.getHospitalCode(),3);   		
   		hisDAO_p.setProcOutValue(nProcedureIndex1, "err", 					    1, 4); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex1, "resultset", 				2, 5); // 1 for
   		/* Executing Procedure */
		hisDAO_p.executeProcedureByPosition(nProcedureIndex1);
   	
		// get value
		err = hisDAO_p.getString(nProcedureIndex1, "err");

		if (err == null)
			err = "";

		if (err.equals("")) {

			rs_controldtl = hisDAO_p.getWebRowSet(nProcedureIndex1, "resultset");			

		} else {

			throw new Exception(err);
		}
   		
		System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 2]------------");
		
        nProcedureIndex2 = hisDAO_p.setProcedure(strProcName);   	  	
   		
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex2, "modeval",					"2", 1);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex2, "templateid",					fb.getTemplateId(),2);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex2, "hospitalCode",				uservo.getHospitalCode(),3);   		
   		hisDAO_p.setProcOutValue(nProcedureIndex2, "err", 					    1, 4); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex2, "resultset", 				2, 5); // 1 for
   		/* Executing Procedure */
		hisDAO_p.executeProcedureByPosition(nProcedureIndex2);
   	
		// get value
		err = hisDAO_p.getString(nProcedureIndex2, "err");

		if (err == null)
			err = "";

		if (err.equals("")) {

			rs_propertydtl = hisDAO_p.getWebRowSet(nProcedureIndex2, "resultset");			

		} else {

			throw new Exception(err);
		}
		
		/*
		
		System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 3]------------");
	    nProcedureIndex3 = hisDAO_p.setProcedure(strProcName);   	  	
   		
   		hisDAO_p.setProcInValue(nProcedureIndex3, "modeval",					"3", 1);
   		hisDAO_p.setProcInValue(nProcedureIndex3, "templateid",					fb.getTemplateId(),2);
   		hisDAO_p.setProcInValue(nProcedureIndex3, "hospitalCode",				uservo.getHospitalCode(),3);   		
   		hisDAO_p.setProcOutValue(nProcedureIndex3, "err", 					    1, 4); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex3, "resultset", 				2, 5); // 1 for
   		
		hisDAO_p.executeProcedureByPosition(nProcedureIndex3);
   	
		// get value
		err = hisDAO_p.getString(nProcedureIndex3, "err");

		if (err == null)
			err = "";

		if (err.equals("")) {

			rs_propertydtl = hisDAO_p.getWebRowSet(nProcedureIndex3, "resultset");			

		} else {

			throw new Exception(err);
		}
		*/
		
		System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 4]------------");
		
        nProcedureIndex4 = hisDAO_p.setProcedure(strProcName);   	  	
   		
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex4, "modeval",					"4", 1);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex4, "templateid",					fb.getTemplateId(),2);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex4, "hospitalCode",				uservo.getHospitalCode(),3);   		
   		hisDAO_p.setProcOutValue(nProcedureIndex4, "err", 					    1, 4); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex4, "resultset", 				2, 5); // 1 for
   		/* Executing Procedure */
		hisDAO_p.executeProcedureByPosition(nProcedureIndex4);
   	
		// get value
		err = hisDAO_p.getString(nProcedureIndex4, "err");

		if (err == null)
			err = "";

		if (err.equals("")) {

			rs_propertyvaluedtl = hisDAO_p.getWebRowSet(nProcedureIndex4, "resultset");			

		} else {

			throw new Exception(err);
		}
		
		System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 5]------------");
		
        nProcedureIndex5 = hisDAO_p.setProcedure(strProcName);   	  	
   		
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex5, "modeval",					"5", 1);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex5, "templateid",					fb.getTemplateId(),2);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex5, "hospitalCode",				uservo.getHospitalCode(),3);   		
   		hisDAO_p.setProcOutValue(nProcedureIndex5, "err", 					    1, 4); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex5, "resultset", 				2, 5); // 1 for
   		/* Executing Procedure */
		hisDAO_p.executeProcedureByPosition(nProcedureIndex5);
   	
		// get value
		err = hisDAO_p.getString(nProcedureIndex5, "err");

		if (err == null)
			err = "";

		if (err.equals("")) {

			rs_propertydefaultvaluedtl = hisDAO_p.getWebRowSet(nProcedureIndex5, "resultset");			

		} else {

			throw new Exception(err);
		}
		
		System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 6]------------");
		
		    nProcedureIndex6 = hisDAO_p.setProcedure(strProcName);   	  	
	   		
	   		hisDAO_p.setProcInValueByPosition(nProcedureIndex6, "modeval",					"6", 1);
	   		hisDAO_p.setProcInValueByPosition(nProcedureIndex6, "templateid",					fb.getTemplateId(),2);
	   		hisDAO_p.setProcInValueByPosition(nProcedureIndex6, "hospitalCode",				uservo.getHospitalCode(),3);   		
	   		hisDAO_p.setProcOutValue(nProcedureIndex6, "err", 					    1, 4); // 1 for
	   		hisDAO_p.setProcOutValue(nProcedureIndex6, "resultset", 				2, 5); // 1 for
	   		/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex6);
	   	
			// get value
			err = hisDAO_p.getString(nProcedureIndex6, "err");

			if (err == null)
				err = "";

			if (err.equals("")) {

				rs_templateCategory = hisDAO_p.getWebRowSet(nProcedureIndex6, "resultset");			

			} else {

				throw new Exception(err);
			}
			
			
			System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 7]------------");
			
			 nProcedureIndex7 = hisDAO_p.setProcedure(strProcName);   	  	
		   		
		   		hisDAO_p.setProcInValueByPosition(nProcedureIndex7, "modeval",					"7", 1);
		   		hisDAO_p.setProcInValueByPosition(nProcedureIndex7, "templateid",					fb.getTemplateId(),2);
		   		hisDAO_p.setProcInValueByPosition(nProcedureIndex7, "hospitalCode",				uservo.getHospitalCode(),3);   		
		   		hisDAO_p.setProcOutValue(nProcedureIndex7, "err", 					    1, 4); // 1 for
		   		hisDAO_p.setProcOutValue(nProcedureIndex7, "resultset", 				2, 5); // 1 for
		   		/* Executing Procedure */
				hisDAO_p.executeProcedureByPosition(nProcedureIndex7);
		   	
				// get value
				err = hisDAO_p.getString(nProcedureIndex7, "err");

				if (err == null)
					err = "";

				if (err.equals("")) {

					rs_docType = hisDAO_p.getWebRowSet(nProcedureIndex7, "resultset");			

				} else {

					throw new Exception(err);
				}
				
				System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 8]------------");
				
				 nProcedureIndex8 = hisDAO_p.setProcedure(strProcName);   	  	
			   		
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex8, "modeval",					"8", 1);
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex8, "templateid",					fb.getTemplateId(),2);
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex8, "hospitalCode",				uservo.getHospitalCode(),3);   		
			   		hisDAO_p.setProcOutValue(nProcedureIndex8, "err", 					    1, 4); // 1 for
			   		hisDAO_p.setProcOutValue(nProcedureIndex8, "resultset", 				2, 5); // 1 for
			   		/* Executing Procedure */
					hisDAO_p.executeProcedureByPosition(nProcedureIndex8);
			   	
					// get value
					err = hisDAO_p.getString(nProcedureIndex8, "err");

					if (err == null)
						err = "";

					if (err.equals("")) {

						rs_templateMstData = hisDAO_p.getWebRowSet(nProcedureIndex8, "resultset");			

					} else {

						throw new Exception(err);
					}
					
					System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 9]------------");
					
                    nProcedureIndex9 = hisDAO_p.setProcedure(strProcName);   	  	
			   		
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex9, "modeval",					"9", 1);
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex9, "templateid",					fb.getTemplateId(),2);
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex9, "hospitalCode",				uservo.getHospitalCode(),3);   		
			   		hisDAO_p.setProcOutValue(nProcedureIndex9, "err", 					    1, 4); // 1 for
			   		hisDAO_p.setProcOutValue(nProcedureIndex9, "resultset", 				2, 5); // 1 for
			   		/* Executing Procedure */
					hisDAO_p.executeProcedureByPosition(nProcedureIndex9);
			   	
					// get value
					err = hisDAO_p.getString(nProcedureIndex9, "err");

					if (err == null)
						err = "";

					if (err.equals("")) {

						rs_templateSubCategory = hisDAO_p.getWebRowSet(nProcedureIndex9, "resultset");			

					} else {

						throw new Exception(err);
					}
					
					
                    System.out.println("------------------- PKG_OT_VIEW.proc_GET_TEMPLATE_DESIGN ---------[ Mode - 10]------------");
					
                    nProcedureIndex10 = hisDAO_p.setProcedure(strProcName);   	  	
			   		
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex10, "modeval",					"10", 1);
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex10, "templateid",				fb.getTemplateId(),2);
			   		hisDAO_p.setProcInValueByPosition(nProcedureIndex10, "hospitalCode",				uservo.getHospitalCode(),3);   		
			   		hisDAO_p.setProcOutValue(nProcedureIndex10, "err", 					    1, 4); // 1 for
			   		hisDAO_p.setProcOutValue(nProcedureIndex10, "resultset", 				2, 5); // 1 for
			   		/* Executing Procedure */
					hisDAO_p.executeProcedureByPosition(nProcedureIndex10);
			   	
					// get value
					err = hisDAO_p.getString(nProcedureIndex10, "err");

					if (err == null)
						err = "";

					if (err.equals("")) {

						rs_templateCtrlMstData = hisDAO_p.getWebRowSet(nProcedureIndex10, "resultset");			

					} else {

						throw new Exception(err);
					}
						
   					
		System.out.println("---------after executing procedure---------" );
		
		//System.out.println("rs_controldtl_SIZE--1--"+rs_controldtl.getFetchSize());
		
		while(rs_controldtl.next())
		{
			OTTemplateControlVO objOTTemplateControlVO=new OTTemplateControlVO();
			objOTTemplateControlVO.setControlId(rs_controldtl.getString(1));
			objOTTemplateControlVO.setControlName(rs_controldtl.getString(2));
			objOTTemplateControlVO.setControlHtmlTag(rs_controldtl.getString(3));
			objOTTemplateControlVO.setControltldImport(rs_controldtl.getString(4));
			objOTTemplateControlVO.setControlstructTag(rs_controldtl.getString(5));
			objOTTemplateControlVO.setControlDisplayName(rs_controldtl.getString(6));
			objOTTemplateControlVO.setControlImage(rs_controldtl.getString(7));
			objOTTemplateControlVO.setIsVisble(rs_controldtl.getString(8));
			objOTTemplateControlVO.setAllPropertyIds(rs_controldtl.getString(9));
			objOTTemplateControlVO.setControlType(rs_controldtl.getString(10));
			objOTTemplateControlVO.setIsUsedVisDep(rs_controldtl.getString(11));
			lstcontroldtl.add(objOTTemplateControlVO);
		}
		
		//System.out.println("rs_propertydtl_SIZE--2--"+rs_propertydtl.getFetchSize());
		
		while(rs_propertydtl.next())
		{
			OTTemplatePropertyVO objOTTemplatePropertyVO=new OTTemplatePropertyVO();
			objOTTemplatePropertyVO.setPropertyId(rs_propertydtl.getString(1));
			objOTTemplatePropertyVO.setPropertyName(rs_propertydtl.getString(2));
			objOTTemplatePropertyVO.setPropertyType(rs_propertydtl.getString(3));
			objOTTemplatePropertyVO.setPropertyDisplayName(rs_propertydtl.getString(4));
			objOTTemplatePropertyVO.setAllpropertyValueSqnos(rs_propertydtl.getString(5));		
			objOTTemplatePropertyVO.setIsVisble(rs_propertydtl.getString(6));
			objOTTemplatePropertyVO.setParentId(rs_propertydtl.getString(7));
			objOTTemplatePropertyVO.setParentName(rs_propertydtl.getString(8));
			
			lstpropertydtl.add(objOTTemplatePropertyVO);
		}
		
		//System.out.println("rs_propertyvaluedtl_SIZE--3--"+rs_propertyvaluedtl.getFetchSize());
		
		while(rs_propertyvaluedtl.next())
		{
			OTTemplatePropertyValueVO objOTTemplatePropertyValueVO=new OTTemplatePropertyValueVO();
			objOTTemplatePropertyValueVO.setSqno(rs_propertyvaluedtl.getString(1));
			objOTTemplatePropertyValueVO.setPropertyValue(rs_propertyvaluedtl.getString(2));
			objOTTemplatePropertyValueVO.setPropertyDisplayValue(rs_propertyvaluedtl.getString(3));
			objOTTemplatePropertyValueVO.setIsDefault(rs_propertyvaluedtl.getString(4));
			objOTTemplatePropertyValueVO.setPropertyId(rs_propertyvaluedtl.getString(5));
			lstpropertyvaluedtl.add(objOTTemplatePropertyValueVO);
		}
		
		//System.out.println("rs_propertydefaultvaluedtl_SIZE--4--"+rs_propertydefaultvaluedtl.getFetchSize());
	
		while(rs_propertydefaultvaluedtl.next())
		{
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= new OTTemplateControlPropertyVO();
			objOTTemplateControlPropertyVO.setControlId(rs_propertydefaultvaluedtl.getString(1));
			objOTTemplateControlPropertyVO.setIsVisble(rs_propertydefaultvaluedtl.getString(2));
			objOTTemplateControlPropertyVO.setPropertyId(rs_propertydefaultvaluedtl.getString(3));
			objOTTemplateControlPropertyVO.setPropertyName(rs_propertydefaultvaluedtl.getString(4));
			objOTTemplateControlPropertyVO.setPropertyType(rs_propertydefaultvaluedtl.getString(5));
			objOTTemplateControlPropertyVO.setPropertyDisplayName(rs_propertydefaultvaluedtl.getString(6));
			objOTTemplateControlPropertyVO.setSqno(rs_propertydefaultvaluedtl.getString(7));
			objOTTemplateControlPropertyVO.setPropertyValue(rs_propertydefaultvaluedtl.getString(8));
			objOTTemplateControlPropertyVO.setPropertyDisplayValue(rs_propertydefaultvaluedtl.getString(9));
			lstdefaultcontrolproperty.add(objOTTemplateControlPropertyVO);
		}
		
		//System.out.println("rs_templateCategory_SIZE--5--"+rs_templateCategory.getFetchSize());
		
		while(rs_templateCategory.next())
		{
			Entry ent = new Entry();
			ent.setValue(rs_templateCategory.getString(1));
			ent.setLabel(rs_templateCategory.getString(2));
			lstTemplateCategory.add(ent);				
		}
		
		//System.out.println("rs_templateSubCategory_SIZE--6--"+rs_templateSubCategory.getFetchSize());
		
		while(rs_templateSubCategory.next())
		{
			Entry ent = new Entry();
			ent.setValue(rs_templateSubCategory.getString(1));
			ent.setLabel(rs_templateSubCategory.getString(2));
			lstTemplateSubCategory.add(ent);				
		}
		
		//System.out.println("rs_templateMstData_SIZE--7--"+rs_templateMstData.getFetchSize());
		
		while(rs_templateMstData.next())
		{
			fb.setTemplateName(rs_templateMstData.getString(1));
			fb.setCategoryId(rs_templateMstData.getString(2));
			fb.setSubCategoryId(rs_templateMstData.getString(3));
			fb.setIsActive(rs_templateMstData.getString(4));
		}
		
		//System.out.println("rs_templateCtrlMstData_SIZE--8--"+rs_templateCtrlMstData.getFetchSize());
		
		while(rs_templateCtrlMstData.next())
		{
			
			//System.out.println("ParaId-----"+rs_templateCtrlMstData.getString(1));
			//System.out.println("ControlId--"+rs_templateCtrlMstData.getString(2));
						
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= new OTTemplateControlPropertyVO();
			objOTTemplateControlPropertyVO.setParaId(rs_templateCtrlMstData.getString(1));
			objOTTemplateControlPropertyVO.setControlId(rs_templateCtrlMstData.getString(2));
			objOTTemplateControlPropertyVO.setAllPropertyIds(rs_templateCtrlMstData.getString(3));
			objOTTemplateControlPropertyVO.setLocation(rs_templateCtrlMstData.getString(4));			
			String propertyValues=rs_templateCtrlMstData.getString(5);
			if(rs_templateCtrlMstData.getString(6)!=null)
				propertyValues+=rs_templateCtrlMstData.getString(6);
			if(rs_templateCtrlMstData.getString(7)!=null)	
				propertyValues+=rs_templateCtrlMstData.getString(7);
			objOTTemplateControlPropertyVO.setAllPropertyValues(propertyValues);	
			objOTTemplateControlPropertyVO.setControlName(rs_templateCtrlMstData.getString(8));
			objOTTemplateControlPropertyVO.setIsInputType(rs_templateCtrlMstData.getString(9));			
			lstTemplateCtrlMstData.add(objOTTemplateControlPropertyVO);
		}
		
		//System.out.println("rs_docType_SIZE--9--"+rs_docType.getFetchSize());
		
		while(rs_docType.next())
		{			
			Entry ent = new Entry();
			ent.setValue(rs_docType.getString(1));
			ent.setLabel(rs_docType.getString(2));
			lstDocType.add(ent);				
		}
		
		
		List lstfinalControList=new ArrayList();
		
		System.out.println("-----A---------lstcontroldtl.size()--"+lstcontroldtl.size());
		
		int flag=0;
		for(int i=0;i<lstcontroldtl.size();i++)
		{
			OTTemplateControlVO objOTTemplateControlVO=(OTTemplateControlVO)lstcontroldtl.get(i);
			//System.out.println(objOTTemplateControlVO.getControlName());
			String allDefaltPropertyIds="";
			String alldefaltPropertyValues="";
			
			for(int j=0;j<lstdefaultcontrolproperty.size();j++)
			{
				OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= (OTTemplateControlPropertyVO)lstdefaultcontrolproperty.get(j);
				if(objOTTemplateControlVO.getControlId().equals(objOTTemplateControlPropertyVO.getControlId()))
				{						
					allDefaltPropertyIds+=objOTTemplateControlPropertyVO.getPropertyId()+",";
					
					// setting for style type
					if(objOTTemplateControlPropertyVO.getPropertyType().equals(OperationTheatreConfig.STYLE_TYPE) )
					{						
						flag=1;
						alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
					}
					// setting for html type
					if(objOTTemplateControlPropertyVO.getPropertyType().equals(OperationTheatreConfig.HTML_TYPE) )
					{
						if(objOTTemplateControlPropertyVO.getPropertyName().toLowerCase().equals("name"))
						{
							//htmlTypeString+=" "+ objOTTemplateControlPropertyVO.getPropertyName()+"=$";
							alldefaltPropertyValues+="$";
						}
						else{
							if(objOTTemplateControlPropertyVO.getPropertyName().toLowerCase().equals("id") )
							{
								//htmlTypeString+=" "+ objOTTemplateControlPropertyVO.getPropertyName()+"=@";
								alldefaltPropertyValues+="@";
							}
							else
							{
								alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
								
							}
						}							
					}
					// setting for div  and div style  type
					if(objOTTemplateControlPropertyVO.getPropertyType().equals("3") ||objOTTemplateControlPropertyVO.getPropertyType().equals("4") )
					{
						alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
						
					}
					alldefaltPropertyValues+=",";
				}
			}
				
				/*
				 * System.out.println("---------------------------------------");
				 * System.out.println("allDefaltPropertyIds-------"+allDefaltPropertyIds);
				 * System.out.println("alldefaltPropertyValues----"+alldefaltPropertyValues);
				 * System.out.println("---------------------------------------");
				 */
				 
			
			if(!alldefaltPropertyValues.equals(""))
			{			
				allDefaltPropertyIds=allDefaltPropertyIds.substring(0 , allDefaltPropertyIds.length()-1);
				alldefaltPropertyValues=alldefaltPropertyValues.substring(0 , alldefaltPropertyValues.length()-1);
				objOTTemplateControlVO.setDefaultControlpropertyIds(allDefaltPropertyIds);
				objOTTemplateControlVO.setDefaultControlpropertyValues(alldefaltPropertyValues);
				lstfinalControList.add(objOTTemplateControlVO);			
			}
		}
		
		
		Map mp=new HashMap();
		mp.clear();
		
		
		mp.put(OperationTheatreConfig.LISTCONTROL, lstfinalControList);
		mp.put(OperationTheatreConfig.LISTPROPERTYVO, lstpropertydtl);
		mp.put(OperationTheatreConfig.LISTPROPERTYVALUEVO, lstpropertyvaluedtl);
		mp.put(OperationTheatreConfig.LIST_TEMPLATE_CATEGORY, lstTemplateCategory);
		mp.put(OperationTheatreConfig.LIST_TEMPLATE_SUBCATEGORY, lstTemplateSubCategory);
		mp.put(OperationTheatreConfig.LIST_DOC_TYPE,lstDocType);
		
		
		WebUTIL.setMapInSession(mp, request);
		int indxForCtrl=0;
		for(int i=0;i<lstTemplateCtrlMstData.size();i++)
		{
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO=(OTTemplateControlPropertyVO) lstTemplateCtrlMstData.get(i);
			if(!objOTTemplateControlPropertyVO.getControlId().equals(OperationTheatreConfig.TD_CONTROL_ID))
				indxForCtrl++;
		}
		String []parameterLocation		=	new String [indxForCtrl];
		String []paraId					=	new String [indxForCtrl];		
		String []parameterControlId		=	new String [indxForCtrl];
		String []parameterControlName	=	new String [indxForCtrl];
		String []controlPropertyIds		=	new String [indxForCtrl];
		String []controlPropertyValues	=	new String [indxForCtrl];
	    int k=0;
	    
	    System.out.println("-----B---------lstTemplateCtrlMstData.size()--"+lstTemplateCtrlMstData.size());
	    
		for(int i=0;i<lstTemplateCtrlMstData.size();i++)
		{
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO=(OTTemplateControlPropertyVO) lstTemplateCtrlMstData.get(i);
			if(objOTTemplateControlPropertyVO.getControlId().equals(OperationTheatreConfig.TD_CONTROL_ID))
			{
				// setting data for td
					/*
					 * System.out.println("Para Id--A->"+objOTTemplateControlPropertyVO.getParaId())
					 * ; System.out.println("getControlId-->"+objOTTemplateControlPropertyVO.
					 * getControlId());
					 * System.out.println("Para Value--->"+objOTTemplateControlPropertyVO.
					 * getAllPropertyValues());
					 */
				
				fb.setTemplateTDParaId(objOTTemplateControlPropertyVO.getParaId());
				if(objOTTemplateControlPropertyVO.getAllPropertyValues()!=null)
				{	
					String []allpropertyValues = objOTTemplateControlPropertyVO.getAllPropertyValues().replace("^","#").split("#");
					fb.setTdPropertyvalues(allpropertyValues);
					String      allpropertyIds = objOTTemplateControlPropertyVO.getAllPropertyIds();
					String [] arrpropertyids=new String[allpropertyValues.length];
					fb.setLocation(objOTTemplateControlPropertyVO.getLocation().replace("^", "#").split("#"));
					for(int j=0;j<allpropertyValues.length;j++)
					{
						arrpropertyids[j] = allpropertyIds;					
					}		
					fb.setTdPropertyids(arrpropertyids);
				}
			}
			else
			{
				/*
				System.out.println("Para Id-B-->"+objOTTemplateControlPropertyVO.getParaId());
				System.out.println("getControlId-->"+objOTTemplateControlPropertyVO.getControlId());
				System.out.println("Para Value--->"+objOTTemplateControlPropertyVO.getAllPropertyValues());
				*/
				
				// for other control
				parameterLocation[k]		=	objOTTemplateControlPropertyVO.getLocation();
				paraId[k]					=	objOTTemplateControlPropertyVO.getParaId();
				parameterControlId[k]		=	objOTTemplateControlPropertyVO.getControlId();
				parameterControlName[k]		=	objOTTemplateControlPropertyVO.getControlName();
				controlPropertyIds[k]		=	objOTTemplateControlPropertyVO.getAllPropertyIds();
				controlPropertyValues[k]	=	objOTTemplateControlPropertyVO.getAllPropertyValues();				
				k++;				
			}
		}
		fb.setParameterLocation(parameterLocation);
		fb.setParaId(paraId);
		fb.setParameterControlId(parameterControlId);
		fb.setParameterControlName(parameterControlName);
		fb.setControlPropertyIds(controlPropertyIds);
		fb.setControlPropertyValues(controlPropertyValues);
		
		setObjectInSession(request, fb); 
		
		System.out.println("-------------------TemplateDesignerMstUTIL . initModify -----------END----------");
		
	}
	catch (Exception e) {
		status.add(status.DONE, "Exception while getting record!", "");			
		e.printStackTrace();			
	}
	finally{
		
			
		
		WebUTIL.setStatus(request, status);
	}	
}


public static void initModify_org(HttpServletRequest request, TemplateDesignerMstFB fb) {
	Status status= new Status();
	UserVO uservo=ControllerUTIL.getUserVO(request);
	
	ResultSet rs_controldtl=null;
	ResultSet rs_propertydtl=null;
	ResultSet rs_propertyvaluedtl=null;
	ResultSet rs_propertydefaultvaluedtl=null;
	ResultSet rs_templateCategory=null;
	ResultSet rs_templateSubCategory=null;
	ResultSet rs_templateMstData=null;
	ResultSet rs_templateCtrlMstData=null;
	ResultSet rs_docType=null;
	List lstDocType= new ArrayList();
	
	List lstcontroldtl= new ArrayList();
	List lstpropertydtl= new ArrayList();
	List lstpropertyvaluedtl= new ArrayList();
	List lstdefaultcontrolproperty= new ArrayList();
	List lstTemplateCategory= new ArrayList();
	List lstTemplateSubCategory= new ArrayList();
	List lstTemplateCtrlMstData= new ArrayList();
	
	
	CallableStatement csmt=null;
	Connection conn = null;
	HttpSession sess= request.getSession(); 
	sess.removeAttribute(OperationTheatreConfig.LISTCONTROL);
	sess.removeAttribute(OperationTheatreConfig.LISTPROPERTYVO);
	sess.removeAttribute(OperationTheatreConfig.LISTPROPERTYVALUEVO);
	sess.removeAttribute(OperationTheatreConfig.LIST_TEMPLATE_CATEGORY);
	sess.removeAttribute(OperationTheatreConfig.LIST_TEMPLATE_SUBCATEGORY);
	sess.removeAttribute(OperationTheatreConfig.LIST_DOC_TYPE);
	HisDAO hisDAO_p=null;
	final int nProcedureIndex;
	final String strProcName = "{call AHIS_OT_UTILITY.GETTEMPDESIGMODIFYINITIALIZE(?,?,?,?,?,?,?,?,?,?,?,?,?)}"; 
	try{
		
		System.out.println("-------------------TemplateDesignerMstUTIL . initModify -----------START----------");
		
		
   	
		HisDAO dao = null;

		dao = new HisDAO("MMS", "DrugInventoryTransDAO");	
					conn=dao.getConnection();
		
		System.out.println("------------------- AHIS_OT_UTILITY.GETTEMPDESIGMODIFYINITIALIZE ---------------------");
		
   	
   		hisDAO_p = new HisDAO("operationTheatre", "TemplateDesignerMstUTIL");
   		nProcedureIndex = hisDAO_p.setProcedure(strProcName);   		  		
   		
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex, "templateid",					fb.getTemplateId(), 1);
   		hisDAO_p.setProcInValueByPosition(nProcedureIndex, "hospitalCode",				uservo.getHospitalCode(), 2);   		
   		hisDAO_p.setProcOutValue(nProcedureIndex, "errormsg", 					1, 3); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "errorcode", 					1, 4); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rc_controldtl", 				2, 5); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_propertydtl", 			2, 6); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_propertyvaluedtl", 		2, 7); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_propertydefaultvaluedtl", 2, 8); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_templateCategory", 		2, 9); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_templateSubCategory", 	2, 10); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_templateMstData", 		2, 11); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_templateCtrlMstData", 	2, 12); // 1 for
   		hisDAO_p.setProcOutValue(nProcedureIndex, "rs_docType", 				2, 13); // 1 for
   		
   		/* Executing Procedure */
		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
   		
		//System.out.println("error msg:"+csmt.getString(3)+"\nerror code"+csmt.getString(4));
		rs_controldtl				=	hisDAO_p.getWebRowSet(nProcedureIndex, "rc_controldtl"); //(ResultSet)csmt.getObject(5); 
		rs_propertydtl				=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_propertydtl"); ;//(ResultSet)csmt.getObject(6);
		rs_propertyvaluedtl			=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_propertyvaluedtl");;//(ResultSet)csmt.getObject(7);
		rs_propertydefaultvaluedtl	=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_propertydefaultvaluedtl");;//(ResultSet)csmt.getObject(8);
		rs_templateCategory			=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_templateCategory");//(ResultSet)csmt.getObject(9);
		rs_templateSubCategory		=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_templateSubCategory");;//(ResultSet)csmt.getObject(10);
		rs_templateMstData			=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_templateMstData");;//(ResultSet)csmt.getObject(11);
		rs_templateCtrlMstData		=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_templateCtrlMstData");;//(ResultSet)csmt.getObject(12);
		rs_docType					=	hisDAO_p.getWebRowSet(nProcedureIndex, "rs_docType");//(ResultSet)csmt.getObject(13);
		
		System.out.println("---------after executing procedure---------" );
		
		//System.out.println("rs_controldtl_SIZE--1--"+rs_controldtl.getFetchSize());
		
		while(rs_controldtl.next())
		{
			OTTemplateControlVO objOTTemplateControlVO=new OTTemplateControlVO();
			objOTTemplateControlVO.setControlId(rs_controldtl.getString(1));
			objOTTemplateControlVO.setControlName(rs_controldtl.getString(2));
			objOTTemplateControlVO.setControlHtmlTag(rs_controldtl.getString(3));
			objOTTemplateControlVO.setControltldImport(rs_controldtl.getString(4));
			objOTTemplateControlVO.setControlstructTag(rs_controldtl.getString(5));
			objOTTemplateControlVO.setControlDisplayName(rs_controldtl.getString(6));
			objOTTemplateControlVO.setControlImage(rs_controldtl.getString(7));
			objOTTemplateControlVO.setIsVisble(rs_controldtl.getString(8));
			objOTTemplateControlVO.setAllPropertyIds(rs_controldtl.getString(9));
			objOTTemplateControlVO.setControlType(rs_controldtl.getString(10));
			objOTTemplateControlVO.setIsUsedVisDep(rs_controldtl.getString(11));
			lstcontroldtl.add(objOTTemplateControlVO);
		}
		
		//System.out.println("rs_propertydtl_SIZE--2--"+rs_propertydtl.getFetchSize());
		
		while(rs_propertydtl.next())
		{
			OTTemplatePropertyVO objOTTemplatePropertyVO=new OTTemplatePropertyVO();
			objOTTemplatePropertyVO.setPropertyId(rs_propertydtl.getString(1));
			objOTTemplatePropertyVO.setPropertyName(rs_propertydtl.getString(2));
			objOTTemplatePropertyVO.setPropertyType(rs_propertydtl.getString(3));
			objOTTemplatePropertyVO.setPropertyDisplayName(rs_propertydtl.getString(4));
			objOTTemplatePropertyVO.setAllpropertyValueSqnos(rs_propertydtl.getString(5));		
			objOTTemplatePropertyVO.setIsVisble(rs_propertydtl.getString(6));
			objOTTemplatePropertyVO.setParentId(rs_propertydtl.getString(7));
			objOTTemplatePropertyVO.setParentName(rs_propertydtl.getString(8));
			
			lstpropertydtl.add(objOTTemplatePropertyVO);
		}
		
		//System.out.println("rs_propertyvaluedtl_SIZE--3--"+rs_propertyvaluedtl.getFetchSize());
		
		while(rs_propertyvaluedtl.next())
		{
			OTTemplatePropertyValueVO objOTTemplatePropertyValueVO=new OTTemplatePropertyValueVO();
			objOTTemplatePropertyValueVO.setSqno(rs_propertyvaluedtl.getString(1));
			objOTTemplatePropertyValueVO.setPropertyValue(rs_propertyvaluedtl.getString(2));
			objOTTemplatePropertyValueVO.setPropertyDisplayValue(rs_propertyvaluedtl.getString(3));
			objOTTemplatePropertyValueVO.setIsDefault(rs_propertyvaluedtl.getString(4));
			objOTTemplatePropertyValueVO.setPropertyId(rs_propertyvaluedtl.getString(5));
			lstpropertyvaluedtl.add(objOTTemplatePropertyValueVO);
		}
		
		//System.out.println("rs_propertydefaultvaluedtl_SIZE--4--"+rs_propertydefaultvaluedtl.getFetchSize());
	
		while(rs_propertydefaultvaluedtl.next())
		{
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= new OTTemplateControlPropertyVO();
			objOTTemplateControlPropertyVO.setControlId(rs_propertydefaultvaluedtl.getString(1));
			objOTTemplateControlPropertyVO.setIsVisble(rs_propertydefaultvaluedtl.getString(2));
			objOTTemplateControlPropertyVO.setPropertyId(rs_propertydefaultvaluedtl.getString(3));
			objOTTemplateControlPropertyVO.setPropertyName(rs_propertydefaultvaluedtl.getString(4));
			objOTTemplateControlPropertyVO.setPropertyType(rs_propertydefaultvaluedtl.getString(5));
			objOTTemplateControlPropertyVO.setPropertyDisplayName(rs_propertydefaultvaluedtl.getString(6));
			objOTTemplateControlPropertyVO.setSqno(rs_propertydefaultvaluedtl.getString(7));
			objOTTemplateControlPropertyVO.setPropertyValue(rs_propertydefaultvaluedtl.getString(8));
			objOTTemplateControlPropertyVO.setPropertyDisplayValue(rs_propertydefaultvaluedtl.getString(9));
			lstdefaultcontrolproperty.add(objOTTemplateControlPropertyVO);
		}
		
		//System.out.println("rs_templateCategory_SIZE--5--"+rs_templateCategory.getFetchSize());
		
		while(rs_templateCategory.next())
		{
			Entry ent = new Entry();
			ent.setValue(rs_templateCategory.getString(1));
			ent.setLabel(rs_templateCategory.getString(2));
			lstTemplateCategory.add(ent);				
		}
		
		//System.out.println("rs_templateSubCategory_SIZE--6--"+rs_templateSubCategory.getFetchSize());
		
		while(rs_templateSubCategory.next())
		{
			Entry ent = new Entry();
			ent.setValue(rs_templateSubCategory.getString(1));
			ent.setLabel(rs_templateSubCategory.getString(2));
			lstTemplateSubCategory.add(ent);				
		}
		
		//System.out.println("rs_templateMstData_SIZE--7--"+rs_templateMstData.getFetchSize());
		
		while(rs_templateMstData.next())
		{
			fb.setTemplateName(rs_templateMstData.getString(1));
			fb.setCategoryId(rs_templateMstData.getString(2));
			fb.setSubCategoryId(rs_templateMstData.getString(3));
			fb.setIsActive(rs_templateMstData.getString(4));
		}
		
		//System.out.println("rs_templateCtrlMstData_SIZE--8--"+rs_templateCtrlMstData.getFetchSize());
		
		while(rs_templateCtrlMstData.next())
		{
			
			//System.out.println("ParaId-----"+rs_templateCtrlMstData.getString(1));
			//System.out.println("ControlId--"+rs_templateCtrlMstData.getString(2));
						
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= new OTTemplateControlPropertyVO();
			objOTTemplateControlPropertyVO.setParaId(rs_templateCtrlMstData.getString(1));
			objOTTemplateControlPropertyVO.setControlId(rs_templateCtrlMstData.getString(2));
			objOTTemplateControlPropertyVO.setAllPropertyIds(rs_templateCtrlMstData.getString(3));
			objOTTemplateControlPropertyVO.setLocation(rs_templateCtrlMstData.getString(4));			
			String propertyValues=rs_templateCtrlMstData.getString(5);
			if(rs_templateCtrlMstData.getString(6)!=null)
				propertyValues+=rs_templateCtrlMstData.getString(6);
			if(rs_templateCtrlMstData.getString(7)!=null)	
				propertyValues+=rs_templateCtrlMstData.getString(7);
			objOTTemplateControlPropertyVO.setAllPropertyValues(propertyValues);	
			objOTTemplateControlPropertyVO.setControlName(rs_templateCtrlMstData.getString(8));
			objOTTemplateControlPropertyVO.setIsInputType(rs_templateCtrlMstData.getString(9));			
			lstTemplateCtrlMstData.add(objOTTemplateControlPropertyVO);
		}
		
		//System.out.println("rs_docType_SIZE--9--"+rs_docType.getFetchSize());
		
		while(rs_docType.next())
		{			
			Entry ent = new Entry();
			ent.setValue(rs_docType.getString(1));
			ent.setLabel(rs_docType.getString(2));
			lstDocType.add(ent);				
		}
		
		
		List lstfinalControList=new ArrayList();
		
		System.out.println("-----A---------lstcontroldtl.size()--"+lstcontroldtl.size());
		
		int flag=0;
		for(int i=0;i<lstcontroldtl.size();i++)
		{
			OTTemplateControlVO objOTTemplateControlVO=(OTTemplateControlVO)lstcontroldtl.get(i);
			//System.out.println(objOTTemplateControlVO.getControlName());
			String allDefaltPropertyIds="";
			String alldefaltPropertyValues="";
			
			for(int j=0;j<lstdefaultcontrolproperty.size();j++)
			{
				OTTemplateControlPropertyVO objOTTemplateControlPropertyVO= (OTTemplateControlPropertyVO)lstdefaultcontrolproperty.get(j);
				if(objOTTemplateControlVO.getControlId().equals(objOTTemplateControlPropertyVO.getControlId()))
				{						
					allDefaltPropertyIds+=objOTTemplateControlPropertyVO.getPropertyId()+",";
					
					// setting for style type
					if(objOTTemplateControlPropertyVO.getPropertyType().equals(OperationTheatreConfig.STYLE_TYPE) )
					{						
						flag=1;
						alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
					}
					// setting for html type
					if(objOTTemplateControlPropertyVO.getPropertyType().equals(OperationTheatreConfig.HTML_TYPE) )
					{
						if(objOTTemplateControlPropertyVO.getPropertyName().toLowerCase().equals("name"))
						{
							//htmlTypeString+=" "+ objOTTemplateControlPropertyVO.getPropertyName()+"=$";
							alldefaltPropertyValues+="$";
						}
						else{
							if(objOTTemplateControlPropertyVO.getPropertyName().toLowerCase().equals("id") )
							{
								//htmlTypeString+=" "+ objOTTemplateControlPropertyVO.getPropertyName()+"=@";
								alldefaltPropertyValues+="@";
							}
							else
							{
								alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
								
							}
						}							
					}
					// setting for div  and div style  type
					if(objOTTemplateControlPropertyVO.getPropertyType().equals("3") ||objOTTemplateControlPropertyVO.getPropertyType().equals("4") )
					{
						alldefaltPropertyValues+=objOTTemplateControlPropertyVO.getPropertyValue();
						
					}
					alldefaltPropertyValues+=",";
				}
			}
				
				/*
				 * System.out.println("---------------------------------------");
				 * System.out.println("allDefaltPropertyIds-------"+allDefaltPropertyIds);
				 * System.out.println("alldefaltPropertyValues----"+alldefaltPropertyValues);
				 * System.out.println("---------------------------------------");
				 */
				 
			
			if(!alldefaltPropertyValues.equals(""))
			{			
				allDefaltPropertyIds=allDefaltPropertyIds.substring(0 , allDefaltPropertyIds.length()-1);
				alldefaltPropertyValues=alldefaltPropertyValues.substring(0 , alldefaltPropertyValues.length()-1);
				objOTTemplateControlVO.setDefaultControlpropertyIds(allDefaltPropertyIds);
				objOTTemplateControlVO.setDefaultControlpropertyValues(alldefaltPropertyValues);
				lstfinalControList.add(objOTTemplateControlVO);			
			}
		}
		
		
		Map mp=new HashMap();
		mp.clear();
		
		
		mp.put(OperationTheatreConfig.LISTCONTROL, lstfinalControList);
		mp.put(OperationTheatreConfig.LISTPROPERTYVO, lstpropertydtl);
		mp.put(OperationTheatreConfig.LISTPROPERTYVALUEVO, lstpropertyvaluedtl);
		mp.put(OperationTheatreConfig.LIST_TEMPLATE_CATEGORY, lstTemplateCategory);
		mp.put(OperationTheatreConfig.LIST_TEMPLATE_SUBCATEGORY, lstTemplateSubCategory);
		mp.put(OperationTheatreConfig.LIST_DOC_TYPE,lstDocType);
		
		
		WebUTIL.setMapInSession(mp, request);
		int indxForCtrl=0;
		for(int i=0;i<lstTemplateCtrlMstData.size();i++)
		{
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO=(OTTemplateControlPropertyVO) lstTemplateCtrlMstData.get(i);
			if(!objOTTemplateControlPropertyVO.getControlId().equals(OperationTheatreConfig.TD_CONTROL_ID))
				indxForCtrl++;
		}
		String []parameterLocation		=	new String [indxForCtrl];
		String []paraId					=	new String [indxForCtrl];		
		String []parameterControlId		=	new String [indxForCtrl];
		String []parameterControlName	=	new String [indxForCtrl];
		String []controlPropertyIds		=	new String [indxForCtrl];
		String []controlPropertyValues	=	new String [indxForCtrl];
	    int k=0;
	    
	    System.out.println("-----B---------lstTemplateCtrlMstData.size()--"+lstTemplateCtrlMstData.size());
	    
		for(int i=0;i<lstTemplateCtrlMstData.size();i++)
		{
			OTTemplateControlPropertyVO objOTTemplateControlPropertyVO=(OTTemplateControlPropertyVO) lstTemplateCtrlMstData.get(i);
			if(objOTTemplateControlPropertyVO.getControlId().equals(OperationTheatreConfig.TD_CONTROL_ID))
			{
				// setting data for td
					/*
					 * System.out.println("Para Id--A->"+objOTTemplateControlPropertyVO.getParaId())
					 * ; System.out.println("getControlId-->"+objOTTemplateControlPropertyVO.
					 * getControlId());
					 * System.out.println("Para Value--->"+objOTTemplateControlPropertyVO.
					 * getAllPropertyValues());
					 */
				
				fb.setTemplateTDParaId(objOTTemplateControlPropertyVO.getParaId());
				if(objOTTemplateControlPropertyVO.getAllPropertyValues()!=null)
				{	
					String []allpropertyValues = objOTTemplateControlPropertyVO.getAllPropertyValues().replace("^","#").split("#");
					fb.setTdPropertyvalues(allpropertyValues);
					String      allpropertyIds = objOTTemplateControlPropertyVO.getAllPropertyIds();
					String [] arrpropertyids=new String[allpropertyValues.length];
					fb.setLocation(objOTTemplateControlPropertyVO.getLocation().replace("^", "#").split("#"));
					for(int j=0;j<allpropertyValues.length;j++)
					{
						arrpropertyids[j] = allpropertyIds;					
					}		
					fb.setTdPropertyids(arrpropertyids);
				}
			}
			else
			{
				/*
				System.out.println("Para Id-B-->"+objOTTemplateControlPropertyVO.getParaId());
				System.out.println("getControlId-->"+objOTTemplateControlPropertyVO.getControlId());
				System.out.println("Para Value--->"+objOTTemplateControlPropertyVO.getAllPropertyValues());
				*/
				
				// for other control
				parameterLocation[k]		=	objOTTemplateControlPropertyVO.getLocation();
				paraId[k]					=	objOTTemplateControlPropertyVO.getParaId();
				parameterControlId[k]		=	objOTTemplateControlPropertyVO.getControlId();
				parameterControlName[k]		=	objOTTemplateControlPropertyVO.getControlName();
				controlPropertyIds[k]		=	objOTTemplateControlPropertyVO.getAllPropertyIds();
				controlPropertyValues[k]	=	objOTTemplateControlPropertyVO.getAllPropertyValues();				
				k++;				
			}
		}
		fb.setParameterLocation(parameterLocation);
		fb.setParaId(paraId);
		fb.setParameterControlId(parameterControlId);
		fb.setParameterControlName(parameterControlName);
		fb.setControlPropertyIds(controlPropertyIds);
		fb.setControlPropertyValues(controlPropertyValues);
		
		setObjectInSession(request, fb); 
		
		System.out.println("-------------------TemplateDesignerMstUTIL . initModify -----------END----------");
		
	}
	catch (Exception e) {
		status.add(status.DONE, "Exception while getting record!", "");			
		e.printStackTrace();			
	}
	finally{
		try {
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		WebUTIL.setStatus(request, status);
	}	
}
/** Getting Parameter-Values List
 * @param _request 
 * @return List of Parameters Values in OTTemplateParameterVO class Objects
 */
public static List<OTTemplateParameterVO> getTempParameterValueList(HttpServletRequest _request)
{
	System.out.println("---TemplateDesignerMstUTIL . getTempParameterValueList---");
	
	List<OTTemplateParameterVO> lstParaValues= new ArrayList<OTTemplateParameterVO>();
	try{
	Enumeration requestParameters = _request.getParameterNames();
	while (requestParameters.hasMoreElements())
	{
		String nextElement = (String) requestParameters.nextElement();
		if (nextElement.toString().startsWith(OperationTheatreConfig.PARAMETER_STARTS_WITH))
		{
			//System.out.println("nextElement---" + nextElement);
			
			String requestDataArray[] = _request.getParameterValues(nextElement);
			if (requestDataArray.length != 0) 
				for (int i = 0; i < requestDataArray.length; i++)
					if (!requestDataArray[i].trim().equals(""))
					{
						OTTemplateParameterVO tempPara = new OTTemplateParameterVO();
						String pId = (String) nextElement;
						//System.out.println("pId-"+i+"--" + pId);
						tempPara.setTemplateId(pId.replace("^", "#").split("#")[2]);
						tempPara.setParaId(pId.replace("^", "#").split("#")[3]);
						tempPara.setParaValue(requestDataArray[i]);
						//System.out.println("template id-" + tempPara.getTemplateId() +" paraid-" + tempPara.getParaId() + " value-" + tempPara.getParaValue());
					
						lstParaValues.add(tempPara);
					}
		}
	}
	}catch(Exception e){
		e.printStackTrace();
	}
	return lstParaValues;
}
public Map initAllHTMLDocFilesData(HttpServletRequest request, TemplateDesignerMstFB fb){
	Map mpFileData= new HashMap();
	try{
	
	 File folder = new File("C:\\OT_HTML_DOC\\"+ fb.getTemplateId());
	 File[] listOfFiles = folder.listFiles();
	    if(listOfFiles!=null && listOfFiles.length>0){
	    for (int i = 0; i < listOfFiles.length; i++) {
	      String resultStr="";	
	      if (listOfFiles[i].isFile()) {
	       // System.out.println("\nFile " + listOfFiles[i].getAbsolutePath() + "\n");
	        FileInputStream fstream = new FileInputStream(listOfFiles[i].getAbsolutePath());
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) 	{
				// Print the content on the console
				resultStr+=strLine;
				//System.out.println (strLine);
			}
			mpFileData.put(listOfFiles[i].getName(), resultStr);
			//Close the input stream
			in.close();
	        
	      } 
	    }    
	   }
	}
	catch(Exception e){
		e.printStackTrace();
		mpFileData=null;
	}
	return mpFileData;
}
public String initHTMLDocFilesData(HttpServletRequest request, TemplateDesignerMstFB fb,String fileName){
	String resultHTML="";
	try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream("C:\\OT_HTML_DOC\\"+fb.getTemplateId()+"\\"+fileName);
			// Get the object of DataInputStream
			if(fstream!=null){
				DataInputStream in = new DataInputStream(fstream);
		        BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				//Read File Line By Line
				while ((strLine = br.readLine()) != null) 	{
					// Print the content on the console
				//	System.out.println (strLine);
					resultHTML+=strLine;
				}
				//Close the input stream
				in.close();
			}
	}		
	catch(Exception e){
		System.out.println("Error messgae--" + e.getMessage());
		return resultHTML="";
	}
	return resultHTML;
}


public void getAlldocumentAttached(HttpServletRequest request,HttpServletResponse response,TemplateDesignerMstFB fb) {
	List lstDocType=(List)request.getSession().getAttribute(OperationTheatreConfig.LIST_DOC_TYPE);
	Map mp= (Map)request.getSession().getAttribute(OperationTheatreConfig.TEMPLATEHTMLMAP);	
	List lstData= new ArrayList();
	String strResult=fb.getDivId()+"^";
	if(fb.getTemplateId()!=null && fb.getTemplateId().equals("master")){
		fb.setTemplateId((String)request.getSession().getAttribute("MASTER_TEMPLATEID"));
	}
	if(lstDocType!=null && lstDocType.size()>0){
		for(int i=0;i<lstDocType.size();i++){			
			Entry ent=(Entry)lstDocType.get(i);
			boolean flag=true;
			String resultString="";
			if(mp!=null && mp.size()>0 && mp.containsKey(fb.getObjId()+"_"+  ent.getValue()))
			{
				flag=false;
				resultString=(String) mp.get(fb.getObjId()+"_"+  ent.getValue());
			}
			else
			{
				flag=true;
			}
			if(flag)	
				resultString=initHTMLDocFilesData(request, fb,fb.getObjId()+"_"+  ent.getValue()+".html");
			if(resultString!=null && !resultString.equals(""))
			{
				strResult+="<br><b><u>"+ent.getLabel()+"</u></b><br>";
				strResult+=resultString;
			}
			
		}
	}
	populateDataInResponse(request, response, strResult);	
}
}//class ends
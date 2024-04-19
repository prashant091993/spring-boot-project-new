package global.Template;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class WebUTIL extends HelperMethods
{
	public static void setMapInRequest(Map _mp, HttpServletRequest request)
	{
		Set stEntry = _mp.entrySet();
		Iterator itr = stEntry.iterator();
		while (itr.hasNext())
		{
			Map.Entry entry = (Map.Entry) itr.next();
			String strKey = (String) entry.getKey();
			request.setAttribute(strKey, entry.getValue());
		}
	}

	public static String getCustomisedSysDate(Date _dt, String _formatString)
	{
		
		SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
				if (_formatString == null || _formatString.equals(""))
		{
			sf.applyPattern("dd/MM/yyyy HH:mm");
		}
		else
		{
			sf.applyPattern(_formatString);
		}
		String date = sf.format(_dt);
		System.out.println("date in dao" + date);
		return date;
	}

	public static Date getDateFromString(String _strDate, String _formatString)
	{
		Date objDate = null;
		try
		{
			SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getInstance();
			if (_formatString == null || _formatString.equals(""))
			{
				sdf.applyPattern("dd/MM/yyyy HH:mm");
			}
			else
			{
				sdf.applyPattern(_formatString);
			}
			objDate = sdf.parse(_strDate);
		}
		catch(ParseException pe)
		{
			pe.printStackTrace();
			objDate = null;
		}
		return objDate;
	}

	/**
	 * This method sets the map(key(as an attrib name)-value) in session
	 * It also retrievs the transaction specific collection from the session
	 * and adds the key to this collection 
	 */

	
	public static void setMapInSession(Map _mp, HttpServletRequest request)
	{
		try
		{
		Set stEntry = _mp.entrySet();
		Iterator itr = stEntry.iterator();
		HttpSession session = request.getSession();
		HashSet transSpecificColl = (HashSet) session.getAttribute(Config.TRANSACTION_SPECIFIC_SESSION_ITEMS);
		if(transSpecificColl==null)
		{
			transSpecificColl=new HashSet();
			session.setAttribute(Config.TRANSACTION_SPECIFIC_SESSION_ITEMS,transSpecificColl);
		}
		//System.out.println(session.isNew());
		while (itr.hasNext())
		{
			Map.Entry entry = (Map.Entry) itr.next();
			String strKey = (String) entry.getKey();
			session.setAttribute(strKey, entry.getValue());
			transSpecificColl.add(strKey);
		}
		
		session.setAttribute(Config.TRANSACTION_SPECIFIC_SESSION_ITEMS, transSpecificColl);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		}

	
	
	public static Collection removeEntriesfromOptions(Collection _entryCollection, String[] _entryvalues)
	{

		Collection newEntryCollection = new ArrayList(_entryCollection);
		Collection toRemove = new ArrayList();

		for (int i = 0; i < _entryvalues.length; i++)
		{
			Entry objEntry = new Entry(null, _entryvalues[i]);
			toRemove.add(objEntry);
		}
		//System.out.println("toRemove" + toRemove);
		newEntryCollection.removeAll(toRemove);
		//System.out.println("newEntryCollection " + newEntryCollection);

		sortEntryCollection(newEntryCollection);
		//System.out.println("after sort");
		//System.out.println("newEntryCollection after sort" + newEntryCollection);
		return newEntryCollection;
	}

	public static Collection removeEntriesfromOptions(Collection _entryCollection, String _entryvalues)
	{

		Collection newEntryCollection = new ArrayList(_entryCollection);
		Collection toRemove = new ArrayList();
		Entry objEntry = new Entry(null, _entryvalues);
		toRemove.add(objEntry);
		//System.out.println("toRemove" + toRemove);
		newEntryCollection.removeAll(toRemove);
		//System.out.println("newEntryCollection " + newEntryCollection);
		//sortEntryCollection(newEntryCollection);
	//	System.out.println("after sort");
		//System.out.println("newEntryCollection after sort" + newEntryCollection);
		return newEntryCollection;
	}

	public static Collection addEntryToOptions(Collection _entryCollection, String _entryvalues, String _label)
	{

		Collection newEntryCollection = new ArrayList(_entryCollection);

		Entry objEntry = new Entry();
		objEntry.setLabel(_label);
		objEntry.setValue(_entryvalues);

		newEntryCollection.add(objEntry);
		return newEntryCollection;
	}

	public static void sortEntryCollection(Collection _col)
	{
		Collections.sort((ArrayList) _col, new Entry.EntryComparator());
	}

	public static String[] removeFromArray(String[] _arrObj, int _idx)
	{
		//Check for length = 0
		if (_arrObj.length == 0) throw new IllegalArgumentException();
		//List lst = Arrays.asList(_arrObj);
		String[] tmp = new String[_arrObj.length - 1];

		for (int i = 0, j = 0; i < _arrObj.length; i++)
		{
			if (i != _idx)
			{
				tmp[j++] = _arrObj[i];
			}
		}
		return tmp;
	}

	public static String[] addNewElementToArray(String[] _arrStr)
	{
		return (String[]) addElementToArray(_arrStr, new String());
	}

	//to be made generic
	public static String[] addElementToArray(String[] _arrObj, String _obj)
	{

		String[] tmp = new String[_arrObj.length + 1];
		//System.out.println("dfsdgh");
		for (int i = 0; i < _arrObj.length; i++)
		{
			tmp[i] = _arrObj[i];
		}
		tmp[_arrObj.length] = _obj;
		//System.out.println("dfsdgh");
		return tmp;
	}

	public static HttpSession getSession(HttpServletRequest _request)
	{
		HttpSession session = _request.getSession();
		return session;
	}

	public static void setStatus(HttpServletRequest request, Status _status)
	{
		Status status = (Status) request.getAttribute(Config.STATUS_OBJECT);
		if (status == null) status = new Status();
		status.add(_status);

		request.setAttribute(Config.STATUS_OBJECT, status);
	}

	public static void removeFromStatus(HttpServletRequest request, Entry _objEntry)
	{
		Status status = (Status) request.getAttribute(Config.STATUS_OBJECT);
		if (status != null) status.remove(_objEntry);
		request.setAttribute(Config.STATUS_OBJECT, status);
	}

	public static Status getStatus(HttpServletRequest request)
	{
		Status status = (Status) request.getAttribute(Config.STATUS_OBJECT);
		if (status == null)
		{
			status = new Status();
		}
		return status;

	}

	public static Object[] addElementToArray(Object[] _arrObj, Object _obj)
	{
		//System.out.println("web uitlllll object or;;;;;;;;;;;;;;'''''''''");
		Object[] tmp = new Object[_arrObj.length + 1];
		//System.out.println("dfsdgh");
		for (int i = 0; i < _arrObj.length; i++)
		{
			tmp[i] = _arrObj[i];
		}
		tmp[_arrObj.length] = _obj;
		return tmp;
	}

	public static Entry getEntryInCollectionByValue(Collection col, String val)
	{
		if (col == null) throw new IllegalArgumentException();

		if (col.size() == 0) return null;

		Iterator it = col.iterator();
		Entry entry = null;
		while (it.hasNext())
		{
			entry = (Entry) it.next();
			if (!(entry instanceof Entry)) throw new IllegalArgumentException();

			if (entry.getValue().trim().equalsIgnoreCase(val.trim()))
			{
				return entry;
			}
		}
		return null;
	}

	public static Entry getEntryInCollectionByLabel(Collection col, String label)
	{
		if (col == null) throw new IllegalArgumentException();

		if (col.size() == 0) return null;

		Iterator it = col.iterator();
		Entry entry = null;
		while (it.hasNext())
		{
			entry = (Entry) it.next();
			if (!(entry instanceof Entry)) throw new IllegalArgumentException();

			if (entry.getLabel().trim().equalsIgnoreCase(label.trim()))
			{
				return entry;
			}
		}
		return null;
	}

	public static Collection getValidVO(ValueObject _valueObject[]) throws Exception
	{

		Collection CollectionVO = new ArrayList();
		for (int i = 0; i < _valueObject.length; i++)
		{
			Class VOClass = _valueObject[i].getClass();
			Method voMethod = VOClass.getMethod("getIsValid", null);

			String methodVal = (String) (voMethod.invoke(_valueObject[i], null));
			if (methodVal.equals(Config.IS_VALID_ACTIVE) || methodVal.equals(""))
			{
				CollectionVO.add(_valueObject[i]);
			}
		}
		return CollectionVO;
	}

	/**
	 * Sets the Status to New 
	 * This will create Transaction specific collection if not already there in sesion.
	 * else this Will remove the corresponding items of collection from session. 
	 * @param _request
	 */

	public static void refreshTransState(HttpServletRequest _request)
	{
		HttpSession session = getSession(_request);
		Status objStatus = new Status();
		objStatus.add(Status.NEW);
		setStatus(_request, objStatus);
		HashSet transSpecCollection = (HashSet) session.getAttribute(Config.TRANSACTION_SPECIFIC_SESSION_ITEMS);
		if (transSpecCollection == null)
		{
			//System.out.println("inside case null");
			Collection TranspecificCollection = new HashSet();
			session.setAttribute(Config.TRANSACTION_SPECIFIC_SESSION_ITEMS, TranspecificCollection);
		}
		else
		{
			//System.out.println("refreshTransState");
			Iterator it = transSpecCollection.iterator();
			while (it.hasNext())
			{
				//System.out.println();
				session.removeAttribute((String) it.next());
			}
			//System.out.println("hh");
		}
	}

	public static void setParameter(HttpServletRequest _request, String _paramName, String _paramValue)
	{
		((HisHttpServletRequest) _request).setHisParameter(_paramName, _paramValue);
	}

	public static void setAttributeInSession(HttpServletRequest _request, String _attrName, Object _attrValue)
	{
		HttpSession session = getSession(_request);
		//System.out.println("setAttributeInSession...");
		//System.out.println("_attrName: " + _attrName);
		//System.out.println("_attrValue: " + _attrValue);
		session.setAttribute(_attrName, _attrValue);
		HashSet transSpecificColl = (HashSet) session.getAttribute(Config.TRANSACTION_SPECIFIC_SESSION_ITEMS);
		if (transSpecificColl == null) transSpecificColl = new HashSet(); // added on 2006 09 26
		transSpecificColl.add(_attrName);
	}

	/*public static void saveimage(HttpServletRequest _request,String _crNo){		
		FormFile imageFile=(FormFile)WebUTIL.getSession(_request).getAttribute(RegistrationConfig.UPLOADED_FILE);	
		FileOutputStream fos=null;
		try {	
			byte [] bytearray=imageFile.getFileData();
			String FileType=imageFile.getFileName();
			int idx=FileType.lastIndexOf(".");
			String ext=FileType.substring(idx);			
			File file=new File("c:\\PatientImages");
			 if(!file.exists()){
				 if(file.mkdir()){
					 File file1=new File("c:\\PatientImages\\"+_crNo+ext);
					 fos=new FileOutputStream(file1);
					 fos.write(bytearray);		
					 System.out.println("after writing to file first time");				 
				 }				 
			 }else
			 {
				 File file1=new File("c:\\PatientImages\\"+_crNo+ext);
				 fos=new FileOutputStream(file1);
				 fos.write(bytearray);		
				 System.out.println("after writing to file");
			 }
				 
			 
		 }
		catch (FileNotFoundException e) {		
			e.printStackTrace();
			
		}
		catch (IOException e) {		
			e.printStackTrace();
		}		
	finally{
		try{
		fos.close();}
		catch(Exception e){
			System.out.println("Exception while closing");
		}
	  }
	}*/
	/**
	 * The method return byte array from FORMFILE object as an bytearray 
	 * kept in session
	 * @param _request
	 * @return
	 */

	/*public static byte[] getByteArrayOfImage(HttpServletRequest _request){
		byte[] bytearray=(byte[])WebUTIL.getSession(_request).getAttribute(RegistrationConfig.UPLOADED_FILE);		 
		try {
			bytearray = imageFile.getFileData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return bytearray;		
	}*/
	
	public static String getAbsolutePath(HttpServletRequest _request, String _src)
	{
		String strContextPath = _request.getContextPath();
		String path = strContextPath + (_src.startsWith("/") ? "" : "/") + _src;
		return path;
	}

	public static String getAbsoluteImagePath(HttpServletRequest _request, String _src)
	{
		String strContextPath = _request.getContextPath();
		String path = strContextPath + "/hisglobal/images" + (_src.startsWith("/") ? "" : "/") + _src;
		return path;
	}

	public static void setAttribute(HttpServletRequest _request, String year, String year2)
	{
	}
/*
	public static void getPreparedChart(JFreeChart jFreeChart, HttpServletRequest _request, HttpServletResponse _response)
	{

		BufferedImage image = jFreeChart.createBufferedImage(590, 430);
		byte[] byteArray = null;
		//byte[] chartse = null;
		try
		{
			byteArray = ChartUtilities.encodeAsPNG(image);

			
			WebUTIL.setAttributeInSession(_request, Config.UPLOAD_CHART_IMAGE, byteArray);

		}
		catch (IOException ie)
		{
			ie.printStackTrace();
		}

	}
	*/

	public static ResourceBundle loadPropertiesFile(String _fileName) throws Exception
	{
		//System.out.println("fileName::::::::::::::::::::::::::::");
		String BUNDLE_NAME = _fileName;
		ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME);
		return rb;
	}

	public static String getValueFromPropertiesFile(String _filename, String _Key)
	{
		String value = "";
		try
		{
			ResourceBundle rb = loadPropertiesFile(_filename);
			System.out.println("Key" + _Key);
			value = rb.getString(_Key);
			System.out.println("value " + value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return value;
		}
		return value;
	}
	
	public static String getDepartmentCodeFromPropertiesFile(String _filename, String _Key)
	{
		String value = "";
		try
		{
			ResourceBundle rb = loadPropertiesFile(_filename);
			//System.out.println("Key" + _Key);
			value = rb.getString(_Key);
			///System.out.println("value " + value);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return value;
		}
		return value;
	}

	public static String getEntryLabel(Collection _col, String _value)
	{
		//List ls= new ArrayList(col);
		Iterator it = _col.iterator();
		while (it.hasNext())
		{
			Entry objEntry = (Entry) it.next();
			if (objEntry.getValue().equals(_value)) return objEntry.getLabel();
		}
		//System.out.println("getEntryLabel:  "+)
		return _value;
	}

	public static String addDate(String sysdate, int days)
	{
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.DATE, Integer.parseInt(sysdate.substring(0, 2).trim()));
		cl.set(Calendar.MONTH, (Integer.parseInt(sysdate.substring(3, 5).trim())) - 1);
		cl.set(Calendar.YEAR, Integer.parseInt(sysdate.substring(6, 10).trim()));
		cl.add(Calendar.DATE, days);
		Date dt = cl.getTime();
		SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
		sf.applyPattern("dd/MM/yyyy");
		String sdate = sf.format(dt);
		//System.out.println("sdate====== " + sdate);
		return sdate;
	}

	public static boolean objectIsAvailableInSession(HttpServletRequest _request, String key)
	{
		HttpSession session = _request.getSession();
		Object obj = session.getAttribute(key);
		if (obj == null || obj.equals(""))
		{
			return false;
		}
		else return true;
	}


	public static void setValueForModuleSwitching(HttpServletRequest _request, String _prefix, String _page)
	{
		HisHttpServletRequest req = (HisHttpServletRequest) _request;
		req.setHisParameter("prefix", _prefix);
		req.setHisParameter("page", _page);
	}
	
	////session containers for map/////////
	
	public static void refreshMasterSession(HttpServletRequest _request)
	{
		HttpSession session = getSession(_request);
		//Status objStatus = new Status();
		//objStatus.add(Status.NEW);
		//setStatus(_request, objStatus);
		HashSet masterSpecCollection = (HashSet) session.getAttribute(Config.MASTER_SPECIFIC_SESSION_ITEMS);
		if (masterSpecCollection == null)
		{
			//System.out.println("inside case null");
			Collection MasterSpecificCollection = new HashSet();
			session.setAttribute(Config.MASTER_SPECIFIC_SESSION_ITEMS, MasterSpecificCollection);
		}
		else
		{
			//System.out.println("refreshTransState");
			Iterator it = masterSpecCollection.iterator();
			while (it.hasNext())
			{
				//System.out.println();
				session.removeAttribute((String) it.next());
			}
			//System.out.println("hh");
		}
	}
	
	public static void setMasterAttributeInSession(HttpServletRequest _request, String _attrName, Object _attrValue)
	{
		HttpSession session = getSession(_request);
		//System.out.println("setAttributeInSession...");
		//System.out.println("_attrName: " + _attrName);
		//System.out.println("_attrValue: " + _attrValue);
		session.setAttribute(_attrName, _attrValue);
		HashSet masterSpecificColl = (HashSet) session.getAttribute(Config.MASTER_SPECIFIC_SESSION_ITEMS);
		if (masterSpecificColl == null) masterSpecificColl = new HashSet(); // added on 2006 09 26
		masterSpecificColl.add(_attrName);
	}
	
	public static String convertStringToInitcap(String _convertString)
	{
		String tempString="";
		if(_convertString!=null && !_convertString.equals(""))
		{
			_convertString.trim();
		String[] tempStringArray=_convertString.split(" ");
		for(int i=0;i<tempStringArray.length;i++)
		{
			char[] arrCh = tempStringArray[i].toCharArray();
			if(arrCh.length!=0)
			{
				arrCh[0] = Character.toUpperCase(arrCh[0]);
			tempString=tempString+new String(arrCh)+" ";
			}
		}
			//_convertString=tempString.trim();
		}
		return tempString.trim();
	}
	
	public static boolean checkForMandatoryField(String _key,PatientVO _patVO,HttpServletRequest _req)
	{
		boolean flag=false;
		try{
		String mandatoryText=getDepartmentCodeFromPropertiesFile(Config.PROPERTIES_FILE_FOR_MANDATORY_FIELD, _key);
		if(!mandatoryText.equals(""))
		{
			String[] control = mandatoryText.split("#");
			String htmlCode = "";
			String scriptString="";
			String validationString="";
			for (int i = 0; i < control.length; i++)
			{
				String[] controlStr = control[i].split(":");
				String controlName = controlStr[0];
				String controlType = controlStr[1];
				String controlLabel = controlStr[2];
				String controlMaxlength = controlStr[3];
				String controlValidation = controlStr[4];

				char[] arrCh = controlName.toCharArray();
				arrCh[0] = Character.toUpperCase(arrCh[0]);
				

				String strMethodName = new String(arrCh);
				strMethodName = "get" + strMethodName;
				Object methodValue = "";
				Method[] classVOMethod = _patVO.getClass().getMethods();

				for (int j = 0; j < classVOMethod.length; j++)
				{
					String methodName = classVOMethod[j].getName();
					
					if (methodName.equals(strMethodName))
					{
						methodValue = classVOMethod[j].invoke(_patVO, null);
						break;
					}
				}
				if(methodValue==null)
					methodValue="";
				if (controlType.equalsIgnoreCase("text"))
					{
					if(methodValue.equals("")){
					htmlCode=htmlCode+"<tr>	<td  class=\"tdfonthead\"><div align=\"right\">"+
							"<font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"+
							controlLabel+"</font></div></td>"+
							"<td  class=\"tdfonthead\"><div align=\"left\">"+
							 "<input type=\"text\" name=\""+controlName+"\" maxlength=\""+controlMaxlength+"\" "+
							 "onkeypress=\" return "+controlValidation+"\" value=\""+ methodValue+"\"/></div></td></tr>";
					}
					}
				if (controlType.equalsIgnoreCase("combo"))
				{
					String optionString="";
					if(methodValue.equals("") || methodValue.equals("-1")){
					if(controlName.equals("patMaritalStatusCode"))
					{
						List maritalStatusList=ControllerDATA.getMaritalStatusList(ControllerUTIL.getUserVO(_req));
						Iterator itr = maritalStatusList.iterator();
						
						while (itr.hasNext())
						{
							Entry objEntry = (Entry) itr.next();
							optionString = optionString + "<option value='" + objEntry.getValue() + "'>" + objEntry.getLabel() + "</option>";
						}
				
					}
					if(controlName.equals("patReligionCode"))
					{
						List religionList=ControllerDATA.getReligionList(ControllerUTIL.getUserVO(_req));
						Iterator itr = religionList.iterator();
						
						while (itr.hasNext())
						{
							Entry objEntry = (Entry) itr.next();
							optionString = optionString + "<option value='" + objEntry.getValue() + "'>" + objEntry.getLabel() + "</option>";
						}
					}
					if(controlName.equals("patOccupation") || controlName.equals("patFatherOccupation") || controlName.equals("patHusbandOccupation") )
					{
						List occupationList=ControllerDATA.getOcccupationList(ControllerUTIL.getUserVO(_req));
						Iterator itr = occupationList.iterator();
						
						while (itr.hasNext())
						{
							Entry objEntry = (Entry) itr.next();
							optionString = optionString + "<option value='" + objEntry.getValue() + "'>" + objEntry.getLabel() + "</option>";
						}	
					}
				htmlCode=htmlCode+"<tr>	<td  class=\"tdfonthead\"><div align=\"right\">"+
						"<font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"+
						controlLabel+"</font></div></td>"+
						"<td  class=\"tdfonthead\"><div align=\"left\">"+
						"<select name=\""+controlName+"\" tabindex=\"1\" value=\""+methodValue+"\"> "
						+ "<option value='-1'>Select Value</option>"
						+ optionString + "</select></div></td></tr>";
					}
				}
				
				if (controlType.equalsIgnoreCase("text")) 
				{
					if(methodValue.equals(""))
					{
						if(validationString.equals(""))
							validationString = "if(isEmpty(document.forms[0]."+ controlName + ",\"" + controlLabel + "\")";
						else
							validationString = validationString+ "&& isEmpty(document.forms[0]."+ controlName + ",\"" + controlLabel + "\")";
					}
				}
				
				if (controlType.equalsIgnoreCase("combo"))
				{
					if(methodValue.equals("") || methodValue.equals("-1"))
					{
						if(validationString.equals(""))
							validationString = "if( isSelected(document.forms[0]."+ controlName + ",\"" + controlLabel + "\")";
						else
							validationString = validationString + " && isSelected(document.forms[0]."+ controlName + ",\"" + controlLabel + "\")";
					}
				}
				
			}
			
			scriptString="<script> function validateMandatory(){"+validationString+"){ return true }}</script>";
			if(!htmlCode.equals(""))
			{
				_req.setAttribute("HTML_CODE",htmlCode+scriptString);
				
				flag=true;
				
			}
		}
	
	
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}finally
		{
			return flag;
		}
	}


}//end of class

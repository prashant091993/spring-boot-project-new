/**********************************************************
 Project:	   AHIMS_G5	
 File:         HelperMethods.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package global.Template;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HelperMethods
{
	// Static Operating System Name 
	private static final String OS_NAME = System.getProperties().getProperty("os.name");

	public static void populate(Object obj1, Object obj2)
	{
		// data from object2 is populated into obj1
		// getter of object2 is called
		// setter of object1 is called

		Class cls2 = obj2.getClass();

		Method[] cls2Methods = cls2.getMethods();

		HashMap mpGettersInCls2 = new HashMap();
		for (int i = 0; i < cls2Methods.length; i++)
		{
			if (cls2Methods[i].getName().indexOf("get") == 0)
			{
				// if the method name starts with set
				mpGettersInCls2.put(cls2Methods[i].getName().substring(3), new Integer(i));
			}
		}

		Class cls1 = obj1.getClass();
		Method[] cls1Methods = cls1.getMethods();

		int i=0;
		try
		{
			for (i = 0; i < cls1Methods.length; i++)
			{
				// System.out.println("cls1Methods[i].getName().indexOf(set): "+cls1Methods[i].getName().indexOf("set"));
				if (cls1Methods[i].getName().indexOf("set") == 0)
				{
					// if the method name starts with set
					if (mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(3)))
					{
						//System.out.println("pos2 of " + i);
						int idx = ((Integer) mpGettersInCls2.get(cls1Methods[i].getName().substring(3))).intValue();
						//System.out.println("pos3 of " + i + " cls1Methods[i].getName():  " + cls1Methods[i].getName()
						//		+ "  cls2Methods[idx].getName():  " + cls2Methods[idx].getName());
						Object str = cls2Methods[idx].invoke(obj2, null);
						// System.out.println("pos4 of "+i +" str: "+ str);
						cls1Methods[i].invoke(obj1, new Object[]
						{ str });
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		//System.out.println("after populate ");
	}

	/**
	 * sets Emergency New Patient Registration Essentials.
	 * 
	 * @param _fb EmgNewPatientRegFB form bean
	 * @param _request
	 */
	public static void checkNotNullOrEmpty(Object obj, Map map)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("get") == 0)
			{
				//System.out.println("clsMethods[i].getName()"+clsMethods[i].getName());
				//System.out.println("obj"+obj.getClass());
				try
				{
					if (clsMethods[i].invoke(obj, null) instanceof String)
					{
						String str = (String) clsMethods[i].invoke(obj, null);
						if (str.equals("")) map.put(clsMethods[i].getName().substring(3), cls.toString() + i);

					}// end of if instanceof
					else
					{
						if (clsMethods[i].invoke(obj, null) == null)
						{
							map.put(clsMethods[i].getName().substring(3), cls.toString() + i);
						}
						/*
						 * else{ if(clsMethods[i].invoke(obj,null) instanceof Object){
						 * if(clsMethods[i].getReturnType().equals(null))
						 * map.put(clsMethods[i].getName().substring(3),cls.toString()+i); } }
						 */
					}
					//System.out.println("value of i"+i);

				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}// end if getname
			
		}// end of for
	}

	public static void populatetToNullOrEmpty(Object obj1, Object obj2)
	{
		// data from object2 is populated into obj1
		// getter of object2 is called
		// setter of object1 is called

		Class cls2 = obj2.getClass();

		Method[] cls2Methods = cls2.getMethods();

		HashMap mpGettersInCls2 = new HashMap();
		for (int i = 0; i < cls2Methods.length; i++)
		{
			if (cls2Methods[i].getName().indexOf("get") == 0)
			{
				// if the method name starts with get
				mpGettersInCls2.put(cls2Methods[i].getName().substring(3), new Integer(i));
			}
		}

		Class cls1 = obj1.getClass();
		Method[] cls1Methods = cls1.getMethods();

		try
		{
			HashMap mpSetterInCls1 = new HashMap();
			checkNotNullOrEmpty(obj1, mpSetterInCls1);
			/*
			 * for(int i=0; i<cls1Methods.length; i++){ if(cls1Methods[i].getName().indexOf("get")==0){
			 * //System.out.println((String)cls1Methods[i].invoke(obj1,null));
			 * 
			 * if(cls1Methods[i].invoke(obj1,null)instanceof String) {String str=(String)cls1Methods[i].invoke(obj1,null);
			 * if(str==null || str==""){ mpSetterInCls1.put(cls1Methods[i].getName().substring(3), new Integer(i)); } } } }
			 */

			for (int i = 0; i < cls1Methods.length; i++)
			{
				// System.out.println("cls1Methods[i].getName().indexOf(set): "+cls1Methods[i].getName().indexOf("set"));
				if (cls1Methods[i].getName().indexOf("set") == 0)
				{
					// if the method name starts with set
					if (mpGettersInCls2.containsKey(cls1Methods[i].getName().substring(3))
							&& (mpSetterInCls1.containsKey(cls1Methods[i].getName().substring(3))))
					{
						//System.out.println("pos2 of " + i);
						int idx = ((Integer) mpGettersInCls2.get(cls1Methods[i].getName().substring(3))).intValue();
						//System.out.println("pos3 of " + i + " cls1Methods[i].getName():  " + cls1Methods[i].getName()
						//		+ "  cls2Methods[idx].getName():  " + cls2Methods[idx].getName());
						Object str = cls2Methods[idx].invoke(obj2, null);

						// System.out.println("pos4 of "+i +" str: "+ str);
						cls1Methods[i].invoke(obj1, new Object[]
						{ str });
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			
		}
		//System.out.println("after populate ");
	}

	
	

	public static String getSysdate(java.util.Date _dt, String _pattern)
	{
		String strDate = "";
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getInstance();
		// df.applyPattern("dd/MM/yyyy hh:mm:ss a");
		df.applyPattern(_pattern);
		strDate = df.format(_dt);
		//System.out.println("formatedDate" + strDate);
		return strDate;
	}

	public static String storeImageInCorrectFileSystem(byte[] _imageArr, String _fileName, String _newFileName, String _windowsPath, String _linuxPath)
			throws FileNotFoundException, IOException
	{
		//System.out.println("Entered in Image to Correct File System Writing ........................");

		FileOutputStream fos = null;

		String storagePath = "";
		String filePath = "";

		if (OS_NAME.startsWith("Linux"))
		{
			storagePath = "/root" + _linuxPath; // Linux Path should start from / forward slash
			filePath = storagePath + "/" + _newFileName;
		}
		else
		{
			storagePath = _windowsPath; // Windows Path should start from drive name e.g C:\ABC etc.
			filePath = storagePath + "\\" + _newFileName;
		}

		File dir = new File(storagePath);
		File file = new File(dir, _newFileName);
		if (!dir.exists()) dir.mkdir();
		if (dir.exists())
		{
			fos = new FileOutputStream(file);
			fos.write(_imageArr);
		//	System.out.println("After Writing to File........................");
		}

		try
		{
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception while closing");
		}
		return filePath;
	}

	public static String storeImageInFileSystem(byte[] _imageArr, String _fileName, String _newFileName, String _storagePath)
			throws FileNotFoundException, IOException
	{
		//System.out.println("inside storeImageInFileSystem");
		FileOutputStream fos = null;
		int idx = _fileName.lastIndexOf(".");
		String ext = _fileName.substring(idx);
		File file = new File(_storagePath);
		String filePath = _storagePath + "\\" + _newFileName;
		if (!file.exists())
		{
			if (file.mkdir())
			{
				File file1 = new File(_storagePath + "\\" + _newFileName);
				fos = new FileOutputStream(file1);
				fos.write(_imageArr);
				//System.out.println("after writing to file first time");
			}
		}
		else
		{
			File file1 = new File(_storagePath + "\\" + _newFileName);
			fos = new FileOutputStream(file1);
			fos.write(_imageArr);
			//System.out.println("after writing to file");
		}
		try
		{
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception while closing");
		}
		return filePath;
	}// end of method processimage

	public static String storeHTMLFileSystem(String _fileArray, String _fileName, String _newFileName, String _storagePath)
			throws FileNotFoundException, IOException
	{
		//System.out.println("inside storeImageInFileSystem");
		FileOutputStream fos = null;
		int idx = _fileName.lastIndexOf(".");
		String ext = _fileName.substring(idx);
		File file = new File(_storagePath);
		byte[] byteArray = _fileArray.getBytes();
		String filePath = _storagePath + "\\" + _newFileName;
		if (!file.exists())
		{
			if (file.mkdir())
			{
				File file1 = new File(_storagePath + "\\" + _newFileName);
				fos = new FileOutputStream(file1);
				fos.write(byteArray);
				//System.out.println("after writing to file first time");
			}
		}
		else
		{
			File file1 = new File(_storagePath + "\\" + _newFileName);
			fos = new FileOutputStream(file1);
			fos.write(byteArray);
			//System.out.println("after writing to file");
		}
		try
		{
			fos.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception while closing");
		}
		return filePath;
	}// end of method processimage

	public static byte[] getByteArrayOfImage(String _fileNameWithPath) throws IOException, FileNotFoundException
	{
		byte[] readBytes = null;
		try
		{
			FileInputStream fos = new FileInputStream(_fileNameWithPath);
			readBytes = new byte[fos.available()];
			fos.read(readBytes);

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			// throw new HisRecordNotFoundException("No Specified Image Record Found");
		}
		return readBytes;
	}

	/*public static void populateParameteresForReport(Map _parameterMap, ReportVO _reportVO)
	{

		Class reportVOClass = _reportVO.getClass();
		Method[] methodsReportVO = reportVOClass.getMethods();
		try
		{
			for (int i = 0; i < methodsReportVO.length; i++)
			{
				String methodName = methodsReportVO[i].getName();
				String keyName = methodName.substring(3);
				String k = keyName.substring(1);
				String fistChar = keyName.substring(0, 1).toLowerCase();
				String finalKey = fistChar + k;
				if (methodName.startsWith("get"))
				{
					Object methodValue = methodsReportVO[i].invoke(_reportVO, null);
					if (methodValue instanceof String)
					{
						String Value = (String) methodValue;
						_parameterMap.put(finalKey, Value);
					}
				}
			}
		}
		catch (Exception e)
		{
			throw new HISApplicationExecutionException();

		}

	}*/

	/*
	 * public static List getOpdMenuDtl(HttpServletRequest request){ HttpSession session=request.getSession(); List
	 * list=(List) session.getAttribute(OpdConfig.OPD_DESK_MENU_DTL); OpdEssentialDelegate opdEssentialDelegate=new
	 * OpdEssentialDelegate(); if(list==null){ list=opdEssentialDelegate.getOpdMenuDetail(ControllerUTIL.getUserVO(request));
	 * session.setAttribute(OpdConfig.OPD_DESK_MENU_DTL,list); } return list; }
	 */
	public static String getFileName(char seprator, String[] collecOfNameParts)
	{
		String fileName = "";
		for (int i = 0; i < collecOfNameParts.length; i++)
		{
			fileName = fileName + collecOfNameParts[i];
			if (i < (collecOfNameParts.length - 1))
			{
				fileName = fileName + seprator;
			}
		}
		return fileName;
	}

	public static HashMap createQueryMapFromVO(Object _classVOObject)
	{
		HashMap queryMap = new HashMap();
		Class classVO = _classVOObject.getClass();
		Method[] classVOMethod = classVO.getMethods();

		for (int i = 0; i < classVOMethod.length; i++)
		{
			String methodName = classVOMethod[i].getName();
			Object methodValue = "";

			String mapKey = "";
			if (methodName.indexOf("get") == 0)
			{
				try
				{
					methodValue = classVOMethod[i].invoke(_classVOObject, null);
					boolean fl = methodValue instanceof String;

				}
				catch (IllegalArgumentException e)
				{

					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{

					e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{

					e.printStackTrace();
				}
				if ((methodValue instanceof String) && (methodValue != null && !methodValue.equals("") && !methodValue.equals("-1")))
				{
					mapKey = methodName.substring(3);
					mapKey = mapKey.toLowerCase();
					queryMap.put(mapKey, methodValue);

				}

			}
		}

		return queryMap;
	}
	
	
	
	/**
	 * Set Null to empty
	 * @param obj
	 * @param map
	 */
	public static void setNullToEmpty(Object obj)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{""};
		List <String> methodList=new ArrayList<String>();
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("get") == 0)
			{
				try
				{
					if (clsMethods[i].invoke(obj, null) == null)
					{
						Class returnType=clsMethods[i].getReturnType();
						//System.out.println(clsMethods[i].getDeclaringClass());
						if (returnType==String.class && clsMethods[i].getDeclaringClass()==cls) {
							setEmpty(obj, clsMethods[i].getName().substring(3));
						}	
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}// end if getname
		}// end of for
		
	}
	
	
	/**
	 * Set Null to empty
	 * @param obj
	 * @param map
	 */
	public static void setEmptyToNull(Object obj)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{""};
		List <String> methodList=new ArrayList<String>();
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("get") == 0)
			{
				try
				{
					if (clsMethods[i].invoke(obj, null) == "")
					{
						Class returnType=clsMethods[i].getReturnType();
						//System.out.println(clsMethods[i].getDeclaringClass());
						if (returnType==String.class && clsMethods[i].getDeclaringClass()==cls) {
							setNull(obj, clsMethods[i].getName().substring(3));
						}	
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}// end if getname
		}// end of for
		
	}
	
	public static void setNull(Object obj,String methodName)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{null};
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("set") == 0)
			{
				try
				{
					//System.out.println("Method Name : " + clsMethods[i].getName());
					if (clsMethods[i].getName().substring(3).equalsIgnoreCase(methodName))
					{
						clsMethods[i].invoke(obj, arg);
						
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}// end for
		
	}	
	public static void setEmpty(Object obj,String methodName)
	{
		Class cls = obj.getClass();
		Method[] clsMethods = cls.getMethods();
		String arg[]=new String[]{""};
		for (int i = 0; i < clsMethods.length; i++)
		{
			if (clsMethods[i].getName().indexOf("set") == 0)
			{
				try
				{
					//System.out.println("Method Name : " + clsMethods[i].getName());
					if (clsMethods[i].getName().substring(3).equalsIgnoreCase(methodName))
					{
						clsMethods[i].invoke(obj, arg);
						
					}
				}// end try
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}// end for
		
	}	
	/*public static String getSysDateFormate(String sysDate) {
		Calendar cal=Calendar.getInstance();
		
		
		investigation.InvestigationConfig.month [] monthArray=investigation.InvestigationConfig.month.values();
		try
		{
			System.out.println("Date changed =");
		String [] arrayDate=sysDate.replace('/','#').split("#");
		
		cal.set(Integer.parseInt(arrayDate[2].substring(0,4)),(Integer.parseInt(arrayDate[1])-1),Integer.parseInt(arrayDate[0]));
		System.out.println("Date changed ="+cal.get(Calendar.DAY_OF_MONTH)+"-"+monthArray[cal.get(Calendar.MONTH)]+"-"+cal.get(Calendar.YEAR));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		return cal.get(Calendar.DAY_OF_MONTH)+"-"+monthArray[cal.get(Calendar.MONTH)]+"-"+cal.get(Calendar.YEAR);
	}

	public static String getDOB(String sysDate,int age, String ageUnit)
	{
		//String sysDate="10-Nov-2007";
		Calendar cal=Calendar.getInstance();
		
		String [] arrayDate=sysDate.replace('-','#').split("#");
		investigation.InvestigationConfig.month [] monthArray=investigation.InvestigationConfig.month.values();
		int mon=0;
		for(int i=0;i<monthArray.length;i++)
		{
			if(monthArray[i].toString().equalsIgnoreCase(arrayDate[1]))
				mon=i;
		}
		
		cal.set(Integer.parseInt(arrayDate[2]),mon,Integer.parseInt(arrayDate[0]));
		if(ageUnit.equalsIgnoreCase(InvestigationConfig.YEAR))
			cal.add(Calendar.YEAR,-age);
		else if(ageUnit.equalsIgnoreCase(InvestigationConfig.MONTH))
			cal.add(Calendar.MONTH,-age);
		else if(ageUnit.equalsIgnoreCase(InvestigationConfig.WEEK))
			cal.add(Calendar.DAY_OF_WEEK_IN_MONTH,-age);
		else
			cal.add(Calendar.DAY_OF_WEEK,-age);
		
		return cal.get(Calendar.DAY_OF_MONTH)+"-"+monthArray[cal.get(Calendar.MONTH)]+"-"+cal.get(Calendar.YEAR);
	 
	}*/
	
}

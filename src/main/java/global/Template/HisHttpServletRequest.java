package global.Template;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HisHttpServletRequest extends HttpServletRequestWrapper
{

	Map mpHisParams = new HashMap();

	public HisHttpServletRequest(HttpServletRequest request)
	{
		super(request);
	}

	public void setHisParameter(String _paramName, String _paramValue)
	{
		this.mpHisParams.put(_paramName, _paramValue);
	}

	public Enumeration getParameterNames()
	{
		
		Map newMp = new HashMap();
		newMp.putAll(super.getParameterMap());
		newMp.putAll(this.mpHisParams);
		// Collection colParamSet = newMp.values();

		Vector col = new Vector(newMp.keySet());
		
		Enumeration names = col.elements();
		while (names.hasMoreElements())
		{
			String name = (String) names.nextElement();
			String stripped = name;
		}
		return col.elements();
	}

	public Map getParameterMap()
	{
		
		Map newMp = new HashMap();
		newMp.putAll(super.getParameterMap());
		newMp.putAll(this.mpHisParams);
		
		return newMp;
	}

	public String[] getParameterValues(String arg0)
	{
		//System.out.println("getParameterValues (" + arg0 + ")");
		Map newMp = new HashMap();
		newMp.putAll(super.getParameterMap());
		newMp.putAll(this.mpHisParams);
		// String[] arrString = new String[newMp.size()];
		Object objParamVal = newMp.get(arg0);
		if (objParamVal == null) return null;
		/*
		 * Collection col = null; if(objParamVal.getClass().isArray()){ col = Arrays.asList((Object[])objParamVal); }else{
		 * col = new ArrayList(); col.add((String)objParamVal); }
		 * 
		 * Iterator it = col.iterator(); int i=0; while(it.hasNext()){ //System.out.println("inside
		 * getParameterValues"+(String)it.next()); arrString[i++]=(String)it.next(); //System.out.println("inside
		 * getParameterValues"+(String)it.next()); }
		 */
		
		String[] arrString = new String[1];
		if (!objParamVal.getClass().isArray())
		{
			arrString[0] = (String) objParamVal;
			objParamVal = arrString;
		}
		//System.out.println("objParamVal:::::" + objParamVal.getClass());
		return (String[]) objParamVal;
	}

	public String getParameter(String arg0)
	{
		//System.out.println("getParameter (" + arg0 + ")");
		Map newMp = new HashMap();
		newMp.putAll(super.getParameterMap());
		newMp.putAll(this.mpHisParams);
		//System.out.println("getParameter" + newMp.get(arg0));
		Object objParam = newMp.get(arg0);
		String strParam = null;
		if (objParam != null && objParam.getClass().isArray())
		{
			Object obj[] = (Object[]) objParam;
			/*for (int i = 0; i < obj.length; i++)
			{
				System.out.println("array: [" + i + "] -   " + obj[i]);
			}*/
			strParam = (String) obj[0];
		}
		else strParam = (String) newMp.get(arg0);

		return strParam;
	}
}

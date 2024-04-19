package com.service;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.model.LabReportPDFObject;

import global.transactionmgnt.HisDAO;

@Service
@ComponentScan("com.config.*")
public class LabReportPDFService 
{
	public ArrayList<LabReportPDFObject> getpdf(String crno,String hospcode,String reqdno)
	{
        ArrayList<LabReportPDFObject> messageData = new ArrayList<LabReportPDFObject>();
		HisDAO dao = null;
		WebRowSet ws =null;
		String strProcName = "",strerr="";
		int nprocIndex = 0;
		String base64EncodedString="";
		try 
		{
	        System.out.println("------------------ LabReportPDFService.getpdf ----[pkg_pwa_mst.proc_labreport_list]----------------");
	        dao = new HisDAO("MMS", "DrugInventoryTransDAO");
	        strProcName = "{call pkg_pwa_mst.proc_labreport_pdf(?,?,?,?,?)}";				       
	        nprocIndex = dao.setProcedure(strProcName);				
			dao.setProcInValue(nprocIndex, "modeval", 		    "1");						
			dao.setProcInValue(nprocIndex, "hosp_code",	        hospcode);
			dao.setProcInValue(nprocIndex, "reqdno",	        reqdno);
			dao.setProcOutValue(nprocIndex, "err",			     1); 
			dao.setProcOutValue(nprocIndex, "resultset", 	     2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
			{
				strerr = "";
			}
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			while(ws.next())
		    {
				LabReportPDFObject mobRptObject = new LabReportPDFObject();			   	 
		   		mobRptObject.setPdfname(ws.getString(1));
		   		System.out.println("Filename: "+mobRptObject.getPdfname());
		   		messageData.add(mobRptObject);
		   		if(mobRptObject.getPdfname() != "" || mobRptObject.getPdfname() != null)
		   		{
		        	byte[] byteArr = getReport(mobRptObject.getPdfname(), hospcode);
		        	base64EncodedString = org.apache.commons.codec.binary.Base64.encodeBase64String(byteArr);
		        }
		   		mobRptObject.setPdfreport(base64EncodedString);
		    }				
			if (strerr.equals("")) 
			{
			} 
			else 
			{
				throw new Exception(strerr);
			}
			
		} 
		catch (Exception e) 
		{					
			e.printStackTrace();
		}
		return messageData;
	}
	
	public static  byte[] getReport(String filename, String hosCode) 
	{
		byte[] byteArray=null;
		String crNo=filename.substring(0,15);
		String patientcreatefileftpaddress = setFTPDetails("ReportFTPConnection" , hosCode);
		System.out.println("patientcreatefileftpaddress : "+patientcreatefileftpaddress);
		System.out.println("filename:::::"+filename);
		String year=    crNo.substring(5,7); //MergeAllPdfNewInv.getYear(crNo); 
	    String insideyear = getInsideYear(crNo);
	    String count= getcount(crNo);
	    String strPdfPath = patientcreatefileftpaddress+"/"+hosCode+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+filename.substring(0,15)+"/"+filename;
		System.out.println("Fetch PDF from following path : "+strPdfPath);
		try 
		{
			URL urlftp=new URL(strPdfPath);
			URLConnection urlcon=urlftp.openConnection();
			System.out.println("urlcon:: "+urlcon.toString());
			//byteArray = IOUtils.toByteArray(urlcon.getInputStream());
		} 
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    	
	    return byteArray;
	}
	
public static String getInsideYear(String crNo) 
{
		  int insideYear=1000000;
		  String compareYearWith = crNo.substring(7,14);	 
		  if (Integer.parseInt(compareYearWith) <= 100000)
		  {
				  insideYear=100000;
	      }
		  else  if ((Integer.parseInt(compareYearWith)) > 100000 && (Integer.parseInt(compareYearWith)) <= 200000)
		  {
			  insideYear=200000;
		  }
		  else  if ((Integer.parseInt(compareYearWith)) > 200000 && (Integer.parseInt(compareYearWith)) <= 300000)
		  {
			  insideYear=300000;
		  }
		  else  if ((Integer.parseInt(compareYearWith)) > 300000 && (Integer.parseInt(compareYearWith)) <= 400000)
		  {
			  insideYear=400000;
		  }
		  else  if ((Integer.parseInt(compareYearWith)) > 400000 && (Integer.parseInt(compareYearWith)) <= 500000)
		  {
			  insideYear=500000;
		  }
		  else  if ((Integer.parseInt(compareYearWith)) > 500000 && (Integer.parseInt(compareYearWith)) <= 600000)
		  {
			  insideYear=600000;
		  }
		  
		  else  if ((Integer.parseInt(compareYearWith)) > 600000 && (Integer.parseInt(compareYearWith)) <= 700000)
		  {
			  insideYear=700000;
		  } 
		  else  if ((Integer.parseInt(compareYearWith)) > 700000 && (Integer.parseInt(compareYearWith)) <= 800000)
		  {
			  insideYear=800000;
		  }
		  else  if ((Integer.parseInt(compareYearWith)) > 800000 && (Integer.parseInt(compareYearWith)) <= 900000)
		  {
			  insideYear=900000;
		  }
		  else  if ((Integer.parseInt(compareYearWith)) > 900000 && (Integer.parseInt(compareYearWith)) <= 1000000)
		  {
			  insideYear=1000000;
		  }
		  return Integer.toString(insideYear);    
}

public static String getcount(String crNo) 
{
	  int count=100000;
	  String last5Digits = crNo.substring(9,14); 
	  int digit=Integer.parseInt(last5Digits); 
	  if (digit <= 10000)
	  {
		  count=10000;
	  }
	   else  if ((digit) > 10000 && (digit) <= 20000)
	   {
		   count=20000;
	   }
	   else  if ((digit) > 20000 && (digit) <= 30000)
	   {
		   count=30000;
	   }
	   else  if ((digit) > 30000 && (digit) <= 40000)
	   {
		   count=40000;
	   }
	   else  if ((digit) > 40000 && (digit) <= 50000)
	   {
		   count=50000;
	   }
	   else  if ((digit) > 50000 && (digit) <= 60000)
	   {
		   count=60000;
	   }
	   else  if ((digit) > 60000 && (digit) <= 70000)
	   {
		   count=70000;
	   }
	   else  if ((digit) > 70000 && (digit) <= 80000)
	   {
		   count=80000;
	   }
	   else  if ((digit) > 80000 && (digit) <= 90000)
	   {
		   count=90000;
	   }
	   else  if ((digit) > 90000 && (digit) <= 99999)
	   {
		   count=100000;
	   }
	  return Integer.toString(count);
	}

public  static String setFTPDetails(String propHeader, String hospcode) 
{
	HisDAO dao = null;
	WebRowSet ws =null;
	String strProcName = "",strerr="";
	int nprocIndex = 0;
	String ftpDtl=null;
	try
	{
		System.out.println("------------------ LabReportPDFService.setFTPDetails ----[pkg_pwa_mst.proc_pdf_ftpdtl]----------------");
        dao = new HisDAO("MMS", "DrugInventoryTransDAO");
        strProcName = "{call pkg_pwa_mst.proc_pdf_ftpdtl(?,?,?,?)}";				       
        nprocIndex = dao.setProcedure(strProcName);				
		dao.setProcInValue(nprocIndex, "modeval", 		    "1");						
		dao.setProcInValue(nprocIndex, "hosp_code",	        hospcode);
		dao.setProcOutValue(nprocIndex, "err",			     1); 
		dao.setProcOutValue(nprocIndex, "resultset", 	     2);
		dao.executeProcedure(nprocIndex);
		strerr = dao.getString(nprocIndex, "err");
		if (strerr == null)
		{
			strerr = "";
		}
		ws = dao.getWebRowSet(nprocIndex, "resultset");
		while(ws.next())
	    {
			ftpDtl=ws.getString(1);
	    }				
		if (strerr.equals("")) 
		{
		} 
		else 
		{
			throw new Exception(strerr);
		}

	}		 
	catch (Exception e)
	{
		e.printStackTrace();
	} 
	return ftpDtl;
}
}

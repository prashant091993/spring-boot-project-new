package com.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("com.config.*")
public class ReportDownloadService 
{
	
	 public static String digilocker_dev_url = "http://10.10.10.193:8082";	 
	 public static String testlist_source = "digilocker";
	 public static String testlist_key = "123e4567e89b12d3a456556642440000";
	 
	public  String getList(String cr_no) 
	{
		String testList = null;
		String json=null;		
		try 
		{

			System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

			String digilocker_getTestList_url = digilocker_dev_url;
			System.out.println("digilocker_getTestList_url---------->"+digilocker_getTestList_url);
			String source = testlist_source;
			String key = testlist_key;
						
			URL obj = new URL(digilocker_getTestList_url + "/HISInvestigationServices/rest/getTestDtl/getTestListBasdOnCr?cr="+cr_no+"&source="+source+"&key="+key);
			
			//URL obj = new URL(digilocker_getTestList_url + "/HISInvestigationServices/rest/getTestDtl/getTestList?name=%20Dr%20Ajit%20Kumar%20&mobileNo=7979756933&gender=M&dob=\"\"&source=digilocker&key=123e4567e89b12d3a456556642440000");
			
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			StringBuffer responses = new StringBuffer();
			while ((inputLine = in.readLine()) != null) 
			{
					responses.append(inputLine);
					testList = responses.toString();
					JSONArray response=new JSONArray(testList);
					
					for (int i = 0; i < response.length(); i++) 
					{
						JSONObject jsonObject=response.getJSONObject(i);
						JSONArray data=jsonObject.getJSONArray("data");
						//System.out.println("data " + data);
						json = "{\"data\": [";
						for(int z=0;z<data.length();z++) 
						{
								JSONObject datajObj   =	data.getJSONObject(z);
								String testname       = datajObj.getString("testname");								
								String testdate       = datajObj.getString("testdate");							
								String requisitiondno = datajObj.getString("RequisitionDno");
								json += "[\"";
								json += testdate;
								json += "\",\"";
								json += testname;								
								json += "\",\"";
								json += "<button type='button' class='btn btn-secondary btn-sm' onclick=downloadPDF('"+requisitiondno+"') value= '"+requisitiondno+"'><span class='fas fa-download' aria-hidden='true'></span></button>";
								json += "\"]";
								if(z != data.length() -1)
									json += ",";
						
						}
						json += "]}";
					 
					}
					if(json == null || json.equals(""))
						json = "{\"data\" : []}";
					
					
			}
			in.close();
			System.out.println("json " + json.toString());
			testList = responses.toString();
			System.out.println("response " + testList);
						
		} 
		catch (Exception ex) {
			testList = null;
			ex.printStackTrace();
		}
		return json;
	
	}
	
	public static String getPDF(String req_d_no) 
	{
		String pdfjson = null;
			
		try 
		{
			
			String digilocker_getpdf_url = digilocker_dev_url;
			String source 				 = testlist_source;
			String key 					 = testlist_key;	
						
			URL obj = new URL(digilocker_getpdf_url + "/HISInvestigationServices/rest/getTestDtl/getReport?requisitionNo="+req_d_no+"&source="+source+"&key="+key);
			
			
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			StringBuffer responses = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
					responses.append(inputLine);
					pdfjson = responses.toString();
					
			//	System.out.println("pdfjson " + pdfjson);
					
				
					
			}
			in.close();
			
		}
		catch (Exception ex) {
			pdfjson = null;
			ex.printStackTrace();
		}
		return pdfjson;
	}



}

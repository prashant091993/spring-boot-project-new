package global.Template;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ControllerUTIL
{
	/**
	 * @roseuid 45C2F539034B
	 */
	public ControllerUTIL()
	{
	}
	/**
	 * @param request
	 * @roseuid 45C1AAA20186
	 */
	public static void initialize(HttpServletRequest request)
	{
		WebUTIL.refreshTransState(request);
	}

	
	/*public static UserVO getUserVO(HttpServletRequest request)
	{
		UserVO userVO=null;
		try
		{
			userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			HospitalMstVO objHospitalMstVO=(HospitalMstVO)request.getSession().getAttribute(HISConfig.HOSPITAL_VO);
			userVO.setStrHospitalMstVo(objHospitalMstVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userVO;
	}*/

	public static UserVO getUserVO(HttpServletRequest request)
	{
		UserVO userVO = (UserVO) request.getAttribute(Config.USER_VO);
		if (userVO == null)
		{
			userVO = new UserVO();

			// ////////////getting seat id from session and setting it to uservo///////
			String seatID = (String) request.getAttribute(Config.SEAT_ID);
			if (seatID == null)
			{
				seatID = (String) request.getSession().getAttribute(Config.SEAT_ID);
				if (seatID == null) seatID = request.getParameter(Config.SEAT_ID);
			}
			userVO.setSeatId(seatID);
			request.getSession().setAttribute(Config.USER_VO, userVO);
			// if (seatID == null) userVO.setSeatId("10008");
			if (seatID == null) userVO.setSeatId("10001");

			// /////////////Getting HOSPITAL_ID from session and setting it to uservo///////
			String hospitaCode = (String) request.getAttribute(Config.HOSPITAL_CODE);
			if (hospitaCode == null)
			{
				hospitaCode = (String) request.getSession().getAttribute(Config.HOSPITAL_CODE);
				if (hospitaCode == null) hospitaCode = request.getParameter(Config.HOSPITAL_CODE);
			}
			userVO.setHospitalCode(hospitaCode);

			request.getSession().setAttribute(Config.USER_VO, userVO);
			if (hospitaCode == null) userVO.setHospitalCode(Config.HOSPITAL_CODE_VALUE);

			// /////////////IP_ADDRESS from session and setting it to uservo///////
			String ipAddress = (String) request.getAttribute(Config.IP_ADDRESS);
			if (ipAddress == null)
			{
				ipAddress = (String) request.getSession().getAttribute(Config.IP_ADDRESS);
				if (ipAddress == null) ipAddress = request.getParameter(Config.IP_ADDRESS);
			}
			userVO.setIpAddress(ipAddress);
			request.getSession().setAttribute(Config.USER_VO, userVO);

			// Getting User seat id from session and place into UserVO ***************
			String userSeatId = (String) request.getAttribute(Config.USER_SEAT_ID);
			if (userSeatId == null)
			{
				userSeatId = (String) request.getSession().getAttribute(Config.USER_SEAT_ID);
				if (userSeatId == null) userSeatId = request.getParameter(Config.USER_SEAT_ID);
			}
			userVO.setUserSeatId(userSeatId);
			request.getSession().setAttribute(Config.USER_VO, userVO);
			if (userSeatId == null) userVO.setUserSeatId("10003");

			// Get Employee ID from session and place into User VO *********************
			String userEmpId = (String) request.getAttribute(Config.USER_EMP_ID);
			if (userEmpId == null)
			{
				userEmpId = (String) request.getSession().getAttribute(Config.USER_EMP_ID);
				if (userEmpId == null) userEmpId = request.getParameter(Config.USER_EMP_ID);
			}
			userVO.setUserEmpID(userEmpId);

			// Get Employee Full Name from session and place into User VO *********************
			String userEmpName = (String) request.getAttribute(Config.USER_FULL_NAME);
			if (userEmpName == null)
			{
				userEmpName = (String) request.getSession().getAttribute(Config.USER_FULL_NAME);
				if (userEmpName == null) userEmpName = request.getParameter(Config.USER_FULL_NAME);
			}
			userVO.setUserName(userEmpName);

			// Get User Level from session and place into User VO *********************
			String userLevel = (String) request.getAttribute(Config.USER_LEVEL);
			if (userLevel == null)
			{
				userLevel = (String) request.getSession().getAttribute(Config.USER_LEVEL);
				if (userLevel == null) userLevel = request.getParameter(Config.USER_LEVEL);
			}
			userVO.setUserLevel(userLevel);

			request.getSession().setAttribute(Config.USER_VO, userVO);
		}
		TransactionVO transactionVO = userVO.getTransactionInfo();
		if (transactionVO == null)
		{
			transactionVO = new TransactionVO();
			userVO.setTransactionInfo(transactionVO);
		}
		String menuID = (String) request.getSession().getAttribute(Config.MENU_ID);
		if (menuID == null) menuID = "10000";
		transactionVO.setMenuID(menuID);

		return userVO;

	}

	public static void setSysdate(HttpServletRequest _request)
	{
		Status objStatus = new Status();
		try
		{
			// String[] dateAndCrNoFormat=ControllerDATA.getSysDate(getUserVO(_request));

			// Date dateObj=ControllerDATA.getSysDateAsDate();
			List dateAndCrNoFormat = ControllerDATA.getSystemDateAndFormat(getUserVO(_request));
			
			String sysdate = (String) dateAndCrNoFormat.get(0);
			String defalutCrNoFormat = (String) dateAndCrNoFormat.get(1);
			Date dateObj = (Date) dateAndCrNoFormat.get(2);

			_request.getSession().setAttribute(Config.SYSDATE, sysdate);
			_request.getSession().setAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT, defalutCrNoFormat);
			_request.getSession().setAttribute(Config.SYSDATEOBJECT, dateObj);
			
			// isRegistrationAllowed(registration, _hospitalCode);
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);			
		}
	}

	public static String settingThePageString(List sessionList, String _recordPerPage, String _pagesPerBlock)
	{
		String Str = "";
		int TotalNopages = (sessionList.size() % Integer.parseInt(_recordPerPage) == 0) ? sessionList.size()
				/ Integer.parseInt(_recordPerPage) : sessionList.size() / Integer.parseInt(_recordPerPage) + 1;
		// int recordsPerPage = Integer.parseInt(_recordPerPage);
		int pagesperblock = Integer.parseInt(_pagesPerBlock);
		int totalNoBlock = (TotalNopages % pagesperblock == 0) ? TotalNopages / pagesperblock : TotalNopages / pagesperblock
				+ 1;
		int block = 1;
		int page = 1;
		System.out.println("TotalNopages  =" + TotalNopages + "  totalNoBlock=" + totalNoBlock + " block=" + block
				+ " page=" + page);
		for (int i = 1; i <= TotalNopages; i++)
		{
			if ((i % pagesperblock) == 1)
			{
				if (((i / pagesperblock) + 1) == block) Str += "<div id='block" + ((i / pagesperblock) + 1)
						+ "' style='display:block' >";
				else Str += "<div id='block" + ((i / pagesperblock) + 1) + "' style='display:none' >";
				if (((i / pagesperblock) + 1) > 1)
				{
					Str += "<a name='prev'  onClick='showPrevious()'><font Style='cursor:pointer'>  Prev  </font></a>";
				}
			}
			if (i == page)
			{
				Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
						+ "\",\"GO\")' style='color:red'><font Style='cursor:default'>&nbsp;" + i + "</font></a>";
			}
			else Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
					+ "\",\"GO\")' style='color:blue'><font Style='cursor:pointer'>&nbsp;" + i + "</font></a>";
			if ((i % pagesperblock) == 0)
			{
				if (((i / pagesperblock)) + 1 <= totalNoBlock)
				{
					Str += "<a name='next'  onClick='showNext()'><font Style='cursor:pointer'>  next  </font></a>";
				}
				Str += "</div>";
			}
		}
		Str += "</div>";
		System.out.println("Str   =" + Str);
		return Str;

	}

	public static String settingThePageString(ValueObject[] _VO, String _recordPerPage, String _pagesPerBlock)
	{
		String Str = "";
		int TotalNopages = (_VO.length % Integer.parseInt(_recordPerPage) == 0) ? _VO.length
				/ Integer.parseInt(_recordPerPage) : _VO.length / Integer.parseInt(_recordPerPage) + 1;
		// int recordsPerPage = Integer.parseInt(_recordPerPage);
		int pagesperblock = Integer.parseInt(_pagesPerBlock);
		int totalNoBlock = (TotalNopages % pagesperblock == 0) ? TotalNopages / pagesperblock : TotalNopages / pagesperblock
				+ 1;
		int block = 1;
		int page = 1;
		System.out.println("TotalNopages  =" + TotalNopages + "  totalNoBlock=" + totalNoBlock + " block=" + block
				+ " page=" + page);
		for (int i = 1; i <= TotalNopages; i++)
		{
			if ((i % pagesperblock) == 1)
			{
				if (((i / pagesperblock) + 1) == block) Str += "<div id='block" + ((i / pagesperblock) + 1)
						+ "' style='display:block' >";
				else Str += "<div id='block" + ((i / pagesperblock) + 1) + "' style='display:none' >";
				if (((i / pagesperblock) + 1) > 1)
				{
					Str += "<a name='prev'  onClick='showPrevious()'><font Style='cursor:pointer'>  Prev  </font></a>";
				}
			}
			if (i == page)
			{
				Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
						+ "\",\"GO\")' style='color:red'><font Style='cursor:default'>&nbsp;" + i + "</font></a>";
			}
			else Str += "<a name=a" + i + " value=" + i + " onClick='return showPage(\"" + i
					+ "\",\"GO\")' style='color:blue'><font Style='cursor:pointer'>&nbsp;" + i + "</font></a>";
			if ((i % pagesperblock) == 0)
			{
				if (((i / pagesperblock)) + 1 <= totalNoBlock)
				{
					Str += "<a name='next'  onClick='showNext()'><font Style='cursor:pointer'>  next  </font></a>";
				}
				Str += "</div>";
			}
		}
		Str += "</div>";
		//System.out.println("Str   =" + Str);
		return Str;

	}
	

	/*public static HospitalMstVO getHospitalVO(HttpServletRequest _request)
	{
		HospitalMstVO objHospitalMstVO = null;
		try
		{
			objHospitalMstVO=(HospitalMstVO)_request.getSession().getAttribute(HISConfig.HOSPITAL_VO);
		}
		catch (Exception e)
		{
			e.printStackTrace();	
		}
		return objHospitalMstVO;
	}*/



	public static HospitalMstVO getHospitalVO(HttpServletRequest _request)
	{
		HospitalMstVO hospitalMstVO=new HospitalMstVO();
		String hospitalCode=(String)_request.getSession().getAttribute(Config.HOSPITAL_CODE);
		try{
			hospitalMstVO=(HospitalMstVO)_request.getSession().getAttribute(Config.HOSPITAL_VO);
			if(hospitalMstVO==null || hospitalMstVO.getHospitalName().equals(""))
			{
				
//				if(hospitalMstVO.getHospitalName()==null)
//					hospitalMstVO.setHospitalName("");
//				if(hospitalMstVO.getAddress1()==null)
//					hospitalMstVO.setAddress1("");
//				if(hospitalMstVO.getAddress2()==null)
//					hospitalMstVO.setAddress2("");
//				if(hospitalMstVO.getCity()==null)
//					hospitalMstVO.setCity("");
//				if(hospitalMstVO.getHospitalShortName()==null)
//					hospitalMstVO.setHospitalShortName("");
//				if(hospitalMstVO.getState()==null)
//					hospitalMstVO.setState("");
				WebUTIL.setNullToEmpty(hospitalMstVO);
				
				_request.getSession().setAttribute(Config.HOSPITAL_VO, hospitalMstVO);
			}
			
		}catch (Exception e)
		{
		e.printStackTrace();	
		}
		return hospitalMstVO;
	}

	public static String isRegistrationAllowed(String _categoryCode, UserVO _userVO)
	{
		String registrationAllowed="";
		///making rgistration timin check configurable
		if(Config.TIME_BOUND_REGISTRATION_REQUIRED.equals(Config.TIME_BOUND_REGISTRATION_REQUIRED_YES))
			registrationAllowed= ControllerDATA.isRegistrationAllowed(_categoryCode, _userVO);
		
		return registrationAllowed;
	}

	/*
	 * public static String isRegistrationAllowed(String _categoryCode,HttpServletRequest _request){ String isAllowed=null;
	 * Status status=new Status(); try{ isAllowed=ControllerDATA.isRegistrationAllowed(_categoryCode, getUserVO(_request)); }
	 * catch (HisRegistrationTimingExpiredException e){ e.printStackTrace(); WebUTIL.removeFromStatus(_request,Status.NEW);
	 * status.add(Status.ERROR_AE,e.getMessage(),""); } catch(HisRecordNotFoundException e){
	 * 
	 * e.printStackTrace(); WebUTIL.removeFromStatus(_request,Status.NEW); status.add(Status.ERROR_AE,e.getMessage(),""); }
	 * catch(HisDataAccessException e){ e.printStackTrace(); WebUTIL.removeFromStatus(_request,Status.NEW);
	 * status.add(Status.ERROR_AE,e.getMessage(),""); } catch(HisException e){ e.printStackTrace();
	 * WebUTIL.removeFromStatus(_request,Status.NEW); status.add(Status.ERROR_AE,e.getMessage(),""); } finally{
	 * WebUTIL.setStatus(_request,status); } return isAllowed; }
	 */

}

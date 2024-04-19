package com.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@ComponentScan("com.config.*")

/**
 * The Class TokenConfig.
 */
public class TokenConfig {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#generateToken(javax.servlet.http.
	 * HttpServletRequest)
	 */
	public String generateToken(HttpServletRequest request) 
	{
		//System.out.println("Indie Token Config---->"+request.getSession().getAttribute("LANGUAGE"));
	 
				  
		saveToken(request);
		return "1";
	}

	/**
	 * Validate token. 
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public String validateToken(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String retVal = "0";
		try {
			if (isTokenValid(request))
				retVal = "1";
			else
				retVal = "0";
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "0";
		}
        //System.out.println("--T/F-->"+retVal);
		freeToken(request);

		if (retVal.equals("0")) {
			/*response.sendRedirect("/DWH_CMSS/startup/index_exeption.jsp");*/
			response.sendRedirect("/NPPA/WebContent/WEB-INF/view/error.jsp");
			throw new Exception("Token not validated !!");
		}
		return retVal;
	}

	/**
	 * Free token.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	public String freeToken(HttpServletRequest request) {

		resetToken(request);
		return "1";
	}

	/**
	 * Generate secure random number.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	public static String GenerateSecureRandomNumber(HttpServletRequest request) {
		String tokenNo = "";
		try {
			// Initialize SecureRandom
			// This is a lengthy operation, to be done only upon
			// initialization of the application
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			// generate a random number
			String randomNum = new Integer(prng.nextInt()).toString();
			// get its digest
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] result = sha.digest(randomNum.getBytes());

			tokenNo = hexEncode(result);
			request.getSession().setAttribute("UNIQUE_ID", tokenNo);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return tokenNo;
	}
	
	public  String GenerateSecureRandomNumberForToken(HttpServletRequest request) {
		String tokenNo = "";
		try {
			// Initialize SecureRandom
			// This is a lengthy operation, to be done only upon
			// initialization of the application
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			// generate a random number
			String randomNum = new Integer(prng.nextInt()).toString();
			// get its digest
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] result = sha.digest(randomNum.getBytes());

			tokenNo = hexEncode(result);
			request.getSession().setAttribute("IMCS_TOKEN", tokenNo);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return tokenNo;
	}

	/**
	 * Hex encode.
	 * 
	 * @param aInput
	 *            the a input
	 * @return the string
	 */
	private static String hexEncode(byte[] aInput) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}
	 public void saveToken(HttpServletRequest request){
		 
		  this.GenerateSecureRandomNumberForToken(request);
	 }
	
	 
	 public boolean isTokenValid(HttpServletRequest request)
	 {
		 System.out.println("Inside Token Validate --[IMCS_TOKEN]-->"+request.getParameter("IMCS_TOKEN"));
		return request.getSession().getAttribute("IMCS_TOKEN") != null && 
				request.getSession().getAttribute("IMCS_TOKEN").toString().trim().length() > 0 &&
				 request.getSession().getAttribute("IMCS_TOKEN").toString().trim().equals(request.getParameter("IMCS_TOKEN"));
		 
	 }
	  
	 
	 public void resetToken(HttpServletRequest request){
		 request.getSession().setAttribute("IMCS_TOKEN", "");
	 }

}
/**********************************************************
 Project:	   AHIMS_G5	
 File:         SecureHashAlgorithm.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package global.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureHashAlgorithm
{
	public static String getRandomSalt(int nSaltSize)
	{
		SecureRandom objRandom = new SecureRandom();
		byte[] randomSalt = new byte[nSaltSize];
		objRandom.nextBytes(randomSalt);

		return randomSalt.toString();
	}

	public static String generateRandom() throws Exception
	{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		// System.out.println ( "SecureRandom : " + sr.nextInt () ); //if you
		// enable then output return in negative
		return String.valueOf(Math.abs(sr.nextInt()));
	}

	public static String[] sanitizedData(String... input)
	{
		String sanitizedData[] = new String[input.length];
		int index = 0;

		for (String i : input)
		{
			sanitizedData[index++] = i.replaceAll("[\'~!@#$%^&*()\";: <>?/,.]", "�");
		}
		return sanitizedData;
	}

	private static String convertToHex(byte[] data)
	{
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++)
		{
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do
			{
				if ((0 <= halfbyte) && (halfbyte <= 9)) buf.append((char) ('0' + halfbyte));
				else buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	public static void main(String args[])
	{
		String sanitizedData[] = SecureHashAlgorithm.sanitizedData("vijay\'~ !@#$%^&*()\";:<>?/,.", "jessy%");
		System.out.println(sanitizedData[0] + "\n" + sanitizedData[1]);
	}

}

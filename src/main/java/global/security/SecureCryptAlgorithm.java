/**********************************************************
 Project:	   AHIMS_G5	
 File:         SecureCryptAlgorithm.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package global.security;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;


import sun.misc.BASE64Encoder;

import java.util.*;
import java.io.*;

public abstract class SecureCryptAlgorithm
{
	private static String KEY_GENERATION_ALGORITHM = "DES";
	private static String CRYPT_ALGORITHM = "DES/ECB/PKCS5Padding";
	
	private static String KEY_GENERATION_ALGORITHM_AES = "AES";
	private static String CRYPT_ALGORITHM_AES = "AES/ECB/PKCS7Padding";//"AES/ECB/PKCS5Padding";
	// Generates a DES Secret Key
	public static String generateKey()
	{
		try
		{
			KeyGenerator keygen = KeyGenerator.getInstance(KEY_GENERATION_ALGORITHM);
			SecretKey desKey = keygen.generateKey();
			byte[] bytes = desKey.getEncoded();
			return getString(bytes);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String generateKeyAES()
	{
		try
		{
			/*byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
					0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };

			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");*/
					
			KeyGenerator keygen = KeyGenerator.getInstance(KEY_GENERATION_ALGORITHM_AES);
			SecretKey key = keygen.generateKey();
			//String secKeyEncoded = new String(key.getEncoded());
			byte[] bytes = key.getEncoded();
			return getString(bytes);
			//return secKeyEncoded;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	// Getting Key Object for Encryption from the passed Secret Key String
	private static Key getKey(String secretKey)
	{
		try
		{
			byte[] bytes = getBytes(secretKey);
			DESKeySpec pass = new DESKeySpec(bytes);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_GENERATION_ALGORITHM);
			SecretKey s = skf.generateSecret(pass);
			return s;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private static Key getKeyAES(String secretKey)
	{
		try
		{
			byte[] keyBytes = getBytes(secretKey);
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			return key;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// Encrypting Source with the help of Passed secretKey
	public static String encrypt(String source, String secretKey)
	{
		try
		{
			Key key = getKey(secretKey);
			Cipher desCipher = Cipher.getInstance(CRYPT_ALGORITHM);
			desCipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cleartext = source.getBytes();
			byte[] ciphertext = desCipher.doFinal(cleartext);
			return getString(ciphertext);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// Decrypting Source with the help of Passed secretKey
	public static String decrypt(String source, String secretKey)
	{
		try
		{
			Key key = getKey(secretKey);
			Cipher desCipher = Cipher.getInstance(CRYPT_ALGORITHM);
			byte[] ciphertext = getBytes(source);
			desCipher.init(Cipher.DECRYPT_MODE, key);
			byte[] cleartext = desCipher.doFinal(ciphertext);
			return new String(cleartext);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	// Encrypting Source with the help of Passed secretKey
	public static String encryptAES(String source, String secretKey)
	{
		try
		{
			//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Key key = getKeyAES(secretKey);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			/*byte[] cleartext = source.getBytes();
			byte[] cipherText = new byte[cipher.getOutputSize(cleartext.length)];
			int ctLength = cipher.update(cleartext, 0, cleartext.length, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);*/
			
			
		    byte[] cipherText = cipher.doFinal(source.getBytes());
		    String encryptedValue = new BASE64Encoder().encode(cipherText);
		    encryptedValue = URLEncoder.encode(encryptedValue, "UTF-8");
		    return encryptedValue;
		    //return getString(cipherText);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

	    int digestLength = md.getDigestLength();
	    int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
	    byte[] generatedData = new byte[requiredLength];
	    int generatedLength = 0;

	    try {
	        md.reset();

	        // Repeat process until sufficient data has been generated
	        while (generatedLength < keyLength + ivLength) {

	            // Digest data (last digest if available, password data, salt if available)
	            if (generatedLength > 0)
	                md.update(generatedData, generatedLength - digestLength, digestLength);
	            md.update(password);
	            if (salt != null)
	                md.update(salt, 0, 8);
	            md.digest(generatedData, generatedLength, digestLength);

	            // additional rounds
	            for (int i = 1; i < iterations; i++) {
	                md.update(generatedData, generatedLength, digestLength);
	                md.digest(generatedData, generatedLength, digestLength);
	            }

	            generatedLength += digestLength;
	        }

	        // Copy key and IV into separate byte arrays
	        byte[][] result = new byte[2][];
	        result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
	        if (ivLength > 0)
	            result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

	        return result;

	    } catch (DigestException e) {
	        throw new RuntimeException(e);

	    } finally {
	        // Clean out temporary data
	        Arrays.fill(generatedData, (byte)0);
	    }
	}
	
	public static String decryptAES(String source, String secretKey)
	{
		
		try
		{
			byte[] cipherData =null;
			byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

			MessageDigest md5 = MessageDigest.getInstance("MD5");
			final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secretKey.getBytes(StandardCharsets.UTF_8), md5);
			SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
			IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);
			byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
			

			Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] decryptedData = aesCBC.doFinal(encrypted);
			String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

			System.out.println(decryptedText);
			
			
			/*Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			
			Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM_AES, "BC");
			Key key = getKey(secretKey);
			byte[] ciphertext = source.getBytes();
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] cleartext = cipher.doFinal(ciphertext);*/

			//byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
			//int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
			//ptLength += cipher.doFinal(plainText, ptLength);
			
			
			return new String(decryptedText);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static String getString(byte[] bytes)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
		{
			byte b = bytes[i];
			sb.append((int) (0x00FF & b));
			if (i + 1 < bytes.length)
			{
				sb.append(".");
			}
		}
		return sb.toString();
	}

	private static byte[] getBytes(String str)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StringTokenizer st = new StringTokenizer(str, ".", false);
		while (st.hasMoreTokens())
		{
			int i = Integer.parseInt(st.nextToken());
			bos.write((byte) i);
		}
		return bos.toByteArray();
	}

	/*
	 * public static void main( String[] args ) {
	 * 
	 * String str= SecurityUtil.encrypt( "U.S." ); String str2= SecurityUtil.decrypt( str); System.out.println("enc  "+str
	 * +"   length "+str.length()); System.out.println("desc  "+str2); }
	 */
}

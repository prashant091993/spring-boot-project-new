/**********************************************************
 Project:	   CDWH	
 File:         SaxHandler.java
 Created:      Jul 3, 2014
 Last Changed: Jul 3, 2014
 Author:       cdac

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package  global.utility;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class SaxHandler.
 */
public class SaxHandler extends DefaultHandler {

	/** The temp val. */
	private String tempVal;

	/** The str result value. */
	private String strResultValue;

	/** The str param. */
	private String strParam;

	// to maintain context

	/**
	 * Instantiates a new sax handler.
	 */
	public SaxHandler() {
	}

	/**
	 * Gets the parameter.
	 * 
	 * @param _FilePath
	 *            the _ file path
	 * @param _Parameter
	 *            the _ parameter
	 * @return the parameter
	 */
	public String getParameter(String _FilePath, String _Parameter) {
		strParam = _Parameter;
		parseDocument(_FilePath);
		return strResultValue;
	}

	/**
	 * Parses the document.
	 * 
	 * @param _FilePath
	 *            the _ file path
	 */
	private void parseDocument(String _FilePath) {
		SAXParserFactory spf = SAXParserFactory.newInstance();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(_FilePath);

			SAXParser sp = spf.newSAXParser();

			sp.parse(fis, this);

		} catch (SAXException se) {
			se.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tempVal = "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		tempVal = new String(ch, start, length);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase(strParam)) {
			strResultValue = tempVal;
		}
	}
}

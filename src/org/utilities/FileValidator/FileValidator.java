package org.utilities.FileValidator;

import org.apache.tika.Tika;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.validator.routines.UrlValidator;

/**
 * This class validates files by examining the content of the file
 * and determining its Mime Type. This class uses the Apache Tika package.
 * @author RLopez - www.rolando-lopez.com
 * @date 2012/10/10
 */
public class FileValidator {
	private Tika tikaService;
	
	
	public FileValidator(){
		tikaService = new Tika();
	}
	
	/**
	 * 
	 * @param byteArray
	 * @return boolean
	 */
	public String getMimeType(byte[] byteArray){
		return detectFromByteArray(byteArray);
	}
	
	/**
	 * @param byteArrayOutputStream
	 * @return String
	 */
	public String getMimeType(ByteArrayOutputStream byteArrayOutputStream){
		return detectFromByteArray(byteArrayOutputStream.toByteArray());
	}
	
	/**
	 * 
	 * @param url
	 * @return String
	 * @throws IOException
	 * @throws Exception
	 */
	public String getMimeTypeFromUrl(String url) throws IOException, java.net.ConnectException, Exception{
		UrlValidator urlValidator = new UrlValidator();
		if(!urlValidator.isValid(url))
			throw new Exception("Invalid URL string: " + url);

		URL oUrl = new URL(url);
		
		return tikaService.detect(oUrl).split(";")[0];
	}
	/**
	 * 
	 * @param filePath
	 * @return String
	 */
	public String getMimeType(String filePath){
		return tikaService.detect(filePath);
	}
	
	/**
	 * 
	 * @param byteArrayOutputStream
	 * @return boolean
	 */
	public boolean isValidPdf(ByteArrayOutputStream byteArrayOutputStream){
		String mimeType = getMimeType(byteArrayOutputStream);
		
		if(mimeType.equalsIgnoreCase(MimeType.PDF.getContentType()))
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param filePath
	 * @param mimeType
	 * @return boolean
	 */
	public boolean isValidMimeType(String filePath, MimeType mimeType){
		return getMimeType(filePath).equalsIgnoreCase(mimeType.getContentType());
	}
	/**
	 * 
	 * @param filePath
	 * @param mimeType
	 * @return boolean
	 */
	public boolean isValidMimeType(ByteArrayOutputStream byteArrayOutputStream, MimeType mimeType){
		return getMimeType(byteArrayOutputStream).equalsIgnoreCase(mimeType.getContentType());
	}
	/**
	 * 
	 * @param url
	 * @param mimeType
	 * @return boolean
	 * @throws Exception 
	 * @throws IOException 
	 */
	public boolean isValidMimeTypeFromUrl(String url, MimeType mimeType) throws IOException, java.net.ConnectException,Exception{
		return getMimeTypeFromUrl(url).equalsIgnoreCase(mimeType.getContentType());
	}
	/**
	 * 
	 * @param byteArray
	 * @param mimeType
	 * @return boolean
	 */
	public boolean isValidMimeType(byte[] byteArray, MimeType mimeType){
		return getMimeType(byteArray).equalsIgnoreCase(mimeType.getContentType());
	}
	
	/**
	 * 
	 * @param byteArray
	 * @return String
	 */
	private String detectFromByteArray(byte[] byteArray){
		return tikaService.detect(byteArray);
	}
	
	
}

package org.utilities.FileValidator;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class FileValidatorTest {

/**
 * @author RLopez www.rolando-lopez.com
 *
 */
	private FileValidator tester;
	
	@Before
	public void setup(){
		this.tester = new FileValidator();
	}
	
	/**
	 * Test method for {@link org.utilities.FileValidator#getMimeType(byte[])}.
	 * @throws FileNotFoundException, IOException
	 */
	@Test
	public void testGetMimeTypeByteArray() throws FileNotFoundException, IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		File aFile = new File(getSampleTextFilePath());
		InputStream is = new FileInputStream(aFile);
		byte[] temp = new byte[1024];
		int read;
		try {
			while((read = is.read(temp)) > 0){
			   buffer.write(temp, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			is.close();
		}
		String expected = "text/plain";
		assertEquals("MimeType: ", expected,  tester.getMimeType(buffer.toByteArray()));
	}

	/**
	 * Test method for {@link org.utilities.FileValidator#getMimeType(java.io.ByteArrayOutputStream)}.
	 * @throws FileNotFoundException, IOException
	 */
	@Test
	public void testGetMimeTypeByteArrayOutputStream() throws FileNotFoundException,IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		File aFile = new File(getSampleTextFilePath());
		InputStream is = new FileInputStream(aFile);
		byte[] temp = new byte[1024];
		int read;
		try {
			while((read = is.read(temp)) > 0){
			   buffer.write(temp, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expected = "text/plain";
		assertEquals("MimeType: ", expected,  tester.getMimeType(buffer));
	}
	
	/**
	 * Test method for {@link org.utilities.FileValidator#getMimeTypeFromUrl(String)}.
	 */
	@Test
	public void testGetMimeTypeFromUrlStringUrlSimpleHttpTextHtml() throws IOException, Exception{
		String expected = "text/html";
		String sUrl = "http://google.com";
		try{	
			assertEquals("MimeType: ", expected,  testGetMimeTypeStringUrl(sUrl));
		}catch (java.net.ConnectException e){
			fail("Connection refused. Ensure you can connecto to the test URL: " + sUrl);
		}
	}

	/**
	 * Test method for {@link org.utilities.FileValidator#getMimeTypeFromUrl(String)}.
	 */
	@Test
	public void testGetMimeTypeFromUrlStringUrlSimpleFtpTextPlain() throws IOException, Exception{
		String expected = "text/plain";
		String sUrl = "ftp://ftp1.us.FreeBSD.org/pub/FreeBSD/README.TXT";
		try{
			assertEquals("MimeType: ", expected,  testGetMimeTypeStringUrl(sUrl));
		}catch (java.net.ConnectException e){
			fail("Connection refused. Ensure you can connecto to the test URL: " + sUrl);
		}
	}

	/**
	 * Test method for {@link org.utilities.FileValidator#getMimeType(String)}.
	 */
	@Test
	public void testGetMimeTypeStringTextPlain() throws IOException, Exception{
		String expected = "text/plain";
		String filePath = getSampleTextFilePath();
		System.out.println(filePath);
		assertEquals("MimeType: ", expected,  tester.getMimeType(filePath));
	}
	
	/**
	 * Test method for {@link org.utilities.FileValidator#isValidMimeType(String,MimeType)}.
	 */
	@Test
	public void testIsValidMimeTypeFromFilePath_Text(){
		boolean expected = true;
		String filePath = getSampleTextFilePath();
		System.out.println(filePath);
		MimeType mimeType = MimeType.TEXT;
		System.out.println(mimeType.getContentType());
		assertEquals("MimeType: ", expected,  tester.isValidMimeType(filePath,mimeType));
	}
	@Test
	public void testIsValidMimeTypeFromUrl_Html() throws IOException, Exception{
		String sUrl = "http://google.com";
		MimeType mimeType = MimeType.HTML;
		System.out.println(mimeType.getContentType());
		try{
			assertTrue("MimeType: ",  tester.isValidMimeTypeFromUrl(sUrl,mimeType));
		}catch (java.net.ConnectException e){
			fail("Connection refused. Ensure you can connecto to the test URL: " + sUrl);
		}
		
	}

		//used by tests but NOT a test
	public String testGetMimeTypeStringUrl(String sUrl) throws IOException, Exception{
		
		String mimeType = tester.getMimeTypeFromUrl(sUrl);
		return mimeType; 
		
	}
	private String getSampleTextFilePath(){
		URL url = getClass().getResource("/assets/SampleTextFile.txt");
		String filePath = url.getPath();
		
		return filePath; 
	}
	
}

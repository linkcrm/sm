package com.owen.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64V2 {
	/**  加密
	 * 
	 * @param str
	 * @return
	 */
	 public static String getBase64(String str) {  
	        byte[] b = null;  
	        String s = null;  
	        try {  
	            b = str.getBytes("utf-8");  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	        if (b != null) {  
	            s = new BASE64Encoder().encode(b);  
	        }  
	        return s;  
	    }  
	  
	    /**
	     *  解密  
	     * @param s
	     * @return
	     */
	    public static String getFromBase64(String s) {  
	        byte[] b = null;  
	        String result = null;  
	        if (s != null) {  
	            BASE64Decoder decoder = new BASE64Decoder();  
	            try {  
	                b = decoder.decodeBuffer(s);  
	                result = new String(b, "utf-8");  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return result;  
	    }  
	
	public static void main(String[] args) {
		String aa="彭大虾";
		String encode = Base64V2.getBase64(aa);
		System.out.println(encode);
		String bb = Base64V2.getFromBase64(encode);
		System.out.println(bb);
		System.out.println(Base64V2.getFromBase64("MTAwMDAwMDA="));
	}
}

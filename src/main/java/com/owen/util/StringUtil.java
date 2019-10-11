package com.owen.util;

public class StringUtil {

	public static boolean isEmpty(String s) {// 判断字符串是否是null或""
		if (s == null || "".equals(s)) {
			return true;
		}
		s = s.trim();
		if ("".equals(s))
			return true;
		return false;
	}
   

}

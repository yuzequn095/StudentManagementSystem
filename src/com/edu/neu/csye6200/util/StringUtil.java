package com.edu.neu.csye6200.util;

/**
 * 
 * @author Runyao Xia
 *
 */
public class StringUtil {

	public static boolean isEmpty(String str){
		if(str==null || "".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isNotEmpty(String str){
		if(str!=null && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
}

package org.wind.sc.utils;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.Validate;

public class ServletTools {
	
	
	public static Map<String,Object> getParametersStartingWith(ServletRequest request,String prefix){
		Validate.notNull(request,"request can not be null");
		
		Map<String,Object> params = new TreeMap();
		
		Enumeration paramNames = request.getParameterNames();
		if(prefix == null){
			prefix = "";
		}
		while((paramNames != null) && paramNames.hasMoreElements()){
			String element = (String)paramNames.nextElement();
			if("".equals(prefix) || element.startsWith(prefix)){
				String noPrefixParam = element.substring(prefix.length());
				String[] values = request.getParameterValues(noPrefixParam);
				if((values == null) || values.length == 0){
					//do nothing
				}else if(values.length > 1){
					params.put(noPrefixParam, values);
				}else{
					params.put(noPrefixParam, values[0]);
				}
			}
		}
		return params;
	}
}

package com.trulygeneric.common.util;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.trulygeneric.common.constants.Message;
import com.trulygeneric.common.constants.SysCommon;

public class ParseUtil {

	public static Map<String,String> parseParamStr( String param ) throws ParseException {
		Map<String,String> params = new LinkedHashMap<>();
		if( StringUtils.isNotEmpty(param) ) {
			String [] keyvals = param.split(SysCommon.CMA_SPRTR_PT);
			for( String keyval : keyvals ) {
				if( keyval.contains(SysCommon.EQ) ) {
					params.put(	StringUtils.substringBefore(keyval, SysCommon.EQ),
							StringUtils.substringAfter(keyval, SysCommon.EQ));
				} else
					throw new ParseException(Message.PRM_PRS_ERR,-1);
			}
		}
		return params;
	}
	
	public static void main( String [ ] arg) throws ParseException {
		System.out.println(ParseUtil.parseParamStr("name=truncate, subj=FINCODE"));
	}
	
}

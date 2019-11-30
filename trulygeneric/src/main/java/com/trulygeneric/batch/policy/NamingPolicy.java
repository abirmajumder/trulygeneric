package com.trulygeneric.batch.policy;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.trulygeneric.common.constants.SysCommon;

public class NamingPolicy {
	
	public static String processName( Map<String,String> keyval ) {
		return StringUtils.substringBefore(keyval.get("name"), SysCommon.DOT).toUpperCase();
	}
	
	public static String flowName( String flowItemName ) {
		return flowItemName + SysCommon.USR + SysCommon.FLW;
	}
	
}

package com.xiaoshabao.base.component.tokenParser;

import java.util.Map;

/**
 * 简单的参数匹配
 */
public class TokenParserUtil {
	
	/**
	 * map参数解析形式
	 * @param str 需要解析的字符串
	 * @param openToken "@{"
	 * @param closeToken "}"
	 * @param params 替换的参数
	 * @return
	 */
	public static String parser(String str,String openToken, String closeToken,Map<String,Object> params) {
		TokenHandler handler=new MapTokenHandler(params);
	    GenericTokenParser parser=new GenericTokenParser(openToken,closeToken,handler);
	    return parser.parse(openToken);
	}
}

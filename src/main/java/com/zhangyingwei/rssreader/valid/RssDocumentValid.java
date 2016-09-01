package com.zhangyingwei.rssreader.valid;

import com.zhangyw.utils.String.StringUtil;

public class RssDocumentValid {
	public static boolean valid(String documentString){
		return StringUtil.isNotEmpty(documentString)&&validPrefix(documentString)&&validSuffix(documentString);
	}
	private static boolean validPrefix(String documentString){
		return documentString.trim().startsWith("<?xml")&&(documentString.indexOf("<feed")>0||documentString.indexOf("<rss")>0);
	}
	private static boolean validSuffix(String documentString){
		return documentString.trim().endsWith("rss>")||documentString.trim().endsWith("feed>");
	}
}

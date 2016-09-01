package com.zhangyingwei.rssreader.valid;

public class DocumentValid {
	public static boolean valid(String documentString){
		return validPrefix(documentString)&&validSuffix(documentString);
	}
	private static boolean validPrefix(String documentString){
		return documentString.startsWith("<");
	}
	private static boolean validSuffix(String documentString){
		return documentString.endsWith(">");
	}
}

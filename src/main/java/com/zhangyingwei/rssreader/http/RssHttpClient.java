package com.zhangyingwei.rssreader.http;

import java.io.InputStream;

import org.dom4j.Document;

import com.zhangyingwei.rssreader.expection.URLException;

public interface RssHttpClient {
	public Document readDoc(String url) throws Exception;
	public String text(String url) throws Exception;
	public InputStream stream(String url) throws Exception;
}

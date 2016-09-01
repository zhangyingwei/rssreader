package com.zhangyingwei.rssreader.http.proxy;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;

import com.zhangyingwei.rssreader.expection.TextParseException;
import com.zhangyingwei.rssreader.expection.URLException;
import com.zhangyingwei.rssreader.http.RssHttpClient;
import com.zhangyingwei.rssreader.http.client.OkRssHttpClient;
import com.zhangyingwei.rssreader.http.client.SuperRssHttpClient;
import com.zhangyingwei.rssreader.valid.RssDocumentValid;
import com.zhangyingwei.rssreader.valid.URLValid;

public class RssHttpClientProxy implements RssHttpClient{
	private Logger logger = Logger.getLogger(RssHttpClientProxy.class);
	private RssHttpClient httpClient;
	
	public RssHttpClientProxy(){
		httpClient = new SuperRssHttpClient();
//		httpClient = new OkRssHttpClient();
	}

	@Override
	public Document readDoc(String url) throws Exception {
		return this.httpClient.readDoc(url);
	}

	@Override
	public String text(String url) throws Exception {
		if (!URLValid.valid(url)) {
			logger.info(url);
			throw new URLException("url is not valid");
		}
		return this.httpClient.text(url);
	}
	
	@Override
	public InputStream stream(String url) throws Exception{
		if (!URLValid.valid(url)) {
			logger.info(url);
			throw new URLException("url is not valid");
		}
		return this.httpClient.stream(url);
	}
}

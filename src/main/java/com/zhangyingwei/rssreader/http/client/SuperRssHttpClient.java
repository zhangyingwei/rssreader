package com.zhangyingwei.rssreader.http.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import com.zhangyingwei.rssreader.expection.StreamReadException;
import com.zhangyingwei.rssreader.expection.TextParseException;
import com.zhangyingwei.rssreader.http.RssDocumentReader;
import com.zhangyingwei.rssreader.http.RssHttpClient;
import com.zhangyingwei.rssreader.utils.RssUtil;
import com.zhangyingwei.rssreader.valid.RssDocumentValid;
import com.zhangyw.superhttp.http.Http;

public class SuperRssHttpClient implements RssHttpClient,RssDocumentReader {
	private Logger logger  = Logger.getLogger(SuperRssHttpClient.class);
	Http http = new Http();
	@Override
	public Document readDoc(String url) throws IOException {
		try {
			return readDocByStream(stream(url));
		} catch (Exception e) {
			logger.info(e.getMessage());
			try {
				String text = text(url);
				if(RssDocumentValid.valid(text)) {
					return readDocByText(text);
				}else{
					logger.info(text);
					logger.info("text is not valid");
				}
			} catch (Exception e2) {
				logger.info(e2.getMessage());
			}
		}
		return null;
	}

	@Override
	public String text(String url) throws IOException {
		return http.get(url).getResponseBodyAsString();
	}

	@Override
	public InputStream stream(String url) throws Exception {
		return http.get(url).getResponseBodyAsStream();
	}

	@Override
	public Document readDocByText(String text) throws Exception {
		logger.info("read document by text");
		if(RssDocumentValid.valid(text)){
			logger.info("document valid:true");
			try {
				return DocumentHelper.parseText(text.trim());
			} catch (DocumentException e) {
				throw new TextParseException("parse text to document err",e);
			}
		}
		logger.info("document valid:false");
		return null;
	}

	@Override
	public Document readDocByStream(InputStream stream) throws Exception {
		logger.info("read document by stream");
		try {
			return new SAXReader().read(stream);
		} catch (DocumentException e) {
			throw new StreamReadException("read stream to document err");
		}
	}

}

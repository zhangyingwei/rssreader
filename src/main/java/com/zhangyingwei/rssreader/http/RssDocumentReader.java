package com.zhangyingwei.rssreader.http;

import java.io.InputStream;

import org.dom4j.Document;

import com.zhangyingwei.rssreader.expection.URLException;

public interface RssDocumentReader {
	public Document readDocByText(String text) throws Exception;
	public Document readDocByStream(InputStream stream) throws Exception;
}

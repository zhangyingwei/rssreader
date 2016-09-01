package com.zhangyingwei.rssreader.http.client;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhangyingwei.rssreader.expection.URLException;
import com.zhangyingwei.rssreader.http.RssHttpClient;
import com.zhangyingwei.rssreader.https.Https;
import com.zhangyingwei.rssreader.valid.RssDocumentValid;
import com.zhangyingwei.rssreader.valid.URLValid;

public class OkRssHttpClient implements RssHttpClient {
	private Logger logger = Logger.getLogger(OkRssHttpClient.class);
	private OkHttpClient client;
	public OkRssHttpClient() {
		client = new OkHttpClient();
	}

	@Override
	public Document readDoc(String url) throws URLException {
		String text = text(url);
		try {
			return DocumentHelper.parseText(text);
		} catch (DocumentException e) {
			logger.info("parse text to document err");
			logger.debug(text);
		}
		return null;
	}

	@Override
	public String text(String url) throws URLException {
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			throw new URLException(e);
		}
	}

	@Override
	public InputStream stream(String url) throws Exception {
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().byteStream();
		} catch (IOException e) {
			throw new URLException(e);
		}
	}
}

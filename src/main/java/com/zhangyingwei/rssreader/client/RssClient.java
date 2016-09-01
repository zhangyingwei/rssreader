package com.zhangyingwei.rssreader.client;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.zhangyingwei.rssreader.expection.URLException;
import com.zhangyingwei.rssreader.http.RssHttpClient;
import com.zhangyingwei.rssreader.http.client.OkRssHttpClient;
import com.zhangyingwei.rssreader.http.client.SuperRssHttpClient;
import com.zhangyingwei.rssreader.http.proxy.RssHttpClientProxy;
import com.zhangyingwei.rssreader.model.RssEntity;
import com.zhangyingwei.rssreader.model.RssHead;
import com.zhangyingwei.rssreader.model.RssModel;
import com.zhangyingwei.rssreader.utils.RssUtil;

public class RssClient {
	private RssHttpClient httpClient ;
	
	public RssClient(){
		httpClient = new RssHttpClientProxy();
	}
	
	public RssModel buildRssModel(String url) throws Exception{
		return new RssModel(buildRssHead(url), buildRssEntitys(url));
	}
	
	public RssHead buildRssHead(String url) throws Exception{
		Element root = this.getRootElement(url);
		if(root!=null){
			return RssUtil.readRssHead(root);
		}
		return null;
	}
	public List<RssEntity> buildRssEntitys(String url) throws Exception{
		Element root = this.getRootElement(url);
		if(root!=null){
			return RssUtil.readRssEneity(root);
		}
		return null;
	}
	
	private Element getRootElement(String url) throws Exception{
		Document doc = httpClient.readDoc(url);
		if(doc!=null){
			return doc.getRootElement();
		}
		return null;
	}
}

package com.zhangyingwei.rssreader;

import org.dom4j.DocumentHelper;

import com.zhangyingwei.rssreader.client.RssClient;

public class TestApp {
	public static void main(String[] args) throws Exception {
		String headurl1 = "http://blog.zhangyingwei.com/atom.xml";
		String headurl2 = "https://luolei.org/rss/";
		RssClient client = new RssClient();
		System.out.println(client.buildRssHead(headurl1));
		System.out.println(client.buildRssHead(headurl2));
	}
}

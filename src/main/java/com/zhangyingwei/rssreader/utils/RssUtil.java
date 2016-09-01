package com.zhangyingwei.rssreader.utils;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.zhangyingwei.rssreader.model.RssEntity;
import com.zhangyingwei.rssreader.model.RssHead;
import com.zhangyingwei.rssreader.model.RssModel;
import com.zhangyw.utils.String.StringUtil;

public class RssUtil {
	public static RssHead readRssHead(Element root) {
		if (root.getName().equals(RssModel.TYPE_FEED)) {
			return new RssHead(root.elementText("title"), findLink(root), root.elementText("updated"), null);
		} else if (root.getName().equals(RssModel.TYPE_RSS)) {
			return new RssHead(root.element("channel").elementText("title"), findLink(root.element("channel")),
					root.element("channel").elementText("lastBuildDate"),
					root.element("channel").elementText("description"));
		}
		return null;
	}

	public static List<RssEntity> readRssEneity(Element root) {
		List rssEntitys = new ArrayList();
		if (root.getName().equals(RssModel.TYPE_FEED)) {
			for (Element e : (List<Element>) root.elements("entry")) {
				rssEntitys.add(new RssEntity(e.elementText("title"), e.element("link").attributeValue("href"),
						e.elementText("content"), e.elementText("updated"), e.elementText("summary")));
			}
		} else if (root.getName().equals(RssModel.TYPE_RSS)) {
			for (Element e : (List<Element>) root.element("channel").elements("item")) {
				rssEntitys.add(new RssEntity(e.elementText("title"), e.elementText("link"), e.elementText("content"),
						e.elementText("pubDate"), e.elementText("description")));
			}
		}
		return rssEntitys;
	}

	private static String findLink(Element e) {
		List<Element> eles = e.elements("link");
		for (Element ele : eles) {
			if (StringUtil.isNotEmpty(ele.getText())) {
				return ele.getText();
			} else if (StringUtil.isEmpty(ele.attributeValue("rel"))) {
				return ele.attributeValue("href");
			}
		}
		return null;
	}

	public static String formateRssString(String content) {
		byte[] bytes = content.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] > 0xFFFD) {
				bytes[i] = ' ';
			} else if (bytes[i] < 0x20 && bytes[i] != '\t' & bytes[i] != '\n' & bytes[i] != '\r') {
				bytes[i] = ' ';
			} else if (bytes[i] >= 0x80 && bytes[i] <= 0x9f) {
				bytes[i] = ' ';
			}
		}
		return new String(bytes);
	}
}

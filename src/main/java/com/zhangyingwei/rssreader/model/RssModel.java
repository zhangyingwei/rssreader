package com.zhangyingwei.rssreader.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.dom4j.Document;

public class RssModel {
	public static final String TYPE_FEED = "feed";
	public static final String TYPE_RSS = "rss";
	
	public RssModel(RssHead rssHead, List<RssEntity> rssEntity) {
		super();
		this.rssHead = rssHead;
		this.rssEntitys = rssEntity;
	}
	public RssModel(JSONObject rssJson) {
		super();
		this.rssHead = new RssHead(rssJson.getJSONObject("rssHead"));
		rssEntitys = new ArrayList<RssEntity>();
		for(Object obj:rssJson.getJSONArray("rssEntitys")){
			this.rssEntitys.add(new RssEntity(JSONObject.fromObject(obj)));
		}
	}
	private RssHead rssHead;
	private List<RssEntity> rssEntitys;
	public RssHead getRssHead() {
		return rssHead;
	}
	public void setRssHead(RssHead rssHead) {
		this.rssHead = rssHead;
	}
	public List<RssEntity> getRssEntitys() {
		return rssEntitys;
	}
	public void setRssEntitys(List<RssEntity> rssEntity) {
		this.rssEntitys = rssEntity;
	}
	public boolean isHeadEmpty(){
		return this.rssHead==null;
	}
	public boolean isEntitysEmpty(){
		return this.rssEntitys==null||this.rssEntitys.size()==0;
	}
	@Override
	public String toString() {
		return "RssModel [rssHead=" + rssHead + ", rssEntitys=" + rssEntitys + "]";
	}
}
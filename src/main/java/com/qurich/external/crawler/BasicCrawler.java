/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qurich.external.crawler;

import java.util.Set;

import com.qurich.external.task.QuartzTask;
import com.qurich.external.task.RegexUtils;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author 爬虫抓取新url
 */

public class BasicCrawler extends WebCrawler {
	
	
	private QuartzTask quartzTask;
	 
	@Override
	public void onStart() {
		 quartzTask=(QuartzTask) super.getMyController().getCustomData();
	}
	
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		return true;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		/*
		 * Object[] objs=(Object[]) myController.getCustomData();
		 * xmlParser=(XmlParserBean) objs[2]; productService=(ProductService)
		 * objs[3];
		 */
		int docid = page.getWebURL().getDocid();
		String url = page.getWebURL().getURL();
		/*
		 * String domain = page.getWebURL().getDomain(); String path =
		 * page.getWebURL().getPath(); String subDomain =
		 * page.getWebURL().getSubDomain(); String parentUrl =
		 * page.getWebURL().getParentUrl(); String anchor =
		 * page.getWebURL().getAnchor();
		 */

		logger.info(Thread.currentThread().getName().toString() + "开始处理：" + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			// String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			// Set<WebURL> links = htmlParseData.getOutgoingUrls();
			// System.out.println(html);
			// 解析
			try {
				if ("http://www.gw.com.cn/news/news/pageNewsHotMore_1.shtml".equals(url)) {
					Set<String> set = RegexUtils.gw(html);
					quartzTask.startCrawler(set2Array(set));
				} else if ("http://news.cnstock.com/bwsd/index.html".equals(url)) {
					Set<String> set = RegexUtils.cnstock(html);
					quartzTask.startCrawler(set2Array(set));
				} else if ("http://finance.eastmoney.com/news/cgsxw.html".equals(url)) {
					Set<String> set = RegexUtils.eastmoney(html);
					quartzTask.startCrawler(set2Array(set));
				} else {
					if (url.startsWith("http://www.cailianpress.com")) {// 财联社
						quartzTask.saveBeanFromResult(RegexUtils.cailianpress(html), 0);
					} else if (url.startsWith("http://news.cnstock.com")) {// 上证快讯
						quartzTask.saveBeanFromResult(RegexUtils.cnstock2(html, url), 1);
					} else if (url.startsWith("http://www.gw.com.cn")) {// 大智慧
						quartzTask.saveBeanFromResult(RegexUtils.gw2(html, url), 2);
					} else if (url.startsWith("http://finance.eastmoney.com/")) {// 东方财富
						quartzTask.saveBeanFromResult(RegexUtils.eastmoney2(html, url), 3);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info(Thread.currentThread().getName().toString() + "完成处理："
					+ url);
		}
	}
	
	private String[] set2Array(Set<String> set) {
		Object[] obj = set.toArray();// 先讲set集合转为Object对象数组（向上转型）
		String temp[] = new String[obj.length];
		for (int i = 0; i < obj.length; i++) {
			temp[i] = (String) obj[i];// 将Object对象数组转为整型数组（强制向下转型）
		}
		return temp;
	}

}

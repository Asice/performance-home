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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qurich.external.task.QuartzTask;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author Yasser Ganjisaffar
 */
@Component
public class BasicCrawlController {
  private static final Logger logger = LoggerFactory.getLogger(BasicCrawlController.class);

  @Autowired
  public  QuartzTask quartzTask;
  
  public void startCrawler(String ...url) throws Exception{
	  
	String crawlStorageFolder = "/home/logs/crawler/1";
    int numberOfCrawlers = 1;

    CrawlConfig config = new CrawlConfig();

    config.setCrawlStorageFolder(crawlStorageFolder);
    config.setMaxDepthOfCrawling(0); //深度,只抓当前页
    config.setPolitenessDelay(100); //线程休眠
    config.setResumableCrawling(false);

    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
    
    for(String u:url){
    	controller.addSeed(u);
    }
    controller.setCustomData(quartzTask);
    controller.startNonBlocking(BasicCrawler.class, numberOfCrawlers);
  }
  
}
package com.qurich.external.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.qurich.external.crawler.BasicCrawlController;
import com.qurich.external.lucene.LuceneUtil;
import com.qurich.external.mapper.MessageMapper;
import com.qurich.external.mapper.StockLimitUpMapper;
import com.qurich.external.model.Message;
import com.qurich.external.model.StockLimitUp;
import com.qurich.external.service.LuceneService;
import com.qurich.external.utils.HttpUtils;
import com.qurich.external.utils.LinkQueue;

/**
 * 资讯定时器
 * @author Asice
 *
 */

@Service
@EnableAsync
public class QuartzTask {
	
	//public final static String StockFilePath="/home/stock/stockdeal.txt";
	private static Logger log = Logger.getLogger(QuartzTask.class.getClass());
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private StockLimitUpMapper stockLimitUpMapper;
	
	@Autowired
	private LuceneService luceneService;
	
	
	@Autowired
	public BasicCrawlController basicCrawlController;
	
	private final static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
	private static LinkQueue<String> queue = new LinkQueue<String>();//队列保存抓取的最新资讯50条
	
	
	//资讯爬虫,先只抓财联社
	@Scheduled(cron ="0 0/5 * * * ?")
	@Async
	protected void autoGetMsg(){
		try {
			//同花顺24小时直播
			try{
				String result_flush=HttpUtils.doGet("http://stock.10jqka.com.cn/thsgd/realtimenews.js?_="+System.currentTimeMillis(),"gbk");
				Set<Message> messages=RegexUtils.flush(result_flush);
				this.saveBeanFromResult(messages, 4);
			}catch(Exception e){
				e.printStackTrace();
			}
			//东方财富24小时直播
			try{
				String result_eastmoney2=HttpUtils.doGet("http://newsapi.eastmoney.com/kuaixun/v1/getlist_102_ajaxResult_50_1_"+System.currentTimeMillis()+".html");
				Set<Message> messages=RegexUtils.eastmoney3(result_eastmoney2);
				this.saveBeanFromResult(messages, 5);
			}catch(Exception e){
				e.printStackTrace();
			}
			//华尔街见闻
			try{
				String result_wallstreetcn=HttpUtils.doGet("https://api-prod.wallstreetcn.com/apiv1/content/lives/pc?limit=20");
				Set<Message> messages=RegexUtils.wallstreetcn(result_wallstreetcn);
				this.saveBeanFromResult(messages, 6);
			}catch(Exception e){
				e.printStackTrace();
			}
			//华尔街见闻
			try{
				String result_yunvs=HttpUtils.doGet("http://www.yuncaijing.com/news/get_realtime_news/yapi/ajax.html");
				Set<Message> messages=RegexUtils.yunvs(result_yunvs);
				this.saveBeanFromResult(messages, 7);
			}catch(Exception e){
				e.printStackTrace();
			}
			//东方财富公司新闻
			try{
				String result_eastmoney=HttpUtils.doGet("http://finance.eastmoney.com/news/cgsxw.html");
				Set<String> urls_eastmoney=RegexUtils.eastmoney(result_eastmoney);
				for(String url:urls_eastmoney){
					String result2=HttpUtils.doGet(url);
					Set<Message> messages=RegexUtils.eastmoney2(result2, url);
					this.saveBeanFromResult(messages, 3);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//大智慧,好像挂了
			try{
				String result_gw=HttpUtils.doGet("http://www.gw.com.cn/news/news/pageNewsHotMore_1.shtml");
				Set<String> urls_gw=RegexUtils.gw(result_gw);
				for(String url:urls_gw){
					String result2=HttpUtils.doGet(url);
					Set<Message> messages=RegexUtils.gw2(result2, url);
					this.saveBeanFromResult(messages, 2);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//上证
			try{
				String result_cnstock=HttpUtils.doGet("http://news.cnstock.com/bwsd");
				Set<String> urls_cnstock=RegexUtils.cnstock(result_cnstock);
				for(String url:urls_cnstock){
					String result2=HttpUtils.doGet(url,"GBK");
					Set<Message> messages=RegexUtils.cnstock2(result2, url);
					this.saveBeanFromResult(messages, 1);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//财联社json
			long nowTime=new Date().getTime()/1000;
			String line=HttpUtils.doPost("http://www.cailianpress.com/v2/article/h5_get_article",
					"type=-1&count=15&staid="+nowTime);
			if(!line.startsWith("{")){
				log.info(line);
				return;
			}
			  log.info("资讯抓取start");
	    	  JSONObject obj=JSONObject.fromObject(line);
	    	  Object data=obj.get("data");
	    	  if(null!=data&&StringUtils.isNotBlank(data.toString())){
	    		  JSONArray arr=JSONArray.fromObject(data);
	    		  Iterator<JSONObject> it=arr.iterator();
	    		  Set<Message> set=new HashSet<>();
	    		  while(it.hasNext()){
	    			  JSONObject json=it.next();
	    			  String content=json.getString("content");
	    			  long time=json.getLong("time")*1000;
	    			  //long id=json.getLong("id");
	    			  Message message=new Message();
	    			  message.setContent(content);
	    			  message.setCreate_time(new Date());
	    			  message.setTime(new Date(time));
	    			  String one=message.getContent();
	    			  if(one.trim().startsWith("【")){
	  					message.setTitle(one.trim().split("】")[0].replaceAll("【|】", ""));
	  				  }else{
	  					message.setTitle(one.split("，|,")[0]);
	  				  }
	    			  set.add(message);
	    		  }
	    		  this.saveBeanFromResult(set, 0);
	    	  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("资讯抓取完毕");
	}
	
	
	
	//重建索引
	@Scheduled(cron ="0 0 3,8,12,16,20 * * ?")
	//@Scheduled(fixedRate=15*60*60*1000)
	@Async
	protected void luceneindex(){
		System.out.println("lucene index start");
		int count=5000;
		List<Message> listAll=new ArrayList<Message>();
		List<Message> list=messageMapper.getBeanListLucene(0,0, count);
		listAll.addAll(list);
		int i=0;
		while(list.size()>0){
			i++;
			list=messageMapper.getBeanListLucene(0,count*i, count);
			listAll.addAll(list);
			if(list.size()<count){
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LuceneUtil.indexFile(listAll);
		if(null!=listAll&&listAll.size()>0)
			messageMapper.updateLuceneList(listAll);//已索引
		System.out.println("lucene index compele");
	}
	
	//股票历史数据，抓取当天
	@Scheduled(cron ="0 0 16 * * ?")
	//@Scheduled(fixedRate=15*60*60*1000)
	@Async
	protected void stockIndex(){
		System.out.println("stock start");
		Calendar cal = Calendar.getInstance();
		int dayWeek=cal.get(Calendar.DAY_OF_WEEK);
		if(dayWeek!=1&&dayWeek!=7){
			String date=sdf2.format(new Date());
			
			saveStockfile(date);
		}
		System.out.println("end");
	}
	
	public void saveBeanFromResult(Set<Message> r1,int form){
		Date now=new Date();
		for(Message message:r1){
			synchronized (queue) {
				if(null!=message&&StringUtils.isNotBlank(message.getTitle())
						&&!queue.contains(message.getTitle())){
					while(queue.size()>100){
						queue.dequeue();//出队
					}
					queue.enqueue(message.getTitle());//入队
					if(null==message.getTime()){
						message.setTime(now);
					}
					message.setFrom(form);
					message.setCreate_time(now);
					//TODO 临时用的，后面改进
					if(null!=message.getContent())
					message.setSummary(message.getContent().trim().split("。")[0].trim());
					if(message.getSummary().length()>500){
						message.setSummary(message.getSummary().substring(0,500));
					}
					//索引找出资讯的时间集合
					try{
						List<String> dates=LuceneUtil.SearchFile(message.getContent(),"time");
						message.setStock(luceneService.getStockResult(dates)); //推荐股票
						if(null!=message.getStock()&&message.getStock().length()>201){
							message.setStock(message.getStock().substring(0,200));
						}
					}catch(Exception e){
						log.error(e);
					}
					messageMapper.saveBean(message); //保存	
					//System.err.println(message.toString());
				}
			}
		}
	}
	public void startCrawler(String...arr){
		try {
			basicCrawlController.startCrawler(arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Scheduled(fixedRate=105*60*60*1000)
	@Async
	protected void insertMessage(){
		String docsPath = "D:\\spark\\2000W\\cailianpress.txt";
		BufferedReader in=null;
		try{
			  in = new BufferedReader(new InputStreamReader(new FileInputStream(docsPath)));
			 String line=null;
		      Set<Long> ids=new HashSet<Long>();
		      while((line=in.readLine())!=null){
		    	  if(!line.startsWith("{")) continue;
		    	  JSONObject obj=JSONObject.fromObject(line);
		    	  Object data=obj.get("data");
		    	  if(null!=data){
		    		  JSONArray arr=JSONArray.fromObject(data);
		    		  Iterator<JSONObject> it=arr.iterator();
		    		  while(it.hasNext()){
		    			  JSONObject json=it.next();
		    			  String content=json.getString("content");
		    			  long time=json.getLong("time")*1000;
		    			  long id=json.getLong("id");
		    			  if(ids.contains(id)){
		    				  continue;
		    			  }
		    			  ids.add(id);
		    			  Message message=new Message();
		    			  message.setContent(content);
		    			  message.setCreate_time(new Date());
		    			  message.setTime(new Date(time));
		    			  message.setFrom(0);//财联社
		    			  String one=message.getContent();
		    			  if(one.trim().startsWith("【")){
		  					message.setTitle(one.trim().split("】")[0].replaceAll("【|】", ""));
		  				  }else{
		  					message.setTitle(one.split("，|,")[0]);
		  				  }
		    			  System.out.println(messageMapper.saveBean(message));
		    			  System.out.println("save:"+message.getTitle());
		    		  }
		    	  }
		      }
		}catch(Exception e){
			
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	*/
	
	private void saveStockfile(String date){
		String code="000";
		while(true){
			try{
				for(int i=1;i<1000;i++){
					String tem=String.valueOf(i);
					while(tem.length()<3){
						tem="0"+tem;
					}
					String url="http://q.stock.sohu.com/hisHq?code=cn_"+code+tem+"&start="+date+"&end="+date+"&stat=1&order=A&period=d&callback=historySearchHandler&rt=jsonp";
					String result=HttpUtils.doGet(url);
					String jsonstr=result.split("\\(")[1].split("\\)")[0];
					if("{}".equals(jsonstr))continue;
					JSONArray arr=JSONArray.fromObject(jsonstr);
					JSONObject obj=arr.getJSONObject(0);
					Object obj2=obj.get("hq");
					if(null==obj2)continue;
					JSONArray arr2=JSONArray.fromObject(obj2);
					Iterator<JSONArray> it=	arr2.iterator();
					while(it.hasNext()){
						JSONArray arr3=it.next();
						double yeat=Double.valueOf(arr3.getString(2))-Double.valueOf(arr3.getString(3));
						double top=Double.valueOf(arr3.getString(6));
						//double topGroup=new BigDecimal((top-yeat)*100d/yeat).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						double limit=yeat*1.1d;
						BigDecimal b=new BigDecimal(limit); 
						double f1=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
						if(top==f1){ //涨停
							try{
								double group=Double.valueOf(arr3.getString(4).replaceAll("%", ""));
								StockLimitUp bean=new StockLimitUp();
								bean.setStock(code+tem);
								bean.setPrice_open(Double.valueOf(arr3.getString(1)));
								bean.setPrice_close(Double.valueOf(arr3.getString(2)));
								bean.setPrice_top(Double.valueOf(arr3.getString(6)));
								bean.setPrice_lower(Double.valueOf(arr3.getString(5)));
								bean.setPrice_rise(Double.valueOf(arr3.getString(3)));
								bean.setPrice_rise(group);//收盘涨幅度
								bean.setTime(sdf2.parse(date));//当天涨停
								//保存涨停信息
								stockLimitUpMapper.saveBean(bean);
							}catch(Exception e){
								e.printStackTrace();
							}
							
						}
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if("603".equals(code)){
					break;
				}
				if("601".equals(code)){
					code="603";
				}
				if("600".equals(code)){
					code="601";
				}
				if("300".equals(code)){
					code="600";
				}
				if("002".equals(code)){
					code="300";
				}
				if("000".equals(code)){
					code="002";
				}
				
			}catch(Exception e){
				log.info("股票写入错误");
			}
		}
	}
	
}

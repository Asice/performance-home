package com.qurich.external.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.qurich.external.model.Message;
import com.qurich.external.utils.HttpUtils;
import com.qurich.external.utils.LinkQueue;

public class RegexUtils {

	public static String regex(String inputstr, String reg) {
		Pattern pat = Pattern.compile(reg);
		Matcher mat = pat.matcher(inputstr);
		if (mat.find()) {
			return mat.group();
		}
		return "not";
	}

	public static boolean userNameRegex(String userName) {
		if (userName == null || userName.trim().equals("")
				|| userName.substring(0, 1).equals("_")) {
			return false;
		}
		Pattern pat = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{1,15}$");
		Matcher mat = pat.matcher(userName);
		if (mat.find()) {
			return true;
		}
		return false;
	}

	public static boolean emailRegex(String email) {
		Pattern pat = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
		Matcher mat = pat.matcher(email);
		if (mat.find()) {
			return true;
		}
		return false;
	}

	public static String getSummary(String summary) {
		summary = summary.replaceAll("<[^>]*>", "");
		if (summary.contains("&rsquo;")) {
			summary = summary.replaceAll("&rsquo;", "’");
		}
		if (summary.contains("&lsquo;")) {
			summary = summary.replaceAll("&lsquo;", "‘");
		}
		if (summary.contains("&#39;")) {
			summary = summary.replaceAll("&#39;", "'");
		}
		if (summary.contains("&lt;")) {
			summary = summary.replaceAll("&lt;", "<");
		}
		if (summary.contains("&gt;")) {
			summary = summary.replaceAll("&gt;", ">");
		}
		if (summary.contains("&ldquo;")) {
			summary = summary.replaceAll("&ldquo;", "“");
		}
		if (summary.contains("&rdquo;")) {
			summary = summary.replaceAll("&rdquo;", "”");
		}
		if (summary.contains("&quot;")) {
			summary = summary.replaceAll("&quot;", "\"");
		}
		return summary;
	}

	/**
	 * 正则判断字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 从一个字符串中截取股票代码
	 * @param input
	 * @return
	 */
	public static List<String> getStockCodes(String input){
		List<String> list=new ArrayList<String>();
		Pattern pat = Pattern.compile("\\d{6}");
		Matcher mat = pat.matcher(input);
		while (mat.find()) {
			list.add(mat.group(0));
		}
		return list;
	}
	/**
	 * 从一个字符串中截取板块名称
	 * @param input
	 * @return
	 */
	public static List<String> getConceptNames(String input){
		List<String> list=new ArrayList<String>();
		Pattern pat = Pattern.compile("(?<=【).*?(?=(】|$))");
		Matcher mat = pat.matcher(input);
		while (mat.find()) {
			if(mat.group(0).length()>6){
				Pattern pat2 = Pattern.compile("\\d{6,}");
				Matcher mat2 = pat2.matcher(mat.group(0));
				if(mat2.find()){
					if(mat2.group(0).length()!=6){
						list.add(mat.group(0));
					}
				}else{
					list.add(mat.group(0));
				}
			}else{
				list.add(mat.group(0));
			}
		}
		return list;
	}
	/**
	 * 过滤特殊字符 
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String StringFilter(String str) throws PatternSyntaxException { 
		// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]"; 
		// 清除掉所有特殊字符 
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		return m.replaceAll("").replaceAll("\"", "").trim();
	} 
	/**
	 * 财联社正则
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static Set<Message> cailianpress(String str)  {
		//正则 开始：<li class="content 结束：</li>
		//标题正则
		Pattern pat = Pattern.compile("(?<=id=\"entries\">)[\\s\\S]*?(?=(id=\"more\"|$))");
		Matcher mat = pat.matcher(str);
		Set<Message> set=new HashSet<Message>();
		int i=0;
		while (mat.find()) {
			String result=mat.group();
			Pattern pat2 = Pattern.compile("(?<=<li class=\"content)[\\s\\S]*?(?=(</li>|$))");
			mat = pat2.matcher(result);
			while (mat.find()) {
				String one=mat.group().trim();
				if(one.contains(">")){
					one=one.split(">")[1];
				}
				Message bean=new Message();
				if(one.trim().startsWith("【")){
					bean.setTitle(one.trim().split("】")[0].replaceAll("【|】", ""));
				}else{
					bean.setTitle(one.split("，|,")[0]);
				}
				bean.setContent(one.trim());
				set.add(bean);
				if(i==4)break;
				i++;
			}
		}
		return set;
	} 
	/**
	 * 上证快讯正则
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	private static LinkQueue<String> queue = new LinkQueue<String>();
	private final static int QUEUESIZE=100;//队列保存抓取的最新资讯100条
	public static Set<String> cnstock(String str)  { 
		Set<String> set=new HashSet<String>();
		Pattern pat0 =  Pattern.compile("(?<=id=\"bw-list\")[\\s\\S]*?(?=(</ul>|$))");
		Matcher mat0 = pat0.matcher(str);
		String str2="";
		while (mat0.find()) {
			str2=mat0.group();
		}
		
		//标题正则
		Pattern pat =  Pattern.compile("(?<=<li>)[\\s\\S]*?(?=(</li>|$))");
		Matcher mat = pat.matcher(str2);
		int i=0;
		while (mat.find()) {
			if(i>5){
				break;
			}
			i++;
			//Pattern pat2 = Pattern.compile("title=\".*?\"");
			Pattern pat3 = Pattern.compile("href=\".*?\"");
			String temp=mat.group(0);
			//Matcher mat2 = pat2.matcher(temp);
			Matcher mat3= pat3.matcher(temp);
			while (mat3.find()) {
				String title=mat3.group(0).replaceAll("href=\"|\"","");
				if(!queue.contains(title)){
					//不包含才去找正文
					while(queue.size()>QUEUESIZE){
						queue.dequeue();//出队
					}
					queue.enqueue(title);//入队
					if(StringUtils.isNotBlank(title))
						set.add(title);
					//查找正文
					/*String href="";
					while (mat3.find()) {
						href=mat3.group(0).replaceAll("href=\"|\"","");
						if(StringUtils.isNotBlank(href))
							set.add(href);
					}*/
				}
			}
		}
		return set;
	} 
	public static Set<Message> cnstock2(String result,String url)  { 
		Set<Message> set=new HashSet<Message>();
		//标题正则
		Message bean=new Message();
		bean.setHref(url);
		Pattern pat =  Pattern.compile("(?<=<h1 class=\"title\">).*?(?=(</h1>|$))");
		Matcher mat = pat.matcher(result);
		while (mat.find()) {
			bean.setTitle(mat.group());
		}
		Pattern pat4 = Pattern.compile("(?<=id=\"qmt_content_div\">)[\\s\\S]*?(?=(</div>|$))");
		Matcher mat4= pat4.matcher(result);
		while (mat4.find()) {
			String content=mat4.group(0);
			content=HtmlUtils.htmlUnescape(content.replaceAll("<[^>]+>", ""));
			bean.setContent(content.trim());
		}
		set.add(bean);
		return set;
	} 
	/**
	 * 大智慧正则
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static Set<String> gw(String str)  { 
		Set<String> set=new HashSet<String>();
		//标题正则
		Pattern pat =  Pattern.compile("(?<=<a href=\"news/news).*?(?=(</a>|$))");
		Matcher mat = pat.matcher(str);
		int i=0;
		while (mat.find()) {
			if(i>5){
				break;
			}
			i++;
			Pattern pat2 = Pattern.compile("(?<=\">).*?(?=(</a>|$))");  
			Pattern pat3 = Pattern.compile("href=\".*?\"");
			String temp="<a href=\"news/news"+mat.group(0)+"</a>";
			Matcher mat2 = pat2.matcher(temp);
			Matcher mat3= pat3.matcher(temp);
			while (mat2.find()) {
				String title=mat2.group(0);
				if(!queue.contains(title)){
					//不包含才去找正文
					while(queue.size()>QUEUESIZE){
						queue.dequeue();//出队
					}
					queue.enqueue(title);//入队
					//查找正文
					String href="";
					while (mat3.find()) {
						href=mat3.group(0).replaceAll("href=\"|\"","");
					}
					href="http://www.gw.com.cn/"+href;
					if(StringUtils.isNotBlank(href))
						set.add(href);
				}
			}
		}
		return set;
	} 
	public static Set<Message> gw2(String result,String url){ 
		Set<Message> set=new HashSet<Message>();
		Message bean=new Message();
		bean.setHref(url);
		
		Pattern pat =  Pattern.compile("(?<=class=\"left_column\">)[\\s\\S]*?(?=(class=\"right_column\">|$))");
		Matcher mat = pat.matcher(result);
		while (mat.find()) {
			String one=mat.group();
			Pattern titlePat =  Pattern.compile("(?<=<h2>).*?(?=(</h2>|$))");
			Matcher titleMat = titlePat.matcher(one);
			while (titleMat.find()) {
				bean.setTitle(titleMat.group().trim());
			}
			Pattern contentPat =  Pattern.compile("(?<=class=\"inner\">)[\\s\\S]*?(?=(</div>|$))");
			Matcher contentMat = contentPat.matcher(one);
			while (contentMat.find()) {
				String content=contentMat.group();
				content=HtmlUtils.htmlUnescape(content.replaceAll("<[^>]+>", ""));
				bean.setContent(content.trim());
			}
		}
		set.add(bean);
		return set;
	} 
	
	/**
	 * 东方财富
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static Set<String> eastmoney(String str)  {
		Set<String> set=new HashSet<String>();
		//标题正则
		Pattern pat =  Pattern.compile("(?<=class=\"text text-no-img\">)[\\s\\S]*?(?=(</li>|$))");
		Matcher mat = pat.matcher(str);
		int i=0;
		while (mat.find()) {
			if(i>5){
				break;
			}
			i++;
			Pattern pat2 = Pattern.compile("title=\".*?\"");
			Pattern pat3 = Pattern.compile("href=\".*?\"");
			String temp=mat.group(0);
			Matcher mat2 = pat2.matcher(temp);
			Matcher mat3= pat3.matcher(temp);
			while (mat2.find()) {
				String title=mat2.group(0).replaceAll("title=\"|\"","");
				if(!queue.contains(title)){
					//不包含才去找正文
					while(queue.size()>QUEUESIZE){
						queue.dequeue();//出队
					}
					queue.enqueue(title);//入队
					//查找正文
					String href="";
					while (mat3.find()) {
						href=mat3.group(0).replaceAll("href=\"|\"","");
					}
					if(StringUtils.isNotBlank(href))
						set.add(href);
				}
			}
		}
		return set;
	} 
	
	public static Set<Message> eastmoney2(String result,String url)  {
		Set<Message> set=new HashSet<Message>();
		Message bean=new Message();
		bean.setHref(url);
		Pattern pat1 = Pattern.compile("(?<=<h1>).*?(?=(</h1>|$))");
		Matcher mat1= pat1.matcher(result);
		while (mat1.find()) {
			bean.setTitle(mat1.group().trim());
		}
		
		Pattern pat4 = Pattern.compile("(?<=id=\"ContentBody\")[\\s\\S]*?(?=(id=\"comBodyEnd\"|$))");
		Matcher mat4= pat4.matcher(result);
		while (mat4.find()) {
			String content="<"+mat4.group(0)+">";
			content=HtmlUtils.htmlUnescape(content.replaceAll("<[^>]+>", ""));
			bean.setContent(content.trim());
		}
		set.add(bean);
		return set;
	} 
	/**
	 * 东方财富24小时直播
	 * @param str
	 * @return
	 */
	public static Set<Message> eastmoney3(String str)  {
		Set<Message> set=new HashSet<Message>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//标题正则
		Pattern pat = Pattern.compile("(?<=var ajaxResult=)[\\s\\S]*?(?=($))");
		Matcher mat = pat.matcher(str);
		while (mat.find()) {
			String jsonStr=mat.group(0);
			JSONObject josn1=JSONObject.fromObject(jsonStr);
			if(josn1.containsKey("LivesList")){
				JSONArray array=JSONArray.fromObject(josn1.get("LivesList"));
				Iterator<JSONObject> it=array.iterator();
				int i=0;
				while(it.hasNext()){
					if(i>5)break;
					i++;
					JSONObject josn= it.next();
					String title="";
					if(josn.containsKey("title")){
						title=josn.getString("title");
					}
					if(!queue.contains(title)&&StringUtils.isNotBlank(title)){
						//不包含才去找正文
						while(queue.size()>QUEUESIZE){
							queue.dequeue();//出队
						}
						queue.enqueue(title);//入队
						Message msg=new Message();
						msg.setTitle(title);
						if(josn.containsKey("url_w")){
							msg.setHref(josn.getString("url_w"));
						}
						if(josn.containsKey("digest")){
							msg.setContent(josn.getString("digest"));
						}
						try {
							msg.setTime(dateFormat.parse(josn.getString("showtime")));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						msg.setCreate_time(new Date());
						msg.setFrom(5);
						set.add(msg);
					}
				}
			}
		}
		return set;
	} 
	
	/**
	 * 同花顺
	 * @param str
	 * @return
	 */
	public static Set<Message> flush(String str)  {
		Set<Message> set=new HashSet<Message>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		//标题正则
		Pattern pat =  Pattern.compile("(?<=item:)[\\s\\S]*?(?=(};|$))");
		Matcher mat = pat.matcher(str);
		while (mat.find()) {
			String jsonStr=mat.group(0);
			JSONArray array=JSONArray.fromObject(jsonStr);
			Iterator<JSONObject> it=array.iterator();
			int i=0;
			while(it.hasNext()){
				if(i>5)break;
				i++;
				JSONObject josn= it.next();
				String title="";
				if(josn.containsKey("title")){
					title=josn.getString("title");
				}
				if(!queue.contains(title)&&StringUtils.isNotBlank(title)){
					//不包含才去找正文
					while(queue.size()>QUEUESIZE){
						queue.dequeue();//出队
					}
					queue.enqueue(title);//入队
					Message msg=new Message();
					msg.setTitle(title);
					if(josn.containsKey("url")){
						msg.setHref(josn.getString("url"));
					}
					if(josn.containsKey("content")){
						msg.setContent(josn.getString("content"));
					}
					try {
						msg.setTime(dateFormat.parse(josn.getString("pubDate")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					msg.setCreate_time(new Date());
					msg.setFrom(4);
					set.add(msg);
				}
			}
		}
		return set;
	} 
	/**
	 * 华尔街见闻
	 * @param str
	 * @return
	 */
	public static Set<Message> wallstreetcn(String str)  {
		Set<Message> set=new HashSet<Message>();
		JSONObject jsonStr=JSONObject.fromObject(str);
		JSONArray array=jsonStr.getJSONObject("data").getJSONObject("a_stock").getJSONArray("items");
		Iterator<JSONObject> it=array.iterator();
		int i=0;
		while(it.hasNext()){
			if(i>5)break;
			i++;
			JSONObject josn= it.next();
			String title="",con="";
			if(josn.containsKey("content")){
				con=josn.getString("content");
				if(con.contains("【")&&con.contains("】")){
					title=con.split("【")[1].split("】")[0];
				}
			}
			if(!queue.contains(title)&&StringUtils.isNotBlank(title)){
				//不包含才去找正文
				while(queue.size()>QUEUESIZE){
					queue.dequeue();//出队
				}
				queue.enqueue(title);//入队
				Message msg=new Message();
				msg.setTitle(title);
				msg.setContent(con);
				msg.setFrom(6);
				msg.setTime(new Date());
				msg.setCreate_time(new Date());
				set.add(msg);
			}
		}
		return set;
	}
	/**
	 * 云财经
	 * @param str
	 * @return
	 */
	public static Set<Message> yunvs(String str) {
		Set<Message> set=new HashSet<Message>();
		JSONObject jsonStr=JSONObject.fromObject(str);
		JSONArray array=jsonStr.getJSONArray("data");
		Iterator<JSONObject> it=array.iterator();
		int i=0;
		while(it.hasNext()){
			if(i>5)break;
			i++;
			JSONObject josn= it.next();
			String title="",con="";
			if(josn.containsKey("push_live")&&josn.getInt("push_live")==1){
				continue;
			}
			if(josn.containsKey("title")){
				title=josn.getString("title");
			}
			if(josn.containsKey("description")){
				con=josn.getString("description");
			}
			if(!queue.contains(title)&&StringUtils.isNotBlank(title)){
				//不包含才去找正文
				while(queue.size()>QUEUESIZE){
					queue.dequeue();//出队
				}
				queue.enqueue(title);//入队
				Message msg=new Message();
				msg.setTitle(title);
				msg.setContent(con);
				msg.setFrom(7);
				if(josn.containsKey("inputtime")){
					msg.setTime(new Date(josn.getLong("inputtime")*1000));
				}else{
					msg.setTime(new Date());
				}
				msg.setCreate_time(new Date());
				set.add(msg);
			}
		}
		return set;
	} 
	
	
}

package com.qurich.external.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.qurich.external.service.StockPerformanceWebclient;
import com.qurich.external.utils.QuarterReportDayUtil;

/**
 * 业绩定时器
 * @author Asice
 *
 */
@Service
@EnableAsync
public class PerformanceTask {

		private static Logger log = Logger.getLogger(PerformanceTask.class.getClass());
		
		@Autowired
		private StockPerformanceWebclient stockPerformanceWebclient;
		//业绩报表,每晚23点15分
		@Scheduled(cron ="0 15 23 * * ?")
		//@Scheduled(fixedRate=30*60*60*1000)
		@Async
		protected void spilerReport() throws ParseException{
			/*log.info("业绩报表开始抓取");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String yestDay=dateFormat.format(new Date(new Date().getTime()-24*60*60*1000));
			Date startDay=QuarterReportDayUtil.getCurrentQuarterStartTime();
			String start=dateFormat.format(startDay);
			stockPerformanceWebclient.quarterNotice(start.split("-")[0], start.split("-")[1], start.split("-")[2],yestDay);
			stockPerformanceWebclient.quarterReport(start.split("-")[0], start.split("-")[1], start.split("-")[2],yestDay);
			Date endDay=QuarterReportDayUtil.getCurrentQuarterEndTime();
			String end=dateFormat.format(endDay);
			stockPerformanceWebclient.quarterNotice(end.split("-")[0], end.split("-")[1], end.split("-")[2],yestDay);
			stockPerformanceWebclient.quarterReport(end.split("-")[0], end.split("-")[1], end.split("-")[2],yestDay);
			log.info("业绩报表结束");*/
			
			log.info("业绩报表开始抓取");
			Calendar cal=Calendar.getInstance();
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date startDay=QuarterReportDayUtil.getCurrentQuarterStartTime();
			Date endDay=QuarterReportDayUtil.getCurrentQuarterEndTime();
			String start=dateFormat.format(startDay);
			String end=dateFormat.format(endDay);
			stockPerformanceWebclient.quarterReport(start.split("-")[0], start.split("-")[1], start.split("-")[2],cal.getTime());
			//预告
			stockPerformanceWebclient.quarterNotice(start.split("-")[0], start.split("-")[1], start.split("-")[2], cal.getTime());
			stockPerformanceWebclient.quarterNotice(end.split("-")[0], end.split("-")[1], end.split("-")[2], cal.getTime());
			log.info("业绩报表结束");
		}
}

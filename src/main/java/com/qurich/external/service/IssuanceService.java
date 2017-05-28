package com.qurich.external.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.IssuanceMapper;
import com.qurich.external.mapper.IssuanceNoticeMapper;
import com.qurich.external.model.Issuance;
import com.qurich.external.model.IssuanceNotice;
import com.qurich.external.utils.PageUtil;

@Service
public class IssuanceService {
	
	private static Logger log = Logger.getLogger(IssuanceService.class.getClass());

	@Autowired
    private IssuanceMapper issuanceMapper;
	@Autowired
    private IssuanceNoticeMapper issuanceNoticeMapper;

	/*public String list(String date, String field, String sort, int page,
			Model model, HttpServletRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if("new".equals(date)){
				date=dateFormat.format(QuarterReportDayUtil.getCurrentQuarterStartTime());
			}
			List<StockPerformance> list = stockPerformanceMapper.getBeanList(date,field,sort,(page-1)*50, 50);
    		int totalRecord = stockPerformanceMapper.getBeanAllCount(date);
    		List<StockPerformance> quarterDayAll= stockPerformanceMapper.getBeanQuarterDayAll();
			
    		PageUtil pageUtil = new PageUtil(20, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("performance/report/"+date+"/"+field+"/"+sort);
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
			
    		model.addAttribute("field", field);
    		model.addAttribute("date", date);
    		model.addAttribute("sort", sort);
    		model.addAttribute("quarterDayAll", quarterDayAll);
    		if("desc".equals(sort)){
    			model.addAttribute("sortdest", "asc");
    		}else{
    			model.addAttribute("sortdest", "desc");
    		}
    		
		}catch(Exception e){
    		log.error("StockPerformanceService.list异常"+e);
    	}
    	return "performance/list";
	}*/





	public String report(String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			List<Issuance> list = issuanceMapper.getBeanList(field,sort,(page-1)*50, 50);
    		int totalRecord = issuanceMapper.getBeanAllCount();
    		PageUtil pageUtil = new PageUtil(50, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("issuance/report/"+field+"/"+sort);
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    		model.addAttribute("field", field);
    		model.addAttribute("sort", sort);
    		if("desc".equals(sort)){
    			model.addAttribute("sortdest", "asc");
    		}else{
    			model.addAttribute("sortdest", "desc");
    		}
    		
		}catch(Exception e){
    		log.error("IssuanceService.report异常",e);
    	}
    	return "issuance/report";
	}

	public String notice(String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			List<IssuanceNotice> list = issuanceNoticeMapper.getBeanList(field,sort,(page-1)*50, 50);
    		int totalRecord = issuanceNoticeMapper.getBeanAllCount();
    		PageUtil pageUtil = new PageUtil(50, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("issuance/notice/"+field+"/"+sort);
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    		model.addAttribute("field", field);
    		model.addAttribute("sort", sort);
    		if("desc".equals(sort)){
    			model.addAttribute("sortdest", "asc");
    		}else{
    			model.addAttribute("sortdest", "desc");
    		}
    		
		}catch(Exception e){
    		log.error("IssuanceService.report异常",e);
    	}
    	return "issuance/notice";
	}

	public String stock(String stock, Model model, HttpServletRequest request) {
		try{
			List<Issuance> list = issuanceMapper.getBeanStockList(stock);
    		model.addAttribute("list", list);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest", "desc");
		}catch(Exception e){
    		log.error("IssuanceService.report异常",e);
    	}
    	return "issuance/report";
	}
	
	public String stock2(String stock, Model model, HttpServletRequest request) {
		try{
			List<IssuanceNotice> list = issuanceNoticeMapper.getBeanStockList(stock);
    		model.addAttribute("list", list);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest", "desc");
		}catch(Exception e){
    		log.error("IssuanceService.stock2异常",e);
    	}
    	return "issuance/notice";
	}
	
}

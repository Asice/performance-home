package com.qurich.external.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.ShareholderMapper;
import com.qurich.external.model.Shareholder;
import com.qurich.external.model.ShareholderPer;
import com.qurich.external.utils.PageUtil;

@Service
public class ShareholderService {

	private static Logger log = Logger.getLogger(ShareholderService.class.getClass());

	@Autowired
    private ShareholderMapper shareholderMapper;


	public String list(int type,String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			List<Shareholder> list = shareholderMapper.getBeanList(type,field,sort,(page-1)*50, 50);
    		int totalRecord = shareholderMapper.getBeanAllCount(type);
    		PageUtil pageUtil = new PageUtil(50, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("shareholder/list/"+type+"/"+field+"/"+sort);
    		model.addAttribute("list", list);
    		model.addAttribute("showPage", pageUtil);
    		model.addAttribute("field", field);
    		model.addAttribute("sort", sort);
    		model.addAttribute("type", type);
    		if("desc".equals(sort)){
    			model.addAttribute("sortdest", "asc");
    		}else{
    			model.addAttribute("sortdest", "desc");
    		}
		}catch(Exception e){
    		log.error("ShareholderService.list异常",e);
    	}
    	return "shareholder/list";
	}

	public String stock(int type,String stock, Model model, HttpServletRequest request) {
		try{
			List<Shareholder> list = shareholderMapper.getBeanStockList(type,stock);
    		model.addAttribute("list", list);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest", "desc");
    		model.addAttribute("type", type);
		}catch(Exception e){
    		log.error("ShareholderService.report异常",e);
    	}
    	return "shareholder/list";
	}

	
	public String calculate(String field, String sort, int page, Model model,
			HttpServletRequest request) {
		try{
			List<ShareholderPer> list = shareholderMapper.getBeanPerList(field,sort,(page-1)*50, 50);
    		int totalRecord = shareholderMapper.getBeanPerAllCount();
    		PageUtil pageUtil = new PageUtil(50, totalRecord, page);
    		pageUtil.setTotalRecord(totalRecord);
    		pageUtil.setPageNumStart(pageUtil.getPageNumStart());
    		pageUtil.setPageNumEnd(pageUtil.getPageNumEnd());
    		pageUtil.setCurrentPage(page);
    		pageUtil.setUrlName("shareholder/calculate/"+field+"/"+sort);
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
    		log.error("ShareholderService.calculate异常",e);
    	}
    	return "shareholder/calculate";
	}

	public String calculatestock(String stock, Model model, HttpServletRequest request) {
		try{
			List<ShareholderPer> list = shareholderMapper.getBeanPerStockList(stock);
    		model.addAttribute("list", list);
    		model.addAttribute("stock", stock);
    		model.addAttribute("sortdest", "desc");
		}catch(Exception e){
    		log.error("ShareholderService.calculatestock异常",e);
    	}
    	return "shareholder/calculate";
	}
	
	
	
	
}

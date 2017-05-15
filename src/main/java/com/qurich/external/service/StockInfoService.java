package com.qurich.external.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.qurich.external.mapper.StockInfoMapper;
import com.qurich.external.model.StockInfo;

@Service
public class StockInfoService {
	
	private static Logger log = Logger.getLogger(StockInfoService.class.getClass());

	@Autowired
    private StockInfoMapper stockInfoMapper;
	

	public String stockInfo(String stock, Model model,
			HttpServletRequest request) {
		try{
			StockInfo stockInfo=stockInfoMapper.getBean(stock);
			model.addAttribute("stockInfo", stockInfo);
		}catch(Exception e){
    		log.error("StockInfoService.list异常",e);
    	}
    	return "stock/stock";
	}
	
}

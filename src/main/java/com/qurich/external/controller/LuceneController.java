package com.qurich.external.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qurich.external.lucene.LuceneUtil;
import com.qurich.external.mapper.MessageMapper;
import com.qurich.external.model.Message;
import com.qurich.external.service.LuceneService;

@Controller
@RequestMapping(value = "/lucene")
public class LuceneController {
	
	
	@Autowired
	private MessageMapper messageMapper;
	
	@Autowired
	private LuceneService luceneService;
	
	
	@RequestMapping(value="/analyze",produces = "application/json; charset=utf-8")
	public String list(
			@RequestParam(value="content", required=false, defaultValue="")String content,
			Model model){
		return luceneService.list(content,model);
	}
	
	
	
	@RequestMapping(value="/indexFiles", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String watchcount(
			Model model){
		System.out.println("lucene index start");
		int count=5000;
		List<Message> listAll=new ArrayList<Message>();
		List<Message> list=messageMapper.getBeanList(0, count);
		listAll.addAll(list);
		int i=0;
		while(list.size()>0){
			i++;
			list=messageMapper.getBeanList(count*i, count);
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
		System.out.println("lucene index compele");
		return "1";
	}
}

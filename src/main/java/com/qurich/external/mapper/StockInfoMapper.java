package com.qurich.external.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.StockInfo;

@Mapper
public interface StockInfoMapper {

	
	@Insert("replace into stock_info(stock, name, price, zsz, ltsz, syl, sjl, area, concept, main_business, pday, mgjzc, mgsy, jlr, jlrzzl, yysr, mgxjl, mggjj, mgwfplr, zgb, ltg, `time`)"
			+" values(#{stock},#{name},#{price},#{zsz},#{ltsz},#{syl},#{sjl},#{area},#{concept},#{main_business},#{pday},#{mgjzc},#{mgsy},#{jlr},#{jlrzzl},#{yysr},#{mgxjl},#{mggjj},#{mgwfplr},#{zgb},#{ltg},#{time})")
	int saveBean(StockInfo bean);
	
	@Select("select * from  stock_info where stock=#{stock}")
	@Results({  
	    @Result(property="main_business",column="main_business")
	})
	StockInfo getBean(@Param("stock")String stock);
	
}

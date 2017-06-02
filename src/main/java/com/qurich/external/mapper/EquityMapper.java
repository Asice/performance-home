package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.Equity;

@Mapper
public interface EquityMapper {

	@Select({"<script>",
        "SELECT * from equity", 
        " order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	List<Equity> getBeanList(@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select("SELECT count(id) from equity")
	int getBeanAllCount();
	
	@Select({"<script>",
        "SELECT * from equity where stock=#{stock} or name=#{stock}", 
        "</script>"})
	List<Equity> getBeanStockList(@Param("stock") String stock);
}

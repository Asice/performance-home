package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.EquityIpo;

@Mapper
public interface EquityIpoMapper {

	@Select({"<script>",
        "SELECT * from equity_ipo", 
        " order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	List<EquityIpo> getBeanList(@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select("SELECT count(id) from equity_ipo")
	int getBeanAllCount();
	
	@Select({"<script>",
        "SELECT * from equity_ipo where stock=#{stock} or name=#{stock}", 
        "</script>"})
	List<EquityIpo> getBeanStockList(@Param("stock") String stock);
}

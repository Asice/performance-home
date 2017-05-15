package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.IssuanceNotice;

@Mapper
public interface IssuanceNoticeMapper {

	@Select({"<script>",
        "SELECT * from issuance_notice", 
        " order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	List<IssuanceNotice> getBeanList(@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select("SELECT count(id) from issuance_notice")
	int getBeanAllCount();
	

}

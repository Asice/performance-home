<html>
<style>
.product-box {
	width: 320px;
}

.product-imitation {
	padding: 15px 0;
	width: 320px;
	height: 350px;
}

.product-desc {
	padding: 10px;
}
.timeline-item{
	font-size: 14px;
    font-family: Tahoma,Arial,Helvetica,'Microsoft Yahei';
    color: #333;
}
.timeline-item .date {
	color: #333;
    left: 0;
    font-weight: bold;
    font-size: 15px;
    
}
.timeline-item .content{
	border-bottom: 1px solid #e7eaec;
}
.pull-right a{
	margin-left: 30px;
}

</style>
<#include "/comm/head.ftl">
<div class="container">
                <div class="ibox float-e-margins">
                    <div class="ibox-content ">
                   	 <#list list as bean> 
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                   ${bean.time?string("HH:mm")}
                                </div>
                                <div class="col-xs-8 content no-top-border" style="min-height: 80px;">
                                    <p><a target="_black" href="${bean.href}">${bean.title}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bean.time?string("yyy-MM-dd")}</p>
                                     <div class="pull-left text-danger">
                                    	<#if bean.bull_category!>
                                     	<#list bean.bull_category?split(",") as category>
									        	 【${category}】
									     </#list>
									     </#if>
                                    </div> 
                                </div>
                            </div>
                        </div>
                        </#list>
                        <#include "/comm/pagination.ftl">
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">
<script type="text/javascript">
	$(function(){
		
	});
</script>
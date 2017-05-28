<#include "taglibs.ftl">
<title>qurich</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="${ctx}/font-awesome/css/font-awesome.css">

<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">

 <!-- Mainly scripts -->
<script src="${ctx}/js/jquery-2.1.1.js" type="text/javascript"></script>
<script src="${ctx}/js/bootstrap.min.js" type="text/javascript"></script>

<style>
	.w1200{
		  width: 1200px;
	}
</style>
<body style="background-color: #fff;">

<div class="row">
       <nav class="navbar navbar-default navbar-fixed-top navbar-scroll" role="navigation" style="position: static;">
            <div class="container">
                <div class="navbar-header page-scroll">
                    <a class="navbar-brand" href="#">QUEICH</a>
                </div>
                <div>
                	<ul class="nav navbar-nav">
                        <li class=""><a class="page-scroll home" href="${BASE_PATH}/index">资讯直播</a></li>
                        <#--<li class=""><a class="page-scroll" href="${BASE_PATH}/lucene/analyze">资讯分析</a></li>-->
                         <li class=""><a class="page-scroll" href="${BASE_PATH}/message/bull">公司利好</a></li>
                        <li class="dropdown profile-element">
                        	<a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">业绩数据</a>
                        	<ul uib-dropdown-menu="" class="animated fadeInRight m-t-xs dropdown-menu">
		                        <li><a ui-sref="app.profile" href="${BASE_PATH}/performance/report/new/pday">业绩财报</a></li>
		                         <li class="divider"></li>
		                        <li><a ui-sref="app.profile" href="${BASE_PATH}/performance/notice/new/pday">业绩预告</a></li>
		                    </ul>
                        </li>
                        <li class="dropdown profile-element">
                        	<a data-toggle="dropdown" class="dropdown-toggle" href="#" aria-expanded="false">定向增发</a>
                        	<ul uib-dropdown-menu="" class="animated fadeInRight m-t-xs dropdown-menu">
		                        <li><a ui-sref="app.profile" href="${BASE_PATH}/issuance/notice">增发预案</a></li>
		                         <li class="divider"></li>
		                        <li><a ui-sref="app.profile" href="${BASE_PATH}/issuance/report">增发实施</a></li>
		                    </ul>
                        </li>
                         <li class=""><a class="page-scroll" href="${BASE_PATH}/concept/list">概念</a></li>
                    </ul>
                 </div>
                <div id="navbar" class="navbar-collapse collapse">
                	 <ul class="nav navbar-nav navbar-right">
                        <li><a class="page-scroll" href="#">登录</a></li>
                        <li><a class="page-scroll" href="#">注册</a></li>
                    </ul>
                	<div class="navbar-form-custom navbar-right" style="margin: 0;">
		                <div class="form-group">
		                    <input type="text" style="height:50px;" placeholder="查询个股" class="form-control" name="top-search" id="top-search" />
		                </div>
		            </div>
                </div>
            </div>
        </nav> 
</div>
<script type="text/javascript">
	$(function(){
		var href=location.href;
		$(".page-scroll").each(function(){
			if(href.indexOf($(this).prop("href"))!=-1){
				$(this).parent().addClass("active");
				return;
			}
		});
		$("#top-search").keyup(function(event){ 
			if(event.keyCode==13){ 
				location.href="/s/"+$(this).val();
			} 
		 }); 
		
	});
</script>
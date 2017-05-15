<html>

<#include "/comm/head.ftl">
<div class="container">
                <div class="ibox float-e-margins">
                    <div class="ibox-content" style="overflow-x:scroll;">
                   		<table class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default"
							data-page-size="15">
							<thead>
								<tr>
									<th class="text-center footable-visible footable-first-column">序号</th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/stock/${sortdest}/1">股票代码</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/name/${sortdest}/1">股票名称</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/schedule/${sortdest}/1">方案进度</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/zfsl/${sortdest}/1">增发数量(万股)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/zgb/${sortdest}/1">增发前总股数(万股)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/zfzgb/${sortdest}/1">增发占股比</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/price/${sortdest}/1">发行价</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/yamjje/${sortdest}/1">募资总额(万元)</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/nprice/${sortdest}/1">最新价</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/zyjl/${sortdest}/1">折溢价率</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/dshday/${sortdest}/1">董事会公告日</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/gddhday/${sortdest}/1">股东大会公告日</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/fswday/${sortdest}/1">发审委公告日</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/zjhshday/${sortdest}/1">证监会核审公告日</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/fxxgday/${sortdest}/1">发行新股日</a></th>
									<th class="text-center footable-visible"><a href="${BASE_PATH}/issuance/notice/xgssday/${sortdest}/1">新股上市公告日</a></th>
								</tr>
							</thead>
							<tbody>
								<#list list as bean>
								<tr class="text-center footable-even"
									style="display: table-row;">
									<td class="footable-visible">${bean_index+1}</td>
									<td class="footable-visible">${bean.stock}</td>
									<td class="footable-visible">${bean.name}</td>
									<td class="footable-visible">${bean.schedule}</td>
									<td class="footable-visible">${bean.zfsl}</td>
									<td class="footable-visible">${bean.zgb}</td>
									<td class="footable-visible">${bean.zfzgb}</td>
									<td class="footable-visible">${bean.price}</td>
									<td class="footable-visible">${bean.yamjje}</td>
									<td class="footable-visible">${bean.nprice}</td>
									<td class="footable-visible">${bean.zyjl}</td>
									<td class="footable-visible">${(bean.dshday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${(bean.gddhday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${(bean.fswday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${(bean.zjhshday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${(bean.fxxgday?string("yyyy-MM-dd"))!}</td>
									<td class="footable-visible">${(bean.xgssday?string("yyyy-MM-dd"))!}</td>
								</tr>
								</#list>
							</tbody>
							<#include "/comm/pagination.ftl">
						</table>
                    </div>
                </div>
            </div>
<#include "/comm/foot.ftl">

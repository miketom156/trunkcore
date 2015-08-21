<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd HH:mm:ss">
<script type="text/javascript">
    totalPageCount = ${pageCount};//总页数
    pageNum = ${pageNumber};//当前页
    pageSize = ${pageSize}; //页大小
    smsSendLogs = new Object();//清空全局变量用于存储
</script>
<div class="smsStaticDivTitle">
    统计结果
</div>
<table border="0" width="100%" cellspacing="0" cellpadding="0" class="box_table" align="center"
       style="border-left: 1px solid #9cc;height:30px;line-height:30px;" id="batchSmsStatisticsData">
    <tr>
        <td class="middle">发送次数</td>
        <td class="middle"><span  style="color:red;">${sendTime?default(0)}</span>
        <td class="middle">发送总数量</td>
        <td class="middle"><span  style="color:red;">${totalAmout?default(0)}</span></td>
    </tr>
</table>
<div class="smsStaticDivTitle">
    明细数据
</div>
<table border="0" width="100%" cellspacing="0" cellpadding="0" class="box_table" align="center"
       style="border-left: 1px solid #9cc;" id="batchSmsStatisticsTbData">
    <tr>
        <th class="middle">用户姓名</th>
        <th class="middle">发送数量</th>
        <th class="middle">发送时间</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.id =${item.id};
        temp.userId = ${item.userId};
        temp.sendAmout = ${item.sendAmout};
        temp.userName = '${item.userName?js_string}';
        temp.sendTime = '${item.sendTime}';
        smsSendLogs["${item.id}"] = temp;
    </script>
    <tr>
        <td class="middle">${item.userName}</td>
        <td class="middle">${item.sendAmout}</td>
        <td class="middle">${item.sendTime}</td>
    </tr>
</#list>
</table>
<div id="batchSmsStatisticsPager"></div>
<script type="text/javascript">
    pageBarInitBatchSmsStatistics();
</script>


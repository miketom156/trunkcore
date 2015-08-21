<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd">
<script type="text/javascript">
    scanTotalPageCount = ${pageCount};//总页数
    scanPageNum = ${pageNumber};//当前页
    scanPageSize = ${pageSize}; //页大小
    scanActivities = new Object();//清空全局变量用于存储
    scans  = new Array();
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="wechatActivityTbData">
    <tr>
        <th class="middle">扫描次数</th>
        <th class="middle">扫描日期</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.scanCount = ${item.count};
        temp.createDate = '${item.date}';
        scans["${item.date}"] = temp;
    </script>
    <tr>
        <td class="middle" width="80px">
        ${item.count}
        </td>
        <td class="middle" width="120px">
        ${item.date}
        </td>
    </tr>
</#list>
</table>
<div id="wechatActivityScanPager"></div>
<script type="text/javascript">
    pageBarInitWechatActivityScan();
</script>

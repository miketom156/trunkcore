<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd">
<script type="text/javascript">
    followerTotalPageCount = ${pageCount};//总页数
    followerPageNum = ${pageNumber};//当前页
    followerPageSize = ${pageSize}; //页大小
    followers  = new Array();
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="wechatActivityTbData">
    <tr>
        <th class="middle">微信用户存量</th>
        <th class="middle">微信用户增量(日)</th>
        <th class="middle">日期</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.addAmout = ${item.addAmout};
        temp.saveAmout = ${item.saveAmout};
        temp.createDate = '${item.createDate}';
        followers["${item.createDate}"] = temp;
    </script>
    <tr>
        <td class="middle" width="100px">
        ${item.saveAmout}
        </td>
        <td class="middle" width="100px">
        ${item.addAmout}
        </td>
        <td class="middle" width="120px">
        ${item.createDate}
        </td>
    </tr>
</#list>
</table>
<div id="wechatFollowerPager"></div>
<script type="text/javascript">
    pageBarInitWechatFollowerCount();
</script>

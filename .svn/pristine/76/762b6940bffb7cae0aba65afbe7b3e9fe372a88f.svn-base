<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd HH:mm:ss">
<script type="text/javascript">
    totalPageCount = ${pageCount};//总页数
    pageNum = ${pageNumber};//当前页
    pageSize = ${pageSize}; //页大小
    activities = new Object();//清空全局变量用于存储
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="wechatActivityTbData">
    <tr>
        <th class="middle"><input type="checkbox" data-act="isAllSelected"/></th>
        <th class="middle">活动名称</th>
        <th class="middle">描述</th>
        <th class="middle">senceId</th>
        <th class="middle">活动二维码</th>
        <th class="middle">客服消息</th>
        <th class="middle">扫描次数</th>
        <th class="middle">更新日期</th>
        <th class="middle">更新人姓名</th>
        <th class="middle">是否生效</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.id =${item.id};
        temp.senceId = '${item.senceId}';
        temp.msg = '${item.msg?js_string}';
        temp.scanCount = ${item.scanCount};
        temp.activityId = '${item.activityId}';
        temp.activityName = '${item.activityName?js_string}';
        temp.description = '${item.description?js_string}';
        temp.isEffective = ${item.isEffective};
        temp.fileName = '${item.fileName?js_string}';
        temp.imgDomain = '${item.imgDomain?js_string}';
        temp.updateDate = '${item.updateDate}';
        temp.updator = '${item.updator?js_string}';
        activities["${item.id}"] = temp;
    </script>
    <tr>
        <td class="middle" width="40px">
            <input type="checkbox" class="isSelected" name="isSelected"
                   data-act-id="${item.id}"/>
        </td>
        <td class="middle" width="150px">${item.activityName}</td>
        <td class="middle" width="150">${item.description}</td>
        <td class="middle" width="100px">${item.senceId}</td>
        <td class="middle" width="80px">
            <#if item.fileName??>
                <a href='http://${item.imgDomain}/content/activity_QRCode/${item.fileName}' target ='blank' >浏览</a>
                <a href="javascript:;" onclick='downloadQRCode(${item.id})' >下载</a>
            </#if>
        </td>
        <td class="middle" width="*"><div id ="msg_box_${item.id}" style="display:inline;" >${item.msg}</div>
            <div  id ="msg_btn_${item.id}"data-type="1" onclick="showMsg(${item.id})" style="display:inline;color:blue;text-decoration:underline;cursor:pointer;"  >↓</div>
        </td>
        <td class="middle" width="80px"><a href="javascript:;" onclick="showScanDetail(${item.id})">${item.scanCount}</a></td>
        <td class="middle" width="150px">${item.updateDate}</td>
        <td class="middle" width="80px">${item.updator}</td>
        <td class="middle" width="80px">
            <#if item.isEffective == 1>
                是
            <#else>
                否
            </#if>
        </td>
    </tr>
    <script type="text/javascript">
        showMsg(${item.id});
    </script>
</#list>
</table>
<div id="wechatActivityPager"></div>
<script type="text/javascript">
    pageBarInitWechatActivity();
    //绑定全选反选事件
    $("[data-act='isAllSelected']").click(function () {
        var hasChecked = $(this).attr("checked");
        $(".isSelected").each(function (index, element) {
            if (hasChecked) {
                $(element).attr("checked", "checked");
            } else {
                $(element).removeAttr("checked");
            }
        });
    });

    $(":checkbox").each(function (index, element) {
        $(element).click(function (event) {
            event.stopPropagation();
        });
    });


</script>


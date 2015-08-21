<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd">
<script type="text/javascript">
    totalPageCountLogo = ${pageCount};//总页数
    pageNumLogo = ${pageNumber};//当前页
    pageSizeLogo = ${pageSize}; //页大小
    scrollLogos = new Object();//清空全局变量用语存储
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="scrollLogoData">
    <tr>
        <th class="middle"><input type="checkbox" data-act="isAllLogoSelected"/></th>
        <th class="middle">序号</th>
        <th class="middle">标题</th>
        <th class="middle">图片地址</th>
        <th class="middle">链接地址</th>
        <th class="middle">nofollow</th>
        <th class="middle">开始日期</th>
        <th class="middle">截止日期</th>
        <th class="middle">文件模板</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.id =${item.id};
        temp.orderNum = '${item.orderNum}';
        temp.title ='${item.title}';
        temp.imgUrl = '${item.imgUrl?js_string}';
        temp.aUrl = '${item.aUrl}';
        temp.beginDate = '${item.beginDate}';
        temp.endDate = '${item.endDate}';
        temp.template = ${item.template};
        temp.advId =${item.advId};
    <#if item.isNofollow?exists>
        temp.isNofollow =${item.isNofollow};
    </#if>
        scrollLogos["${item.id}"] = temp;
    </script>
    <tr>
        <td class="middle" width="40px">
            <input type="checkbox" class="isLogoSelected" name="isLogoSelected"
                   data-logo-id="${item.id}"/>
        </td>
        <td class="middle" width="60px">${item.orderNum}</td>
        <td class="middle" width="*">${item.title}</td>
        <td class="middle" width="*">${item.imgUrl}</td>
        <td class="middle" width="*">${item.aUrl}</td>
        <td class="middle" width="60px"><#if item.isNofollow?exists><#if item.isNofollow == 1 > 是<#else>否</#if><#else>否</#if></td>
        <td class="middle" width="130px">${item.beginDate}</td>
        <td class="middle" width="130px">${item.endDate}</td>
        <td class="middle" width="130px">
            <span data-act="template" data-act-code="${item.template}" ></span>
        </td>
    </tr>
</#list>
</table>
<div id="scrollLogoPager"></div>
<script type="text/javascript">
    pageBarInitScrollLogo();
    //绑定全选反选事件
    $("[data-act='isAllLogoSelected']").click(function () {
        var hasChecked = $(this).attr("checked");
        $(".isLogoSelected").each(function (index, element) {
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

    //填入所有template中文名
    $("[data-act='template']").each(function(index,element){
        var code = $(element).attr("data-act-code");
        $(element).text(templates[code].nameCN);
    });


     $("[data-id='scrollLogoData']").show();
</script>


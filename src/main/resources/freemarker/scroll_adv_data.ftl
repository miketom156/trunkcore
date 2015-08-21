<#setting number_format="#">
<script type="text/javascript">
    totalPageCountAdv = ${pageCount};//总页数
    pageNumAdv = ${pageNumber};//当前页
    pageSizeAdv = ${pageSize}; //页大小
    scrollAdvs = new Object();//清空全局变量用语存储
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="scrollAdvData">
    <tr>
        <th class="middle"><input type="checkbox" data-act="isAllSelected"/></th>
        <th class="middle">所属站点</th>
        <th class="middle">文件名称</th>
        <th class="middle">描述</th>
        <th class="middle">图片尺寸</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.id =${item.id};
        temp.name = '${item.name}';
        temp.description = '${item.description}';
        temp.siteId = ${item.siteId};
        temp.siteName = '${item.siteName?js_string}';
        <#if item.height??>
        temp.height = ${item.height};
        </#if>
        <#if item.width??>
        temp.width = ${item.width};
        </#if>
        scrollAdvs["${item.id}"] = temp;
    </script>
    <td class="middle" width="40px">
        <input type="checkbox" class="isAdvSelected" name="isAdvSelected"
               data-adv-id="${item.id}"/>
    </td>
    <td class="middle" width="80px">${item.siteName}</td>
    <td class="middle" width="*">${item.name}</td>
    <td class="middle" width="*">${item.description}</td>
    <td class="middle" width="80px">
        <#if item.width??>
            ${item.width}×
            <#else>
                <#if item.height??>
                    ×
                <#else>

                </#if>
        </#if>
        <#if item.height??>${item.height}</#if>
    </td>
    </tr>
</#list>
</table>
<div id="scrollAdvPager"></div>
<script type="text/javascript">
    pageBarInitScrollAdv();
    //绑定全选反选事件
    $("[data-act='isAllSelected']").click(function () {
        var hasChecked = $(this).attr("checked");
        $(".isAdvSelected").each(function (index, element) {
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


    //绑定主表记录选中事件
    $("#scrollAdvData tr").each(function (index, element) {
        if (index != 0) {
            $(element).click(function () {
                onAdvClick($(element));
            });
        }
    });

    //每一次加载表头信息后更新表体信息
    if ($("#scrollAdvData tr").size() > 1) {
        $("#scrollAdvData tr").eq(1).click();
    } else {
        $("[data-id = 'scrollLogoData']").css("display", "none");
    }

    $("[data-id='scrollAdvData']").show();
</script>


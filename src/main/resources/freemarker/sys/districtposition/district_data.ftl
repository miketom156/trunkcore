<#setting number_format="#">
<script type="text/javascript">
    totalPageCountDistrict = ${pageCount};//园区总页数
    pageNumDistrict = ${pageNumber};//园区当前页
    pageSizeDistrict = ${pageSize}; //园区页大小
    districts = new Object();//清空全局变量用语存储区域
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="districtData">
    <tr>
        <th class="middle"><input type="checkbox" data-act="isAllSelected"/></th>
        <th class="middle">园区编号</th>
        <th class="middle">园区名称</th>
        <th class="middle">备注</th>
        <th class="middle">创建人</th>
        <th class="middle">创建日期</th>
        <th class="middle">最近修改人</th>
        <th class="middle">最近更新</th>
        <th class="middle">状态</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.id =${item.id};
        temp.name = '${item.name?js_string}';
        temp.status =${item.status};
        temp.remark = '${item.remark?js_string}';
        temp.creator = '${item.creator}';
        temp.updator = '${item.updator}';
        temp.createDate = '${item.createDate}';
        temp.updateDate = '${item.updateDate}';
        districts["${item.id}"] = temp;
    </script>
    <tr>
        <td class="middle" width="40px">
            <input type="checkbox" class="isDistrictSelected" name="isDistrictSelected"
                   data-district-id="${item.id}"/>
        </td>
        <td class="middle" width="100px">${item.id}</td>
        <td class="middle" width="100px">${item.name}</td>
        <td class="middle" width="200px">${item.remark}</td>
        <td class="middle" width="60px">${item.creator}</td>
        <td class="middle" width="120px">${item.createDate}</td>
        <td class="middle" width="60px">${item.updator}</td>
        <td class="middle" width="120px">${item.updateDate}</td>
        <td class="middle" width="60px">
        <#if item.status == 1>
            生效
        </#if>
        <#if item.status == 2>
            已修改
        </#if>
        <#if item.status == 0>
            失效
        </#if>
        </td>
    </tr>
</#list>
</table>
<div id="districtPager"></div>
<script type="text/javascript">
    pageBarInitDistrict();
    //绑定全选反选时间
    $("[data-act='isAllSelected']").click(function () {
        var hasChecked = $(this).attr("checked");
        $(".isDistrictSelected").each(function (index, element) {
            if (hasChecked) {
                $(element).attr("checked", "checked");
            } else {
                $(element).removeAttr("checked");
            }
        });
    });

    $("#districtTb tr").each(function (index, element) {
        if (index != 0) {
            $(element).click(function () {
                onDistrictClick($(element));
            });
        }
    });

    $(":checkbox").each(function (index, element) {
        $(element).click(function (event) {
            event.stopPropagation();
        });
    });

    //每一次加载表头信息后更新表体信息
    if ($("#districtTb tr").size() > 1) {
        $("#districtTb tr").eq(1).click();
    } else {
        $("[data-id = 'districtComData']").css("display", "none");
    }
    $('[data-id="districtData"]').show();
</script>


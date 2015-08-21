<#setting number_format="#">
<script type="text/javascript">
    totalPageCountDistrictCom = ${pageCount};//园区总页数
    pageNumDistrictCom = ${pageNumber};//园区当前页
    pageSizeDistrictCom = ${pageSize}; //园区页大小
    districtComs = new Object();
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="districtComData">
    <tr>
        <th class="middle"><input type="checkbox" data-act="isAllSelectedCom"/></th>
        <th class="middle">公司名称</th>
        <th class="middle">面试地址</th>
        <th class="middle">联系人</th>
        <th class="middle">电话</th>
        <!-- 头5列是公司信息 -->
        <th class="middle">创建人</th>
        <th class="middle">创建日期</th>
        <th class="middle">最后更新人</th>
        <th class="middle">最后更新</th>
        <th class="middle">状态</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.id =${item.id};
        temp.comName = '${item.comName?js_string}';
        temp.interviewAddress = '${item.interviewAddress?js_string}';
        temp.contactPerson = '${item.contactPerson}';
        temp.contactPhone = '${item.contactPhone}';
        temp.status =${item.status};
        temp.creator = '${item.creator}';
        temp.createDate = '${item.createDate}';
        temp.updatortor = '${item.updator}';
        temp.updateDate = '${item.updateDate}';
        districtComs["${item.id}"] = temp;
    </script>
    <tr>
        <td class="middle" width="40px">
            <input type="checkbox" class="isDistrictComSelected" name="isDistrictComSelected"
                   data-district-id="${item.id}"/>
        </td>
        <td class="middle">${item.comName}</td>
        <td class="middle">${item.interviewAddress}</td>
        <td class="middle" width="60px">${item.contactPerson}</td>
        <td class="middle" width="100px">${item.contactPhone}</td>
        <td class="middle" width="80px">${item.creator}</td>
        <td class="middle" width="140px">${item.createDate}</td>
        <td class="middle" width="80px">${item.updator}</td>
        <td class="middle" width="140px">${item.updateDate}</td>
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
<div id="districtComPager"></div>
<script type="text/javascript">
    pageBarInitDistrictCom();
    //绑定全选反选时间
    $("[data-act='isAllSelectedCom']").click(function () {
        var hasChecked = $(this).attr("checked");
        $(".isDistrictComSelected").each(function (index, element) {
            if (hasChecked) {
                $(element).attr("checked", "checked");
            } else {
                $(element).removeAttr("checked");
            }
        });
    });
    $('[data-id="districtComData"]').show();
</script>


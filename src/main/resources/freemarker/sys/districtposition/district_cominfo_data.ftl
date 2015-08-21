<#setting number_format="#">
<script type="text/javascript">
    totalPageCountDistrictCominfo = ${pageCount};//园区总页数
    pageNumDistrictCominfo = ${pageNumber};//园区当前页
    pageSizeDistrictCominfo = ${pageSize}; //园区页大小
    districtCominfos = new Object();
</script>
<div id="districtCominfoTb" style="padding:5px;padding-top:0;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
           id="districtCominfoData">
        <tr>
            <th class="middle"><input type="checkbox" data-act="isAllSelectedCominfo"/></th>
            <th class="middle">公司编码/ID</th>
            <th class="middle">公司名称</th>
            <th class="middle">面试地址</th>
            <th class="middle">联系人</th>
            <th class="middle">电话</th>
        </tr>
<#list page.items as item >
        <script type="text/javascript">
            var temp = new Object();
            temp.comId =${item.comId};
            temp.comName = '${item.comName?js_string}';
            temp.interviewAddress = '${item.interviewAddress?js_string}';
            temp.contactPerson = '${item.contactPerson}';
            temp.contactPhone = '${item.contactPhone}';
            districtCominfos[temp.comId] =temp;
        </script>
        <tr>
            <td class="middle" width="40px">
                <input type="checkbox" class="isDistrictCominfoSelected" name="isDistrictCominfoSelected"
                       data-district-cominfo-id="${item.comId}"/>
            </td>
            <td class="middle">${item.comId}</td>
            <td class="middle"><a href="/corp/${item.comId}" target="blank" >${item.comName}</a></td>
            <td class="middle">${item.interviewAddress}</td>
            <td class="middle" width="60px">${item.contactPerson}</td>
            <td class="middle" width="100px">${item.contactPhone}</td>
    </#list>
    </table>
</div>
<div id="districtCominfoPageCount" style="display:none;"></div>
<div id="districtCominfoPager" style="margin-left: 5px;"></div>
<script type="text/javascript">
    pageBarInitDistrictCominfo();
    //绑定全选反选时间
    $("[data-act='isAllSelectedCominfo']").click(function () {
        var hasChecked = $(this).attr("checked");
        $(".isDistrictCominfoSelected").each(function (index, element) {
            if (hasChecked) {
                $(element).attr("checked", "checked");
            } else {
                $(element).removeAttr("checked");
            }
        });
    });
</script>


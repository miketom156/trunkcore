<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd HH:mm:ss">
<script type="text/javascript">
     totalPageCountCitySiteRelation = ${pageCount};//总页数
     pageNumCitySiteRelation = ${pageNumber};//当前页
     pageSizeCitySiteRelation = ${pageSize}; //页大小
     citySiteRelations = new Object();//清空全局变量用于存储
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
       id="pv_city_site_tb_data">
    <tr>
        <th class="middle"><input type="checkbox" data-act="is_all_selected_pv_city_site"/></th>
        <th class="middle">城市</th>
        <th class="middle">站点</th>
        <th class="middle">更新时间</th>
        <th class="middle">更新人</th>
    </tr>
<#list page.items as item >
    <script type="text/javascript">
        var temp = new Object();
        temp.id =${item.id};
        temp.cityCode = '${item.cityCode}';
        temp.siteCode = '${item.siteCode}';
        temp.cityName = '${item.cityName}';
        temp.siteName = '${item.siteName}';
        temp.updateTime = '${item.updateTime}';
        temp.updater = '${item.updater?js_string}';
        temp.updaterId = '${item.updaterId?js_string}';
        citySiteRelations["${item.id}"] = temp;
    </script>
    <tr>
        <td class="middle" width="40px">
            <input type="checkbox" class="is_selected_pv_city_site" name="is_selected_pv_city_site"   data-act="is_selected_pv_city_site"
                   data-act-id="${item.id}"/>
        </td>
        <td class="middle" width="100px">${item.cityName}</td>
        <td class="middle" width="100px">${item.siteName}</td>
        <td class="middle" width="120px">${item.updateTime}</td>
        <td class="middle" width="100px">${item.updater}</td>
    </tr>
</#list>
</table>
<script type="text/javascript">
    pageBarInitPvCitySite();
    //绑定全选反选事件
    $("[data-act='is_all_selected_pv_city_site']").click(function () {
        var hasChecked = $(this).attr("checked");
        $(".is_selected_pv_city_site").each(function (index, element) {
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


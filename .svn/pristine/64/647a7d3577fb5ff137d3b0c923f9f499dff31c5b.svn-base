<div id="pv_city_site_panel">
    <div data-id="count_pv_city_site_bar"class="pv_city_count_tool_bar" >
        <input type="button" id="add_pv_city_site" name="add_pv_city_site" value="增加" class="botton_blue"/>
        <input type="button" id="update_pv_city_site" name="update_pv_city_site" value="更新" class="botton_blue"/>
    </div>
    <div id="search_pv_city_site_panel" class= "search_box">
        <input type="hidden" id="search_pv_city_site_siteId" name="search_pv_city_site_siteId" value=""/>
        站点：<input type="text" id="search_pv_city_site_site_name" name="search_pv_city_site_site_name" readonly="readonly"
                  style="width: 100px" value=""/>
        <input type="hidden" id="search_pv_city_city_code" name="search_pv_city_city_code" value=""/>
        城市：<input type="text" id="search_pv_city_city_name" name="search_pv_city_city_name" readonly="readonly"
                  style="width: 100px" value=""/>

        <input type="button" id="search_pv_city_site" name="search_pv_city_site" value="搜索" class="botton_blue"/>
    </div>
    <div id="show_pv_city_site_panel">
    <#include "sys/pv_city_site_data.ftl"  >
    </div>
    <div id="pv_city_site_page_bar"></div>
    <div id="pv_city_site_pager_count" style="display: none;" ></div>
</div>
<script type="text/javascript" >
    //分站点选择事件
    $("#search_pv_city_site_site_name").click(function () {
        selectSite("search_pv_city_site_site_name", "search_pv_city_site_siteId");
    });
    //绑定地点点击事件
    $('#search_pv_city_city_name').click(function(){
        $.jBox(getLocationDialogHtml('select_location_pv_city_site','search_pv_city_city_code','search_pv_city_city_name'),{
            id: "select_location_pv_city_site",
            title: "选择地点",
            width: 540,
            height: 350,
            buttons: {}
        });
    });

    //保存事件
    $('#search_pv_city_site').click(function(){
        citySiteRelationLoadData(1);
    });

    //新增
    $('#add_pv_city_site').click(function(){
        edit_pv_city_site_box();
    });

    //更新
    $('#update_pv_city_site').click(function(){
        var result = getFirstSelectedDataId('is_selected_pv_city_site');
        if(!result||result.length < 1){
            $.jBox.info("请选择1条需要修改的信息。", '提示');
            return false;
        }
        if(result.length > 1){
            $.jBox.info("请选择1条需要修改的信息。", '提示');
            return false;
        }
        var id = result[0];
        if(!id || !citySiteRelations[id] ){
            $.jBox.info("错误。", '提示');
            return false;
        }
        edit_pv_city_site_box(citySiteRelations[id]);
    });


</script>
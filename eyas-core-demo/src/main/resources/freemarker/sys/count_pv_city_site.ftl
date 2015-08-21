<#setting number_format="#">
<#setting datetime_format="yyyy-MM-dd">
<html>
<head>
    <style>
        #pv_city_site_page_bar ul.pages, #count_pv_city_site_pager ul.pages, #count_pv_city_site_day_pager ul.pages {
            width: 100%;
            display: block;
            border: none;
            text-transform: uppercase;
            font-size: 12px;
            margin: 10px 0;
            padding: 0;
        }

        #pv_city_site_page_bar ul.pages li, #count_pv_city_site_pager ul.pages li, #count_pv_city_site_day_pager ul.pages li {
            color: #666;
            list-style: none;
            float: left;
            border: 1px solid #ddd;
            text-decoration: none;
            margin: 0 5px 0 0;
            padding: 2px 10px;
        }

        #pv_city_site_page_bar ul.pages li.pgEmpty, #count_pv_city_site_pager ul.pages li.pgEmpty, #count_pv_city_site_day_pager ul.pages li.pgEmpty {
            border: 1px solid #e0e0e0;
        }

        #pv_city_site_page_bar ul.pages li.pgEmpty, #count_pv_city_site_pager ul.pages li.pgEmpty, #count_pv_city_site_day_pager ul.pages li.pgEmpty {
            border: 1px solid #eee;
            color: #ddd;
        }

        #pv_city_site_page_bar ul.pages li.pgCurrent, #count_pv_city_site_pager ul.pages li.pgCurrent , #count_pv_city_site_day_pager ul.pages li.pgCurrent {
            border: 1px solid #e0e0e0;
            color: #000;
            font-weight: 700;
            background-color: #f0f0f0;
        }

        .search_box, .pv_city_count_tool_bar, .count_pv_city_site_tool_bar {
            vertical-align: middle;
            margin-right: 10px;
        }

        .pv_city_count_tool_bar, .search_box, .count_pv_city_site_tool_bar {
            background: none repeat scroll 0 0 #e8f8fd;
            border-left: 1px solid #9cc;
            border-right: 1px solid #9cc;
            border-top: 1px solid #9cc;
            color: #333;
            float: none;
            font-weight: bold;
            height: 25px;
            line-height: 25px;
            text-align: left;
            padding: 1px;
            margin: 0px;
            width: 98%;
        }

        .box_table td {
            border-bottom: 1px solid #9cc;
            border-right: 1px solid #9cc;
            color: #333;
            font-weight: 100;
            height: 20px;
            line-height: 20px;
            text-align: center;
            text-indent: 5px;
        }

        .select_pv_city_site_checkbox {
            position: relative;
            float: left;
            display: inline-block;
            margin: 2px;
            height: 25px;
            line-height: 25px;
        }

        .select_pv_city_site_span {
            position: relative;
            float: left;
            display: inline-block;
            margin: 2px;
            height: 25px;
            line-height: 25px;
            width: 100px;
        }

        #add_pv_city_site_panel span {
            width: 80px;
            display: inline-block;
            display: inline-block;
            margin: 2px;
            margin-top: 20px;
            text-align: center;
        }

        .count_pv_city_site_chart,.count_pv_city_site_day_chart {
            width: 98%;
            height: 280px;
        }

        .show_count_pv_city_site_panel,.show_count_pv_city_site_day_panel {
            width: 100%;
        }

        #count_pv_city_site_tb_total,#count_pv_city_site_day_tb_total {
            border-left: 1px solid #9cc;
            border-right: 1px solid #9cc;
            border-top: 1px solid #9cc;
            background: none repeat scroll 0 0 #e8f8fd;
            height: 25px;
            line-height: 25px;
            color: red;
        }

        .unselected {
            position: relative;
            margin-right: -14px;
            margin-left: 10px;
            padding: 0px;
            width: 100px;
            line-height: 20px;
            text-align: center;
            display: inline-block;
            background: url("/static/img/sys/bg_btn.gif") repeat-x;
            border: 1px solid #cdcdcd;
            font-size: 10px;
        }

        .selected {
            position: relative;
            margin-right: -14px;
            margin-left: 10px;
            padding: 2px;
            line-height: 20px;
            width: 100px;
            display: inline-block;
            text-align: center;
            background: url("/static/img/sys/bg_btn.gif") repeat-x;
            border: 1px solid #cdcdcd;
            font-size: 14px;
            border-bottom: 1px solid #f9f9f9;
            z-index: 200;
        }

        .tab_panel {
            position: relative;
            top: -1px;
            border: 1px solid #cdcdcd;
            margin: 0;
            background: #f7f7f7;
            z-index: 1;
            width:98%;
            padding:5px;
            min-height: 600px;
        }


    </style>
    <script src="/static/js/v1/sys/jquery/pager/jquery.pager.js"></script>
    <script src="/static/js/v1/sys/SearchShortcuts.js"></script>
    <script src="/static/js/v1/sys/statistics/count_pv_city_site.js"></script>
    <script type="text/javascript">
        var cityAndSite = ${cityAndSite};
        var cityAndSiteMap = new Object();
        for (var i = 0; i < cityAndSite.length; i++) {
            cityAndSiteMap[cityAndSite[i].siteId] = cityAndSite[i];
        }

    </script>
</head>
<body>
<div data-id="count_pv_city_site_panel">
    <div data-id="count_pv_site_tab_panel"  >
        <a class="selected" title="参数详细对比" href="javascript:;" data-act-id="tab" data-tab-index="0">参数详细对比</a>
        <a class="unselected" title="按天对比" href="javascript:;" data-act-id="tab" data-tab-index="1">按天对比</a>
    </div>
    <div data-act-tab="count_pv_site_tab_panel_tab_0"  class="tab_panel">
        <div data-id="count_pv_city_site_search_box_param" class="search_box" style="padding:5px;">
            <span>选择时间查看： </span> <input
                type="text" id="count_pv_city_site_date" name="count_pv_city_site_date"
                style="width: 100px" class="validate[future[begin]]"
                onfocus="WdatePicker({maxDate:'%y-%M-{%d-1}'});"
                placeholder="查询日期"
                value=""/>
            <input type="hidden" id="count_pv_city_select_id" name="site_id"/>
            <span>选择站点/城市： </span><input type="text" id="count_pv_city_select_name" name="site_name"/>
            <input type="button" id="search_count_pv_city_site" name="search_count_pv_city_site" value="查询"
                   class="botton_blue"/>
        </div>
        <div id="count_pv_city_site_chart" data-id="count_pv_city_site_chart" class="count_pv_city_site_chart"></div>
        <div id="count_pv_city_site_tb_total" style="display: none;"></div>
        <div data-id="show_count_pv_city_site_panel" class="show_count_pv_city_site_panel"
             id="show_count_pv_city_site_panel"></div>
        <div data-id="count_pv_city_site_pager" id="count_pv_city_site_pager" class="count_pv_city_site_pager"  style="height: 30px;"></div>
    </div>
    <div style="padding:5px;display: none" data-act-tab="count_pv_site_tab_panel_tab_1" class="tab_panel">
        <div data-id="count_pv_city_site_search_box_day" class="search_box" style="padding:5px;">
            <span>选择时间查看： </span><input type="text" id="count_pv_city_site_start" name="count_pv_city_site_start"
                                        style="width: 100px" class="validate[past[end]]"
                                        onfocus="WdatePicker({onpicked:function(){count_pv_city_site_end.focus();},maxDate:'#F{$dp.$D(\'count_pv_city_site_end\')||\'${yesterday}\'}'})"
                                        placeholder="开始日期" value=""/> 至 <input
                type="text" id="count_pv_city_site_end" name="count_pv_city_site_end"
                style="width: 100px" class="validate[future[begin]]"
                onfocus="WdatePicker({minDate:'#F{$dp.$D(\'count_pv_city_site_start\')}',maxDate:'#F{\'${yesterday}\'}'});"
                placeholder="结束日期"
                value=""/>
            <input type="hidden" id="count_pv_city_select_day_id" name="site_id"/>
            <span>选择站点/城市： </span><input type="text" id="count_pv_city_select_day_name" name="site_name"/>
            <span>选择数据类型： </span>
            <input type="radio" name="dataType" value="1" checked="checked" /><span>城市统计ip量 </span>
            <input type="radio" name="dataType" value="2" /><span>站点统计ip量</span>
            <input type="radio" name="dataType" value="3" /><span>城市统计pv量</span>
            <input type="radio" name="dataType" value="4" /><span>站点统计pv量</span>
            <input type="button" id="search_day_count_pv_city_site" name="search_day_count_pv_city_site" value="查询"
                   class="botton_blue"/>
        </div>
        <div id="count_pv_city_site_day_chart" data-id="count_pv_city_site_day_chart" class="count_pv_city_site_day_chart"></div>
        <div id="count_pv_city_site_day_tb_total" style="display: none;"></div>
        <div data-id="show_count_pv_city_site_day_panel" class="show_count_pv_city_site_day_panel"
             id="show_count_pv_city_site_day_panel"></div>
        <div data-id="count_pv_city_site_day_pager" id="count_pv_city_site_day_pager" class="count_pv_city_site_day_pager"  style="height: 30px;"></div>
    </div>
    <div data-id="count_pv_city_site_info" style="display:none"></div>
</div>
<script type="text/javascript">
    //绑定数据查询事件
    $('#search_count_pv_city_site').click(function () {
        load_pv_count_city_site(null,null, $('#count_pv_city_site_date').val());
    });

    $('#search_day_count_pv_city_site').click(function () {
        load_pv_count_city_site_day($('#count_pv_city_site_start').val(), $('#count_pv_city_site_end').val(),null);
    });

    $('[data-id="count_pv_site_tab_panel"]').find("a").each(function (index, element) {
        $(element).click(function(){
            $('[data-id="count_pv_site_tab_panel"]').find("a").each(function (_index, _element) {
                $(_element).attr("class", "unselected");
                $('[data-act-tab="count_pv_site_tab_panel_tab_' + $(_element).attr('data-tab-index') + '"]').hide();
            });
            $(element).attr("class", "selected");
            $('[data-act-tab="count_pv_site_tab_panel_tab_' + $(element).attr('data-tab-index') + '"]').show();
        });
    });

</script>
</body>
</html>
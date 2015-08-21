<style>
    .search_box {
        vertical-align: middle;
        margin-right: 10px;
    }

    .search_box {
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

    .select_box_checkbox, . select_box_checkbox_all {
        position: relative;
        float: left;
        display: inline-block;
        height: 25px;
        line-height: 25px;
    }

    .select_count_pv_city_all_span {
        position: relative;
        display: inline-block;
        margin: 2px;
        height: 25px;
        line-height: 25px;
        width: 60px;
    }

    .select_box_span {
        position: relative;
        display: inline-block;
        margin: 2px;
        height: 25px;
        line-height: 25px;
        width: 120px;
        text-align: right;
    }

    .select_box_span_check_all {
        position: relative;
        display: inline-block;
        margin: 2px;
        height: 25px;
        color: red;
        line-height: 25px;
        width: 120px;
        text-align: right;
    }

    .select_count_pv_city_all_span_check_all {
        position: relative;
        display: inline-block;
        margin: 2px;
        height: 25px;
        color: red;
        line-height: 25px;
        width: 80px;
        text-align: left;
    }

    #count_pv_city_all_pager ul.pages {
        width: 100%;
        display: block;
        border: none;
        text-transform: uppercase;
        font-size: 12px;
        margin: 10px 0;
        padding: 0;
    }

    #count_pv_city_all_pager ul.pages li {
        color: #666;
        list-style: none;
        float: left;
        border: 1px solid #ddd;
        text-decoration: none;
        margin: 0 5px 0 0;
        padding: 2px 10px;
    }

    #count_pv_city_all_pager ul.pages li.pgEmpty {
        border: 1px solid #e0e0e0;
    }

    #count_pv_city_all_pager ul.pages li.pgEmpty {
        border: 1px solid #eee;
        color: #ddd;
    }

    #count_pv_city_all_pager ul.pages li.pgCurrent {
        border: 1px solid #e0e0e0;
        color: #000;
        font-weight: 700;
        background-color: #f0f0f0;
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

    .count_pv_city_all_data_panel {
        width: 98%;
    }

    .count_pv_city_all_tb_count {
        background: none repeat scroll 0 0 #e8f8fd;
        border-left: 1px solid #9cc;
        border-right: 1px solid #9cc;
        border-top: 1px solid #9cc;
        color: red;
        float: none;
        font-weight: bold;
        height: 25px;
        line-height: 25px;
        text-align: left;
        padding: 1px;
        margin: 0px;
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
        width: 98%;
        padding: 5px;
        min-height: 550px;
    }

    .count_pv_city_all_chart_panel {
        min-height: 600px;;
    }

    #count_pv_city_all_tb_total {
        border-left: 1px solid #9cc;
        border-right: 1px solid #9cc;
        border-top: 1px solid #9cc;
        background: none repeat scroll 0 0 #e8f8fd;
        height: 25px;
        line-height: 25px;
        color: red;
    }

</style>
<script src="/static/js/v1/sys/jquery/pager/jquery.pager.js"></script>
<script src="/static/js/v1/sys/SearchShortcuts.js"></script>
<script src="/static/js/v1/sys/statistics/count_pv_city_all.js"></script>
<script type="text/javascript">
    cities =${cities} ;
    fields =${fields};
</script>
<div data-id="count_pv_city_all_panel">


    <div data-id="count_pv_city_all_tab_panel">
        <a class="selected" title="数据表" href="javascript:;" data-act-id="tab" data-tab-index="0">数据表</a>
        <a class="unselected" title="图表" href="javascript:;" data-act-id="tab" data-tab-index="1">图表</a>
    </div>
<#--  数据表页签 -->
    <div data-act-tab="count_pv_city_all_tab_panel_tab_0" class="tab_panel">
        <div data-id="count_pv_city_all_tool_bar" class="search_box">
            <span>选择时间查看： </span><input type="text" id="count_pv_city_all_start" name="count_pv_city_all_start"
                                        style="width: 100px" class="validate[past[end]]"
                                        onfocus="WdatePicker({onpicked:function(){count_pv_city_all_end.focus();},maxDate:'#F{$dp.$D(\'count_pv_city_all_end\')||\'${yesterday}\'}'})"
                                        placeholder="开始日期" value=""/> 至 <input
                type="text" id="count_pv_city_all_end" name="count_pv_city_all_end"
                style="width: 100px" class="validate[future[begin]]"
                onfocus="WdatePicker({minDate:'#F{$dp.$D(\'count_pv_city_all_start\')}',maxDate:'#F{\'${yesterday}\'}'});"
                placeholder="结束日期"
                value=""/>
            <span>选择城市： </span><input type="text" id="select_city_count_pv_city_all"
                                      name="select_city_count_pv_city_all" class="select_city_count_pv_city_all"/>
            <span>选择统计项： </span><input type="text" id="select_field_count_pv_city_all"
                                       name="select_field_count_pv_city_all" class="select_field_count_pv_city_all"/>
            <input type="button" id="search_count_pv_city_all" name="search_count_pv_city_all" value="查询"
                   class="botton_blue"/>
            <input type="button" id="excel_export_count_pv_city_all" name="excel_export_count_pv_city_all"
                   value="导出excel"
                   class="botton_blue"/>
            &nbsp;&nbsp;
            <a href="/count_pv_city_all_instruction.html" target="_blank">旧版数据导入的说明</a>
        </div>
        <div>
            <div id="count_pv_city_all_tb_total" style="display: none;"></div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="box_table" align="center"
                   id="count_pv_city_all_tb_data">
            </table>
            <div id="count_pv_city_all_data_panel" class="count_pv_city_all_data_panel"></div>
        </div>
        <div id="count_pv_city_all_pager" data-id="count_pv_city_all_pager" style="height:25px;"></div>
        <div data-id="count_pv_city_all_count_info" style="display:none;"></div>
        <table>
            <tr>
                <td>IP总量:来自此城市的IP数量</td>
                <td>PV总量:来自此城市的页面访问量</td>
            </tr>
            <tr>
                <td>有效总数:公司所在地为此城市的职位数</td>
                <td>自有职位:网站会员企业所在地为此城市的职位数</td>
            </tr>
            <tr>
                <td>简历注册:现在所在地在此城市的简历</td>
                <td>职位应聘:有多少个求职者点了应聘职位</td>
            </tr>
            <tr>
                <td>刷新简历:现在所在地在此城市的刷新简历数量</td>
                <td>简历审核通过:现在所在地在此城市的开通简历数量</td>
            </tr>
            <tr>
                <td>企业注册:注册企业所在地在此城市的企业数量</td>
                <td>企业收到简历:企业一共收到了多少份应聘记录</td>
            </tr>
            <tr>
                <td>企业浏览简历:企业查看了多少份简历</td>
                <td>邀请面试:企业发出了多少个邀请面试</td>
            </tr>
            <tr>
                <td>邀请面试企业:有多少个企业发出了邀请面试</td>
                <td>发布职位:真实职位新发布的数量</td>
            </tr>
            </table>
    </div>
    <div data-id="download_form_container" style="display: none;">
        <form data-id="download_form" target="blank" action="/sys/statistics/count_pv_city_all/export_excel">

        </form>
    </div>

<#--  图表页签 -->
    <div data-act-tab="count_pv_city_all_tab_panel_tab_1" class="tab_panel" style="display: none;">
        <div data-id="count_pv_city_all_chart_tool_bar" class="search_box">
            <span>选择时间查看： </span><input type="text" id="count_pv_city_all_start_chart"
                                        name="count_pv_city_all_start_chart"
                                        style="width: 100px" class="validate[past[end]]"
                                        onfocus="WdatePicker({onpicked:function(){count_pv_city_all_end_chart.focus();},maxDate:'#F{$dp.$D(\'count_pv_city_all_end_chart\')||\'${yesterday}\'}'})"
                                        placeholder="开始日期" value=""/> 至 <input
                type="text" id="count_pv_city_all_end_chart" name="count_pv_city_all_end_chart"
                style="width: 100px" class="validate[future[begin]]"
                onfocus="WdatePicker({minDate:'#F{$dp.$D(\'count_pv_city_all_start_chart\')}',maxDate:'#F{\'${yesterday}\'}'});"
                placeholder="结束日期"
                value=""/>
            <span>城市： </span><input type="text" id="select_city_count_pv_city_all_chart"
                                    name="select_city_count_pv_city_all_chart" class="select_city_count_pv_city_all"/>
            <span>统计项： </span><input type="text" id="select_field_count_pv_city_all_chart"
                                     name="select_field_count_pv_city_all_chart"
                                     class="select_field_count_pv_city_all"/>
            <span>图表类型： </span>
            <input type="radio" data-id="chartTypeCountPvCityAll" name="chartTypeCountPvCityAll" value="1"
                   checked="true"/> <span>折线图</span>
            <input type="radio" data-id="chartTypeCountPvCityAll" name="chartTypeCountPvCityAll"
                   value="2"/><span>柱状图</span>
            <input type="radio" data-id="chartTypeCountPvCityAll" name="chartTypeCountPvCityAll" value="3"/>
            <span>饼图</span>
            <input type="button" id="search_count_pv_city_all_chart" name="search_count_pv_city_all_chart" value="查询"
                   class="botton_blue"/>
        </div>
        <div data-id="count_pv_city_all_chart_panel" class="count_pv_city_all_chart_panel"></div>
    </div>
</div>


<script type="text/javascript">
    $('[data-id="count_pv_city_all_tab_panel"]').find("a").each(function (index, element) {
        $(element).click(function () {
            $('[data-id="count_pv_city_all_tab_panel"]').find("a").each(function (_index, _element) {
                $(_element).attr("class", "unselected");
                $('[data-act-tab="count_pv_city_all_tab_panel_tab_' + $(_element).attr('data-tab-index') + '"]').hide();
            });
            $(element).attr("class", "selected");
            $('[data-act-tab="count_pv_city_all_tab_panel_tab_' + $(element).attr('data-tab-index') + '"]').show();
            showType = $(element).attr('data-tab-index');
        });
    });

</script>
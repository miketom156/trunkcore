<#setting datetime_format="yyyy-MM-dd">
<#setting number_format="#">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="/static/style/v1/sys/city.css" rel="stylesheet" type="text/css"/>
    <script src="/static/js/v1/common/base_common.min.js"></script>
    <script src="/static/js/v1/common/jqPlugs_common.min.js"></script>
    <script src="/static/js/v1/common/jquery.boxSelector.min.js"></script>
    <script src="/static/js/v1/sys/jquery/pager/jquery.pager.js"></script>
    <script src="/static/js/v1/sys/SearchShortcuts.js"></script>
    <style>

        #batchSmsStatisticsPager ul.pages{
            width: 100%;
            display: block;
            border: none;
            text-transform: uppercase;
            font-size: 12px;
            margin: 10px 0;
            padding: 0;
        }

        #batchSmsStatisticsPager ul.pages li{
            color: #666;
            list-style: none;
            float: left;
            border: 1px solid #ddd;
            text-decoration: none;
            margin: 0 5px 0 0;
            padding: 2px 10px;
        }

        #batchSmsStatisticsPager ul.pages li.pgEmpty{
            border: 1px solid #e0e0e0;
        }

        #batchSmsStatisticsPager ul.pages li.pgEmpty{
            border: 1px solid #eee;
            color: #ddd;
        }

        #batchSmsStatisticsPager ul.pages li.pgCurrent {
            border: 1px solid #e0e0e0;
            color: #000;
            font-weight: 700;
            background-color: #f0f0f0;
        }

        .batchSmsStatistic_ssearch_box{
            float: none;
            background-color: #FFFFCC;
            text-align: center;
            background: #E8F8FD;
            border: #9cc 1px solid;
            color: #333;
            height: 30px;
            line-height: 30px;
            font-weight: 100;
            text-align: left;
            font-weight: bold;
        }


        .batchSmsStatistic_ssearch_box {
            vertical-align: middle;
            margin-right: 10px;
        }


        .botton_blue {
            margin-right: 2px;
        }

        .box_table td{
            line-height:20px;
            height:20px;
            border-right:1px solid #9cc;
        }

        .smsStaticDivTitle{
            text-align: left;
            background-color: #e8f8fd;
            height:30px;
            border-right:1px solid #9cc;
            border-left:1px solid #9cc;
            line-height:30px;
            font-size: 12px;
            text-align: center;
            font-weight: bold;
        }
    </style>
    <script type="text/javascript">
        var pageNum;//存放当前页码
        var totalPageCount;//存放总页数
        var pageSize; //页大小
        var smsSendLogs = new Object();//清空全局变量用于存储

        //绑定快捷键
        SearchShortcuts.bindShortcutsByContainer($("#batchSmsStatistics_search_form"), $("#batchSmsStatisticsDateSearch"));

        function pageBarInitBatchSmsStatistics() {
            $("#batchSmsStatisticsPager").pager({pagenumber: pageNum, pagecount: totalPageCount, buttonClickCallback: BatchSmsStatisticsLoadData});
        }


        function BatchSmsStatisticsLoadData(pageNum) {
            var url = "/sys/sms/statistics/ajax/list";
            var param = {
                userName: $("#search_userName").val(),
                startDate: $("#startDate").val(),
                endDate: $("#endDate").val(),
                pn: pageNum
            };
            ajaxLoadDataList(url, param, "batchSmsStatisticsTb", "batchSmsStatisticsCount");
        }

    </script>
</head>
<div class="right_box">
    <div class="main_info">
        <div data-id="batchSmsStatisticsData" style="width:600px;" >
            <div class="batchSmsStatistic_ssearch_box" style="margin:0px;padding:0px;">
                <form action="" id="batchSmsStatistics_search_form" method="post">
                    <div>
                        发送人：<input type="text" id="search_userName" name="userName" class="search_name"/>
                        <label style="display:inline-block; width:80px;text-align: center">发送日期：</label>
                        <input type="text" id="startDate" name="begin" style="width: 100px"
                               onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'});" placeholder="开始日期"
                               value=""/>
                        <label style="display:inline-block; width:30px;text-align: center">至</label>
                        <input type="text" id="endDate" name="end" style="width: 100px"
                               onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'});" placeholder="结束日期"
                               value=""/>
                        <input type="button" id="batchSmsStatisticsDateSearch" name="batchSmsStatisticsdateSearch"
                               value="搜索" class="botton_blue"/>
                    </div>
                </form>
            </div>
            <div id="batchSmsStatisticsTb"  ></div>
            <div id="batchSmsStatisticsCount" style="display:none;"></div>
        </div>
    </div>
</div>
<script>
    //加载第一页的数据
    BatchSmsStatisticsLoadData(1);
    //绑定搜索函数
    $("#batchSmsStatisticsDateSearch").click(function () {
        BatchSmsStatisticsLoadData(1);
    });
</script>
</html>
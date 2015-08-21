<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="/static/js/v1/sys/jquery/pager/Pager.css" rel="stylesheet" type="text/css"/>
    <style>
        #wechatActivityPager ul.pages ,#wechatActivityScanPager ul.pages，,#wechatFollowerPager ul.pages{
            width: 100%;
            display: block;
            border: none;
            text-transform: uppercase;
            font-size: 12px;
            margin: 10px 0;
            padding: 0;
        }

        #wechatActivityPager ul.pages li,#wechatActivityScanPager ul.pages li,#wechatFollowerPager ul.pages li {
            color: #666;
            list-style: none;
            float: left;
            border: 1px solid #ddd;
            text-decoration: none;
            margin: 0 5px 0 0;
            padding: 2px 10px;
        }

        #wechatActivityPager ul.pages li.pgEmpty,#wechatActivityScanPager ul.pages li.pgEmpty ,#wechatFollowerPager ul.pages li.pgEmpty {
            border: 1px solid #e0e0e0;
        }

        #wechatActivityPager ul.pages li.pgEmpty,#wechatActivityScanPager ul.pages li.pgEmpty ,#wechatFollowerPager ul.pages li.pgEmpty {
            border: 1px solid #eee;
            color: #ddd;
        }

        #wechatActivityPager ul.pages li.pgCurrent,#wechatActivityScanPager ul.pages li.pgCurrent,#wechatFollowerPager ul.pages li.pgCurrent{
            border: 1px solid #e0e0e0;
            color: #000;
            font-weight: 700;
            background-color: #f0f0f0;
        }

        .search_box, .Tbbtn{
            float: none;
            background-color: #FFFFCC;
            text-align: center;
            background: #E8F8FD;
            border-top: #9cc 1px solid;
            border-left: #9cc 1px solid;
            border-right: #9cc 1px solid;
            color: #333;
            height: 30px;
            line-height: 30px;
            font-weight: 100;
            text-align: left;
            font-weight: bold;
        }

        .Tbbtn, {
            height: auto;
        }

        .search_box {
            vertical-align: middle;
            margin-right: 10px;
        }

        .selectedTr {
            background-color: lightgoldenrodyellow;
        }

        .botton_blue {
            margin-right: 2px;
        }

        .box_table td{
            line-height:25px;
            height:25px;
        }

        .search_effective, .search_name {
            vertical-align: middle;
            margin-right: 10px;
        }

    </style>
    <script src="/static/js/v1/sys/jquery/pager/jquery.pager.js"></script>
    <script src="/static/js/v1/third/plug/validation/jquery.validate.js"></script>
    <script src="/static/js/v1/sys/SearchShortcuts.js"></script>
    <script type="text/javascript">
        var activities = new Object();
        var totalPageCount = 0;//主表总页数
        var pageNum = 0;//主表当前页
        var pageSize = 0; //主表页大小
        var scanTotalPageCount = 0;//详细表总页数
        var scanPageNum = 0;//详细表当前页
        var scanPageSize = 0; //详细表页大小
        var followerTotalPageCount = 0;//总页数
        var followerPageNum = 0;//当前页
        var followerPageSize = 0; //页大小

        htmlencode = function (str, type) {
            if(!str || str == null){
                  return '';
            }
            if(typeof(str) != "string"){
               return str;
            }
            str = str.replace(/&/g, '&amp;');
            str = str.replace(/</g, '&lt;');
            str = str.replace(/>/g, '&gt;');
            str = str.replace(/  /g, '&nbsp; ');
            str = str.replace(/\t/g, '&nbsp; &nbsp; ');
            str = str.replace(/x22/g, '&quot;');
            str = str.replace(/x27/g, '&#39;');
            str = str.replace(/\u003c/g, '&lt;');
            str = str.replace(/\u003e/g, '&gt;');
            str = str.replace(/"/g, '&quot;');
            str = str.replace(/'/g, '&#39;');
            return str;
        };

        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1,                 //月份
                "d+": this.getDate(),                    //日
                "h+": this.getHours(),                   //小时
                "m+": this.getMinutes(),                 //分
                "s+": this.getSeconds(),                 //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds()             //毫秒
            };
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        };

    </script>
    <script src="/static/js/v1/sys/wechatActivity/wechat_activity.js"></script>
</head>
<html>
<div class="right_box">
    <div class="main_info">
        <div data-id="wechatActivityData" >
            <div class="Tbbtn">
                <input type="button" id="add" name="add" value="添加" class="botton_blue"/>
                <input type="button" id="update" name="update" value="更新" class="botton_blue"/>
                <input type="button" id="effective" name="effective" value="生效" class="botton_blue"/>
                <input type="button" id="ineffective" name="ineffective" value="失效" class="botton_blue"/>
                <input type="button" id="editSenceId" name="editSenceId" value="修改senceId" class="botton_blue"/>
                <input type="button" id="viewFollower" name="viewFollower" value="粉丝数量明细(日)" class="botton_blue"/>
            </div>
            <div class="search_box" style="margin:0px;width:auto;padding:0px;">
                <form action="" id="search_form" method="post">
                    <div>
                        活动名称：<input type="text" id="search_activityName" name="activityName"  class="search_name"/>
                        状态：
                        <label>生效</label><input type="radio" data-id="search_effective"
                                                name="search_effective" class="search_effective"
                                                value="1"/>
                        <label>失效</label><input type="radio" data-id="search_effective"
                                                name="search_effective" class="search_effective" value="0"/>
                        <input type="button" id="dateSearch" name="dateSearch" value="搜索" class="botton_blue"/>
                    </div>
                </form>
            </div>
            <div id="wechatActivityTb"></div>
            <div id="wechatActivityCount" style="display:none;"></div>
        </div>
    </div>
</div>
<div id = "download_QRCode"></div>
<script type="text/javascript">
    WechatActivityLoadData(1);//进入页面自动加载第一页

    $('#dateSearch').click(function (event) {
        WechatActivityLoadData(1);
        event.preventDefault();
        event.stopPropagation();
        return false;
    });



    //绑定查询快捷键
    SearchShortcuts.bindShortcutsByContainer($("#search_form"), $("#dateSearch"));

    $('#add').click(function () {
        saveWechatActivity('');
    });

    $('#update').click(function () {
        updateWechatActivity();
    });

    $('#effective').click(function () {
        effective();
    });

    $('#ineffective').click(function () {
        ineffective();
    });

    $('#editSenceId').click(function () {
        editSenceId();
    });
    //查看粉丝数量明细
    $('#viewFollower').click(function () {
        showFollowerDayly();
    });

</script>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="/static/js/v1/sys/jquery/pager/Pager.css" rel="stylesheet" type="text/css"/>
    <style>

        #scrollLogoPager ul.pages, #scrollAdvPager ul.pages {
            width: 100%;
            display: block;
            border: none;
            text-transform: uppercase;
            font-size: 12px;
            margin: 10px 0;
            padding: 0;
        }

        #scrollLogoPager ul.pages li, #scrollAdvPager ul.pages li {
            color: #666;
            list-style: none;
            float: left;
            border: 1px solid #ddd;
            text-decoration: none;
            margin: 0 5px 0 0;
            padding: 2px 10px;
        }

        #scrollLogoPager ul.pages li.pgEmpty, #scrollAdvPager ul.pages li.pgEmpty {
            border: 1px solid #e0e0e0;
        }

        #scrollLogoPager ul.pages li.pgEmpty, #scrollAdvPager ul.pages li.pgEmpty {
            border: 1px solid #eee;
            color: #ddd;
        }

        #scrollLogoPager ul.pages li.pgCurrent, #scrollAdvPager ul.pages li.pgCurrent {
            border: 1px solid #e0e0e0;
            color: #000;
            font-weight: 700;
            background-color: #f0f0f0;
        }

        .search_box_scroll_logo, .scrollLogoTbbtn, .search_box_scroll_adv, .scrollAdvTbbtn {
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

        .scrollLogoTbbtn, .scrollAdvTbbtn {
            height: auto;
        }

        .search_box_scroll_logo, .search_box_scroll_adv {
            vertical-align: middle;
            margin-right: 10px;
        }

        .selectedAdvTr {
            background-color: lightgoldenrodyellow;
        }

        .botton_blue {
            margin-right: 2px;
        }

        .box_table td{
            line-height:25px;
            height:25px;
        }

    </style>
    <script src="/static/js/v1/sys/jquery/pager/jquery.pager.js"></script>
    <script src="/static/js/v1/third/plug/validation/jquery.validate.js"></script>
    <script src="/static/js/v1/sys/SearchShortcuts.js"></script>
    <script type="text/javascript">
        var scrollAdvs = new Object();
        var totalPageCountAdv = 0;//主表总页数
        var pageNumAdv = 0;//主表当前页
        var pageSizeAdv = 0; //主表页大小
        var scrollLogos = new Object();
        var totalPageCountLogo = 0;//从表总页数
        var pageNumLogo = 0;//从表当前页
        var pageSizeLogo = 0; //从表页大小

        //保存当前模板种类
        var templates = new Object();
        <#list templates as template >
            var temp = new Object();
            temp.code =${template.code};
            temp.nameCN ='${template.nameCN?js_string}';
            temp.url = '${template.url?js_string}';
            templates[temp.code] = temp;
        </#list>


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

        $(document).ready(function () {
            //分站点选择事件
            $("#search_siteName").click(function () {
                selectSite("search_siteName", "search_siteId");
            });


        });

    </script>
    <script src="/static/js/v1/sys/logo/scroll_logo.js"></script>
    <script src="/static/js/v1/sys/logo/scroll_adv.js"></script>
</head>
<html>
<div class="right_box">
    <h4 style="color:red;height:24px;">备注：每天00:00将会自动抓取有效期内的切屏广告，并覆盖首页相关内容</h4>

    <div class="main_info">
        <div data-id="scrollAdvData" style="display:none;">
            <div class="scrollAdvTbbtn">
                <input type="button" id="addScrollAdv" name="addScrollAdv" value="添加" class="botton_blue"/>
                <input type="button" id="updateScrollAdv" name="updateScrollAdv" value="更新" class="botton_blue"/>
                <input type="button" id="generateFile" name="generateFile" value="生成文件" class="botton_blue"/>
            </div>
            <div class="search_box_scroll_adv" style="margin:0px;">
                <form action="" id="search_form_scroll_adv" method="post">
                    <div>
                        <input type="hidden" id="search_siteId" name="search_siteId" value="${siteId}"/>
                        所属分站点：<input type="text" id="search_siteName" name="search_siteName" readonly="readonly"
                                     style="width: 100px" value=""/>
                        文件名称：<input type="text" id="search_scroll_adv_name" class="search_scroll_adv_name"/>
                        <input type="button" id="dateSearchAdv" name="dateSearchAdv" value="搜索" class="botton_blue"/>
                    </div>
                </form>
            </div>
            <div id="scrollAdvTb"></div>
            <div id="scrollAdvPageCount" style="display:none;"></div>

            <!--  =============================================下面为从表信息============================================================ -->
            <div data-id="scrollLogoData" style="display:none;margin-top: 40px;">
                <h4 style="color:red;height:24px;">备注：结束日期为空的话将视为由开始日期起一直有效</h4>
                <div class="scrollLogoTbbtn">
                    <input type="button" id="addScrollLogo" name="addScrollLogo" value="添加" class="botton_blue"/>
                    <input type="button" id="updateScrollLogo" name="updateScrollLogo" value="更新" class="botton_blue"/>
                    <input type="button" id="deleteScrollLogo" name="deleteScrollLogo" value="删除" class="botton_blue"/>
                </div>
                <div class="search_box_scroll_logo" style="margin:0px;">
                    <form action="" id="search_form_scroll_logo" method="post">
                        <div>
                            <input name="searchBeginDate" id="searchBeginDate" type="text" value=""
                                   class="inpt Wdate" onFocus="WdatePicker();" value=""
                                   placeholder="开始日期" style="width:85px;"/>
                            -
                            <input name="searchEndDate" id="searchEndDate" type="text" value=""
                                   class="inpt Wdate"
                                   onFocus="WdatePicker();" value="" placeholder="结束日期"
                                   style="width:85px;"/>
                            <input type="button" id="dateSearchLogo" name="dateSearchLogo" value="搜索" class="botton_blue"/>
                        </div>
                    </form>
                </div>
                <div id="scrollLogoTb"></div>
                <div id="scrollLogoPageCount" style="display:none;"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    advLoadData(1);//进入页面自动加载第一页

    $('#dateSearchAdv').click(function () {
        advLoadData(1);
    });

    $('#dateSearchLogo').click(function () {
        var idCurRow = $("[data-selected=true]").find(".isAdvSelected").attr("data-adv-id");
        logoLoadData(idCurRow,1);
    });

    //绑定查询快捷键
    SearchShortcuts.bindShortcutsByContainer($("#search_form_scroll_adv"), $("#dateSearchAdv"));
    SearchShortcuts.bindShortcutsByContainer($("#search_form_scroll_logo"), $("#dateSearchLogo"));

    $('#addScrollAdv').click(function () {
        saveScrollAdv(null,true);
    });
    $('#addScrollLogo').click(function () {
        saveScrollLogo();
    });

    $('#updateScrollAdv').click(function () {
        updateScrollAdv();
    });
    $('#updateScrollLogo').click(function () {
        updateScrollLogo();
    });

    $('#generateFile').click(function () {
        generateFile();
    });

    $('#deleteScrollLogo').click(function () {
        deleteScrollLogo();
    });


</script>
</html>
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
    <script type="text/javascript">

        var param = new Object();//用于存放查询参数

        $(document).ready(function () {
            param.createDateBegin = '${searchCondition.createDateBegin?js_string}';
            param.createDateEnd = '${searchCondition.createDateEnd?js_string}';
        <#if count??>
            param.maxResult =${count};
        </#if>
            param.freDateBegin = '${searchCondition.freDateBegin?js_string}';
            param.freDateEnd = '${searchCondition.freDateEnd?js_string}';
        <#if searchCondition.pass??>
            param.pass = ${searchCondition.pass};
        </#if>
            param.location = '${searchCondition.location?js_string}';
            param.jobLocation = '${searchCondition.jobLocation?js_string}';
            param.workYearBegin = '${searchCondition.workYearBegin?js_string}';
            param.workYearEnd = '${searchCondition.workYearEnd?js_string}';
        <#if searchCondition.degreeBegin??>
            param.degreeBegin =  ${searchCondition.degreeBegin};
        </#if>
        <#if searchCondition.degreeEnd??>
            param.degreeEnd =  ${searchCondition.degreeEnd};
        </#if>
        <#if searchCondition.age1??>
            param.age1 =  ${searchCondition.age1};
        </#if>
        <#if searchCondition.age2??>
            param.age2 =  ${searchCondition.age2};
        </#if>
        <#if searchCondition.gender??>
            param.gender =  ${searchCondition.gender};
        </#if>
            var selectedJobs = new Array();
        <#list searchCondition.selectedJob as industry >
            selectedJobs.push(${industry});
        </#list>
            if(selectedJobs.length>0){
            param.selectedJob = selectedJobs;
            }
            //绑定函数
            $('#sendSms').click(sendSms);
        });

        /**
         * 发送短信
         */
        function sendSms() {
            if(!param.maxResult||param.maxResult<=0){
                $.jBox.info('没有符合条件的用户','提示');
                return;
            }
            if( $('#targetMsg').val() == ''){
                $.jBox.info('请输入发送的短信内容。','提示');
                return;
            }
            $.jBox.confirm("您确定需要送送短信吗？", "提示", function (v, h, f) {
                if (v == 'ok') {
                    $.jBox.tip("短信发送中...请稍候...", "loading");
                    param.msg = $('#targetMsg').val();
                    $.ajax({
                        type: "POST",
                        data: param,
                        traditional: true,
                        url: "/sys/sms/sendSms.json",
                        dataType: "json",
                        success: function (data) {
                            $.jBox.closeTip();
                            if (data && data.ret && data.ret == 1) {
                                $.jBox.info("短信发送成功。");
                                WechatActivityLoadData(pageNum);
                            } else {
                                if (data.msg) {
                                    $.jBox.tip(data.msg);
                                } else if (data.message) {
                                    $.jBox.tip(data.message);
                                }
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            $.jBox.tip("内部服务器错误！");
                        }
                    });
                }
            });
        }


    </script>
</head>
<div class="right_box">
<div class="main_info">
<div class="box_full">
<form id="resume_filt_form">
<table width="100%" border="0" cellspacing="3" cellpadding="0" align="center">
<tr>
    <td colspan="3" class="filtResult" style="font-size: 15px;">筛选结果：</td>
</tr>
<tr>
    <td align="right" width="6%"></td>
    <td align="right" width="15%">发送目标人数：</td>
    <td><span style="color:red;text-decoration :underline;">${count}</span></td>
</tr>
<tr>
    <td colspan="3" class="filtResult" style="font-size: 15px;">筛选条件：</td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<#if (searchCondition.createDateBegin??&&(searchCondition.createDateBegin)?length>0)
|| (searchCondition.createDateEnd??&&(searchCondition.createDateEnd)?length>0) >
<tr id="filedis">
    <td align="right" width="6%"></td>
    <td align="right" width="15%">简历注册时间：</td>
    <td width="83%">
        <#if (searchCondition.createDateBegin??&&(searchCondition.createDateBegin)?length>0)
        && (searchCondition.createDateEnd??&&(searchCondition.createDateEnd)?length>0)>
        ${searchCondition.createDateBegin}&nbsp;至&nbsp;${searchCondition.createDateEnd}
        <#else>
            <#if (searchCondition.createDateBegin??&&(searchCondition.createDateBegin)?length>0) >
            ${searchCondition.createDateBegin}&nbsp;以后
            </#if>
            <#if (searchCondition.createDateEnd??&&(searchCondition.createDateEnd)?length>0)>
            ${searchCondition.createDateEnd}&nbsp;以前
            </#if>
        </#if>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
</#if>
<#if (searchCondition.freDateBegin??&&(searchCondition.freDateBegin)?length>0)
|| (searchCondition.freDateEnd??&&(searchCondition.freDateEnd)?length>0) >
<tr id="filedis">
    <td align="right" width="6%"></td>
    <td align="right" width="15%">简历刷新时间：</td>
    <td width="83%">
        <#if  (searchCondition.freDateBegin??&&(searchCondition.freDateBegin)?length>0)&&
        (searchCondition.freDateEnd??&&(searchCondition.freDateEnd)?length>0)>
        ${searchCondition.freDateBegin}&nbsp;至&nbsp;${searchCondition.freDateEnd}
        <#else>
            <#if  (searchCondition.freDateBegin??&&(searchCondition.freDateBegin)?length>0)>
            ${searchCondition.freDateBegin}&nbsp;以后
            </#if>
            <#if (searchCondition.freDateEnd??&&(searchCondition.freDateEnd)?length>0)>
            ${searchCondition.freDateEnd}&nbsp;以前
            </#if>
        </#if>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
</#if>
<tr>
    <td align="right" width="6%"></td>
    <td align="right">&nbsp;简历状态：</td>
    <td>
    <#if searchCondition.pass??>
        <#if searchCondition.pass == 1>
            审核通过
        </#if>
        <#if searchCondition.pass == 0>
            未审核通过
        </#if>
    <#else>
        所有简历
    </#if>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<#if searchCondition.location??>
<tr>
    <td align="right"></td>
    <td align="right">&nbsp;现所在地：</td>
    <td id="locationTd">
        <script type="text/javascript">
            $('#locationTd').text(common.json.getAreaById(${searchCondition.location?js_string}));
        </script>
    </td>
</tr>
</#if>
<#if searchCondition.jobLocation??>
<tr>
    <td align="right"></td>
    <td align="right">&nbsp;工作地点（求职意向）：</td>
    <td id='jobLocationTd'>
        <script type="text/javascript">
            $('#jobLocationTd').text(common.json.getAreaById(${searchCondition.jobLocation?js_string}));
        </script>
    </td>
</tr>
</#if>
<#if searchCondition.location?? || searchCondition.jobLocation??>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
</#if>
<#if searchCondition.workYearBegin?? || searchCondition.workYearEnd??>
<tr>
    <td align="right" width="6%"></td>
    <td align="right">工作年限：</td>
    <td>
        <#if searchCondition.workYearBegin??&& searchCondition.workYearEnd??>
            从&nbsp;${searchCondition.workYearBegin} &nbsp;年&nbsp;到&nbsp;${searchCondition.workYearEnd}&nbsp;年
        <#else>
            <#if searchCondition.workYearBegin??>
            ${searchCondition.workYearBegin}&nbsp;年以上
            </#if>
            <#if searchCondition.workYearEnd??>
            ${searchCondition.workYearEnd}&nbsp;年以下
            </#if>
        </#if>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
</#if>
<#if searchCondition.degreeBegin?? || searchCondition.degreeEnd??>
<tr>
    <td align="right" width="6%"></td>
    <td align="right">学历：</td>
    <td id="degreeTd">
        <script type="text/javascript">
                <#if searchCondition.degreeBegin?? && searchCondition.degreeEnd??>
                $('#degreeTd').html('从&nbsp;'
                        + common.json.getNameById(datajson.degree,${searchCondition.degreeBegin})
                        + '&nbsp;&nbsp;到&nbsp;'
                        + common.json.getNameById(datajson.degree,${searchCondition.degreeEnd}) + '&nbsp;');
                <#else>
                    <#if searchCondition.degreeBegin??>
                    $('#degreeTd').html('&nbsp;'
                            + common.json.getNameById(datajson.degree,${searchCondition.degreeBegin})
                            + '&nbsp;以上');
                    </#if>
                    <#if searchCondition.degreeEnd??>
                    $('#degreeTd').html('&nbsp;'
                            + common.json.getNameById(datajson.degree,${searchCondition.degreeEnd})
                            + '&nbsp;以下');
                    </#if>
                </#if>
        </script>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
</#if>
<#if searchCondition.age1?? || searchCondition.age2??>
<tr>
    <td align="right"></td>
    <td align="right">年龄：</td>
    <td>
        <#if searchCondition.age1??>从&nbsp;${searchCondition.age1}&nbsp;岁<#else>不限</#if>
        &nbsp;到&nbsp;
        <#if searchCondition.age2??>${searchCondition.age2}&nbsp;岁<#else>不限</#if>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
</#if>
<tr>
    <td align="right"></td>
    <td align="right">性别：</td>
    <td height="25">
    <#if searchCondition.gender??>
        <#if searchCondition.gender == 1>
            男
        <#else>
            女
        </#if>
    <#else>
        不限
    </#if>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
<#if searchCondition.selectedJob??>
<tr>
    <td align="right"></td>
    <td align="right">求职意向：</td>
    <td id="selectedJobTd">
        <#list searchCondition.selectedJob as industry >
            <#if industry_has_next>
                <script type="text/javascript">
                    $('#selectedJobTd').text($('#selectedJobTd').text() + common.json.getNameById(datajson.jobFun,${industry}) + ',');
                </script>
            <#else>
                <script type="text/javascript">
                    $('#selectedJobTd').text($('#selectedJobTd').text() + common.json.getNameById(datajson.jobFun,${industry}));
                </script>
            </#if>
        </#list>
    </td>
</tr>
<tr style="background:#cecfce">
    <td align="right" colspan="3" height="1"></td>
</tr>
</#if>
</table>
</form>
<table width="100%" align="center" border="0" cellpadding="0" cellspacing="3">
    <tr>
        <td style="font-size: 15px;">
            消息内容：
        </td>
    </tr>
    <tr>
        <td align="center" width="*" height="200px">
            <textarea name="targetMsg" id="targetMsg" style="display: block;width:500px;height:200px;"></textarea>
        </td>
    </tr>
    <tr>
        <td align="center">
            <br/>
            <input type="button" id="sendSms" class="botton_blue_large" value="发送短信"/>&nbsp;
        </td>
    </tr>
</table>
</div>
</div>
</div>
</html>
<table width="100%" border="0" cellspacing="0" cellpadding="5">
    <tr>
        <td style="font-size:14px;line-height:28px">
        ${introduceText}
        </td>
    </tr>
    <tr>
        <td>
            <div style="border:2px solid #dfdfdf">
                <table width="100%" border="0" cellspacing="0" cellpadding="12" bgcolor="#FFFFFF">
                    <tr style="color:#8b8b8b; text-align:center;">
                        <td style="border-bottom: 1px solid #eee;font-size:12px">编号</td>
                        <td style="border-bottom: 1px solid #eee;font-size:12px">姓名</td>
                        <td style="border-bottom: 1px solid #eee;font-size:12px">性别</td>
                        <td style="border-bottom: 1px solid #eee;font-size:12px">工作经验</td>
                        <td style="border-bottom: 1px solid #eee;font-size:12px">学历</td>
                        <td style="border-bottom: 1px solid #eee;font-size:12px">专业</td>
                        <td style="border-bottom: 1px solid #eee;font-size:12px">推荐备注</td>
                    </tr>
                <#list perList as per>
                    <tr style="color:#343434; text-align:center;">
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">
                        	<a href="${siteUrl}/resume/view/${(per.id)?c}" target="_blank" style="color:#0075c7">${(per.id * 102)?c}
                        </a>
                        </td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${per.perUserVo.userName}</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${per.basicDataVo.gender}</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${per.basicDataVo.jobyearType}</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${per.basicDataVo.highDegree}</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${per.basicDataVo.speciality}</td>
                        <#if remarkMap??>
                            <td style="border-bottom: 1px dashed #eee;font-size:12px">${remarkMap[(per.id)?c]}</td>
                        <#else>
                            <td style="border-bottom: 1px dashed #eee;font-size:12px"><input type="text" name="${(per.id)?c}_remark"/></td>
                        </#if>
                    </tr>
                </#list>
                </table>
            </div>
            <br/>

            <div style="border:2px solid #dfdfdf">
                <table width="100%" border="0" cellspacing="0" cellpadding="12" bgcolor="#FFFFFF">
                    <tr style="color:#8b8b8b; text-align:left;">
                        <td colspan="2" style="border-bottom: 1px solid #eee;font-size:12px">我的联系方式</td>
                        <td colspan="2" style="border-bottom: 1px solid #eee;font-size:12px"></td>
                    </tr>
                    <tr style="color:#343434; text-align:left;">
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">手机：</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${sysUser.mobile}</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">电话：</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${sysUser.phone}</td>
                    </tr>
                    <tr style="color:#343434; text-align:left;">
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">传真：</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${sysUser.fax}</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">邮箱：</td>
                        <td style="border-bottom: 1px dashed #eee;font-size:12px">${sysUser.userEmail}</td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
</table>

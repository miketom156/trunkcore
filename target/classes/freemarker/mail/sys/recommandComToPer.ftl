<#macro getPosCount count>
    <#if count = 0>若干<#else>${count}</#if>
</#macro>
<p style="text-indent:2em;">根据您的求职意向，智通人才网招聘顾问将您的简历推荐给以下正在招聘的企业，希望能符合您的要求，祝您早日找到满意的工作。</p>
<br/>
<#list posList as pos>
<table width="100%" class="content" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td align="right">企业名称：</td>
        <td><a href="${siteUrl}/corp/${pos.comInfoVo.id}" target="_blank">${pos.comInfoVo.comName}</a></td>
    </tr>
    <tr>
        <td align="right">招聘职位：</td>
        <td><a href="${siteUrl}/jobs/${pos.pid}" target="_blank">${pos.posName}
            (<@getPosCount count="${pos.recruitmentNumber}"/>人)</a></td>
    </tr>
    <tr>
        <td align="right">性别要求：</td>
        <td>${pos.basicDataVo.gender}</td>
    </tr>
    <tr>
        <td align="right">工作地区：</td>
        <td>${pos.basicDataVo.workLocation}</td>
    </tr>
    <tr>
        <td align="right">联系人：</td>
        <td>${pos.contactPerson}</td>
    </tr>
    <tr>
        <td align="right">通讯地址：</td>
        <td>${pos.basicDataVo.address}</td>
    </tr>
    <tr>
        <td align="right" valign="top" style="border:0;">岗位要求：</td>
        <td style="display:block; width:500px;border:0;">${pos.description}</td>
    </tr>
</table>
</#list>
<br/>
<p>职位不适合我？<a href="${siteUrl}/per/resume" target="_blank">点此登陆</a>更改求职意向。</p>
<p>温馨提示：</p>
<p style="text-indent:2em;">此邮件为系统自动发送，请不要回复，如有疑问，请登陆<a href="http://www.job5156.com/">智通人才网</a></p>
<p style="text-indent:2em;">或拨打智通人才网的服务热线95105333，即可了解更多的帮助信息</p>

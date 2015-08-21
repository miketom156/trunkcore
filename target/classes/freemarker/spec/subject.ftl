<#--纯文本-->
<#if contentType == 1 && subjectList?size gt 0 >
    <#list subjectList as item>
    ${item.spDesc}
    </#list>
</#if>

<#if category == 1 >
<#--&lt;#&ndash;纯文本&ndash;&gt;
    <#if contentType == 1 && subjectList?size gt 0 >
    <div class="inner">
        <ul class="logos">
            <#list subjectList as item>
                <li><a href="${item.spUrl}" target="_blank">${item.spDesc}</a></li>
            </#list>
        </ul>
    </div>
    </#if>-->

<#--纯图片-->
    <#if contentType == 2 && subjectList?size gt 0 >
    <div class="inner">
        <ul class="logos">
            <#list subjectList as item>
                <li><a href="${item.spUrl}" target="_blank"><img src="${item.spPath}"/></a></li>
            </#list>
        </ul>
    </div>
    </#if>
<#--图片加文本-->
    <#if contentType == 3 && subjectList?size gt 0 >
    <div class="inner">
        <ul class="logos">
            <#list subjectList as item>
                <li><a href="${item.spUrl}" target="_blank"><img src="${item.spPath}"/><br/>${item.spDesc}</a></li>
            </#list>
        </ul>
    </div>
    </#if>

</#if>

<#--热招职位-->
<#if category == 2 && subjectList?size gt 0 >
<div class="inner">
    <#list subjectList as item>
        <h4>${item.spPath}</h4>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;${item.spOther}</p>
    </#list>
    <p></p>
</div>
</#if>


<#--媒体报道-->
<#if category == 3 && subjectList?size gt 0 >
<tbody>
    <#list subjectList as item>
    <tr>
        <td>${item.spPath}</td>
        <th><a href="${item.spUrl}" target="_blank">${item.spOther}</a></th>
        <td>${item.spDesc}</td>
    </tr>
    </#list>
</tbody>
</#if>

<#--企业名+职位-->
<#if category == 4 && subjectList?size gt 0 >
    <#list subjectList as item>
    <li><a href="${item.spUrl}" target="_blank">${item.spPath}</a><span><a href="${item.spUrl}" target="_blank">${item.spOther}</a></span></li>
    </#list>
</#if>

<#--资讯类-->
<#if category == 5 && subjectList?size gt 0 >
    <#list subjectList as item>
    <li><a href="${item.spUrl}" target="_blank">${item.spOther}</a></li>
    </#list>
</#if>

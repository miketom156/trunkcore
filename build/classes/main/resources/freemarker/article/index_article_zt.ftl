<#--智通才富-->
<#assign test=false />
<#-- 描述截取长度 -->
<#macro desc str>
    <#if str?length gt 25>
        ${str[0..25]}
        <#else>
         ${str}>
    </#if>
</#macro>
<div class="t1">
    <#list showMap["首页专题"] as art>
    <p class="pic"><img src="${art.thumbnail}" width="61" height="80"/></p>
    <h3><a href="${art.url}" target="_blank">${art.title}</a></h3>
    <p class="info"><@desc str="${art.desc}" /><a href="${art.url}" target="_blank">[全文]</a></p>
   </#list>
</div>
<div class="t2">
    <ul>
        <#list showMap["首页职场故事"] as art>
        <li><a href="${art.url}" target="_blank">${art.title}</a></li>
        </#list>
        <#list showMap["首页跳槽攻略"] as art>
            <li><a href="${art.url}" target="_blank">${art.title}</a></li>
        </#list>
        <#list showMap["首页职业规划"] as art>
            <li><a href="${art.url}" target="_blank">${art.title}</a></li>
        </#list>
        <#list showMap["首页面试技巧"] as art>
            <li><a href="${art.url}" target="_blank">${art.title}</a></li>
        </#list>
        <#list showMap["首页简历技巧"] as art>
            <li><a href="${art.url}" target="_blank">${art.title}</a></li>
        </#list>
        <#list showMap["首页职场乐翻天"] as art>
            <li><a href="${art.url}" target="_blank">${art.title}</a></li>
        </#list>
    </ul>
</div>
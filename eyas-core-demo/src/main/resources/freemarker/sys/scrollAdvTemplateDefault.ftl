<div class="item">
<#if aUrl??>
    <#if aUrl?length gt 0>
    <a href="http://${aUrl}" target="_blank"  <#if title?exists> title="${title}" </#if>   <#if isNofollow?exists><#if isNofollow == 1 > rel="nofollow" </#if></#if>  >
    <#else>
    <a href="javascript:;"  <#if title?exists> title="${title}" </#if>   style="cursor: default;">
    </#if>
<#else>
    <a href="javascript:;"  <#if title?exists> title="${title}" </#if>   style="cursor: default;">
</#if>
    <img src="http://${imgUrl}"/>
</a>
</div>

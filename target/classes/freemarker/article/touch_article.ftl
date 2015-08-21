<#-- 触屏版资讯 2014 -->
<#-- 描述截取长度 -->
<#macro desc str>
    <#if str?length gt 60>
    ${str[0..60]}
    <#else>
    ${str}
    </#if>
</#macro>
<#if showMap?size gt 0 >
<ul class="article_list">
    <#list showMap["触屏专题新闻"] as art>
    <li class="itemStyle">
        <a href="http://article.job5156.com/touch/plus/view.php?aid=${art.arcId}">
            <dl>
                <dt>${(art.arcShorttitle?length>1)?string(art.arcShorttitle,art.title)}</dt>
                <dd class="img"><img src="${art.thumbnail}"/></dd>
              <#--  <dd>[职场生存]</dd>
                <dd>标签：发展</dd>-->
                <dd><@desc str="${art.desc}" /></dd>
            </dl>
        </a>
    </li>
    </#list>
</ul>
</#if>
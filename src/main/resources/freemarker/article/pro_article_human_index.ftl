<ul class="itemMod expertTeam" id="expertTeam" style="height: 3944px; top: -200px;">
    <#list list as article>
        <li><div class="pic"><a href="/human/cms/article/${article.id }" target="_parent"><img src="${article.pic }"></a></div>
            <p class="tit"><a href="/human/cms/article/${article.id }">${article.title }</a></p>
            <p class="info">
                <a href="/human/cms/article/${article.id }">
                    <#if (article.description?length > 30)>
                       ${article.description[0..29]}...
                       <#else>
                       ${article.description}
                    </#if>
                </a>
            </p>
        </li>
    </#list>
</ul>
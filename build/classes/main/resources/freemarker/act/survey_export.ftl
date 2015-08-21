<meta charset="utf-8" />
<style>
    .content dt{ font-weight:bold; font-family: "microsoft yahei"}
    .title.p{font-weight: bold;font-size: 18px; }
    h3{ font-family: "microsoft yahei"}
</style>

<div class="content">
<#list actSurveyByAnswererVoList as item >
    <div class="title">
        <h3>问卷：${item.surveyName} &nbsp;&nbsp;&nbsp;&nbsp;回复人：${item.answerer}</h3>
    </div>
    <#list item.questionVoList as q >
        <dl>
            <dt>${q.question}</dt>
            <#list q.answerList as answer >
                <#if q.answerFlag?exists &&  q.answerFlag != "">
                    <label>${answer.key}.${answer.name} ${(answer.key == q.answerFlag)?string(' (√)','')}&nbsp;&nbsp;</label>
                <#else>
                    <label><input name="${q.id}" type="${answer.button}" />${answer.key}.${answer.name}</label>
                </#if>
            </#list>
            <br/><#if q.suggestion?exists &&  q.suggestion != ""><br/>${q.suggestion}</#if>
        </dl>
        <br/>
    </#list>
    <#if item_has_next> <hr/></#if>
</#list>
</div>
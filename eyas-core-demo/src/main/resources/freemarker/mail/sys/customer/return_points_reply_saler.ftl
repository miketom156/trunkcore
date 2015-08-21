<meta charset="utf-8" />
<#-- 双陪返点 通知销售顾问 -->
<div align="center">
    <div style="width:598px;margin:0 auto;border:1px solid #ccc;text-align:left;color:#333;font-family:宋体,Arial;font-size:12px;line-height:22px;">
        <div style="border-bottom:1px solid #eee;">
            <a href="${siteUrl}" target="_blank"><img src="${imageUrl}/static/style/mail/perAccount/logo_email.gif" alt="智通人才网" width="598" height="59" border="0" /></a>
        </div>
        <div style="margin:28px 28px 16px 28px;">
            <div style="margin-bottom:10px;"><strong style="font-size:16px;">尊敬的${saler}，您好！</strong></div>
            <div style="margin-bottom:10px;"><strong style="font-size:12px;">基于客户的如下反馈：</strong></div>
            <div style="margin：10px;border-style:solid;border-color:#f2e4bd;border-width: 1px;background-color: #edf2ce">${content}</div>
            <div style="margin-bottom:10px;"><strong style="font-size:12px;">经过我们的评估确认，决定向您负责的客户（${comName}）返还${returnPoints.points}个点数（返还前${returnPoints.comOldPoints}，返还后${returnPoints.comNewPoints}），敬请知悉。</strong></div>
            <p style="text-align:right;">智通人才网（<a href="${siteUrl}" target="_blank">www.job5156.com</a>）<br />${.now?date}</p>
        </div>
    </div>
</div>
<meta charset="utf-8" />
<!--发送通知信=======企业简历管理模块-->
<style>
    a{
        text-decoration: none;
        color:#3377cc;
    }
    a:hover{
        text-decoration: underline;
    }
    p{ margin:0; line-height:25px; }
    table{ font-size:15px; color:#333;}
</style>
<table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
    <!--top begin-->
    <tr>
        <td style="padding:8px"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="273"><a href="${siteUrl}" target="_blank"><img src="${imageUrl}/static/style/mail/perAccount/logo.png" width="273" height="45" alt="智通人才网" border=0 /></a></td>
                <td align="right" style="font-size:12px;line-height:21px;color:#8c8b8b">此邮件来自<a href="http://www.job5156.com/" target="_blank" style="color:#0075c7;text-decoration: underline">智通人才网</a></td>
            </tr>
        </table></td>
    </tr>
    <!--top end-->
    <tr>
        <td background="${imageUrl}/static/style/mail/perAccount/di_2.png" style="border-top: 3px solid #0076c7;padding:17px; font-size:12px;">
            <!--content bengin-->
            <p><strong style="font-size:16px;">尊敬的<span style="color:#ff6600;">${userName}</span>，您好！</strong></p><br/>
            <p style="text-indent:2em;">${content}</p>
            <br/><br/>
            <p align="right">智通人才网（<a href="${siteUrl}" target="_blank">www.job5156.com</a>）<br/>
            ${.now?date}
            </p>
            <!--content end-->
        </td>
    </tr>
    <tr>
        <td height="100" style="font-size:12px; text-indent:2em; padding:5px 17px 5px 17px; color:#888;" bgcolor="#f0f0f0">
        	<p><span style="color: #ff6600;">本邮件为系统自动发送，请不要回复，</span>如有疑问，请发送邮件至<a href="mailto:webmaster@job5156.com" target="_blank">webmaster@job5156.com</a></p>
            <p align="left">为了保证您能正常接收智通人才网的邮件（不被列入垃圾邮件），请参照《<a href="http://hr.job5156.com/winopen/2010/set_mail/chi_set.html" target="_blank">如何正常接收智通人才网的邮件</a>》进行设置。</p>
            <p>如需任何帮助，请拨打我们的服务热线：<span style="color:#ff6600;">95105333</span>或者<a href="http://www.job5156.com/static/53kf/dialog/dialog_1.htm" target="_blank">在线留言</a>，我们将竭诚为您服务。 </p>
        </td>
    </tr>
    <!--footer begin-->
    <tr>
        <td align="center" style="padding:16px;color:#a9a9a9;font-size:12px;font-family:Verdana">Copyright 1999-2014 <a href="http://job5156.com/" target="_blank" style="color:#a9a9a9">job5156.com</a> 智通人才网 版权所有</td>
    </tr>
    <!--footer end-->
</table>
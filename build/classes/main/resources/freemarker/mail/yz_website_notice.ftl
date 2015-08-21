<style>
    a {
        text-decoration: none;
        color: #3377cc;
    }

    a:hover {
        text-decoration: underline;
    }

    p {
        margin: 0;
        line-height: 25px;
    }

    table {
        font-size: 15px;
        color: #333;
        font-size: 12px;
    }

    .content {
        border: 2px solid #dfdfdf
    }

    .content tr {
        height: 30px;
    }

    .content tr td {
        background: #fff;
        padding: 5px;
        border-bottom: 1px dashed #eee;
    }
</style>
<table width="650" border="0" align="center" cellpadding="0" cellspacing="0">
    <!--top begin-->
    <tr>
        <td style="padding:8px">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="273"><a href="${siteUrl}" target="_blank"><img src="${imageUrl}/static/style/mail/perAccount/logo.png"
                                                                                           width="273" height="45"
                                                                                           alt="智通人才网" border=0/></a>
                    </td>
                    <td align="right" style="font-size:12px;line-height:21px;color:#8c8b8b">此邮件来自<a
                            href="${siteUrl}" target="_blank"
                            style="color:#0075c7;text-decoration: underline">智通人才网</a><br/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <!--top end-->
    <tr>
        <td background="${imageUrl}/static/style/mail/perAccount/di_2.png" style="border-top: 3px solid #0076c7;padding:17px; font-size:12px;">
            <p><span style="font-size:16px;">尊敬的<span style="color:#ff6600;">${userName}</span>，您好！</span></p><br/>
            <p>即日起登陆智通人才网永州站点（<a href="http://yz.job5156.com/" target="_blank">yz.job5156.com</a>)，上万个新出炉的高薪岗位随你挑，好工作先到先得，心动不如赶快行动哦！温馨提示：好消息可别忘了分享哦！</p>
            <br/>
        	<br/><br/>
        	<p align="right">智通人才网（<a href="www.job5156.com" target="_blank">www.job5156.com</a>）<br/>
        		${.now?string("yyyy-MM-dd")}
        	</p>
        </td>
    </tr>
    <tr>
  		<td height="100" style="font-size:12px; text-indent:2em; padding:5px 17px 5px 17px; color:#888;" bgcolor="#f0f0f0">
  	  		<p>本邮件为系统自动发送，不接受回复，如有疑问，请发送邮件至<a href="mailto:webmaster@job5156.com" target="_blank">webmaster@job5156.com</a></p>
  	  		<p>如需任何帮助，请拨打我们的服务热线：<span style="color:#ff6600;">95105333</span>或者<a href="#" target="_blank">在线留言</a>，我们将竭诚为您服务。 </p>
    	</td>
  	</tr>
    <!--footer begin-->
    <tr>
        <td align="center" style="padding:16px;color:#a9a9a9;font-size:12px;font-family:Verdana">Copyright 1999-2014 <a
                href="http://job5156.com/" target="_blank" style="color:#a9a9a9">job5156.com</a> 智通人才网 版权所有
        </td>
    </tr>
    <!--footer end-->
</table>
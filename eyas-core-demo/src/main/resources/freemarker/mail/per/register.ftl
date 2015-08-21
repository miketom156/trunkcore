<meta charset="utf-8" />
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
                <a href="${siteUrl}" target="_blank"><img src="${imageUrl}/static/style/mail/perAccount/logo.png" alt="智通人才网" width="273" height="45" border="0" /></a>
                <td align="right" style="font-size:12px;line-height:21px;color:#8c8b8b">此邮件来自<a href="http://www.job5156.com/" target="_blank" style="color:#0075c7;text-decoration: underline">智通人才网</a></td>
            </tr>
        </table></td>
    </tr>
    <!--top end-->
    <tr>
        <td background="${imageUrl}/static/style/mail/perAccount/di_2.png" style="border-top: 3px solid #0076c7;padding:17px; font-size:12px;">
            <!--content bengin-->
            <p><strong style="font-size:16px;">尊敬的<span style="color:#ff6600;"domain.main><#if (username)?exists>${username}<#else>用户</#if></span>
             ，您好！</strong></p><br/>
            <p style="text-indent:2em;">感谢您注册智通人才网，请您点击以下链接对邮箱地址进行验证：</p>
            <p style="text-indent:2em;"><a href="${validLink}" target="_blank">${validLink}</a></p>
            <p style="text-indent:2em;">以上链接在您收到邮件后1小时内有效。</p>
            <p style="text-indent:2em;">如果通过点击以上链接无法访问，请将该网址复制并粘贴至新的浏览器窗口中。</p>
            <p style="text-indent:2em;">如果您有任何疑问或建议，可以通过以下方式联系我们：
                <a href="mailto:webmaster@job5156.com" target="_blank">webmaster@job5156.com</a></p>
            <br/><br/><br/><br/>
            <p style="text-align:right;">智通人才网（<a href="${siteUrl}" target="_blank">www.job5156.com</a>）<br />${.now?date}</p>
            <!--content end-->
        </td>
    </tr>
    <tr>
        <td height="100" style="font-size:12px; padding:5px 17px 5px 17px; color:#888;" bgcolor="#f0f0f0">
            <p><b>关于智通人才网:</b></p>
            <p align="left" style="text-indent:2em;">智通人才网（www.job5156.com）1999年9月始创于中国东莞市，由中国最大的人才服务连锁品牌——广东智通人才连锁集团全资创办和运营，是智通人才连锁旗下支柱产品之一。智通人才网在全国首倡“线上+线下”的互动招聘服务模式，有效保证人才求职和企业招聘的效果。随着公司在全国的连锁运营，智通人才网已在全国二十多个大中型城市开设了分站，积极推进网站的本地化运营。智通人才网始依托资深人力资源服务经验，及时洞察市场供求的微观变化，并开发针对性地功能与信息服务，服务效果始终有保障。</p>
        </td>
    </tr>
    <!--footer begin-->
    <tr>
        <td align="center" style="padding:16px;color:#a9a9a9;font-size:12px;font-family:Verdana">Copyright 1999-2014 <a href="http://job5156.com/" target="_blank" style="color:#a9a9a9">job5156.com</a> 智通人才网 版权所有</td>
    </tr>
    <!--footer end-->
</table>

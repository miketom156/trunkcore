<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>简历预览</title>
        <style type="text/css">
            body{color:#000;font-family:simsun;font-size:10pt;}
            table{table-layout:fixed; word-break:break-strict;}
            table,div,pre{font-size:10pt;line-height:20px;}
            table td{padding-bottom:4;margin:0;font-size:10pt;vertical-align:top;line-height:20px;}
            .infoBlock td,.itemBlock td,.keyWordBar td{padding-left:20px;padding-right:20px;}
            .infoBlock td td,.itemBlock td td{padding-left:0;padding-right:0;}
            a{color:#3377cc;}
            a:hover{color:#F60;}
            p,pre{margin:0;padding:0;}
            pre{
				white-space:pre-wrap; /* css-3 */
				white-space:-moz-pre-wrap; /* Mozilla, since 1999 */
				white-space:-pre-wrap; /* Opera 4-6 */
				white-space:-o-pre-wrap; /* Opera 7 */
				word-wrap:break-word; /* Internet Explorer 5.5+ */
			}
            h2{font-size:14pt;font-family:"Microsoft YaHei";padding:0;margin:0; display:block}
            h2 span,strong{font-size:11pt;}
            h3{height:20px;line-height:20px; padding:0; margin:0;font-size:14pt; font-family:"Microsoft YaHei";}
            .lightFont{font-size:11pt;color:#37c;}
        </style>
        <style type="text/css" media="print">
            #toolResume, #listIds{display:none;}

        </style>
    </head>

    <body>
        <table width="720" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#999999">
          <tr>
            <td>
                ${resumeContentCn}
                ${resumeContentEn}
            </td>
          </tr>
        </table>
    </body>
</html>
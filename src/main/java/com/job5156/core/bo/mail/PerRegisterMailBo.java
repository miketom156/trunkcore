package com.job5156.core.bo.mail;

public class PerRegisterMailBo extends MailBo<PerRegisterMailBo.PlaceHolderVo> {
    public MailServer getServer() {
        return MailServer.MAIL_SERVER_MAIN;
    }

    public String getTemplate() { 
        return "/mail/per/register.ftl";
    }

    public String getFrom() {
        return "no-reply@job5156.com";
    }

    public String getFromName() {
        return "智通人才网";
    }

    public String getSubject() {
        return "感谢您注册智通人才网，请验证邮箱地址";
    }

    public PerRegisterMailBo(PlaceHolderVo vo, String[] to) {
        setTemplateMap(vo);
        setTo(to);
    }

    public static class PlaceHolderVo {
        private String account;
        private String username;
        private String validLink;

        public PlaceHolderVo(String account, String username, String validLink) {
            this.account = account;
            this.username = username;
            this.validLink = validLink;
        }

        public String getAccount() {
            return account;
        }

        public String getUsername() {
            return username;
        }

        public String getValidLink() {
            return validLink;
        }
    }
}

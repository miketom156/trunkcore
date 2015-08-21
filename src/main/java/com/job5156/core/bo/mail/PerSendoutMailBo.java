package com.job5156.core.bo.mail;

import com.job5156.core.bo.form.per.PerSendoutFormBo;


public class PerSendoutMailBo extends MailBo<PerSendoutMailBo.PlaceHolderVo> {

    protected String subject; //邮件的发送主题

	@Override
	public MailServer getServer() { 
		return MailServer.MAIL_SERVER_MAIN;
	}

	@Override
	public String getTemplate() {
		return "/mail/per/sendoutMail.ftl";
	}

	@Override
	public String getFrom() {
		return "no-reply@mx.job5156.com";
	}

	@Override
	public String getFromName() {
		return "智通人才网";
	}

	@Override
	public String getSubject() {
		return subject;
	}

	public PerSendoutMailBo(PlaceHolderVo vo, String[] to, Integer emailType,String subject) {
        setTemplateMap(vo);
        setTo(to);
        setEmailType(emailType);
        this.subject =subject;
    }
	
	public static class PlaceHolderVo {
        private String jobLetter;
        private PerSendoutFormBo perSendoutFormBo;
        private String userName;
        private String resumeContentCn;
        private String resumeContentEn;

        public PlaceHolderVo(String jobLetter, PerSendoutFormBo perSendoutFormVo, String userName, String resumeContentCn, String resumeContentEn) {
            this.jobLetter = jobLetter;
            this.perSendoutFormBo = perSendoutFormVo;
            this.userName = userName;
            this.resumeContentCn = resumeContentCn;
            this.resumeContentEn = resumeContentEn;
        }

        public String getJobLetter() {
            return jobLetter;
        }

        public void setJobLetter(String jobLetter) {
            this.jobLetter = jobLetter;
        }

        public PerSendoutFormBo getPerSendoutFormVo() {
            return perSendoutFormBo;
        }

        public void setPerSendoutFormVo(PerSendoutFormBo perSendoutFormVo) {
            this.perSendoutFormBo = perSendoutFormVo;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getResumeContentCn() {
            return resumeContentCn;
        }

        public void setResumeContentCn(String resumeContentCn) {
            this.resumeContentCn = resumeContentCn;
        }

        public String getResumeContentEn() {
            return resumeContentEn;
        }

        public void setResumeContentEn(String resumeContentEn) {
            this.resumeContentEn = resumeContentEn;
        }
    }
}

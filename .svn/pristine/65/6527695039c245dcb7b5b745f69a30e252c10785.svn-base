package com.job5156.core.bo.mail;


import com.job5156.core.common.MailConstants;

import java.io.File;

public abstract class MailBo<T> {

    public enum MailServer  { 
        MAIL_SERVER_MAIN,
        MAIL_SERVER_EXCHANGE,
        MAIL_SERVER_BUSINESS,
    }

	private MailServer server; //邮件发送的Server
    private String template; //邮件模板路径
    protected String subject; //邮件的发送主题
    private String from; //邮件头的from字段 - 地址
    private String fromName; //邮件头的from字段 - 名字
    private String[] to; //邮件要发送到的目标地址
    private MailAttachVo[] attachList; //邮件附件列表
    private T templateMap; //邮件模板要填充的内容Map
    private Integer emailType = MailConstants.MAIL_TYPE_SIMPLIFIED;  //邮件类型，默认为简体

    abstract public MailServer getServer();
    abstract public String getTemplate();
    abstract public String getFrom();
    abstract public String getFromName(); 
    abstract public String getSubject();

    protected void setSubject(String subject){
        this.subject = subject;
    }

    public String[] getTo() {
        return to;
    }

    public T getTemplateMap(){
        return templateMap;
    }

    protected void setTo(String[] to) {
        this.to = to;
    }

    protected void setTemplateMap(T templateMap) {
        this.templateMap = templateMap;
    }
    
    public MailAttachVo[] getAttachList() {
		return attachList;
	}
	protected void setAttachList(MailAttachVo... attachList) {
		this.attachList = attachList;
	}
	
	public Integer getEmailType() {
		return emailType;
	}
	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}
	
	public void setServer(MailServer server) {
		this.server = server;
	}

	/**
	 * 
	* @ClassName: MailAttachVo 
	* @Description: 邮件附件vo
	* @author xh 
	* @date 2013-9-25 下午6:34:55 
	*
	 */
	public static class MailAttachVo {
		//附件名称
		String attachName;
		//文件对象
		File file;
		
		public MailAttachVo(String attachName, File file) {
			this.attachName = attachName;
			this.file = file;
		}
		
		public String getAttachName() {
			return attachName;
		}
		
		public File getFile() {
			return file;
		}
	}
}

package com.job5156.webapp.model.proj;


/**
 * 新闻实体
 * 
 * @author zhaoman Date: 2015/5/25 下午: 17:56
 */
public class ProjOfficialSiteNews{

	private Integer id;

	/**
	 * 新闻标题
	 */
	private String title;

	/**
	 * 新闻类型名称
	 */
	private String typename;

	/**
	 * 新闻类型id
	 */
	private String typeid;

	/**
	 * 新闻创建时间
	 */
	private String pubdate;

	/**
	 * 新闻内容
	 */
	private String body;

	/**
	 * 浏览量
	 */
	private String click;

	/**
	 * 文章来源
	 */
	private String source;

	/**
	 * 文章来源 url
	 */
	private String sourceurl;

	/**
	 * 缩略图
	 */
	private String litpic;

	/**
	 * 点赞
	 */
	private String goodPost;
	
	/**
	 * 编辑人id
	 */
	private Integer mid;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceurl() {
		return sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

	public String getLitpic() {
		return litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	public String getGoodPost() {
		return goodPost;
	}

	public void setGoodPost(String goodPost) {
		this.goodPost = goodPost;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}


}

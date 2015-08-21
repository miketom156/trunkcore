package com.job5156.webapp.model.com;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class ComContact implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private Integer comId;//企业ID
	@Embedded
	private ComContactBase comContactBase;
	private Integer defaultFlag;//1为默认联系方式
	private Date createDate; //创建日期
	private Date updateDate;// 修改日期
	private Integer delStatus;//-1已删除
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "comId", insertable = false, updatable = false)
	private ComInfo comInfo;                                //关联企业

    public String getAddressStr() {
        String str = "";
        if(getComContactBase() != null && StringUtils.isNotBlank(getComContactBase().
                getAddress()) && !StringUtils.equals("{}",getComContactBase().getAddress())) {
            Map<String,String> map = new Gson().fromJson(getComContactBase().getAddress(), new TypeToken<Map<String,String>>() {}.getType());
            if(StringUtils.isNotBlank(map.get("prov"))){
                str += map.get("prov") + "省";
            }
            if(StringUtils.isNotBlank(map.get("city"))){
            	str += map.get("city");
            	//为兼容前端JS将公共选择添加了“市”，固需要处理旧数据未包含“市”的职位自动添加“市” by 2014-12-09
            	if(map.get("city") != null && map.get("city").indexOf("市") == -1) {
            		str += "市";
            	}
            }
            if(StringUtils.isNotBlank(map.get("dist"))){
                str += map.get("dist");
            }
            if(StringUtils.isNotBlank(map.get("addr"))){
                str += map.get("addr");
            }
        }
        return str;
    }

    public String getInterviewAddressStr() {
        String str = "";
        if(getComContactBase() != null && StringUtils.isNotBlank(getComContactBase().
                getInterviewAddress()) && !StringUtils.equals("{}",getComContactBase().
                getInterviewAddress())) {
            Map<String,String> map = new Gson().fromJson(getComContactBase().
                    getInterviewAddress(), new TypeToken<Map<String,String>>() {}.getType());
            if(StringUtils.isNotBlank(map.get("prov"))){
                str += map.get("prov") + "省";
            }
            if(StringUtils.isNotBlank(map.get("city"))){
            	str += map.get("city");
            	//为兼容前端JS将公共选择添加了“市”，固需要处理旧数据未包含“市”的职位自动添加“市” by 2014-12-09
            	if(map.get("city") != null && map.get("city").indexOf("市") == -1) {
            		str += "市";
            	}
            }
            if(StringUtils.isNotBlank(map.get("dist"))){
                str += map.get("dist");
            }
            if(StringUtils.isNotBlank(map.get("addr"))){
                str += map.get("addr");
            }
        }
        return str;
    }

    public String getEmailStr(){
        if(getComContactBase() != null && StringUtils.isNotBlank(getComContactBase().
                getEmail()) && !StringUtils.equals("[]",getComContactBase().getEmail())) {
            Gson gson = new Gson();
            List<String> emailList = gson.fromJson(getComContactBase().getEmail(),
                    new TypeToken<List<String>>() {}.getType());
            return StringUtils.join(emailList,"  ");
        }
        return "";
    }

    // ============== getter && setter =======================

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public ComContactBase getComContactBase() {
		return comContactBase;
	}
	public void setComContactBase(ComContactBase comContactBase) {
		this.comContactBase = comContactBase;
	}
	public Integer getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public Date getCreateDate() {
		return createDate ;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate ;
	}
	public Date getUpdateDate() {
		return updateDate ;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate ;
	}
	public Integer getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	public ComInfo getComInfo() {
		return comInfo;
	}
	public void setComInfo(ComInfo comInfo) {
		this.comInfo = comInfo;
	}
	
}

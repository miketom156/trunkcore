package com.job5156.webapp.model.irc;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: IrcProd
 * @Description: 招聘会产品实体类
 * @author lqf & xh
 * @date 2013-8-06
 * 
 */

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class IrcProd implements Serializable {
    private static final long   serialVersionUID    = 1L;

    public static final String PROD_TYPE_ITEM = "item";              //单品
    public static final String PROD_TYPE_COMBO = "combo";           //套餐
    public static final String PROD_TYPE_COMBOITEM = "comboitem";  //套餐专属单品

    public static final Integer PROD_STATUS_DRAFT = 1;      //未发布
    public static final Integer PROD_STATUS_PUBLISH = 2;    //发布
    public static final Integer PROD_STATUS_HIDE = 3;       //隐藏
    public static final Integer PROD_STATUS_ARCHIVE = 4;    //归档

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer             prodId;                           // 主键

    private String              prodName;                         // 产品名称
    private String              prodMemo;                         // 商品备注，用于推广促销
    private Integer             privId;                           // 权限ID,定义产品面向用户可订购范围
    private Integer             prodStatus;                       // 产品状态 {未发布: 1; 发布:2; 隐藏:3; 归档:4;}
    private Date                validTime;                        // 生效时间
    private Date                expireTime;                       // 失效时间
    private Date                serveTime;                        // 服务时间
    private String              prodExtend;                       // 产品扩展信息，用以记录并在产品上显示扩展的标志，如 [国庆套餐, 最新优惠]
    private String              prodImage;                        // 产品图片
    private String              prodCate;                         // 产品类别，16位，每2位为分隔，支持8级分类
    private String              prodType;                         // 产品类型 {单品: item; 团购:tuan; 套餐:combo;}
    private BigDecimal          prodPriceMin;                     // 产品最低价格，当有属性配置时自动抓取属性组合设置中最低的价格，否则最低价格和最高价格保持一致
    private BigDecimal          prodPriceMax;                     // 最大价格
    private BigDecimal          prodPriceOriginal;                // 产品原价，当最低价格和最高价格保持一致且此项有设置时，用于对比现价和原价的优惠优势
    private Integer             prodQuantity;                     // 产品总数量
    private Integer             prodTrade;                        // 产品总成交量
    private String              prodArea;                         // 产品所属地区，地区值参照系统城市编码
    private String              prodDesc;                         // 产品描述
    private String              prodAttrs;                        // 产品属性 [{"attrCate":"座位类型","attrName":["标准位","豪华位","贵宾房"]}]
    private String              prodAttrEffects;                  // 产品属性影响 [{"effectCompose":"标准位","effectPrice":"400","effectQuantity":"377","effectTrade":"11"},{"effectCompose":"豪华位","effectPrice":"500","effectQuantity":"45","effectTrade":"0"},{"effectCompose":"贵宾房","effectPrice":"600","effectQuantity":"4","effectTrade":"0"}]
    private String              prodContainItems;                 // 产品包含的对象 JSON
    private String              prodConnectItems;                 // 产品关联的对象 JSON

    private String              prodBuyRule;                      // 商品购买限制 {"area":["1400"]}
    private Integer             prodSysuser;                      // 商品发布后台人员ID
    

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(
            Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(
            String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getProdMemo() {
        return prodMemo;
    }

    public void setProdMemo(
            String prodMemo) {
        this.prodMemo = prodMemo;
    }

    public Integer getPrivId() {
        return privId;
    }

    public void setPrivId(
            Integer privId) {
        this.privId = privId;
    }

    public Integer getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(
            Integer prodStatus) {
        this.prodStatus = prodStatus;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(
            Date validTime) {
        this.validTime = validTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(
            Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getServeTime() {
        return serveTime;
    }

    public void setServeTime(
            Date serveTime) {
        this.serveTime = serveTime;
    }

    public String getProdExtend() {
        return prodExtend;
    }

    public void setProdExtend(
            String prodExtend) {
        this.prodExtend = prodExtend == null ? null : prodExtend.trim();
    }

    public String getProdImage() {
        return "/static/style/v1" + prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage == null ? null : prodImage.trim();
    }

    public String getProdCate() {
        return prodCate;
    }

    public void setProdCate(
            String prodCate) {
        this.prodCate = prodCate == null ? null : prodCate.trim();
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(
            String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    public BigDecimal getProdPriceMin() {
        return prodPriceMin;
    }

    public void setProdPriceMin(
            BigDecimal prodPriceMin) {
        this.prodPriceMin = prodPriceMin;
    }

    public BigDecimal getProdPriceMax() {
        return prodPriceMax;
    }

    public void setProdPriceMax(
            BigDecimal prodPriceMax) {
        this.prodPriceMax = prodPriceMax;
    }

    public BigDecimal getProdPriceOriginal() {
        return prodPriceOriginal;
    }

    public void setProdPriceOriginal(
            BigDecimal prodPriceOriginal) {
        this.prodPriceOriginal = prodPriceOriginal;
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(
            Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public Integer getProdTrade() {
        return prodTrade;
    }

    public void setProdTrade(
            Integer prodTrade) {
        this.prodTrade = prodTrade;
    }

    public String getProdArea() {
        return prodArea;
    }

    public void setProdArea(
            String prodArea) {
        this.prodArea = prodArea;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(
            String prodDesc) {
        this.prodDesc = prodDesc == null ? null : prodDesc.trim();
    }

    public String getProdAttrs() {
        return prodAttrs;
    }

    public void setProdAttrs(
            String prodAttrs) {
        this.prodAttrs = prodAttrs == null ? null : prodAttrs.trim();
    }

    public String getProdAttrEffects() {
        return prodAttrEffects;
    }

    public void setProdAttrEffects(
            String prodAttrEffects) {
        this.prodAttrEffects = prodAttrEffects == null ? null : prodAttrEffects.trim();
    }

    public String getProdContainItems() {
        return prodContainItems;
    }

    public void setProdContainItems(
            String prodContainItems) {
        this.prodContainItems = prodContainItems;
    }

    public String getProdConnectItems() {
        return prodConnectItems;
    }

    public void setProdConnectItems(
            String prodConnectItems) {
        this.prodConnectItems = prodConnectItems;
    }

    public String getProdBuyRule() {
        return prodBuyRule;
    }

    public void setProdBuyRule(
            String prodBuyRule) {
        this.prodBuyRule = prodBuyRule;
    }

    public Integer getProdSysuser() {
        return prodSysuser;
    }

    public void setProdSysuser(
            Integer prodSysuser) {
        this.prodSysuser = prodSysuser;
    }
    

    //TODO:以下的方法暂时用旧版的,下一版本再改进
    //=========================非POJO方法 ====================
    
    public String getProdImage80(){
        String imageUrl = this.getProdImage();
        return imageUrl.substring(0,imageUrl.lastIndexOf("/")+1) + "80x80_" + imageUrl.substring(imageUrl.lastIndexOf("/")+1);
    }

    public String getProdImage100(){
        String imageUrl = this.getProdImage();
        return imageUrl.substring(0,imageUrl.lastIndexOf("/")+1) + "100x100_" + imageUrl.substring(imageUrl.lastIndexOf("/")+1);
    }

    public String getProdImage180(){
        return this.getProdImage();
    }

    public String getProdAreaStr() {
        String areaStr = "";
        for (String[] aArea : areaArray) {
            if(this.getProdArea().indexOf("\""+NumberUtils.toInt(aArea[1])+"\"") != -1) {
                areaStr +=  aArea[0];
                if(areaStr.equals("全国")) {
                    break;
                } else {
                    areaStr = areaStr + " ";
                }
            }
        }
        return areaStr;
    }

    /**
     * 在商品备注的基础上进行的处理
     * 对备注上如果存在中括号[]括起来的文字，将设置成在右上角显示标签的形式，此处返回标签的文字内容
     * @return
     */
    public String getProdMemoTag() {
        if(getProdMemo() != null) {
            Pattern p = Pattern.compile("\\[.*?\\]");
            Matcher m = p.matcher(getProdMemo());
            if(m.find()) {
                return StringUtils.substringBetween(m.group(), "[", "]");
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 在商品备注的基础上进行的处理
     * 对备注上如果存在中括号[]括起来的文字，将过滤掉[]中的文字，剩下的内容以红色字体显示在商品名称的后面（类京东）
     * @return
     */
    public String getProdMemoString(){
        if(getProdMemo() != null) {
            Pattern p = Pattern.compile("\\[.*?\\]");
            Matcher m = p.matcher(getProdMemo());
            if(m.find()) {
                return StringUtils.remove(getProdMemo(),m.group());
            } else {
                return getProdMemo();
            }
        } else {
            return null;
        }
    }

    public String getProdAreaFirstStr() {
        String areaStr = "";
        for (String[] aArea : areaArray) {
            if(this.getProdArea().indexOf("\""+NumberUtils.toInt(aArea[1])+"\"") != -1) {
                if("".equals(areaStr)) {
                    areaStr +=  aArea[0];
                } else {
                    areaStr = areaStr + "等";
                    break;
                }
                if(areaStr.equals("全国")) {
                    break;
                }
            }
        }
        return areaStr;
    }

    public String getProdTypeStr() {
        if (PROD_TYPE_ITEM.equalsIgnoreCase(this.getProdType())) {
            return "单品";
        } else if (PROD_TYPE_COMBOITEM.equalsIgnoreCase(this.getProdType())) {
            return "套餐专属单品";
        } else if (PROD_TYPE_COMBO.equalsIgnoreCase(this.getProdType())) {
            return "套餐";
        } else {
            return "";
        }
    }

    public String getProdStatusStr() {
        if (PROD_STATUS_DRAFT.equals(this.getProdStatus())) {
            return "未发布";
        } else if (PROD_STATUS_PUBLISH.equals(this.getProdStatus())) {
            return "发布";
        } else if (PROD_STATUS_HIDE.equals(this.getProdStatus())) {
            return "下架";
        } else if (PROD_STATUS_ARCHIVE.equals(this.getProdStatus())) {
            return "过期";
        } else {
            return "";
        }
    }

    public String getProdCateStr() {
        String cateStr = "";
        for (String[] aCate : cateArray) {
            if(aCate[1].equalsIgnoreCase(this.getProdCate())) {
                cateStr = aCate[0];
                break;
            }
        }
        return cateStr;
    }

    // 以下常量设置已迁移到IrcProductConstants类
    public static final String[][] areaArray = {
            {"全国", "0"},
            {"东莞莞城", "116"},
            {"东莞长安", "26"},
            {"东莞塘厦", "54"},
            {"郴州", "57"},
            {"佛山", "63"},
            {"江门华侨", "70"},
            {"江门冈州", "71"},
            {"中山", "91"},
            {"重庆", "96"},
            {"武汉", "102"},
            {"长沙", "104"},
            {"南昌", "110"},
            {"宁波", "134"},
            {"南京", "184"},
            {"衡阳", "185"},
            {"荆州", "186"},
            {"西安", "74"},
            {"合肥", "175"},
            {"湘潭", "212"},
            {"咸宁", "2710"},
    };

    public static final String[][] cateArray = {
            {"招聘会",   "0100000000000000"},
            {"招聘报",   "0200000000000000"},
            {"网络招聘", "0300000000000000"},
            {"LED广告", "0400000000000000"},
//            {"其他服务", "0500000000000000"},
//            {"服务预订", "0600000000000000"},
//            {"劳务派遣", "0700000000000000"},
//            {"猎头服务", "0800000000000000"},
            {"组合套餐", "9900000000000000"},
    };

    public static final String[][] presetImageArray = {
            {"单一招聘会", "/irc/images/prod/fair.png", "/irc/images/prod/80x80_fair.png"},
            {"周一招聘会", "/irc/images/prod/fair1.png", "/irc/images/prod/80x80_fair1.png"},
            {"周二招聘会", "/irc/images/prod/fair2.png", "/irc/images/prod/80x80_fair2.png"},
            {"周三招聘会", "/irc/images/prod/fair3.png", "/irc/images/prod/80x80_fair3.png"},
            {"周四招聘会", "/irc/images/prod/fair4.png", "/irc/images/prod/80x80_fair4.png"},
            {"周五招聘会", "/irc/images/prod/fair5.png", "/irc/images/prod/80x80_fair5.png"},
            {"周六招聘会", "/irc/images/prod/fair6.png", "/irc/images/prod/80x80_fair6.png"},
            {"周日招聘会", "/irc/images/prod/fair7.png", "/irc/images/prod/80x80_fair7.png"},
            {"网络招聘", "/irc/images/prod/website.png", "/irc/images/prod/80x80_website.png"},
            {"招聘报", "/irc/images/prod/newspaper.png", "/irc/images/prod/80x80_newspaper.png"},
            {"劳务派遣", "/irc/images/prod/dispatch.png", "/irc/images/prod/80x80_dispatch.png"},
            {"猎头服务", "/irc/images/prod/hunter.png", "/irc/images/prod/80x80_hunter.png"},
            {"优惠套餐", "/irc/images/prod/package.png", "/irc/images/prod/80x80_package.png"},
            {"LED广告", "/irc/images/prod/led.png", "/irc/images/prod/80x80_led.png"},
            {"套餐B01", "/irc/images/prod/b01.png", "/irc/images/prod/80x80_b01.png"},
            {"套餐B02", "/irc/images/prod/b02.png", "/irc/images/prod/80x80_b02.png"},
            {"套餐B03", "/irc/images/prod/b03.png", "/irc/images/prod/80x80_b03.png"},
            {"套餐B04", "/irc/images/prod/b04.png", "/irc/images/prod/80x80_b04.png"},
            {"套餐W01", "/irc/images/prod/w01.png", "/irc/images/prod/80x80_w01.png"},
            {"套餐W02", "/irc/images/prod/w02.png", "/irc/images/prod/80x80_w02.png"},
            {"套餐W03", "/irc/images/prod/w03.png", "/irc/images/prod/80x80_w03.png"},
            {"套餐W04", "/irc/images/prod/w04.png", "/irc/images/prod/80x80_w04.png"},
            {"24周年约", "/irc/images/prod/24week.png", "/irc/images/prod/80x80_24week.png"},
            {"36周年约", "/irc/images/prod/36week.png", "/irc/images/prod/80x80_36week.png"},
            {"48周年约", "/irc/images/prod/48week.png", "/irc/images/prod/80x80_48week.png"},
            {"赢家", "/irc/images/prod/winner.png", "/irc/images/prod/80x80_winner.png"},
    };

    public static final String[][] extendArray = {
            {"WIFI", "WIFI全场覆盖", "/irc/images/prodextend/public/wifi.gif","公共服务"},
            {"PARKING", "免费停车", "/irc/images/prodextend/public/parking.gif","公共服务"},
            {"SNACK", "免费餐饮", "/irc/images/prodextend/public/snack.gif","公共服务"},
            {"POP", "免费海报", "/irc/images/prodextend/public/pop.gif","公共服务"},
            {"SECURITY", "现场安保服务", "/irc/images/prodextend/public/security.gif","公共服务"},
            {"PROCESS", "过程管控", "/irc/images/prodextend/public/process.gif","公共服务"},
            {"FETCH", "整合引才传播", "/irc/images/prodextend/public/fetch.gif","公共服务"},
            {"VALIDATE", "资质审核", "/irc/images/prodextend/public/validate.gif","公共服务"},
            {"ZERO", "零时差响应", "/irc/images/prodextend/public/zero.gif","公共服务"},
            {"6S", "6S服务", "/irc/images/prodextend/public/6S.gif","公共服务"},
            {"1S", "首问责任制", "/irc/images/prodextend/public/1S.gif","公共服务"},

            {"3C", "综合招聘服务系统", "/irc/images/prodextend/management/3C.gif","后台管理服务"},
            {"TEL", "全国统一热线", "/irc/images/prodextend/management/tel.gif","后台管理服务"},
            {"MATCH", "现场招聘智能匹配系统", "/irc/images/prodextend/management/match.gif","后台管理服务"},
            {"QC", "可追溯质量管理体系", "/irc/images/prodextend/management/QC.gif","后台管理服务"},
            {"CCS", "在线定制系统", "/irc/images/prodextend/management/CCS.gif","后台管理服务"},
            {"CRM", "客户关系管理", "/irc/images/prodextend/management/CRM.gif","后台管理服务"},

            {"1TO1", "一对一服务", "/irc/images/prodextend/spec/1to1.gif","特色服务"},
            {"PREENGAGE", "人才预约服务", "/irc/images/prodextend/spec/preengage.gif","特色服务"},
            {"LINE", "线上+线下互动", "/irc/images/prodextend/spec/line.gif","特色服务"},
            {"INFO", "原创资讯服务", "/irc/images/prodextend/spec/info.gif","特色服务"},
            {"MAP", "电子地图导航", "/irc/images/prodextend/spec/map.gif","特色服务"},
            {"MESSAGE", "即时提醒服务", "/irc/images/prodextend/spec/message.gif","特色服务"},
            {"PHONEWEB", "手机上网服务", "/irc/images/prodextend/spec/phoneweb.gif","特色服务"},
            {"SEARCH", "智能搜索服务", "/irc/images/prodextend/spec/search.gif","特色服务"},
    };
}

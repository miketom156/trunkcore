package com.job5156.core.bo;

/**
 * 图片上传的基本设置信息
 */
public class UploadSettingInfoBo {

    private String uploadType; //上传图片的类型, 在Constants中的定义
    private String baseUri; //上传图片显示的主url, 完整URI组合为 baseUri + customPath + 数据库内容 ( 时间戳path + 文件名)
    private String basePath; //上传图片的主物理路径, 完整路径组合为 basePath + customPath + 数据库内容 ( 时间戳path + 文件名)
    private String customPath; //此类型的上传图片自定义的路径, 现在默认为何uploadType一致
    private Integer width; //上传图片的指定宽度, 用于剪裁和缩放, 如果没有剪裁缩放要求的设置为0 (如简历照片有宽高要求, 证书则没有)
    private Integer height; //上传图片的指定高度, 用于剪裁和缩放, 如果没有剪裁缩放要求的设置为0 (如简历照片有宽高要求, 证书则没有)

    public UploadSettingInfoBo(String uploadType, String baseUri, String basePath, String customPath, Integer width, Integer height) {
        this.uploadType = uploadType;
        this.baseUri = baseUri;
        this.basePath = basePath;
        this.customPath = customPath;
        this.width = width;
        this.height = height;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getCustomPath() {
        return customPath;
    }

    public void setCustomPath(String customPath) {
        this.customPath = customPath;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}

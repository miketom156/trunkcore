package com.job5156.core.common.helper;

import com.job5156.core.common.Constants;
import com.job5156.core.common.util.MD5Builder;
import com.job5156.core.bo.UploadSettingInfoBo;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UploadHelper {

    @Value("${base.upload.uri}")
    private String baseUploadUri;

    @Value("${base.upload.path}")
    private String baseUploadPath;

    @Value("${domain.img}")
    private String imagesDomain;
    
    @Resource
    private DomainHelper domainHelper;

    public UploadSettingInfoBo getUploadSettingInfoVoByType(String uploadType) {

        if(Constants.UPLOAD_SIZE.get(uploadType) != null) {
            Integer width = Constants.UPLOAD_SIZE.get(uploadType)[0];
            Integer height = Constants.UPLOAD_SIZE.get(uploadType)[1];

            return new UploadSettingInfoBo(
                    uploadType, baseUploadUri, baseUploadPath,
                    uploadType, width, height);
        }
        return null;
    }

    /**
     * 生成基于时间的 目录名
     * Why? 便于按照时间进行整理, 如果有同步的需求, 可以减少目录的比对
     * @return
     */
    public String genTimeStr() {
        return DateTime.now().getYear() + "-" + DateTime.now().getMonthOfYear();
    }

    /**
     * 生成基于时间和原文件名进行hash的新文件名
     * 以便保证文件的独立性, 减少重名的情况
     * @param fileOriginalName
     * @return
     */
    public String genFileName(String fileOriginalName) {
        String fileName = MD5Builder.md5(System.currentTimeMillis() + fileOriginalName);
        String fileSuffix = StringUtils.substringAfterLast(fileOriginalName, ".");
        if(StringUtils.isBlank(fileSuffix)){
            fileSuffix = "jpg";
        }
        return fileName + "." + fileSuffix;
    }

    /**
     * 获取文件的绝对路径, 注意此处只有路径, 没有filename
     * 如： /opt/tomcat/webapp/ROOT/content/per_photo/2013-01
     * @param vo
     * @param timeStr
     * @return
     */
    public String getFileAbsolutePath(UploadSettingInfoBo vo, String timeStr){
        if(vo != null) {
            return vo.getBasePath() + System.getProperty("file.separator") + getFileRelativePath(vo,timeStr);
        } else {
            return "";
        }
    }

    /**
     * 获取文件的相对路径, 注意此处只有路径, 没有filename
     * 如： per_photo/2013-01
     * @param vo
     * @param timeStr
     * @return
     */
    public String getFileRelativePath(UploadSettingInfoBo vo, String timeStr){
        if(vo != null) {
            return vo.getCustomPath() + System.getProperty("file.separator") + timeStr;
        } else {
            return "";
        }
    }

    /**
     * 获取文件的Url目录, 注意此处只有目录, 没有filename
     * @param vo
     * @param timeStr
     * @return
     */
    public String getUriPath(UploadSettingInfoBo vo, String timeStr){
        if(vo != null && StringUtils.isNotBlank(timeStr)) {
            return vo.getBaseUri() + "/" + vo.getCustomPath() + "/" + timeStr;
        } else {
            return "";
        }
    }

    /**
     * 获取文件的URI路径，如 /content/per_photo/2013-01/123456.jpg
     * @param vo
     * @param timeStr
     * @param filename
     * @return
     */
    public String getUri(UploadSettingInfoBo vo, String timeStr, String filename) {
        if(vo != null && StringUtils.isNotBlank(timeStr) && StringUtils.isNotBlank(filename)) {
            return getUriPath(vo, timeStr) + "/" + filename;
        } else {
            return "";
        }
    }

    public String getUri(String uploadType, String timeStr, String filename) {
        return getUri(getUploadSettingInfoVoByType(uploadType), timeStr, filename);
    }

    /**
     * 获取文件的URL路径，如 http://localhost/content/per_photo/2013-01/123456.jpg
     * @param vo
     * @param timeStr
     * @param filename
     * @return
     */
    public String getUrl(UploadSettingInfoBo vo, String timeStr, String filename) {
        if(vo != null && StringUtils.isNotBlank(timeStr) && StringUtils.isNotBlank(filename)) {
            if(new DateTime(Constants.EYAS_ACTIVE_TIME).isAfter(new DateTime(timeStr))){ //旧数据
                if(StringUtils.equals(Constants.UPLOAD_TYPE_PER_PHOTO, vo.getUploadType())
                        ||StringUtils.equals(Constants.UPLOAD_TYPE_PER_ATTACHMENT, vo.getUploadType())
                        ||StringUtils.equals(Constants.UPLOAD_TYPE_PER_CERTIFICATION, vo.getUploadType())){
                    return Constants.EYAS_IMAGE_URL + "/content/upload/resource/" + timeStr + "/" + filename;
                } else if(StringUtils.equals(Constants.UPLOAD_TYPE_COM_LOGO, vo.getUploadType())
                        ||StringUtils.equals(Constants.UPLOAD_TYPE_COM_EXTRA, vo.getUploadType())){
                    if(new DateTime("2008-5").isAfter(new DateTime(timeStr))) { //企业08年5月前的旧数据是和个人的放一起的
                        return Constants.EYAS_IMAGE_URL+"/content/upload/resource/" + timeStr + "/" + filename;
                    } else {
                        return Constants.EYAS_IMAGE_URL+"/content/upload/company/" + timeStr + "/" + filename;
                    }
                } else if(StringUtils.equals(Constants.UPLOAD_TYPE_SYS_LOGO, vo.getUploadType())){
                	return Constants.EYAS_IMAGE_URL +"/content/upload/logo/" + timeStr + "/" + filename;
                }
            }

            return imagesDomain + getUriPath(vo, timeStr) + "/" + filename;
        } else {
            return "";
        }
    }

    public String getUrl(String uploadType, String timeStr, String filename) {
        return getUrl(getUploadSettingInfoVoByType(uploadType), timeStr, filename);
    }
}

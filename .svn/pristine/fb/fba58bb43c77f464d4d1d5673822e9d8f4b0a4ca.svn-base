package com.job5156.core.common.helper;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class FreeMarkerHelper {

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    public String generateContent(String templateName, Object map) {
        try {
            Template t = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 配置为不缓存的freemark模板
     * @param templateName
     * @return
     */
    public Template getNotCacheTemplate(String templateName){
        
        Configuration cfg= freeMarkerConfigurer.getConfiguration();
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateUpdateDelay(0); //缓存时间为零
        cfg.setNumberFormat("0.##########");
        
        Template temp = null;
        try {
            temp = cfg.getTemplate(templateName);
        } catch (IOException e) {
         return null;
        }
        return temp;
    }
    
    public String getNotCacheContent(String templateName, Object map) {
        try {
            Template t = getNotCacheTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }
}

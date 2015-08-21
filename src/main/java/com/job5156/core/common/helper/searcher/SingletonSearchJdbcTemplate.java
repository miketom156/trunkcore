package com.job5156.core.common.helper.searcher;

import com.google.common.collect.Lists;
import com.job5156.core.common.util.ApplicationContextUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SingletonSearchJdbcTemplate {

    private static SingletonSearchJdbcTemplate instance;

	private List<JdbcTemplate> jdbcTemplateList;
	
	public List<JdbcTemplate> getJdbcTemplateList() {
		return jdbcTemplateList;
	}

	public void setJdbcTemplateList(List<JdbcTemplate> jdbcTemplateList) {
		this.jdbcTemplateList = jdbcTemplateList;
	}

    private SingletonSearchJdbcTemplate() {
    }

    public static SingletonSearchJdbcTemplate getInstance(List<String> searchJdbcs) {
        if(instance == null) {
            synchronized (SingletonSearchJdbcTemplate.class){
                if(instance == null) {
                    instance = new SingletonSearchJdbcTemplate();
                    instance.setJdbcTemplateList(assembleJdbcTemplate(searchJdbcs));
                }
            }
        }
        return instance;
    }

    /**
     * 初始化查询库的JDBCTemplate
     * 这里会根据Spring配置文件中（主要是applicationContext-dao.xml文件中）的定义，对全局参数 jdbcTemplateList 进行初始化
     * 根据 properties 中的配置，初始化可直接调用的 Bean
     * 只有当全局变量为空的时候才会抓取配置中的 Bean，非空（已创建）时跳过
     */
    private static List<JdbcTemplate> assembleJdbcTemplate(List<String> searchJdbcs) {
        List<JdbcTemplate> jdbcTemplateList = Lists.newArrayList();
        for(String searchJdbc : searchJdbcs) {
            JdbcTemplate jdbcTemplate = (JdbcTemplate) ApplicationContextUtil.getApplicationContext().getBean(searchJdbc);
            if(jdbcTemplate != null) {
                jdbcTemplateList.add(jdbcTemplate);
            }
        }
        return jdbcTemplateList;
    }

}

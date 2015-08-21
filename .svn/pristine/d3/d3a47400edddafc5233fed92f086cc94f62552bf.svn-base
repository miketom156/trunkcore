package com.job5156.core.common.helper.frame;

import com.google.common.collect.Maps;
import com.job5156.core.common.util.keyword.analysis.KWSeeker;
import com.job5156.webapp.model.sys.system.SysDepartment;
import com.job5156.webapp.model.sys.system.SysModule;
import com.job5156.webapp.model.sys.system.SysOperate;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * <p>
 * 服务启动后缓存系统信息<br>
 * 1、缓存后台系统菜单<br>
 * </p>
 * 
 * @author leo
 * 
 */
public class ServerInformation {
	/**
	 * 服务器信息实例
	 */
	private static ServerInformation instance;
	/**
	 * 应用上下文
	 */
	private ApplicationContext applicationContext;
	/**
	 * 项目WEB路径
	 */
	private String webPath;
	/**
	 * 项目存储根目录
	 */
	private String rootPath;
	/**
	 * 模块MAP 说明：key-模块ID value-对应的模块对象
	 */
	private Map<String, SysModule> moduleMap = Maps.newLinkedHashMap();
	/**
	 * 模块轨迹MAP 说明：key-模块ID value-从根模块到当前模块的轨迹
	 */
	private Map<String, String> modulePathMap = Maps.newHashMap();
	/**
	 * 部门MAP 说明：key-部门ID value-对应的部门对象
	 */
	private Map<String, SysDepartment> departmentMap = Maps.newHashMap();
	/**
	 * 部门轨迹MAP 说明：key-部门ID value-从顶级部门到当前部门的轨迹
	 */
	private Map<String, String> departmentPathMap = Maps.newHashMap();
	/**
	 * 模块操作MAP
	 */
	private Map<String, SysOperate> operateMap = Maps.newHashMap();
	/**
	 * 过滤关键字查询组件
	 */
	private KWSeeker kwSeeker;

	private ServerInformation() {

	}

	public static ServerInformation getInstance() {
		if (instance == null) {
			instance = new ServerInformation();
		}
		return instance;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public String getWebPath() {
		return webPath;
	}

	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public Map<String, SysModule> getModuleMap() {
		return moduleMap;
	}

	public void setModuleMap(Map<String, SysModule> moduleMap) {
		this.moduleMap = moduleMap;
	}

	public Map<String, String> getModulePathMap() {
		return modulePathMap;
	}

	public void setModulePathMap(Map<String, String> modulePathMap) {
		this.modulePathMap = modulePathMap;
	}

	public Map<String, SysDepartment> getDepartmentMap() {
		return departmentMap;
	}

	public void setDepartmentMap(Map<String, SysDepartment> departmentMap) {
		this.departmentMap = departmentMap;
	}

	public Map<String, String> getDepartmentPathMap() {
		return departmentPathMap;
	}

	public void setDepartmentPathMap(Map<String, String> departmentPathMap) {
		this.departmentPathMap = departmentPathMap;
	}

	public Map<String, SysOperate> getOperateMap() {
		return operateMap;
	}

	public void setOperateMap(Map<String, SysOperate> operateMap) {
		this.operateMap = operateMap;
	}

	public KWSeeker getKwSeeker() {
		return kwSeeker;
	}

	public void setKwSeeker(KWSeeker kwSeeker) {
		this.kwSeeker = kwSeeker;
	}
}

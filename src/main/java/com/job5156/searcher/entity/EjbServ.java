package com.job5156.searcher.entity;

/**
 * <p>
 * EJB服务基类
 * </p>
 * 
 * Date:2015-6-26 下午2:11:22
 * 
 * @author leo
 * @version 1.0
 */
public class EjbServ {

	/**
	 * EJB远程主机IP
	 */
	private String hostIp;
	/**
	 * EJB服务名称
	 */
	private String servName;
	/**
	 * EJB服务状态[0-停止 1-正常]
	 */
	private Integer servStatus;

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getServName() {
		return servName;
	}

	public void setServName(String servName) {
		this.servName = servName;
	}

	public Integer getServStatus() {
		return servStatus;
	}

	public void setServStatus(Integer servStatus) {
		this.servStatus = servStatus;
	}

	/**
	 * <p>服务状态枚举</p>
	 *
	 * Date:2015-6-26 下午2:47:25
	 * @author leo
	 * @version 1.0
	 */
	public enum ServStatus{
		ACTIVE(1, "正常"), STOP(0, "停止");
		
		/**
		 * 服务状态值
		 */
		private int value;
		/**
		 * 服务状态类型
		 */
		private String name;
		
		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		private ServStatus(int value, String name){
			this.value = value;
			this.name = name;
		}
		
		public static String getS(){
			return "";
		}
	}
}

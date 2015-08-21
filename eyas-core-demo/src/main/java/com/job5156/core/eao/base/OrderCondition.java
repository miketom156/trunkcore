package com.job5156.core.eao.base;

/**
 * 
* @ClassName: OrderCondition 
* @Description:  排序条件类
* @author xh 
* @date 2013-8-19 上午10:33:23 
*
 */
public class OrderCondition {
	private Order order;
	private String property;
	
	public OrderCondition(Order order, String property) {
		this.order = order;
		this.property = property;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public enum Order {
		/**
		 * 升序
		 */
		ASC("asc"),
		/**
		 * 降序
		 */
		DESC("desc");
		
		private String orderStr;
		
		private Order(String orderStr) {
			this.orderStr = orderStr;
		}
		
		public String getOrderStr() {
			return orderStr;
		}

		@Override
		public String toString() {
			return orderStr;
		}
	}
}

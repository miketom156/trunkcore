package com.job5156.core.common.helper;

import com.job5156.core.common.option.OptionMap;
import com.job5156.core.eao.base.PropertyFilter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 组装城市搜索条件组件
 * 
 * @author leo
 * 
 */
@Component
public class CityCauseHelper {

	/**
	 * <p>
	 * 组装单个城市编码查询条件，封装成criterion
	 * </p>
	 * 
	 * <pre>
	 * 说明：针对数据库中字段类型为INT的使用此方法
	 * </pre>
	 * 
	 * @param cityCode
	 *            城市编码
	 * @param fieldName
	 *            数据库字段名称
	 * @return
	 */
	public Criterion assembleCityCondition(String cityCode, String fieldName) {
		return assembleCityCondition(NumberUtils.toInt(cityCode, 0), fieldName);
	}

	/**
	 * <p>
	 * 组装多个城市编码查询条件，封装成criterion
	 * </p>
	 * 
	 * <pre>
	 * 说明：针对数据库中字段类型为INT的使用此方法
	 * </pre>
	 * 
	 * @param cityValList
	 *            城市编码列表
	 * @param fieldName
	 *            数据库字段名称
	 * @return
	 */
	public Criterion assembleCityCondition(List<Integer> cityValList, String fieldName) {
		Criterion last = null;
		if (CollectionUtils.isNotEmpty(cityValList)) {
			for (Integer cityCode : cityValList) {
				Criterion criterion = assembleCityCondition(cityCode, fieldName);
				if (criterion != null) {
					if (last == null) {
						last = criterion;
					} else {
						last = Restrictions.or(last, criterion);
					}
				}
			}
		}
		return last;
	}

	/**
	 * <p>
	 * 具体组装单个城市编码查询条件，封装为criterion
	 * </p>
	 * 
	 * <pre>
	 * 说明：针对数据库中字段INT为整型的使用此方法
	 * </pre>
	 * 
	 * @param cityCode
	 *            城市编码
	 * @param fieldName
	 *            数据库字段名称
	 * @return
	 */
	public Criterion assembleCityCondition(Integer cityCode, String fieldName) {
		if (cityCode != null && cityCode > 0 && StringUtils.isNotBlank(fieldName)) {
			cityCode = OptionMap.formatCityCode(cityCode);
			if (cityCode % 100 > 0) {
				return Restrictions.sqlRestriction(fieldName + " = " + cityCode);
			} else if (cityCode / 100 % 100 > 0) {
				return Restrictions.sqlRestriction(fieldName + " LIKE '" + String.valueOf(cityCode / 100) + "%'");
			} else if (cityCode / 10000 % 100 > 0) {
				return Restrictions.sqlRestriction(fieldName + " LIKE '" + String.valueOf(cityCode / 10000) + "%'");
			} else {
				return Restrictions.sqlRestriction(fieldName + " LIKE '" + String.valueOf(cityCode / 1000000) + "%'");
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 组装多个城市编码查询条件，封装成criterion
	 * <p>
	 * 
	 * <pre>
	 * 说明：
	 * 1、针对数据库中字段类型为VARCHAR的使用此方法
	 * 2、数据库中存储格式为多个城市编码用“,”分隔
	 * </pre>
	 * 
	 * @param cityValList
	 *            城市编码列表
	 * @param fieldName
	 *            数据库字段名称
	 * @return
	 */
	public Criterion assembleCityConditionStr(List<Integer> cityValList, String fieldName) {
		Criterion last = null;
		if (CollectionUtils.isNotEmpty(cityValList)) {
			for (Integer cityCode : cityValList) {
				Criterion criterion = assembleCityConditionStr(cityCode, fieldName);
				if (criterion != null) {
					if (last == null) {
						last = criterion;
					} else {
						last = Restrictions.or(last, criterion);
					}
				}
			}
		}
		return last;
	}

	/**
	 * <p>
	 * 具体组装单个城市编码查询条件，封装为criterion
	 * </p>
	 * 
	 * <pre>
	 * 说明：
	 * 1、针对数据库中字段类型为VARCHAR的使用此方法
	 * 2、数据库中存储格式为多个城市编码用“,”分隔
	 * </pre>
	 * 
	 * @param cityCode
	 *            城市编码
	 * @param fieldName
	 *            数据库字段名称
	 * @return
	 */
	public Criterion assembleCityConditionStr(Integer cityCode, String fieldName) {
		if (cityCode != null && cityCode > 0 && StringUtils.isNotBlank(fieldName)) {
			cityCode = OptionMap.formatCityCode(cityCode);
			if (cityCode % 100 > 0) {
				return Restrictions.sqlRestriction("LOCATE('" + cityCode + "'," + fieldName + ") > 0");
			} else if (cityCode / 100 % 100 > 0) {
				return Restrictions.or(
					Restrictions.sqlRestriction(fieldName + " LIKE '" + String.valueOf(cityCode / 100) + "%'"),
					Restrictions.sqlRestriction(fieldName + " LIKE '%," + String.valueOf(cityCode / 100) + "%'")
				);
			} else if (cityCode / 10000 % 100 > 0) {
				return Restrictions.or(
					Restrictions.sqlRestriction(fieldName + " LIKE '" + String.valueOf(cityCode / 10000) + "%'"),
					Restrictions.sqlRestriction(fieldName + " LIKE '%," + String.valueOf(cityCode / 10000) + "%'")
				);
			} else {
				return Restrictions.or(
					Restrictions.sqlRestriction(fieldName + " LIKE '" + String.valueOf(cityCode / 1000000) + "%'"),
					Restrictions.sqlRestriction(fieldName + " LIKE '%," + String.valueOf(cityCode / 1000000) + "%'")
				);
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 组装单个城市编码查询条件，封装成具体的SQL
	 * </p>
	 * 
	 * <pre>
	 * 说明：针对数据库中字段类型为整型的使用此方法
	 * </pre>
	 * 
	 * @param cityCode
	 *            城市编码
	 * @param fieldName
	 *            字段名称
	 * @return
	 */
	public String assembleCityWhere(Integer cityCode, String fieldName) {
		if (cityCode != null && cityCode > 0 && StringUtils.isNotBlank(fieldName)) {
			cityCode = OptionMap.formatCityCode(cityCode);
			if (cityCode % 100 > 0) {
				return " AND " + fieldName + " = " + cityCode;
			} else if (cityCode / 100 % 100 > 0) {
				return " AND " + fieldName + " LIKE '" + (cityCode / 100) + "%'";
			} else if (cityCode / 10000 % 100 > 0) {
				return " AND " + fieldName + " LIKE '" + (cityCode / 10000) + "%'";
			} else {
				return " AND " + fieldName + " LIKE '" + (cityCode / 1000000) + "%'";
			}
		}
		return "";
	}

	/**
	 * <p>
	 * 组装多个城市编码查询条件，封装成具体的SQL
	 * <p>
	 * 
	 * <pre>
	 * 说明：针对数据库中字段类型为整型的使用此方法
	 * </pre>
	 * 
	 * @param cityCodeList
	 *            城市编码列表
	 * @param fieldName
	 *            字段名称
	 * @return
	 */
	public String assembleCityWhere(List<Integer> cityCodeList, String fieldName) {
		StringBuffer buf = new StringBuffer();
		if (CollectionUtils.isNotEmpty(cityCodeList)) {
			for (Integer cityCode : cityCodeList) {
				String cityWhere = assembleCityWhere(cityCode, fieldName);
				if (StringUtils.isNotBlank(cityWhere)) {
					buf.append(StringUtils.substringAfter(cityWhere, "AND"));
					buf.append(" OR ");
				}
			}
			if (buf.length() > 0) {
				buf.delete(buf.lastIndexOf("OR"), buf.length());
				buf.insert(0, " AND(");
				buf.append(")");
			}
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * 组装单个城市编码查询条件，封装成PropertyFilter
	 * </p>
	 * 
	 * <pre>
	 * 说明：针对数据库中字段类型为整型的使用此方法
	 * </pre>
	 * 
	 * @param cityCode
	 *            城市编码
	 * @param fieldName
	 *            实体属性名称
	 * @return
	 */
	public PropertyFilter assembleCityFilter(Integer cityCode, String fieldName) {
		if (cityCode != null && cityCode > 0 && StringUtils.isNotBlank(fieldName)) {
			cityCode = OptionMap.formatCityCode(cityCode);
			if (cityCode % 100 > 0) {
				return new PropertyFilter(fieldName, PropertyFilter.MatchType.EQ, cityCode);
			} else if (cityCode / 100 % 100 > 0) {
				return new PropertyFilter(fieldName, PropertyFilter.MatchType.LIKEIEND, cityCode / 100);
			} else if (cityCode / 10000 % 100 > 0) {
				return new PropertyFilter(fieldName, PropertyFilter.MatchType.LIKEIEND, cityCode / 10000);
			} else {
				return new PropertyFilter(fieldName, PropertyFilter.MatchType.LIKEIEND, cityCode / 1000000);
			}
		}
		return null;
	}

	/**
	 * <p>
	 * 组装多个城市编码查询条件，封装成PropertyFilter
	 * </p>
	 * 
	 * <pre>
	 * 说明：针对数据库中字段类型为整型的使用此方法
	 * </pre>
	 * 
	 * @param cityCodeList
	 *            城市编码列表
	 * @param fieldName
	 *            实体属性名称
	 * @return
	 */
	public PropertyFilter assembleCityFilter(List<Integer> cityCodeList, String fieldName) {
		PropertyFilter baseFilter = null;
		if (CollectionUtils.isNotEmpty(cityCodeList)) {
			for (Integer cityCode : cityCodeList) {
				PropertyFilter filter = assembleCityFilter(cityCode, fieldName);
				if (baseFilter == null) {
					baseFilter = filter;
				} else {
					if (filter != null) {
						baseFilter.addRoundOrFilter(filter);
					}
				}
			}
		}
		return baseFilter;
	}
}

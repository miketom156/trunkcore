package com.job5156.core.eao.base;

import com.job5156.core.common.util.Servlets;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: PropertyFilter 
* @Description: 属性过滤器
* @author xh 
* @date 2013-8-12 上午9:16:43 
*
 */
public class PropertyFilter {
	private String fieldName;
	private String otherField;
	private MatchType matchType;
	private boolean or;
	private boolean and;
	private boolean roundOr;
	private boolean roundAnd;
	private Object[] values;
	private List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
	
	private PropertyFilter(final String filterName, final String... value) {
		String firstPart = StringUtils.substringBefore(filterName, "_");
        String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
        String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());

        Class<?> propertyClass = null;
        
        try {
        	matchType = Enum.valueOf(MatchType.class, matchTypeCode);
        } catch (RuntimeException e) {
                throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
        }

        try {
        	propertyClass = Enum.valueOf(FieldType.class, propertyTypeCode).getValue();
        } catch (RuntimeException e) {
                throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
        }
        
        String propertyName = StringUtils.substringAfter(filterName, "_");

        Assert.isTrue(StringUtils.isNotBlank(propertyName), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
        Assert.notNull(propertyClass, "filter名称" + filterName + "没有按规则编写,无法得到属性类型.");
        
        this.fieldName = propertyName;
        this.values = (Object[])ConvertUtils.convert(value, propertyClass);
        
        //自动处理时间范围查询问题
        if(propertyClass == Date.class) {
        	if(values.length > 1) {
        		Date startDate = (Date)values[0];
        		Date endDate = (Date)values[1];
        		
        		java.util.Date newStartDate = new DateTime(startDate).withHourOfDay(0).
        				withMinuteOfHour(0).withSecondOfMinute(0).toDate();
        		java.util.Date newEndDate = new DateTime(endDate).withHourOfDay(23).
        				withMinuteOfHour(59).withSecondOfMinute(59).toDate();
        		
        		this.values = new java.util.Date[] { newStartDate, newEndDate };
        	}
        }
        
        filters.add(this);
	}

	/**
	 * values为具体类型值的构造函数
	 * 
	 * @param fieldName
	 *            属性名
	 * @param matchType
	 *            匹配类型 {@link MatchType}
	 * @param values
	 *            值数组，MatchType为BETWEEN类型时，长度必须是2，其他为1，值必须是具体类型的值，
	 *            如果是字符串需要转换类型，见另一个构造函数
	 *            {@link #PropertyFilter(String, MatchType, FieldType, Object...)}
	 */
	public PropertyFilter(final String fieldName, MatchType matchType,
			Object... values) {
		this.fieldName = fieldName;
		this.matchType = matchType;
		if (this.matchType == MatchType.BETWEEN
				&& (values == null || values.length != 2)) {
			throw new IllegalArgumentException(String.format(
					"%s属性选择MatchType.BETWEEN类型时，values参数长度必须为2", fieldName));
		}
		this.values = values;
		filters.add(this);
	}

	/**
	 *
	 * values值需要转换类型的构造函数
	 *
	 * @param fieldName
	 *            属性名
	 * @param matchType
	 *            匹配类型 {@link MatchType}
	 * @param fieldType
	 *            属性的类型，value将被转换到此类型
	 * @param values
	 *            值数组,BETWEEN类型时，长度必须是2，其他为1，值必须是具体类型的值， 如果是字符串需要转换类型，见另一个构造函数
	 *            {@link #FieldFilter(String, MatchType, FieldType, Object...)}
	 */
	public PropertyFilter(final String fieldName, MatchType matchType,
			FieldType fieldType, Object... values) {
		this.fieldName = fieldName;
		this.matchType = matchType;
		Assert.notEmpty(values);
		if (this.matchType == MatchType.BETWEEN
				&& (values == null || values.length != 2)) {
			throw new IllegalArgumentException(String.format(
					"%s属性选择MatchType.BETWEEN类型时，values参数长度必须为2", fieldName));
		}
		for (int i = 0; i < values.length; i++) {
			this.values[i] = ConvertUtils.convert(values[i],
					fieldType.getValue());
		}
		filters.add(this);
	}

	/**
	 * 属性比较构造函数
	 *
	 * @param fieldName
	 *            属性名
	 * @param matchType
	 *            条件类型
	 * @param otherField
	 *            其他属性
	 */
	public PropertyFilter(final String fieldName, String otherField,
			MatchType matchType) {
		this.fieldName = fieldName;
		this.matchType = matchType;
		this.otherField = otherField;
		filters.add(this);
	}

	public static List<PropertyFilter> buildFromHttpRequest(
			final HttpServletRequest request) {
		return buildFromHttpRequest(request, "filter");
	}

	public static List<PropertyFilter> buildFromHttpRequest(
			final HttpServletRequest request, final String filterPrefix) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

		// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, Object> filterParamMap = Servlets.getParametersStartingWith(request, filterPrefix + "_");

		// 分析参数Map,构造PropertyFilter列表
		for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String[] values = null;
			if(entry.getValue() instanceof String[]) {
				values = (String[])entry.getValue();
			} else {
				values = new String[] { entry.getValue().toString() };
			}
			// 如果value值为空,则忽略此filter.
			if (values != null && isArrayNotBlank(values)) {
				PropertyFilter filter = new PropertyFilter(filterName, values);
				filterList.add(filter);
			}
		}
		return filterList;
	}

	/**
	 *
	* @Title: isArrayNotBlank
	* @Description: 判断查询条件中的数组值是否不为空
	* @param @param values
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	private static boolean isArrayNotBlank(String[] values) {
		boolean isBlank = true;

		for(String value : values) {
			if(StringUtils.isBlank(value))
				return false;
		}
		return isBlank;
	}

    /**
     * 生成where sql，适合直接执行sql用
     * @return
     */
    public String buildFilterSql() {
        MatchType matchType = getMatchType();
        String fieldName = getFieldName();
        String otherField = getOtherField();
        String filterSql = "";
        if (MatchType.EQ.equals(matchType)) {
            filterSql = fieldName + "=?";
        } else if (MatchType.LE.equals(matchType)) {
            filterSql = fieldName + "<=?";
        } else if (MatchType.LT.equals(matchType)) {
            filterSql = fieldName + "<?";
        } else if (MatchType.GE.equals(matchType)) {
            filterSql = fieldName + ">=?";
        } else if (MatchType.GT.equals(matchType)) {
            filterSql = fieldName + ">?";
        } else if (MatchType.NE.equals(matchType)) {
            filterSql = fieldName + "!=?";
        } else if (MatchType.EQF.equals(matchType)) {
            filterSql = fieldName + "!=" + otherField;
        } else if (MatchType.LEF.equals(matchType)) {
            filterSql = fieldName + "<=" + otherField;
        } else if (MatchType.LTF.equals(matchType)) {
            filterSql = fieldName + "<" + otherField;
        } else if (MatchType.GEF.equals(matchType)) {
            filterSql = fieldName + ">=" + otherField;
        } else if (MatchType.GTF.equals(matchType)) {
            filterSql = fieldName + ">" + otherField;
        } else if (MatchType.NEF.equals(matchType)) {
            filterSql = fieldName + "!=" + otherField;
        } else if (MatchType.LIKE.equals(matchType)) {
            filterSql = fieldName + " LIKE ?";
        } else if (MatchType.LIKESTART.equals(matchType)) {
            filterSql = fieldName + " LIKE ?";
            getValues()[0] = "%" + getValues()[0];
        } else if (MatchType.LIKEEND.equals(matchType)) {
            filterSql = fieldName + " LIKE ?";
            getValues()[0] = getValues()[0] + "%";
        } else if (MatchType.LIKEANYWHERE.equals(matchType)) {
            filterSql = fieldName + " LIKE ?";
            getValues()[0] = "%" + getValues()[0] + "%";
        } else if (MatchType.BETWEEN.equals(matchType)) {
            filterSql = fieldName + " BETWEEN ? AND ?";
        } else if (MatchType.IN.equals(matchType)) {
            filterSql = fieldName + " IN (";
            for (int i = 1; i <= getValues().length; i++) {
                filterSql += (i == getValues().length ? "?)" : "?,");
            }
        }
        return filterSql;
    }

    /**
	 * 获取属性名
	 *
	 * @return
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * 向当前filter添加一个or联合过滤条件
	 *
	 * @param filter
	 * @return
	 */
	public PropertyFilter addOrFilter(PropertyFilter filter) {
		filter.or = true;
		filters.add(filter);
		return this;
	}

	/**
	 * 向当前filter添加一个or联合过滤条件，
	 * <p>
	 * 过滤条件将作为一个整体,即将所有条件放入括号内
	 *
	 * @param filter
	 * @return
	 */
	public PropertyFilter addRoundOrFilter(PropertyFilter filter) {
		//Assert.isTrue(filter == this, "PropertyFilter不允许添加自身");
		filter.roundOr = true;
		filters.add(filter);
		return this;
	}

	/**
	 * 向当前filter添加一个and联合过滤条件，
	 *
	 * @param filter
	 * @return
	 */
	public PropertyFilter addAndFilter(PropertyFilter filter) {
		//Assert.isTrue(filter == this, "PropertyFilter不允许添加自身");
		filter.and = true;
		filters.add(filter);
		return this;
	}

	/**
	 *
	 * 向当前filter添加一个and联合过滤条件，
	 * <p>
	 * 过滤条件将作为一个整体,即将所有条件放入括号内
	 *
	 * @param filter
	 * @return
	 */
	public PropertyFilter addRoundAndFilter(PropertyFilter filter) {
		//Assert.isTrue(filter == this, "PropertyFilter不允许添加自身");
		filter.roundAnd = true;
		filters.add(filter);
		return this;
	}

	/**
	 * 判断该filter是否是一个or联合过滤，见{@link #addOrFilter(PropertyFilter)}
	 *
	 * @return
	 */
	public boolean isOr() {
		return or;
	}

	/**
	 * 判断该filter是否是一个and联合过滤，见{@link #addAndFilter(PropertyFilter)}
	 *
	 * @return
	 */
	public boolean isAnd() {
		return and;
	}

	/**
	 * 判断该filter是否是一个or联合过滤, 见 {@link #addRoundOrFilter(PropertyFilter)}
	 *
	 * @return
	 */
	public boolean isRoundOr() {
		return roundOr;
	}

	/**
	 * 判断该filter是否是一个and联合过滤, 见 {@link #addRoundAndFilter(PropertyFilter)}
	 * 
	 * @return
	 */
	public boolean isRoundAnd() {
		return roundAnd;
	}
	/**
	 * 判断该filter是否是一个联合过滤
	 * @return
	 */
	public boolean isMulti() {
		return !filters.isEmpty();
	}
	/**
	 * 获取属性的比较类型
	 * @return
	 */
	public MatchType getMatchType() {
		return matchType;
	}
	/**
	 * 获取属性比较参数值集合
	 * @return
	 */
	public Object[] getValues() {
		return values;
	}

	/**
	 * 联合filter迭代器
	 * <p>
	 * 不支持删除操作
	 * 
	 * @return
	 */
	public Iterator<PropertyFilter> iterator() {
		return new Iterator<PropertyFilter>() {
			private Iterator<PropertyFilter> it = filters.iterator();

			public boolean hasNext() {
				return it.hasNext();
			}

			public PropertyFilter next() {
				return it.next();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * 联合filter作为一个过滤条件
	 * 
	 * @param filter
	 * @return
	 */
	public PropertyFilter joinFilter(PropertyFilter filter) {
		Assert.isTrue(filter == this, "PropertyFilter不允许添加自身");
		filters.add(filter);
		return this;
	}
	/**
	 * 其他field，两个属性比较时
	 * 
	 * @return
	 */
	public String getOtherField() {
		return otherField;
	}
	/**
	 * 属性类型
	 * @author sun4love
	 *
	 */
	public enum FieldType {
		S(String.class), 
		D(Date.class), 
		I(Integer.class), 
		N(Double.class), 
		L(Long.class), 
		B(Boolean.class);
		
		private Class<?> clazz;

		private FieldType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	/** 属性比较类型. */
	public enum MatchType {
		/**
		 * 等于
		 * */
		EQ,

		/**
		 * 等于另一属性
		 * */
		EQF,

		/**
		 * like 'value'
		 * */
		LIKE,

		/**
		 * like '%value'
		 * */
		LIKESTART,

		/**
		 * like 'value%'
		 * */
		LIKEEND,

		/**
		 * like '%value%'
		 * */
		LIKEANYWHERE,
		
		/**
		 * 针对整型字段like '%value'
		 */
		LIKEISTART,
		
		/**
		 * 针对整型字段like 'value%'
		 */
		LIKEIEND,
		
		/**
		 * 针对整型字段like '%value%'
		 */
		LIKEIANYWHERE,

		/**
		 * 小于
		 * */
		LT,

		/**
		 * 小于另一属性
		 * */
		LTF,

		/**
		 * 大于
		 * */
		GT,

		/**
		 * 大于另一属性
		 * */
		GTF,

		/**
		 * 小于等于
		 * */
		LE,

		/**
		 * 小于等于另一属性
		 * */
		LEF,

		/**
		 * 大于等于
		 */
		GE,

		/**
		 * 大于等于另一属性
		 */
		GEF,

		/**
		 * 
		 * 在两者之间
		 * 
		 */
		BETWEEN,

		/**
		 * 
		 * 不等于
		 * 
		 */
		NE,

		/**
		 * 
		 * 不等于另一属性
		 * 
		 */
		NEF,

		/**
		 * 包含
		 */
		IN ,
        /**
         * 为空
         */
        ISNULL
	}

}

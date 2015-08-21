package com.job5156.core.common.helper.searcher;

import com.job5156.core.common.helper.CityCauseHelper;

import com.job5156.searcher.entity.ComBrief;
import com.job5156.searcher.entity.ExportResumeBrief;
import com.job5156.searcher.entity.PosBrief;
import com.job5156.searcher.entity.ResumeBrief;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class SearchDetailHelper {

	@Resource
	private CityCauseHelper cityCauseHelper;

	private static final int TRY_AGAIN_COUNT = 3;

	/**
	 * 查询库的配置，自动从properties文件中读取，以逗号分隔
	 */
	@Value("#{'${search.jdbcs}'.split(',')}")
	private List<String> searchJdbcs;

	/**
	 * 根据职位ID在查询库中获取职位具体信息
	 * 
	 * @param idList
	 *            要获取具体内容的 id 列表
	 * @return PosBreif List
	 */
	public List<PosBrief> getPosBriefList(List<Integer> idList) {

		List<PosBrief> posBriefList = null;
		if (CollectionUtils.isNotEmpty(idList)) {
			String sql = StringUtils.replace("SELECT * FROM pos_brief pb WHERE pb.id in (:ids)", ":ids", StringUtils.join(idList, ","));
			posBriefList = queryPosBriefList(sql);
		}
		return posBriefList;
	}

	/**
	 * 根据企业ID在查询库中获取职位具体信息
	 * 
	 * @param comIds
	 *            企业ID
	 * @return PosBreif List
	 */
	public List<PosBrief> getPosBriefListByComIds(List<Integer> comIds) {
		List<PosBrief> posBriefList = null;
		if (comIds != null && comIds.size() > 0) {
			String sql = "SELECT id,posName,comId,comName,jobLocPc1,jobLocPc2,jobLocPc3,jsoninfo  FROM pos_brief pb WHERE pb.comId in ( ";
			for (Integer id : comIds) {
				sql += id + ",";
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";
			posBriefList = queryPosBriefList(sql);
		}
		return posBriefList;
	}

	/**
	 * 根据企业ID在查询库中获取职位具体信息
	 * 
	 * @param comId
	 *            企业ID
	 * @return PosBreif List
	 */
	public List<PosBrief> getPosBriefListByComId(Integer comId) {
		List<PosBrief> posBriefList = null;
		if (comId != null && comId > 0) {
			String sql = "SELECT * FROM pos_brief pb WHERE pb.comId = " + comId + " ORDER BY deptId ASC ,id DESC";
			posBriefList = queryPosBriefList(sql);
		}
		return posBriefList;
	}

	public List<PosBrief> getNetPosBriefListByComId(Integer comId) {
		List<PosBrief> posBriefList = null;
		if (comId != null && comId > 0) {
			String sql = "SELECT * FROM pos_brief pb WHERE pb.posType = 1 AND pb.comId = " + comId + " ORDER BY deptId ASC,id DESC LIMIT 300";
			posBriefList = queryPosBriefList(sql);
		}
		return posBriefList;
	}

	public List<PosBrief> getIrcPosBriefListByComId(Integer comId) {
		List<PosBrief> posBriefList = null;
		if (comId != null && comId > 0) {
			String sql = "SELECT * FROM pos_brief pb WHERE pb.posType = 2 AND pb.comId = " + comId + " ORDER BY prodId ASC,id DESC LIMIT 300";
			posBriefList = queryPosBriefList(sql);
		}
		return posBriefList;
	}

	public List<PosBrief> getDepListByComId(Integer comId) {
		List<PosBrief> posBriefList = null;
		if (comId != null && comId > 0) {
			String sql = "SELECT DISTINCT(deptId) FROM pos_brief pb WHERE pb.comId = " + comId;
			posBriefList = queryPosBriefList(sql);
		}
		return posBriefList;
	}

	public List<PosBrief> getPosBriefListByPage(Integer start, Integer limit) {
		String sql = "SELECT * FROM pos_brief where updateDate >= CURDATE() LIMIT " + start + "," + limit;
		return queryPosBriefList(sql);
	}

	public List<PosBrief> getPosBriefListByPage(Integer start, Integer limit, Integer location) {
		StringBuffer sql = new StringBuffer("SELECT * FROM pos_brief where updateDate >= CURDATE() and (");

		String condition1 = cityCauseHelper.assembleCityWhere(location, "jobLocPC1");
		String condition2 = cityCauseHelper.assembleCityWhere(location, "jobLocPC2");
		String condition3 = cityCauseHelper.assembleCityWhere(location, "jobLocPC3");

		sql.append(StringUtils.replace(condition1, "AND", ""));
		sql.append(" or " + StringUtils.replace(condition2, "AND", ""));
		sql.append(" or " + StringUtils.replace(condition3, "AND", ""));
		sql.append(" )");
		sql.append(" LIMIT " + start + "," + limit);

		return queryPosBriefList(sql.toString());
	}

	public List<PosBrief> getPosBriefListByPage(Integer start, Integer limit, Date beginDate, Date endDate) {
		String beginDateStr = new DateTime(beginDate).toString("yyyy-MM-dd HH:mm:ss");
		String endDateStr = new DateTime(endDate).toString("yyyy-MM-dd HH:mm:ss");
		StringBuffer sql = new StringBuffer("SELECT * FROM pos_brief where updateDate between '");
		sql.append(beginDateStr);
		sql.append("' and '");
		sql.append(endDateStr);
		sql.append("'");

		sql.append(" LIMIT " + start + "," + limit);

		return queryPosBriefList(sql.toString());
	}

	public List<PosBrief> getPosBriefListByPage(Integer start, Integer limit, Integer location, Date beginDate,
			Date endDate) {
		String beginDateStr = new DateTime(beginDate).toString("yyyy-MM-dd HH:mm:ss");
		String endDateStr = new DateTime(endDate).toString("yyyy-MM-dd HH:mm:ss");
		StringBuffer sql = new StringBuffer("SELECT * FROM pos_brief where updateDate between '");
		sql.append(beginDateStr);
		sql.append("' and '");
		sql.append(endDateStr);
		sql.append("' and (");

		String condition1 = cityCauseHelper.assembleCityWhere(location, "jobLocPC1");
		String condition2 = cityCauseHelper.assembleCityWhere(location, "jobLocPC2");
		String condition3 = cityCauseHelper.assembleCityWhere(location, "jobLocPC3");

		sql.append(StringUtils.replace(condition1, "AND", ""));
		sql.append(" or " + StringUtils.replace(condition2, "AND", ""));
		sql.append(" or " + StringUtils.replace(condition3, "AND", ""));
		sql.append(" )");
		sql.append(" LIMIT " + start + "," + limit);

		return queryPosBriefList(sql.toString());
	}

	/**
	 * 根据企业ID在查询库中获取校园招聘职位具体信息
	 * 
	 * @param comId
	 *            企业ID
	 * @return PosBreif List
	 */
	public List<PosBrief> getSchoolPosBriefListByComId(Integer comId) {
		List<PosBrief> posBriefList = null;
		if (comId != null && comId > 0) {
			String sql = "SELECT * FROM pos_brief pb WHERE pb.workedYear =0 and pb.comId = " + comId;
			posBriefList = queryPosBriefList(sql);
		}
		return posBriefList;
	}

	/**
	 * 排除指定的企业id列表，在查询库中获取指定地区的最新更新职位具体信息(排除互动招聘的职位)
	 * 
	 * @param comIdList
	 *            企业ID列表
	 * @param comLocationList
	 *            地区列表
	 * @param isSchoolPos
	 *            是否为校园招聘职位
	 * @return PosBreif List
	 */
	public List<PosBrief> getNewPosition(int maxResult, List<String> comLocationList, List<Integer> comIdList,
			boolean isSchoolPos) {
		List<PosBrief> posBriefList = null;

		String sql = StringUtils.replace("select * from pos_brief pb where pb.comId not in (:comIds) and pb.posType!=2", ":comIds", StringUtils.join(comIdList, ","));
		String groupOrderBy = " group by pb.comId order by updateDate desc";
		String limit = " limit 0," + maxResult;

		// 判断是否为校园职位
		if (isSchoolPos) {
			sql += " and workedYear =0 ";
		}

		// 备注：为什么用名称匹配不用地区id匹配，考虑到部分企业的名称并不包含地区名称，固通过企业名称匹配
		// 如果分站点没有设置企业区域代码，则获取所有职位，否则则获取指定区域企业的职位
		if (comLocationList.size() > 0) {
			sql += " and (";
			String locationSql = "";
			for (String location : comLocationList) {
				if (StringUtils.isNotBlank(locationSql))
					locationSql += " or ";
				locationSql += "pb.comName like '%" + location + "%'";
			}
			sql += locationSql + ")";
		}
		sql += groupOrderBy + limit;

		posBriefList = queryPosBriefList(sql);

		return posBriefList;
	}

	/**
	 * 排除指定的企业id列表，在查询库中获取指定地区的最新更新职位具体信息(排除互动招聘的职位)
	 * 
	 * @param comIdList
	 *            企业ID列表
	 * @param comLocation
	 *            地区列表
	 * @param isSchoolPos
	 *            是否为校园招聘职位
	 * @return PosBreif List
	 */
	public List<PosBrief> getNewPosition2014(int maxResult, Integer comLocation, List<Integer> comIdList,
			boolean isSchoolPos) {
		List<PosBrief> posBriefList = null;

		String sql = StringUtils.replace("select * from pos_brief pb where pb.comId not in (:comIds) and pb.posType!=2", ":comIds", StringUtils.join(comIdList, ","));
		String groupOrderBy = " group by pb.comId order by updateDate desc ";
		String limit = " limit 0," + maxResult;

		// 判断是否为校园职位
		if (isSchoolPos) {
			sql += " and workedYear =0 ";
		}

		// 备注：为什么用名称匹配不用地区id匹配，考虑到部分企业的名称并不包含地区名称，固通过企业名称匹配
		// 如果分站点没有设置企业区域代码，则获取所有职位，否则则获取指定区域企业的职位
		if (comLocation != null) {
			sql += "  and ( pb.jobLocPC1=" + comLocation + " or pb.jobLocPC2= " + comLocation + " or pb.jobLocPC3=" + comLocation + ")";
		}
		sql += groupOrderBy + limit;
		posBriefList = queryPosBriefList(sql);
		return posBriefList;
	}

	/**
	 * 根据SQL统计总数
	 * 
	 * @param sql
	 * @return countNum
	 */
	public Integer getCountNumBySql(String sql) {
		Integer countNum = getRandomQueryJdbcTemplates().queryForObject(sql, Integer.class);
		return countNum == null ? 0 : countNum;
	}

	/**
	 * 根据SQL统计总数(含参数)
	 * 
	 * @param sql
	 * @return countNum
	 */
	public Integer getCountNumBySql(String sql, List params) {
		Integer countNum = getRandomQueryJdbcTemplates().queryForObject(sql, params.toArray(), Integer.class);
		return countNum == null ? 0 : countNum;
	}

	/**
	 * 根据企业ID在查询库中获取企业基本信息
	 * 
	 * @param id
	 *            企业ID
	 * @return ComBrief
	 */
	public ComBrief getComBriefById(Integer id) {
		ComBrief comBrief = null;
		if (id != null && id > 0) {
			String sql = StringUtils.replace("SELECT * FROM com_brief cb WHERE cb.id = :id", ":id", ObjectUtils.toString(id));
			comBrief = queryComBrief(sql);
		}
		return comBrief;
	}

	/**
	 * 根据ID在查询库中获取简历的具体信息
	 * 
	 * @param idList
	 *            要获取具体内容的 id 列表
	 * @return ResumeBrief List
	 */
	public List<ResumeBrief> getResumeBriefList(List<Integer> idList) {
		List<ResumeBrief> resumeBriefList = null;
		if (CollectionUtils.isNotEmpty(idList)) {
			String sql = StringUtils.replace("SELECT * FROM resume_brief rb WHERE rb.id in (:ids) ORDER BY FIELD(rb.id, :ids)", ":ids", StringUtils.join(idList, ","));
			resumeBriefList = queryResumeBriefList(sql);
		}
		return resumeBriefList;
	}

	/**
	 * 
	 * @Title: getExportResumeList
	 * @Description: 根据指定的sql从查询库中获取指定数量的简历信息
	 * @param @param sql 查询语句
	 * @param @param maxRows 返回的最大行数
	 * @param @param params 参数数组
	 * @param @return 设定文件
	 * @return List<ExportResumeBrief> 返回类型
	 * @throws
	 */
/*
	public List<ExportResumeBrief> getExportResumeList(String sql, int maxRows, Object[] params) {
		List<ExportResumeBrief> exportResumeBriefList = null;

		if (StringUtils.isNotBlank(sql) && maxRows > 0) {
			exportResumeBriefList = queryExportResumeBriefList(sql, maxRows, params);
		}
		return exportResumeBriefList;
	}
*/

	/**
	 * 
	 * @Description: 根据指定的sql从查询库中获取指定数量的简历信息中的电话号码
	 * @param @param sql 查询语句
	 * @param @param maxRows 返回的最大行数
	 * @param @param params 参数数组
	 * @param @return 设定文件
	 * @return List<ExportResumeBrief> 返回类型
	 * @throws
	 */
	public List<String> getExportResumeMobileList(String sql, int maxRows, Object[] params) {
		List<String> mobiles = null;

		if (StringUtils.isNotBlank(sql) && maxRows > 0) {
			mobiles = queryExportResumeBriefMobileList(sql, maxRows, params);
		}
		return mobiles;
	}

	/**
	 * 根据传入的sql查询 PosBrief 对象列表 增加3次容错机制 当出现数据库执行异常的时候，清空全局的查询库JDBC列表，重新初始化
	 * 当3次都出错的时候，不再重试，直接返回null
	 * 
	 * @param sql
	 * @return
	 */
	private List<PosBrief> queryPosBriefList(String sql) {
		List<PosBrief> posBriefList = null;
		JdbcTemplate jdbcTemplate = null;
		// int tryCount = 0; //尝试次数计数器
		// while(true) {
		// if(tryCount >= TRY_AGAIN_COUNT) {
		// break; //超过尝试次数时直接跳出
		// }

		jdbcTemplate = getRandomQueryJdbcTemplates();

		if (jdbcTemplate == null) {
			// tryCount++; //获取不到查询库的JDBC，重试次数+1
		} else {
			try {
				posBriefList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PosBrief.class));
			} catch (Exception e) {
				System.err.println(e);
				// SingletonSearchJdbcTemplate.clear();
				// //出现数据库访问错误的时候，清空全局JDBC设置，以便重新初始化
				// tryCount++; //增加重试次数计数器
				// continue; //重试
			}
			// break; //成功获取数据后跳出
		}
		// }
		return posBriefList;
	}

	/**
	 * 根据传入的sql查询 ComBrief 对象 增加3次容错机制 当出现数据库执行异常的时候，清空全局的查询库JDBC列表，重新初始化
	 * 当3次都出错的时候，不再重试，直接返回null
	 * 
	 * @param sql
	 * @return
	 */
	private ComBrief queryComBrief(String sql) {
		ComBrief comBrief = null;
		JdbcTemplate jdbcTemplate = null;
		// int tryCount = 0; //尝试次数计数器
		// while(true) {
		// if(tryCount >= TRY_AGAIN_COUNT) {
		// break; //超过尝试次数时直接跳出
		// }

		jdbcTemplate = getRandomQueryJdbcTemplates();

		if (jdbcTemplate == null) {
			// tryCount++; //获取不到查询库的JDBC，重试次数+1
		} else {
			try {
				comBrief = (ComBrief) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(ComBrief.class));
			} catch (Exception e) {
				System.err.println(e);
				// SingletonSearchJdbcTemplate.clear();
				// //出现数据库访问错误的时候，清空全局JDBC设置，以便重新初始化
				// tryCount++; //增加重试次数计数器
				// continue; //重试
			}
			// break; //成功获取数据后跳出
		}
		// }
		return comBrief;
	}

	/**
	 * 根据传入的sql查询 ResumeBrief 对象列表 增加3次容错机制 当出现数据库执行异常的时候，清空全局的查询库JDBC列表，重新初始化
	 * 当3次都出错的时候，不再重试，直接返回null
	 * 
	 * @param sql
	 * @return
	 */
	private List<ResumeBrief> queryResumeBriefList(String sql) {
		List<ResumeBrief> resumeBriefList = null;
		JdbcTemplate jdbcTemplate = null;
		// int tryCount = 0; //尝试次数计数器
		// while(true) {
		// if(tryCount >= TRY_AGAIN_COUNT) {
		// break; //超过尝试次数时直接跳出
		// }

		jdbcTemplate = getRandomQueryJdbcTemplates();

		if (jdbcTemplate == null) {
			// tryCount++; //获取不到查询库的JDBC，重试次数+1
		} else {
			try {
				resumeBriefList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ResumeBrief.class));
			} catch (Exception e) {
				System.err.println(e);
				// SingletonSearchJdbcTemplate.clear();
				// //出现数据库访问错误的时候，清空全局JDBC设置，以便重新初始化
				// tryCount++; //增加重试次数计数器
				// continue; //重试
			}
			// break; //成功获取数据后跳出
		}
		// }
		return resumeBriefList;
	}

	private List<ExportResumeBrief> queryExportResumeBriefList(String sql, int maxRows, Object[] params) {
		List<ExportResumeBrief> exportResumeBriefList = null;
		JdbcTemplate jdbcTemplate = null;
		// int tryCount = 0; //尝试次数计数器
		// while(true) {
		// if(tryCount >= TRY_AGAIN_COUNT) {
		// break; //超过尝试次数时直接跳出
		// }

		jdbcTemplate = getRandomQueryJdbcTemplates();
		jdbcTemplate.setMaxRows(maxRows);// 设置最大结果集数

		if (jdbcTemplate == null) {
			// tryCount++; //获取不到查询库的JDBC，重试次数+1
		} else {
			try {
				// exportResumeBriefList = jdbcTemplate.query(sql, params,
				// ParameterizedBeanPropertyRowMapper.newInstance(ExportResumeBrief.class));

				// 因jdbcTemplate
				// BeanPropertyRowMapper对字段命名规范比较严格，当字段中出现数字（如：job_code1、job_location1等）时将无法自动注入该字段的值，固需要手动注入
				exportResumeBriefList = jdbcTemplate.query(sql, params, new RowMapper<ExportResumeBrief>() {
					public ExportResumeBrief mapRow(ResultSet rs, int rowNum) throws SQLException {
						ExportResumeBrief exportResumeBrief = new ExportResumeBrief();

						exportResumeBrief.setId(rs.getInt("id"));
						exportResumeBrief.setResumeId(rs.getInt("resume_id"));
						exportResumeBrief.setUserName(rs.getString("user_name"));
						exportResumeBrief.setGender(rs.getInt("gender"));
						exportResumeBrief.setBirthday(rs.getDate("birthday"));
						exportResumeBrief.setAge(rs.getInt("age"));
						exportResumeBrief.setDegree(rs.getInt("degree"));
						exportResumeBrief.setMobile(rs.getString("mobile"));
						exportResumeBrief.setEmail(rs.getString("email"));
						exportResumeBrief.setWorkYear(rs.getInt("work_year"));
						exportResumeBrief.setAddress(rs.getString("address"));
						exportResumeBrief.setCreateDate(rs.getDate("create_date"));
						exportResumeBrief.setUpdateDate(rs.getDate("update_date"));
						exportResumeBrief.setFreDate(rs.getDate("fre_date"));
						exportResumeBrief.setPass(rs.getInt("pass"));
						exportResumeBrief.setLocation(rs.getInt("location"));
						exportResumeBrief.setJobLocation1(rs.getInt("job_location1"));
						exportResumeBrief.setJobLocation2(rs.getInt("job_location2"));
						exportResumeBrief.setJobLocation3(rs.getInt("job_location3"));
						exportResumeBrief.setJobCode1(rs.getInt("job_code1"));
						exportResumeBrief.setJobCode2(rs.getInt("job_code2"));
						exportResumeBrief.setJobCode3(rs.getInt("job_code3"));

						return exportResumeBrief;
					}
				});
			} catch (Exception e) {
				System.err.println(e);
				// SingletonSearchJdbcTemplate.clear();
				// //出现数据库访问错误的时候，清空全局JDBC设置，以便重新初始化
				// tryCount++; //增加重试次数计数器
				// continue; //重试
			}
			// break; //成功获取数据后跳出
		}
		// }
		return exportResumeBriefList;
	}

	private List<String> queryExportResumeBriefMobileList(String sql, int maxRows, Object[] params) {
		List<String> exportResumeBriefList = null;
		JdbcTemplate jdbcTemplate = null;

		jdbcTemplate = getRandomQueryJdbcTemplates();
		jdbcTemplate.setMaxRows(maxRows);// 设置最大结果集数

		if (jdbcTemplate == null) {
			// tryCount++; //获取不到查询库的JDBC，重试次数+1
		} else {
			try {
				// exportResumeBriefList = jdbcTemplate.query(sql, params,
				// ParameterizedBeanPropertyRowMapper.newInstance(ExportResumeBrief.class));

				// 因jdbcTemplate
				// BeanPropertyRowMapper对字段命名规范比较严格，当字段中出现数字（如：job_code1、job_location1等）时将无法自动注入该字段的值，固需要手动注入
				exportResumeBriefList = jdbcTemplate.query(sql, params, new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("mobile");
					}
				});
			} catch (Exception e) {
				System.err.println(e);
				// SingletonSearchJdbcTemplate.clear();
				// //出现数据库访问错误的时候，清空全局JDBC设置，以便重新初始化
				// tryCount++; //增加重试次数计数器
				// continue; //重试
			}
			// break; //成功获取数据后跳出
		}
		// }
		return exportResumeBriefList;
	}

	/**
	 * 获取随机的查询库JDBCTemplate 由于配置中支持指定多个 JDBCTemplate
	 * 来调用不同的查询库，所以为了尽可能平均查询库服务器的负载均衡 采用随机的策略来使用查询库对应的 JDBCTemplate
	 * 目前会对全局变量声明的查询库 JDBCTemplate 列表在此功能进行随机排序 然后抽取第一个 JDBC
	 * 
	 * @return
	 */
	private JdbcTemplate getRandomQueryJdbcTemplates() {
		SingletonSearchJdbcTemplate singletonSearchJdbcTemplate = SingletonSearchJdbcTemplate.getInstance(searchJdbcs);
		List<JdbcTemplate> jdbcTemplateList = singletonSearchJdbcTemplate.getJdbcTemplateList();
		Integer jdbcTemplateListSize = CollectionUtils.size(jdbcTemplateList);
		if (CollectionUtils.isNotEmpty(jdbcTemplateList) && jdbcTemplateListSize > 0) {
			if (jdbcTemplateListSize == 1) {
				return jdbcTemplateList.get(0);
			} else if (jdbcTemplateListSize > 1) {
				int r = new Random().nextInt();
				if (r < 0) {
					r = -r;
				}
				return jdbcTemplateList.get(r % jdbcTemplateListSize); // 随机获取搜索数据库，平均搜索数据库的负载
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 以轮询的方式获取查询库连接池
	 * 
	 * @return
	 */
/*	private JdbcTemplate getPollingJdbcTemplate() {
		SingletonSearchJdbcTemplate singletonSearchJdbcTemplate = SingletonSearchJdbcTemplate.getInstance(searchJdbcs);
		List<JdbcTemplate> jdbcTemplateList = singletonSearchJdbcTemplate.getJdbcTemplateList();
		if (CollectionUtils.isNotEmpty(jdbcTemplateList)) {
			synchronized (this) {
				if (this.queryDbPointer > jdbcTemplateList.size()) {
					this.queryDbPointer = 1;
				}
				return jdbcTemplateList.get(this.queryDbPointer++ - 1);
			}
		}
		return null;
	}*/
}

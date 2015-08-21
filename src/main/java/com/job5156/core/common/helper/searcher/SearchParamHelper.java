package com.job5156.core.common.helper.searcher;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.job5156.core.bo.form.searcher.SearchPosFilterFormBo;
import com.job5156.core.bo.form.searcher.SearchPosFormBo;
import com.job5156.core.bo.form.searcher.SearchResumeFilterFormBo;
import com.job5156.core.bo.form.searcher.SearchResumeFormBo;
import com.job5156.core.common.Constants;
import com.job5156.core.common.ResumeEnum;
import com.job5156.core.common.option.OptionMap;
import com.job5156.core.common.util.ChineseCodeChangeUtil;
import com.job5156.core.common.util.IKAnalysisUtil;
import com.job5156.core.common.util.KeyMaskUtil;
import com.job5156.searcher.parameter.PosSearchParam;
import com.job5156.searcher.parameter.ResumeSearchParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Date:2015/6/15 10:20
 *
 * @author hjs
 * @version 1.0
 */
@Component
public class SearchParamHelper {
    public PosSearchParam assemblePosParam(SearchPosFormBo vo, SearchPosFilterFormBo filterVo) {
        PosSearchParam param = initPosParam();
        param = assembleBySearch(param, vo);
        param = assembleByFilter(param, filterVo);
        return param;
    }

    public ResumeSearchParam assembleResumeParam(SearchResumeFormBo vo ,SearchResumeFilterFormBo filterVo) {
        ResumeSearchParam param = initResumeParam();
        param = assembleResumeParamBySearch(param, vo);
        param = assembleResumeParamByFilter(param, filterVo);
        return param;
    }

    private PosSearchParam assembleByFilter(PosSearchParam param, SearchPosFilterFormBo vo) {
        if(vo == null) {
            return param;
        }

        if(vo.getFilterWorkyear() != null) {
            param.setWorkedYearDown(vo.getFilterWorkyear());
            param.setWorkedYearUp(vo.getFilterWorkyear());
            param.setContainNoLimitOfWorkYearIF(false); //既然选择了过滤了，这里默认就将包含不限的条件去掉
        }
        if(vo.getFilterDegree() != null) {
            param.setDegreeDown(vo.getFilterDegree());
            param.setDegreeUp(vo.getFilterDegree());
            param.setContainNoLimitOfDegreeIF(false); //既然选择了过滤了，这里默认就将包含不限的条件去掉
        }
        if(vo.getFilterGender() != null) {
            param.setGender(vo.getFilterGender());
        }
        if(vo.getFilterSalary() != null && vo.getFilterSalary() >= 0) {
            param.setSalaryRangeVal(vo.getFilterSalary());
        }
        if(vo.getFilterIndustry() != null) {
            param.setExpectCalling(Lists.newArrayList(vo.getFilterIndustry()));
        }
        if(vo.getFilterUpdateIn() != null) {
            param.setFreshDate(vo.getFilterUpdateIn());
        }
        if(StringUtils.isNotBlank(vo.getFilterKey())) {
            if(CollectionUtils.isNotEmpty(param.getKeywordList())){
                param.getKeywordList().add(vo.getFilterKey());
            }else{
                param.setKeywordList(Lists.newArrayList(vo.getFilterKey()));
            }

            String keyword = StringUtils.isNotBlank(param.getKeyword()) ? (param.getKeyword() + " " + vo.getFilterKey()) : vo.getFilterKey();
            param.setKeyword(keyword);
        }
        return param;
    }

    public PosSearchParam assembleBySearch(PosSearchParam param, SearchPosFormBo vo) {
        if(vo == null) {
            return param;
        }
        //关键字
        if(StringUtils.isNotBlank(StringUtils.trim(vo.getKeyword()))) {
            String keyword = StringUtils.trim(vo.getKeyword());
            //繁体转简体
            keyword = ChineseCodeChangeUtil.toSimplified(keyword);
            String[] keywordArr = IKAnalysisUtil.splitKeyword(keyword);
            List<String> segKeywordList = Lists.newArrayList();
            List<String> keywordList = Lists.newArrayList();

            for(String kw : keywordArr) {
                if(StringUtils.isNotBlank(kw)){
                    List<String> kwSegList = IKAnalysisUtil.analysisKeywordSeg(kw);
                    if(CollectionUtils.isNotEmpty(kwSegList)){
                        segKeywordList.add(StringUtils.join(kwSegList, ","));
                    }

                    if(checkSingleWordByIkAnalysis(kwSegList)){
                        segKeywordList.add(kw);
                    }
                    keywordList.add(kw);
                }
            }

            param.setKeyword(StringUtils.join(keywordList, " "));
            param.setKeywordList(segKeywordList);

            if(vo.getKeywordOr() != null && vo.getKeywordOr() == 1) {
                param.setKeywordOr(true);
            }
        }
        //关键字类型
        if(vo.getKeywordType() != null) {
            param.setKeywordType(vo.getKeywordType());
        }
        //工作地点
        if(CollectionUtils.isNotEmpty(vo.getLocationList())) {
            Map<Integer, String> locationMap = Maps.newHashMap();
            for(Integer location : vo.getLocationList()) {
                if(location != null && location > 0) {
                    location = OptionMap.formatCityCode(location);
                    if(location / 100 % 100 > 0){
                        locationMap.put(OptionMap.formatCityCode(location / 10000), OptionMap.getTownAddr(location));
                        locationMap.put(location, "");
                    }else{
                        locationMap.put(location, "");
                    }
                }
            }
            param.setExpectWorkPlacePC(locationMap);
        }
        //具体通讯地址
        if(StringUtils.isNotBlank(vo.getLocationAddr())) {
            param.setJobLocPCDistrict(vo.getLocationAddr());
        }
        //职位类别
        if(CollectionUtils.isNotEmpty(vo.getPosTypeList())) {
            param.setExpectWorkJob(vo.getPosTypeList());
        }
        //行业
        if(CollectionUtils.isNotEmpty(vo.getIndustryList())) {
            param.setExpectCalling(vo.getIndustryList());
        }
        //筛选允许接收简历类型
        if(CollectionUtils.isNotEmpty(vo.getAcceptResType())){
            param.setAcceptResTypeList(vo.getAcceptResType());
        }
        //行业名称
        if(StringUtils.isNotBlank(vo.getIndustryName())){
            param.setCallingName(vo.getIndustryName());
        }
        //更新日期
        if(vo.getUpdateIn() != null && vo.getUpdateIn() > 0) {
            param.setFreshDate(vo.getUpdateIn());
        }

        //学历要求（范围+勾选）
        if(vo.getDegreeFrom() != null && vo.getDegreeFrom() > 0) {
            param.setDegreeUp(vo.getDegreeFrom());
        }
        if(vo.getDegreeTo() != null && vo.getDegreeTo() > 0) {
            param.setDegreeDown(vo.getDegreeTo());
        }
        if(vo.getDegreeUnlimit() != null) {
            param.setContainNoLimitOfDegreeIF(vo.getDegreeUnlimit());
        }
        //工作经验要求（范围+勾选）
        if(vo.getWorkyearFrom() != null && vo.getWorkyearFrom() >= -1) {
            param.setWorkedYearUp(vo.getWorkyearFrom());
        }
        if(vo.getWorkyearTo() != null && vo.getWorkyearTo() >= -1) {
            param.setWorkedYearDown(vo.getWorkyearTo());
        }
        if(vo.getWorkyearUnlimit() != null) {
            param.setContainNoLimitOfWorkYearIF(vo.getWorkyearUnlimit());
        }
        //薪资要求
        if(vo.getSalary() != null && vo.getSalary() >= 0) {
            param.setExpectSalary(vo.getSalary());
        }
        if(vo.getSalaryUnPublic() != null){
            param.setContainUnPublicOfSalaryIF(vo.getSalaryUnPublic());
        }
        //性别
        if(vo.getGender() != null) {
            param.setGender(vo.getGender());
        }
        //年龄
        if(vo.getAge() != null) {
            //TODO: 由于年龄的范围判断需要调整，所以此处暂不加入到搜索条件中，直接取搜索的默认值，待搜索器调整后修改
        }
        //淘标签
        if(CollectionUtils.isNotEmpty(vo.getTaoLabelList())) {
            //TODO: 淘标签由于原有搜索器中没有针对淘标签的搜索字段，所以这个暂时只能作为关键词来进行搜索，以后搜索器中增加淘标签后调整
            param.setKeyword(param.getKeyword() + "," + StringUtils.join(vo.getTaoLabelList(),","));
        }
        //工作性质
        if(CollectionUtils.isNotEmpty(vo.getPropertyList())) {
            param.setPropertyList(vo.getPropertyList());
        }

        //分页，处理buffer page问题
        if(vo.getPageNo() == null || vo.getPageNo() == 0) {
            vo.setPageNo(1);
        }
        param.setPageNo(vo.getPageNo() * Constants.SEARCH_BUFFER_PAGE_NUM);
        param.setPageSize(vo.getPageSize() / Constants.SEARCH_BUFFER_PAGE_NUM);

        //招聘会
        if(vo.getProdId() != null && vo.getProdId() > 0) {
            param.setProdId(vo.getProdId());
        }

        //企业
        if(CollectionUtils.isNotEmpty(vo.getComIdList())) {
            param.setComIdList(vo.getComIdList());
        }

        //搜索类型
        if(vo.getSearchType() != null && vo.getSearchType() > 0) {
            param.setSearchType(vo.getSearchType());
        }

        if(vo.getSortBy() != null && vo.getSortBy() > 0) {
            param.setSortBy(vo.getSortBy());
        }

        if(vo.getLat() !=null && vo.getLat() > 0 && vo.getLng() !=null && vo.getLng() > 0 && vo.getMile() != null && vo.getMile() > 0) {
            param.setLat(vo.getLat());
            param.setLng(vo.getLng());
            param.setMiles(vo.getMile());
        }

        //手动排序
        if(vo.getManualSort() != null && vo.getManualSort() > 0){
            param.setManualSort(vo.getManualSort());
        }

        //搜索第一意向岗位
        if(vo.getExactPosType() != null && vo.getExactPosType()){
            param.setExactWorkJob(true);
        }
        //搜索第一意向地区
        if(vo.getExactWorkArea() != null && vo.getExactWorkArea()){
            param.setExactWorkPlace(true);
        }
        return param;
    }

    private PosSearchParam initPosParam() {
        PosSearchParam param = new  PosSearchParam();

        //虽然上面的构造器已经有初始化查询的部分数据了，但这里为了灵活处理，并适应新版的调整，重新将部分值进行默认设置
        //-------默认值设置 begin-------
        param.setSearchType(Constants.SEARCH_TYPE_POS_ALL); //默认搜索类别为全部搜索
        param.setPageNo(Constants.SEARCH_BUFFER_PAGE_NUM); //默认第1页
        param.setPageSize(Constants.SEARCH_PAGE_SIZE / Constants.SEARCH_BUFFER_PAGE_NUM); //默认SEARCH_PAGE_SIZE个
        param.setContainNoLimitOfDegreeIF(true); //包含学历不限
        param.setContainNoLimitOfWorkYearIF(true); //包含工作经验不限
        param.setContainUnPublicOfSalaryIF(true); //包含期望薪资未公开
        param.setKeywordType(0); //关键字匹配范围，默认全文匹配
        param.setFreshDate(90); //默认为简历更新时间3个月内
        param.setSortBy(0); //默认按更新时间排序（倒序）
        param.setGender(0); //默认性别不限
        param.setDegreeUp(1); //默认学历要求最低为【初中】
        param.setDegreeDown(8); //默认学历要求最高为【博士】
        param.setWorkedYearUp(0); //默认工作经验要求最低为【应届生】
        param.setWorkedYearDown(11); //默认工作经验要求最高为【11年及以上】

        /**
         * TODO：
         * 下面两个年龄过滤参数，经分析由于搜索器的逻辑颠倒了，导致搜索结果和想要的有区别
         * 这里需要进行修改，将搜索器的两个参数合并成一个具体的年龄，然后将年龄匹配职位的区间
         * 现在是搜索条件必须大于职位设定的区间才能搜索出来，这样就反了
         * 所以这里只是设定最大条件，且暂时不可以修改，保证求职者起码可以搜索出全部的信息
         */
        param.setAgeUp(16); //默认年龄要求最低为16岁
        param.setAgeDown(60); //默认年龄要求最高为60岁
        //-------默认值设置 end-------

        return param;
    }

    private ResumeSearchParam initResumeParam() {
        ResumeSearchParam param = new  ResumeSearchParam();

        //虽然上面的构造器已经有初始化查询的部分数据了，但这里为了灵活处理，并适应新版的调整，重新将部分值进行默认设置
        //-------默认值设置 begin-------
        param.setSearchType(Constants.SEARCH_TYPE_RESUME_ADVANCE); //默认搜索类别
        param.setPageNo(Constants.SEARCH_BUFFER_PAGE_NUM); //默认第1页
        param.setPageSize(Constants.SEARCH_PAGE_SIZE / Constants.SEARCH_BUFFER_PAGE_NUM); //默认SEARCH_PAGE_SIZE个
        param.setContainNoLimitOfTheProvIF(true); //是否包含工作地区取向为所选省(直辖市) 辖下市（区、县）不限的简历
//        param.setFreshDate(90); //默认为简历更新时间3个月内
        param.setFreshDate(180); //默认为简历更新时间半年内
//        param.setSortBy(0); //默认按相关度排序（倒序）
        param.setSortBy(1); //默认按时间排序（倒序）
        param.setGender(0); //默认性别不限
        param.setDegreeUp(1); //默认学历要求最低为【初中】
        param.setDegreeDown(8); //默认学历要求最高为【博士】
        param.setWorkedYearUp(0); //默认工作经验要求最低为【应届生】
        param.setWorkedYearDown(11); //默认工作经验要求最高为【11年及以上】
        param.setResumeType(ResumeEnum.SearchResumeType.ALL.getValue()); //默认搜索普通简历

        /**
         * 下面两个薪资过滤参数，新版中调整为一个参数，所以这里先默认设定为最大条件
         */
//        param.setExpectSalaryUp(0);
//        param.setExpectSalaryDown(11);

        /**
         * 设定最大条件
         */
        param.setAgeUp(16); //默认年龄要求最低为16岁
        param.setAgeDown(60); //默认年龄要求最高为60岁
        //-------默认值设置 end-------

        return param;
    }

    public ResumeSearchParam assembleResumeParamBySearch(ResumeSearchParam param, SearchResumeFormBo vo) {
        if(vo == null) {
            return param;
        }
        //精确搜索条件
        if(StringUtils.isNotBlank(vo.getPerName()) || NumberUtils.toInt(String.valueOf(vo.getPerUserId()), 0) > 0){
            if(StringUtils.isNotBlank(vo.getPerName())){
                //兼容旧版一个搜索框
                if(NumberUtils.isNumber(vo.getPerName())){
                    param.setUserId(KeyMaskUtil.resumeNoToId(NumberUtils.toLong(vo.getPerName()))); //这里需要从编号转成简历ID
                }else{
                    param.setPerName(vo.getPerName());
                }
            }
            if(NumberUtils.toInt(String.valueOf(vo.getPerUserId()), 0) > 0){
                param.setUserId(KeyMaskUtil.resumeNoToId(vo.getPerUserId())); //这里需要从编号转成简历ID
            }
            param.setSearchType(Constants.SEARCH_TYPE_RESUME_EXACT);
            param.setFreshDate(0); //当精确搜索的时候时间不作限制
        }
        //关键字
        if(StringUtils.isNotBlank(StringUtils.trim(vo.getKeyword()))) {
            String keyword = StringUtils.trim(vo.getKeyword());
            //繁体转简体
            keyword = ChineseCodeChangeUtil.toSimplified(keyword);
            String[] keywordArr = IKAnalysisUtil.splitKeyword(keyword);
            List<String> segKeywordList = Lists.newArrayList();
            List<String> keywordList = Lists.newArrayList();

            for(String kw : keywordArr) {
                if(StringUtils.isNotBlank(kw)){
                    List<String> kwSegList = IKAnalysisUtil.analysisKeywordSeg(kw);
                    if(CollectionUtils.isNotEmpty(kwSegList)){
                        segKeywordList.add(StringUtils.join(kwSegList, ","));
                    }

                    if(checkSingleWordByIkAnalysis(kwSegList)){
                        segKeywordList.add(kw);
                    }
                    keywordList.add(kw);
                }
            }
            param.setKeyword(StringUtils.join(keywordList, " "));
            param.setKeywordList(segKeywordList);

            if(vo.getKeywordOr() != null && vo.getKeywordOr() == 1) {
                param.setKeywordOr(true);
            }
        }

        //希望工作岗位类别
        if(CollectionUtils.isNotEmpty(vo.getPosTypeList())) {
            param.setExpectWorkJob(vo.getPosTypeList());
        }
        //现所在地(省市)
        if(CollectionUtils.isNotEmpty(vo.getLocationList()) ) {
            Map<Integer, String> locationMap = Maps.newHashMap();
            for(Integer location : vo.getLocationList()){
                if(location != null && location > 0){
                    location = OptionMap.formatCityCode(location);
                    if(location / 100 % 100 > 0){
                        locationMap.put(OptionMap.formatCityCode(location / 10000), OptionMap.getTownAddr(location));
                        locationMap.put(location, "");
                    }else{
                        locationMap.put(location, "");
                    }
                }
            }
            param.setCurrentLocPC(locationMap);
        }
        //现所在地详细地址
        if(StringUtils.isNotBlank(vo.getLocationAddr())){
            param.setCurrentLocPCDistrict(vo.getLocationAddr());
        }
        //工作经验要求（范围+勾选）
        if(vo.getWorkyearFrom() != null &&  vo.getWorkyearFrom() >= -1) {
            param.setWorkedYearUp(vo.getWorkyearFrom());
        }
        if(vo.getWorkyearTo() != null && vo.getWorkyearTo() >= -1) {
            param.setWorkedYearDown(vo.getWorkyearTo());
        }

        //学历要求（范围+勾选）
        if(vo.getDegreeFrom() != null && vo.getDegreeFrom() > 0) {
            param.setDegreeUp(vo.getDegreeFrom());
        }
        if(vo.getDegreeTo() != null && vo.getDegreeTo() > 0) {
            param.setDegreeDown(vo.getDegreeTo());
        }
        //期望工作地区(省市)
        if(CollectionUtils.isNotEmpty(vo.getWorkareaList())) {
            List<Integer> workareaList = Lists.newArrayList();
            Map<Integer, String> jobLocMap = Maps.newHashMap();

            for(Integer workarea : vo.getWorkareaList()) {
                if(workarea != null && workarea > 0) {
                    workarea = OptionMap.formatCityCode(workarea);
                    workareaList.add(workarea);

                    if(workarea / 100 % 100 > 0){
                        jobLocMap.put(OptionMap.formatCityCode(workarea / 10000), OptionMap.getTownAddr(workarea));
                        jobLocMap.put(workarea, "");
                    }else{
                        jobLocMap.put(workarea, "");
                    }
                }
            }
            param.setExpectWorkPlacePC(workareaList);
            param.setJobLocPC(jobLocMap);
        }
        //多关键字关联关系组装-用于系统推荐简历
        if(StringUtils.isNotBlank(vo.getKeywordQueryStr())){
            param.setKeywordQuerystr(vo.getKeywordQueryStr());
        }
        //职位关键字-用于系统推荐简历
        if(CollectionUtils.isNotEmpty(vo.getPosKeywordList())){
            param.setPosKeywordList(vo.getPosKeywordList());
        }
        //排除简历ID-用于系统推荐简历
        if(CollectionUtils.isNotEmpty(vo.getExcludeIdList())){
            param.setExcludeResumeIdList(vo.getExcludeIdList());
        }
        //年龄最小
        if(vo.getAgeFrom() != null && vo.getAgeFrom() > 0 ) {
            param.setAgeUp(vo.getAgeFrom());
        }
        //年龄最大
        if(vo.getAgeTo() != null && vo.getAgeTo() > 0 ) {
            param.setAgeDown(vo.getAgeTo());
        }
        //性别
        if(vo.getGender() != null && vo.getGender() >= 0 ) {
            param.setGender(vo.getGender());
        }
        //更新日期
        if(vo.getUpdateIn() != null && vo.getUpdateIn() > 0) {
            if(param.getFreshDate() != 0) {
                param.setFreshDate(vo.getUpdateIn());
            }
        }
        if(vo.getBeforeUpdateIn() != null && vo.getBeforeUpdateIn() ){
            param.setAfterfreshDate(true);
        }

        //薪资要求下线
        if(vo.getSalaryFrom() != null && vo.getSalaryFrom() > 0) {
            param.setExpectSalaryUp(vo.getSalaryFrom());
        }
        //薪资要求上限
        if(vo.getSalaryTo() != null && vo.getSalaryTo() > 0){
            param.setExpectSalaryDown(vo.getSalaryTo());
        }
        //语言要求
        if(CollectionUtils.isNotEmpty(vo.getLanguageLevel())) {
            List<Integer> languageList = Lists.newArrayList();
            for(String languageLevelStr : vo.getLanguageLevel()){
                if(StringUtils.contains(languageLevelStr, "-")){
                    String[] languageArr = StringUtils.split(languageLevelStr, "-");
                    if(ArrayUtils.isNotEmpty(languageArr) && languageArr.length == 2){
                        String foreignLanLevel = languageArr[0] + StringUtils.leftPad(languageArr[1], 4, "0");
                        languageList.add(NumberUtils.toInt(foreignLanLevel, 0));
                    }
                }else{
                    String foreignLanLevel = StringUtils.rightPad(languageLevelStr, 8, "0");
                    languageList.add(NumberUtils.toInt(foreignLanLevel, 0));
                }
            }
            param.setLanguageList(languageList);
        }
        //身高要求
        if(vo.getHeightFrom() != null && vo.getHeightFrom() > 0){
            param.setHightUp(vo.getHeightFrom());
        }
        if(vo.getHeightTo() != null && vo.getHeightTo() > 0){
            param.setHightDown(vo.getHeightTo());
        }
        //最近从事行业
        if(vo.getLatestIndustry() != null && vo.getLatestIndustry() >= -1) {
            param.setLatestCalling(vo.getLatestIndustry());
        }
        //曾就职的单位
        if(StringUtils.isNotBlank(vo.getComName())) {
            param.setComName(vo.getComName());
        }
        //只搜最近工作过的公司名
        if(vo.getComRecent() != null && vo.getComRecent()) {
            param.setOnlySearchLatestComName(true);
        }
        //兼容旧版最近工作的职位
        if(vo.getLatestJob() != null && vo.getLatestJob() > 0){
            String jobName = OptionMap.getValue(OptionMap.OptionType.OPT_POSITION, vo.getLatestJob());
            if(StringUtils.isNotBlank(jobName)){
                param.setJobName(jobName);
                param.setOnlySearchLatestJobName(true);
            }
        }
        //曾就职的职位
        if(StringUtils.isNotBlank(vo.getJobName())){
            param.setJobName(vo.getJobName());
        }
        //只搜最近工作过的职位名
        if(vo.getJobRecent() != null && vo.getJobRecent()){
            param.setOnlySearchLatestJobName(true);
        }
        //学校
        if(StringUtils.isNotBlank(vo.getSchool())) {
            param.setSchool(vo.getSchool());
        }
        //专业
        if(StringUtils.isNotBlank(vo.getSpeciality())) {
            param.setMajor(vo.getSpeciality());
        }
        //户口所在地
        if(CollectionUtils.isNotEmpty(vo.getHometownList())) {
            List<Integer> hometownList = Lists.newArrayList();
            for(Integer hometown : vo.getHometownList()) {
                if(hometown != null && hometown > 0) {
                    hometownList.add(OptionMap.formatCityCode(hometown));
                }
            }
            param.setHomeTownPC(hometownList);
        }
        //是否有照片
        if(vo.getPhotoExist() != null && vo.getPhotoExist()) {
            param.setPhotoIF(true);
        }

        //只搜索公开英文简历的
        if(vo.getResumeEnExist() != null && vo.getResumeEnExist()) {
            param.setEngResumeIF(true);
        }

        //是否限定在哪些简历中进行搜索，主要用于互动招聘的参会人才搜索
        if(CollectionUtils.isNotEmpty(vo.getResumeIdList())){
            param.setResumeIdList(vo.getResumeIdList());
        }

        //分页，处理buffer page问题
        if(vo.getPageNo() == null || vo.getPageNo() == 0) {
            vo.setPageNo(1);
        }
        param.setPageNo(vo.getPageNo() * Constants.SEARCH_BUFFER_PAGE_NUM);
        param.setPageSize(vo.getPageSize() / Constants.SEARCH_BUFFER_PAGE_NUM);

        //搜索类型
        if(vo.getSearchType() != null && vo.getSearchType() > 0) {
            param.setSearchType(vo.getSearchType());
        }

        //简历类型[100-普通简历 101-求职名片 0-所有类型]
        if(vo.getResumeType() != null && vo.getResumeType() >= 0) {
            param.setResumeType(vo.getResumeType());
        }

        if(vo.getSortBy() != null && vo.getSortBy() >= 0) {
            param.setSortBy(vo.getSortBy());
        }

        if(vo.getSortVal() != null && vo.getSortVal() > 0){
            param.setSortVal(vo.getSortVal());
        }

        if(vo.getUsedComId() != null && vo.getUsedComId() > 0){
            param.setComId(vo.getUsedComId());
        }
        //搜索用户第一意向岗位
        if(vo.getExactPosType() != null && vo.getExactPosType()){
            param.setExactWorkJob(true);
        }
        //搜索用户第一意向地区
        if(vo.getExactWorkArea() != null && vo.getExactWorkArea()){
            param.setExactWorkPlace(true);
        }

        return param;
    }

    private ResumeSearchParam assembleResumeParamByFilter(ResumeSearchParam param, SearchResumeFilterFormBo vo) {
        if(vo == null) {
            return param;
        }

        if(vo.getFilterWorkyear() != null) {
            param.setWorkedYearDown(vo.getFilterWorkyear());
            param.setWorkedYearUp(vo.getFilterWorkyear());
        }
        if(vo.getFilterDegree() != null) {
            param.setDegreeDown(vo.getFilterDegree());
            param.setDegreeUp(vo.getFilterDegree());
        }
        if(vo.getFilterGender() != null) {
            param.setGender(vo.getFilterGender());
        }
        if(vo.getFilterSalary() != null) {
            param.setExpectSalaryUp(vo.getFilterSalary());
            param.setExpectSalaryDown(vo.getFilterSalary());
        }
        if(vo.getFilterUpdateIn() != null) {
            param.setFreshDate(vo.getFilterUpdateIn());
        }

        if(StringUtils.isNotBlank(vo.getFilterKey())) {
            if(CollectionUtils.isNotEmpty(param.getKeywordList())){
                param.getKeywordList().add(vo.getFilterKey());
            }else{
                param.setKeywordList(Lists.newArrayList(vo.getFilterKey()));
            }

            String keyword = StringUtils.isNotBlank(param.getKeyword()) ? (param.getKeyword() + " " + vo.getFilterKey()) : vo.getFilterKey();
            param.setKeyword(keyword);
        }

        return param;
    }

    /**
     * 判断分词后的关键词列表是否都是单个字符
     * @param segKeywordList
     * @return
     */
    private boolean checkSingleWordByIkAnalysis(List<String> segKeywordList){
        if(CollectionUtils.isNotEmpty(segKeywordList)){
            for(String segKeyword : segKeywordList){
                String[] segKeywordArr = StringUtils.split(segKeyword, ",");
                if(ArrayUtils.isNotEmpty(segKeywordArr)){
                    for(String keyword : segKeywordArr){
                        if(StringUtils.length(keyword) > 1){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}

package com.job5156.core.common.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.dic.Hit;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * IKAnalyzer分词器工具类
 * 
 * @author zt
 * 
 */
public class IKAnalysisUtil {
	
	/**
	 * <p>
	 * 对关键字进行分词
	 * </p>
	 * 
	 * @param keyword
	 *            关键字
	 * @return
	 */
	public static List<String> analysisKeywordSeg(String keyword) {
		return analysisKeywordSeg(keyword, FilterMode.DEFAULT);
	}
	
	/**
	 * <p>对关键字进行分词</p>
	 * @param keyword
	 * @param modelVal
	 * @return
	 */
	public static List<String> analysisKeywordSeg(String keyword, Integer modelVal){
		return analysisKeywordSeg(keyword, FilterMode.getInstance(modelVal));
	}

	/**
	 * <p>
	 * 对关键字进行分词
	 * </p>
	 * 
	 * @param keyword
	 *            关键字
	 * @param filterMode
	 *            过滤方式
	 * @return
	 */
	public static List<String> analysisKeywordSeg(String keyword, FilterMode filterMode) {
		List<String> keywordList = Lists.newArrayList();
		if (StringUtils.isNotBlank(keyword)) {
			try {
				IKSegmentation ikSeg = new IKSegmentation(new StringReader(keyword), false);
				Lexeme lex = null;
				while((lex = ikSeg.next()) != null){  
					String keywordSeg = lex.getLexemeText();
					if (filterKeywordSeg(filterMode, keywordSeg) && !keywordList.contains(keywordSeg)) {
						keywordList.add(keywordSeg);
					}
				}
				Collections.sort(keywordList, new Comparator<String>(){
					@Override
					public int compare(String o1, String o2) {
						return StringUtils.length(o2) - StringUtils.length(o1);
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return keywordList;
	}
	
	/**
	 * 对多个关键字进行分割
	 * @param keyword
	 * @return
	 */
	public static String[] splitKeyword(String keyword){
		return StringUtils.split(keyword, "[,;: 、；，：]");
	}

	/**
	 * <p>
	 * 过滤关键字词条
	 * </p>
	 * 
	 * @param filterMode
	 *            过滤方式
	 */
	private static boolean filterKeywordSeg(FilterMode filterMode, String keyword) {
		switch (filterMode) {
			case EXIST_IN_MAIN_DICT:
				return checkExistInMainDict(keyword);
			default:
				return true;
		}
	}

	/**
	 * <p>
	 * 判断关键字是否存在主词库中，关键字长度必须大于等于2
	 * </p>
	 * 
	 * @param keyword
	 * @return
	 */
	private static boolean checkExistInMainDict(String keyword) {
		if (StringUtils.isNotBlank(keyword) && keyword.length() >= 2) {
			Hit hit = Dictionary.matchInMainDict(keyword.toCharArray());
			return hit.isMatch() || StringUtils.isAlpha(keyword);
		}
		return false;
	}

	/**
	 * <p>
	 * 关键字过滤方式<br>
	 * DEFAULT:默认不过滤<br>
	 * EXIST_IN_MAIN_DICT:过滤词条必须存在主词库中<br>
	 * </p>
	 * 
	 * @author leo
	 * 
	 */
	public enum FilterMode {
		DEFAULT(0), EXIST_IN_MAIN_DICT(1);
		
		private Integer value;
		
		public Integer getValue() {
			return value;
		}
		
		private FilterMode(Integer value){
			this.value = value;
		}

		public static FilterMode getInstance(Integer value){
			if(value != null){
				for(FilterMode mode : FilterMode.values()) {
					if(mode.getValue().equals(value))
						return mode;
				}
			}
			return FilterMode.DEFAULT;
		}
	}
	
	public static void main(String[] args) throws IOException{
		List<String> list = analysisKeywordSeg("电信网络工程师", FilterMode.EXIST_IN_MAIN_DICT);
		for(String keyword : list){
			System.out.println(keyword);
		}
	}

}

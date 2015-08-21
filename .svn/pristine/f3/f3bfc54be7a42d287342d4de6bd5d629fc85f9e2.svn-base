package com.job5156.core.common.util.keyword;

import com.google.common.collect.Lists;
import com.job5156.core.common.Constants;
import com.job5156.core.common.util.keyword.analysis.KWSeeker;
import com.job5156.core.common.helper.frame.ServerInformation;

import java.util.List;
import java.util.Set;

/**
 * 关键字过滤工具类
 * 
 * @author leo
 * 
 */
public class KeywordFilterUtil {

	/**
	 * 查询内容中存在的过滤关键字
	 * 
	 * @param content
	 * @return
	 */
	public static Set<String> getFilterWord(String content) {
		KWSeeker kwSeeker = ServerInformation.getInstance().getKwSeeker();
		if (kwSeeker == null) {
			List<String> fileNameList = Lists.newArrayList(Constants.FILTER_KEYWORD_FILENAME);
			kwSeeker = KWSeeker.getInstanceWithFileList(fileNameList);
		}
		return kwSeeker.findWords(content);
	}

}

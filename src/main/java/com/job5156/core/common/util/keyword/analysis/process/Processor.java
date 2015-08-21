package com.job5156.core.common.util.keyword.analysis.process;


import com.job5156.core.common.util.keyword.analysis.fragment.AbstractFragment;

import java.util.Map;

public interface Processor {
	/**
	 * 处理操作
	 * 
	 * @param wordsTree
	 *            词表树
	 * @param text
	 *            目标文本
	 * @param fragment
	 *            每个命中的词处理器
	 * @param minLen
	 *            词树中最短的词的长度
	 * @return 返回处理结果
	 */
	@SuppressWarnings("rawtypes")
	public Object process(Map<String, Map> wordsTree, String text, AbstractFragment fragment, int minLen);
}

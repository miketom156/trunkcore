package com.job5156.core.common.pagination;

import java.util.List;
import java.util.Map;

/**
 * 动态分页实现.每次查询返回一页记录的情况下使用.
 *
 * @version 1.0, 2010-4-22
 */
public class QuickPageContext<E> implements IPageContext<E>{
    private List<E> items;
    private int totalCount;//总记录数
    private int pageSize;  //每页显示记录数
    private Map<Object,Object> colSumMap; //各字段求和
    
    /**
     * 
     * @param totalCount
     * @param pageSize
     * @param items
     */
    public QuickPageContext(int totalCount, int pageSize, List<E> items) {
        this(totalCount,pageSize,null,items);
    }

    /**
     *
     * @param totalCount
     * @param pageSize
     * @param items
     * @param colSumMap
     */
    public QuickPageContext(int totalCount, int pageSize, Map<Object,Object> colSumMap,List<E> items) {
        this.totalCount = totalCount;
        this.pageSize = pageSize == 0 ? 10 : pageSize;
        if (items != null) {
            this.items = items;
        }
        this.colSumMap = colSumMap;
    }
    
    public Page<E> getPage(int index) {
        Page<E> page = new Page<E>();
        page.setContext(this);
        int index2 = index > getPageCount() ? 1 : index;
        page.setHasNext(index2 < getPageCount());
        page.setHasPre(index2 > 1);
        page.setIndex(index2);
        page.setItems(items);
        return page;
    }

    /**
     * 计算总页数.
     * 
     * @return
     */
    public int getPageCount() {
        int div = totalCount / pageSize;
        int result = (totalCount % pageSize == 0) ? div : div + 1;
        
        return result;
    }
    
    public int getTotal() {
        return this.totalCount;
    }
    
    public int getPageSize() {
        return this.pageSize;
    }

    public Map<Object, Object> getColSumMap() { return colSumMap; }
}

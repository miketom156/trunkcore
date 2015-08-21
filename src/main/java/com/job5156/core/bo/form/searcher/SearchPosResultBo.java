package com.job5156.core.bo.form.searcher;

import com.job5156.searcher.entity.PosBrief;
import com.job5156.searcher.entity.PosSearchFilter;
import com.job5156.searcher.entity.SearchResult;

import java.util.List;

/**
 * <p></p>
 * Date:2015/6/13 10:20
 *
 * @author hjs
 * @version 1.0
 */
public class SearchPosResultBo {
    private SearchResult searchResult;
    private List<PosBrief> list;
    private PosSearchFilter filter;
    private List<PosBrief> rankPosList;

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public List<PosBrief> getList() {
        return list;
    }

    public void setList(List<PosBrief> list) {
        this.list = list;
    }

    public PosSearchFilter getFilter() {
        return filter;
    }

    public void setFilter(PosSearchFilter filter) {
        this.filter = filter;
    }

    public List<PosBrief> getRankPosList() {
        return rankPosList;
    }

    public void setRankPosList(List<PosBrief> rankPosList) {
        this.rankPosList = rankPosList;
    }

}

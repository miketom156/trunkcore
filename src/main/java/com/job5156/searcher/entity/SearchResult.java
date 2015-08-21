package com.job5156.searcher.entity;


import java.util.List;
import java.util.Map;

public class SearchResult {
    private Integer total; //搜索返回的结果总数
    private Integer realTotal; //搜索返回的真实结果总数
    private List<Integer> ids;  //搜索返回的结果列表
    private Map<String, ChoiceType> choices; //分类统计信息

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRealTotal() {
        return realTotal;
    }

    public void setRealTotal(Integer realTotal) {
        this.realTotal = realTotal;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Map<String, ChoiceType> getChoices() {
        return choices;
    }

    public void setChoices(Map<String, ChoiceType> choices) {
        this.choices = choices;
    }

    public class ChoiceType {
        private List<Choice> choicelist;

        public List<Choice> getChoicelist() {
            return choicelist;
        }

        public void setChoicelist(List<Choice> choicelist) {
            this.choicelist = choicelist;
        }
    }

    public class Choice {
        private String val;
        private Integer realHits;
        private Integer hits;

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public Integer getRealHits() {
            return realHits;
        }

        public void setRealHits(Integer realHits) {
            this.realHits = realHits;
        }

        public Integer getHits() {
            return hits;
        }

        public void setHits(Integer hits) {
            this.hits = hits;
        }
    }
}

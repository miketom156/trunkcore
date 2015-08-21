package com.job5156.searcher.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job5156.webapp.model.com.ComInfo;

import javax.persistence.Id;
import java.util.Map;

public class ComBrief {

    @Id
    private Integer id;
    private String jsoninfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJsoninfo() {
        return jsoninfo;
    }

    public void setJsoninfo(String jsoninfo) {
        this.jsoninfo = jsoninfo;
    }

    //----------非POJO

    public ComInfo getComInfo(){
        Gson gson = new Gson();
        Map<String, Object> jsonMap = gson.fromJson(getJsoninfo(), new TypeToken<Map<String, Object>>() {}.getType());
        return gson.fromJson(gson.toJson(jsonMap.get("company")), ComInfo.class);
    }

}

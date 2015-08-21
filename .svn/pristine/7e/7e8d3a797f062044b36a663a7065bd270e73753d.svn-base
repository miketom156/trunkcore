package com.job5156.core.entity.irc;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class IrcPeranaly implements Serializable {
    private static final long   serialVersionUID    = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer panaId;
    private Integer prodId;
    private String intent;
    private String degree;
    private String age;
    private String gender;
    private String workyear;

    public Integer getPanaId() {
        return panaId;
    }

    public void setPanaId(Integer panaId) {
        this.panaId = panaId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkyear() {
        return workyear;
    }

    public void setWorkyear(String workyear) {
        this.workyear = workyear;
    }
}

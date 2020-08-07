package com.example.lewjun.domain.bo;

public class Ab01BO extends BaseObj {
    /**
     * 部门编号 如果不是id字段，那么需要使用@TableId注释这就是id
     */
    private Integer aab001;
    /**
     * 部门名称
     */
    private String aab002;
    /**
     * 部门所在位置
     */
    private String aab003;

    public Integer getAab001() {
        return aab001;
    }

    public void setAab001(final Integer aab001) {
        this.aab001 = aab001;
    }

    public String getAab002() {
        return aab002;
    }

    public void setAab002(final String aab002) {
        this.aab002 = aab002;
    }

    public String getAab003() {
        return aab003;
    }

    public void setAab003(final String aab003) {
        this.aab003 = aab003;
    }
}
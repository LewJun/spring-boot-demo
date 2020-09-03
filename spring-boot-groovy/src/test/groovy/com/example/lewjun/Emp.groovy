package com.example.lewjun

import groovy.transform.ToString

/**
 * 雇员信息
 * @author LewJun
 */
@ToString
class Emp {
    Integer empno
    String ename
    String job
    Integer mgr
    Date hiredate
    Integer deptno
    Boolean sex
    Float height
    Float weight

    String display() {
        return "I am $ename, my empno is $empno"
    }

    void fuck() {
        println "fuck"
    }
}
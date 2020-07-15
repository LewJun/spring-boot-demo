package com.example.lewjun.model;


import com.fasterxml.jackson.annotation.JsonInclude;

// 若为null，则不要返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseObj {
}

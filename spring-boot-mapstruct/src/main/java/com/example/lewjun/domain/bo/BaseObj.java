package com.example.lewjun.domain.bo;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

// 若为null，则不要返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseObj implements Serializable {
}

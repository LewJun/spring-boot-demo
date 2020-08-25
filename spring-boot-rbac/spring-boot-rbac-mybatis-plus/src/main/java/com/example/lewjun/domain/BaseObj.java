package com.example.lewjun.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

// 若为null，则不要返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BaseObj implements Serializable {

}

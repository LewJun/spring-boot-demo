package com.example.lewjun.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

// 若为null，则不要返回给前端
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BaseObj implements Serializable {
    @Id
    @GeneratedValue
    private long id;
}

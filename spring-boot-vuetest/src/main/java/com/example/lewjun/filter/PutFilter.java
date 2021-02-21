package com.example.lewjun.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * 解决springboot 接收参数为null问题
 */
@Component
public class PutFilter extends HttpPutFormContentFilter {
}

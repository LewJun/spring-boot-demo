package com.example.lewjun.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SysRoleRepository {
    private
    static final Map<String, String[]> map = new HashMap<>(2);

    private static final String[] anonymousList = {
            "/login",
            "/captchaImage",
            "/profile/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/druid/**"
    };

    private static final String[] permitAllList = {
            "/permitAll",
            "/*.html",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
    };

    static {
        map.put("/admin", new String[]{"ADMIN"});
        map.put("/normal", new String[]{"NORMAL", "USER"});
    }

    public Map<String, String[]> findRolesByRequestUrl(final String requestUrl) {

        if (map.containsKey(requestUrl)) {
            final HashMap<String, String[]> stringHashMap = new HashMap<>();
            stringHashMap.put(requestUrl, map.get(requestUrl));
            return stringHashMap;
        }
        return new HashMap<>();
    }

    public String[] getAnonymousList() {
        return anonymousList;
    }

    public String[] getPermitAllList() {
        return permitAllList;
    }
}

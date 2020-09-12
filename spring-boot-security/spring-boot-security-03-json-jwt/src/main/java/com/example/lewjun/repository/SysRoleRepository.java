package com.example.lewjun.repository;

import com.example.lewjun.config.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SysRoleRepository {
    private static final Map<String, String[]> MAP = new HashMap<>(2);

    static {
        // 存放的时候不能以/结尾
        MAP.put("/admin", new String[]{"ADMIN"});
        MAP.put("/normal", new String[]{"NORMAL", "USER"});
    }

    @Autowired
    private JwtToken jwtToken;

    public Map<String, String[]> findRolesByRequestUrl(final String requestUrl) {

        if (MAP.containsKey(requestUrl)) {
            final HashMap<String, String[]> stringHashMap = new HashMap<>(1);
            stringHashMap.put(requestUrl, MAP.get(requestUrl));
            return stringHashMap;
        }
        return new HashMap<>(0);
    }

    public String[] getAnonymousList() {
        return jwtToken.getAnonymousList();
    }

    public String[] getPermitAllList() {
        return jwtToken.getPermitAllList();
    }
}

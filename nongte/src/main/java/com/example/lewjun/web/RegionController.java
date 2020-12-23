package com.example.lewjun.web;

import com.example.lewjun.domain.Region;
import com.example.lewjun.mapper.RegionMapper;
import com.example.lewjun.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/regions")
@RestController
public class RegionController {

    private RegionMapper regionMapper;

    @Autowired
    RegionController(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @GetMapping("/select")
    public String getSelect() {
        Map<String, Object> map = new HashMap(3);
        List<Region> provs = regionMapper.queryAllProvs();

        map.put("prov", provs);

        Map<Integer, List<Region>> cityMap = new HashMap<>();
        Map<Integer, List<Region>> areaMap = new HashMap<>();
        for (Region prov : provs) {
            Integer provCode = prov.getCode();
            List<Region> cities = regionMapper.queryCitiesByProvinceCode(provCode);
            cityMap.put(provCode, cities);

            for (Region city : cities) {
                Integer cityCode = city.getCode();
                List<Region> areas = regionMapper.queryAreasByCityCode(cityCode);
                areaMap.put(cityCode, areas);
            }
        }
        map.put("city", cityMap);
        map.put("area", areaMap);

        return JsonUtils.object2String(map);
    }
}

package com.example.lewjun.mapper;

import com.example.lewjun.domain.Fd;
import com.example.lewjun.domain.result.FdDownloadResult;
import com.example.lewjun.domain.vo.FdQueryParam;
import com.example.lewjun.domain.vo.FdUpdateParam;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@CacheNamespace(flushInterval = 60000)
@Repository
public interface FdMapper {
    @SelectProvider(type = FdMapperProvider.class, method = "findByParam")
    List<Fd> findByParam(FdQueryParam param);

    @SelectProvider(type = FdMapperProvider.class, method = "findDownloadByParam")
    List<FdDownloadResult> findDownloadByParam(FdQueryParam param);

    @SelectProvider(type = FdMapperProvider.class, method = "countByParam")
    int countByParam(FdQueryParam param);

    @Update("update fd set yyss001=#{yyss001}, fd018=now() where fd001=#{fd001}")
    int updateYyss001(FdUpdateParam param);

    @Select("select * from fd where fd001=#{fd001} limit 1")
    Fd findByFd001(String fd001);
}

package com.example.lewjun.domain.convert;

import com.example.lewjun.domain.bo.Ab01BO;
import com.example.lewjun.domain.dataobject.Ab01DO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Ab01Convert {

    Ab01Convert INSTANCE = Mappers.getMapper(Ab01Convert.class);

    /**
     * 将Ab01DO转换为Ab01BO
     * @param ab01DO
     * @return
     */
    Ab01BO convert(Ab01DO ab01DO);
}

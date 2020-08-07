package com.example.lewjun.domain.convert;

import com.example.lewjun.domain.bo.Ab01BO;
import com.example.lewjun.domain.dataobject.Ab01DO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

@Mapper
public interface Ab01Convert {

    Ab01Convert INSTANCE = Mappers.getMapper(Ab01Convert.class);

    /**
     * 将Ab01DO转换为Ab01BO
     *
     * @param ab01DO
     * @return
     */
    @Mappings({
            // 使用mapping将两个不同名字的属性对应起来 ab01BO.setBob( ab01DO.getBirthday() );
            @Mapping(source = "birthday", target = "bob"),
            // 限定 将以逗号分隔的字符串转换为List，ab01BO.setHobbies( translateStringToListString( ab01DO.getHobbies() ) );
            @Mapping(source = "hobbies", target = "hobbies", qualifiedByName = "translateStringToListString")
    })
    Ab01BO convert(Ab01DO ab01DO);

    default List<String> translateStringToListString(final String string) {
        if (string == null) return null;
        return Arrays.asList(string.split(",", -1));
    }
}

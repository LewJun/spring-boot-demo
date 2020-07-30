package com.example.lewjun.service.impl;

import com.example.lewjun.domain.Ab01;
import com.example.lewjun.service.Ab01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Ab01ServiceImpl implements Ab01Service {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(final Ab01 ab01) {
        return jdbcTemplate.update("insert into ab01(aab002, aab003) values(?, ?)", ab01.getAab002(), ab01.getAab003());
    }

    @Override
    public int delete(final Integer pk) {
        return jdbcTemplate.update("delete from ab01 where aab001=?", pk);
    }

    @Override
    public int update(final Ab01 ab01) {
        return jdbcTemplate.update("update ab01 set aab002=?, aab003=? where aab001=?", ab01.getAab002(), ab01.getAab003(), ab01.getAab001());
    }

    @Override
    public Ab01 queryById(final Integer id) {
        return jdbcTemplate.queryForObject("select * from ab01 where aab001=?", new Object[]{id}, new BeanPropertyRowMapper<>(Ab01.class));
    }

    @Override
    public List<Ab01> queryByAab002(final String aab002) {
        return jdbcTemplate.query("select * from ab01 where aab002=?", (resultSet, i) ->
                        new Ab01()
                                .setAab001(resultSet.getInt("aab001"))
                                .setAab002(resultSet.getString("aab002"))
                                .setAab003(resultSet.getString("aab003"))
                , aab002);
    }

    @Override
    public List<Ab01> queryByAab003(final String aab003) {
        return jdbcTemplate.query("select * from ab01 where aab003=?", (resultSet, i) ->
                        new Ab01()
                                .setAab001(resultSet.getInt("aab001"))
                                .setAab002(resultSet.getString("aab002"))
                                .setAab003(resultSet.getString("aab003"))
                , aab003);
    }

    @Override
    public List<Ab01> queryAll() {
//        return jdbcTemplate.query("select * from ab01", (resultSet, i) ->
//                new Ab01()
//                        .setAab001(resultSet.getInt("aab001"))
//                        .setAab002(resultSet.getString("aab002"))
//                        .setAab003(resultSet.getString("aab003"))
//        );


//        使用错误new BeanPropertyRowMapper<>()
//        java.lang.IllegalStateException: Mapped class was not specified
//        return jdbcTemplate.query("select * from ab01", new Object[]{}, new BeanPropertyRowMapper<>());

        // 需要把Ab01.class传入到BeanPropertyRowMapper中
        return jdbcTemplate.query("select * from ab01", new Object[]{}, new BeanPropertyRowMapper<>(Ab01.class));
    }

    @Override
    public int getAb01Count() {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select count(1) from ab01", Integer.class)).orElse(0);
    }

    @Override
    public List<Integer> queryAllAab001() {
        return jdbcTemplate.queryForList("select aab001 from ab01", new Object[]{}, Integer.class);
    }
}

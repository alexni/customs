package ru.customs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.customs.entity.ClaimEntity;
import ru.customs.entity.ClaimState;
import ru.customs.entity.DeclarantEntity;
import ru.customs.entity.DeclarantState;

import java.util.List;

public class DeclarantDao {
    @Autowired
    JdbcTemplate jdbc;

    public DeclarantDao() {
    }

    public List<DeclarantEntity> getDeclarants(DeclarantState state, int limit, int offset) {
        String sql = "SELECT * FROM declarant_entity WHERE state=? limit ? offset ?";
        List<DeclarantEntity> declarants  = jdbc.query(sql, new Object[]{state.ordinal(), limit, offset},
                new BeanPropertyRowMapper(DeclarantEntity.class));
        return declarants;
    }

    public Long getCount(ClaimState state) {
        return jdbc.queryForObject("SELECT count(1) from declarant_entity WHERE state = ?", new Object[]{state.ordinal()}, Long.class);
    }

    public List<DeclarantEntity> getDeclarants(int limit, int offset) {
        String sql = "SELECT * FROM declarant_entity limit ? offset ?";
        List<DeclarantEntity> declarants  = jdbc.query(sql, new Object[]{limit, offset},
                new BeanPropertyRowMapper(DeclarantEntity.class));
        return declarants;
    }

    public Long getCount() {
        return jdbc.queryForObject("SELECT count(1) from declarant_entity",  Long.class);
    }


    public Long getCountByPhone(String phone) {
        return jdbc.queryForObject("SELECT count(1) from declarant_entity where phone = ?",new Object[]{phone},  Long.class);
    }
}

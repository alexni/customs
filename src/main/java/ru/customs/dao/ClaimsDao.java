package ru.customs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.customs.dto.ClaimsResponse;
import ru.customs.entity.ClaimEntity;
import ru.customs.entity.ClaimState;
import ru.customs.entity.DeclarantState;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ClaimsDao {
    @Autowired
    JdbcTemplate jdbc;

    public ClaimsDao() {
    }

    public List<ClaimEntity> getClaimsByState(ClaimState state, int limit, int offset) {
        String sql = "SELECT * FROM claims WHERE state=? order by timestamp limit ? offset ?";
        List<ClaimEntity> claims  = jdbc.query(sql, new Object[]{state.ordinal(), limit, offset},
                new BeanPropertyRowMapper(ClaimEntity.class));
        return claims;
    }

    public List<ClaimEntity> getClaimsByManagerAndState(ClaimState state, int managerId, int limit, int offset) {
        String sql = "SELECT * FROM claims WHERE state=? and manager_id=? order by timestamp limit ? offset ?";
        List<ClaimEntity> claims  = jdbc.query(sql, new Object[]{state.ordinal(), managerId, limit, offset},
                new BeanPropertyRowMapper(ClaimEntity.class));
        return claims;
    }

    public List<ClaimEntity> getClaimsByManager(int managerId, int limit, int offset) {
        String sql = "SELECT * FROM claims WHERE manager_id=? order by timestamp limit ? offset ?";
        List<ClaimEntity> claims  = jdbc.query(sql, new Object[]{managerId, limit, offset},
                new BeanPropertyRowMapper(ClaimEntity.class));
        return claims;
    }

    public Long getCount(ClaimState state) {
        return jdbc.queryForObject("SELECT count(1) from claims WHERE state = ?", new Object[]{state.ordinal()}, Long.class);
    }

    public List<ClaimEntity> getClaims(int limit, int offset) {
        String sql = "SELECT * FROM claims order by timestamp limit ? offset ?";
        List<ClaimEntity> claims  = jdbc.query(sql, new Object[]{limit, offset},
                new BeanPropertyRowMapper(ClaimEntity.class));
        return claims;    }

    public Long getCount() {
        return jdbc.queryForObject("SELECT count(1) from claims", Long.class);
    }

    public Long getCountByManager(int managerId) {
        return jdbc.queryForObject("SELECT count(1) from claims where managerId="+managerId, Long.class);
    }


    public Long getByDeclarant(Long id){
        return jdbc.queryForObject("Select c.id from claims c left join declarant_entity d on c.phone=d.phone where d.id ="+id+" order by c.timestamp desc limit 1", Long.class);
    }
}

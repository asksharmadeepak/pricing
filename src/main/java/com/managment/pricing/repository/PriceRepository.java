package com.managment.pricing.repository;

import com.managment.pricing.entity.Oil;
import com.managment.pricing.entity.TradeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PriceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Oil> findAll() {
        return jdbcTemplate.query("select * from oil", (rs, i) -> new Oil(rs.getString("id")
                ,rs.getString("type") ,
                rs.getDouble("barrelValue"),
                rs.getDouble("fixedRevenue"),
                rs.getDouble("variableRevenue")));
    }
}

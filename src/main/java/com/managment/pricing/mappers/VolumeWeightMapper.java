package com.managment.pricing.mappers;

import com.managment.pricing.entity.VolumeWeight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VolumeWeightMapper implements RowMapper<VolumeWeight> {

    @Override
    public VolumeWeight mapRow(ResultSet rs, int rowNum) throws SQLException {
        VolumeWeight volumeWeight = new VolumeWeight();
        volumeWeight.setTradeTime(rs.getString("tradeTimestamp"));
        return volumeWeight;
    }
}

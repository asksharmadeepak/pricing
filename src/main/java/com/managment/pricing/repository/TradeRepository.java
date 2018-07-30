package com.managment.pricing.repository;

import com.managment.pricing.entity.TradeRequest;
import com.managment.pricing.entity.TradeResponse;
import com.managment.pricing.entity.VolumeWeight;
import com.managment.pricing.mappers.TradeRowMapper;
import com.managment.pricing.mappers.VolumeWeightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TradeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public int insertTradeRequest(TradeRequest tradeRequest) {
        return jdbcTemplate.update("insert into trade (tradeTimestamp, quantity, buySellIndicator, price ) " + "values(?,  ?, ?, ?)",
                new Object[] {
                        tradeRequest.getTimestamp(), tradeRequest.getQuantity(),
                        tradeRequest.getBuySellIndicator(),tradeRequest.getPrice()});
    }

    public List<TradeResponse> findAll() {
        return jdbcTemplate.query("select * from trade", new TradeRowMapper());
    }

}

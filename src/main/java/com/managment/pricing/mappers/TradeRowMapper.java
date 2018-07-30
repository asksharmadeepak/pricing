package com.managment.pricing.mappers;

import com.managment.pricing.entity.TradeResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeRowMapper implements RowMapper<TradeResponse> {
    @Override
    public TradeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        TradeResponse tradeResponse = new TradeResponse();
        tradeResponse.setTradeId(rs.getInt("transactionId"));
        tradeResponse.setTradeTime(rs.getString("tradeTimestamp"));
        tradeResponse.setTradeStatus(rs.getString("buySellIndicator"));
        tradeResponse.setTradePrice(rs.getDouble("price"));
        tradeResponse.setQuantity(rs.getInt("quantity"));
        return tradeResponse;
    }
}

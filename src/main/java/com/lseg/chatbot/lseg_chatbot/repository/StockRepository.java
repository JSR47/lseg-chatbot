package com.lseg.chatbot.lseg_chatbot.repository;

import com.lseg.chatbot.lseg_chatbot.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
        List <Stock> findByStockExchangeId(Integer stockExchangeId);
}

package com.lseg.chatbot.lseg_chatbot.repository;

import com.lseg.chatbot.lseg_chatbot.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {
    Optional<StockExchange> findByStockExchange(String stockExchange);
}

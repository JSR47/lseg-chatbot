package com.lseg.chatbot.lseg_chatbot.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exchangeCode;
    private String stockExchange;
    @OneToMany(mappedBy = "stockExchange", cascade = CascadeType.ALL)
    private List<Stock> topStocks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public List<Stock> getTopStocks() {
        return topStocks;
    }

    public void setTopStocks(List<Stock> topStocks) {
        this.topStocks = topStocks;
    }
}

package com.lseg.chatbot.lseg_chatbot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String stockName;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "stock_exchange_id")
    private StockExchange stockExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public StockExchange getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(StockExchange stockExchange) {
        this.stockExchange = stockExchange;
    }
}

package com.lseg.chatbot.lseg_chatbot.controller;
import com.lseg.chatbot.lseg_chatbot.model.Stock;
import com.lseg.chatbot.lseg_chatbot.model.StockExchange;
import com.lseg.chatbot.lseg_chatbot.repository.StockExchangeRepository;
import com.lseg.chatbot.lseg_chatbot.service.StockExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/stock-exchanges")
public class StockController {

    private final StockExchangeService stockExchangeService;

    private final StockExchangeRepository stockExchangeRepository;


    public StockController(StockExchangeService stockExchangeService, StockExchangeRepository stockExchangeRepository) {
        this.stockExchangeService = stockExchangeService;
        this.stockExchangeRepository = stockExchangeRepository;
    }

    @GetMapping("/get-all-stocks")
    public ResponseEntity <List<String>> getAllStockExchangeNames() {
        return ResponseEntity.ok().body(stockExchangeService.findAll().stream()
                .map(StockExchange::getStockExchange)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{name}/stocks")
    public ResponseEntity<List<String>> getStocksByExchangeName(@PathVariable String name) {
        Optional<StockExchange> stockExchange = stockExchangeService.findByStockExchangeName(name);

        if (stockExchange.isPresent()) {
            List<String> stockNames = stockExchange.get().getTopStocks().stream()
                    .map(Stock::getStockName)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(stockNames);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stocks/{name}/price")
    public ResponseEntity<String> getStockPriceByName(@PathVariable String name) {
        Optional<Double> stockPrice = stockExchangeService.findStockPriceByName(name);

        if (stockPrice.isPresent()) {
            String responseMessage = String.format("Stock price of %s is %.2f. Please select an option.", name, stockPrice.get());
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.lseg.chatbot.lseg_chatbot.controller;
import com.lseg.chatbot.lseg_chatbot.dto.PriceDto;
import com.lseg.chatbot.lseg_chatbot.dto.StockExchangeDto;
import com.lseg.chatbot.lseg_chatbot.dto.TopStocksDto;
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



    public StockController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @GetMapping("/get-all-stocks")
    public ResponseEntity <List<StockExchangeDto>> getAllStockExchangeNames() {
        List<StockExchangeDto> stockExchangeNames = stockExchangeService.findAll().stream()
                .map(stockExchange -> new StockExchangeDto(stockExchange.getStockExchange()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(stockExchangeNames);
    }

    @GetMapping("/{id}/stocks")
    public ResponseEntity<List<TopStocksDto>> getStocksByExchangeName(@PathVariable int id) {

        List <Stock> stock = stockExchangeService.findByStockExchangeId(id);

        if (!stock.isEmpty()) {
            List<TopStocksDto> topStockExchanges = stockExchangeService.findByStockExchangeId(id).stream()
                    .map(topStocks -> new TopStocksDto(topStocks.getStockName()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(topStockExchanges);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stocks/{name}/price")
    public ResponseEntity <PriceDto> getStockPriceByName(@PathVariable String name) {
        Optional<Double> stockPrice = stockExchangeService.findStockPriceByName(name);

        if (stockPrice.isPresent()) {
            String responseMessage = String.format("Stock price of %s is %.2f. Please select an option.", name, stockPrice.get());
            PriceDto price = new PriceDto();
            price.setPrice(responseMessage);
            return ResponseEntity.ok(price);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
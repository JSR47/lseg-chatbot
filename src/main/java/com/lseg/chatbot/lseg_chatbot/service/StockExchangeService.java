package com.lseg.chatbot.lseg_chatbot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lseg.chatbot.lseg_chatbot.model.Stock;
import com.lseg.chatbot.lseg_chatbot.model.StockExchange;
import com.lseg.chatbot.lseg_chatbot.repository.StockExchangeRepository;
import com.lseg.chatbot.lseg_chatbot.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class StockExchangeService {

    private final Logger log = LoggerFactory.getLogger(StockExchangeService.class);


    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    private StockRepository stockRepository;

    public void saveStockDataFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("json/(TC3)Chatbot - stock data.json");

        try (InputStream inputStream = resource.getInputStream()) {
            List<StockExchange> stockExchanges = mapper.readValue(inputStream, new TypeReference<List<StockExchange>>() {});

            for (StockExchange exchange : stockExchanges) {
                for (Stock stock : exchange.getTopStocks()) {
                    stock.setStockExchange(exchange);
                }
                stockExchangeRepository.save(exchange);
            }
        }catch (IOException e){
            log.error("File probably is not there: {}", e.getMessage());
        }
    }

    public List<StockExchange> findAll() {
        return stockExchangeRepository.findAll();
    }

    public Optional<StockExchange> findByStockExchangeName(String stockExchangeName) {
        return stockExchangeRepository.findByStockExchange(stockExchangeName);
    }

    public List <Stock> findByStockExchangeId(int id) {
        return stockRepository.findByStockExchangeId(id);
    }

    public Optional<Double> findStockPriceByName(String stockName) {

        List<StockExchange> exchanges = stockExchangeRepository.findAll();
        for (StockExchange exchange : exchanges) {
            for (Stock stock : exchange.getTopStocks()) {
                if (stock.getStockName().equalsIgnoreCase(stockName)) {
                    return Optional.of(stock.getPrice());
                }
            }
        }
        return Optional.empty();
    }
}

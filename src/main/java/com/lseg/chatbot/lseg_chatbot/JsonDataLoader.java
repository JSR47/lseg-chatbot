package com.lseg.chatbot.lseg_chatbot;

import com.lseg.chatbot.lseg_chatbot.service.StockExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JsonDataLoader implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(JsonDataLoader.class);

    @Autowired
    private StockExchangeService stockExchangeService;

    @Override
    public void run(String... args) throws Exception {
        stockExchangeService.saveStockDataFromFile();
        log.info("Stock data loaded successfully!");
    }
}
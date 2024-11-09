package com.lseg.chatbot.lseg_chatbot.dto;

import com.lseg.chatbot.lseg_chatbot.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TopStocksDto {
    private String stockName;
}

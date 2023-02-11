package com.ignacioillanes.CurrencyExchangejava.Api;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ignacioillanes.CurrencyExchangejava.Bl.ExchangeBl;
import com.ignacioillanes.CurrencyExchangejava.Dto.ConvertionRequestDto;
import com.ignacioillanes.CurrencyExchangejava.Dto.ResponseDto;

@RestController
@RequestMapping("/api/v1/currency")
public class ExchangeApi {
    private static Logger logger = LoggerFactory.getLogger(ExchangeApi.class);

    ExchangeBl exchangeBl;

    public ExchangeApi(ExchangeBl exchangeBl) {
        this.exchangeBl = exchangeBl;
    }

    @GetMapping("/convert")
    public ResponseEntity<ResponseDto> convert(@RequestBody ConvertionRequestDto request) {
        String to = request.getTo();
        String from = request.getFrom();
        BigDecimal amount = request.getAmount();

        ResponseDto response = new ResponseDto();

        try {
            logger.info("GET /api/v1/currency/convert - from: " + from + ", to: " + to + ", amount: " + amount.toString());
            response.setData(exchangeBl.makeConvertion(from, to, amount));
            response.setSuccess(true);
            response.setMessage("Convertion made successfully");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        logger.info("200 OK");
        return ResponseEntity.ok(response);
    }
}

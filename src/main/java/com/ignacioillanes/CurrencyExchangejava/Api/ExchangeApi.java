package com.ignacioillanes.CurrencyExchangejava.Api;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignacioillanes.CurrencyExchangejava.Bl.ExchangeBl;
import com.ignacioillanes.CurrencyExchangejava.Dto.ConvertionRequestDto;
import com.ignacioillanes.CurrencyExchangejava.Dto.ResponseDto;

@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeApi {
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
            response.setData(exchangeBl.makeConvertion(from, to, amount));
            response.setSuccess(true);
            response.setMessage("Convertion made successfully");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());   
        } 
        
        return ResponseEntity.ok(response);
    }
}

package com.ignacioillanes.CurrencyExchangejava.Dto;

import java.math.BigDecimal;

public class ConvertionRequestDto {
    private String from;
    private String to;
    private BigDecimal amount;


    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public ConvertionRequestDto() {
    }

    public ConvertionRequestDto(String from, String to, BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}

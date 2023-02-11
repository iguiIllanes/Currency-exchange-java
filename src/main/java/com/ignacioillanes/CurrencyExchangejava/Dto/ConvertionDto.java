package com.ignacioillanes.CurrencyExchangejava.Dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertionDto {
    private QueryDto query;
    private BigDecimal result;

    public QueryDto getQuery() {
        return this.query;
    }

    public void setQuery(QueryDto query) {
        this.query = query;
    }

    public BigDecimal getResult() {
        return this.result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }


    public ConvertionDto() {
    }

    public ConvertionDto(QueryDto query, BigDecimal result) {
        this.query = query;
        this.result = result;
    }

}

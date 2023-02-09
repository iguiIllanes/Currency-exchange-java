package com.ignacioillanes.CurrencyExchangejava.Bl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

@Service
public class ExchangeBl {
    @Value("${fixer.api.key}")
    private String API_KEY;
    @Value("${fixer.api.baseurl}")
    private String BASE_URL;

    ObjectMapper objectMapper = new ObjectMapper();

    OkHttpClient client = new OkHttpClient();

    // makes the convertion using the fixer.io api
    public Object makeConvertion(String from, String to, BigDecimal amount) throws Exception{
        if(amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL+"/convert").newBuilder();
        urlBuilder
            .addQueryParameter("to", to)
            .addQueryParameter("from", from)
            .addQueryParameter("amount", amount.toString());

        String requestUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
            .url(requestUrl)
            .addHeader("apikey", API_KEY)
            .build();
        ResponseBody responseBody = client.newCall(request).execute().body();

        
        return objectMapper.readValue(responseBody.string(), Object.class);
    }
}

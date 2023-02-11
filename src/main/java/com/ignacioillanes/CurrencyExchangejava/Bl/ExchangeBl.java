package com.ignacioillanes.CurrencyExchangejava.Bl;

import com.ignacioillanes.CurrencyExchangejava.Dto.ConvertionDto;
import com.ignacioillanes.CurrencyExchangejava.Exception.ConvertionException;
import com.ignacioillanes.CurrencyExchangejava.Exception.ServiceUnavailableException;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ExchangeBl {
    @Value("${fixer.api.key}")
    private String API_KEY;
    @Value("${fixer.api.baseurl}")
    private String BASE_URL;

    ObjectMapper objectMapper = new ObjectMapper();

    OkHttpClient client = new OkHttpClient();

    // makes the convertion using the fixer.io api
    public ConvertionDto makeConvertion(String from, String to, BigDecimal amount)
            throws ServiceUnavailableException, ConvertionException {

        /*
         * Fixer "/convert" request example:
         * GET
         * https://data.fixer.io/api/convert?access_key=API_KEY&from=USD&to=BOB&amount=
         * 100
         * 
         * Fixer "/convert" response example:
         * {
         *   success: true,
         *   query: {
         *     from: "USD",
         *     to: "BOB",
         *     amount: 100
         *   },
         *   info: {
         *     timestamp: 1676075043,
         *     rate: 6.911391
         *   },
         *   date: 2023-02-11,
         *   result: 691.1391
         * }
         */

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/convert").newBuilder();
        urlBuilder
                .addQueryParameter("to", to)
                .addQueryParameter("from", from)
                .addQueryParameter("amount", amount.toString());

        String requestUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader("apikey", API_KEY)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new ConvertionException("Error while making the convertion request");
            }

            ConvertionDto convertion = objectMapper.readValue(response.body().string(), ConvertionDto.class);
            return convertion;
        } catch (Exception e) {
            throw new ServiceUnavailableException("Error while making the convertion request");
        }
    }
}

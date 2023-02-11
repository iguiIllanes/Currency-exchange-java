package com.ignacioillanes.CurrencyExchangejava.Dto;

public class ResponseDto {
    private Boolean success;
    private ConvertionDto data;
    private String message;


    public Boolean isSuccess() {
        return this.success;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ConvertionDto getData() {
        return this.data;
    }

    public void setData(ConvertionDto data) {
        this.data = data;
    }


    public ResponseDto() {
    }

    public ResponseDto(Boolean success, String message, ConvertionDto data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}

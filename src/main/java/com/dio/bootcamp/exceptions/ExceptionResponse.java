package com.dio.bootcamp.exceptions;

public class ExceptionResponse {

    private  String mesage;

   public ExceptionResponse (String message){
        this.mesage = message;
    }
    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }
}

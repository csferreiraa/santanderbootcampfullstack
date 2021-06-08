package com.dio.bootcamp.exceptions;

import com.dio.bootcamp.util.MessageUtils;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super(MessageUtils.NO_RECORDS_FOUNDS);
    }
}

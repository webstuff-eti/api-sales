package com.tiagotibaes.controller.exceptions.errors;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class GenericErrors {

    @Getter
    private List<String> errors;

    public GenericErrors(String messageError){
        this.errors = Arrays.asList(messageError);
    }
}

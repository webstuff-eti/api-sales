package com.tiagotibaes.domain.enumerated;

public enum TypeDocument {
    CPF("CPF"),
    RG("RG"),
    CNH("CNH"),
    CNPJ("CNPJ");

    private String value;

    private TypeDocument(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        return value;
    }
}

package com.tiagotibaes.domain.enumerated;

public enum TypeDocument {
    CPF("Cadastro de Pessoa Física"),
    RG("Registro Geral"),
    CNH("Carteira Nacional de Habilitação"),
    CNPJ("Cadastro Nacional de Pessoas Jurídicas");

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

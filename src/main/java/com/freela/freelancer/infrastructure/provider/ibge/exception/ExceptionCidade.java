package com.freela.freelancer.infrastructure.provider.ibge.exception;

public class ExceptionCidade extends  RuntimeException{
    public ExceptionCidade(){
        super("Algo deu errado ao buscar as cidades");
    }

}

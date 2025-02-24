package com.freela.freelancer.Ibge.Execoes;

public class ExceptionCidade extends  RuntimeException{
    public ExceptionCidade(){
        super("Algo deu errado ao buscar as cidades");
    }

}

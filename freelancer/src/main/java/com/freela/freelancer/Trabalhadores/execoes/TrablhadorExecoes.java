package com.freela.freelancer.Trabalhadores.execoes;

public class TrablhadorExecoes extends RuntimeException {
    public TrablhadorExecoes(){
        super("Trabalhador já existe");
    }
}

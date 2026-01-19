package com.freela.freelancer.workers.execoes;

public class WorkersExceptions extends RuntimeException {
    public WorkersExceptions(){
        super("Trabalhador já existe");
    }
}

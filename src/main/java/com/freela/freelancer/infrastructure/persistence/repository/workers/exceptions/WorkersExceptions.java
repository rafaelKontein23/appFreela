package com.freela.freelancer.infrastructure.persistence.repository.workers.exceptions;

public class WorkersExceptions extends RuntimeException {
    public WorkersExceptions(){
        super("Trabalhador já existe");
    }
}

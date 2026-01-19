package com.freela.freelancer.Trabalhadores.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.rmi.server.UID;
import java.util.UUID;

@Entity
public class ProfissaoEntidade {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String nome;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

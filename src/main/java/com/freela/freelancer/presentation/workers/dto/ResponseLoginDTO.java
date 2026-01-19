package com.freela.freelancer.presentation.workers.dto;

public class ResponseLoginDTO {

    private String token;
    private String cpf;


    public ResponseLoginDTO(String token, String cpf) {
        this.token = token;
        this.cpf = cpf;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

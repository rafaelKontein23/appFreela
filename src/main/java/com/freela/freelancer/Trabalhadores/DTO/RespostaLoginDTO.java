package com.freela.freelancer.Trabalhadores.DTO;

public class RespostaLoginDTO {

    private String token;
    private String cpf;


    public RespostaLoginDTO(String token, String cpf) {
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

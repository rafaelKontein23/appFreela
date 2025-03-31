package com.freela.freelancer.Ultis;

public class RespostaPadrao {

    private Boolean valido;
    private Object dados;
    private String mensagem;

    public RespostaPadrao(Boolean valido, Object dados, String mensagem) {
        this.valido = valido;
        this.dados = dados;
        this.mensagem = mensagem;
    }

    public RespostaPadrao() {
    }

    public Boolean getValido() {
        return valido;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }
}

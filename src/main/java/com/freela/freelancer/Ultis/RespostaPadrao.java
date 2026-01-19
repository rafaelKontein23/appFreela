package com.freela.freelancer.Ultis;

public class RespostaPadrao {

    private Boolean valido;
    private Object dados;
    private String mensagemErro;

    public RespostaPadrao(Boolean valido, Object dados, String mensagemErro) {
        this.valido = valido;
        this.dados = dados;
        this.mensagemErro = mensagemErro;
    }

    public RespostaPadrao() {
    }

    public Boolean getValido() {
        return valido;
    }

    public void setValido(Boolean valido) {
        this.valido = valido;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensafemErro) {
        this.mensagemErro = mensafemErro;
    }
}

package com.freela.freelancer.Feed.DTO;

import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;

import java.util.UUID;

public class FeedDTO {
    private UUID trabalhadorEntidade;
    private String descricao;
    private String fotoPerfil; // interligar com o s3 da amazon
    private String Banner;

    public UUID getTrabalhadorEntidade() {
        return trabalhadorEntidade;
    }

    public void setTrabalhadorEntidade(UUID trabalhadorEntidade) {
        this.trabalhadorEntidade = trabalhadorEntidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getBanner() {
        return Banner;
    }

    public void setBanner(String banner) {
        Banner = banner;
    }
}

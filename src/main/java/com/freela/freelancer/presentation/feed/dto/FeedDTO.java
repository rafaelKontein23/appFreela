package com.freela.freelancer.presentation.feed.dto;

import java.util.UUID;

public class FeedDTO {
    private UUID idTrabalhador;
    private String descricao;
    private String fotoPerfil; // interligar com o s3 da amazon
    private String Banner;

    public UUID getIdTrabalhador() {
        return idTrabalhador;
    }

    public void setIdTrabalhador(UUID idTrabalhador) {
        this.idTrabalhador = idTrabalhador;
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

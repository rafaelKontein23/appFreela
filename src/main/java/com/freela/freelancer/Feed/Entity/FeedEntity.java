package com.freela.freelancer.Feed.Entity;


import com.freela.freelancer.workers.Entity.WorkersEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class FeedEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_trabalhador",  nullable = false)// interliga as tabelas
    private WorkersEntity trabalhadorEntidade;

    private String descricao;
    private String fotoPerfil; // interligar com o s3 da amazon
    private String Banner;

    @OneToMany(mappedBy = "feedEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentariosFeed> comentariosFeed = new ArrayList<>();

    public String getBanner() {
        return Banner;
    }

    public void setBanner(String banner) {
        Banner = banner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WorkersEntity getTrabalhadorEntidade() {
        return trabalhadorEntidade;
    }

    public void setTrabalhadorEntidade(WorkersEntity trabalhadorEntidade) {
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
}

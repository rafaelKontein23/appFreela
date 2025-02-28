package com.freela.freelancer.Feed.Entity;


import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
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
    private TrabalhadorEntidade trabalhadorEntidade;

    private String descricao;
    private String fotoPerfil; // interligar com o s3 da amazon
    private String Banner;

    @OneToMany(mappedBy = "feedEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentariosFeed> comentariosFeed = new ArrayList<>();


}

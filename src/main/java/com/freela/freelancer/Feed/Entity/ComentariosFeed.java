package com.freela.freelancer.Feed.Entity;

import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class ComentariosFeed {

    private String nomeCliente;
    private String fotoPerfilCliente;
    private String comentario;


    @ManyToOne
    @JoinColumn(name = "id_feed", nullable = false) // Comentário pertence a um Feed
    private FeedEntity feedEntity;
}

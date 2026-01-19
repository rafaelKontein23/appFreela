package com.freela.freelancer.Feed.Entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class ComentariosFeed {

    @Id
    @GeneratedValue (
            strategy = GenerationType.UUID

    )
    private UUID id;

    private String nomeCliente;
    private String fotoPerfilCliente;
    private String comentario;


    @ManyToOne
    @JoinColumn(name = "id_feed", nullable = false) // Comentário pertence a um Feed
    private FeedEntity feedEntity;
}

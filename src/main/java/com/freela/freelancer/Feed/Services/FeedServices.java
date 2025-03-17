package com.freela.freelancer.Feed.Services;

import com.freela.freelancer.Feed.DTO.FeedDTO;
import com.freela.freelancer.Feed.DTO.FeedResponseDTO;
import com.freela.freelancer.Feed.Entity.FeedEntity;
import com.freela.freelancer.Feed.Repository.FeedRepository;
import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedServices {

    @Autowired
    private FeedRepository feedRepository;


    public void  salvarFeed(FeedDTO feedDTO){
        try {
            FeedEntity feedEntity;

            // Busca o FeedEntity pelo id_trabalhador
            feedEntity = feedRepository.findByTrabalhadorEntidadeId(feedDTO.getIdTrabalhador())
                    .orElse(new FeedEntity());
            TrabalhadorEntidade trabalhador = new TrabalhadorEntidade();
            trabalhador.setId(feedDTO.getIdTrabalhador()); // Supondo que TrabalhadorEntidade tenha um campo id
            feedEntity.setTrabalhadorEntidade(trabalhador);

            feedEntity.setBanner(feedDTO.getBanner());
            feedEntity.setFotoPerfil(feedDTO.getFotoPerfil());
            feedEntity.setDescricao(feedDTO.getDescricao());

            feedRepository.save(feedEntity);
        }catch (Exception e ){
            e.printStackTrace();
        }


    }

    public FeedResponseDTO buscaFeed(UUID trbalhadorID ){
        var feedEntity = feedRepository.findByTrabalhadorEntidadeId(trbalhadorID).orElseThrow(() ->{
                   throw  new RuntimeException("Registre seu feed!");
                }
        );
        var responsefeedDTO = new  FeedResponseDTO();
        responsefeedDTO.setBanner(feedEntity.getBanner());
        responsefeedDTO.setFotoPerfil(feedEntity.getFotoPerfil());
        responsefeedDTO.setDescricao(feedEntity.getDescricao());
        return  responsefeedDTO;


    }

}

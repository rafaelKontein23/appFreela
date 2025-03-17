package com.freela.freelancer.Feed.Services;

import com.freela.freelancer.Feed.DTO.FeedDTO;
import com.freela.freelancer.Feed.DTO.FeedResponseDTO;
import com.freela.freelancer.Feed.Entity.FeedEntity;
import com.freela.freelancer.Feed.Repository.FeedRepository;
import com.freela.freelancer.Trabalhadores.Entity.TrabalhadorEntidade;
import com.freela.freelancer.Ultis.RespostaPadrao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedServices {

    @Autowired
    private FeedRepository feedRepository;


    public RespostaPadrao  salvarFeed(FeedDTO feedDTO){
        try {
            FeedEntity feedEntity;

            feedEntity = feedRepository.findByTrabalhadorEntidadeId(feedDTO.getIdTrabalhador())
                    .orElse(new FeedEntity());
            TrabalhadorEntidade trabalhador = new TrabalhadorEntidade();
            trabalhador.setId(feedDTO.getIdTrabalhador());
            feedEntity.setTrabalhadorEntidade(trabalhador);

            feedEntity.setBanner(feedDTO.getBanner());
            feedEntity.setFotoPerfil(feedDTO.getFotoPerfil());
            feedEntity.setDescricao(feedDTO.getDescricao());

            feedRepository.save(feedEntity);
            return new RespostaPadrao(true, feedEntity, "Feed salvo com sucesso");
        }catch (Exception e ){
            e.printStackTrace();
            return new RespostaPadrao(false, null, "Algo deu errado");
        }
    }

    public RespostaPadrao buscaFeed(UUID trbalhadorID ){
        var feedEntity = feedRepository.findByTrabalhadorEntidadeId(trbalhadorID).orElseThrow(() ->{
                   throw  new RuntimeException("Esse trabalhador não existe");
                }
        );

        var responsefeedDTO = new  FeedResponseDTO();
        responsefeedDTO.setBanner(feedEntity.getBanner());
        responsefeedDTO.setFotoPerfil(feedEntity.getFotoPerfil());
        responsefeedDTO.setDescricao(feedEntity.getDescricao());
        return new RespostaPadrao(true, responsefeedDTO, "");
    }

}

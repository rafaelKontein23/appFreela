package com.freela.freelancer.presentation.feed;


import com.freela.freelancer.presentation.feed.dto.FeedDTO;
import com.freela.freelancer.Feed.Services.FeedServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/feed")
@Tag(name = "feed", description = "feed do trabalhador")
@SecurityRequirement(name = "BearerAuth") // Adiciona o Bearer Token automaticamente
public class FeedController {

    @Autowired
    private FeedServices feedServices;

    @PostMapping("/atualiza/feed/trabalhador")
    @PreAuthorize("hasRole('trabalhador')")
    public ResponseEntity<Object> atualizaFeed(@RequestBody FeedDTO  feedDTO, HttpServletRequest request){
        var trabalhadorId = UUID.fromString(String.format((String) request.getAttribute("id")));
        feedDTO.setIdTrabalhador(trabalhadorId);
        var result = feedServices.salvarFeed(feedDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/busca/feed/trabalhador")
    public ResponseEntity<Object> buscaFeed(HttpServletRequest request){
        try {
            var  trbalhadorID = UUID.fromString((String)  request.getAttribute("id"));
            var result = feedServices.buscaFeed(trbalhadorID);
            return ResponseEntity.ok(result);

        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

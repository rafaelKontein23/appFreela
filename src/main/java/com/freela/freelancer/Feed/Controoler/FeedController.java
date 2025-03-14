package com.freela.freelancer.Feed.Controoler;


import com.freela.freelancer.Feed.DTO.FeedDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @PostMapping("/busca/feed/trabalhador")
    @Tag(name = "feed", description = "feed do trabalhador")
    public ResponseEntity<Object> feed(@RequestBody FeedDTO  feedDTO){

        return ResponseEntity.ok("feed");
    }
}

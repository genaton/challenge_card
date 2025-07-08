package br.com.pos_tech_dev_foudation.challenge_card.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste-swagger")
public class SwaggerTesteController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello Swagger");
    }
}

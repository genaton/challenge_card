package br.com.pos_tech_dev_foudation.challenge_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.pos_tech_dev_foudation.challenge_card.model.domain.usuario.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
// import br.com.pos_tech_dev_foudation.challenge_card.infra.security.TokenService;

// @RestController
// @RequestMapping("/login")
// public class AutenticacaoController {

//     @Autowired
//     private AuthenticationManager manager;

//      @Autowired
//      private TokenService TokenService;

//     @PostMapping
//      public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados){
//         var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//         var authetication = manager.authenticate(authenticationToken);

//         return ResponseEntity.ok().build(); // sem jwt
//          var tokenJWT = tokenService.gerarToken((Usuario) authetication.getPrincipal());
//         return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));


//      }

// }

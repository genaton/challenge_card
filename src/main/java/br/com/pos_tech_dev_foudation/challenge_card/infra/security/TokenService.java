package br.com.pos_tech_dev_foudation.challenge_card.infra.security;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.usuario.Usuario;

// @Service
// public class TokenService {
//     @Value("${api.security.token.secret}")
//     public String gerarToken(Usuario usuario){
// // vide dcumentacao guithub.com/auth0/java-twt: 4.2.1

// private String secret;
//         try {
//             var algoritmo = Algorithm.HMAC256(secret);
//             return JWT.create()
//             .withIssuer("API challenge_card")
//             .withSubject(usuario.getLogin())
//             // .withClaim("id", usuario.getId())
//             .withExpiresAt(dataExpiracao())
//             .sign(algoritmo);

//         } catch (JWTCreationException e) {
//             throw RuntimeException("erro ao gerar token jwt", e);
//         }
        
//         public String getSubject(String tokenJWT{
//          try {
//              var algoritmo = Algorithm.HMAC256(secret)  ;
//                 return JTW.require(algorithm)
//                 .withIssuer("API challenge_card")
//                 .build()
//                 .verify(tokenJWT)
//                 .getSubject();
//             }catch(JWTVerificationException e){
//                 throw new RuntimeException ("Token JWT inválido ou expirado!");

//             }
//         // // }
//         } 
//         private Instant dataExpiracao() {
//             return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3:00"));

//         }
//     }

// }

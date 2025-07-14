package br.com.pos_tech_dev_foudation.challenge_card.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfigurations {

// @Autowired
// private SecurityFilter securityFilter;

// @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            return http.csrf(csrf -> csrf.disable())
//             .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                 .authorizeHttpRequests(req -> {
//                     req.requestMatchers("/login").permitAll();
//                     req.anyRequest().authenticated();
//                 })
                    // .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//             .build(); 

//VERSÃO ANTERIOR AO 3.1 DO SPRING BOOT
// //         return http.csrf().disable()
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//     }
// @Bean
    // public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
    //         return configuration.getAuthenticationManager();
    // }
// @Bean
// public PasswordEncoder passwordEnconder(){
//     return new BCryptPasswordEncoder();

// }

// }

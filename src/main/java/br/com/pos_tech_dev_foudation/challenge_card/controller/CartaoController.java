package br.com.pos_tech_dev_foudation.challenge_card.controller;

import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.DadosNovoCartao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    @Operation(summary = "inclusão de novo cartao")
    @ApiResponse(responseCode = "201", description = "novo cartao incluído")
    @PostMapping
    public void cadastrar(@RequestBody DadosNovoCartao dados){

        System.out.println(dados);


    }

}

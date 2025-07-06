package br.com.pos_tech_dev_foudation.challenge_card.controller;

import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosCadastroCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Operation(summary = "cadastra novo cliente")
    @ApiResponse(responseCode = "201", description = "cliente cadastrado com sucesso")
    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroCliente dados){

        System.out.println(dados);


    }

}

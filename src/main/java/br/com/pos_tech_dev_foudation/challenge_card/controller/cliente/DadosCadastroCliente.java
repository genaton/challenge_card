package br.com.pos_tech_dev_foudation.challenge_card.controller.cliente;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosCadastroCliente(String nome, 
String email, 
String cpf, 
@JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd") LocalDate dataNascimento) {

}

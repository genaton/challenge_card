package br.com.pos_tech_dev_foudation.challenge_card.controller.cartao;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosNovoCartao(
    String numeroCartao,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd") LocalDate dataVencimento,
    Double anuidade,
    Tipo tipo,
    Bandeira bandeira,
    Nome nome


) {

}

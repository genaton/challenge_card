package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCartao(

    @NotNull
    Bandeira bandeira,

    @NotBlank
    String tipo,

    @NotBlank
    String nome,

    @NotNull
    Double anuidade
) {}

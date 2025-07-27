package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoCartao(

        @NotNull
        Long id,

        Double anuidade,

        String tipo,

        Bandeira bandeira,

        String nome
) {}

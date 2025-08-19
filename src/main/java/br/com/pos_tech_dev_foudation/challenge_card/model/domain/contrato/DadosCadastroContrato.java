package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

import java.time.LocalDate;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroContrato(

    @NotNull
    // @Schema(description = "Estado do contrato", example = "ATIVO ou CANCELADO") 
    Status status,

    @NotNull
    // @Schema(description = "Data de início do contrato no formato AAAA-MM-DD", example = "1990-05-15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    LocalDate data,

    @NotNull
    Long clienteId,

    @NotNull
    Long cartaoId
){}

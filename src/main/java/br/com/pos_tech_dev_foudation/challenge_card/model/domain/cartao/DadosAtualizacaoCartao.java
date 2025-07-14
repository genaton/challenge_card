package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoCartao(

        @NotNull 
        // @Schema(description = "id unico do cartao", example = "1") 
        Long id,

        // @NotBlank 
        // @Pattern(regexp = "\\d{19}") 
        // // @Schema(description = "número do cartão com 19 dígitos", example = "0123456789012345678") 
        // String numeroCartao,
        // @NotNull 
        // // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
        // LocalDate dataVencimento,

        @NotNull 
        // @Schema(description = "valor da anuidade", example = "100.11") 
        Double anuidade,

        @NotNull 
        // @Schema(description = "tipo de cartão", example = "CREDITO ou DEBITO") 
        // Tipo tipo,
        String tipo,

        @NotNull 
        // @Schema(description = "bandeira do cartão", example = "VISA, MASTERCAR ou ELO") 
        // Bandeira bandeira,
        String bandeira,

        @NotNull 
        // @Schema(description = "nome do cartão", example = "BLACK, PLATINUM ou GOLD") 
        // Nome nome
        String nome

) {

}

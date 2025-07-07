package br.com.pos_tech_dev_foudation.challenge_card.controller.contrato;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.Bandeira;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.Nome;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.Tipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record DadosContrato( 

    @NotNull
    @Schema(description = "Estado do contrato", example = "ATIVO ou CANCELADO") 
    Status status,

    @NotNull
    @Schema(description = "Data de início do contrato no formato AAAA-MM-DD", example = "1990-05-15")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    LocalDate data){
    
    

}

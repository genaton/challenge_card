package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoCliente(

        @NotNull 
        // @Schema(description = "id unico do cliente", example = "1") 
        Long id,

        // @Schema(description = "Nome completo do cliente", example = "João da Silva") 
        String nome,

        @Email 
        // @Schema(description = "Endereço de e-mail válido", example = "joao.silva@email.com") 
        String email,

        @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
        // @Pattern(regexp ="^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$", message = "CPF
        // pode ser no formato formato 000.000.000-00 ou somente 11 dígitos numéricos" )
        // @CPF(message = "CPF inválido") - para produção
        // @Schema(description = "Número de CPF (somente dígitos)", example = "12345678901") 
        String cpf,

        Boolean ativo,

        // @Schema(description = "cliente ativo ou não", example = "0 - inativo, 1 - ativo") Boolean ativo,

        @Past(message = "A data deve estar no passado") 
        // @Schema(description = "Data de nascimento no formato AAAA-MM-DD", example = "1990-05-15") 
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
        LocalDate dataNascimento) {

}

package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.*;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.ativo;
// import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Nome;
// import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Tipo;
// import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCliente(

    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    String cpf,

    @NotNull
    @Past(message = "A data deve estar no passado")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate dataNascimento
) {}
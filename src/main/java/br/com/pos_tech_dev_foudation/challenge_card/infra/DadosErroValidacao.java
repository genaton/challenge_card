package br.com.pos_tech_dev_foudation.challenge_card.infra;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String mensagem) {
    public DadosErroValidacao(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());

    }
}
package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;

import java.time.LocalDate;

public record DadosContrato(Long id, String cartao, String cliente, LocalDate data_contrato, Status status) {
    public DadosContrato(Contrato contrato) {
        this(contrato.getId(), contrato.getCartao().getNome(), contrato.getCliente().getNome(), contrato.getData(), contrato.getStatus());
    }
}



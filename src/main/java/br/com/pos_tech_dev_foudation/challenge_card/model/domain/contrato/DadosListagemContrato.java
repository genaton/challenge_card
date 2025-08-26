package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

import java.time.LocalDate;

public record DadosListagemContrato(Long id, LocalDate data, Long cliente_id, Long cartao_id, Status status) {

    public DadosListagemContrato(Contrato contrato){
        this(contrato.getId(), contrato.getData(), contrato.getCliente().getId(), contrato.getCartao().getId(), contrato.getStatus());
    }
}

package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

import java.time.LocalDate;



public record DadosDetalhamentoContrato(
Long id, Status status, LocalDate data
) {

     public DadosDetalhamentoContrato(Contrato contrato){
            this(contrato.getId(), contrato.getStatus(), contrato.getData());

        }

}

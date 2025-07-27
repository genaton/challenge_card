package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

import java.time.LocalDate;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Bandeira;


public record DadosDetalhamentoContrato(
    Long id,
    Status status,
    LocalDate data,
    String cliente,
    String cpf,
    String nomeCartao,
    String tipoCartao,
    Bandeira bandeiraCartao,
    Double anuidade
) {
    public DadosDetalhamentoContrato(Contrato contrato){
        this(contrato.getId(), contrato.getStatus(), contrato.getData(),
        contrato.getCliente().getNome(),contrato.getCliente().getCpf(),
        contrato.getCartao().getNome(), contrato.getCartao().getTipo(), contrato.getCartao().getBandeira(),
        contrato.getCartao().getAnuidade());
    }
}

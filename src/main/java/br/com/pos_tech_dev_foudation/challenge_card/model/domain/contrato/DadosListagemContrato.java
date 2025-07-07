package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

public record DadosListagemContrato(
    Status status
) {

    public DadosListagemContrato(Contrato contrato){
        this(contrato.getStatus());

    }

}

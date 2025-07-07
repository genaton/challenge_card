package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;


public record DadosListagemCartao(
     String numeroCartao,
     Tipo tipo,
     Boolean ativo

) {
    public DadosListagemCartao (Cartao cartao){
        this(cartao.getNumeroCartao(), cartao.getTipo(), cartao.getAtivo());

    }

}

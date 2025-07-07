package br.com.pos_tech_dev_foudation.challenge_card.controller.cartao;

public record DadosDetalhamentoCartao(
    Long id, 
    String numeroCartao,
    Double anuidade,
    Tipo tipo,
    Bandeira bandeira,
    Nome nome
) {

    public DadosDetalhamentoCartao(Cartao cartao){
        this(cartao.getId(), cartao.getNumeroCartao(),
         cartao.getAnuidade(),  cartao.getTipo(), 
         cartao.getBandeira(), cartao.getNome());
    }

}

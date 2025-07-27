package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

public record DadosDetalhamentoCartao(
    Long id, 
    Double anuidade,
    String nome,
    Bandeira bandeira,
    String tipo
) {
    public DadosDetalhamentoCartao(Cartao cartao){
        this(cartao.getId(),
        cartao.getAnuidade(),  cartao.getTipo(),
        cartao.getBandeira(), cartao.getNome());
    }
}

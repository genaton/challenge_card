package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

public record DadosDetalhamentoCartao(
    Long id, 
    // String numeroCartao,
    Double anuidade,
    String nome,
    String bandeira,
    String tipo

    // Tipo tipo,
    // Bandeira bandeira,
    // Nome nome
) {

    public DadosDetalhamentoCartao(Cartao cartao){
        this(cartao.getId(),
        // cartao.getNumeroCartao(),
         cartao.getAnuidade(),  cartao.getTipo(), 
         cartao.getBandeira(), cartao.getNome());
    }

}

package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

public record DadosDetalhamentoCartao(
    Long id,
    Bandeira bandeira,
    String tipo,
    String nome,
    Double anuidade
) {
    public DadosDetalhamentoCartao(Cartao cartao){
        this(cartao.getId(), cartao.getBandeira(),
          cartao.getTipo(), cartao.getNome(), cartao.getAnuidade());
    }

}

package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

public record DadosListagemCartao(
    String tipo,
    Bandeira bandeira,
    String nome,
    Double anuidade,
    Boolean ativo
) {
    public DadosListagemCartao (Cartao cartao){
        this(cartao.getTipo(), cartao.getBandeira(), cartao.getNome(), cartao.getAnuidade(), cartao.getAtivo());
    }
}

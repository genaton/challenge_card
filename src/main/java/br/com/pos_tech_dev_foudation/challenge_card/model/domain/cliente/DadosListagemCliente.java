package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente;

public record DadosListagemCliente(
    Long id, String nome, String email, Boolean ativo
) {

    public DadosListagemCliente (Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getAtivo());

    }
    

}

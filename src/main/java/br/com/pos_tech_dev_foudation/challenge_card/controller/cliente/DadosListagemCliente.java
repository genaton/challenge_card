package br.com.pos_tech_dev_foudation.challenge_card.controller.cliente;

public record DadosListagemCliente(
    String nome, String email, Boolean ativo
) {

    public DadosListagemCliente (Cliente cliente){
        this( cliente.getNome(), cliente.getEmail(), cliente.getAtivo());

    }
    

}

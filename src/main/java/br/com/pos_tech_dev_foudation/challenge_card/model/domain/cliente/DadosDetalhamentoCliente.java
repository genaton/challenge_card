package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente;

import java.time.LocalDate;

public record DadosDetalhamentoCliente(
        Long id,
        String nome,
        String email,
        String cpf,
        LocalDate dataNascimento,
        Boolean ativo
) {
    public DadosDetalhamentoCliente(Cliente cliente){
       this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getAtivo());
    }
}

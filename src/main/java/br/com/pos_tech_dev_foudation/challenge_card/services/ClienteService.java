package br.com.pos_tech_dev_foudation.challenge_card.services;

import br.com.pos_tech_dev_foudation.challenge_card.infra.exception.ValidacaoException;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;
import br.com.pos_tech_dev_foudation.challenge_card.repository.ClienteRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosCadastroCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosDetalhamentoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public void excluirCliente(Long idCliente) {
        var cliente = clienteRepository.getReferenceById(idCliente);
        var contrato = contratoRepository.findByCliente(cliente);
        if(contrato != null && contrato.getAtivo()) {
            throw new ValidacaoException("Não foi possível excluir cliente. Cliente com contrato ativo. Desativar o contrato.");
        }

        cliente.excluir();
    }

    public DadosDetalhamentoCliente cadastrarCliente(DadosCadastroCliente dadosCadastroCliente) {
        var cliente = clienteRepository.getClienteByCpf(dadosCadastroCliente.cpf());

        if(cliente != null) {
            throw new ValidacaoException("Não foi possível cadastrar cliente. Cliente com cpf já cadastrado. Cliente pode ser ativado, caso inativo.");
        }

        var clienteCadastro = new Cliente(dadosCadastroCliente);
        clienteRepository.save(clienteCadastro);

        return new DadosDetalhamentoCliente(clienteCadastro);
    }
}

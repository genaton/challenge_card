package br.com.pos_tech_dev_foudation.challenge_card.services;

import br.com.pos_tech_dev_foudation.challenge_card.infra.exception.ValidacaoException;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.CartaoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.ClienteRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosDetalhamentoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.ContratoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.DadosAtualizacaoContrato;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.DadosDetalhamentoContrato;
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
}

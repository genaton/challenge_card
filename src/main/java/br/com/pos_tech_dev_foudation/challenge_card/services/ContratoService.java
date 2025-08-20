package br.com.pos_tech_dev_foudation.challenge_card.services;

import br.com.pos_tech_dev_foudation.challenge_card.infra.exception.ValidacaoException;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.CartaoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.ClienteRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.*;
import ch.qos.logback.core.CoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContratoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    public DadosDetalhamentoContrato contratarCartaoCliente(DadosCadastroContrato dados) {
        if(!clienteRepository.existsById(dados.clienteId())) {
            throw new ValidacaoException("Id do cliente não encontrado!");
        }
        if(!cartaoRepository.existsById(dados.cartaoId())) {
            throw new ValidacaoException("Id do cartão não encontrado!");
        }

        var cliente = clienteRepository.getReferenceById(dados.clienteId());
        var cartao = cartaoRepository.getReferenceById(dados.cartaoId());

        if(!cliente.getAtivo()) {
            throw new ValidacaoException("Este cliente não está ativo, atualize os dados do cliente!");
        }
        if(!cartao.getAtivo()) {
            throw new ValidacaoException("Este cartao não está disponível para novas contratações, escolha outro cartão!");
        }

        var contrato = new Contrato(null, cliente, cartao, dados.status(), dados.data(), true);
        contratoRepository.save(contrato);

        return new DadosDetalhamentoContrato(contrato);
    }

    public DadosDetalhamentoContrato atualizarContratoCliente(DadosAtualizacaoContrato dados) {
        var contrato = contratoRepository.getReferenceById(dados.id());
        if(!contrato.getAtivo()) {
            throw new ValidacaoException("Este contrato encontra-se cancelado. Não é possível alterar os dados deste contrato.");
        }

        var cartao = new Cartao();
        if(dados.cartaoId() != null) {
            cartao = cartaoRepository.getReferenceById(dados.cartaoId());
            if(!cartao.getAtivo()) {
                throw new ValidacaoException("Este cartão não está disponível para novas contratações, escolha outro cartão!");
            }
        }
        contrato.atualizarInformacoes(dados, cartao);
        return new DadosDetalhamentoContrato(contrato);
    }
}

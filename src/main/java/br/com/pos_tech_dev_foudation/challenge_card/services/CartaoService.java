package br.com.pos_tech_dev_foudation.challenge_card.services;

import br.com.pos_tech_dev_foudation.challenge_card.infra.exception.ValidacaoException;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosCadastroCartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosDetalhamentoCartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosCadastroCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosDetalhamentoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository cartaoRepository;

    public DadosDetalhamentoCartao cadastrarCartao(DadosCadastroCartao dadosCadastroCartao) {
        var cartao = cartaoRepository.getCartaoByNomeAndBandeira(dadosCadastroCartao.nome(), dadosCadastroCartao.bandeira());

        if(cartao != null) {
            throw new ValidacaoException("Não foi possível cadastrar o cartão. Cartão já existe. Se for o caso, atualize os dados do cartão.");
        }

        var cartaoCadastro = new Cartao(dadosCadastroCartao);
        cartaoRepository.save(cartaoCadastro);

        return new DadosDetalhamentoCartao(cartaoCadastro);
    }
}

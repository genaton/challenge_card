package br.com.pos_tech_dev_foudation.challenge_card.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.CartaoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosCartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.ClienteRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosAtualizacaoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosCadastroCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosDetalhamentoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosListagemCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.Contrato;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.ContratoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar um cliente")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);

        // Retornar URI com DTO já existente
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        var resposta = new DadosDetalhamentoCliente(cliente); // Usa seu record

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    public ResponseEntity<Page<Cliente>> listar(
            @ParameterObject
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = clienteRepository.findAll(paginacao);
        return ResponseEntity.ok(page);

    }

    @GetMapping("ativos")
    @Operation(summary = "Listar clientes ativos")
    public ResponseEntity<Page<DadosListagemCliente>> listarAtivo(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = clienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("dados_parciais")
    @Operation(summary = "Listar por paginação")
    public ResponseEntity<Page<DadosListagemCliente>> listarParcial(Pageable paginacao) {
        var page = clienteRepository.findAll(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar dados de um cliente")
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @Parameter(name = "id", description = "ID do cliente a ser consultado", required = true)
    @GetMapping("/{id}")
    @Operation(summary = "Detalhar os dados de um clientes")
    public ResponseEntity detahar(@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir um clientes - Inativar")
    public ResponseEntity excluir(@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        cliente.excluir();

        var contrato = contratoRepository.findByCliente(cliente);
        if (contrato != null) {
            contrato.excluir();

            var cartao = contrato.getCartao();
            if (cartao != null) {
                cartao.excluir();
            }
        }
        return ResponseEntity.noContent().build();
    }
}
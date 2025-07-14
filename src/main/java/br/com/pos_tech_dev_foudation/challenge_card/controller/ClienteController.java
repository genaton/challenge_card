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
    private CartaoRepository cartaoRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        // 1. Criar Cliente
        var cliente = new Cliente(dados);
        clienteRepository.save(cliente);

        // 2. Gerar número do cartão com 19 dígitos
        // String numero = gerarNumeroCartao();

        // 3. Criar Cartão com número gerado

        // var cartao = new Cartao(
        // // numero,
        // dados.anuidadeCartao(),
        // dados.tipoCartao(),
        // dados.bandeiraCartao(),
        // dados.nomeCartao(),
        // true // ativo
        // );
        // cartaoRepository.save(cartao);
        Optional<Cartao> modelo = cartaoRepository.findByNomeAndTipoAndBandeiraAndAnuidade(
                dados.nomeCartao(),
                dados.tipoCartao(),
                dados.bandeiraCartao(),
                dados.anuidadeCartao());

        if (modelo.isEmpty()) {
            return ResponseEntity.badRequest().body("Modelo de cartão não disponível.");
        }

        var contrato = new Contrato(cliente, modelo.get(), Status.ATIVO, LocalDate.now(), true);
        contratoRepository.save(contrato);

        // 5. Retornar URI com DTO já existente
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        var resposta = new DadosDetalhamentoCliente(cliente); // Usa seu record

        return ResponseEntity.created(uri).body(resposta);
    }

    // private String gerarNumeroCartao() {
    // // Gera um número com 19 dígitos usando valor positivo
    // long valor = Math.abs(new Random().nextLong());
    // return String.format("%019d", valor);
    // }
    // apos o endpoint pode-se usar ?size="coloque aqui a quantidade de
    // registros"&page="coloque aqui no número da página desejada" lembrando que a
    // pág começa em 0
    // apos o endpoint pode-se usar ?sort="coloque aqui o atributo que vc deseja
    // como parametro de ordenação" exp. = nome
    // apos o endpoint pode-se usar ?sort="coloque aqui o atributo que vc deseja
    // como parametro de ordenação", desc para orde decrescente exp. = nome

    // // traz todos dados do cliente com paginacao de 10/pag ordenando os registro
    // por nome ascendente.
    // @Operation(summary = "consulta cliente")
    // @ApiResponse(responseCode = "200", description = "cliente encontrado")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou
    // ausentes")
    @GetMapping
    public ResponseEntity<Page<Cliente>> listar(
    @ParameterObject
    @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = clienteRepository.findAll(paginacao);
        return ResponseEntity.ok(page);

    }

    // @Operation(summary = "consulta clientes ativos")
    // @ApiResponse(responseCode = "200", description = "cliente encontrado")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou
    // ausentes")
    @GetMapping("ativos")
    public ResponseEntity<Page<DadosListagemCliente>> listarAtivo(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = clienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);

    }

    // personaliza os dados a serem exibidos por meio do record
    // DadosListagemCliente.
    // @Operation(summary = "consulta dados parciais cliente")
    // @ApiResponse(responseCode = "200", description = "cliente encontrado")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou
    // ausentes")
    @GetMapping("dados_parciais")
    public ResponseEntity<Page<DadosListagemCliente>> listarParcial(Pageable paginacao) {
        var page = clienteRepository.findAll(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);

    }

    // @Operation(summary = "atualiza dados cliente")
    // @ApiResponse(responseCode = "200", description = "dados do cliente
    // atualizados")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou
    // ausentes")
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));

    }

    // @Operation(summary = "detalha dados cliente")
    // @ApiResponse(responseCode = "200", description = "dados do cliente
    // detalhados")
    // @ApiResponse(responseCode = "404", description = "Dados inválidos ou
    // ausentes")
    @Parameter(name = "id", description = "ID do cliente a ser consultado", required = true)
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detahar(@PathVariable Long id) {

        var cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));

    }

    // @Operation(summary = "exclui dados cliente")
    // @ApiResponse(responseCode = "204", description = "dados do cliente
    // excluídos")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou
    // ausentes")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // repository.deleteById(id); //exclusão TOTAL DO REGISTRO
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
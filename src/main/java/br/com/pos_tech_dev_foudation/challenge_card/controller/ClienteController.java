package br.com.pos_tech_dev_foudation.challenge_card.controller;

import br.com.pos_tech_dev_foudation.challenge_card.services.ClienteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;
import br.com.pos_tech_dev_foudation.challenge_card.repository.ClienteRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosAtualizacaoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosCadastroCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosDetalhamentoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosListagemCliente;
import br.com.pos_tech_dev_foudation.challenge_card.repository.ContratoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar um cliente")
    @ApiResponse(responseCode = "201", description = "Cliente cadastrado")
    @ApiResponse(responseCode = "400", description = "Dados enviados estão inválidos.")
    @ApiResponse(responseCode = "500", description = "Dados enviados em desacordo com as regras de negócio.")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var dadosClienteCadastrado = clienteService.cadastrarCliente(dados);

        // Retornar URI com DTO já existente
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(dadosClienteCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(dadosClienteCadastrado);
    }

    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    @ApiResponse(responseCode = "200", description = "Clientes encontrados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    public ResponseEntity<Page<Cliente>> listar(
            @ParameterObject
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = clienteRepository.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("ativos")
    @Operation(summary = "Listar clientes ativos")
    @ApiResponse(responseCode = "200", description = "Cliente ativos encontrados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    public ResponseEntity<Page<DadosListagemCliente>> listarAtivo(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = clienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("dados_parciais")
    @Operation(summary = "Listar por paginação")
    @ApiResponse(responseCode = "200", description = "Clientes encontrados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    public ResponseEntity<Page<DadosListagemCliente>> listarParcial(Pageable paginacao) {
        var page = clienteRepository.findAll(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar dados de um cliente")
    @ApiResponse(responseCode = "201", description = "Cliente atualizado")
    @ApiResponse(responseCode = "500", description = "Dados inválidos ou ausentes")
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = clienteRepository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @Parameter(name = "id", description = "ID do cliente a ser consultado", required = true)
    @GetMapping("/{id}")
    @Operation(summary = "Consultar um cliente")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    public ResponseEntity detahar(@PathVariable Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir um clientes - Inativar")
    @ApiResponse(responseCode = "202", description = "Cliente excluído")
    @ApiResponse(responseCode = "500", description = "Dados inválidos ou ausentes")
    public ResponseEntity excluir(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
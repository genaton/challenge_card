package br.com.pos_tech_dev_foudation.challenge_card.controller;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.*;
import br.com.pos_tech_dev_foudation.challenge_card.repository.ContratoRepository;
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

import br.com.pos_tech_dev_foudation.challenge_card.services.ContratoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("contrato")
public class ContratoController {

    @Autowired
    private ContratoRepository repository;

    @Autowired
    private ContratoService contratoService;

    @Operation(summary = "Cadastrar novo contrato")
    @PostMapping
    @Transactional
    @ApiResponse(responseCode = "201", description = "Contrato cadastrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Dados inválidos ou ausentes")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroContrato dados, UriComponentsBuilder uriBuilder) {
        var dadosContrato = contratoService.contratarCartaoCliente(dados);

        var uri = uriBuilder.path("/contrato/{id}").buildAndExpand(dadosContrato.id()).toUri();
        return ResponseEntity.created(uri).body(dadosContrato);
    }

    @Operation(summary = "Listar todos os contrato")
    @GetMapping
    @ApiResponse(responseCode = "200", description = "Contratos listados")
    @ApiResponse(responseCode = "400", description = "Nenhum contrato encontrado")
    public ResponseEntity<Page<Contrato>> listar(
            @ParameterObject
            @PageableDefault(size = 10, sort = "status") Pageable paginacao) {
        var page = repository.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Listar dados parciais do contrato")
    @GetMapping("dados_parciais")
    @ApiResponse(responseCode = "200", description = "Contratos encontrados")
    @ApiResponse(responseCode = "400", description = "Nenhum contrato encontrado")
    public ResponseEntity<Page<DadosListagemContrato>> listarParcial(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemContrato::new);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Listar contratos ativos")
    @GetMapping("ativos")
    @ApiResponse(responseCode = "200", description = "Contratos ativos encontrados")
    @ApiResponse(responseCode = "400", description = "Nenhum contrato ativo encontrado")
    public ResponseEntity<Page<DadosListagemContrato>> listarAtivo(
            @PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemContrato::new);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Atualizar dados do contrato")
    @ApiResponse(responseCode = "201", description = "Dados do contrato atualizado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoContrato dados) {
        return ResponseEntity.ok(contratoService.atualizarContratoCliente(dados));
    }

    @Operation(summary = "Consulta contrato por id")
    @ApiResponse(responseCode = "200", description = "Dados do contrato detalhados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("/{id}")
    public ResponseEntity<DadosContrato> detahar(@PathVariable Long id) {
        var contrato = repository.getReferenceById(id);
        System.out.println("Elton : " + contrato.getId());
        return ResponseEntity.ok(new DadosContrato(contrato));
    }

    @Operation(summary = "Cancelar contrato")
    @ApiResponse(responseCode = "204", description = "Contrato cancelado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var contrato = repository.getReferenceById(id);
        contrato.excluir();
        return ResponseEntity.noContent().build();
    }

}

package br.com.pos_tech_dev_foudation.challenge_card.controller;

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

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.Contrato;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.ContratoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.DadosAtualizacaoContrato;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.DadosContrato;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.DadosDetalhamentoContrato;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.DadosListagemContrato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("contratos")
public class ContratoController {

    @Autowired
    private ContratoRepository repository;

    @Operation(summary = "cadastra novo contrato")
    @ApiResponse(responseCode = "201", description = "contrato cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    // @Parameter(name = "id", description = "ID do cliente a ser consultado",
    // required = true)
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosContrato dados, UriComponentsBuilder uriBuilder) {
        var contrato = new Contrato(dados);
        repository.save(contrato);

        var uri = uriBuilder.path("/contrato/{id}").buildAndExpand(contrato.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoContrato(contrato));
        // System.out.println(dados);

    }

    // traz todos dados do cliente com paginacao de 10/pag ordenando os registro por
    // nome ascendente.
    @Operation(summary = "consulta contrato")
    @ApiResponse(responseCode = "200", description = "contrato encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping
    public ResponseEntity<Page<Contrato>> listar(@PageableDefault(size = 10, sort = "status") Pageable paginacao) {
        var page = repository.findAll(paginacao);
        return ResponseEntity.ok(page);

    }

    // personaliza os dados a serem exibidos por meio do record
    // DadosListagemCliente.
    @Operation(summary = "consulta dados parciais contrato")
    @ApiResponse(responseCode = "200", description = "contrato encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("dados_parciais")
    public ResponseEntity<Page<DadosListagemContrato>> listarParcial(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemContrato::new);
        return ResponseEntity.ok(page);

    }

    @Operation(summary = "consulta contratos ativos")
    @ApiResponse(responseCode = "200", description = "contratos ativos encontrados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("ativos")
    public ResponseEntity<Page<DadosListagemContrato>> listarAtivo(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemContrato::new);
        return ResponseEntity.ok(page);

    }

    @Operation(summary = "atualiza dados do contrato")
    @ApiResponse(responseCode = "200", description = "dados do contrato atualizado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoContrato dados) {
        var contrato = repository.getReferenceById(dados.id());
        contrato.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoContrato(contrato));

    }

    @Operation(summary = "detalha dados contrato")
    @ApiResponse(responseCode = "200", description = "dados do contrato detalhados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detahar(@PathVariable Long id) {
        
        var contrato = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoContrato(contrato));

    }

    @Operation(summary = "exclui dados do contrato")
    @ApiResponse(responseCode = "204", description = "dados do contrato excluídos")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // repository.deleteById(id); //exclusão TOTAL DO REGISTRO
        var contrato = repository.getReferenceById(id);
        contrato.excluir();
        return ResponseEntity.noContent().build();

    }

}

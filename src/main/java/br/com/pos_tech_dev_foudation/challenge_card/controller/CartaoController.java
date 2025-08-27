package br.com.pos_tech_dev_foudation.challenge_card.controller;

import br.com.pos_tech_dev_foudation.challenge_card.services.CartaoService;
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

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.repository.CartaoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosAtualizacaoCartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosCadastroCartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosDetalhamentoCartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosListagemCartao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cartoes")
public class CartaoController {
    @Autowired
    private CartaoRepository repository;
    @Autowired
    private CartaoService cartaoService;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar um cartão")
    @ApiResponse(responseCode = "201", description = "Cartao cadastrado")
    @ApiResponse(responseCode = "400", description = "Dados enviados estáo inválidos.")
    @ApiResponse(responseCode = "500", description = "Dados enviados em desacordo com as regras de negócio.")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCartao dados, UriComponentsBuilder uriBilder) {
        var dadosCartaoCadastrado = cartaoService.cadastrarCartao(dados);

        // Retornar URI com DTO já existente
        var uri = uriBilder.path("/cartao/{id}").buildAndExpand(dadosCartaoCadastrado.id()).toUri();
        return ResponseEntity.created(uri).body(dadosCartaoCadastrado);
    }

    @Operation(summary = "Consultar cartões cadastrados")
    @ApiResponse(responseCode = "200", description = "Cartoes encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping
    public ResponseEntity<Page<Cartao>> listar(
            @ParameterObject
            @PageableDefault(size = 10, sort = "id") Pageable paginacao) {
        var page = repository.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Consultar cartões ativos")
    @ApiResponse(responseCode = "200", description = "Cartoes ativos encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("ativos")
    public ResponseEntity<Page<DadosListagemCartao>> listarAtivo(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCartao::new);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Consultar dados parciais do cartão")
    @ApiResponse(responseCode = "200", description = "Cartao encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("dados_parciais")
    public ResponseEntity<Page<DadosListagemCartao>> listarParcial(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemCartao::new);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Atualizar dados de um cartão")
    @ApiResponse(responseCode = "201", description = "Dados do cartao atualizado")
    @ApiResponse(responseCode = "400", description = "Dados enviados estáo inválidos.")
    @ApiResponse(responseCode = "500", description = "Dados enviados em desacordo com as regras de negócio.")
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCartao dados) {
        var cartao = repository.getReferenceById(dados.id());
        cartao.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCartao(cartao));
    }

    @Operation(summary = "Detalhar dados de um cartão")
    @ApiResponse(responseCode = "200", description = "Dados do cartao detalhados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("/{id}")
    public ResponseEntity detahar(@PathVariable Long id) {
        var cartao = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCartao(cartao));
    }

    @Operation(summary = "Inativar um cartão")
    @ApiResponse(responseCode = "204", description = "Dados do cartao excluídos")
    @ApiResponse(responseCode = "400", description = "Dados enviados estão inválidos.")
    @ApiResponse(responseCode = "500", description = "Dados enviados em desacordo com as regras de negócio.")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var cartao = repository.getReferenceById(id);
        cartao.excluir();
        return ResponseEntity.noContent().build();
    }
}

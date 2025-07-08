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

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.CartaoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosAtualizacaoCartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.DadosCartao;
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

    // @Operation(summary = "inclusão de novo cartao")
    // @ApiResponse(responseCode = "201", description = "novo cartao incluído")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCartao dados, UriComponentsBuilder uriBilder) {
        var cartao = new Cartao(dados);
        repository.save(cartao);

        var uri = uriBilder.path("/cartao/{id}").buildAndExpand(cartao.getId()).toUri();

        return ResponseEntity.created(uri).body((new DadosDetalhamentoCartao(cartao)));

        // System.out.println(dados);

    }

    // @Operation(summary = "consulta cartao")
    // @ApiResponse(responseCode = "200", description = "cartao encontrado")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping
    public ResponseEntity<Page<Cartao>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = repository.findAll(paginacao);
        return ResponseEntity.ok(page);

    }

    // @Operation(summary = "consulta cartoes ativos")
    // @ApiResponse(responseCode = "200", description = "cartoes ativos encontrados")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("ativos")
    public ResponseEntity<Page<DadosListagemCartao>> listarAtivo(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCartao::new);
        return ResponseEntity.ok(page);

    }

    // personaliza os dados a serem exibidos por meio do record
    // DadosListagemCliente.
    @Operation(summary = "consulta dados parciais cartao")
    @ApiResponse(responseCode = "200", description = "cartao encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("dados_parciais")
    public ResponseEntity<Page<DadosListagemCartao>> listarParcial(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemCartao::new);

        return ResponseEntity.ok(page);

    }

    // @Operation(summary = "atualiza dados cartao")
    // @ApiResponse(responseCode = "201", description = "dados do cartao atualizado")
    // @ApiResponse(responseCode = "200", description = "Dados inválidos ou ausentes")
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCartao dados) {
        var cartao = repository.getReferenceById(dados.id());
        cartao.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCartao(cartao));

    }

    // @Operation(summary = "detalha dados cartao")
    // @ApiResponse(responseCode = "200", description = "dados do cartao detalhados")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detahar(@PathVariable Long id) {

        var cartao = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCartao(cartao));

    }

    // exclusão TOTAL DO REGISTRO
    // @Operation(summary = "exclui dados do cartao")
    // @ApiResponse(responseCode = "204", description = "dados do cartao excluídos")
    // @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // repository.deleteById(id); //exclusão TOTAL DO REGISTRO
        var cartao = repository.getReferenceById(id);
        cartao.excluir();
        return ResponseEntity.noContent().build();

    }

}

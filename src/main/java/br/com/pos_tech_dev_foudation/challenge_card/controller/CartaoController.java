package br.com.pos_tech_dev_foudation.challenge_card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.CartaoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.DadosAtualizacaoCartao;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.DadosCartao;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.DadosListagemCartao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cartoes")
public class CartaoController {
    @Autowired
    private CartaoRepository repository;

    @Operation(summary = "inclusão de novo cartao")
    @ApiResponse(responseCode = "201", description = "novo cartao incluído")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCartao dados){
        repository.save(new Cartao(dados));

        // System.out.println(dados);


    }

     
    @Operation(summary = "consulta cartao")
    @ApiResponse(responseCode = "201", description = "cartao encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping
    public Page<Cartao> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        return repository.findAll(paginacao);

    }

    @Operation(summary = "consulta cartoes ativos")
    @ApiResponse(responseCode = "201", description = "cartoes ativos encontrados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("ativos")
    public Page<DadosListagemCartao> listarAtivo(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemCartao::new);

    }

    

    // personaliza os dados a serem exibidos por meio do record
    // DadosListagemCliente.
    @Operation(summary = "consulta dados parciais cartao")
    @ApiResponse(responseCode = "201", description = "cartao encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("dados_parciais")
    public Page<DadosListagemCartao> listarParcial(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemCartao::new);

    }
     @Operation(summary = "atualiza dados cartao")
    @ApiResponse(responseCode = "201", description = "dados do cartao atualizado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoCartao dados) {
        var cartao = repository.getReferenceById(dados.id());
        cartao.atualizarInformacoes(dados);
        

    }

    //exclusão TOTAL DO REGISTRO 
    @Operation(summary = "exclui dados do cartao")
    @ApiResponse(responseCode = "201", description = "dados do cartao excluídos")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        // repository.deleteById(id); //exclusão TOTAL DO REGISTRO 
        var cartao = repository.getReferenceById(id);
        cartao.excluir();
        

    }

}

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

import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.Cliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.ClienteRepository;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosAtualizacaoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosCadastroCliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosDetalhamentoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosListagemCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController

@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Operation(summary = "cadastra novo cliente")
    @ApiResponse(responseCode = "201", description = "cliente cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    // @Parameter(name = "id", description = "ID do cliente a ser consultado",
    // required = true)
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente (dados);
        repository.save(cliente);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body((new DadosDetalhamentoCliente(cliente)));

        // System.out.println(dados);

    }

    //apos o endpoint pode-se usar ?size="coloque aqui a quantidade de registros"&page="coloque aqui no número da página desejada" lembrando que a pág começa em 0
    //apos o endpoint pode-se usar ?sort="coloque aqui o atributo que vc deseja como parametro de ordenação" exp. = nome
    //apos o endpoint pode-se usar ?sort="coloque aqui o atributo que vc deseja como parametro de ordenação", desc para orde decrescente exp. = nome
    
    
    // traz todos dados do cliente com paginacao de 10/pag ordenando os registro por nome ascendente. 
    @Operation(summary = "consulta cliente")
    @ApiResponse(responseCode = "200", description = "cliente encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping
    public ResponseEntity<Page<Cliente>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = repository.findAll(paginacao);
        return ResponseEntity.ok(page);

    }

    @Operation(summary = "consulta clientes ativos")
    @ApiResponse(responseCode = "200", description = "cliente encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("ativos")
    public ResponseEntity<Page<DadosListagemCliente>> listarAtivo(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);

    }

    // personaliza os dados a serem exibidos por meio do record
    // DadosListagemCliente.
    @Operation(summary = "consulta dados parciais cliente")
    @ApiResponse(responseCode = "200", description = "cliente encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("dados_parciais")
    public ResponseEntity<Page<DadosListagemCliente>> listarParcial(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);

    }

    @Operation(summary = "atualiza dados cliente")
    @ApiResponse(responseCode = "200", description = "dados do cliente atualizados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
        var cliente = repository.getReferenceById(dados.id());
        cliente.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
        

    }

    @Operation(summary = "exclui dados cliente")
    @ApiResponse(responseCode = "204", description = "dados do cliente excluídos")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // repository.deleteById(id); //exclusão TOTAL DO REGISTRO 
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
        return ResponseEntity.noContent().build();
        

    }


    

}

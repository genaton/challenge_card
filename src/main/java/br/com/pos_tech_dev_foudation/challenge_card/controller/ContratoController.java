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

import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.DadosAtualizacaoCartao;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cartao.DadosListagemCartao;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.Cliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosAtualizacaoCliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosCadastroCliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.cliente.DadosListagemCliente;
import br.com.pos_tech_dev_foudation.challenge_card.controller.contrato.Contrato;
import br.com.pos_tech_dev_foudation.challenge_card.controller.contrato.ContratoRepository;
import br.com.pos_tech_dev_foudation.challenge_card.controller.contrato.DadosAtualizacaoContrato;
import br.com.pos_tech_dev_foudation.challenge_card.controller.contrato.DadosContrato;
import br.com.pos_tech_dev_foudation.challenge_card.controller.contrato.DadosListagemContrato;
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
    public void cadastrar(@RequestBody @Valid DadosContrato dados) {
        repository.save(new Contrato(dados));

        // System.out.println(dados);

    }
        
    
    // traz todos dados do cliente com paginacao de 10/pag ordenando os registro por nome ascendente. 
    @Operation(summary = "consulta contrato")
    @ApiResponse(responseCode = "201", description = "contrato encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping
    public Page<Contrato> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        return repository.findAll(paginacao);

    }

    // personaliza os dados a serem exibidos por meio do record
    // DadosListagemCliente.
    @Operation(summary = "consulta dados parciais contrato")
    @ApiResponse(responseCode = "201", description = "contrato encontrado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("dados_parciais")
    public Page<DadosListagemContrato> listarParcial(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemContrato::new);

    }

    @Operation(summary = "consulta contratos ativos")
    @ApiResponse(responseCode = "201", description = "contratos ativos encontrados")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @GetMapping("ativos")
    public Page<DadosListagemContrato> listarAtivo(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemContrato::new);

    }

      @Operation(summary = "atualiza dados do contrato")
    @ApiResponse(responseCode = "201", description = "dados do contrato atualizado")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoContrato dados) {
        var contrato = repository.getReferenceById(dados.id());
        contrato.atualizarInformacoes(dados);
        

    }

    //exclusão TOTAL DO REGISTRO 
    @Operation(summary = "exclui dados do contrato")
    @ApiResponse(responseCode = "201", description = "dados do contrato excluídos")
    @ApiResponse(responseCode = "400", description = "Dados inválidos ou ausentes")
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        // repository.deleteById(id); //exclusão TOTAL DO REGISTRO 
        var contrato = repository.getReferenceById(id);
        contrato.excluir();
        

    }
    

    

}

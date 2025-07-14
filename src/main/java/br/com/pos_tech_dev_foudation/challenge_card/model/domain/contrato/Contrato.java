package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

// @Schema(description = "Representa um contrato cadastrado no sistema")

@Table(name = "contrato")
@Entity(name = "Contrato")

public class Contrato {

    // @Schema(description = "Identificador único do contrato", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    // @Schema(description = "Estado do contrato", example = "ATIVO, CANCELADO")
    @Enumerated(EnumType.STRING)
    private Status status;

    // @Schema(description = "Data de início do contrato no formato AAAA-MM-DD", example = "2000-01-01")
    private LocalDate data;

    //  @Schema(description = "Indica se o contrato está ou não ativo", example = "0 - inativo ou 1 - ativo")
    private Boolean ativo;

    public Contrato(DadosContrato dados) {
        this.status = dados.status();
        this.data = dados.data();
        this.ativo = true;
    }

    public Contrato(Cliente cliente, Cartao cartao, Status status, LocalDate data, Boolean ativo) {
    this.cliente = cliente;
    this.cartao = cartao;
    this.status = status;
    this.data = data;
    this.ativo = ativo;
}
     public void atualizarInformacoes(DadosAtualizacaoContrato dados) {
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (dados.data() != null) {

            this.data = dados.data();
        }

        
    }

    public void excluir() {
        this.ativo = false;
    }

}

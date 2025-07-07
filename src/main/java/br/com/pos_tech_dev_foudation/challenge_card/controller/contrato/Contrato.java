package br.com.pos_tech_dev_foudation.challenge_card.controller.contrato;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Schema(description = "Representa um contrato cadastrado no sistema")

@Table(name = "contrato")
@Entity(name = "Contrato")

public class Contrato {

    @Schema(description = "Identificador único do contrato", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Estado do contrato", example = "ATIVO, CANCELADO")
    private Status status;

    @Schema(description = "Data de início do contrato no formato AAAA-MM-DD", example = "2000-01-01")
    private LocalDate data;

     @Schema(description = "Indica se o contrato está ou não ativo", example = "0 - inativo ou 1 - ativo")
    private Boolean ativo;

    public Contrato(DadosContrato dados) {
        this.status = dados.status();
        this.data = dados.data();
        this.ativo = true;
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

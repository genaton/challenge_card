package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.DadosAtualizacaoCliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

// @Schema(description = "Representa um novo cartão")
@Table(name = "cartao")
@Entity(name = "Cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Schema(description = "Identificador único do cartão", example = "1")
    private Long id;

    // @Schema(description = "número do cartão com 19 dígitos", example = "0123456789012345678")
    // private String numeroCartao;

    // @Schema(description = "valor da anuidade", example = "100.11")
    private Double anuidade;

    private String tipo;

    private String bandeira;

    private String nome;

    // @Enumerated(EnumType.STRING)
    // // @Schema(description = "tipo de cartão", example = "CREDITO ou DEBITO")
    // private Tipo tipo;

    // @Enumerated(EnumType.STRING)
    // // @Schema(description = "bandeira do cartão", example = "VISA, MASTERCAR ou ELO")
    // private Bandeira bandeira;

    // @Enumerated(EnumType.STRING)
    // // @Schema(description = "nome do cartão", example = "BLACK, PLATINUM ou GOLD")
    // private Nome nome;

    // @Schema(description = "Indica se o cartao está ou não ativo", example = "0 - inativo ou 1 - ativo")
    private Boolean ativo;

    public Cartao(DadosCartao dados) {
        this.ativo = true;
        // this.numeroCartao = dados.numeroCartao();
        this.anuidade = dados.anuidade();
        this.tipo = dados.tipo();
        this.bandeira = dados.bandeira();
        this.nome = dados.nome();

    }

    public Cartao(Double anuidade, String tipo, String bandeira, String nome, Boolean ativo) {
    this.anuidade = anuidade;
    this.tipo = tipo;
    this.bandeira = bandeira;
    this.nome = nome;
    this.ativo = ativo;
}

     public void atualizarInformacoes(DadosAtualizacaoCartao dados) {
        // if (dados.numeroCartao() != null) {
        //     this.numeroCartao = dados.numeroCartao();
        // }
        if (dados.anuidade() != null) {

            this.anuidade = dados.anuidade();
        }

        if (dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
        if (dados.bandeira() != null) {
            this.bandeira = dados.bandeira();
        }
        

    }

    public void excluir() {
        this.ativo = false;
    }

}

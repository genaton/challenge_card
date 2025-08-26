package br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao;

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
@Table(name = "cartao")
@Entity(name = "Cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double anuidade;

    private String tipo;

    @Enumerated(EnumType.STRING)
    private Bandeira bandeira;

    private String nome;

    private Boolean ativo;

    public Cartao(DadosCadastroCartao dados) {
        this.bandeira = dados.bandeira();
        this.tipo = dados.tipo();
        this.nome = dados.nome();
        this.anuidade = dados.anuidade();
        this.ativo = true;
    }

     public void atualizarInformacoes(DadosAtualizacaoCartao dados) {
        if (dados.anuidade() != null) {
            this.anuidade = dados.anuidade();
        }
        if (dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
        if (dados.bandeira() != null) {
            this.bandeira = dados.bandeira();
        }
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}

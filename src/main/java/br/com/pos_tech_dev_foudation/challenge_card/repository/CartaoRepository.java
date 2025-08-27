package br.com.pos_tech_dev_foudation.challenge_card.repository;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Bandeira;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Page<Cartao> findAllByAtivoTrue(Pageable paginacao);

    Object getCartaoByNomeAndBandeira(@NotBlank String nome, @NotNull Bandeira bandeira);
}

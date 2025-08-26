package br.com.pos_tech_dev_foudation.challenge_card.repository;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cartao.Cartao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Page<Cartao> findAllByAtivoTrue(Pageable paginacao);

}

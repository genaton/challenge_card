package br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato,Long> {
    Page<Contrato> findAllByAtivoTrue(Pageable paginacao);

}

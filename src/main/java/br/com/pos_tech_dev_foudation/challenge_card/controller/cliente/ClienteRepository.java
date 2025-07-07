package br.com.pos_tech_dev_foudation.challenge_card.controller.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Page<Cliente> findAllByAtivoTrue(Pageable paginacao);

}

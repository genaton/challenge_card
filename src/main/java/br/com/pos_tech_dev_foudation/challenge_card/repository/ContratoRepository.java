package br.com.pos_tech_dev_foudation.challenge_card.repository;

import br.com.pos_tech_dev_foudation.challenge_card.model.domain.contrato.Contrato;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.pos_tech_dev_foudation.challenge_card.model.domain.cliente.Cliente;


public interface ContratoRepository extends JpaRepository<Contrato,Long> {
    Page<Contrato> findAllByAtivoTrue(Pageable paginacao);
    Contrato findByCliente(Cliente cliente);

    Contrato getContratoesByCartao_Id(@NotNull Long aLong);
}

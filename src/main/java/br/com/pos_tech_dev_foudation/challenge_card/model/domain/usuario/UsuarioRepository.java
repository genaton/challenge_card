package br.com.pos_tech_dev_foudation.challenge_card.model.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // UserDatails indById(String login);

}

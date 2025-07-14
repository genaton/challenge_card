package br.com.pos_tech_dev_foudation.challenge_card.model.domain.usuario;

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

// @Schema(description = "Representa um cliente cadastrado no sistema")

@Table(name = "usuario")
@Entity(name = "Usuario")
// public class Usuario implements UserDatails { // springSecurity -> implementar os metodos e alterar seus returns para true nos getPassword -> return senha no getUsername -> return login
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

// um dos métodos de assinatura da inteface que deve ter, pelo menos, um perfil de usuário qualquer para compilar.
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthority(){
    //     return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    // }
//ATENCAO, PARA IMPLEMENTAR O SPRING SCURITY DEVE-SE CRIAR A TABELA USUÁRIO E CADASTRA PELO MENOS UM PARA REALIZAR OS TESTES:
// insert into usuarios values (1, 'ana.souza@voll.med', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
// O hash é a senha 123456 criptografada em BCrypt que foi o método de criptografia configurado nesta aplicacao. 
}

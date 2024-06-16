package br.com.uricer.programacaoweb.library.domain;

import br.com.uricer.programacaoweb.library.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="users")
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="is_admin")
    private boolean isAdmin;

    public UserDTO toDTO() {
        return new UserDTO(id,name,password,email,isAdmin);
    }

}

package br.com.uricer.programacaoweb.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private boolean isAdmin;


    public UserDTO(Integer id, String name, String password, String email, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}

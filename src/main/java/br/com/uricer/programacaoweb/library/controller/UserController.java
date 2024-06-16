package br.com.uricer.programacaoweb.library.controller;

import br.com.uricer.programacaoweb.library.dto.UserDTO;
import br.com.uricer.programacaoweb.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http:localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userData){
        this.userService.register(userData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOList = this.userService.getAllUsers();
        return new ResponseEntity<>(userDTOList,HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        this.userService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

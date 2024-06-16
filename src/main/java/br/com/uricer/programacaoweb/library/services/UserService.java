package br.com.uricer.programacaoweb.library.services;

import br.com.uricer.programacaoweb.library.domain.User;
import br.com.uricer.programacaoweb.library.dto.UserDTO;
import br.com.uricer.programacaoweb.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void register(UserDTO userData) {
        if (!isValidEmail(userData.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        User user = new User();
        user.setAdmin(userData.isAdmin());
        user.setName(userData.getName());
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());
        user.toDTO();
        this.userRepository.save(user);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && (email.endsWith(".com") || email.endsWith(".net") || email.endsWith(".org") || email.endsWith(".com.br"));
    }

    public List<UserDTO> getAllUsers() {

        List<User> userList = this.userRepository.findAll();
        return userList.stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getPassword(), u.getEmail(), u.isAdmin()))
                .toList();
    }

    public void deleteByID(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User does not exist!");
        }
        this.userRepository.deleteById(user.get().getId());
    }
}

package com.example.crud.crudusuarios.controller;

import com.example.crud.crudusuarios.dto.CreateUserDto;
import com.example.crud.crudusuarios.entity.User;
import com.example.crud.crudusuarios.exceptions.InvalidCredentialsException;
import com.example.crud.crudusuarios.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class userController {

    //Posso usar o -> @ @Autowired inves do constructor
    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return ResponseEntity.ok("Usuário Criado Com Sucesso !");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId,
                                           @RequestBody @Valid CreateUserDto updateUserDto) {
        userService.updateUser(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CreateUserDto loginUser) {
        boolean isAuthenticated = userService.authenticateUser(loginUser.getEmail(), loginUser.getPassword());

        if(isAuthenticated) {
            return ResponseEntity.ok("Login Bem Sucedido");

        } else {
            throw new InvalidCredentialsException();
        }
    }

}

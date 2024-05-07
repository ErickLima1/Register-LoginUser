package com.example.crud.crudusuarios.service;

import com.example.crud.crudusuarios.dto.CreateUserDto;
import com.example.crud.crudusuarios.entity.User;
import com.example.crud.crudusuarios.exceptions.EmailAlreadyExistsException;
import com.example.crud.crudusuarios.exceptions.IdNotExistsException;
import com.example.crud.crudusuarios.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    //Posso usar o -> @ @Autowired inves do constructor
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    public UUID createUser(CreateUserDto createUserDto)  {

        if (userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        String encryptedPassword = encoder.encode(createUserDto.getPassword());

        //Tentar Entender Como Funciona isso aqui -> []
        //Dto -> Entity
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                encryptedPassword,
                Instant.now(),
                null);

        var userSaved =  userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public void updateUser(String userId, CreateUserDto updateUserDto) {
        UUID id = UUID.fromString(userId);
        Optional<User> optionalUser = userRepository.findById(id);

        if (userRepository.existsByEmail(updateUserDto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Atualize os detalhes do usuário com base nos dados do DTO
            BeanUtils.copyProperties(updateUserDto, user);
            userRepository.save(user);
        } else {
            throw new IdNotExistsException();
        }
    }

    public void deleteUserById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if(userExists) {
            userRepository.deleteById(id);

        } else {
            throw new IdNotExistsException();
        }
    }

    public boolean authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return encoder.matches(password, user.getPassword());

        } else {
            return false;
        }
    }
}

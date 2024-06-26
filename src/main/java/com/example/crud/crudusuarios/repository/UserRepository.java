    package com.example.crud.crudusuarios.repository;
    
    import com.example.crud.crudusuarios.entity.User;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;
    import java.util.UUID;
    @Repository
    public interface UserRepository extends JpaRepository<User, UUID> {
        boolean existsByEmail(String email);
        Optional<User> findByEmail(String email);
    }

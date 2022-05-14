package com.twitter.gateway.repository;

import com.twitter.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String usename);

    Optional<User> findByEmail(String email);

    @Query("Select u from User u where u.username like %:username%")
    List<User> findByUsernameContaining(String username);
}

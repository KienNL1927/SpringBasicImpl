package com.example.sql.repository;

import com.example.sql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // JPQL query using @Query
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    // Named parameters example
    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findByStatus(@Param("status") String status);

    // Native SQL example
    @Query(value = "SELECT * FROM user WHERE name = ?1", nativeQuery = true)
    List<User> findNativeByName(String name);
}

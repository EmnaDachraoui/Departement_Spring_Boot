package com.emna.departements.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emna.departements.entities.User;
public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);
}
package com.edivanio.curso.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edivanio.curso.boot.model.User;

public interface UserRepository extends JpaRepository<User, Long>{


}

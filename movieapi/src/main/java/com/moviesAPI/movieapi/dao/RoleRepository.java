package com.moviesAPI.movieapi.dao;

import com.moviesAPI.movieapi.Entity.Role;
import com.moviesAPI.movieapi.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}

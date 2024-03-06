package com.example.nnpiacv02.repository;

import com.example.nnpiacv02.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAppUserByActive(Boolean active);

    @Query("SELECT u FROM app_user u JOIN u.roles r WHERE r.name = :roleName")
    List<AppUser> findUsersByRole(@Param("roleName") String roleName);

    AppUser findByUsername(String username);

}

package com.spring.notes.services.repository;

import com.spring.notes.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r.role from Role r where r.id = :id")
    String findRoleNameByRoleId(Long id);
}

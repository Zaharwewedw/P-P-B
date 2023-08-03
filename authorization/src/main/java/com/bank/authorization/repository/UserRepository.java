package com.bank.authorization.repository;

import com.bank.authorization.model.Users;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Users (password, role, profile_id) VALUES (:password, :role, :profileId)", nativeQuery = true)
    void saveNoJPA(@Param("password") String password,
                        @Param("role") String role,
                        @Param("profileId") int profileId);
}

package com.renga.money.manager.common.repositories;

import com.renga.money.manager.common.models.entities.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {
    UserDetailsEntity findByEmail(String email);
}

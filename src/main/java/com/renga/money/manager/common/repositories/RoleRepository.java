package com.renga.money.manager.common.repositories;

import com.renga.money.manager.common.models.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}

package com.mool_backend.repository;

import com.mool_backend.model.MemoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<MemoryEntity, String> {
}

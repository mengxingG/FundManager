package com.example.fundmanager.dao;

import com.example.fundmanager.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {
    @Query("SELECT f FROM Fund f WHERE f.name=?1")
    Optional<Fund> findFundByName(String name);
}

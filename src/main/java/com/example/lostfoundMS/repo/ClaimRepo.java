package com.example.lostfoundMS.repo;

import com.example.lostfoundMS.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepo extends JpaRepository<Claim, Long> {
    List<Claim> findByItemId(Long itemId);
}
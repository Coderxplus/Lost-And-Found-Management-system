package com.example.lostfoundMS.services;

import com.example.lostfoundMS.entities.*;
import com.example.lostfoundMS.repo.ClaimRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepo claimRepository;

    public ClaimService(ClaimRepo claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Claim createClaim(String message, User user, Item item) {
        Claim claim = new Claim(message, user, item);
        return claimRepository.save(claim);
    }

    public List<Claim> getClaimsByItemId(Long itemId) {
        return claimRepository.findByItemId(itemId);
    }

    public Claim updateStatus(Long claimId, ClaimStatus status) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(status);
        return claimRepository.save(claim);
    }
}
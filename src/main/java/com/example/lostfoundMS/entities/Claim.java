package com.example.lostfoundMS.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message; // user explains why it's theirs

    @Enumerated(EnumType.STRING)
    private ClaimStatus status; // PENDING, APPROVED, REJECTED

    private LocalDateTime createdAt;

    // The person making the claim
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // The item being claimed
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Claim() {}

    public Claim(String message, User user, Item item) {
        this.message = message;
        this.user = user;
        this.item = item;
        this.status = ClaimStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // getters and setters
}
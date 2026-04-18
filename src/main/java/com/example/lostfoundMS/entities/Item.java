package com.example.lostfoundMS.entities;

import jakarta.persistence.*;
import java.util.List;

import java.time.LocalDateTime;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    @Enumerated(EnumType.STRING)
    private ItemType type; // LOST or FOUND
    private LocalDateTime datePosted;
    @OneToMany(mappedBy = "item")
    private List<Claim> claims;

    // Who posted it
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Item() {
    }

    public Item(String name, String description, String location, ItemType type) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.datePosted = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }
}
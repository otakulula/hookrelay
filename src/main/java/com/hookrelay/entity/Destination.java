package com.hookrelay.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "destinations")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 2048)
    private String url;

    private String description;

    @Column(nullable = false)
    private boolean active = true;

    @ElementCollection
    @CollectionTable(
            name = "destination_event_types",
            joinColumns = @JoinColumn(name = "destination_id")
    )

    @Column(name = "event_type")
    private Set<String> subscribedEvents = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    // Required by JPA
    public Destination() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<String> getSubscribedEvents() {
        return subscribedEvents;
    }

    public void setSubscribedEvents(Set<String> subscribedEvents) {
        this.subscribedEvents = subscribedEvents;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
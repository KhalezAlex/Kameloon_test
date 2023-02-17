package org.klozevitz.kameleoon_test.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_t")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "registered")
    private LocalDate registered;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Quote> quotes;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }

    public Set<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }

    // nullObject for further usages
    public User() {
        this.id = -1L;
        this.name = "undefined";
        this.email = "undefined";
        this.password = "undefined";
        this.registered = LocalDate.of(1000, 1, 1);
        this.quotes = new HashSet<>();
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = LocalDate.now();
        this.quotes = new HashSet<>();
    }

    public User(String name, String password) {
        this.name = name;
        this.email = "undefined";
        this.password = password;
        this.registered = LocalDate.now();
        this.quotes = new HashSet<>();
    }

    @Override
    public String toString() {
        return "{name='" + name +
                "', email='" + email +
                "', registered=" + registered + '}';
    }
}

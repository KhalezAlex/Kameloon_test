package org.klozevitz.kameloon_test.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "quote_t")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

// perhaps final variable "= LocalDate.now()"
    @Column(name = "updated")
    private LocalDate updated;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "quote")
    @JsonIgnore
    Set<Like> likes;



// nullObject for Optional<Quote> database responses
    public Quote() {
        this.id = -1L;
        this.content = "undefined";
        this.updated = LocalDate.of(1000, 1, 1);
        this.user = new User();
        this.likes = new HashSet<>();
    }

    public Quote(String content, User user) {
        this.content = content;
        this.updated = LocalDate.now();
        this.user = user;
    }

    @Override
    public String toString() {
        return "{content='" + content + "', updated=" + updated +
                ", user=" + user + '}';
    }
}

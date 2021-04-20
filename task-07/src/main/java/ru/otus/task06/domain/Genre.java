package ru.otus.task06.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity
@Table(name ="genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "kind", nullable = false, unique = true)
    private  String kind;

    public Genre(String kind) {
        this.kind = kind;
    }

    public Genre(long id, String kind) {
        this.id = id;
        this.kind = kind;
    }
}

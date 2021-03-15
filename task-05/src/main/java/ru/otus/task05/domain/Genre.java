package ru.otus.task05.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Genre {
    private  long id;
    private  String kind;

    public Genre(String kind) {
        this.kind = kind;
    }

    public Genre(long id, String kind) {
        this.id = id;
        this.kind = kind;
    }
}

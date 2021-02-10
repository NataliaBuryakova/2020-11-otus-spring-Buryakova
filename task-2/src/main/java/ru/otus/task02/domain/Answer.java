package ru.otus.task02.domain;

import lombok.*;

import java.util.Optional;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Answer {
    private String number;
    private  String text;
}

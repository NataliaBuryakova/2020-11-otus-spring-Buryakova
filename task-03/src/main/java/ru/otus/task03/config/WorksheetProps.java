package ru.otus.task03.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "worksheet")
@Getter
@Setter
public class WorksheetProps {

    private int answers;
}

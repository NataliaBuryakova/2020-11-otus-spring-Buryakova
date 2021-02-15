package ru.otus.task03.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class AppProps {
    private String language;
    private String fileName;
    public Locale getLocale() {
        return new Locale(language);
    }
}

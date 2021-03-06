package ru.otus.task04.config;

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
    public String getFileNameCsv (){
        return fileName+"_"+language+".csv";
    }
}

package ru.otus.task04.service;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.task04.config.AppProps;

@Service
public class MessageSourceServiceImpl implements MessageSourceService {

    private final  MessageSource messageSource;
    private final  AppProps appConfig;

    public MessageSourceServiceImpl(MessageSource messageSource, AppProps appConfig) {

        this.messageSource = messageSource;
        this.appConfig = appConfig;
    }

    /*public String getMessage(String message){
        return messageSource.getMessage(message, null, appConfig.getLocale());
    }*/
    public String getMessage(String message,String ...params){
        return messageSource.getMessage(message, params, appConfig.getLocale());
    }



}

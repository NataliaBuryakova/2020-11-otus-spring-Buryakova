package ru.otus.task03.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.task03.config.AppProps;

@Service
public class MessageSourceServiceImpl implements MessageSourceService {

    final private MessageSource messageSource;
    final private AppProps appConfig;

    public MessageSourceServiceImpl(MessageSource messageSource, AppProps appConfig) {

        this.messageSource = messageSource;
        this.appConfig = appConfig;
    }

    public String getMessage(String message){
        return messageSource.getMessage(message, null, appConfig.getLocale());
    }
    public String getMessage(String message,Object[] objects){
        return messageSource.getMessage(message, objects, appConfig.getLocale());
    }


}

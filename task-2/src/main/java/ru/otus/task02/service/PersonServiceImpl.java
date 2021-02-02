package ru.otus.task02.service;

import org.springframework.stereotype.Service;
import ru.otus.task02.domain.Person;
import ru.otus.task02.exception.PersonCreateException;
@Service
public class PersonServiceImpl implements PersonService{
    @Override
    public Person createPerson(String name) throws PersonCreateException {
        if (name.isEmpty()){
            throw new PersonCreateException("Name is Empty");
        }

        return new Person(name);
    }
}

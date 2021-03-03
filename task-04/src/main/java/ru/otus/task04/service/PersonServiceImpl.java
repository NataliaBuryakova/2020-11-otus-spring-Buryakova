package ru.otus.task04.service;

import org.springframework.stereotype.Service;
import ru.otus.task04.domain.Person;

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

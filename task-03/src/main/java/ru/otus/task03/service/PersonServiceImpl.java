package ru.otus.task03.service;

import org.springframework.stereotype.Service;
import ru.otus.task03.domain.Person;

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

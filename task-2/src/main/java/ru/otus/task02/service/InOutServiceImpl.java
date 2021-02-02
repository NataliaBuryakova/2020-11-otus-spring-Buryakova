package ru.otus.task02.service;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class InOutServiceImpl implements InOutService {
    @Override
    public Scanner initScanner(){
        return new Scanner(System.in);
    }
    @Override
    public String read(Scanner s) {
        return s.nextLine();
    }

    @Override
    public void println(String s) {
        System.out.println(s);
    }




}

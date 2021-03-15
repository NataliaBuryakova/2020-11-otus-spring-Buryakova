package ru.otus.task05.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InOutServiceImpl implements InOutService {
    private final Scanner scanner;
    private final PrintStream out;
    public InOutServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream out, @Value("#{T(java.lang.System).in}") InputStream in ) {
        this.out = out;
        this.scanner = new Scanner(in);
    }
    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void println(String s) {
        out.println(s);
    }






}

package ru.otus.task02.service;

import java.util.Scanner;

public interface InOutService {
    Scanner initScanner();
    String read(Scanner s);
    void println(String s);
}

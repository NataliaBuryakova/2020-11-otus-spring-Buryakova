package ru.otus.task03.service;

import ru.otus.task03.domain.Worksheet;

import java.util.List;

public interface WorksheetService {
    /**
     * Получить текст списка всех вопросов с вариантами ответов
     * @return
     * @throws TestBuildingException
     * @throws TestEmptyException
     */
    String getWorksheetText() throws TestBuildingException, TestEmptyException;

    /**
     * Получить список всех вопросов с вариантами ответов
     * @return
     * @throws TestEmptyException
     * @throws TestBuildingException
     */
    List<Worksheet> getWorksheetList() throws TestEmptyException, TestBuildingException;

    /**
     * Получить текст вопроса с вариантами ответов
     * @param worksheet
     * @return
     */
    String getTextFromQA(Worksheet worksheet);

    /**
     * Проверить правильность ответа
     * @param worksheet
     * @param answer
     * @return
     */
    Boolean isCorrectAnswer(Worksheet worksheet, String answer);





    Boolean isTestDone(int correctAnswers);
}

package ru.otus.task01.service;

public class StartServiceImpl implements StartService{
    private final WorksheetService worksheetService;
    public StartServiceImpl(WorksheetServiceImpl worksheetService) {
        this.worksheetService = worksheetService;
    }

    public void start()  {
        try {
            String questionsText = worksheetService.getWorksheetText();
            System.out.println(questionsText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

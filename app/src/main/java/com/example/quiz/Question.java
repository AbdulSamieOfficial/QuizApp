package com.example.quiz;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private List<String> options = new ArrayList<>();
    private String correctOption;
    private String newQuestion;

    public Question(String newQuestion,String correctOption, String ... options ) {
        this.newQuestion = newQuestion;
        this.correctOption = correctOption;
        this.options.add(options[0]);
        this.options.add(options[1]);
        this.options.add(options[2]);
        this.options.add(options[3]);
    }


    public String getNewQuestion() {
        return newQuestion;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectOption() {
        return correctOption;
    }
}

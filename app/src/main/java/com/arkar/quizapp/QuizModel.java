package com.arkar.quizapp;

public class QuizModel {

    private int mQuestion;
    private Boolean mAnswer;

    public QuizModel(int Question, Boolean Answer) {
        this.mQuestion = Question;
        this.mAnswer = Answer;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public Boolean getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(Boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}

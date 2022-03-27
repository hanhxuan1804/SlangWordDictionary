package com.studenthcmus;

public class RandomGame {
    public String question;
    public String[] answers;
    public int userAnswer;
    public String correctAnswer;

    public RandomGame(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
        this.userAnswer = -1;
        this.correctAnswer = "";
    }


    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return this.answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
    public int getUserAnswer() {
        return this.userAnswer;
    }

    public void printResult() {
        if (this.userAnswer == -1) {
            System.out.println("You haven't answered yet!");
        } else if (this.answers[this.userAnswer] == this.correctAnswer) {
            System.out.println("Correct Answer!");
        } else {
            System.out.println("Wrong Answer!");
        }
    }
    
}

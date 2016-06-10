package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class TextQuizUnit extends Unit {
    public TextQuizUnit(String question, String[] answers, int correctAnswer, String answerExplanation, String voiceUri) {
        this.mUnitType = UnitType.TextQuiz;

        this.mQuestion = question;
        this.mAnswers = answers;
        this.mCorrectAnswer = correctAnswer;
        this.mAnswerExplanation = answerExplanation;
        this.mVoiceUri = voiceUri;
    }
}

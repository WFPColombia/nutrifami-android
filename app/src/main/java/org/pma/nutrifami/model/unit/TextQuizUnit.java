package org.pma.nutrifami.model.unit;

/**
 * Created by Peter Juras on 01.06.16.
 */

public class TextQuizUnit extends Unit {
    public TextQuizUnit(String question, String[] answers, int correctAnswer, String answerExplanation, String voiceUri) {
        super(UnitType.TextQuiz, question, answers, correctAnswer, answerExplanation, voiceUri);
    }
}

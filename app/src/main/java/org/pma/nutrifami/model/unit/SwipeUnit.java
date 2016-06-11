package org.pma.nutrifami.model.unit;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeUnit extends Unit{
        public SwipeUnit(String question, String[] answers, int correctAnswer, String answerExplanation, String voiceUri) {
            super(UnitType.Swipe, question, answers, correctAnswer, answerExplanation, voiceUri);
    }

        public boolean isTrue() {
            return getCorrectAnswer() == 1;
        }
}

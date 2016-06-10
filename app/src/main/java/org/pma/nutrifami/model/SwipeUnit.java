package org.pma.nutrifami.model;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeUnit extends Unit{
        public SwipeUnit(String question, String[] answers, int correctAnswer, String answerExplanation, String voiceUri) {
                this.mUnitType = UnitType.Swipe;

                this.mQuestion = question;
                this.mAnswers = answers;
                this.mCorrectAnswer = correctAnswer;
                this.mAnswerExplanation = answerExplanation;
                this.mVoiceUri = voiceUri;
    }

        public boolean isTrue() {
                return this.mCorrectAnswer == 1;
        }
}

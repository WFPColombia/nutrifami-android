package org.pma.nutrifami.model.unit;

/**
 * Created by Peter on 05.06.2016.
 */
public class SwipeUnit extends Unit {
    private int mImage;
    private int explanationImage;

    public SwipeUnit(String question, String[] answers, int correctAnswer, String answerExplanation, int image, int explanationImage) {
        super(UnitType.Swipe, question, answers, correctAnswer, answerExplanation, null);
        this.mImage = image;
        this.explanationImage = explanationImage;
    }

    public boolean isTrue() {
        return getCorrectAnswer() == 1;
    }

    public int getImage() {
        return this.mImage;
    }

    public int getExplanationImage() {
        return explanationImage;
    }
}

package org.pma.nutrifami.model;

/**
 * Created by Peter Juras on 01.06.16.
 */

public abstract class Unit {
    private final UnitType mUnitType;
    private final String mQuestion;
    private final String[] mAnswers;
    private final int mCorrectAnswer;
    private final String mAnswerExplanation;
    private final String mVoiceUri;

    Unit(UnitType unitType, String question, String[] answers, int correctAnswer, String answerExplanation, String voiceUri) {
        this.mUnitType = unitType;
        this.mQuestion = question;
        this.mAnswers = answers;
        this.mCorrectAnswer = correctAnswer;
        this.mAnswerExplanation = answerExplanation;
        this.mVoiceUri = voiceUri;
    }

    public UnitType getGameType() {
        return this.mUnitType;
    }

    public String getQuestion() {
        return this.mQuestion;
    }

    public String[] getAnswers() {
        return this.mAnswers;
    }

    public int getCorrectAnswer() {
        return this.mCorrectAnswer;
    }

    public String getAnswerExplanation() {
        return this.mAnswerExplanation;
    }

    public String getVoiceUri() {
        return this.mVoiceUri;
    }
}
